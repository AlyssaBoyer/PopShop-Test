package com.popshop.popshop.controllers;

import com.popshop.popshop.models.PopFigure;
import com.popshop.popshop.services.FigureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FigureController {
    private static final Logger logger = LoggerFactory.getLogger(FigureController.class);

    @Autowired
    FigureService figureService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("figures", figureService.getAllFigures());
        return "index";
    }

    @GetMapping("/add")
    public String showAddFigureForm() {
        return "add-figure";
    }

    @PostMapping("/add")
    public String addFigure(@RequestParam String name, @RequestParam String theme, Model model) {
        logger.info("Adding figure: " + name + " with theme: " + theme);
        PopFigure figure = new PopFigure(name, theme);
        figureService.saveFigure(figure);
        return "redirect:/";
    }
}