package com.imooc.mybatis.dao;

import com.imooc.mybatis.bean.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface PersonMapper {

    void deletePerson(Integer id);

    List<Person> selectByNameAndGender(String username, String gender);

    List<Person> selectByNameAndGender2(Map<String,Object> param);
}
