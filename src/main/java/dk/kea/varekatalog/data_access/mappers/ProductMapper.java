package dk.kea.varekatalog.data_access.mappers;

import dk.kea.varekatalog.data_access.DBManager;
import dk.kea.varekatalog.domain.entities.Product;

import java.sql.*;

public class ProductMapper {


    public void create(Product product) throws SQLIntegrityConstraintViolationException {
        String query = "INSERT INTO products (name, id, price) VALUES (?, ?, ?)";
        Connection connection = DBManager.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getId());
            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

        } catch (SQLIntegrityConstraintViolationException ex) {
            throw ex;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
