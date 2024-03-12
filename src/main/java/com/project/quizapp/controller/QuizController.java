package com.project.quizapp.controller;

import com.project.quizapp.mapper.QuestionMapper;
import com.project.quizapp.model.Quiz;
import com.project.quizapp.service.QuizService;
import com.project.quizapp.utils.ViewNames;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    private Quiz quiz;
    private final QuestionMapper questionMapper = QuestionMapper.getInstance();

    @GetMapping
    private ModelAndView getQuiz() {
        ModelAndView mav = new ModelAndView(ViewNames.QUIZ_VIEW);
        quiz = new Quiz(quizService.getRandomQuestionsForQuiz(20), 0, 20);
        mav.addObject("questionList", questionMapper.toListDto(quiz.getQuestions()));
        return mav;
    }
}
