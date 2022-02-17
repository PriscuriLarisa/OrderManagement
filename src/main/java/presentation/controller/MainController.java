package presentation.controller;

import presentation.view.ClientsView;
import presentation.view.MainView;
import presentation.view.OrdersView;
import presentation.view.ProductsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controllerul principal ce se ocupa cu executarea
 * comenzilor primite din meniul principal, facand conexiunea
 * intre view si bll.
 */

public class MainController {

    private MainView view;
    public MainController(MainView view) {
        this.view = view;

        this.view.clientListener(new ClientsListener());
        this.view.productsListener(new ProductListener());
        this.view.ordersListener(new OrderListener());

    }

    class ClientsListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            ClientsView view = new ClientsView("Clients");
            ClientsController controller = new ClientsController(view);
        }
    }

    class ProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ProductsView productsView = new ProductsView("Products");
            ProductsController productsController = new ProductsController(productsView);

        }
    }

    class OrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            OrdersView ordersView = new OrdersView("Orders");
            OrdersController ordersController = new OrdersController(ordersView);

        }
    }

}
