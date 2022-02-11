package com.walkhhh.community.controller;

import com.walkhhh.community.dto.QuestionDTO;
import com.walkhhh.community.mapper.QuestionMapper;
import com.walkhhh.community.model.Question;
import com.walkhhh.community.model.QuestionExample;
import com.walkhhh.community.service.QuestionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-11 22:16
 */
@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") String id,
                           Model model){
        Integer questionId = Integer.parseInt(id);
        QuestionExample questionExample = new QuestionExample();
        QuestionDTO questionDTO = questionService.getById(questionId);

        model.addAttribute("question", questionDTO);

        return "question";


    }
}
