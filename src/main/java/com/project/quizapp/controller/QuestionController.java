package com.project.quizapp.controller;

import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.entity.Question;
import com.project.quizapp.mapper.QuestionMapper;
import com.project.quizapp.service.QuestionService;
import com.project.quizapp.utils.ViewNames;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
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
        QuestionDto questionDto = questionService.getRandomQuestion().getBody();
        questionView.addObject("question", questionDto);
        return questionView;
    }

    @GetMapping("/question/{questionId}")
    public ModelAndView question(@PathVariable Long questionId) {
        QuestionDto questionDto = questionService.getQuestionById(questionId).getBody();
        questionView.addObject("question", questionDto);
        return questionView;
    }

    @PostMapping("/answer")
    public String answer(@RequestParam Long questionId, @RequestParam String ans, RedirectAttributes redirectAttributes) {
        QuestionDto questionDto = questionService.getQuestionById(questionId).getBody();

        // TODO :
        //  Here tha print out should be deleted and some logging maybe should be added

        if (questionMapper.toEntity(questionDto).isAnswerCorrect(ans)) {
            System.out.println("Correct!");
            redirectAttributes.addFlashAttribute("result", "success");
            redirectAttributes.addFlashAttribute("message", "Well done! :)");

        } else {
            System.out.println("Not this time :(");
            redirectAttributes.addFlashAttribute("result", "failure");
            redirectAttributes.addFlashAttribute("message", "Not this time! :(");

        }
        return "redirect:/question/" + questionId;
    }
}
