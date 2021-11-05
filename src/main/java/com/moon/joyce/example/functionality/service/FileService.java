package com.moon.joyce.example.functionality.service;

import com.moon.joyce.example.functionality.entity.PageComponent;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author: Joyce
 * @autograph: Logic is justice
 * @date: 2021/09/23-- 0:15
 * @describe: 文件上传服务接口
 */
public interface FileService {
    /**
     * 图片上传
     * @param file
     * @return
     */
    String uploadImg(MultipartFile file);

    /**
     * 读取Joyce用户设置配置
     * @param path
     * @return
     */
    Map<String, List<PageComponent>> readJoyceConfig(String username);

    /**
     * 设置Joyce用户设置配置
     * @param path
     * @return
     */
    void writeJoyceConfig(String username,Map<String, List<PageComponent>> map);
}