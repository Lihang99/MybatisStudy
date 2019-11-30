package com.leo.dao;

import com.leo.pojo.User;
import com.leo.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

public  class UserDaoTest{


    @Test
    public void test(){
        //第一步：获得SqlSession对象
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        try {
            sqlSession=MybatisUtils.getSqlSession();
            //执行Sql
            UserMapper userDao = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userDao.getUserList();
            for (User user:userList
            ) {
                System.out.println(user);
            }

        }finally {
            //关闭SqlSession
            sqlSession.close();
        }

    }


    @Test
    public void getUserByid(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserById(1);
        System.out.println(user);

        sqlSession.close();

    }

    //增删改需要提交事务
    @Test
    public void addUser(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        int res = mapper.addUser(new User(4, "sunshurui", "123456666"));
        if (res>0){
            sqlSession.commit();
        }
        sqlSession.close();
    }

    @Test
    public void updateUser(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.updateUser(new User(1,"lihang","367960"));
        if (res>0)
            sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void deleteUserById(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        int res = mapper.deleteUserById(4);
        if (res>0)
            sqlSession.commit();

        sqlSession.close();
    }

    @Test
    public void addUser2(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        HashMap<String, Object> map = new HashMap<>();
        map.put("userid",5);
        map.put("userName","ollllll");
        map.put("password","sadasdasdasd");
        mapper.addUser2(map);
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void getUserLike(){
        SqlSession sqlSession=MybatisUtils.getSqlSession();
        UserMapper mapper=sqlSession.getMapper(UserMapper.class);
        List<User> userList = mapper.getUserLike("li");
        for (User user : userList) {
            System.out.println(user);
        }

        sqlSession.close();

    }
}