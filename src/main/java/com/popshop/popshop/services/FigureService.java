package com.popshop.popshop.services;

import com.popshop.popshop.models.PopFigure;
import com.popshop.popshop.repositories.FigureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FigureService {
    @Autowired
    private FigureRepository figureRepository;

    public List<PopFigure> getAllFigures() {
        return figureRepository.findAll();
    }

    public PopFigure saveFigure(PopFigure figure) {
        return figureRepository.save(figure);
    }
}