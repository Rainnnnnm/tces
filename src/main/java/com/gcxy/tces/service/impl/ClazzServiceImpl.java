package com.gcxy.tces.service.impl;

import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;
import com.gcxy.tces.entity.CourseTeacherDO;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.mapper.ClazzMapper;
import com.gcxy.tces.service.ClazzService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public List<Clazz> getClazz(String clazzName,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);//分页
         return clazzMapper.selectClazz(clazzName);
    }

    @Override
    public boolean addClazz(Clazz clazz) {
        return clazzMapper.addClazz(clazz) > 0;
    }

    @Override
    public boolean deleteClazz(String clazzId) {
        return clazzMapper.deleteClazz(clazzId) > 0;
    }

    @Override
    public boolean updateClazz(Clazz clazz) {
        return clazzMapper.updateClazz(clazz) > 0;
    }


    /*班级课程管理*/
    @Override
    public List<Course> selectCourseOfClazz(String clazzId) {
        return clazzMapper.selectCourseOfClazz(clazzId);
    }

    @Override
    public boolean addCourseOfClazz(String clazzId, String courseId,String userId) {
        return clazzMapper.addCourseOfClazz(clazzId,courseId,userId) > 0;
    }

    @Override
    public boolean deleteClazzCourse(String clazzId, String courseId,String userId) {
        return clazzMapper.deleteCourseOfClazz(clazzId,courseId,userId) > 0;
    }

    @Override
    public List<CourseTeacherDO> selectCourse() {
        return clazzMapper.selectCourse();
    }

    @Override
    public List<User> selectFreeStudent() {
        return clazzMapper.selectFreeStudent();
    }

    @Override
    public boolean addStudentToCLass(String userId, String clazzId) {
        return clazzMapper.addStudentToCLass(userId,clazzId) > 0;
    }

    @Override
    public List<CourseTeacherDO> selectStudentByCid(String clazzId) {
        return clazzMapper.selectStudentByCid(clazzId);
    }

    @Override
    public List<CourseTeacherDO> selectCourseByCid(String clazzId) {
        return clazzMapper.selectCourseByCid(clazzId);
    }

    @Override
    public String getClazzName(String clazzId) {
        return clazzMapper.selectClazzName(clazzId);
    }

    @Override
    public boolean deleteStudent(String userId, String clazzId) {
        return clazzMapper.deleteStudentByCid(userId,clazzId) > 0;
    }
}
