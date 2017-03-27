package za.co.wifi.info.config;

import java.util.Properties;
import javax.sql.DataSource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * DataSource definition for database connection. Settings are read from the
 * application.properties file (using the environment object).
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfiguration {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(DatabaseConfiguration.class.getName());

    @Autowired
    private Environment environment;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactory;

    /**
     * DataSource definition for database connection. Settings are read from the
     * application.properties file (using the environment object).
     *
     * @return DataSource
     */
    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource configuredDatasource = new DriverManagerDataSource();
        configuredDatasource.setDriverClassName(environment.getProperty("spring.database.driverClassName"));
        configuredDatasource.setUrl(environment.getProperty("spring.datasource.url"));
        configuredDatasource.setUsername(environment.getProperty("spring.datasource.username"));
        configuredDatasource.setPassword(environment.getProperty("spring.datasource.password"));

        return configuredDatasource;
    }

    /**
     * Declare the JPA entity manager factory.
     *
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean configuredEntityManagerFactory
                = new LocalContainerEntityManagerFactoryBean();

        configuredEntityManagerFactory.setDataSource(dataSource);

        // Classpath scanning of @Component, @Service, etc annotated class
        configuredEntityManagerFactory.setPackagesToScan(
                environment.getProperty("entitymanager.packagesToScan"));

        // Vendor adapter
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        configuredEntityManagerFactory.setJpaVendorAdapter(vendorAdapter);

        // Hibernate properties
        Properties additionalProperties = new Properties();
        additionalProperties.put(
                "hibernate.dialect",
                environment.getProperty("spring.jpa.properties.hibernate.dialect"));
        additionalProperties.put(
                "hibernate.show_sql",
                environment.getProperty("spring.jpa.show-sql"));
        additionalProperties.put(
                "hibernate.hbm2ddl.auto",
                environment.getProperty("spring.jpa.generate-ddl"));
        additionalProperties.put(
                "spring.jpa.generate-ddl",
                environment.getProperty("spring.jpa.hibernate.ddl-auto"));
        additionalProperties.put(
                "spring.jpa.hibernate.naming-strategy",
                environment.getProperty("spring.jpa.hibernate.naming-strategy"));

        configuredEntityManagerFactory.setJpaProperties(additionalProperties);

        return configuredEntityManagerFactory;
    }

    /**
     * Declare the transaction manager.
     *
     * @return JpaTransactionManager
     */
    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager
                = new JpaTransactionManager();

        transactionManager.setEntityManagerFactory(
                entityManagerFactory.getObject());

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
