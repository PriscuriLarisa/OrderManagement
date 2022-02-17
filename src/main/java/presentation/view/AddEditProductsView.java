package presentation.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
/**
 * Frame care contine campurile in care se introduc datele
 * care urmeaza sa fie inserate sau editate in cadrul tabelului
 * Products al bazei de date.
 */

public class AddEditProductsView extends JFrame {

    private Container container;
    private JLabel nameLabel, priceLabel, quantityLabel;
    private JTextField nameTextField, priceTextField, quantityTextField, idTextField;
    private JButton insertButton, editButton;

    public AddEditProductsView(String title, String name, String price, String quantity, String id, boolean edit){
        setTitle(title);
        setSize(500, 500);
        setLocation(1400, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        container = getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(48, 48, 48));

        nameLabel = new JLabel("Denumire");
        priceLabel = new JLabel("Pret");
        quantityLabel = new JLabel("Cantitate");
        nameTextField = new JTextField(name);
        priceTextField = new JTextField(price);
        quantityTextField = new JTextField(quantity);
        idTextField = new JTextField(id);

        nameLabel.setBounds(10, 10, 200, 30);
        nameLabel.setForeground(new Color(155, 155, 155));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        quantityLabel.setBounds(340, 10, 160, 30);
        quantityLabel.setForeground(new Color(155, 155, 155));
        quantityLabel.setHorizontalAlignment(JLabel.CENTER);
        quantityLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        priceLabel.setBounds(10, 90, 460, 30);
        priceLabel.setForeground(new Color(155, 155, 155));
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        priceLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));

        nameTextField.setBounds(20, 50, 300, 30);
        nameTextField.setBackground(new Color(155, 155, 155));
        nameTextField.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        quantityTextField.setBounds(370, 50, 100, 30);
        quantityTextField.setBackground(new Color(155, 155, 155));
        quantityTextField.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        priceTextField.setBounds(30, 130, 420, 30);
        priceTextField.setBackground(new Color(155, 155, 155));
        priceTextField.setBorder(new LineBorder(new Color(155, 155, 155), 5));

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


        container.add(nameLabel);
        container.add(quantityLabel);
        container.add(priceLabel);
        container.add(nameTextField);
        container.add(priceTextField);
        container.add(quantityTextField);
        container.add(insertButton);
        container.add(editButton);

        setVisible(true);
    }

    public String getNameField() {
        return nameTextField.getText();
    }

    public String getPriceField() {
        return priceTextField.getText();
    }

    public String getQuantityField() {
        return quantityTextField.getText();
    }

    public String getIdField(){
        return idTextField.getText();
    }

    public void setNameField(String text) {
        nameTextField.setText(text);
    }

    public void setPriceField(String text) {
        priceTextField.setText(text);
    }

    public void setQuantityField(String text) {
        quantityTextField.setText(text);
    }

    public void insertListener(ActionListener listenForInsert) {
        insertButton.addActionListener(listenForInsert);
    }

    public void editListener(ActionListener listenForEdit) {
        editButton.addActionListener(listenForEdit);
    }


}
