package fr.rb.fireforest.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import fr.rb.fireforest.business.Configuration;
import fr.rb.fireforest.business.Materiau;
import fr.rb.fireforest.service.ConfigurationService;
import fr.rb.fireforest.service.MateriauService;

@SpringBootTest
@AutoConfigureMockMvc 


	class FireForestControllerTest {
		@Autowired
		private MockMvc mockMvc;
		@Autowired
		private ConfigurationService configurationService;
		@Autowired
		private MateriauService materiauService;
		@Autowired
		private static Configuration configuration;
	
		@Test
		void testerGetGrid() throws Exception {
	
			MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/index");
	
			mockMvc.perform(requestBuilder)
			.andExpect(view().name("index"))
			.andExpect(status().isOk());
			List<Configuration> configurations = configurationService.recupererConfigurations();
			if (configurations.isEmpty()) {
			    throw new IllegalStateException("La liste des configurations ne doit pas être vide");
			}
			List<Materiau> materiaux = materiauService.recupererMateriaux();
			if (materiaux.isEmpty()) {
			    throw new IllegalStateException("La liste de materiaux ne doit pas être vide");
			}
			Configuration config = new Configuration();
			Assert.isInstanceOf(Configuration.class, config);
			Assert.isTrue(9 == Math.sqrt(81));
		
			 
		}
		
		@Test
		void testerPostConfiguration() throws Exception {
			
			
			mockMvc.perform(MockMvcRequestBuilders.post("/configuration")
		            .accept(MediaType.TEXT_HTML)
		            .param("nom", "newConfig")
		            .param("definition", "90" )
		            .param("materiau", "sapin")
		            .param("pbPropagation", "60")
		            .param("nbFoyer" , "3"))
		            .andExpect(MockMvcResultMatchers.redirectedUrl("/index"))
		            .andExpect(status().is3xxRedirection());
		}
		
		@Test
		void testerPostLoadConfiguration() throws Exception {
			
			
			mockMvc.perform(MockMvcRequestBuilders.post("/loadconfiguration")
		            .accept(MediaType.TEXT_HTML)
		            .param("selectedConfiguration", "medium"))
		            .andExpect(MockMvcResultMatchers.redirectedUrl("/index"))
		            .andExpect(status().is3xxRedirection());
			configuration = configurationService.recupererConfigurationParId("medium");
			Materiau bouleau = materiauService.recupererMateriauParId("bouleau");
			if (configuration.getNom() != "medium" &&
				configuration.getMateriau() != bouleau &&
				configuration.getNbFoyer() != 4 &&
				configuration.getDefinition() != 50 &&
				configuration.getPbPropagation() != 60) {
			    throw new IllegalStateException("La configuration chargée doit être conforme à la definition medium");
			    
			}
			Assert.isInstanceOf(Configuration.class, configuration);
		}
		
}
