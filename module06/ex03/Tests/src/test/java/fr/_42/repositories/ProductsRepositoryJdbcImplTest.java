package fr._42.repositories;

import fr._42.models.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsRepositoryJdbcImplTest {

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
            new Product(0, "Laptop", 10),
            new Product(1, "Smartphone", 20),
            new Product(2, "Headphones", 30),
            new Product(3, "Tablet", 40),
            new Product(4, "Smartwatch", 50)
    );

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(2, "Headphones", 30);
    final Product EXPECTED_SAVE_BY_ID_PRODUCT = new Product(5, "MACBOOK", 60);
    final Product EXPECTED_UPDATED_PRODUCT = new Product(3, "Updated Smartphone",  22);


    private EmbeddedDatabase db;
    private Connection connection;
    private ProductsRepositoryJdbcImpl testImpl;

    @BeforeEach
    void init() throws Exception {
        initConnection();
    }



    @AfterEach
    void tearDown() throws Exception {
        if (connection != null) {
            connection.close();  // Close the connection
        }
        if (db != null) {
            db.shutdown();  // Shut down the embedded database
        }
    }

    void initConnection() throws SQLException {
        db = new EmbeddedDatabaseBuilder()
                .generateUniqueName(true)
                .setType(EmbeddedDatabaseType.HSQL)
                .setScriptEncoding("UTF-8")
                .ignoreFailedDrops(true)
                .addScript("schema.sql")
                .addScripts("data.sql")
                .build();

        connection = db.getConnection();
        testImpl = new ProductsRepositoryJdbcImpl(db);
    }


    @Test
    void connectionCheck(){
        assertDoesNotThrow(this::initConnection);
        System.out.println(connection);
    }

    public Connection getConnection() {
        return connection;
    }

    @Test
    void testFindAll() throws SQLException {
        List<Product> allProducts =  testImpl.findAll();
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS.size(), allProducts.size());
        for (int i = 0; i < allProducts.size(); i++) {
//            System.out.println(allProducts.get(i));
//            System.out.println(EXPECTED_FIND_ALL_PRODUCTS.get(i));
            assertEquals(allProducts.get(i), EXPECTED_FIND_ALL_PRODUCTS.get(i));
        }
    }

    @Test
    void testFindById() throws SQLException {
        Optional<Product> product = testImpl.findById(2);
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, product.orElse(null));
    }

    @Test
    void testSave() throws SQLException {
        testImpl.save(EXPECTED_SAVE_BY_ID_PRODUCT);
        Optional<Product> product = testImpl.findById(5);
        assertEquals(EXPECTED_SAVE_BY_ID_PRODUCT, product.orElse(null));
    }


    @Test
    void testUpdate() throws SQLException {

        testImpl.update(EXPECTED_UPDATED_PRODUCT);
        Optional<Product> product = testImpl.findById(3);
        assertEquals(EXPECTED_UPDATED_PRODUCT, product.orElse(null));

    }

    @Test
    void testDelete() throws SQLException {
        testImpl.delete(2L);
        Optional<Product> product = testImpl.findById(2);
        assertEquals(product, Optional.empty());
        List<Product> allProducts =  testImpl.findAll();
        assertEquals(4, allProducts.size());

    }
}
