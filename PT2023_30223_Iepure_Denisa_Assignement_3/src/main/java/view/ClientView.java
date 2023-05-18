package view;

import BLL.ClientBLL;
import BLL.validators.Validator;
import dao.ClientDAO;
import model.Client;
import org.example.Main;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

/**
 * In aceasta clasa se creeaza frame-ul pentru Client , cat si controller-ul pentru toate operatiile CRUD
 */
public class ClientView {
    private JFrame frame;

    private JLabel myLabel;
    JTable table;

  private  Object id_selected;
    private Object id_selected_update;

    private JButton rButtonClient;
    private JButton rButtonOrders;
    private JButton backButton;
    private JScrollPane scrollPane;

    JComboBox<String> comboBox = new JComboBox<String>();
    JButton rButtonApply;
    JButton rButtonView;

    private JTable viewClients;


    private JButton makeChanges;
    private  DefaultTableModel model;

    JTextField nume = new JTextField("Last name");
    JTextField prenume = new JTextField("First name");
    JTextField email = new JTextField("email");

    JTextField numeUpdate;
    JTextField prenumeUpdate ;
    JTextField emailUpdate ;
    ImageIcon backgr;
    JLabel l1;
    public ClientView(String text) {
        backgr= new ImageIcon(this.getClass().getResource("/client.jpg"));
        myLabel= new JLabel(backgr);
        myLabel.setSize(600,500);

        frame= new JFrame("Clients");
        frame.add(myLabel);
        frame.setSize(600,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        nume.setBounds(350, 150, 200, 40);
        myLabel.add(nume);

        prenume.setBounds(350, 200, 200, 40);
        myLabel.add(prenume);

        email.setBounds(350, 250, 200, 40);
        myLabel.add(email);



        nume.setVisible(false);
        prenume.setVisible(false);
        email.setVisible(false);



        Image icon=new ImageIcon(this.getClass().getResource("/customer.png")).getImage();
        frame.setIconImage(icon);

        comboBox.addItem("ADD client");
        comboBox.addItem("UPDATE client");
        comboBox.addItem("DELETE client");
        comboBox.addItem("VIEW client");
        comboBox.setBounds(20, 150, 200, 40);
        myLabel.add(comboBox);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                if (selectedOption.equals("ADD client")) {

                    Component[] components = myLabel.getComponents();
                    for (Component c : components) {
                        if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField) {
                            myLabel.remove(c);
                        }
                    }

                    myLabel.revalidate();
                    myLabel.repaint();


                    myLabel.add(nume);
                    myLabel.add(prenume);
                    myLabel.add(email);

                    nume.setVisible(true);
                    prenume.setVisible(true);
                    email.setVisible(true);
                    nume.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (nume.getText().equals("Last name")) {
                                nume.setText("");
                            }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            if (nume.getText().isEmpty()) {
                                nume.setText("Last name");
                            }
                        }
                    });

                    prenume.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (prenume.getText().equals("First name")) {
                                prenume.setText("");
                            }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            if (prenume.getText().isEmpty()) {
                                prenume.setText("First name");
                            }
                        }
                    });

                    email.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (email.getText().equals("email")) {
                                email.setText("");
                            }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            if (email.getText().isEmpty()) {
                                email.setText("email");
                            }
                        }
                    });
                } else if (selectedOption.equals("UPDATE client")) {
                    // perform UPDATE product action

                        Component[] components = myLabel.getComponents();
                        for (Component c : components) {
                            if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField) {
                                myLabel.remove(c);
                            }
                        }

                        myLabel.revalidate();
                        myLabel.repaint();
                    ClientBLL clientBLL= new ClientBLL();
                    JTable table=clientBLL.tableCreation();

// create JScrollPane for JTable
                    scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(220, 50, 350, 300);

