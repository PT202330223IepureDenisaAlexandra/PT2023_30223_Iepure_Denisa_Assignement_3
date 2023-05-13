package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFr {

    private JFrame frame;

    private JLabel myLabel;


    private JButton rButtonClient;
    private JButton rButtonOrders;
    private JButton rButtonProducts;
    ImageIcon backgr;

    public MainFr()
    {
        backgr= new ImageIcon(this.getClass().getResource("/backgr.jpg"));
        myLabel= new JLabel(backgr);
        myLabel.setSize(900,700);

        frame= new JFrame("HOME");
        frame.add(myLabel);
        frame.setSize(900,700);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        Image icon=new ImageIcon(this.getClass().getResource("/home.png")).getImage();
        frame.setIconImage(icon);

        rButtonClient=new JButton("Clients");
        rButtonClient.setBounds(350, 400, 200, 40);
        rButtonClient.setBackground(Color.BLACK);
        rButtonClient.setForeground(Color.WHITE);
        myLabel.add(rButtonClient);

        rButtonOrders=new JButton("Orders");
        rButtonOrders.setBounds(350, 450, 200, 40);
        rButtonOrders.setBackground(Color.BLACK);
        rButtonOrders.setForeground(Color.WHITE);
        myLabel.add(rButtonOrders);


        rButtonProducts=new JButton("Products");
        rButtonProducts.setBounds(350, 500, 200, 40);
        rButtonProducts.setBackground(Color.BLACK);
        rButtonProducts.setForeground(Color.WHITE);
        myLabel.add(rButtonProducts);

        frame.setVisible(true);


        rButtonClient.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                frame.setVisible(false);
                new ClientView(rButtonClient.getText());

            }

        });

        rButtonProducts.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                frame.setVisible(false);
                new ProductsView(rButtonProducts.getText());

            }

        });


    }

}
