package com.project.quizapp.controller;

import com.project.quizapp.utils.ViewNames;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {
    @GetMapping("/")
    public ModelAndView helloPage() {
        return new ModelAndView(ViewNames.MAIN_VIEW);
    }

    @GetMapping("/randomQuestion")
    public ModelAndView randomQuestion() {
        return new ModelAndView(ViewNames.QUESTION_VIEW);
    }
}
