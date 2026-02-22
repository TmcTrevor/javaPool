package _42.spring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ComponentScan("_42.spring.service")
public class TestApplicationConfig {

    // Single H2 DataSource — replaces both Hikari and Simple
    @Bean
    public DataSource dataSourceHikari() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")  // runs this SQL on startup
                .build();
    }

    // Same bean name — satisfies @Qualifier("dataSourceSimple") too
    @Bean
    public DataSource dataSourceSimple() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("schema.sql")
                .build();
    }
}