package com.moon.joyce.example.controller;

import com.moon.joyce.example.functionality.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Xing Dao Rong
 * @date 2021/10/22 13:42
 * @desc 编辑器控制层
 */
@Controller
@RequestMapping("/example/uedit")
public class UEditorController {
    private static String prefix = "uedit/";

    /**
     * 博客编辑主页
     * @return
     */
    @RequestMapping("/index")
    public String getEdit(){
       return prefix+"index";
   }

   @RequestMapping("/toAddArticle")
   public Result addArticle(){
        
       return null;
   }

}
