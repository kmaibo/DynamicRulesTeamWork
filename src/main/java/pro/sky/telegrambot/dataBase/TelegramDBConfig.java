package pro.sky.telegrambot.dataBase;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "pro.sky.telegrambot.repository.telegram",
        entityManagerFactoryRef = "telegramEntityManagerFactory",
        transactionManagerRef = "telegramTransactionManager"
)
@EntityScan(basePackages = "pro.sky.telegrambot")
public class TelegramDBConfig {


    @Bean(name = "telegramDataSource")
    public DataSource telegramDataSource(PrimaryDataSourceProperties properties) {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url(properties.getUrl());
        builder.username(properties.getUsername());
        builder.password(properties.getPassword());
        builder.driverClassName(properties.getDriverClassName());
        return builder.build();
    }


    @Bean(name = "telegramEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean telegramEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("telegramDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("pro.sky.telegrambot")
                .persistenceUnit("telegram")
                .build();
    }


    @Bean(name = "telegramTransactionManager")
    public PlatformTransactionManager telegramTransactionManager(
            @Qualifier("telegramEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}

