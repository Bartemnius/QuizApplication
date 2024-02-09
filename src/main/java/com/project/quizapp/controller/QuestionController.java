package com.project.quizapp.controller;

import com.project.quizapp.entity.Question;
import com.project.quizapp.service.QuestionService;
import com.project.quizapp.utils.ViewNames;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/")
    public ModelAndView helloPage() {
        return new ModelAndView(ViewNames.MAIN_VIEW);
    }

    @GetMapping("/randomQuestion")
    public ModelAndView randomQuestion() {
        ModelAndView modelAndView = new ModelAndView(ViewNames.QUESTION_VIEW);
        Question question = questionService.getRandomQuestion().getBody();
        modelAndView.addObject("question", question);
        return modelAndView;
    }

    @GetMapping("/question")
    public ModelAndView question(@RequestParam Long questionId) {
        ModelAndView modelAndView = new ModelAndView(ViewNames.QUESTION_VIEW);
        Question question = questionService.getQuestionById(questionId).getBody();
        modelAndView.addObject("question", question);
        return modelAndView;
    }

    @PostMapping("/answer")
    public RedirectView answer(@RequestParam Long questionId, @RequestParam String ans) {
        Question question = questionService.getQuestionById(questionId).getBody();
        if (question.isAnswerCorrect(ans)) {
            System.out.println("Correct!");
            return new RedirectView("/randomQuestion");
        }

        System.out.println("Not this time :(");
        return new RedirectView("/question?questionId=" + questionId);
    }
}
