package com.gcxy.tces.common.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcxy.tces.entity.User;
import com.gcxy.tces.service.StudentService;
import com.gcxy.tces.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rain
 * @date 2019/9/18
 */
public class FileUploadListener extends AnalysisEventListener<User> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadListener.class);
    private UserService service;
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 15;
    List<User> list = new ArrayList<User>();

    public FileUploadListener(){}

    /**
     * 构造方法初始化service
     * @param service service
     */
    public FileUploadListener(UserService service){
        this.service = service;
    }

    /**
     * 传入一条解析后封装好的数据，进行后续操作
     * @param data 封装好的一行数据
     * @param context 解析对象上下文
     *
     */
    @Override
    public void invoke(User data, AnalysisContext context) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            //System.out.println("解析到一条数据: " + mapper.writeValueAsString(data));
            LOGGER.debug("解析到一条数据:{}", mapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        list.add(data);
        //解析了多条数据后进行存储，并清空集合
        if (list.size() >= BATCH_COUNT) {
            saveData();
            //清空集合，回收内存
            list.clear();
        }
    }

    /**
     * 解析完成后的处理方法
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        if(list.size() > 0){
            saveData();
            //清空集合
            list.clear();
        }
        //System.out.println("所有表格数据解析完成");
        LOGGER.debug("所有数据解析完成");
    }

    /**
     * 进行数据持久化操作
     */
    private void saveData(){
        service.saveBatchUser(list);
    }

}
