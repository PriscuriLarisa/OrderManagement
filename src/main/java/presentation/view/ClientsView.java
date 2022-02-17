package presentation.view;

import model.Clients;

/**
 * Clasa care extinde AbstractView si reprezinta modalitatea de
 * a genera dinamic meniul pentru obiectele de tip Clients.
 */

public class ClientsView extends AbstractView<Clients> {

    public ClientsView(String title) {
        super(title);
    }
}
