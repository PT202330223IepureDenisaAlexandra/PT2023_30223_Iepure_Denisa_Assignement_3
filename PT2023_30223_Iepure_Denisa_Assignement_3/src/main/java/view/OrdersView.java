package view;

import BLL.ClientBLL;
import BLL.OrdersBLL;
import BLL.ProductsBLL;
import dao.BillDAO;
import model.Bill;
import model.Client;
import model.Orders;
import model.Product;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.invoke.TypeDescriptor;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 * In aceasta clasa se creeaza frame-ul pentru Orders , cat si controller-ul pentru toate operatiile care se desfasoara
 */

public class OrdersView {

    private JFrame frame;
    JComboBox<String> clients = new JComboBox<String>();
    private JScrollPane scrollPane;
    private  int id_produs_selectat;
    private Integer id_client_selectat;
    private Integer cantitateSelectata;
    private Integer pretProdus;

    private String produs_selected;

    private JLabel myLabel;
    ImageIcon backgr;
    private JButton orderr;
    private JButton viewOrders;

    private JButton  applyO;
    private JButton  backButton;
    private JButton  bilss;
    private String numeClient;
    private String prenumeClient;
    private String numeProdus;


    public OrdersView()
    {
        backgr= new ImageIcon(this.getClass().getResource("/we.png"));
        myLabel= new JLabel(backgr);
        myLabel.setSize(901,701);
        myLabel.setLayout(null);

        frame= new JFrame("ORDERS");
        frame.add(myLabel);
        frame.setSize(901,701);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        Image icon=new ImageIcon(this.getClass().getResource("/store.png")).getImage();
        frame.setIconImage(icon);

        orderr=new JButton("Make an order");
        orderr.setBounds(50, 50, 200, 40);
        orderr.setBackground(Color.BLACK);
        orderr.setForeground(Color.WHITE);
        myLabel.add(orderr);

        bilss=new JButton("Bills");
        bilss.setBounds(50, 150, 200, 40);
        bilss.setBackground(Color.BLACK);
        bilss.setForeground(Color.WHITE);
        myLabel.add(bilss);

        applyO=new JButton("APPLY");
        applyO.setBounds(470, 50, 200, 40);
        applyO.setBackground(Color.BLACK);
        applyO.setForeground(Color.WHITE);


        viewOrders=new JButton("View orders");
        viewOrders.setBounds(50, 100, 200, 40);
        viewOrders.setBackground(Color.BLACK);
        viewOrders.setForeground(Color.WHITE);
        myLabel.add(viewOrders);


// ...


        JTextField textField1 = new JTextField();
        textField1.setBounds(470, 100, 200, 30);

        myLabel.add(textField1);

        JTextField textField2 = new JTextField();
        textField2.setBounds(470, 140, 200, 30);
        myLabel.add(textField2);
        JTextField textField3 = new JTextField();
        textField3.setBounds(470, 180, 200, 30);

// Obțineți data curentă
        Date currentDate = new Date();

// Formatați data în formatul dorit
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);

        textField3.setText(formattedDate);


        myLabel.add(textField3);

        JTextField textField4 = new JTextField();
        textField4.setBounds(470, 220, 200, 30);
        myLabel.add(textField4);

        JTextField textField5 = new JTextField();
        textField5.setBounds(470, 260, 200, 30);
        myLabel.add(textField5);

        // Rest of your code ...
        textField1.setVisible(false);
        textField2.setVisible(false);
        textField3.setVisible(false);
        textField4.setVisible(false);
        textField5.setVisible(false);






        List<Client> clientsList=new ArrayList<>();
        ClientBLL clientBLL= new ClientBLL();
        clientsList= clientBLL.findAll_BLL();




        orderr.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub


                Component[] components = myLabel.getComponents();
                for (Component c : components) {
                    if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField || c instanceof JComboBox<?>) {
                        myLabel.remove(c);
                    }
                }

                myLabel.revalidate();
                myLabel.repaint();

                myLabel.add(applyO);
                myLabel.add(textField1);
                myLabel.add(textField2);
                myLabel.add(textField3);
                myLabel.add(textField4);
                myLabel.add(textField5);


                textField1.setVisible(true);
                textField2.setVisible(true);
                textField3.setVisible(true);
                textField4.setVisible(true);
                textField5.setVisible(true);
                Date currentDate = new Date();

// Formatați data în formatul dorit
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String formattedDate = dateFormat.format(currentDate);

                textField3.setText(formattedDate);


                List<Client> clientsList=new ArrayList<>();
                ClientBLL clientBLL= new ClientBLL();
                clientsList= clientBLL.findAll_BLL();
                String sb= new String();
                for (Client client : clientsList) {
                    sb=client.getId()+" "+client.getLastName()+" "+client.getFirstName();
                    System.out.println(sb);

                    clients.addItem(sb);
                }

                clients.setBounds(20, 300, 200, 40);
                clients.setVisible(false); // Set visibility to false initially
                myLabel.add(clients);



                clients.setVisible(true);
