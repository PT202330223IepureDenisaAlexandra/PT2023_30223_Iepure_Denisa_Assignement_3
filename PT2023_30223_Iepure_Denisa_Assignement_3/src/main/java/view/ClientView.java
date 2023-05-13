package view;

import org.example.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ClientView {
    private JFrame frame;

    private JLabel myLabel;


    private JButton rButtonClient;
    private JButton rButtonOrders;
    private JButton rButtonProducts;
    JButton rButtonApply;
    JButton rButtonView;


    private JButton makeChanges;

    JTextField nume = new JTextField("Last name");
    JTextField prenume = new JTextField("First name");
    JTextField email = new JTextField("email");
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

        rButtonClient=new JButton("ADD client");
        rButtonClient.setBounds(100, 150, 200, 40);
        rButtonClient.setBackground(Color.BLACK);
        rButtonClient.setForeground(Color.WHITE);
        myLabel.add(rButtonClient);

        rButtonOrders=new JButton("UPDATE client");
        rButtonOrders.setBounds(100, 200, 200, 40);
        rButtonOrders.setBackground(Color.BLACK);
        rButtonOrders.setForeground(Color.WHITE);
        myLabel.add(rButtonOrders);


        rButtonProducts=new JButton("DELETE client");
        rButtonProducts.setBounds(100, 250, 200, 40);
        rButtonProducts.setBackground(Color.BLACK);
        rButtonProducts.setForeground(Color.WHITE);
        myLabel.add(rButtonProducts);

        rButtonView=new JButton("VIEW clients");
        rButtonView.setBounds(100, 300, 200, 40);
        rButtonView.setBackground(Color.BLACK);
        rButtonView.setForeground(Color.WHITE);
        myLabel.add(rButtonView);


        rButtonApply = new JButton("APPLY");
        rButtonApply.setBounds(150, 400, 200, 40);
        rButtonApply.setBackground(Color.BLACK);
        rButtonApply.setForeground(Color.WHITE);
        myLabel.add(rButtonApply);



        frame.setVisible(true);

        rButtonClient.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub


                // Make the text fields visible
                nume.setVisible(true);
                prenume.setVisible(true);
                email.setVisible(true);


            }

        });

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
    }
}
