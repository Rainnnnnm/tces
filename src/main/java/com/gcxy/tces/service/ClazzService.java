package com.gcxy.tces.service;

import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;
import com.gcxy.tces.entity.CourseTeacherDO;
import com.gcxy.tces.entity.User;

import java.util.List;

public interface ClazzService {
    /**
     * 查询班级信息
     * @param clazzName
     * @return
     */
    List<Clazz> getClazz(String clazzName,int pageNum,int pageSize);

    /**
     * 添加班级
     * @param clazz
     * @return
     */
    boolean addClazz(Clazz clazz);

    /**
     * 根据id删除班级
     * @param clazzId
     * @return
     */
    boolean deleteClazz(String clazzId);

    /**
     * 修改班级
     * @param clazz
     * @return
     */
    boolean updateClazz(Clazz clazz);
/*班级课程管理*/

    /**
     * 查询班级的课程
     * @param clazzId
     * @return
     */
    List<Course> selectCourseOfClazz(String clazzId);

    /**
     * 为班级添加课程
     * @param clazzId
     * @param courseId
     * @return
     */
    boolean addCourseOfClazz(String clazzId,String courseId,String userId);

    /**
     * 删除班级课程
     * @param clazzId
     * @param courseId
     * @return
     */
    boolean deleteClazzCourse(String clazzId,String courseId,String userId);

    /**
     * 查询与教师关联的课程
     * @return
     */
    List<CourseTeacherDO> selectCourse();

    /**
     * 查询无班级的学生
     * @return
     */
    List<User> selectFreeStudent();

    /**
     * 添加学生
     * @param userId
     * @param clazzId
     * @return
     */
    boolean addStudentToCLass(String userId,String clazzId);

    /**
     * 查询班级的学生
     * @param clazzId
     * @return
     */
    List<CourseTeacherDO> selectStudentByCid(String clazzId);

    /**
     * 查询班级的课程
     * @param clazzId
     * @return
     */
    List<CourseTeacherDO> selectCourseByCid(String clazzId);

    /**
     * 查询班级的名字
     * @param clazzId
     * @return
     */
    String getClazzName(String clazzId);

    /**
     * 删除班级的学生
     * @param userId
     * @param clazzId
     * @return
     */
    boolean deleteStudent(String userId,String clazzId);
}

