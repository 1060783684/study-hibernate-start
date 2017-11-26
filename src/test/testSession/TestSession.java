package test.testSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

/**
 * Created by wangwei on 17-11-26.
 */
public class TestSession {
    //测试两种创建session的方法
    //不同点:
    //1.openSession每次使用创建一个新的session
    //  getCurrentSession每次使用返回的都是同一个session
    //
    // 2.getCurrentSession在事务提交或回滚后会自动关闭，
    //   openSession需要手动关闭(session.close()),否则会造成连接池溢出
    @Test
    public void testOpenSession(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        if(session != null){
            System.out.println("session创建成功");
        }else {
            System.out.println("session创建失败");
        }
    }

    @Test
    public void testGetCurrentSession(){
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        //使用此方法创建session需要配置配置文件
        Session session = factory.getCurrentSession();
        if(session != null){
            System.out.println("session创建成功");
        }else {
            System.out.println("session创建失败");
        }
    }
}
