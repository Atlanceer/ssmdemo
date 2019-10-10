package atlan.ceer.test;

import atlan.ceer.mapper.BookMapper;
import atlan.ceer.model.Book;
import atlan.ceer.model.BookExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器 spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件
@ContextConfiguration({ "classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class BookDaoTest {
    @Autowired
    private BookMapper bookMapper;
    @Test
    public void testQueryById() throws Exception {
        long bookId = 1000;
        Book book = bookMapper.selectByPrimaryKey(1000l);
        System.out.println(book.getBookId());
    }
    @Test
    public void countTest(){
        BookExample ex=new BookExample();
        BookExample.Criteria criteria=ex.createCriteria();
        criteria.andBookIdEqualTo(1000l);
        int count= (int) bookMapper.countByExample(ex);
        System.out.println(count);
    }
    @Test
    public void testCri(){
        BookExample ex=new BookExample();
        BookExample.Criteria criteria=ex.createCriteria();
        criteria.andBookIdEqualTo(1000l);
        int count= (int) bookMapper.countByExample(ex);
        BookExample ex2=new BookExample();
        BookExample.Criteria criteria2=ex2.createCriteria();
        criteria2.andNumberEqualTo(10);
        int count2 = (int) bookMapper.countByExample(ex2);
        System.out.println(count+"  ======="+count2);
    }
}

