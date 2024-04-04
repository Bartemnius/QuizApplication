package com.project.quizapp.dto;

import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PostQuestionDto(
        @NotBlank(message = "Question can not be empty!") String question,
        @NotBlank(message = "Answer A can not be empty!") String ansA,
        @NotBlank(message = "Answer B can not be empty!") String ansB,
        @NotBlank(message = "Answer C can not be empty!") String ansC,
        @NotBlank(message = "Answer D can not be empty!") String ansD,
        @NotBlank(message = "Good answer can not be empty!") String goodAnswer,
        @NotNull(message = "Level can not be empty!") Level level,
        @NotNull(message = "Category can not be empty!") Category category,
        @NotNull(message = "Please provide explanation to the question!") String explanation) {
}
