package model;

/**
 * Clasa Client, contine toate atributele necesare pentru gestionarea simplificată a unui client
 * intr-un magazin online,acesta este identificat in mod unic prin intermediul unui ID
 *
 */

public class Client {
    // id, nume, prenume ,email

    private int id;
    private String lastName;
    private String firstName;
    private String email;

    public Client(){};
    public Client(int id, String lastName, String firstName, String email) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
