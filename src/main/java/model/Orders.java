package model;

import java.util.ArrayList;

/**
 * Clasa care stocheaza datele stocate in tabelul Orders al bazei de date.
 * <p>Atributele corespund coloanelor din tabelul bazei de date.</p>
 * <p>Metodele sunt reprezentate de gettere si settere pentru fiecare atribut
 * si o metoda toStringList de dcare este nevoie in momentul inserarii
 * in tabela bazei de date si afisarii in interfata grafica a unui product</p>
 *
 */

public class Orders {
    private int id=-1;
    private int id_client =-1;
    private int id_product =-1;
    private int quantity=-1;

    public Orders(){}

    /**
     *
     * @param id id-ul comenzii
     * @param id_client id-ul clientului care a facut comanda
     * @param id_product id-ul produsului comandat
     * @param quantity cantitatea comandata
     */

    public Orders(int id, int id_client, int id_product, int quantity) {
        this.id = id;
        this.id_client = id_client;
        this.id_product = id_product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<String> toStringList(){
        ArrayList<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(Integer.toString(id_client));
        list.add(Integer.toString(id_product));
        list.add(Integer.toString(quantity));
        return list;
    }
}
