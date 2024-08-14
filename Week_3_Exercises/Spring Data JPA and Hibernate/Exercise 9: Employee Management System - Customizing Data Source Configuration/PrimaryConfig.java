package com.example.EmployeeManagementSystem;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(
    basePackages = "com.example.EmployeeManagementSystem.repository.primary",
    entityManagerFactoryRef = "primaryEntityManagerFactory",
    transactionManagerRef = "primaryTransactionManager"
)
public class PrimaryConfig { }