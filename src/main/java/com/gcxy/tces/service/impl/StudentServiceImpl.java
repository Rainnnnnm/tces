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


}
