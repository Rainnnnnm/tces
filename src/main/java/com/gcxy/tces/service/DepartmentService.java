package com.gcxy.tces.service;

import com.gcxy.tces.entity.Department;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/24
 */
public interface DepartmentService {
    /**
     * 获取所有的用户分页查询
     */
    List<Department> getAllDepts(int pageNum, int pageSize);

    List<Department> getDeptsByKey(String key, int pageNum, int pageSize);

    Department getDeptById(String deptId);

    boolean saveDept(Department dept);

    boolean updateDeptById(Department dept);
    boolean removeDeptById(String deptId);
}
