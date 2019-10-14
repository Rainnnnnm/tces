package com.gcxy.tces.service;

import com.gcxy.tces.entity.Type;

import java.util.List;

/**
 * @author Rain
 * @date 2019/10/12
 */
public interface TypeService {
    List<Type> getAllType(String pageNum, String pageSize);

    boolean saveType(Type type);

    boolean updateType(Type type);

    boolean removeTypeById(String typeId);

    List<Type> getTypeByKey(String key, String pageNum, String pageSize);
}
