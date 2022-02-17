package presentation.view;


import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Frame care contine campurile in care se introduc datele
 * care urmeaza sa fie inserate sau editate in cadrul tabelului
 * Orders al bazei de date.
 */

public class AddEditOrderView extends JFrame {
    private Container container;
    private JLabel clientLabel, productLabel, quantityLabel;
    private JComboBox<String> clientBox, productBox;
    private JTextField quantityTextField, idTextField;
    private JButton insertButton, editButton;
    private String setProduct, setClient;

    public AddEditOrderView(String title, String client, String product, String quantity, String id, boolean edit) {
        setProduct = product;
        setClient = client;


        setTitle(title);
        setSize(500, 500);
        setLocation(1400, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        container = getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(48, 48, 48));

        clientLabel = new JLabel("Client");
        productLabel = new JLabel("Product");
        quantityLabel = new JLabel("Quantity");
        clientBox = new JComboBox<>();

        productBox = new JComboBox<>();
        productBox.setSelectedItem(product);
        quantityTextField = new JTextField(quantity);
        idTextField = new JTextField(id);

        productLabel.setBounds(10, 10, 200, 30);
        productLabel.setForeground(new Color(155, 155, 155));
        productLabel.setHorizontalAlignment(JLabel.CENTER);
        productLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        quantityLabel.setBounds(340, 10, 160, 30);
        quantityLabel.setForeground(new Color(155, 155, 155));
        quantityLabel.setHorizontalAlignment(JLabel.CENTER);
        quantityLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        clientLabel.setBounds(10, 90, 460, 30);
        clientLabel.setForeground(new Color(155, 155, 155));
        clientLabel.setHorizontalAlignment(JLabel.CENTER);
        clientLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));

        productBox.setBounds(20, 50, 300, 30);
        productBox.setBackground(new Color(155, 155, 155));
        productBox.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        quantityTextField.setBounds(370, 50, 100, 30);
        quantityTextField.setBackground(new Color(155, 155, 155));
        quantityTextField.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        clientBox.setBounds(30, 130, 420, 30);
        clientBox.setBackground(new Color(155, 155, 155));
        clientBox.setBorder(new LineBorder(new Color(155, 155, 155), 5));

        idTextField.setBounds(0, 0, 1, 1);
        idTextField.setBackground(new Color(155, 155, 155));
        idTextField.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        idTextField.setVisible(false);

        insertButton = new JButton("INSERT");
        insertButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        insertButton.setBounds(200, 180, 100, 40);
        insertButton.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        insertButton.setBackground(new Color(63, 127, 0));
        insertButton.setVerticalAlignment(JButton.CENTER);

        editButton = new JButton("Edit");
        editButton.setFont(new Font("Monospaced", Font.PLAIN, 16));
        editButton.setBounds(200, 180, 100, 40);
        editButton.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        editButton.setBackground(new Color(63, 127, 0));
        editButton.setVerticalAlignment(JButton.CENTER);

        if(edit)
            insertButton.setVisible(false);
        else
            editButton.setVisible(false);

        container.add(editButton);
        container.add(insertButton);
        container.add(clientLabel);
        container.add(productLabel);
        container.add(quantityLabel);
        container.add(clientBox);
        container.add(productBox);
        container.add(quantityTextField);

        setVisible(true);

    }

    public void setClient(String[] client){
        clientBox.setModel(new DefaultComboBoxModel<>(client));
        clientBox.setSelectedItem(setClient);
    }
    public void setProduct(String[] product){
        productBox.setModel(new DefaultComboBoxModel<>(product));
        productBox.setSelectedItem(setProduct);
    }
    public void setId(String id) {
        idTextField.setText(id);
    }

    public String getId() {
        return idTextField.getText();
    }
    public void setQuantity(String quantity){
        quantityTextField.setText(quantity);
    }
    public String getClient(){
        return (String) clientBox.getSelectedItem();
    }
    public String getProduct(){
        return (String) productBox.getSelectedItem();
    }
    public String getQuantity(){
        return quantityTextField.getText();
    }

    public void insertListener(ActionListener listenForInsert) {
        insertButton.addActionListener(listenForInsert);
    }

    public void editListener(ActionListener listenForEdit) {
        editButton.addActionListener(listenForEdit);
    }


}
