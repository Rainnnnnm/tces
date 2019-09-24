package com.gcxy.tces.service.impl;

import com.gcxy.tces.entity.Department;
import com.gcxy.tces.mapper.DepartmentMapper;
import com.gcxy.tces.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Rain
 * @date 2019/9/24
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<Department> getAllDepts(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return departmentMapper.selectAllDepts();
    }

    @Override
    public List<Department> getDeptsByKey(String key, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return departmentMapper.selectDeptsByKey(key);
    }

    @Override
    public Department getDeptById(String deptId) {
        return departmentMapper.selectDeptById(deptId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveDept(Department dept) {
        int rs = departmentMapper.insertDept(dept);
        return rs > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateDeptById(Department dept) {
        int rs = departmentMapper.updateDeptById(dept);
        return rs > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeDeptById(String deptId) {
        int rs = departmentMapper.deleteDeptById(deptId);
        return rs > 0;
    }
}
