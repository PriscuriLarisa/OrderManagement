package presentation.controller;

import bll.ClientBLL;
import dao.ClientDAO;
import exceptions.InvalidDataEnteredException;
import model.Clients;
import presentation.view.AbstractView;
import presentation.view.AddEditClientView;
import presentation.view.ClientsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Controllerul pentru meniul de adaugare sau editare a unor clienti
 * ocupandu-se cu executarea comenzilor primite de la utilizator,
 * facand conexiunea intre view si bll.
 */

public class AddEditClientController {
    AddEditClientView view;

    public AddEditClientController(AddEditClientView view, AbstractView<Clients> tableView){
        this.view = view;

        view.insertListener(new DoneListener(tableView));
        view.editListener(new EditListener(tableView));

    }

    class DoneListener implements ActionListener{

        AbstractView<Clients> tableView;

        public DoneListener(AbstractView<Clients> tableView){
            this.tableView = tableView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameField();
            if(name.isEmpty())
                name = null;
            String address = view.getAddressField();
            if(address.isEmpty())
                address = null;
            int age;
            if(view.getAgeField().isEmpty())
                age = -1;
            else
                age = Integer.parseInt(view.getAgeField());

            Clients clients = new Clients(0, name, address, age);
            System.out.println(clients.toString());

            ClientBLL clientBLL = new ClientBLL();
            try {
                System.out.println(clientBLL.insert(clients));
            } catch (InvalidDataEnteredException invalidDataEnteredException) {
                JOptionPane.showMessageDialog(view, "Incorrect input data.");
            }
            view.dispose();
            tableView.setContentsTable(clientBLL.getClients());
        }
    }

    class EditListener implements ActionListener{

        AbstractView<Clients> tableView;

        public EditListener(AbstractView<Clients> tableView){
            this.tableView = tableView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(view.getIdField());
            String name = view.getNameField();
            if(name.isEmpty())
                name = null;
            String address = view.getAddressField();
            if(address.isEmpty())
                address = null;
            int age;
            if(view.getAgeField().isEmpty())
                age = -1;
            else
                age = Integer.parseInt(view.getAgeField());
            Clients clients = new Clients(id, name, address, age);
            System.out.println(id + name + " " + address + " " + age);

            ClientBLL clientBLL = new ClientBLL();
            try {
                System.out.println(clientBLL.update(clients));
            } catch (InvalidDataEnteredException invalidDataEnteredException) {
                JOptionPane.showMessageDialog(view, "Incorrect input data.");
            }

            view.dispose();
            tableView.setContentsTable(clientBLL.getClients());
        }
    }
}
