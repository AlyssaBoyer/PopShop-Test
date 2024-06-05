package com.popshop.popshop.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PopFigureTest {

    @Test
    public void testGetId() {
        PopFigure figure = new PopFigure("Batman", "DC");
        figure.setId(1L);
        assertEquals(1L, figure.getId());
    }

    @Test
    public void testGetName() {
        PopFigure figure = new PopFigure("Batman", "DC");
        assertEquals("Batman", figure.getName());
    }

    @Test
    public void testGetTheme() {
        PopFigure figure = new PopFigure("Batman", "DC");
        assertEquals("DC", figure.getTheme());
    }

    @Test
    public void testSetId() {
        PopFigure figure = new PopFigure();
        figure.setId(1L);
        assertEquals(1L, figure.getId());
    }

    @Test
    public void testSetName() {
        PopFigure figure = new PopFigure();
        figure.setName("Batman");
        assertEquals("Batman", figure.getName());
    }

    @Test
    public void testSetTheme() {
        PopFigure figure = new PopFigure();
        figure.setTheme("DC");
        assertEquals("DC", figure.getTheme());
    }
}