// add JScrollPane to container
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
                            id_selected_update = table.getValueAt(rowIndex, 0);
                            Object lastName_selected = table.getValueAt(rowIndex, 1);
                            Object firstName_selected = table.getValueAt(rowIndex, 2);
                            Object email_selected = table.getValueAt(rowIndex, 3);

                            // Print the values to the console
                            System.out.println("Selected row: " + id_selected + ", " + lastName_selected + ", " + firstName_selected + ", " + email_selected);

                            numeUpdate= new JTextField(lastName_selected.toString());

                            prenumeUpdate=new JTextField(firstName_selected.toString());
                            emailUpdate=new JTextField(email_selected.toString());



                            Component[] components = myLabel.getComponents();
                            for (Component c : components) {
                                if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField) {
                                    myLabel.remove(c);
                                }
                            }

                            myLabel.revalidate();
                            myLabel.repaint();
                            numeUpdate.setBounds(350, 150, 200, 40);
                            myLabel.add(numeUpdate);

                            prenumeUpdate.setBounds(350, 200, 200, 40);
                            myLabel.add(prenumeUpdate);

                            emailUpdate.setBounds(350, 250, 200, 40);
                            myLabel.add(emailUpdate);

                            prenumeUpdate.setVisible(true);
                            emailUpdate.setVisible(true);

                            numeUpdate.setVisible(true);


                            numeUpdate.addFocusListener(new FocusListener() {
                                @Override
                                public void focusGained(FocusEvent e) {

                                    if (numeUpdate.getText().equals(lastName_selected.toString())) {
                                        numeUpdate.setText("");
                                    }
                                }

                                @Override
                                public void focusLost(FocusEvent e) {
                                    if (numeUpdate.getText().isEmpty()) {
                                        numeUpdate.setText(lastName_selected.toString());
                                    }
                                }
                            });

                            prenumeUpdate.addFocusListener(new FocusListener() {
                                @Override
                                public void focusGained(FocusEvent e) {
                                    if (prenumeUpdate.getText().equals(firstName_selected.toString())) {
                                        prenumeUpdate.setText("");
                                    }
                                }

                                @Override
                                public void focusLost(FocusEvent e) {
                                    if (prenumeUpdate.getText().isEmpty()) {
                                        prenumeUpdate.setText(firstName_selected.toString());
                                    }
                                }
                            });

                            emailUpdate.addFocusListener(new FocusListener() {
                                @Override
                                public void focusGained(FocusEvent e) {
                                    if (emailUpdate.getText().equals(email_selected.toString())) {
                                        emailUpdate.setText("");
                                    }
                                }

                                @Override
                                public void focusLost(FocusEvent e) {
                                    if (emailUpdate.getText().isEmpty()) {
                                        emailUpdate.setText(email_selected.toString());
                                    }
                                }
                            });


                        }
                    });



                } else if (selectedOption.equals("DELETE client")) {
                    Component[] components = myLabel.getComponents();
                    for (Component c : components) {
                        if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField) {
                            myLabel.remove(c);
                        }
                    }

                    myLabel.revalidate();
                    myLabel.repaint();

                    ClientBLL clientBLL= new ClientBLL();
                    JTable table=clientBLL.tableCreation();

// create JScrollPane for JTable
                    scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(220, 50, 350, 300);

// add JScrollPane to container
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
                            id_selected = table.getValueAt(rowIndex, 0);
                            Object name_selected = table.getValueAt(rowIndex, 1);
                            Object quanity_selected = table.getValueAt(rowIndex, 2);
                            Object price_selected = table.getValueAt(rowIndex, 3);

                            // Print the values to the console
                            System.out.println("Selected row: " + id_selected + ", " + name_selected + ", " + quanity_selected + ", " + price_selected);


                        }
                    });



                    // perform DELETE product action
                } else if (selectedOption.equals("VIEW client")) {
                    // perform VIEW products action

                    Component[] components = myLabel.getComponents();
                    for (Component c : components) {
                        if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField) {
                            myLabel.remove(c);
                        }
                    }

                    myLabel.revalidate();
                    myLabel.repaint();


                    //numeUpdate.setVisible(false);
                    //prenumeUpdate.setVisible(false);
                    //email.setVisible(false);

                    ClientBLL clientBLL= new ClientBLL();
                    JTable table=clientBLL.tableCreation();

// create JScrollPane for JTable
                    scrollPane = new JScrollPane(table);
                    scrollPane.setBounds(220, 50, 350, 300);

// add JScrollPane to container
                    myLabel.add(scrollPane);

                }
            }
        });



        rButtonApply = new JButton("APPLY");
        rButtonApply.setBounds(150, 400, 200, 40);
        rButtonApply.setBackground(Color.BLACK);
        rButtonApply.setForeground(Color.WHITE);
        myLabel.add(rButtonApply);


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


        /** la inserarea unui client, cat si la updatarea unui client apelez metoda validate pentru validarea emailului
         * /
         */
        rButtonApply.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub

                String selectedOption = (String) comboBox.getSelectedItem();
                if (selectedOption.equals("ADD client")) {
                    // Make the text fields visible
                    String lastName = nume.getText();
                    String firstName = prenume.getText();
                    String email_str = email.getText();
                    //  String value=2+", "+lastName+", "+firstName+", "+email_str;
                    String value = "'" + lastName + "', '" + firstName + "', '" + email_str + "'";

                    System.out.println(value);
                    ClientBLL clientBLL= new ClientBLL();
                    Validator<Client> valEmail= clientBLL.getEmailValidator();
                    try {
                        valEmail.validate(email_str);
                        Client client=clientBLL.insert_BLL(value);
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
                    }


                }
                if (selectedOption.equals("DELETE client"))
                {


                  System.out.println(id_selected);
                    ClientBLL clientBLL= new ClientBLL();
                    Client client = clientBLL.delete_BLL((Integer)id_selected);

                }
                if (selectedOption.equals("UPDATE client"))
                {
                    String lastName = numeUpdate.getText();
                    String firstName = prenumeUpdate.getText();
                    String email_str = emailUpdate.getText();

                    String value =" "+lastName + " " + firstName + " " + email_str+" ";

                    System.out.println(value);
                    ClientBLL clientBLL= new ClientBLL();

                    Validator<Client> valEmail= clientBLL.getEmailValidator();
                    try {
                        valEmail.validate(email_str);
                        Client client = clientBLL.update_BLL(value, (Integer) id_selected_update);


                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Validation Error", JOptionPane.ERROR_MESSAGE);
                    }


                }


            }

        });

    }



}
