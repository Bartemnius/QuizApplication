package com.project.quizapp.entity;

import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Question {

    @Id
    @SequenceGenerator(name = "question_sequence", sequenceName = "question_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_sequence")
    private Long id;
    private String question;
    private String ansA;
    private String ansB;
    private String ansC;
    private String ansD;
    private String goodAnswer;
    @Enumerated(EnumType.STRING)
    private Level level;
    @Enumerated(EnumType.STRING)
    private Category category;

    public Question(String question, String ansA, String ansB, String ansC, String ansD, String goodAnswer, Level level, Category category) {
        this.question = question;
        this.ansA = ansA;
        this.ansB = ansB;
        this.ansC = ansC;
        this.ansD = ansD;
        this.goodAnswer = goodAnswer;
        this.level = level;
        this.category = category;
    }

    public boolean isAnswerCorrect(String ans) {
        return ans.equals(goodAnswer);
    }
}
