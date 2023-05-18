package BLL;

import BLL.validators.EmailValidator;
import BLL.validators.Validator;
import dao.ClientDAO;
import model.Client;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa in care se realizeaza logica ce sta la baza gestionarii tabelului Client
 */
public class ClientBLL {

     private ClientDAO clientDAO;
    private Validator<Client> emailValidator;

    public ClientBLL()
    {
        emailValidator=  new EmailValidator();
        clientDAO= new ClientDAO();
    }
// --------------------FIND BY ID--------------------
    public Client findClientById(int id)
    {
        Client cl=clientDAO.findById(id);
        if (cl==null)
        {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return cl;
    }
//-----------------FIND ALL------------------------
    public List<Client> findAll_BLL()
    {
        List<Client> clients=clientDAO.findAll();
        if (clients.isEmpty())
        {
            throw new NoSuchElementException("Eroare findAll");

        }
        return clients;
    }
//---------------CREATE TABLE------------------

    public JTable tableCreation()
    {
      JTable table=clientDAO.createTable(clientDAO.findAll(),Client.class);

       return table;

    }

    //------------------INSERT-------

    public Client insert_BLL(String values)
    {
    Client cl=clientDAO.insert(values);
    if (cl!=null)
    {
        throw new NoSuchElementException("Eroare inserare");

    }
    return cl;
    }

    // -----------------DELETE------------------
    public Client delete_BLL(int id)
    {
        Client cl=clientDAO.delete(id);
        if (cl!=null)
        {
            throw new NoSuchElementException("Eroare delete");

        }
        return cl;
    }
  // ----------------UPDATE-------------------
    public Client update_BLL(String values, int id)
    {
        Client cl=clientDAO.update(values,id);

        if (cl!=null)
        {
            throw new NoSuchElementException("Eroare update");

        }
        return cl;
    }

    public Validator<Client> getEmailValidator() {
        return emailValidator;
    }
}
