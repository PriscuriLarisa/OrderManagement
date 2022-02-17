package bll.validator;

import model.Products;

/**
 * Clasa care se ocupa de validarea obiectelor de tipul Products.
 *
 */

public class ProductValidator implements Validator<Products> {
    @Override
    public boolean validate(Products products) {
        if(products.getPrice()==-1 || products.getTotal_quantity()==-1 || products.getLabel()==null) {
            return false;
        }
        return true;
    }
}
