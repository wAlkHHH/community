package com.walkhhh.community.controller;

import com.walkhhh.community.dto.Question;
import com.walkhhh.community.dto.User;
import com.walkhhh.community.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if(!StringUtils.hasLength(title)){
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        if(title.length() > MAX_TITLE_LENGTH){
            model.addAttribute("error", "标题最多50个字符");
            return "publish";
        }

        if(!StringUtils.hasLength(description)){
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");

        if( user == null){
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        System.out.println(questionMapper.create(question));
        return "redirect:/";
    }

}
