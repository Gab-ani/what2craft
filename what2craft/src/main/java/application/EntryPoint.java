package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import albionDataCommunication.DataFetcher;
import albionDataCommunication.PriceResponse;
import database.ItemService;
import logic.ItemBasic;

@EnableJpaRepositories({"database"})
@ComponentScan({"database"})
@EntityScan({"logic"})
@Configuration
@SpringBootApplication
public class EntryPoint {
	
	@Autowired
	@Lazy
	ItemService itemService;
	
	@Bean
	public DataFetcher dataFetcher() {
		return new DataFetcher();
	}
	
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			
			ItemBasic item = itemService.getByName("Claymore");
			
			System.out.println(dataFetcher().fetchActualData(item, 5, 1, 1, "Lymhurst")[0]);
			// wrath rampant? hand of khor diary? 
		};
	}

	public static void main(String[] args) throws JsonMappingException, JsonProcessingException, RestClientException {
			
		SpringApplication.run(EntryPoint.class, args);
		
	}

}
