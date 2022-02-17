package presentation.controller;

import bll.ClientBLL;
import bll.ProductBLL;
import exceptions.InvalidDataEnteredException;
import model.Clients;
import model.Products;
import presentation.view.AbstractView;
import presentation.view.AddEditClientView;
import presentation.view.AddEditProductsView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controllerul pentru meniul de adaugare sau editare a unor products
 * ocupandu-se cu executarea comenzilor primite de la utilizator,
 * facand conexiunea intre view si bll.
 */

public class AddEditProductsController {

    AddEditProductsView view;

    public AddEditProductsController(AddEditProductsView view, AbstractView<Products> tableView){
        this.view = view;

        view.insertListener(new DoneListener(tableView));
        view.editListener(new EditListener(tableView));

    }

    class DoneListener implements ActionListener {

        AbstractView<Products> tableView;

        public DoneListener(AbstractView<Products> tableView){
            this.tableView = tableView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameField();
            if(name.isEmpty())
                name=null;
            double price;
            if(view.getPriceField().isEmpty())
                price = -1.0;
            else
                price = Double.parseDouble(view.getPriceField());
            int quantity;
            if(view.getQuantityField().isEmpty())
                quantity = -1;
            else
                quantity = Integer.parseInt(view.getQuantityField());
            System.out.println(name + " " + price + " " + quantity);


            Products products = new Products(0, name, price, quantity);
            System.out.println(products.toString());

            ProductBLL productBLL = new ProductBLL();
            try {
                System.out.println(productBLL.insert(products));
            } catch (InvalidDataEnteredException invalidDataEnteredException) {
                JOptionPane.showMessageDialog(view, "Incorrect input data.");
            }

            view.dispose();
            tableView.setContentsTable(productBLL.getProducts());
        }
    }

    class EditListener implements ActionListener{

        AbstractView<Products> tableView;

        public EditListener(AbstractView<Products> tableView){
            this.tableView = tableView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(view.getIdField());
            String name = view.getNameField();
            if(name.isEmpty())
                name=null;
            double price;
            if(view.getPriceField().isEmpty())
                price = -1.0;
            else
                price = Double.parseDouble(view.getPriceField());
            int quantity;
            if(view.getQuantityField().isEmpty())
                quantity = -1;
            else
                quantity = Integer.parseInt(view.getQuantityField());
            Products products = new Products(id, name, price, quantity);
            System.out.println(id + name + " " + price + " " + quantity);

            ProductBLL productBLL = new ProductBLL();
            try {
                System.out.println(productBLL.update(products));
            } catch (InvalidDataEnteredException invalidDataEnteredException) {
                JOptionPane.showMessageDialog(view, "Incorrect input data.");
            }

            view.dispose();
            tableView.setContentsTable(productBLL.getProducts());
        }
    }
}
