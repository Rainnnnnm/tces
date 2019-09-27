package com.gcxy.tces.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.mapper.StudentMapper;
import com.gcxy.tces.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import java.util.List;

/**
 * @author Rain
 * @date 2019/9/16
 */
@Service
public class StudentServiceImpl implements StudentService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageInfo<User> getAllUsers(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //返回的list是Page类型
        List<User> users = studentMapper.selectAllStudents();
        return new PageInfo<>(users);
    }

    @Override
    public List<User> getStudentsByKey(String key, String userType, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return studentMapper.selectStudentsByKey(key, userType);
    }

    @Override
    public User getStudentById(String stuId) {
        return studentMapper.selectStudentById(stuId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveStudent(User user) {
        //设置初始密码,使用md5加密
        String md5 = SecureUtil.md5("123456");
        //使用两次md5加密
        user.setUserPass(SecureUtil.md5(md5));
        LOGGER.debug("double encode by md5: {}", md5);
        //插入学生
        int rs = studentMapper.insertStudent(user);
        //根据班级名查询班级id
        //String clazzId = studentMapper.selectClazzIdByName(user.getClazz()).getClazzId();
        //插入学生与班级关联信息
        //studentMapper.insertUserClazz(user.getUserId(), clazzId);
        LOGGER.debug("######StudentService.saveStudent######");
        LOGGER.debug("save operation result: " + rs);
        return rs > 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateStudentById(User user) {
        int rs = studentMapper.updateStudentById(user);
        LOGGER.debug("######StudentService.saveStudent######");
        LOGGER.debug("update operation result: " + rs);
        return true;
    }

//    @Override
//    public boolean removeStudentById(String stuId) {
//        //事务定义
//        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
//        //开启事务，获取事务状态
//        TransactionStatus status = transactionManager.getTransaction(def);
//        //标志
//        boolean flag = true;
//        try{
//            //执行db操作
//            int rs = studentMapper.deleteStudentById(stuId);
//            LOGGER.debug("######StudentService.saveStudent######");
//            LOGGER.debug("remove operation result: " + rs);
//            transactionManager.commit(status);
//        } catch (Exception e) {
//            //回滚事务
//            transactionManager.rollback(status);
//            //抛出当前异常
//            //throw e;
//            //打印exception信息
//            e.printStackTrace();
//            flag = false;
//        }
//        return flag;
//    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeStudentById(String stuId) {
        //执行db操作
        int rs = studentMapper.deleteStudentById(stuId);
        LOGGER.debug("######StudentService.saveStudent######");
        LOGGER.debug("remove operation result: " + rs);
        return rs > 0;
    }

    /**
     * excel批量导入
     * @param dataList 解析生成的数据列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBatchStudent(List<User> dataList) {
        for(User data : dataList){
            //处理用户信息
            processUserInfo(data);
            //插入数据库, 会设置自增主键到data对应的属性中
            studentMapper.insertStudent(data);

            //插入学生班级关联关系
            //String userId = studentMapper.selectUserIdByCode(data.getUserCode()).getUserId();
            String clazzId = studentMapper.selectClazzIdByName(data.getClazz()).getClazzId();
            studentMapper.insertUserClazz(data.getUserId(), clazzId);

        }
    }

    /**
     * 处理用户信息，转化成数据库定义格式
     * @param data User
     */
    private void processUserInfo(User data){
        /*
          处理用户性别
         */
        if(data.getUserSex().equals("男")){
            data.setUserSex("1");
        } else if(data.getUserSex().equals("女")){
            data.setUserSex("0");
        } else {
            throw new RuntimeException("性别数据异常");
        }

        /*
          处理用户类型
         */
        if(data.getUserType().equals("学生")){
            data.setUserType("0");
        } else if(data.getUserType().equals("教师")){
            data.setUserType("1");
        } else if(data.getUserType().equals("管理员")){
            data.setUserType("2");
        } else {
            throw new RuntimeException("用户类别数据异常");
        }

        //设置初始密码,使用md5加密
        String md5 = SecureUtil.md5("123456");
        //使用两次md5加密
        data.setUserPass(SecureUtil.md5(md5));
        LOGGER.debug("double encode by md5: {}", md5);

        //处理学号
        String subCode = data.getUserCode().substring(0, data.getUserCode().length() - 2);
        LOGGER.debug("subCode: {}", subCode);
        data.setUserCode(subCode);

    }

}
