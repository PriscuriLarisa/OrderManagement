package dao;

import connection.ConnectionFactory;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Clasa abstracta care se ocupa de preluarea datelor din baza de date
 * <p>Contine metode care creaza query-uri pentru generarea datelor necesare,
 * care urmeaza sa fie executate returnand in final unul sau mai multe obiecte
 * cu criteriile cerute.</p>
 *
 * <p>Query-urile au fost generate dinamic cu ajutorul metodei reflection.</p>
 * <p>Toate operatiile CRUD asupra datelor din baza de date au fost
 *  efectuate prin metoda reflection pentru generarea rezultatelor.</p>
 *
 * @param <T> va primi clasa corespunzatoare tabelului din care se extrag datele
 */

public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO(){
        this.type = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    /**
     *
     * Genereaza query-ul de selectare a unui element cu field-ul egal
     * cu o valoare aleasa mai tarziu.
     *
     * @param field colona din tabel dupa care se face selectia
     * @return un String ce reprezinta query-ul.
     */

    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + "=?");
        return sb.toString();
    }

    /**
     *
     * Genereaza query-ul de selectare a tutror elementelor dintr-un tabel.
     *
     * @return un String ce reprezinta query-ul.
     */

    private String createSelectAll() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();
    }

    /**
     *
     * Genereaza query-ul de adaugare a ununei noi inregistrari
     * in tabela reprezentata de parametrul T.
     *
     * @return un String ce reprezinta query-ul.
     */

    private String createInsert(ArrayList<String> values) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ");
        sb.append(type.getSimpleName());
        sb.append("(");
        String prefix = "";
        for(Field field : type.getDeclaredFields()){
            sb.append(prefix);
            prefix = ",";
            sb.append(field.getName());
        }
        sb.append( ") VALUES (");
        prefix = "";
        for(String value :values) {
            sb.append(prefix);
            prefix = ",";
            sb.append("?");
        }
        sb.append(")");
        return sb.toString();
    }

    /**
     *
     * Genereaza query-ul de editare a unei inregistrari din
     * tabela reprezentata de parametrul T.
     *
     * @return un String ce reprezinta query-ul.
     */

    private String createUpdate(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");
        String prefix = "";
        for(Field fields : type.getDeclaredFields()){
            sb.append(prefix);
            prefix = ",";
            sb.append(fields.getName() + "=?");
        }
        sb.append(" WHERE " + field + "=?");
        return sb.toString();
    }

    /**
     *
     * Genereaza query-ul de stergere a unui nou element.
     *
     * @return un String ce reprezinta query-ul.
     */

    private String createDelete(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + "=?");
        return sb.toString();
    }

    /**
     *
     * Metoda care, dupa generarea unui string ce contine query-ul de selectare a
     * unui element din tabelul reprezentat de clasa T care are id-ul egal cu
     * cel primit ca si parametru, il executa si returneaza un obiect abstract
     * de tipul T.
     *
     * @param id id-ul elementului care trebuie extras din baza de date.
     * @return returneaza un obiect de tipul T.
     */

    public T findById(String id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            System.out.println(statement);
            resultSet = statement.executeQuery();
            if(!resultSet.isBeforeFirst())
                return null;
            else
                return createObjects(resultSet).get(0);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:findById "+ e.getMessage());
        }
        finally {
            close(resultSet, statement, connection);
        }
        return null;
    }

    /**
     *
     * Metoda care, dupa generarea unui string ce contine query-ul de selectare a
     * tuturor elementelor din tabelul reprezentat de clasa T, il executa si
     * returneaza un obiect abstract de tipul T.
     *
     * @return returneaza un obiect de tipul T.
     */

    public List<T> findAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectAll();
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            if(!resultSet.isBeforeFirst())
                return null;
            else
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:findAll "+ e.getMessage());
        }
        finally {
            close(resultSet, statement, connection);
        }
        return null;
    }

    /**
     *
     * Metoda care, dupa generarea unui string ce contine query-ul de inserare a
     * unui element in tabelul reprezentat de clasa T care are valorile egale
     * cu cele primite ca si parametru, il executa si returneaza true in caz de succes
     * si false in caz contrar.
     *
     * @param values lista de string-uti care reprezinta datele ce trebuie inserate
     *               in tabelul reprezentat de clasa T
     * @return returneaza true in caz de succes, false in caz contrar.
     */

    public boolean insert(ArrayList<String> values){
        Connection connection = null;
        PreparedStatement statement = null;
        int resultSet = 0;
        String query = createInsert(values);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i=1;
            for(String value : values) {
                statement.setString(i, value);
                i++;
            }
            System.out.println(statement);
            resultSet = statement.executeUpdate();
                if(resultSet>0)
                    return true;
                return false;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:insert "+ e.getMessage());
            return false;
        }
        finally {
            closeUpdate(statement, connection);
        }
    }

    /**
     *
     * Metoda care, dupa generarea unui string ce contine query-ul de editare a
     * unui element in tabelul reprezentat de clasa T care are id-ul egal
     * cu cel primit ca si parametru in cadrul listei de valori, il executa si
     * returneaza true in caz de succes si false in caz contrar.
     *
     *
     * @param values lista de string-uti care reprezinta datele ce trebuie editate
     *               in tabelul reprezentat de clasa T
     * @return returneaza true in caz de succes, false in caz contrar
     */

    public boolean update(ArrayList<String> values){
        Connection connection = null;
        PreparedStatement statement = null;
        int resultSet = 0;
        String query = createUpdate("id");
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            int i=1;
            for(String value : values) {
                statement.setString(i, value);
                i++;
            }
            statement.setString(i, values.get(0));
            System.out.println(statement);
            resultSet = statement.executeUpdate();
            if(resultSet>0)
                return true;
            return false;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:insert "+ e.getMessage());
            return false;
        }
        finally {
            closeUpdate(statement, connection);
        }
    }

    /**
     *
     * Metoda care, dupa generarea unui string ce contine query-ul de stergere a
     * unui element in tabelul reprezentat de clasa T care are id-ul egal
     * cu cel primit ca si parametru, il executa si returneaza true in caz de succes
     * si false in caz contrar.
     *
     * @param id id-ul inregistrarii care trebuie stearsa din tabel
     *
     * @return returneaza true in caz de succes, false in caz contrar.
     */

    public boolean delete(String id){
        Connection connection = null;
        PreparedStatement statement = null;
        int resultSet = 0;
        String query = createDelete("id");
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, id);
            System.out.println(statement);
            resultSet = statement.executeUpdate();
            if(resultSet>0)
                return true;
            return false;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + " DAO:delete "+ e.getMessage());
            return false;
        }
        finally {
            closeUpdate(statement, connection);
        }
    }

    /**
     * Metoda care, in urma executarii unui query primeste un set de rezultate
     * si folosind metoda reflection genereaza o lista de obiecte de tipul T,
     * corespunzator tabelului din care au fost extrase datele.
     *
     * @param resultSet primeste ca si parametru un set de rezultate obtinute in
     *                  urma executarii query-ului.
     * @return returneaza o lista de obiecte de tipul T
     */

    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        try{
            while(resultSet.next()){
                T instance = type.getDeclaredConstructor().newInstance();
                for(Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        }catch(Exception e) {
            System.out.println(e.getMessage());
            LOGGER.log(Level.WARNING, "Could not create object");
        }

        return list;
    }

    /**
     *  Inchiderea conexiunii, statementului si setului
     *  de rezultate.
     *
     */

    private void close(ResultSet resultSet, Statement statement, Connection connection) {
        ConnectionFactory.close(resultSet);
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }

    private void closeUpdate(Statement statement, Connection connection) {
        ConnectionFactory.close(statement);
        ConnectionFactory.close(connection);
    }

}
