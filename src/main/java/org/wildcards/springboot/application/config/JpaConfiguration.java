package org.wildcards.springboot.application.config;


import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.orm.jpa.hibernate.SpringNamingStrategy;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.PersistenceUnitManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@PropertySource(value = "classpath:application.properties") //Loading the application.properties
@EnableJpaRepositories(basePackages = {"org.wildcards.springboot.domain.repository",  "org.wildcards.springboot.infrastructure.security.repository"}) 
@EnableTransactionManagement 
public class JpaConfiguration implements EnvironmentAware {

	/**
	 * 
	 */
	@Autowired(required = false)
    private PersistenceUnitManager persistenceUnitManager;
	
	/**
	 * 
	 */
	private RelaxedPropertyResolver jpaPropertyResolver;
	 
	@Override
    public void setEnvironment(Environment environment) {
        this.jpaPropertyResolver = new RelaxedPropertyResolver(environment, "spring.jpa.");
    }
	 


    @Bean //Creating and registering in spring context an entityManager
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        if (persistenceUnitManager != null) {
            entityManagerFactoryBean
                    .setPersistenceUnitManager(persistenceUnitManager);
        }
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("org.wildcards.springboot");
        entityManagerFactoryBean.getJpaPropertyMap().putAll(jpaPropertyResolver.getSubProperties("properties."));
        Map<String, Object> properties = (Map<String, Object>) entityManagerFactoryBean.getJpaPropertyMap();
        properties.put("hibernate.ejb.naming_strategy", jpaPropertyResolver.getProperty("hibernate.naming-strategy", SpringNamingStrategy.class.getName()));
        properties.put("hibernate.hbm2ddl.auto", jpaPropertyResolver.getProperty("hibernate.ddl-auto", "none"));
        return entityManagerFactoryBean;
    }

//	    @Bean //Creating the dataSource using the Jndi 
//	    public DataSource dataSource() {
//	        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//	        dsLookup.setResourceRef(true);
//	        DataSource dataSource = dsLookup.getDataSource(dataSource);
//
//	        return dataSource;
//	    }


	@Bean //Configuring the transactionManager
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
