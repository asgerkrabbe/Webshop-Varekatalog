package dk.kea.varekatalog.data_access.mappers;

import dk.kea.varekatalog.data_access.DBManager;
import dk.kea.varekatalog.domain.entities.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductMapper {

    Product product;

    public void create(Product product) throws SQLIntegrityConstraintViolationException {
        String query = "INSERT INTO products (name, price) VALUES (?, ?)";
        Connection connection = DBManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

        } catch (SQLIntegrityConstraintViolationException ex) {
            throw ex;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Product product) {
        String query = "UPDATE products SET name = ?, price = ? WHERE id = ?";
        Connection connection = DBManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Product findById(int id) {
        String query = "SELECT name, price FROM products WHERE id = ?";
        Connection connection = DBManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                return new Product(name, price);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public ArrayList<Product> findAll() {
        String query = "SELECT * FROM products";
        Connection con = DBManager.getConnection();
        ArrayList<Product> products = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ResultSet resultSet = preparedStatement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");
                int id = resultSet.getInt("id");
                products.add(new Product(name, price, id));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return products;
    }


    public void delete(int id) {
        String query = "DELETE FROM products WHERE id = ?";
        Connection connection = DBManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.NO_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


}
