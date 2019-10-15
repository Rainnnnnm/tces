package maintest;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * @author Rain
 * @date 2019/10/14
 */
public class RandomTest {

    @Test
    public void execute(){
        Random r = new Random();
        //生成一个整形随机数
        int i = r.nextInt();
        System.out.println(i);
        //生成一个[0,4)的整形随机数
        int i1 = r.nextInt(4);
        System.out.println(i1);
    }
}
