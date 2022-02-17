package presentation.controller;

import bll.ClientBLL;
import model.Clients;
import presentation.view.AbstractView;
import presentation.view.AddEditClientView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Controllerul pentru meniul clienti, ce se ocupa cu executarea
 * comenzilor primite de la utilizator, facand conexiunea
 * intre view si bll.
 */

public class ClientsController {

    private AbstractView<Clients> view;
    public ClientsController(AbstractView<Clients> view) {
        ClientBLL clientBLL = new ClientBLL();
        this.view = view;
        view.setContentsTable(clientBLL.getClients());

        view.insertListener(new InsertListener());
        view.deleteListener(new DeleteListener());
        view.editListener(new EditListener());
    }

    class InsertListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            AddEditClientView addEditClientView = new AddEditClientView("Inserare", "","", "", "", false);
            AddEditClientController addEditClientController = new AddEditClientController(addEditClientView, view);

            ClientBLL clientBLL = new ClientBLL();
            view.setContentsTable(clientBLL.getClients());
        }
    }

    class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] value = view.getSelectedRow();
            Clients client = new Clients(Integer.parseInt(value[0]), value[1], value[2], Integer.parseInt(value[3]));
            ClientBLL clientBLL = new ClientBLL();
            System.out.println(clientBLL.delete(Integer.toString(client.getId())));
            view.setContentsTable(clientBLL.getClients());
        }
    }

    class EditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String[] value = view.getSelectedRow();
            Clients client = new Clients(Integer.parseInt(value[0]), value[1], value[2], Integer.parseInt(value[3]));

            AddEditClientView addEditClientView = new AddEditClientView("Edit", value[1], value[2], value[3], value[0], true);
            AddEditClientController addEditClientController = new AddEditClientController(addEditClientView, view);


        }
    }

}
