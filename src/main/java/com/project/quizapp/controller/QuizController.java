package com.project.quizapp.controller;

import com.project.quizapp.mapper.QuestionMapper;
import com.project.quizapp.model.Quiz;
import com.project.quizapp.service.QuizService;
import com.project.quizapp.utils.ViewNames;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {
    private final QuizService quizService;
    private Quiz quiz;
    private final QuestionMapper questionMapper = QuestionMapper.getInstance();

    @GetMapping
    public ModelAndView getQuiz(HttpSession session) {
        //check if quiz did not end
        boolean quizEnded = (boolean) session.getAttribute("quizEnded");
//        if (quizEnded) {
//            return new ModelAndView(ViewNames.QUIZ_RESULT_VIEW);
//        }
        //set param quizStarted so user can not go back to it after ending quiz
        if (session.getAttribute("quizStarted") == null) {
            session.setAttribute("quizStarted", true);
        }

        ModelAndView mav = new ModelAndView(ViewNames.QUIZ_VIEW);
        quiz = new Quiz(quizService.getRandomQuestionsForQuiz(20), 0, 20);
        mav.addObject("questionList", questionMapper.toListDto(quiz.getQuestions()));
        mav.addObject("quizEnded", quizEnded);
        //do not cache the answer
        mav.addObject("Cache-Control", "no-cache, no-store, must-revalidate");
        mav.addObject("Pragma", "no-cache");
        mav.addObject("Expires", "0");
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
        session.removeAttribute("quizStarted");
        session.setAttribute("quizEnded", true);
        int score = quizService.calculateScore(allParams);
        ModelAndView mav = new ModelAndView(ViewNames.QUIZ_RESULT_VIEW);
        mav.addObject("score", score);
        return mav;
    }
}
