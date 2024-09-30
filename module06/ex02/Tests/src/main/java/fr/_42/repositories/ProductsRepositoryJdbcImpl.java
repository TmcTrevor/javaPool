package fr._42.repositories;

import fr._42.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import java.util.Objects;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {

    DataSource dataSource;
    public ProductsRepositoryJdbcImpl(DataSource dataSource)
    {
        this.dataSource = dataSource;
    }
    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(rs.getString("name"), rs.getDouble("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    return products;
    }

    @Override
    public Optional<Product> findById(long id) {
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(new Product(rs.getString("name"), rs.getDouble("price")));
            }

        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void save(Product product) {
        String sql = "INSERT INTO products (name, price) VALUES (?, ?)";
        try {
            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE products SET name = ?, price = ? WHERE id = ?";
        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setInt(3, product.getIdentifier());
            ps.executeUpdate();
        } catch (SQLException E)
        {
            throw new RuntimeException(E);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM products WHERE id = ?";
        try {

            Connection con = dataSource.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        }
        catch (SQLException E)
        {
            throw new RuntimeException(E);
        }
    }
}
