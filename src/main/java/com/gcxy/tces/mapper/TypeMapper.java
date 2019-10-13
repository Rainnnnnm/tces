package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.Type;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rain
 * @date 2019/10/12
 */
@Repository
public interface TypeMapper {
    List<Type> selectAllType();

    int insertType(Type type);

    int updateType(Type type);

    int deleteTypeById(String typeId);

    List<Type> selectTypeByKey(String key);
}
