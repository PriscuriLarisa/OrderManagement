package presentation.view;

import model.Orders;

/**
 * Clasa care extinde AbstractView si reprezinta modalitatea de
 * a genera dinamic meniul pentru obiectele de tip Orders.
 */

public class OrdersView extends AbstractView<Orders>{
    public OrdersView(String title) {
        super(title);
    }
}
