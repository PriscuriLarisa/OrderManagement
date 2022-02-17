package model;

import java.util.ArrayList;

/**
 * Clasa care stocheaza datele stocate in tabelul Clients al bazei de date.
 * <p>Atributele corespund coloanelor din tabelul bazei de date.</p>
 * <p>Metodele sunt reprezentate de gettere si settere pentru fiecare atribut
 * si o metoda toStringList de dcare este nevoie in momentul inserarii
 * in tabela bazei de date si afisarii in interfata grafica a unui product</p>
 *
 */

public class Clients {
    private int id=-1;
    private String username=null;
    private String address=null;
    private int age=-1;

    public Clients(){}

    /**
     * Constructor pentru crearea unei noi instante cu atributele primite
     * @param id id-ul clientului
     * @param name numele clientului
     * @param address adresa clientului
     * @param age varsta clientului
     */

    public Clients(int id, String name, String address, int age) {

        this.id = id;
        this.username = name;
        this.address = address;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<String> toStringList(){
        ArrayList<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(username);
        list.add(address);
        list.add(Integer.toString(age));
        return list;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
