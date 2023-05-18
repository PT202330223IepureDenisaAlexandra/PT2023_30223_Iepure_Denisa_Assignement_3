package BLL;

import dao.OrdersDAO;

import model.Client;
import model.Orders;



import javax.swing.*;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Clasa in care se realizeaza logica ce sta la baza gestionarii tabelului Orders
 */
public class OrdersBLL {


    private OrdersDAO ordersDAO;

    public OrdersBLL()
    {
        ordersDAO=new OrdersDAO();
    }


    //-------------------FIND BY ID-----------------
    public Orders findObjectByID(int id)
    {
        Orders ord=ordersDAO.findById(id);
        if (ord==null)
        {
            throw new NoSuchElementException("The student with id =" + id + " was not found!");
        }
        return ord;
    }

    //-----------------FIND ALL------------------------
    public List<Orders> findAll_BLL()
    {
        List<Orders> orders=ordersDAO.findAll();
        if (orders.isEmpty())
        {
            throw new NoSuchElementException("Eroare findAll");

        }
        return orders;
    }

    // -------------INSERT CU UPDATE ----------------
    public Orders insert_BLL(String values)
    {

        Orders orders=ordersDAO.insert(values);
        if(orders!=null)
        {
            throw new NoSuchElementException("Eroare inserare");

        }

        return orders;
    }

    //-------------CREATE TABLE-------------
    public JTable tableCreation()
    {
        JTable table=ordersDAO.createTable(ordersDAO.findAll(), Orders.class);

        return table;

    }








}