// add JScrollPane to container

                clients.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        myLabel.add(applyO);
                        String selectedClient = (String) clients.getSelectedItem();
                        String[] words = selectedClient.split(" ");
                        if (words.length > 0) {
                            String firstWord = words[0];
                            id_client_selectat=Integer.parseInt(firstWord);
                        }// Perform actions based on the selected client

                        if (words.length >= 2) {
                            // Retrieve the second and third words as numeClient and prenumeClient
                             numeClient = words[1];
                             prenumeClient = words[2];
                        }

                        textField2.setText(String.valueOf(id_client_selectat));


                    }

                });


               ProductsBLL productsBLL= new ProductsBLL();
                JTable table=productsBLL.tableCreation();
                scrollPane = new JScrollPane(table);
                scrollPane.setBounds(400, 300, 350, 300);
             myLabel.add(scrollPane);

                table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                    @Override
                    public void valueChanged(ListSelectionEvent event) {
                        // Check if the selection is still adjusting and return if true
                        if (event.getValueIsAdjusting()) {
                            return;
                        }

                        // Get the selected row index
                        int rowIndex = table.getSelectedRow();

                        // Get the values of the selected row
                        id_produs_selectat = (int) table.getValueAt(rowIndex, 0);
                        produs_selected = (String) table.getValueAt(rowIndex, 1);
                        cantitateSelectata = (Integer) table.getValueAt(rowIndex, 2);
                         pretProdus = (Integer) table.getValueAt(rowIndex, 3);

                        // Print the values to the console
                        System.out.println("Selected row: " + id_produs_selectat + ", " + produs_selected + ", " + cantitateSelectata + ", " + pretProdus);

                        textField1.setText(String.valueOf(id_produs_selectat)); // id produs
                        // textField4.setText(String.valueOf(firstName_selected));  // cantitate
                        //textField5.setText(String.valueOf(email_selected.toString())); //pret

                    }
                });


            }

        });
        textField4.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updatePrice();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updatePrice();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updatePrice();
            }

            private void updatePrice() {
                // Obțineți cantitatea din textField4
                String quantityText = textField4.getText();

                // Verificați dacă cantitatea este un număr valid
                try {
                    int quantity = Integer.parseInt(quantityText);

                    // Verificați dacă stocul produsului este suficient
                    if (quantity <=  cantitateSelectata) { // înlocuiți "stocProdus" cu stocul real al produsului
                        // Calculați prețul
                        double price = quantity *  pretProdus; // înlocuiți "pretProdus" cu prețul real al produsului




                        // Actualizați textField5 cu prețul calculat
                        textField5.setText(String.valueOf(price));
                    } else {
                        JOptionPane.showMessageDialog(myLabel, "Cantitatea introdusă depășește stocul disponibil.",
                                "Eroare", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(myLabel, "Valoare invalida.",
                            "Eroare", JOptionPane.ERROR_MESSAGE);
                }
            }


        });

        applyO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textField1Value = textField1.getText();
                String textField2Value = textField2.getText();
                String textField3Value = textField3.getText();
                String textField4Value = textField4.getText();
                String textField5Value = textField5.getText();


                String result = "'" + textField1Value + "', '" + textField2Value + "', '" + textField3Value + "', '" + textField4Value + "', '" + textField5Value + "'";
                System.out.println("Result: " + result);
                OrdersBLL ordersBLL = new OrdersBLL();

                Orders ord = ordersBLL.insert_BLL(result);
                Random random = new Random();
                int randomNumber = random.nextInt(10000); // Generates a random number between 0 (inclusive) and 10000 (exclusive)

                String[] parts = new String[0];
                if (textField5Value.contains(".")) {
                    parts = textField5Value.split("\\.");
                }
                String integerPart = null;
                if (parts.length > 0) {
                    integerPart = parts[0];
                }
                // generare fisier text
                Bill bill = new Bill(randomNumber, numeClient, prenumeClient, produs_selected, Integer.parseInt(textField4Value), Integer.parseInt(integerPart), textField3Value,pretProdus);
                bill.generateBill();
                // incerc insert in tabelul bill
                String values = "'" + randomNumber + "', '" + numeClient + "', '" + prenumeClient + "', '" + produs_selected + "', '" + Integer.parseInt(textField4Value) + "', '" + Integer.parseInt(integerPart) + "', '" + textField3Value + "', '" + pretProdus + "'";

                BillDAO billDAO= new BillDAO();
                Bill bill1= billDAO.insert(values);



                String namePr = produs_selected;
                String quant = String.valueOf(Integer.parseInt(String.valueOf(cantitateSelectata)) - Integer.parseInt(textField4Value));
                String pric = String.valueOf(pretProdus);

                String value = " " + namePr + " " + quant + " " + pric + " ";
                System.out.println(value);
                ProductsBLL productsBLL = new ProductsBLL();

                Product product = productsBLL.update_BLL(value, (Integer) id_produs_selectat);

            }
        });

        viewOrders.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                myLabel.remove(applyO);
                Component[] components = myLabel.getComponents();
                for (Component c : components) {
                    if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField || c instanceof JComboBox<?>) {
                        myLabel.remove(c);
                    }
                }

                myLabel.revalidate();
                myLabel.repaint();

              OrdersBLL ordersBLL= new OrdersBLL();
             // ordersBLL.findAll_BLL();
              JTable table=ordersBLL.tableCreation();

                scrollPane = new JScrollPane(table);
                scrollPane.setBounds(420, 100, 400, 400);

// add JScrollPane to container
                myLabel.add(scrollPane);


            }
        });


        bilss.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myLabel.remove(applyO);
                Component[] components = myLabel.getComponents();
                for (Component c : components) {
                    if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField || c instanceof JComboBox<?>) {
                        myLabel.remove(c);
                    }
                }

                myLabel.revalidate();
                myLabel.repaint();

              BillDAO billDAO= new BillDAO();
                List<Bill> bils= billDAO.findAll();
             JTable table= billDAO.createTable(bils,Bill.class);
                scrollPane = new JScrollPane(table);
                scrollPane.setBounds(280, 100, 600, 400);

// add JScrollPane to container
                myLabel.add(scrollPane);

            }
        });



        backButton = new JButton("BACK");
        backButton.setBounds(20, 5, 80, 30);

        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        myLabel.add(backButton);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MainFr();

            }
        });





        frame.setVisible(true);


    }
}
