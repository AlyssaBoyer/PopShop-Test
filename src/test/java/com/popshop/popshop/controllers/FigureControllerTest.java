package com.popshop.popshop.controllers;

import com.popshop.popshop.models.PopFigure;
import com.popshop.popshop.services.FigureService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class FigureControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private FigureService figureService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    // Tester la page d'accueil
    @Test
    public void testIndex() throws Exception {
        List<PopFigure> figures = new ArrayList<>();
        figures.add(new PopFigure("Iron Man", "Marvel"));

        when(figureService.getAllFigures()).thenReturn(figures);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("figures"))
                .andExpect(model().attribute("figures", figures));
    }

    // Tester l'ajout d'une figurine via une requête POST
    @Test
    public void testAddFigure() throws Exception {
        when(figureService.saveFigure(any(PopFigure.class))).thenReturn(new PopFigure("Iron Man", "Marvel"));

        mockMvc.perform(post("/add")
                        .param("name", "Iron Man")
                        .param("theme", "Marvel"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    // Tester la méthode addFigure du contrôleur
    @Test
    public void testAddFigureMethod() {
        FigureController figureController = new FigureController();
        FigureService figureServiceMock = Mockito.mock(FigureService.class);
        Model model = Mockito.mock(Model.class);

        figureController.figureService = figureServiceMock;
        PopFigure figure = new PopFigure("Spider-Man", "Marvel");
        when(figureServiceMock.saveFigure(any(PopFigure.class))).thenReturn(figure);

        String viewName = figureController.addFigure("Spider-Man", "Marvel", model);
        assertEquals("redirect:/", viewName);
    }
}