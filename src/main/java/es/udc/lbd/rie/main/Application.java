package es.udc.lbd.rie.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import es.udc.lbd.rie.config.PersistenceContext;
import es.udc.lbd.rie.model.WaterNode;
import es.udc.lbd.rie.repository.WaterNodeRepository;

@SpringBootApplication
@Import(PersistenceContext.class)
public class Application {
	
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
	
	@Bean
	public CommandLineRunner run(WaterNodeRepository repo) {
		return (args) -> { 
			log.info(repo.count() + " water nodes.");
			
			for (WaterNode area : repo.findAll()) {
				log.info(area.toString());
			}
		};
	}
}
