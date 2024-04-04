package com.project.quizapp.controller;

import com.project.quizapp.dto.PostQuestionDto;
import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.service.QuestionService;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionRestController {

    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<Page<QuestionDto>> getAllQuestions(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        log.info("Fetching question of page {} with page size {}", page, size);
        Page<QuestionDto> questions = questionService.getQuestions(PageRequest.of(page, size));
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<Long>> addQuestions(@Valid @RequestBody List<PostQuestionDto> postQuestionDtos) {
        log.info("Adding questions to DB");
        List<Long> ids = questionService.addQuestions(postQuestionDtos);
        return new ResponseEntity<>(ids, HttpStatus.CREATED);
    }

    @GetMapping("/level/{questionLevel}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByLevel(@PathVariable Level questionLevel) {
        log.info("Fetching question with {} level", questionLevel);
        return new ResponseEntity<>(questionService.getQuestionsByLevel(questionLevel), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<QuestionDto>> getQuestionsByCategory(@PathVariable Category category) {
        log.info("Fetching question with {} category", category);
        return new ResponseEntity<>(questionService.getQuestionsByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/random-question")
    public ResponseEntity<QuestionDto> getRandomQuestion() {
        log.info("Fetching random question");
        return new ResponseEntity<>(questionService.getRandomQuestion(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long id) {
        return new ResponseEntity<>(questionService.getQuestionById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        log.info("Deleting question with id: {}", id);
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
