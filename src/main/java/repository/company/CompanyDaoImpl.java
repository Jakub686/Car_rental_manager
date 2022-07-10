package repository.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao {

    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:./src/main/resources/db";

    static final String USER = "sa";
    static final String PASS = "";

    static public List<Company> companies = new ArrayList<>();

    @Override
    public void createTable() {
        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = //"DROP TABLE IF EXISTS CAR;" +
                    "CREATE TABLE IF NOT EXISTS company (" +
                            "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT, " +
                            "name VARCHAR(255) NOT NULL UNIQUE" +
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
    public void addCompany(String name) {

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "INSERT INTO company (name) VALUES ('" + name + "')";

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
    public void getAllCompanies() {

        Connection conn = null;
        Statement stmt = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "SELECT * FROM company";


            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                companies.add(new Company(rs.getInt("id"), rs.getString("name")));
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

    public String getCompanyName(int companyId) {

        Connection conn = null;
        Statement stmt = null;
        String result = null;

        try {

            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL);

            stmt = conn.createStatement();

            String sql = "SELECT name FROM company WHERE id =" + companyId;


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

