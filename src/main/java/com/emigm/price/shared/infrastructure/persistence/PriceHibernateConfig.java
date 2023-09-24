package com.emigm.price.shared.infrastructure.persistence;

import com.emigm.price.shared.infrastructure.config.Parameter;
import com.emigm.price.shared.infrastructure.config.ParameterNotExist;
import com.emigm.price.shared.infrastructure.persistence.hibernate.HibernateConfigurationFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@EnableTransactionManagement
public class PriceHibernateConfig {


    private final HibernateConfigurationFactory factory;

    private final Parameter param;

    public PriceHibernateConfig(HibernateConfigurationFactory factory,
                                Parameter param) {
        this.factory = factory;
        this.param = param;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() throws IOException, ParameterNotExist {
        return factory.hibernateTransactionManager(sessionFactory());
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() throws IOException, ParameterNotExist {
        return factory.sessionFactory(dataSource());
    }

    @Bean
    public DataSource dataSource() throws IOException, ParameterNotExist {
        return factory.dataSource(param.get("DATABASE_HOST"),
                param.getInt("DATABASE_PORT"),
                param.get("DATABASE_NAME"),
                param.get("DATABASE_USER"),
                param.get("DATABASE_PASSWORD"));
    }
}

