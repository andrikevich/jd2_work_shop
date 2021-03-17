package it.academy.dao;

import com.mysql.cj.jdbc.Driver;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import it.academy.model.*;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:datasource.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = "it.academy")
public class DaoConfiguration {

    @Autowired
    Environment env;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(env.getProperty("datasourse.url"));
        hikariConfig.setDriverClassName(Driver.class.getName());
        hikariConfig.setUsername(env.getProperty("datasourse.username"));
        hikariConfig.setPassword(env.getProperty("datasourse.password"));
        hikariConfig.setMaximumPoolSize(100);
        return new HikariDataSource(hikariConfig);
    }
//    public DataSource dataSource(){
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setUrl(env.getProperty("datasourse.url"));
//        dataSource.setDriverClassName(Driver.class.getName());
//        dataSource.setUsername(env.getProperty("datasourse.username"));
//        dataSource.setPassword(env.getProperty("datasourse.password"));
//        dataSource.setInitialSize(20);
//        dataSource.setMaxTotal(30);
//        return dataSource;
//    }

    @Bean
    // работает без Autowired
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setAnnotatedClasses(Person.class, Product.class, ProductPrice.class, Promo.class, ShopUser.class, Visitor.class);
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
    }

    private Properties getHibernateProperties() {

        // set hibernate properties
        Properties props = new Properties();

        props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL57Dialect");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "update");

        return props;
    }

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
