package com.project.quizapp.controller;

import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.mapper.QuestionMapper;
import com.project.quizapp.service.QuestionService;
import com.project.quizapp.utils.ViewNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@Slf4j
public class QuestionController {

    private final QuestionService questionService;
    private final ModelAndView questionView = new ModelAndView(ViewNames.QUESTION_VIEW);
    private final QuestionMapper questionMapper = QuestionMapper.getInstance();


    @GetMapping("/")
    public ModelAndView helloPage() {
        return new ModelAndView(ViewNames.MAIN_VIEW);
    }

    @GetMapping("/randomQuestion")
    public ModelAndView randomQuestion() {
        QuestionDto questionDto = questionService.getRandomQuestion();
        questionView.addObject("question", questionDto);
        return questionView;
    }

    @GetMapping("/question/{questionId}")
    public ModelAndView question(@PathVariable Long questionId) {
        QuestionDto questionDto = questionService.getQuestionById(questionId);
        questionView.addObject("question", questionDto);
        return questionView;
    }

    @PostMapping("/answer")
    public String answer(@RequestParam Long questionId, @RequestParam String ans, RedirectAttributes redirectAttributes) {
        QuestionDto questionDto = questionService.getQuestionById(questionId);

        if (questionMapper.toEntity(questionDto).isAnswerCorrect(ans)) {
            log.info("Correct!");
            redirectAttributes.addFlashAttribute("result", "success");
        } else {
            log.info("Not this time :(");
            redirectAttributes.addFlashAttribute("result", "failure");
        }
        return "redirect:/question/" + questionId;
    }
}
