package com.walkhhh.community.service;

import com.walkhhh.community.dto.PaginationDTO;
import com.walkhhh.community.dto.Question;
import com.walkhhh.community.dto.QuestionDTO;
import com.walkhhh.community.dto.User;
import com.walkhhh.community.mapper.QuestionMapper;
import com.walkhhh.community.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-10 22:45
 */
@Component
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size){
        Integer totalCount = questionMapper.count();
        Integer totalPage;
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size + 1;
        }

        //判断页数是否合法
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(page, size);
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.list(offset, size);

        for (Question question : questions) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }
}
