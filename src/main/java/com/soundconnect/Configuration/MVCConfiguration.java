package com.soundconnect.Configuration;

<<<<<<< HEAD
=======
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

>>>>>>> 96b9518e168573bb41c67e69128a3945579bc344
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MVCConfiguration extends WebMvcConfigurerAdapter{

	@Bean(name = "dataSource")
<<<<<<< HEAD
    public DriverManagerDataSource getDataSource() {
=======
    public DataSource getDataSource() throws NamingException {
		// Obtain our environment naming context
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");

		// Look up our data source
//		DataSource dataSource = (DataSource)
//		  envCtx.lookup("jdbc/PostgreSQLDS");
>>>>>>> 96b9518e168573bb41c67e69128a3945579bc344
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://127.0.0.1:53876/i");
        dataSource.setUsername("adminlzisxwn");
        dataSource.setPassword("Q-XgQJAWB_96");
        return dataSource;
    }

	@Bean
	@Autowired
<<<<<<< HEAD
	public JdbcTemplate getJDBCTemplate(){
=======
	public JdbcTemplate getJDBCTemplate() throws NamingException{
>>>>>>> 96b9518e168573bb41c67e69128a3945579bc344
		return new JdbcTemplate(getDataSource());
	}
}