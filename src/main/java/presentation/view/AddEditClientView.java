package presentation.view;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Frame care contine campurile in care se introduc datele
 * care urmeaza sa fie inserate sau editate in cadrul tabelului
 * Clients al bazei de date.
 */

public class AddEditClientView extends JFrame {

    private Container container;
    private JLabel nameLabel, addressLabel, ageLabel;
    private JTextField nameTextField, addressTextField, ageTextField, idTextField;
    private JButton insertButton, editButton;

    public AddEditClientView(String title, String name, String address, String age, String id, boolean edit){
        setTitle(title);
        setSize(500, 500);
        setLocation(1400, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        container = getContentPane();
        container.setLayout(null);
        container.setBackground(new Color(48, 48, 48));

        nameLabel = new JLabel("Nume");
        addressLabel = new JLabel("Adresa");
        ageLabel = new JLabel("Varsta");
        nameTextField = new JTextField(name);
        addressTextField = new JTextField(address);
        ageTextField = new JTextField(age);
        idTextField = new JTextField(id);

        nameLabel.setBounds(10, 10, 200, 30);
        nameLabel.setForeground(new Color(155, 155, 155));
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        ageLabel.setBounds(340, 10, 160, 30);
        ageLabel.setForeground(new Color(155, 155, 155));
        ageLabel.setHorizontalAlignment(JLabel.CENTER);
        ageLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        addressLabel.setBounds(10, 90, 460, 30);
        addressLabel.setForeground(new Color(155, 155, 155));
        addressLabel.setHorizontalAlignment(JLabel.CENTER);
        addressLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));

        nameTextField.setBounds(20, 50, 300, 30);
        nameTextField.setBackground(new Color(155, 155, 155));
        nameTextField.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        ageTextField.setBounds(370, 50, 100, 30);
        ageTextField.setBackground(new Color(155, 155, 155));
        ageTextField.setBorder(new LineBorder(new Color(155, 155, 155), 5));
        addressTextField.setBounds(30, 130, 420, 30);
        addressTextField.setBackground(new Color(155, 155, 155));
        addressTextField.setBorder(new LineBorder(new Color(155, 155, 155), 5));

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
        container.add(ageLabel);
        container.add(addressLabel);
        container.add(nameTextField);
        container.add(addressTextField);
        container.add(ageTextField);
        container.add(insertButton);
        container.add(editButton);

        setVisible(true);
    }

    public String getNameField() {
        return nameTextField.getText();
    }

    public String getAddressField() {
        return addressTextField.getText();
    }

    public String getAgeField() {
        return ageTextField.getText();
    }

    public String getIdField(){
        return idTextField.getText();
    }

    public void setNameField(String text) {
        nameTextField.setText(text);
    }

    public void setAddressField(String text) {
        addressTextField.setText(text);
    }

    public void setAgeField(String text) {
        ageTextField.setText(text);
    }

    public void insertListener(ActionListener listenForInsert) {
        insertButton.addActionListener(listenForInsert);
    }

    public void editListener(ActionListener listenForEdit) {
        editButton.addActionListener(listenForEdit);
    }


}
