package com.project.quizapp.controller;

import com.project.quizapp.dto.PostQuestionDto;
import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.service.QuestionService;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
@RequiredArgsConstructor
public class QuestionRestController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<Page<QuestionDto>> getAllQuestion(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        Page<QuestionDto> questions = questionService.getQuestions(PageRequest.of(page, size));
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Long>> addQuestions(@Valid @RequestBody List<PostQuestionDto> postQuestionDtos) {
        List<Long> ids = questionService.addQuestions(postQuestionDtos);
        return new ResponseEntity<>(ids, HttpStatus.CREATED);
    }

    @GetMapping("/level/{questionLevel}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByLevel(@PathVariable Level questionLevel) {
        return new ResponseEntity<>(questionService.getQuestionsByLevel(questionLevel), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByLevel(@PathVariable Category category) {
        return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/random-question")
    public ResponseEntity<QuestionDto> getRandomQuestion() {
        return new ResponseEntity<>(questionService.getRandomQuestion(), HttpStatus.OK);
    }
}
