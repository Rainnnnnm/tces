package com.gcxy.tces.mapper;

import com.gcxy.tces.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/24
 * 院系信息DAO层
 */
@Repository
public interface DepartmentMapper {
    List<Department> selectAllDepts();

    List<Department> selectDeptsByKey(String key);

    Department selectDeptById(String did);

    int insertDept(Department dept);

    int updateDeptById(Department dept);

    int deleteDeptById(String did);
}
