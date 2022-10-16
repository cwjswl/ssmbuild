import com.xiao.dao.BookMapper;
import com.xiao.pojo.Books;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Mytest {
    @Test
    public void Test(){
       ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        BookMapper book = (BookMapper) context.getBean("queryAllBook");
        List<Books> books = book.queryAllBook();
        for (Books book1 : books) {
            System.out.println(book1);
        }
    }
}
