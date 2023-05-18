package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clasa Orders, contine toate atributele necesare pentru gestionarea simplificată a unei comenzi
 * intr-un magazin online,acesta este identificat in mod unic prin intermediul unui ID
 *
 */
public class Orders {


    // o comanda va avea urmatoare atribute:
    // id, nume produs, nume +prenume
    // client, email, data

    private int id;
    private int productID;
    private int clientID;
    private  String date ;
    private int quantity;

    private int price;


    public Orders(){};

    public Orders(int id, int productID, int clientID, String date, int quanity, int price) {
        this.id = id;
        this.productID = productID;
        this.clientID = clientID;
        this.date = date;
        this.quantity = quanity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
