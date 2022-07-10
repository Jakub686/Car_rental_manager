package repository.car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDaoImpl implements CarDao {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/main/resources/db";

    static final String USER = "sa";
    static final String PASS = "";

    static public List<Car> cars = new ArrayList<>();

    @Override
    public void createTable() {

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = //"DROP TABLE IF EXISTS CAR;" +
                    "CREATE TABLE IF NOT EXISTS car (" +
                            "id INT PRIMARY KEY AUTO_INCREMENT, " +
                            "name VARCHAR(255) NOT NULL UNIQUE," +
                            "company_id INT NOT NULL," +
                            "FOREIGN KEY (company_id) REFERENCES company(id)" +
                            ");";

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
    public void getCarList(int company_id) {

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "SELECT name FROM car WHERE company_id =" + company_id + "";


            ResultSet rs = stmt.executeQuery(sql);

            int carId = 1;
            while (rs.next()) {
                cars.add(new Car(carId++, rs.getString("name")));
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
    public void addCar(String car, int company_id) {
        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "INSERT INTO car (name,company_id) VALUES ('" + car + "'," + company_id + ")";

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
    public String getCarName(int carId) {
        Connection conn = null;
        Statement stmt = null;
        String result = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "SELECT name FROM car WHERE id = " + carId;

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next()) {
                result = rs.getString("name");
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

