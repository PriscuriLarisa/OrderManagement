package presentation.controller;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import exceptions.InvalidDataEnteredException;
import exceptions.NotEnoughStockException;
import model.Clients;
import model.Orders;
import model.Products;
import presentation.view.AbstractView;
import presentation.view.AddEditOrderView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Controllerul pentru meniul de adaugare sau editare a unor orders,
 * ocupandu-se cu executarea comenzilor primite de la utilizator,
 * facand conexiunea intre view si bll.
 */


public class AddEditOrderController {
    AddEditOrderView view;

    public AddEditOrderController(AddEditOrderView view, AbstractView<Orders> tableView) {
        this.view = view;
        ClientBLL clientBLL = new ClientBLL();
        List<Clients> clients = clientBLL.getClients();
        String[] clientArray= new String[clients.size()];
        int i=0;
        for(Clients tmp : clients) {
            clientArray[i]=Integer.toString(tmp.getId());
            i++;
        }

        ProductBLL productBLL = new ProductBLL();
        List<Products> products=productBLL.getProducts();
        String[] productArray = new String[products.size()];
        i=0;
        for(Products tmp : products) {
            if(tmp.getTotal_quantity()>0) {
                productArray[i] = Integer.toString(tmp.getId());
                i++;
            }
        }

        view.setClient(clientArray);
        view.setProduct(productArray);

        view.insertListener(new DoneListener(tableView));
        view.editListener(new EditListener(tableView));
    }

    class DoneListener implements ActionListener{
        AbstractView<Orders>  tableview;

        public DoneListener(AbstractView<Orders>  view) {
            this.tableview = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int client, product, quantity;
            if(view.getClient().isEmpty())
                client = -1;
            else
                client = Integer.parseInt(view.getClient());
            if(view.getProduct().isEmpty())
                product = -1;
            else
                product = Integer.parseInt(view.getProduct());
            if(view.getQuantity().isEmpty())
                quantity = -1;
            else
                quantity = Integer.parseInt(view.getQuantity());

            Orders orders = new Orders(0, client, product, quantity);
            OrderBLL orderBLL = new OrderBLL();
            try {
                System.out.println(orderBLL.placeOrder(orders));
            } catch (InvalidDataEnteredException invalidDataEnteredException) {
                JOptionPane.showMessageDialog(view, "Incorrect input data.");
            } catch (NotEnoughStockException notEnoughStockException) {
                JOptionPane.showMessageDialog(view, "Not enough stock.");
            }

            tableview.setContentsTable(orderBLL.getOrders());
        }
    }

    class EditListener implements ActionListener{
        AbstractView<Orders>  tableview;

        public EditListener(AbstractView<Orders>  view) {
            this.tableview = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(view.getId());
            int client, product, quantity;
            if(view.getClient().isEmpty())
                client = -1;
            else
                client = Integer.parseInt(view.getClient());
            if(view.getProduct().isEmpty())
                product = -1;
            else
                product = Integer.parseInt(view.getProduct());
            if(view.getQuantity().isEmpty())
                quantity = -1;
            else
                quantity = Integer.parseInt(view.getQuantity());

            Orders orders = new Orders(id, client, product, quantity);
            OrderBLL orderBLL = new OrderBLL();
            System.out.println(orderBLL.update(orders));
            view.dispose();
            tableview.setContentsTable(orderBLL.getOrders());
        }
    }


}
