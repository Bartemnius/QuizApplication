package com.project.quizapp.service;

import com.project.quizapp.entity.Question;
import com.project.quizapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuestionRepository questionRepository;

    public List<Question> getRandomQuestionsForQuiz(int numOfQuestions) {
        //get all ids, then shuffle them and get only required number of Questions
        //This is much faster than getting all questions by for example LIMIT RAND() 20
        List<Long> ids = questionRepository.findAllIds();
        Collections.shuffle(ids);
        ids = ids.subList(0, numOfQuestions);
        List<Question> randomQuestions = questionRepository.findAllById(ids);
        return randomQuestions;
    }

    public int calculateScore(Map<String, String> allParams) {
        int score = 0;
        System.out.println(allParams);
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            if (key.startsWith("answer_")) {
                Long questionId = Long.parseLong(key.split("_")[1]);
                String userAnswer = value;
                Question question = questionRepository.getReferenceById(questionId);
                if (question != null && question.isAnswerCorrect(userAnswer)) {
                    score++;
                }
            }
        }
        return score;
    }
}
