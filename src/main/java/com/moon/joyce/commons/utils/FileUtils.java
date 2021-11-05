package com.moon.joyce.commons.utils;


import com.moon.joyce.example.functionality.entity.PageComponent;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

/**
 * @author Xing Dao Rong
 * @date 2021/9/3 17:53
 * @desc 文件工具类
 */
public class FileUtils implements Serializable {
    private static final long serialVersionUID = 6813374997135795261L;

    /**
     * 文件上传工具类
     *
     * @param file
     * @return
     */
    public static String fileUpLoad(MultipartFile file, String sysPath, String access) {
        if (Objects.isNull(file) || StringUtils.isBlank(sysPath) || StringUtils.isBlank(access)) {
            return null;
        }
        //uuid生成的唯一前缀 + 上传文件名 构成唯一的新文件名
        String fileName = UUIDUtils.getUUID() + "_" + DateUtils.dateForMat("sv", new Date()) + "_" + file.getOriginalFilename();
        //文件保存路径
        String now = DateUtils.dateForMat("dv", new Date());
        String path = sysPath + access + now;
        //新建文件filepath
        File filepath = new File(path, fileName);
        //判断路径是否存在，如果不存在就创建一个
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        try {
            //将上传的文件file写入文件filepath
            file.transferTo(new File(path + File.separator + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(path);
        //将请求文件的相对路径返回
        return "/static" + access + now + "/" + fileName;
    }

    /**
     * xml配置的读取
     * @param filePathName
     * @return
     */
    public static Map<String, List<PageComponent>> readXmlConfig(String filePathName) {
        File file = new File(filePathName);
        if (!file.exists()) {
            return null;
        }
        Map<String, List<PageComponent>> map = new HashMap<>();
        List<PageComponent> pageComponents = new ArrayList<>();
        String id = null;
        try {
            SAXReader saxReader = new SAXReader();
            FileInputStream fis = new FileInputStream(filePathName);
            Document document = saxReader.read(fis);
            Element rootElement = document.getRootElement();
            Iterator<Element> rootIterator = rootElement.elementIterator();
            while (rootIterator.hasNext()) {
                Element setting = rootIterator.next();
                id = setting.attributeValue("id");
                Iterator<Element> settingIterator = setting.elementIterator();
                while (settingIterator.hasNext()) {
                    PageComponent pageComponent = new PageComponent();
                    Element object = settingIterator.next();
                    pageComponent.setName(object.attributeValue("name"));
                    Iterator<Element> objectIterator = object.elementIterator();
                    while (objectIterator.hasNext()) {
                        Element field = objectIterator.next();
                        if (field.attributeValue("name").equals("backgroundUrl")) {
                            pageComponent.setBackgroundUrl(field.attributeValue("value"));
                        }
                        if (field.attributeValue("name").equals("backgroundColor")) {
                            pageComponent.setBackgroundColor(field.attributeValue("value"));
                        }
                        if (field.attributeValue("name").equals("backgroundType")) {
                            pageComponent.setBackgroundType(field.attributeValue("value"));
                        }
                        if (field.attributeValue("name").equals("params")) {
                            Iterator<Element> fieldIterator = field.elementIterator();
                            Map<String, String> fieldMap = new HashMap<>();
                            while (fieldIterator.hasNext()) {
                                Element listField = fieldIterator.next();
                                if (Objects.nonNull(listField)) {
                                    fieldMap.put(listField.attributeValue("name"), listField.attributeValue("value"));
                                }
                            }
                            pageComponent.setParams(fieldMap);
                        }
                    }
                    pageComponents.add(pageComponent);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        map.put(id, pageComponents);
        return map;
    }

    /**
     * xml配置的初始化和更改
     * @param filePathName
     * @return
     */
    public static void writeConfigXml(String filePathName, Map<String, List<PageComponent>> map) {
        Document document = createXmlFactory(map);
        try {
            XMLWriter xmlWriter2 = new XMLWriter();
            xmlWriter2.write(document);
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter xmlwriter = new XMLWriter(new FileOutputStream(filePathName), format);
            xmlwriter.write(document);
            xmlwriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * 创建xml节点工厂
     *
     * @param map
     * @return
     */
    private static Document createXmlFactory(Map<String, List<PageComponent>> map) {
        //创建文档并设置文档的根元素节点
        Element root = DocumentHelper.createElement("settings");
        Document document = DocumentHelper.createDocument(root);
        //根节点
        root.addAttribute("name", "settings");
        //创建子节点
        for (Map.Entry<String, List<PageComponent>> entry : map.entrySet()) {
            Element element = root.addElement("setting");
            if (StringUtils.isNotEmpty(entry.getKey())) {
                element.addAttribute("id", entry.getKey());
            }
            for (int i = 0; i < entry.getValue().size(); i++) {
                Element obj = element.addElement("object");
                obj.addAttribute("name", entry.getValue().get(i).getName());
                Element oElement = obj.addElement("obj_map");
                oElement.addAttribute("name", "params");
                for (Map.Entry<String, String> fEntry : entry.getValue().get(i).getParams().entrySet()) {
                    Element fsElement = oElement.addElement("map_field");
                    if (StringUtils.isNotEmpty(fEntry.getKey())) {
                        fsElement.addAttribute("name", fEntry.getKey());
                    }
                    fsElement.addAttribute("value", fEntry.getValue());
                }

                if (StringUtils.isNotEmpty(entry.getValue().get(i).getBackgroundUrl())) {
                    Element backgroundUrl = obj.addElement("obj_field");
                    backgroundUrl.addAttribute("name", "backgroundUrl");
                    backgroundUrl.addAttribute("value", entry.getValue().get(i).getBackgroundUrl());
                }

                if (StringUtils.isNotEmpty(entry.getValue().get(i).getBackgroundColor())) {
                    Element backgroundUrl = obj.addElement("obj_field");
                    backgroundUrl.addAttribute("name", "backgroundColor");
                    backgroundUrl.addAttribute("value", entry.getValue().get(i).getBackgroundColor());
                }

                if (StringUtils.isNotEmpty(entry.getValue().get(i).getBackgroundType())) {
                    Element backgroundUrl = obj.addElement("obj_field");
                    backgroundUrl.addAttribute("name", "backgroundType");
                    backgroundUrl.addAttribute("value", entry.getValue().get(i).getBackgroundType());
                }
            }
        }
        return document;
    }
}