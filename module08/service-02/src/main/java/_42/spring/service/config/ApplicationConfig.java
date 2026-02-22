package _42.spring.service.config;


import _42.spring.service.repositories.UsersRepositoryJdbcTemplateImpl;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;


@Configuration
@PropertySource("classpath:db.properties")
@ComponentScan("_42.spring.service")
public class ApplicationConfig {

    @Value("${db.url}")
    private String url;

    @Value("${db.user}")
    private String user;

    @Value("${db.password}")
    private String password;

    @Value("${db.driver.name}")
    private String driverClassName;

    @Bean
    public DataSource dataSourceHikari() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(this.url);
        ds.setUsername(this.user);
        ds.setPassword(this.password);
        ds.setDriverClassName(this.driverClassName);
        return ds;
    }


    @Bean
    public DataSource dataSourceSimple() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(this.driverClassName);
        ds.setUrl(this.url);
        ds.setPassword(this.password);
        ds.setUsername(this.user);
        return ds;
    }





    @Override
    public String toString()
    {
        return "";
    }

}
