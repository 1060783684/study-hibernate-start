package test.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import test.pojo.User;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 17-11-23.
 */
public class UserInfoDao {
    private static SessionFactory sessionFactory;

    static{
        /*这里的Configuration().configure()法有两个重载方法，可以传入文件对象作为参数，也可以传入文件路径作为参数,文件为hibernate的配置文件*/
        System.out.println("Hibernate init start...");
        sessionFactory = new Configuration().configure().buildSessionFactory();
        System.out.println("Hibernate init end!");
    }

    public static void close(){
        /*关闭sessionFactory,这个方法调用的同时，也会关闭对应的线程*/
        sessionFactory.close();
    }

    /*插入一条user_info记录*/
    public static Integer insertUserInfo(User user){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer userId = null;
        try {
            transaction = session.beginTransaction();
            /*将对象转换成对应的表数据并插入*/
            userId = (Integer) session.save(user);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }

        return userId;
    }

    /*查询user_info的所有的数据*/
    public static List<User> selectAllUser(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<User> users = null;
        try{
            transaction = session.beginTransaction();

            //注意:这里的from语句后面的参数是对应的java类，不是表名
            Query query = session.createQuery("from User");
            users = query.list();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return users;
    }

    /*查询对应id的user对象*/
    public static User selectUserInfoById(int userId){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        User user = null;
        try{
            transaction = session.beginTransaction();
            //这里的Class参数是用来需找对应的表的
            user = session.get(User.class,userId);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }

    /*修改对应id的行的数据*/
    public static void updateUserInfo(int userId,String name, Date birthday){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            User user = session.get(User.class,userId);

            if(name != null)
                user.setName(name);
            if(birthday != null)
                user.setBirthday(birthday);

            /*将修改后的对象数据更新到表中*/
            session.update(user);
            transaction.commit();
        }catch(HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    /*删除对应id的行的数据*/
    public static void deleteUserInfo(int userId){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            User user = session.get(User.class,userId);
            session.delete(user);
            transaction.commit();
        }catch(HibernateException e){
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
    }
}
