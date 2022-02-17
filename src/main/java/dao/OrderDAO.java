package dao;

import model.Orders;
import model.Products;

/**
 * Clasa care extinde AbstractDAO si reprezinta modalitatea de
 * a efectua opeeraii CRUD asupra tabelulului Orders
 * din baza de date
 */

public class OrderDAO extends AbstractDAO<Orders>{
    public OrderDAO(){
        super();
    }

    /**
     *
     * Metoda care calculeaza pretul total al
     * unei comenzi in functie de pretul produsului
     * comandat si al cantitatii comandate
     *
     * @param order comanda al carei pret trebuie calculat
     * @return pretul total sub forma unui double
     */

    public double getTotalPrice(Orders order) {
        int productId = order.getId_product();
        ProductDAO productDAO = new ProductDAO();
        Products product = productDAO.findById(Integer.toString(productId));
        if(product == null){
            System.out.println("Produsul nu exista!");
            return -1.0;
        }
        return product.getPrice()*order.getQuantity();
    }
}
