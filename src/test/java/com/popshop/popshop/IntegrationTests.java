package com.popshop.popshop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testHomePageAndAddFigure() throws Exception {
		// Tester la page d'accueil
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attributeExists("figures"));

		// Tester l'ajout d'une figurine
		mockMvc.perform(post("/add")
						.param("name", "Iron Man")
						.param("theme", "Marvel"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/"));

		// Vérifier que la figurine a été ajoutée en re-vérifiant la page d'accueil
		mockMvc.perform(get("/"))
				.andExpect(status().isOk())
				.andExpect(view().name("index"))
				.andExpect(model().attributeExists("figures"))
				.andExpect(model().attribute("figures", org.hamcrest.Matchers.hasItem(
						org.hamcrest.Matchers.hasProperty("name", org.hamcrest.Matchers.is("Iron Man"))
				)))
				.andExpect(model().attribute("figures", org.hamcrest.Matchers.hasItem(
						org.hamcrest.Matchers.hasProperty("theme", org.hamcrest.Matchers.is("Marvel"))
				)));
	}
}
