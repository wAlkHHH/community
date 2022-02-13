package com.walkhhh.community.service;

import com.walkhhh.community.dto.PaginationDTO;
import com.walkhhh.community.dto.QuestionDTO;
import com.walkhhh.community.model.User;
import com.walkhhh.community.mapper.QuestionMapper;
import com.walkhhh.community.mapper.UserMapper;
import com.walkhhh.community.model.Question;
import com.walkhhh.community.model.QuestionExample;
import com.walkhhh.community.model.UserExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
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

    public PaginationDTO list(Integer userId, Integer page, Integer size){

        QuestionExample questionExample = new QuestionExample();
        long totalCount = questionMapper.countByExample(questionExample);

        Integer totalPage;
        if(totalCount % size == 0){
            totalPage = (int) totalCount / size;
        }else{
            totalPage = (int) totalCount / size + 1;
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
        RowBounds rowBounds = new RowBounds(offset, size);
        questionExample.createCriteria().andCreatorEqualTo(userId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, rowBounds);

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    public PaginationDTO list(Integer page, Integer size){
        QuestionExample questionExample = new QuestionExample();
        long totalCount = questionMapper.countByExample(questionExample);

        int totalPage;
        if(totalCount % size == 0){
            totalPage = (int) totalCount / size;
        }else{
            totalPage = (int) totalCount / size + 1;
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
        RowBounds rowBounds = new RowBounds(offset, size);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, rowBounds);

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOS.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOS);
        return paginationDTO;
    }

    public QuestionDTO getById(Integer id){
        Question question = questionMapper.selectByPrimaryKey(id);
//        if (question == null){
//
//        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.selectByPrimaryKey(questionDTO.getCreator());
        questionDTO.setUser(user);
        return questionDTO;

    }

    public void createOrUpdate(Question question){
        if (question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCommentCount(0);
            question.setViewCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        }else {
            Question dbQuestion = questionMapper.selectByPrimaryKey(question.getId());
            //更新
//            if (dbQuestion == null){
//
//            }

//            if (dbQuestion.getCreator().intValue() != question.getCreator().intValue()){
//
//            }
            Question newQuestion = new Question();
            newQuestion.setGmtModified(System.currentTimeMillis());
            newQuestion.setTitle(dbQuestion.getTitle());
            newQuestion.setDescription(dbQuestion.getDescription());
            newQuestion.setTag(dbQuestion.getTag());
            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria().andIdEqualTo(dbQuestion.getId());
            int update = questionMapper.updateByExample(newQuestion, questionExample);
//            if (update != 1){
//
//            }

        }
    }
}
