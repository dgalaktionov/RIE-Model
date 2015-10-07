package es.udc.lbd.rie.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.postgresql.ds.PGPoolingDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("es.udc.lbd.rie")
public class PersistenceContext {

	@Bean(destroyMethod = "close")
    DataSource dataSource(Environment env) throws IllegalStateException, SQLException { 
		
        PGPoolingDataSource pgPoolingDataSource = new PGPoolingDataSource();
        pgPoolingDataSource.setUrl(env.getRequiredProperty("db.url"));
        pgPoolingDataSource.setUser(env.getRequiredProperty("db.username"));
        pgPoolingDataSource.setPassword(env.getRequiredProperty("db.password"));
        pgPoolingDataSource.setInitialConnections(Integer.parseInt(env.getRequiredProperty("db.pool_size")));
		return pgPoolingDataSource;
    }
	
	 @Bean
	 LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, Environment env) {
		 
	        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
	        entityManagerFactoryBean.setDataSource(dataSource);
	        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
	        entityManagerFactoryBean.setPackagesToScan("es.udc.lbd.rie");
	 
	        Properties jpaProperties = new Properties();
	     
	        //Configures the used database dialect. This allows Hibernate to create SQL
	        //that is optimized for the used database.
	        jpaProperties.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
	 
	        //Configures the naming strategy that is used when Hibernate creates
	        //new database objects and schema elements
	        jpaProperties.put("hibernate.ejb.naming_strategy", 
	                env.getRequiredProperty("hibernate.ejb.naming_strategy")
	        );
	 
	        //If the value of this property is true, Hibernate writes all SQL
	        //statements to the console.
	        jpaProperties.put("hibernate.show_sql", 
	                env.getRequiredProperty("hibernate.show_sql")
	        );
	 
	        //If the value of this property is true, Hibernate will format the SQL
	        //that is written to the console.
	        jpaProperties.put("hibernate.format_sql", 
	                env.getRequiredProperty("hibernate.format_sql")
	        );
	 
	        entityManagerFactoryBean.setJpaProperties(jpaProperties);
	 
	        return entityManagerFactoryBean;
	    }
	 
	 @Bean
	 JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
	        JpaTransactionManager transactionManager = new JpaTransactionManager();
	        transactionManager.setEntityManagerFactory(entityManagerFactory);
	        return transactionManager;
	    }
}
