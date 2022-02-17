package bll;

import bll.validator.ProductValidator;
import dao.ClientDAO;
import dao.ProductDAO;
import exceptions.InvalidDataEnteredException;
import model.Clients;
import model.Products;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care, dupa preluarea datelor primite de la controller, le valideaza
 * cu ajutorul claselor de validare, si in cazul in care acestea sunt corecte,
 * editeaza sau adauga elemente in tabelul Products al bazei de date.
 *
 * De asemenea, aceasta clasa prezinta si functionalitatea extragerii
 * datelor din baza de date.
 *
 */


public class ProductBLL {

    private ProductValidator productValidator;

    public ProductBLL(){
        productValidator = new ProductValidator();
    }


    /**
     * Cu ajutorul instantei clasei DAO, metoda extrage din tabela
     * Products a bazei de date elementul cu id-ul egal cu cel primit ca si parametru.
     * @param id id-ul elementului care trebuie extras din baza de date
     * @return instanta a clasei Products cu id-ul cautat
     */

    public Products getById(String id){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.findById(id);
    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda extrage din tabela
     * Product a bazei de date toate inregistrarile.

     * @return o lista de obiecte Products din tabelul Products
     */

    public List<Products> getProducts(){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.findAll();
    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda editeaza datele din tabela
     * Product ale inregistrarii cu id-ul egal cu cel al atributului
     * instantei primite ca si parametru.
     * <p>Datele introduse se verifica cu ajutorul validatorilor.</p>
     * <p>Arunca exceptie in cazul in care datele sunt invalide</p>
     * @param product un produs cu un id existent deja in tabela Product al
     *                bazei de date, insa ce contine alte date
     * @return true in caz de succes, false in caz contrar
     */

    public boolean update(Products product) throws InvalidDataEnteredException{
        ProductDAO productDAO = new ProductDAO();
        if(productValidator.validate(product)){
            ArrayList<String> values = product.toStringList();
            return productDAO.update(values);
        }
        else
            throw new InvalidDataEnteredException();
    }

    /**
     * Cu ajutorul instantei clasei DAO, metoda stergere inregistrarea
     * din tabela Products care are id-ul egal cu cel al parametrului primit.
     *
     * @param id un produs cu un id existent deja in tabela Product al
     *                bazei de date, insa ce contine alte date
     * @return true in caz de succes, false in caz contrar
     */

    public boolean delete(String id){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.delete(id);
    }



    public boolean insert(Products product) throws InvalidDataEnteredException{
        ProductDAO productDAO = new ProductDAO();
        if(productValidator.validate(product)) {
            ArrayList<String> values = product.toStringList();
            return productDAO.insert(values);
        }
        else
            throw new InvalidDataEnteredException();
    }
}
