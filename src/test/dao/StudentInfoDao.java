package test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.pojo.Address;
import test.pojo.Student;

import java.util.Date;

/**
 * Created by wangwei on 17-11-26.
 */
public class StudentInfoDao {
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init(){
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
    }

    @After
    public void destory(){
        transaction.commit();
        session.close();
        factory.close();
    }

    @Test
    public void insertStudent(){
        Address address = new Address("1310","HAIKOU");
        Student student = new Student("wangwei",new Date(),address);
        session.save(student);
    }

    /*
     *get方法和load方法的区别
     * 1.get方法会直接发送sql语句获取实体对象
     * 　load方法会返回代理对象,此对象只有基本的主键值,直到真正使用非主键属性时才查询数据库
     *
     * 2.当数据库中不存在对应的对象时:
     *    get方法返回null
     *    load方法抛出org.hibernate.ObjectNotFoundException异常
     *
     */

    @Test
    public void loadStudent(){
        //将打印语句注释掉可以发现打印台没有打印sql语句
        Student student = session.load(Student.class,2);
        System.out.println(student);
    }

    @Test
    public void getStudent(){
        Student student = session.get(Student.class,2);
        System.out.println(student);
    }
}
