package bll.validator;

import dao.ProductDAO;
import model.Orders;
import model.Products;

/**
 * Clasa care se ocupa de validarea obiectelor de tipul Orders.
 *
 */

public class OrderValidator implements Validator<Orders> {

    @Override
    public boolean validate(Orders order) {
        if(order.getQuantity() == -1 || order.getId_product()==-1 || order.getId_client()==-1)
            return false;
        return true;

    }

    /**
     * Se verifica daca pentru crearea unui order primit ca si
     * paramtru exista suficient stoc.
     *
     * @param order comanda care trebuei validata
     * @return true in cazul in care exista stoc, false in caz contrar.
     */

    public boolean validateQuantity(Orders order){
        int productId = order.getId_product();
        ProductDAO productDAO = new ProductDAO();

        Products product = productDAO.findById(Integer.toString(productId));
        if(product==null) {
            System.out.println("Nu exista acest produs!");
            return false;
        }
        if(order.getQuantity() > product.getTotal_quantity()) {
            System.out.println("Nu exista suficient stoc!");
            return false;
        }
        return true;
    }
}
