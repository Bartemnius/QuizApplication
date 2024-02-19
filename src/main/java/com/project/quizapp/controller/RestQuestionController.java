package com.project.quizapp.controller;

import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.service.QuestionService;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<QuestionDto>> getAllQuestion() {
        return new ResponseEntity<>(questionService.getAllQuestions(), HttpStatus.OK);
    }

    @PostMapping("/addQuestion")
    public ResponseEntity<Long> addQuestion(@Valid @RequestBody QuestionDto questionDto) {
        return new ResponseEntity<>(questionService.addQuestion(questionDto), HttpStatus.CREATED);

    }

    @GetMapping("/level/{questionLevel}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByLevel(@PathVariable Level questionLevel) {
        return new ResponseEntity<>(questionService.getQuestionsByLevel(questionLevel), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByLevel(@PathVariable Category category) {
        return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/randomQuestion")
    public ResponseEntity<QuestionDto> getRandomQuestion() {
        return new ResponseEntity<>(questionService.getRandomQuestion(), HttpStatus.OK);
    }
}
