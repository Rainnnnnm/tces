package com.gcxy.tces.common.easyexcel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Rain
 * @date 2019/9/17
 * 监听器，进行每一行表格解析后的一些后续操作
 */
public class DemoDataListener extends AnalysisEventListener<DemoData> {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoDataListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 2;
    List<DemoData> list = new ArrayList<DemoData>();

    /**
     * 传入一条解析后封装好的数据，进行后续操作
     * @param data 封装好的一行数据
     * @param context 解析对象上下文
     */
    @Override
    public void invoke(DemoData data, AnalysisContext context) {
        ObjectMapper mapper = new ObjectMapper();
        LOGGER.debug("into invoke method");
        System.out.println("into invoke method");
        try {
            LOGGER.debug("解析到一条数据:{}", mapper.writeValueAsString(data));
            System.out.println("解析到一条数据:{} " + mapper.writeValueAsString(data));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        list.add(data);
        //解析了多条数据后进行存储，并清空集合
        if (list.size() >= BATCH_COUNT) {
            saveData();
            list.clear();
        }
    }

    /**
     * 所有解析结束后调用
     * @param context 解析对象
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //解析完成后，如果集合里还有数据则保存
        if(list.size() > 0){
            saveData();
        }
        LOGGER.debug("所有数据解析完成！");
        System.out.println("所有数据解析完成");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.debug("{}条数据，开始存储数据库！", list.size());
        System.out.println(list.size() + "条数据，开始存储数据库");
        LOGGER.debug("存储数据库成功！");
        System.out.println("存储数据库成功");
    }
}
