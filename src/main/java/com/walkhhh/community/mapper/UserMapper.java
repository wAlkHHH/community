package com.walkhhh.community.mapper;

import com.walkhhh.community.dto.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author HUANG BAIRUI
 * @create 2022-02-08 15:08
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user (name, account_id, token, gmt_create, gmt_modified) values (#{name}, #{accountId}, #{token}, #{gmtCreate}, #{gmtModified})")
    void insert(User user);

    @Select(("select * from user where token = #{token}"))
    User findByToken(@Param("token") String token);

    @Select(("select * from user where id = #{id}"))
    User findById(@Param("id") Integer id);
}
