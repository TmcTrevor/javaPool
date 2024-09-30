package fr._42.repositories;

//import org.hsqldb.jdbc.JDBCDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
//import java.sql.DriverManager;
//import java.sql.Statement;

public class EmbeddedDataSourceTest {




    private EmbeddedDatabase db;
    private Connection connection;

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
    }


    @Test
    void connectionCheck(){
        assertDoesNotThrow(this::initConnection);
        System.out.println(connection);
    }

    public Connection getConnection() {
        return connection;
    }
}