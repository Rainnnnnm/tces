package maintest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcxy.tces.entity.Clazz;
import com.gcxy.tces.entity.Course;
import com.gcxy.tces.service.StudentService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * @author Rain
 * @date 2019/10/14
 */


public class StudentTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentTest.class);

    @Test
    public void execute() throws JsonProcessingException {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        StudentService studentService = (StudentService)context.getBean("studentServiceImpl");
        List<Course> courses = studentService.getCourseTeacherInClazz("1");
        ObjectMapper mapper = new ObjectMapper();
        //把对象转化为json字符串
        String json = mapper.writeValueAsString(courses);
        LOGGER.error("courses object: {}", json);
    }
}
