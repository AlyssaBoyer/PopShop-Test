package com.popshop.popshop.repositories;

import com.popshop.popshop.models.PopFigure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FigureRepository extends JpaRepository<PopFigure, Long> {}

