package com.gcxy.tces.service.impl;

import com.gcxy.tces.entity.User;
import com.gcxy.tces.mapper.StudentMapper;
import com.gcxy.tces.service.StudentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<User> getAllUsers() {
        return studentMapper.selectAllStudents();
    }

    @Override
    public List<User> getStudentsByKey(String key) {
        return studentMapper.selectStudentsByKey(key);
    }

    @Override
    public User getStudentById(String stuId) {
        return studentMapper.selectStudentById(stuId);
    }

    @Override
    @Transactional()
    public boolean saveStudent(User user) {
        int rs = studentMapper.insertStudent(user);
        logger.debug("######StudentService.saveStudent######");
        logger.debug("save operation result: " + rs);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateStudentById(User user) {
        int rs = studentMapper.updateStudentById(user);
        logger.debug("######StudentService.saveStudent######");
        logger.debug("update operation result: " + rs);
        return true;
    }

    @Override
    public boolean removeStudentById(String stuId) {
        //事务定义
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        //开启事务，获取事务状态
        TransactionStatus status = transactionManager.getTransaction(def);
        //标志
        boolean flag = true;
        try{
            //执行db操作
            int rs = studentMapper.deleteStudentById(stuId);
            int rs1 = studentMapper.deleteStudentById("c");
            logger.debug("######StudentService.saveStudent######");
            logger.debug("remove operation result: " + rs);
            if(rs <= 0 || rs1 <= 0){
                throw new RuntimeException();
            }
        } catch (Exception e) {
            //回滚事务
            transactionManager.rollback(status);
            //抛出当前异常
            //throw e;
            //打印exception信息
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

}
