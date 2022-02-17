package dao;

import model.Products;

/**
 * Clasa care extinde AbstractDAO si reprezinta modalitatea de
 * a efectua opeeraii CRUD asupra tabelulului Products
 * din baza de date
 */

public class ProductDAO extends AbstractDAO<Products> {
    public ProductDAO(){
        super();
    }
}
