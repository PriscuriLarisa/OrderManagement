package connection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa care stabileste conexiunea cu baza de date
 * pentru a efectua operatii CRUD asupra ei.
 *
 */

public class ConnectionFactory {
    private static final Logger LOGGER= Logger.getLogger(ConnectionFactory.class.getName());
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DBURL = "jdbc:mysql://localhost:3306/order_management";
    private static final String USER="root";
    private static final String PASS ="eutare12";
    private static ConnectionFactory singleInstance = new ConnectionFactory ();
    private Connection connection;

    private ConnectionFactory(){
        try{
            Class.forName(DRIVER);
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda pentru a crea connectionul cu baza de date.
     * @return connectionul facut cu baza de date
     * @throws SQLException exceptie daca nu s-a putut face conexiunea cu baza de date.
     */
    private Connection createConnection() throws SQLException
    {
        try
        {
            connection = DriverManager.getConnection(DBURL, USER, PASS);
            System.out.println("Connection success");
        }
        catch(SQLException e){
            LOGGER.log(Level.WARNING,"An error occurred while trying to connect to the database");
            e.printStackTrace();
        }
        return connection;
    }

    public static Connection getConnection() throws SQLException
    {
        return singleInstance.createConnection();
    }

    /**
     * inchiderea conexiunii in momentul in care s-a terminat
     * efectuarea operatiilor asupra ei.
     * @param con conexiunea care trebuie inchisa
     */

    public static void close(Connection con)
    {
        if (con != null)
        {
            try
            {
                con.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "close - connection error");
            }
        }
    }

    /**
     * Inchiderea unui statement creat pentru a executa un query.
     *
     * @param statement statementul care trebuie inchis
     */

    public static void close(Statement statement)
    {
        if (statement != null)
        {
            try
            {
                statement.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "close - statement error");
            }
        }
    }

    /**
     * Inchiderea unui set de rezultate obtinut in urma
     * executarii unui statement.
     *
     * @param result setul de rezultate care trebuie inchis.
     */

    public static void close(ResultSet result)
    {
        if (result != null)
        {
            try
            {
                result.close();
            } catch (SQLException e) {
                LOGGER.log(Level.WARNING, "close - ResultSet error");
            }
        }
    }


}
