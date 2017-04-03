package za.co.wifi.info.client.config;

import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DataSource definition for database connection. Settings are read from the
 * application.properties file (using the environment object).
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

    @Autowired
    private Environment environment;

    /**
     * DataSource definition for database connection. Settings are read from the
     * application.properties file (using the environment object).
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {
        BasicDataSource configuredDatasource = new BasicDataSource();
        configuredDatasource.setDriverClassName(environment.getProperty("spring.database.driverClassName"));
        configuredDatasource.setUrl(environment.getProperty("spring.datasource.url"));
        configuredDatasource.setUsername(environment.getProperty("spring.datasource.username"));
        configuredDatasource.setPassword(environment.getProperty("spring.datasource.password"));

        return configuredDatasource;
    }

    /**
     * Declare the JPA entity manager factory.
     *
     * @param dataSource
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean configuredEntityManagerFactory
                = new LocalContainerEntityManagerFactoryBean();

        configuredEntityManagerFactory.setPackagesToScan(environment.getProperty("entitymanager.packagesToScan"));
        configuredEntityManagerFactory.setPersistenceProvider(new HibernatePersistenceProvider());

        // Hibernate properties
        Properties additionalProperties = new Properties();
        additionalProperties.put("hibernate.dialect",
                environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        additionalProperties.put("hibernate.show_sql",
                environment.getProperty("spring.jpa.show-sql"));
        additionalProperties.put("hibernate.hbm2ddl.auto",
                environment.getProperty("spring.jpa.hibernate.ddl-auto"));

        configuredEntityManagerFactory.setJpaProperties(additionalProperties);
        configuredEntityManagerFactory.setDataSource(dataSource);

        return configuredEntityManagerFactory;
    }

    /**
     * Declare the transaction manager.
     *
     * @param emf
     * @return JpaTransactionManager
     */
    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    /**
     * PersistenceExceptionTranslationPostProcessor is a bean post processor
     * which adds an advisor to any bean annotated with Repository so that any
     * platform-specific exceptions are caught and then re-thrown as one
     * Spring's unchecked data access exceptions (i.e. a subclass of
     * DataAccessException).
     *
     * @return PersistenceExceptionTranslationPostProcessor
     */
    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
