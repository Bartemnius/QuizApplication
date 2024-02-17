package com.project.quizapp.dto;

import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import jakarta.validation.constraints.NotBlank;

public record QuestionDto(@NotBlank(message = "Question can not be empty!") String question,
                          @NotBlank(message = "Answer A can not be empty!") String asnA,
                          @NotBlank(message = "Answer B can not be empty!") String ansB,
                          @NotBlank(message = "Answer C can not be empty!") String ansC,
                          @NotBlank(message = "Answer D can not be empty!") String ansD,
                          @NotBlank(message = "Good answer can not be empty!") String goodAns,
                          @NotBlank(message = "Category can not be empty!") Category category,
                          @NotBlank(message = "Level can not be empty!") Level level) {

    // TODO:
    //  Here maybe some validation should be done in custom validation classes or somthing like that

}
