package dao;


import model.Clients;

/**
 * Clasa care extinde AbstractDAO si reprezinta modalitatea de
 * a efectua opeeraii CRUD asupra tabelulului Clients
 * din baza de date
 */

public class ClientDAO extends AbstractDAO<Clients> {
    public ClientDAO(){
        super();
    }
}
