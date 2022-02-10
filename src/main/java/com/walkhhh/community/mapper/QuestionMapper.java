package com.walkhhh.community.mapper;

import com.walkhhh.community.dto.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-10 9:50
 */
@Mapper
public interface QuestionMapper {

    @Insert("insert into question(title, description, gmt_create, gmt_modified, creator, tag) values(#{title}, #{description}, #{gmtCreate}, #{gmtModified}, #{creator}, #{tag})")
    int create(Question question);
}
