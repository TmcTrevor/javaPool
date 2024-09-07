package fr._42.chat.repositories;


import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

public class MessageRepositoryJdbcImpl implements MessageRepository {
    
    // private final HikariDataSource dataSource;
    private final DataSource dataSource;
    
    public MessageRepositoryJdbcImpl(DataSource hikariDS){
        dataSource = hikariDS;
    }
}
