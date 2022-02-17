package presentation.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Frame care sa prezinte meniul principal, de unde
 * utilizatorul poate alege una dintre cele 3 optiuni:
 *      - Meniu clienti
 *      - Meniu produse
 *      - Meniu comenzi
 */

public class MainView extends JFrame {
    private Container container;
    private JButton clientsButton, productsButton, ordersButton;

    public MainView(String title) {
        setTitle(title);
        setSize(500, 150);
        setLocation(100, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        container = getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(48, 48, 48));

        clientsButton = new JButton("Clients");
        clientsButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        clientsButton.setBounds(50, 40, 100, 40);
        clientsButton.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        clientsButton.setBackground(new Color(63, 127, 0));
        clientsButton.setVerticalAlignment(JButton.CENTER);

        productsButton = new JButton("Products");
        productsButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        productsButton.setBounds(200, 40, 100, 40);
        productsButton.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        productsButton.setBackground(new Color(63, 127, 0));
        productsButton.setVerticalAlignment(JButton.CENTER);

        ordersButton = new JButton("Orders");
        ordersButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        ordersButton.setBounds(350, 40, 100, 40);
        ordersButton.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        ordersButton.setBackground(new Color(63, 127, 0));
        ordersButton.setVerticalAlignment(JButton.CENTER);

        container.add(clientsButton);
        container.add(productsButton);
        container.add(ordersButton);

        setVisible(true);

    }

    public void clientListener(ActionListener listenForClients) {
        clientsButton.addActionListener(listenForClients);
    }

    public void productsListener(ActionListener listenForProducts) {
        productsButton.addActionListener(listenForProducts);
    }
    public void ordersListener(ActionListener listenForOrders) {
        ordersButton.addActionListener(listenForOrders);
    }
}
