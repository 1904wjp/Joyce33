package com.moon.joyce.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.moon.joyce.example.entity.SysMenu;
import com.moon.joyce.example.mapper.SysMenuMapper;
import com.moon.joyce.example.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Joyce
 * @since 2021-10-08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper,SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> getMenus(SysMenu menu) {
        QueryWrapper wrapper = new QueryWrapper();
        if (Objects.nonNull(menu)){
            if (StringUtils.isNotEmpty(menu.getMenuName())){
                wrapper.like("menu_name",menu.getMenuName());
            }
            if (StringUtils.isNotEmpty(menu.getMenuUrl())){
                wrapper.like("menu_url",menu.getMenuUrl());
            }
            if (Objects.nonNull(menu.getParentId())){
                wrapper.like("parent_id",menu.getParentId());
            }
           wrapper.orderByAsc("menu_order");
           wrapper.orderByAsc("id");
        }
        return  baseMapper.selectList(wrapper);
    }



    @Override
    public int deleteMenuById(Long id) {
        return baseMapper.deleteById(id);
    }
}
