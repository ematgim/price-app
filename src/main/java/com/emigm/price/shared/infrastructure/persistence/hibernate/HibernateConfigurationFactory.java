package com.emigm.price.shared.infrastructure.persistence.hibernate;

import com.emigm.price.shared.domain.Injectable;
import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.*;

@Injectable
public final class HibernateConfigurationFactory {

    private final ResourcePatternResolver resourceResolver;

    public HibernateConfigurationFactory(ResourcePatternResolver resourceResolver) {
        this.resourceResolver = resourceResolver;
    }

    public PlatformTransactionManager hibernateTransactionManager(LocalSessionFactoryBean sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory.getObject());

        return transactionManager;
    }

    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setHibernateProperties(hibernateProperties());

        List<Resource> mappingFiles = searchMappingFiles();

        sessionFactory.setMappingLocations(mappingFiles.toArray(new Resource[mappingFiles.size()]));

        return sessionFactory;
    }

    public DataSource dataSource(String host,
                                 Integer port,
                                 String databaseName,
                                 String username,
                                 String password) throws IOException {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl(String.format("jdbc:mysql://%s:%s/%s?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true",
                host,
                port,
                databaseName));
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        Resource mysqlResource = resourceResolver.getResource(String.format(
                "classpath:database/%s.sql",
                databaseName
        ));
        String mysqlSentences = new Scanner(mysqlResource.getInputStream(),
                "UTF-8").useDelimiter("\\A").next();

        dataSource.setConnectionInitSqls(new ArrayList<>(Arrays.asList(mysqlSentences.split(";"))));


        return dataSource;
    }

    private List<Resource> searchMappingFiles() {

        try {
            return Arrays.asList(resourceResolver.getResources("classpath*:**/*.hbm.xml"));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    private Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.put(AvailableSettings.HBM2DDL_AUTO,
                "none");
        hibernateProperties.put(AvailableSettings.SHOW_SQL,
                "true");
        hibernateProperties.put(AvailableSettings.DIALECT,
                "org.hibernate.dialect.MySQL5Dialect");
        hibernateProperties.put(AvailableSettings.CREATE_EMPTY_COMPOSITES_ENABLED,
                "true");
        return hibernateProperties;
    }
}
