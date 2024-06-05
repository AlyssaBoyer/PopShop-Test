package com.popshop.popshop.services;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import com.popshop.popshop.models.PopFigure;
import com.popshop.popshop.repositories.FigureRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class FigureServiceTest {

    @Autowired
    private FigureService figureService;

    @MockBean
    private FigureRepository figureRepository;

    @Test
    public void testGetAllFigures() {
        List<PopFigure> figures = new ArrayList<>();
        figures.add(new PopFigure("Iron Man", "Marvel"));
        Mockito.when(figureRepository.findAll()).thenReturn(figures);

        List<PopFigure> result = figureService.getAllFigures();
        assertEquals(1, result.size());
        assertEquals("Iron Man", result.get(0).getName());
    }

    @Test
    public void testSaveFigure() {
        PopFigure figure = new PopFigure("Batman", "DC Comics");
        Mockito.when(figureRepository.save(any(PopFigure.class))).thenReturn(figure);

        PopFigure savedFigure = figureService.saveFigure(figure);
        assertEquals("Batman", savedFigure.getName());
    }
}