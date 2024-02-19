package com.project.quizapp.mapper;

import com.project.quizapp.dto.QuestionDto;
import com.project.quizapp.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    static QuestionMapper getInstance() {
        return Mappers.getMapper(QuestionMapper.class);
    }

    QuestionDto toDto(Question questionEntity); //here name has to be with Entity cos otherwise mapstruct is not working properly
    Question toEntity(QuestionDto questionDto);

    List<QuestionDto> toListDto(List<Question> questions);
    List<Question> toListEntity(List<QuestionDto> questionDtoList);
}
