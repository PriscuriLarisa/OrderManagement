package presentation.controller;

import bll.OrderBLL;
import model.Orders;
import presentation.view.AbstractView;
import presentation.view.AddEditOrderView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controllerul pentru meniul orders, ce se ocupa cu executarea
 * comenzilor primite de la utilizator, facand conexiunea
 * intre view si bll.
 */

public class OrdersController {
    private AbstractView<Orders> view;

    public OrdersController(AbstractView<Orders> view) {
        OrderBLL orderBLL = new OrderBLL();
        this.view = view;
        view.setContentsTable(orderBLL.getOrders());

        view.insertListener(new InsertListener());
        view.deleteListener(new DeleteListener());
        view.editListener(new EditListener());
    }

    class InsertListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AddEditOrderView addEditOrderView = new AddEditOrderView("Inserare",  "","", "", "", false);
            AddEditOrderController addEditOrderController = new AddEditOrderController(addEditOrderView, view);

            OrderBLL orderBLL = new OrderBLL();

            view.setContentsTable(orderBLL.getOrders());
        }
    }
    class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] value = view.getSelectedRow();
            Orders orders = new Orders(Integer.parseInt(value[0]), Integer.parseInt(value[1]), Integer.parseInt(value[2]), Integer.parseInt(value[3]));
            OrderBLL orderBLL = new OrderBLL();
            System.out.println(orderBLL.delete(Integer.toString(orders.getId())));
            view.dispose();
            view.setContentsTable(orderBLL.getOrders());

        }
    }
    class EditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] value = view.getSelectedRow();
            Orders orders = new Orders(Integer.parseInt(value[0]), Integer.parseInt(value[1]), Integer.parseInt(value[2]), Integer.parseInt(value[3]));
            System.out.println(value[0] + " "+ value[1] + " "+value[2] + " " + value[3]);
            OrderBLL orderBLL = new OrderBLL();

            AddEditOrderView addEditOrderView = new AddEditOrderView("Inserare", value[1], value[2], value[3], value[0],true);
            AddEditOrderController addEditOrderController = new AddEditOrderController(addEditOrderView, view);

            view.setContentsTable(orderBLL.getOrders());
        }
    }
}
