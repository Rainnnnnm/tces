package com.gcxy.tces.service.impl;

import com.gcxy.tces.entity.Type;
import com.gcxy.tces.mapper.TypeMapper;
import com.gcxy.tces.service.TypeService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rain
 * @date 2019/10/12
 */
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getAllType(String pageNum, String pageSize) {
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        return typeMapper.selectAllType();
    }

    @Override
    public boolean saveType(Type type) {
        return typeMapper.insertType(type) > 0;
    }

    @Override
    public boolean updateType(Type type) {
        return typeMapper.updateType(type) > 0;
    }

    @Override
    public boolean removeTypeById(String typeId) {
        return typeMapper.deleteTypeById(typeId) > 0;
    }

    @Override
    public List<Type> getTypeByKey(String key, String pageNum, String pageSize) {
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        return typeMapper.selectTypeByKey(key);
    }
}
