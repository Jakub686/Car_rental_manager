package repository.customer;

import repository.company.Company;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/main/resources/db";

    static final String USER = "sa";
    static final String PASS = "";

    static public List<Customer> customers = new ArrayList<>();

    @Override
    public void createTable() {
        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS customer (" +
                    "id INT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255) NOT NULL UNIQUE," +
                    "rented_car_id INT," +
                    "FOREIGN KEY (rented_car_id) REFERENCES car(id));";


            stmt.executeUpdate(sql);

            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    @Override
    public void getAllCustomers() {

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "SELECT * FROM customer";


            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                customers.add(new Customer(rs.getInt("id"), rs.getString("name")));
            }

            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    @Override
    public void createCustomer(String name) {

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "INSERT INTO customer (name) VALUES ('" + name + "');";

            stmt.executeUpdate(sql);

            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }

    @Override
    public void addCar(int id, int carId) {

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "UPDATE customer SET rented_car_id = " + carId + " WHERE id =" + id + ";";


            stmt.executeUpdate(sql);

            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    @Override
    public void removeCar(int id) {

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "UPDATE customer SET rented_car_id = NULL WHERE id = " + id;

            stmt.executeUpdate(sql);

            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public Integer getCar(int id) {

        Connection conn = null;
        Statement stmt = null;

        Integer result = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "SELECT rented_car_id FROM customer WHERE id =" + id + ";";

            ResultSet rs = stmt.executeQuery(sql);


            if (rs.next()) {
                result = rs.getInt("rented_car_id");
            }

            conn.setAutoCommit(true);
            stmt.close();
            conn.close();

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return result;

    }
}
