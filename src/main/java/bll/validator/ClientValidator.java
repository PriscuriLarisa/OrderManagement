package bll.validator;

import model.Clients;

/**
 * Clasa care se ocupa de validarea obiectelor de tipul Clients.
 *
 */

public class ClientValidator implements Validator<Clients>{

    @Override
    public boolean validate(Clients clients) {
        if (clients.getAddress() == null || clients.getAge() == -1 || clients.getUsername()==null) {
            return false;
        }
        return true;
    }
}
