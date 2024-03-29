package com.project.quizapp.repository;

import com.project.quizapp.entity.Question;
import com.project.quizapp.utils.Category;
import com.project.quizapp.utils.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> getQuestionsByLevel(Level questionLevel);

    List<Question> getQuestionsByCategory(Category category);

    @Query(value = "SELECT * FROM question ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Question getRandomQuestion();

    @Query(value = "SELECT q.id FROM Question q", nativeQuery = true)
    List<Long> findAllIds();
}
