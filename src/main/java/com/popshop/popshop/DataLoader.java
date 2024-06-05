package com.popshop.popshop;

import com.popshop.popshop.models.PopFigure;
import com.popshop.popshop.repositories.FigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader {

    @Autowired
    private FigureRepository figureRepository;


    public void loadInitialData() {
        figureRepository.save(new PopFigure("Iron Man", "Marvel"));
        figureRepository.save(new PopFigure("Elsa", "Disney"));
        figureRepository.save(new PopFigure("Michael Jackson", "Pop Stars"));
    }

    public void clearData() {
        figureRepository.deleteAll();
    }
}