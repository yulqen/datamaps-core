package com.matthewlemon.datamaps.core;

import com.matthewlemon.datamaps.core.entities.Datamap;
import com.matthewlemon.datamaps.core.repositories.DatamapRepository;
import com.matthewlemon.datamaps.core.repositories.PersistentDatamapRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DatamapsrestApplication {

	public static final Logger log = LoggerFactory.getLogger(DatamapsrestApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DatamapsrestApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(PersistentDatamapRepository repository) {
		return (args) -> {
		    // Save a couple of new Datamap objects
			repository.save(new Datamap("First Datamap"));
			repository.save(new Datamap("Second Datamap"));

			// fetch all Datamaps
			log.info("Datamaps found with findlAll():");
			log.info("-------------------------------");
			for (Datamap datamap : repository.findAll()) {
				log.info(datamap.toString());
			}
			log.info("There are this many items:");
			log.info(String.valueOf(repository.count()));
		};
	}
}


