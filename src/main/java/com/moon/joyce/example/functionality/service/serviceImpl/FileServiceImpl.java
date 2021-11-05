package com.moon.joyce.example.functionality.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.moon.joyce.commons.constants.Constant;
import com.moon.joyce.commons.utils.DateUtils;
import com.moon.joyce.commons.utils.FileUtils;
import com.moon.joyce.example.entity.SysMenu;
import com.moon.joyce.example.functionality.entity.PageComponent;
import com.moon.joyce.example.functionality.service.FileService;
import com.moon.joyce.example.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * @author: Joyce
 * @autograph: Logic is justice
 * @date: 2021/09/23-- 1:14
 * @describe: 上传文件实现类
 */
@Service
public class FileServiceImpl implements FileService {
    @Value("${file.upload.path}")
    private String sysPath;
/*    @Value("${file.upload.relative}")
    private String relationImg;*/
    @Value("${file.upload.access}")
    private String access;
    @Value("${file.config.path}")
    private String confPath;
    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Override
    public String uploadImg(MultipartFile file) {
       return   FileUtils.fileUpLoad(file,sysPath,access);
    }

    @Override
    public Map <String, List<PageComponent>> readJoyceConfig(String username) {
        String filePathName = confPath + username + "_config.xml";
        Map<String, List<PageComponent>> map = FileUtils.readXmlConfig(filePathName);
        return map;

    }


    @Override
    public void writeJoyceConfig(String username, Map<String, List<PageComponent>> map) {
        //文件全路径
        String filePathName = confPath + username + "_config.xml";
        File file = new File(filePathName);
        if (!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        //初始化配置文件
        if (null == map) {
            Map<String, List<PageComponent>> initMap = new HashMap<>();
            QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
            wrapper.isNotNull("menu_url");
            List<SysMenu> sysMenus = sysMenuMapper.selectList(wrapper);
            System.out.println(sysMenus.toString());
            ArrayList<PageComponent> pageComponents = new ArrayList<>();
            for (SysMenu sysMenu : sysMenus) {
                Map<String, String> params = new HashMap<>();
                params.put(Constant.FONTSIZE_DEFAULT_NAME, Constant.FONTSIZE_DEFAULT_SIZE);
                params.put(Constant.FILE_DEFAULT_SET_NAME, Constant.FILE_DEFAULT_URL);
                PageComponent pageComponent =
                        PageComponent.builder()
                        .name(sysMenu.getMenuName())
                        .params(params)
                        .backgroundType(Constant.BACKGROUND_DEFAULT_TYPE)
                        .backgroundColor(Constant.BACKGROUND_DEFAULT_COLOR)
                        .backgroundUrl(Constant.BACKGROUND_DEFAULT_URL)
                        .build();
                pageComponents.add(pageComponent);
            }
            initMap.put("init_page_config", pageComponents);
            FileUtils.writeConfigXml(filePathName,initMap);
        }else {
            FileUtils.writeConfigXml(filePathName,map);
        }
    }
}
