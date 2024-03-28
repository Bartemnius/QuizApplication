package com.project.quizapp.controller;

import com.project.quizapp.mapper.QuestionMapper;
import com.project.quizapp.model.Quiz;
import com.project.quizapp.service.QuizService;
import com.project.quizapp.utils.ViewNames;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    private final QuestionMapper questionMapper;

    @GetMapping
    public ModelAndView getQuiz(HttpSession session, HttpServletResponse response) {
        log.info("Fetching quiz");
        //on each back reload page so teh user can not change answer after submitting
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        Quiz quiz = new Quiz(quizService.getRandomQuestionsForQuiz(20), 0, 20);
        ModelAndView mav = new ModelAndView(ViewNames.QUIZ_VIEW);
        mav.addObject("questionList", questionMapper.toListDto(quiz.getQuestions()));

        //check if quiz did not end
        boolean quizEnded = (boolean) session.getAttribute("quizEnded");
        //redirect to review view
        if (quizEnded) {
            log.info("Can not fetch quiz, quiz already ended!");
            session.setAttribute("quiz", quiz);
            return new ModelAndView("redirect:/quiz/review");
        }

        return mav;
    }

    @GetMapping("/start-confirmation")
    public ModelAndView getStartQuizConfirmation(HttpSession session) {
        //start new quiz so quizEnded is false
        session.setAttribute("quizEnded", false);
        return new ModelAndView(ViewNames.QUIZ_CONFIRMATION_VIEW);
    }

    @PostMapping("/submitQuiz")
    public ModelAndView submitQuiz(@RequestParam Map<String, String> allParams, HttpSession session) {
        log.info("Quiz submitted");
        session.setAttribute("quizEnded", true);
        int score = quizService.calculateScore(allParams);
        ModelAndView mav = new ModelAndView(ViewNames.QUIZ_RESULT_VIEW);
        mav.addObject("score", score);
        return mav;
    }

    @GetMapping("/review")
    public ModelAndView reviewQuiz(HttpSession session) {
        Quiz quiz = (Quiz) session.getAttribute("quiz");
        ModelAndView mav = new ModelAndView(ViewNames.QUIZ_VIEW);
        mav.addObject("questionList", questionMapper.toListDto(quiz.getQuestions()));
        mav.addObject("quizEnded", true);
        return mav;
    }

}
