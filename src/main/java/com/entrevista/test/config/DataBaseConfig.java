package com.entrevista.test.config;

import java.util.Properties;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration for Data Base and JPA
 *
 * @author Victor Manuel Sanchez (vmso86@gmail.com) vimso
 */
@Configuration
@EnableJpaRepositories(basePackages = {"com.entrevista.test.repository"})
@EnableTransactionManagement
@PropertySource("classpath:config.properties")
public class DataBaseConfig {

    //Data base properties
    private static final String MYSQL_ENABLE = "mysql.enable";

    //Mysql
    private static final String PERSISTENCE_MYSQL_JDBC_DRIVER = "persistence.mysql.jdbc.driver";
    private static final String PERSISTENCE_MYSQL_JDBC_URL = "persistence.mysql.jdbc.url";
    private static final String PERSISTENCE_MYSQL_JDBC_USERNAME = "persistence.mysql.jdbc.username";
    private static final String PERSISTENCE_MYSQL_JDBC_PASSWORD = "persistence.mysql.jdbc.password";

    //ORM Properties
    private static final String HIBERNATE_SHOW_SQL = "hibernate.ShowSql";
    private static final String HIBERNATE_GENERATE_DDL = "hibernate.GenerateDdl";
    private static final String HIBERNATE_DIALECT_MYSQL = "hibernate.dialect.mysql";
    private static final String HIBERNATE_DIALECT_HSQL = "hibernate.dialect.hsql";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String PACKAGES_ENTITIES = "com.entrevista.test.entities";

    @Inject
    Environment env;

    @Bean
    public PlatformTransactionManager transactionManager() {

        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(this.entityManagerFactory().getObject());
        return tm;
    }

    @Bean
    public DataSource dataSource() {

        if (Boolean.parseBoolean(env.getRequiredProperty(MYSQL_ENABLE))) {
            return getMysqlDatasource();
        }

        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
                = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(this.dataSource());
        entityManagerFactoryBean.setPackagesToScan(new String[]{PACKAGES_ENTITIES});
        entityManagerFactoryBean.setPersistenceUnitName("PersistContext");

        HibernateJpaVendorAdapter va = getHibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(va);

        Properties ps = getJpaProperties();
        entityManagerFactoryBean.setJpaProperties(ps);
        entityManagerFactoryBean.afterPropertiesSet();

        return entityManagerFactoryBean;

    }

    private DataSource getMysqlDatasource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(env.getRequiredProperty(PERSISTENCE_MYSQL_JDBC_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PERSISTENCE_MYSQL_JDBC_URL));
        dataSource.setUsername(env.getRequiredProperty(PERSISTENCE_MYSQL_JDBC_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PERSISTENCE_MYSQL_JDBC_PASSWORD));

        return dataSource;
    }

    private HibernateJpaVendorAdapter getHibernateJpaVendorAdapter() {

        final HibernateJpaVendorAdapter hva = new HibernateJpaVendorAdapter();

        hva.setShowSql(env.getRequiredProperty(HIBERNATE_SHOW_SQL, Boolean.class));
        hva.setGenerateDdl(env.getRequiredProperty(HIBERNATE_GENERATE_DDL, Boolean.class));

        return hva;
    }

    private Properties getJpaProperties() {

        final Properties ps = new Properties();

        final String hibernateDialect = Boolean.parseBoolean(env.getRequiredProperty(MYSQL_ENABLE))
                ? HIBERNATE_DIALECT_MYSQL : HIBERNATE_DIALECT_HSQL;

        ps.put("hibernate.dialect", env.getRequiredProperty(hibernateDialect));
        ps.put("hibernate.hbm2ddl.auto", env.getRequiredProperty(HIBERNATE_HBM2DDL_AUTO));

        return ps;
    }
}
