package model;

import java.util.ArrayList;

/**
 * Clasa care stocheaza datele stocate in tabelul Products al bazei de date.
 * <p>Atributele corespund coloanelor din tabelul bazei de date.</p>
 * <p>Metodele sunt reprezentate de gettere si settere pentru fiecare atribut
 * si o metoda toStringList de dcare este nevoie in momentul inserarii
 * in tabela bazei de date si afisarii in interfata grafica a unui product</p>
 */

public class Products {
    private int id=-1;
    private String label=null;
    private double price=-1.0;
    private int total_quantity=-1;

    public Products(){}

    public int getId() {
        return id;
    }

    /**
     * Constructor pentru crearea unei noi instante cu atributele primite
     * @param id id-ul produsului
     * @param label denumirea produsului
     * @param price pretul produsului
     * @param quantity cantitatea produsului
     */
    public Products(int id, String label, double price, int quantity) {
        this.id = id;
        this.price = price;
        this.total_quantity = quantity;
        this.label = label;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<String> toStringList(){
        ArrayList<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(label);
        list.add(Double.toString(price));
        list.add(Integer.toString(total_quantity));
        return list;
    }
}
