package model;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Clasa pentru generarea facturii comenzii: scrierea intr-un fisier text si
 * crearea unui tabel  pe baza facturilor generate
 */
public class Bill {

    private int idBill;
    private String numeClient;
   private String prenumeClient;
    private String numeProdus;
    private int cantitate;
   private int pret;
    private String date;
    private int pretBuc;

public Bill()
{}
    public Bill(int idBill, String numeClient, String prenumeClient, String numeProdus, int cantitate, int pret, String date, int pretBuc) {
        this.idBill = idBill;
        this.numeClient = numeClient;
        this.prenumeClient = prenumeClient;
        this.numeProdus = numeProdus;
        this.cantitate = cantitate;
        this.pret = pret;
        this.date = date;
        this.pretBuc=pretBuc;
    }

    public void generateBill()
    {
        String filename = "Bill_" + idBill + ".txt";
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Bill ID: " + idBill + "\n");
            writer.write("Client Name: " + numeClient + " " + prenumeClient + "\n");
            writer.write("Product Name: " + numeProdus + "\n");
            writer.write("Quantity: " + cantitate + "\n");
            writer.write("Total price: " + pret +"-------------"+ "quantity  x  price   --" + cantitate +"  X  " +pretBuc+"\n");
            writer.write("Date: " + date + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Failed to generate the bill. Reason: " + e.getMessage());
        }
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getPrenumeClient() {
        return prenumeClient;
    }

    public void setPrenumeClient(String prenumeClient) {
        this.prenumeClient = prenumeClient;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPretBuc() {
        return pretBuc;
    }

    public void setPretBuc(int pretBuc) {
        this.pretBuc = pretBuc;
    }
}
