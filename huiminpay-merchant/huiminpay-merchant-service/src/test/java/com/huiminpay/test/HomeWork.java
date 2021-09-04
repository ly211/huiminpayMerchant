package com.huiminpay.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huiminpay.bean.User;
import com.huiminpay.mapper.IUserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HomeWork {

    @Autowired
    IUserMapper userMapper;

    @Test  //查单个
    public void queryById(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test  //查所有
    public void queryUser(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        List<User> users = userMapper.selectList(userQueryWrapper);
        for (User u : users) {
            System.out.println(u);
        }
    }

    @Test //分页查
    public void pageUser(){
        Page<User> userPage = new Page<>(1,2);
        IPage<User> userIPage = userMapper.selectPage(userPage, null);
        List<User> records = userIPage.getRecords();
        for (User user: records) {
            System.out.println(user);
        }
    }
    @Test //模糊查询
    public void query(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.like("name","王");

        List<User> users = userMapper.selectList(userQueryWrapper);
        for (User user : users) {
            System.out.println(user);
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //lambda表达式
        List<User> u = userMapper.selectList(
                new LambdaQueryWrapper<User>().like(User::getName, "王"));
        System.out.println(u);
    }

    @Test  //lambda表达式查询age大于20岁的人
    public void queryAge(){
        List<User> users = userMapper.selectList(
                new LambdaQueryWrapper<User>().gt(User::getAge, 20));

        System.out.println(users);
    }

}
