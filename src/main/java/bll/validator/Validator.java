package bll.validator;

/**
 * Interfata pentry validarea obiectelor de tipul T.
 *
 */

public interface Validator<T> {
    boolean validate(T t);
}
