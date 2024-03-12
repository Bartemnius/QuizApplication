package com.project.quizapp.model;

import com.project.quizapp.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class Quiz {
    private List<Question> questions;
    private int score;
    private int maxScore;
}
