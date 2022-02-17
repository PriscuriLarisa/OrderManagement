package bll;

import bll.validator.OrderValidator;
import dao.OrderDAO;
import dao.ProductDAO;
import exceptions.InvalidDataEnteredException;
import exceptions.NotEnoughStockException;
import model.Clients;
import model.Orders;
import model.Products;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care, dupa preluarea datelor primite de la controller, le valideaza
 * cu ajutorul claselor de validare, si in cazul in care acestea sunt corecte,
 * editeaza sau adauga elemente in tabelul Orders al bazei de date.
 *
 * De asemenea, aceasta clasa prezinta si functionalitatea extragerii
 * datelor din baza de date.
 *
 */


public class OrderBLL {

    OrderValidator orderValidator;

    public OrderBLL() {orderValidator = new OrderValidator();}


    /**
     * Metoda care trebuie sa insereze o comanda in tabela
     * Orders al baze de date, iar dupa valizarea acesteia sa
     * decrementeze stocul produsului si mai apoi sa creeze chitanta.
     *
     *
     * @param order comanda care se doreste inserata in baza de date
     * @return true in caz de succes, false in caz contrar
     * @throws InvalidDataEnteredException exceptie daca datele introduse sunt invalide
     * @throws NotEnoughStockException  exceptie daca nu exista suficient stoc al produsului
     */
    public boolean placeOrder(Orders order) throws InvalidDataEnteredException,NotEnoughStockException {
        try {
            if(insert(order)){
                ProductDAO productDAO = new ProductDAO();
                ProductBLL productBLL = new ProductBLL();
                Products products = productDAO.findById(Integer.toString(order.getId_product()));
                products.setTotal_quantity(products.getTotal_quantity()- order.getQuantity());
                productBLL.update(products);

                OrderBLL orderBLL = new OrderBLL();
                List<Orders> ordersList = orderBLL.getOrders();
                Orders lastOrder = ordersList.get(ordersList.size()-1);
                int orderNb = lastOrder.getId();
                generateBill(orderNb, order);

                return true;
            }
            else
                return false;
        } catch (InvalidDataEnteredException e) {
            throw new InvalidDataEnteredException();
        } catch (NotEnoughStockException e) {
            throw  new NotEnoughStockException();
        }
    }
    /**
     * Cu ajutorul instantei clasei DAO, metoda extrage din tabela
     * orders a bazei de date elementul cu id-ul egal cu cel primit ca si parametru.
     * @param id id-ul elementului care trebuie extras din baza de date
     * @return instanta a clasei Orders cu id-ul cautat
     */

    public Orders getById(String id){
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.findById(id);
    }

    public List<Orders> getOrders(){
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.findAll();
    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda extrage din tabela
     * Orders a bazei de date toate inregistrarile.

     * @return o lista de obiecte Orders din tabelul Orders
     */

    public boolean update(Orders order){
        OrderDAO orderDAO = new OrderDAO();
        ArrayList<String> values = order.toStringList();
        return orderDAO.update(values);
    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda stergere inregistrarea
     * din tabela Orders care are id-ul egal cu cel al parametrului primit.
     *
     * @param id un produs cu un id existent deja in tabela Orders al
     *                bazei de date, insa ce contine alte date
     * @return true in caz de succes, false in caz contrar
     */

    public boolean delete(String id){
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.delete(id);
    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda insereaza in tabela
     * Orders datele primite prin parametru sub forma undei instante
     * a casei Orders.
     * <p>Datele introduse se verifica cu ajutorul validatorilor.</p>
     * <p>Arunca exceptie in cazul in care datele sunt invalide</p>
     * @param order instanta a clasei Products care contine
     *                datele ce trebuie inserate in tabela
     * @return true in caz de succes, false in caz contrar
     */

    public boolean insert(Orders order) throws InvalidDataEnteredException, NotEnoughStockException {
         OrderDAO orderDAO = new OrderDAO();
         if(orderValidator.validate(order)) {
             if(orderValidator.validateQuantity(order)) {
                 ArrayList<String> values = order.toStringList();
                 return orderDAO.insert(values);
             }
             else
                 throw new NotEnoughStockException();
         }
         else throw new InvalidDataEnteredException();


    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda stergere inregistrarea
     * din tabela Orders care are id-ul egal cu cel al parametrului primit.
     *
     * @param order order-ul al carui pret total trebuie calculay
     * @return un obiect de tip double care reprezinta valoarea totala
     *          a comenzii
     */

    public double getTotalPrice(Orders order) {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.getTotalPrice(order);
    }

    /**
     *
     * In urma validarii unei comenzi si inserarea acesteia in tabela
     * Orders a bazeid e date, aceasta metoda se ocupa de
     * generarea unei chitante care contine datele
     * acestei comenzi sub forma unui fisier .txt.
     *
     * @param id numarul comenzii
     * @param order datele despre comanda
     */

    public void generateBill(int id, Orders order) {
        File file = new File("bill" + id + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        PrintWriter pw = new PrintWriter(fw);
        ClientBLL clientBLL = new ClientBLL();
        Clients client = clientBLL.getById(Integer.toString(order.getId_client()));

        ProductBLL productBLL = new ProductBLL();
        Products product = productBLL.getById(Integer.toString(order.getId_product()));

        OrderBLL orderBLL = new OrderBLL();

        pw.println("Comanda numarul " + id);
        pw.println("Client: " + client.getUsername());
        pw.println("Produs: "+ product.getLabel() + "        Cantitate :" + order.getQuantity());
        pw.println("Pret total: " + orderBLL.getTotalPrice(order));
        pw.close();

    }
}
