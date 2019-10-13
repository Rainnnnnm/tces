package maintest;

import com.alibaba.excel.EasyExcel;
import com.gcxy.tces.common.easyexcel.DemoData;
import com.gcxy.tces.common.easyexcel.DemoDataListener;
import com.gcxy.tces.entity.User;
import org.junit.Test;

import java.io.File;

/**
 * @author Rain
 * @date 2019/9/17
 */
public class TestEasyExcel {
    /**
     * 最简单的读
     * <p>1. 创建excel对应的实体对象 参照{@link User}
     * <p>2. 由于默认异步读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>3. 直接读即可
     */
    @Test
    public void simpleRead() {
//        System.out.println(System.getProperty("user.dir"));
//        // 写法1：
//        String fileName = System.getProperty("user.dir") + File.separator + "demo.xlsx";
//        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
//        EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).sheet().doRead();
    }
}
