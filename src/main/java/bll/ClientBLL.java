package bll;

import bll.validator.ClientValidator;
import dao.ClientDAO;
import exceptions.InvalidDataEnteredException;
import model.Clients;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care, dupa preluarea datelor primite de la controller, le valideaza
 * cu ajutorul claselor de validare, si in cazul in care acestea sunt corecte,
 * editeaza sau adauga elemente in tabelul Client al bazei de date.
 *
 * De asemenea, aceasta clasa prezinta si functionalitatea extragerii
 * datelor din baza de date.
 *
 */

public class ClientBLL {

    private ClientValidator clientValidator;

    public ClientBLL() {
        clientValidator = new ClientValidator();
    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda extrage din tabela
     * Clients a bazei de date elementul cu id-ul egal cu cel primit ca si parametru.
     * @param id id-ul elementului care trebuie extras din baza de date
     * @return instanta a clasei Products cu id-ul cautat
     */

    public Clients getById(String id){
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findById(id);
    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda extrage din tabela
     * Clients a bazei de date toate inregistrarile.

     * @return o lista de obiectte Products din tabelul Products
     */

    public List<Clients> getClients(){
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findAll();
    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda editeaza datele din tabela
     * Clients ale inregistrarii cu id-ul egal cu cel al atributului
     * instantei primite ca si parametru.
     * <p>Datele introduse se verifica cu ajutorul validatorilor.</p>
     * <p>Arunca exceptie in cazul in care datele sunt invalide</p>
     * @param client un client cu un id existent deja in tabela Clients al
     *                bazei de date, insa ce contine alte date
     * @return true in caz de succes, false in caz contrar
     */

    public boolean update(Clients client) throws InvalidDataEnteredException {
        ClientDAO clientDAO = new ClientDAO();
        if(clientValidator.validate(client)) {
            ArrayList<String> values = client.toStringList();
            return clientDAO.update(values);
        }
        else throw new InvalidDataEnteredException();
    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda stergere inregistrarea
     * din tabela Clients care are id-ul egal cu cel al parametrului primit.
     *
     * @param id un produs cu un id existent deja in tabela Clients al
     *                bazei de date, insa ce contine alte date
     * @return true in caz de succes, false in caz contrar
     */

    public boolean delete(String id){
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.delete(id);
    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda insereaza in tabela
     * Clients datele primite prin parametru sub forma undei instante
     * a casei Clients.
     * <p>Datele introduse se verifica cu ajutorul validatorilor.</p>
     * <p>Arunca exceptie in cazul in care datele sunt invalide</p>
     * @param client instanta a clasei Clients care contine
     *                datele ce trebuie inserate in tabela
     * @return true in caz de succes, false in caz contrar
     */

    public boolean insert(Clients client) throws InvalidDataEnteredException{
        ClientDAO clientDAO = new ClientDAO();
        if(clientValidator.validate(client)) {
            ArrayList<String> values = client.toStringList();
            return clientDAO.insert(values);
        }
        else throw new InvalidDataEnteredException();
    }
}
