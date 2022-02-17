package presentation.controller;

import bll.ClientBLL;
import bll.ProductBLL;
import model.Clients;
import model.Products;
import presentation.view.AbstractView;
import presentation.view.AddEditClientView;
import presentation.view.AddEditProductsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controllerul pentru meniul products, ce se ocupa cu executarea
 * comenzilor primite de la utilizator, facand conexiunea
 * intre view si bll.
 */

public class ProductsController {
    private AbstractView<Products> view;

    public ProductsController(AbstractView<Products> view) {
        ProductBLL productBLL = new ProductBLL();
        this.view = view;
        view.setContentsTable(productBLL.getProducts());

        view.insertListener(new InsertListener());
        view.deleteListener(new DeleteListener());
        view.editListener(new EditListener());
    }

        class InsertListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                AddEditProductsView addEditProductsView = new AddEditProductsView("Inserare", "","", "", "", false);
                AddEditProductsController addEditProductsController = new AddEditProductsController(addEditProductsView, view);

                ProductBLL productBLL = new ProductBLL();
                view.setContentsTable(productBLL.getProducts());
            }
        }

        class DeleteListener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                String[] value = view.getSelectedRow();
                Products products = new Products(Integer.parseInt(value[0]), value[1], Double.parseDouble(value[2]), Integer.parseInt(value[3]));
                ProductBLL productBLL = new ProductBLL();
                System.out.println(productBLL.delete(Integer.toString(products.getId())));
                view.setContentsTable(productBLL.getProducts());
            }
        }

        class EditListener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                String[] value = view.getSelectedRow();
                Products products = new Products(Integer.parseInt(value[0]), value[1], Double.parseDouble(value[2]), Integer.parseInt(value[3]));

                AddEditProductsView addEditProductsView = new AddEditProductsView("Edit", value[1], value[2], value[3], value[0], true);
                AddEditProductsController addEditProductsController = new AddEditProductsController(addEditProductsView, view);


            }
        }
    }

