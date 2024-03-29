package com.project.quizapp.entity;

import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "Question can not be empty!")
    private String question;
    @NotBlank(message = "Answer A can not be empty!")
    private String ansA;
    @NotBlank(message = "Answer B can not be empty!")
    private String ansB;
    @NotBlank(message = "Answer C can not be empty!")
    private String ansC;
    @NotBlank(message = "Answer D can not be empty!")
    private String ansD;
    @NotBlank(message = "goodAnswer can not be empty!")
    private String goodAnswer;
    @NotNull(message = "Level can not be empty!")
    @Enumerated(EnumType.STRING)
    private Level level;
    @NotNull(message = "Category can not be empty!")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(length = 2000)
    private String explanation = "Explanation has not been provided";

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
        return ans.equalsIgnoreCase(goodAnswer);
    }
}
