package com.moon.joyce;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.moon.joyce.example.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

@SpringBootTest
class JoyceApplicationTests {
    @Test
    void contextLoads() {

    }

   /* @Test
    public void test01(){
        User user1 = new User();
        user1.setUsername("dsadsa");
        List<User> listUser = userService.getListUser(user1);
        for (User user : listUser) {
            System.out.println(user.getUsername());
        }

    }*/
}
