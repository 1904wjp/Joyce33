package com.moon.joyce.example.controller;


import com.moon.joyce.commons.contracts.Contract;
import com.moon.joyce.example.entity.User;
import com.moon.joyce.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Joyce
 * @since 2021-09-01
 */
@Controller
@RequestMapping("/example/user")
public class UserController {
    @Autowired
    private UserService userService;
    private String prefix = "user/";
    @GetMapping("/userList")
    public String getUsers(User user, ModelMap map){
        List<User> users = userService.getListUser(user);
        map.addAttribute("users",users);
        return prefix+"userList";
    }
    @RequestMapping("/regist")
    public String registPage(){
        return prefix+"registPage";
    }
    @RequestMapping("/login")
    public String loginPage(){
        return prefix+"loginPage";
    }
    @RequestMapping("/update")
    public String updatePage(){
        return prefix+"updatePage";
    }
    @RequestMapping("/doSaveUser")
    public String registUser(User user){
        boolean result = userService.saveOrUpdate(user);
        if (result){
            return "login";
        }else {
            return "regist";
        }
    }
    @RequestMapping("/login")
    public String loginUser(String username,String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User dbUser = userService.getUser(user, Contract.SELECT_USER_TYPE_LOGIN);
        if (Objects.nonNull(dbUser)){
            return "redirect:listUser";
        }
            return "login";

    }
    @DeleteMapping("/delete")
    public String deleteUserById(Long id,ModelMap map){
        boolean del = userService.removeById(id);
        if (del){
            return "redirect:listUser";
        }
        map.addAttribute("message","操作失败");
        return "listUser";
    }
    @RequestMapping("/update")
    public String updateUser(Long id,ModelMap map){
        User dbUser = userService.getById(id);
        if (Objects.nonNull(dbUser)){
            map.addAttribute("user",dbUser);
            return "updatepage";
        }
        return "redirect:listUser";
    }

}

