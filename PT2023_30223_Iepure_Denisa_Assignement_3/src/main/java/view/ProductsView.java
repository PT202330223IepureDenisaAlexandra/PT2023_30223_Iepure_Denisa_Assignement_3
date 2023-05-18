package view;

import BLL.ProductsBLL;
import dao.ClientDAO;
import dao.ProductDAO;
import model.Client;
import model.Product;
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
 * In aceasta clasa se creeaza frame-ul pentru Product , cat si controller-ul pentru toate operatiile CRUD
 */

public class ProductsView {

    private  Object id_selected;
    private Object id_selected_update;

    private JFrame frame;

    private JLabel myLabel;
    private JButton  backButton;
/*
    private JButton rButtonClient;
    private JButton rButtonOrders;
    private JButton rButtonProducts;*/

    JComboBox<String> comboBox = new JComboBox<String>();

    JButton rButtonApply;
    JButton rButtonView;
    private  DefaultTableModel model;
    private JScrollPane scrollPane;


    private JButton makeChanges;

    JTextField nameText = new JTextField("product name");
    JTextField quantityText = new JTextField("quantity");
    JTextField priceText = new JTextField("price/quantity");

    JTextField nameTextUpdate;
    JTextField quantityTextUpdate;

    JTextField priceTextUpdate;
    ImageIcon backgr;
    JLabel l1;
    public ProductsView(String text) {
        backgr= new ImageIcon(this.getClass().getResource("/client.jpg"));
        myLabel= new JLabel(backgr);
        myLabel.setSize(600,500);

        frame= new JFrame("Products");
        frame.add(myLabel);
        frame.setSize(600,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);
        nameText.setBounds(350, 150, 200, 40);
        myLabel.add(nameText);

        quantityText.setBounds(350, 200, 200, 40);
        myLabel.add(quantityText);

        priceText.setBounds(350, 250, 200, 40);
        myLabel.add(priceText);

        nameText.setVisible(false);
        quantityText.setVisible(false);
        priceText.setVisible(false);

        Image icon=new ImageIcon(this.getClass().getResource("/package.png")).getImage();
        frame.setIconImage(icon);


        comboBox.addItem("ADD product");
        comboBox.addItem("UPDATE product");
        comboBox.addItem("DELETE product");
        comboBox.addItem("VIEW products");
        comboBox.setBounds(20, 150, 200, 40);
        myLabel.add(comboBox);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOption = (String) comboBox.getSelectedItem();
                if (selectedOption.equals("ADD product")) {
                    // perform ADD product action
                    Component[] components = myLabel.getComponents();
                    for (Component c : components) {
                        if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField) {
                            myLabel.remove(c);
                        }
                    }

                    myLabel.revalidate();
                    myLabel.repaint();

                    myLabel.add(nameText);
                    myLabel.add(quantityText);
                    myLabel.add(priceText);

                    nameText.setVisible(true);
                    quantityText.setVisible(true);
                    priceText.setVisible(true);


                    nameText.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (nameText.getText().equals("product name")) {
                                nameText.setText("");
                            }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            if (nameText.getText().isEmpty()) {
                                nameText.setText("product name");
                            }
                        }
                    });

                    quantityText.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (quantityText.getText().equals("quantity")) {
                                quantityText.setText("");
                            }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            if (quantityText.getText().isEmpty()) {
                                quantityText.setText("quantity");
                            }
                        }
                    });

                    priceText.addFocusListener(new FocusListener() {
                        @Override
                        public void focusGained(FocusEvent e) {
                            if (priceText.getText().equals("price/quantity")) {
                                priceText.setText("");
                            }
                        }

                        @Override
                        public void focusLost(FocusEvent e) {
                            if (priceText.getText().isEmpty()) {
                                priceText.setText("price/quantity");
                            }
                        }
                    });

                } else if (selectedOption.equals("UPDATE product")) {


                    Component[] components = myLabel.getComponents();
                    for (Component c : components) {
                        if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField) {
                            myLabel.remove(c);
                        }
                    }

                    myLabel.revalidate();
                    myLabel.repaint();

                    ProductsBLL productsBLL= new ProductsBLL();
                    JTable table=productsBLL.tableCreation();
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
                            Object nameText_selected = table.getValueAt(rowIndex, 1);
                            Object quantityText_selected = table.getValueAt(rowIndex, 2);
                            Object priceText_selected = table.getValueAt(rowIndex, 3);

                            // Print the values to the console
                            System.out.println("Selected row: " + id_selected + ", " + nameText_selected + ", " + quantityText_selected + ", " + priceText_selected);

                            nameTextUpdate= new JTextField(nameText_selected.toString());

                            quantityTextUpdate=new JTextField(quantityText_selected.toString());
                            priceTextUpdate=new JTextField(priceText_selected.toString());



                            Component[] components = myLabel.getComponents();
                            for (Component c : components) {
                                if (c instanceof JScrollPane) {
                                    myLabel.remove(c);
                                } else if (c instanceof JTable) {
                                    myLabel.remove(c);
                                }else if (c instanceof JTextField) {
                                    myLabel.remove(c);
                                }
                            }
                            myLabel.revalidate();
                            myLabel.repaint();
                            nameTextUpdate.setBounds(350, 150, 200, 40);
                            myLabel.add(nameTextUpdate);

                            quantityTextUpdate.setBounds(350, 200, 200, 40);
                            myLabel.add(quantityTextUpdate);

                            priceTextUpdate.setBounds(350, 250, 200, 40);
                            myLabel.add(priceTextUpdate);

                            nameTextUpdate.setVisible(true);
                            quantityTextUpdate.setVisible(true);

                            priceTextUpdate.setVisible(true);


                            nameTextUpdate.addFocusListener(new FocusListener() {
                                @Override
                                public void focusGained(FocusEvent e) {

                                    if (nameTextUpdate.getText().equals(nameText_selected.toString())) {
                                        nameTextUpdate.setText("");
                                    }
                                }

                                @Override
                                public void focusLost(FocusEvent e) {
                                    if (nameTextUpdate.getText().isEmpty()) {
                                        nameTextUpdate.setText(nameText_selected.toString());
                                    }
                                }
                            });

                            quantityTextUpdate.addFocusListener(new FocusListener() {
                                @Override
                                public void focusGained(FocusEvent e) {
                                    if (quantityTextUpdate.getText().equals(quantityText_selected.toString())) {
                                        quantityTextUpdate.setText("");
                                    }
                                }

                                @Override
                                public void focusLost(FocusEvent e) {
                                    if (quantityTextUpdate.getText().isEmpty()) {
                                        quantityTextUpdate.setText(quantityText_selected.toString());
                                    }
                                }
                            });

                            priceTextUpdate.addFocusListener(new FocusListener() {
                                @Override
                                public void focusGained(FocusEvent e) {
                                    if (priceTextUpdate.getText().equals(priceText_selected.toString())) {
                                        priceTextUpdate.setText("");
                                    }
                                }

                                @Override
                                public void focusLost(FocusEvent e) {
                                    if (priceTextUpdate.getText().isEmpty()) {
                                        priceTextUpdate.setText(priceText_selected.toString());
                                    }
                                }
                            });


                        }
                    });


                    // perform UPDATE product action
                } else if (selectedOption.equals("DELETE product")) {

                    Component[] components = myLabel.getComponents();
                    for (Component c : components) {
                        if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField) {
                            myLabel.remove(c);
                        }
                    }

                    myLabel.revalidate();
                    myLabel.repaint();


                    ProductsBLL productsBLL= new ProductsBLL();
                    JTable table=productsBLL.tableCreation();
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
                            Object lastName_selected = table.getValueAt(rowIndex, 1);
                            Object firstName_selected = table.getValueAt(rowIndex, 2);
                            Object email_selected = table.getValueAt(rowIndex, 3);

                            // Print the values to the console
                            System.out.println("Selected row: " + id_selected + ", " + lastName_selected + ", " + firstName_selected + ", " + email_selected);


                        }
                    });


                    // perform DELETE product action
                } else if (selectedOption.equals("VIEW products")) {
                    // perform VIEW products action
                    // perform VIEW products action
                    Component[] components = myLabel.getComponents();
                    for (Component c : components) {
                        if (c instanceof JScrollPane || c instanceof JTable || c instanceof JTextField) {
                            myLabel.remove(c);
                        }
                    }


                    myLabel.revalidate();
                    myLabel.repaint();


                    ProductsBLL productsBLL= new ProductsBLL();
                    JTable table=productsBLL.tableCreation();
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





       /* rButtonClient.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub


                // Make the text fields visible



            }

        });
*/

        rButtonApply.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String selectedOption = (String) comboBox.getSelectedItem();
                if (selectedOption.equals("ADD product")) {
                  String name=nameText.getText();
                  String quantity=quantityText.getText();
                  String price=priceText.getText();
                    String value ="'"+name + "', '" + quantity + "', '" + price + "'";

                    ProductsBLL productsBLL= new ProductsBLL();
                    Product product= productsBLL.insert_BLL(value);
                }
                if (selectedOption.equals("UPDATE product"))
                {
                    String namePr=nameTextUpdate.getText();
                    String quant=quantityTextUpdate.getText();
                    String pric=priceTextUpdate.getText();

                    String value=" "+namePr+ " "+quant + " " +pric+ " ";
                    System.out.println(value);
                    ProductsBLL productsBLL= new ProductsBLL();

                    Product product=productsBLL.update_BLL(value, (Integer) id_selected_update);
                }
                 if (selectedOption.equals("DELETE product"))
                {
                    System.out.println(id_selected);
                    ProductsBLL productsBLL= new ProductsBLL();
                    Product product= productsBLL.delete_BLL((Integer)id_selected);
                }

                // Make the text fields visible



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
