package biblioteka.przemek.configuration;

import java.beans.PropertyVetoException;
import java.util.LinkedHashMap;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableScheduling
@EnableTransactionManagement
@ComponentScan(basePackages = {"biblioteka.przemek"}) 
@PropertySource("classpath:persistance.properties")
public class AppConfiguration {

	@Autowired
	Environment env;

	@Bean
	public ViewResolver getViewResolver() {

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;

	}
	//Security Database
	@Bean
	public DataSource getDataSourceSecurity() {

		ComboPooledDataSource securityDataSource = new ComboPooledDataSource();
		try {
			securityDataSource.setDriverClass(env.getProperty("security.jdbc.driver"));
			securityDataSource.setJdbcUrl(env.getProperty("security.jdbc.url"));
			securityDataSource.setUser(env.getProperty("security.jdbc.user"));
			securityDataSource.setPassword(env.getProperty("security.jdbc.password"));
		} catch (PropertyVetoException e) {

			e.printStackTrace();
		}

		securityDataSource
				.setInitialPoolSize(Integer.parseInt(env.getProperty("security.connection.pool.initialPoolSize")));
		securityDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("security.connection.pool.minPoolSize")));
		securityDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("security.connection.pool.maxPoolSize")));
		securityDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("security.connection.pool.maxIdleTime")));

		return securityDataSource;

	}
	
	@Bean
	public UserDetailsManager getUserDetailsManager() {
		
		JdbcUserDetailsManager jdbcUserDetailManager = new JdbcUserDetailsManager();
		jdbcUserDetailManager.setDataSource(getDataSourceSecurity());
		
		return jdbcUserDetailManager;
		
	}
	
	//Hibernate
	@Bean
	public DataSource getHibernateDataSource() {

		ComboPooledDataSource hibernateDataSource = new ComboPooledDataSource();
		try {
			hibernateDataSource.setDriverClass(env.getProperty("hibernate.jdbc.driver"));
			hibernateDataSource.setJdbcUrl(env.getProperty("hibernate.jdbc.url"));
			hibernateDataSource.setUser(env.getProperty("hibernate.jdbc.user"));
			hibernateDataSource.setPassword(env.getProperty("hibernate.jdbc.password"));
		} catch (PropertyVetoException e) {

			e.printStackTrace();
		}

		hibernateDataSource
				.setInitialPoolSize(Integer.parseInt(env.getProperty("hibernate.connection.pool.initialPoolSize")));
		hibernateDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("hibernate.connection.pool.minPoolSize")));
		hibernateDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("hibernate.connection.pool.maxPoolSize")));
		hibernateDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("hibernate.connection.pool.maxIdleTime")));

		return hibernateDataSource;
	}

	@Bean
	public Properties getHibernateProperties() {

		Properties hibernateProperties = new Properties();

		hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
		hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));

		return hibernateProperties;
	}

	@Bean
	public LocalSessionFactoryBean SessionFactory() {
		
		LocalSessionFactoryBean sessionFactory =  new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(getHibernateDataSource());		
		sessionFactory.setHibernateProperties(getHibernateProperties());
		sessionFactory.setPackagesToScan(env.getProperty("packagesToScan"));
				
		return sessionFactory;
	}
	
	@Bean
	public HibernateTransactionManager getTransactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(SessionFactory().getObject());
		
		return transactionManager;
	}

	
	@Bean
	@Qualifier("categoryList")
	public LinkedHashMap<String, String> categoryList(){
		LinkedHashMap<String, String> categoryList = new LinkedHashMap<>();
		categoryList.put("Title", "Title");
		categoryList.put("Author", "Author");
	
		return categoryList;
	}
	
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
		
		
		javaMailSender.setHost("smtp.gmail.com");
		javaMailSender.setPort(587);
		
		javaMailSender.setUsername("*******@gmail.com");
		javaMailSender.setPassword("******");
	
		Properties props = javaMailSender.getJavaMailProperties();
		 
			props.put("mail.transport.protocol", "smtp");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.starttls.enable", "true");
		    props.put("mail.debug", "true");
		
		return javaMailSender;
		
	}
	
	
	
}
