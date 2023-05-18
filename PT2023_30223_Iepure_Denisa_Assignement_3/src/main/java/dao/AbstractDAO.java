package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connectionDB.ConnectionFactory;
import model.Client;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Cea mai importantă clasă a întregului program, conține implementarea operațiilor CRUD, care accesează baza de date
 *
 * @param <T>
 */

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }


    // selecteaza interogari

    /**
     * Metoda care selectează dintr-un tabel o inregistrare , filtrată după un anumit camp
     *
     * @param field campul după care se face selecția din tabel
     * @return metoda returnează stringul cu intreaga interogare SQL
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?"+";");
        System.out.println(sb.toString());
        return sb.toString();
    }

    /**
     * Metoda care creeaza interogarea necesara pentru selectarea tuturor inregistrarilor
     * dintr-un tabel specific
     * @return interogarea sub forma de string
     */

    private String createFindAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());

        System.out.println(sb.toString());
        return sb.toString();
    }




    // inserarea unei interogari
    // INSERT INTO type.getSimpleName  VALUES(fields)
    // pt Client id, nume, prenume si email

    /**
     *Metoda care creeaza interogarea necesara inserarii unui nou tip de inregistrare intr-un tabel specific
     *
     * @param values valorile de inserat in fiecare coloana a tabelului , ele sunt despartinte prin ", "
     * @return interogarea sub forma de string
     */
    public String createInsertQuery(String values)
    {
        StringBuilder sb= new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName()); // tabelul
        sb.append(" (");
       // fieldurile fiecarei clase
        Field[] allFields = type.getDeclaredFields();
        for (int i = 0; i < allFields.length; i++) {
            Field f = allFields[i];

            // creez lista de fielduri separate prin spatiu
            // id-ul este ai=auto incremented, il evit
            if (!f.getName().equals("id")) // il exclud
            {

                sb.append(f.getName());
                // pun spatiu dupa ce termin cu numele unui field
                if (i != allFields.length - 1) {
                    sb.append(", ");
                }

            }
        }
            sb.append(")");
        sb.append(" VALUES ");
        //sb.append("(" +fields+ ")");
        sb.append("(" + values.replaceAll("(?<!'),(?=')|(?<=\\()'|'(?=\\))", "\\\\'") + ")"); // add single quotes to string values
        System.out.println(sb.toString());
        return sb.toString();

    }

    /**
     * Metoda care sterge o interogare dintr-un tabel specific , stergerea se face dupa id
     * @param id in functie de id( care este unic) se va face stergerea inregistrarii
     * @return interogarea sub forma de string
     */

    private String createDeleteQuery(int  id) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
      //  sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        sb.append("id= ?");
        //sb.append(id);
        System.out.println(sb.toString());
        return sb.toString();
    }


    /**
     *  Metoda care creeaza interogarea necesara updatarii unei inregistrari dintr-un tabel
     * @param values reprezinta toate valorile pe care inregistrarea trebuie sa le contina, acestea sunt separate
     *               printr-un spatiu asa ca folosindu-ma de un regex despart cuvintele si le stochez
     *             Ma folosesc de metoda getDeclaredFields pentru a lua campurile care trebuie modificate, exclud ID-ul
     *               deoarece acesta nu poate fi modificat
     * @param id Pentru a gasi inregistrarea care trebuie updatata
     * @return interogarea sub forma de string
     */
    public String createUpdatetQuery(String values, int id)
    {
        String[] words = values.split(" ");
        if (words.length == 3) {
            // Accesează cuvintele individual din vector
            String primulCuvant = words[0];
            String alDoileaCuvant = words[1];
            String alTreileaCuvant = words[2];
        }

        StringBuilder sb= new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName()); // tabelul
        sb.append(" SET ");

        // fieldurile fiecarei clase
        Field[] allFields = type.getDeclaredFields();
        for (int i = 0; i < allFields.length; i++) {
            Field f = allFields[i];

            // creez lista de fielduri separate prin spatiu
            // id-ul este ai=auto incremented, il evit
            if (!f.getName().equals("id")) // il exclud
            {

                sb.append(f.getName());
                sb.append("= ");
                sb.append("'").append(words[i]).append("'");

                // pun spatiu dupa ce termin cu numele unui field
                if (i != allFields.length -1) {

                    sb.append(", ");
                }


            }
        }

        sb.append(" WHERE ");
        sb.append("id= ");
        sb.append(id);
        System.out.println(sb.toString());
        return sb.toString();

    }

    /**
     * Aceasta metoda gaseste toate elementele include intr-un tabel
     * @return Lista cu toate elementele din tabelul respectiv
     */

    //selecteaza tot dintr-un tabel
    public List<T> findAll() {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createFindAll();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Aceasta metoda creeaza un tabel pe baza unei liste de elemente de un anumit tip.
     * Astfel, se iau campurile care reprezinta capul de tabel, iar dupa prin intermediul listei date ca parametru
     * adaugam datele intr-un tabel
     * @param objects Lista de un anumit tip
     * @param type Clasa specifica
     * @return interogarea in SQL sub forma de string
     */
    public JTable createTable(List <T> objects,Class<T> type)
    {JTable table;
        DefaultTableModel model;
        Field[] allFields = type.getDeclaredFields();
        // numele coloanelor
        String[] columnNames = new String[allFields.length];
        for (int i = 0; i < allFields.length; i++) {
            columnNames[i] = allFields[i].getName();
        }
        // lucram cu model deoarece lucram generic
        model = new DefaultTableModel(new Object[][] {}, columnNames);
        // adaugam date in tabel
        for (T c : objects) {
            Object[] rowData = new Object[allFields.length];
            for (int i = 0; i < allFields.length; i++) {
                try {
                    Field field = allFields[i];
                    field.setAccessible(true);
                    Object value = field.get(c);
                    rowData[i] = value;
                } catch (IllegalAccessException e) {
                    e.printStackTrace();}
            }
            model.addRow(rowData);
        }
// create JTable
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        return table;
    }

    /**
     * Metoda care se foloseste de interogarea generata de createSelectQuery si cauta intr-un tabel dupa id
     * @param id id-ul elementului care trebuie cautat
     * @return elementul care a fost gasit prin cautarea id-ului
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda care insereaza intr-un tabel valori noi
     * @param values noile valori ai unei noi inregistrari
     * @return elementul creat
     */

    public T insert(String values) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery(values);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            // nu avem nevoie de result set
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:insert " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda care sterge dintr-un tabel pe baza id-ului
     * @param id id-ul pe baza caruia se face stergerea
     * @return elementul sters
     */
    public T delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createDeleteQuery(id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
         statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda care cauta inregistrarea pe baza id-ului si updateaza campurile
     * @param values cu ce se updateaza campurile
     * @param id unde se updateaza campurile
     * @return elementul updatat
     */
    public T update(String values, int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createUpdatetQuery(values,id);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            // nu avem nevoie de result set
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:update " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }



}
