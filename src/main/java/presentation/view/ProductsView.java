package presentation.view;

import model.Products;

/**
 * Clasa care extinde AbstractView si reprezinta modalitatea de
 * a genera dinamic meniul pentru obiectele de tip Products.
 */

public class ProductsView extends AbstractView<Products> {
    public ProductsView(String title) {
        super(title);
    }
}
