package BLL;

import dao.ProductDAO;
import model.Client;
import model.Product;

import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Clasa in care se realizeaza logica ce sta la baza gestionarii tabelului Product
 */
public class ProductsBLL {


    private ProductDAO productsDao;

    public ProductsBLL()
    {
        productsDao= new ProductDAO();
    }

    //-----------------FIND BY ID -------------------
    public Product findProductById(int id)
    {
        Product pr= productsDao.findById(id);
        if (pr==null)
        {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return pr;
    }


    //------------------FIND ALL--------------------
    public List<Product> findAll_BLL()
    {

        List<Product> products=productsDao.findAll();
        if (products.isEmpty())
        {
            throw new NoSuchElementException("Eroare findAll");

        }
        return products;
    }

    //------------------CREATE TABLE ----------------------

    public JTable tableCreation()
    {
        JTable table=productsDao.createTable(productsDao.findAll(),Product.class);

        return table;

    }

   //--------------------INSERT---------------------------

    public Product insert_BLL(String values)
    {
        Product product=productsDao.insert(values);
        if (product!=null)
        {
            throw new NoSuchElementException("Eroare inserare");

        }
        return product;
    }

    //--------------------DELETE--------------------------

    public Product delete_BLL(int id)
    {
        Product product=productsDao.delete(id);
        if (product!=null)
        {
            throw new NoSuchElementException("Eroare delete");

        }
        return product;
    }

    //-------------------UPDATE---------------------------

    public Product update_BLL(String values, int id)
    {

        Product product=productsDao.update(values,id);

        if (product!=null)
        {
            throw new NoSuchElementException("Eroare update");

        }
        return product;
    }


}
