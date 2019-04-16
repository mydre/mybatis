package com.imooc.mybatis.dao;

import com.imooc.mybatis.bean.Person;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface PersonMapper {

    void deletePerson(Integer id);

    List<Person> selectByNameAndGender(String username, String gender);

    List<Person> selectByNameAndGender2(Map<String,Object> param);

    List<Person> selectByNameAndGender3(@Param( "p1" ) String name, @Param( "p2" ) String gender);

    List<Person> selectByCollection(Collection list);

    List<Person> selectByArray(Integer[] integers);

    List<Person> selectByIds(Integer[] integers);
}
