package com.example.EmployeeManagementSystem;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
    basePackages = "com.example.EmployeeManagementSystem.repository.secondary",
    entityManagerFactoryRef = "secondaryEntityManagerFactory",
    transactionManagerRef = "secondaryTransactionManager"
)
public class SecondaryConfig { }