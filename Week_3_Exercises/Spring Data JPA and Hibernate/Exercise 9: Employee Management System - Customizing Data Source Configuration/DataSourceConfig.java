package com.example.EmployeeManagementSystem;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "primaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(
            @Qualifier("primaryDataSource") DataSource primaryDataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(primaryDataSource);
        em.setPackagesToScan("com.example.EmployeeManagementSystem.model.primary");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return em;
    }

    @Bean(name = "secondaryEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory(
            @Qualifier("secondaryDataSource") DataSource secondaryDataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(secondaryDataSource);
        em.setPackagesToScan("com.example.EmployeeManagementSystem.model.secondary");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return em;
    }

    @Primary
    @Bean(name = "primaryTransactionManager")
    public JpaTransactionManager primaryTransactionManager(
            @Qualifier("primaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory) {
        return new JpaTransactionManager(primaryEntityManagerFactory.getObject());
    }

    @Bean(name = "secondaryTransactionManager")
    public JpaTransactionManager secondaryTransactionManager(
            @Qualifier("secondaryEntityManagerFactory") LocalContainerEntityManagerFactoryBean secondaryEntityManagerFactory) {
        return new JpaTransactionManager(secondaryEntityManagerFactory.getObject());
    }
}
