package com.project.quizapp.controller;

import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.mapper.QuestionMapper;
import com.project.quizapp.service.QuestionService;
import com.project.quizapp.utils.ViewNames;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    private final ModelAndView questionView = new ModelAndView(ViewNames.RANDOM_QUESTION_VIEW);
    private final QuestionMapper questionMapper;


    @GetMapping("/")
    public ModelAndView helloPage() {
        return new ModelAndView(ViewNames.MAIN_VIEW);
    }

    @GetMapping("/randomQuestion")
    public ModelAndView randomQuestion() {
        QuestionDto questionDto = questionService.getRandomQuestion();
        QuestionDto modifiedQuestionDto = QuestionDto.withModifiedExplanation(questionDto);
        questionView.addObject("question", modifiedQuestionDto);
        return questionView;
    }

    @GetMapping("/question/{questionId}")
    public ModelAndView question(@PathVariable Long questionId) {
        QuestionDto questionDto = questionService.getQuestionById(questionId);
        //this enables the newline sign to be proper handled in view
        QuestionDto modifiedQuestionDto = QuestionDto.withModifiedExplanation(questionDto);
        questionView.addObject("question", modifiedQuestionDto);
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

    @GetMapping("/about")
    public ModelAndView contact() {
        return new ModelAndView(ViewNames.ABOUT_VIEW);
    }

    @GetMapping("/learn/questions")
    public ModelAndView learnQuestions(@RequestParam(value = "page", defaultValue = "0") int page,
                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        Page<QuestionDto> questionPage = questionService.getQuestions(PageRequest.of(page, size));
        //this enables the newline sign to be proper handled in view
        questionPage.forEach(QuestionDto::withModifiedExplanation);
        ModelAndView mav = new ModelAndView(ViewNames.QUESTIONS_VIEW);
        mav.addObject("questionPage", questionPage);
        return mav;
    }
}
