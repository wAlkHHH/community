package com.walkhhh.community.controller;

import com.walkhhh.community.model.Question;
import com.walkhhh.community.model.User;
import com.walkhhh.community.mapper.QuestionMapper;
import com.walkhhh.community.service.QuestionService;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-10 10:00
 */
@Controller
public class PublishController {

    public final int MAX_TITLE_LENGTH = 50;

    @Autowired
    QuestionMapper questionMapper;


    @Autowired
    QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Integer id,
                       Model model){
        Question question = questionMapper.selectByPrimaryKey(id);
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("id", question.getId());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "id", required = false) Integer id,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if(!StringUtils.hasLength(title)){
            model.addAttribute("error", "??????????????????");
            return "publish";
        }

        if(title.length() > MAX_TITLE_LENGTH){
            model.addAttribute("error", "????????????50?????????");
            return "publish";
        }

        if(!StringUtils.hasLength(description)){
            model.addAttribute("error", "????????????????????????");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");

        if( user == null){
            model.addAttribute("error", "???????????????");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        question.setId(id);

        System.out.println(questionMapper.insert(question));
        return "redirect:/";
    }

}
