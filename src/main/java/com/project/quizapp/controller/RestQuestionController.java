package com.project.quizapp.controller;

import com.project.quizapp.entity.Question;
import com.project.quizapp.service.QuestionService;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
@RequiredArgsConstructor
public class RestQuestionController {

    private final QuestionService questionService;

    // TODO: maybe if there is like 1000000 question in DB there should be paging
    //  but is to be done much later
    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getAllQuestion() {
        return questionService.getAllQuestions();
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@Valid @RequestBody Question question) {
        questionService.addQuestion(question);
        return ResponseEntity.ok("Question Added!");
    }

    @GetMapping("/level/{questionLevel}")
    public ResponseEntity<List<Question>> getQuestionsByLevel(@PathVariable Level questionLevel) {
        return questionService.getQuestionsByLevel(questionLevel);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByLevel(@PathVariable Category category) {
        return questionService.getQuestionsByCategory(category);
    }

    @GetMapping("/randomQuestion")
    public ResponseEntity<Question> getRandomQuestion() {
        return questionService.getRandomQuestion();
    }
}
