package test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 17-11-24.
 *
 *这个程序采用junit测试工具主要依靠注解
 * ＠Test:注释在方法上,被注释的方法相当于主方法可运行,被注释的方法返回值必须为void类型
 * @Befor:注释在方法上,表示在@Test方法启动前的初始化方法
 * @After:注释在方法上,表示在@Test方法结束后的释放资源方法
 */
public class TestHQL {
    private static SessionFactory sessionFactory;

    @Before
    public void sessionFactoryInit(){
        System.out.println("Hibernate init start...");
        sessionFactory = new Configuration().configure().buildSessionFactory();
        System.out.println("Hibernate init end!");
    }

    @After
    public void sessionFactoryDestory(){
        System.out.println("Hibernate destory start...");
        sessionFactory.close();
        System.out.println("Hibernate destory end!");
    }
    @Test
    //查询一整个对象
    public void testFromAs(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "from User As U";
            Query query = session.createQuery(hql);
            List results = query.list();
            transaction.commit();

            results.forEach(x->System.out.println(x));
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Test
    //只查询某一个字段,查询单个字段返回的是字段对应的类型值
    public void testSelect(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            //AS可以省略
            String hql = "select u.name from User u";
            Query query = session.createQuery(hql);
            List list = query.list();
            transaction.commit();

            list.forEach(x->System.out.println(x.getClass()+ ":" +x));

        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Test
    //测试where子句，查询name为wangwei的对象
    public void testWhere(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            //AS可以省略
            String hql = "from User u where name = \'wangwei\'";
            Query query = session.createQuery(hql);
            List list = query.list();
            transaction.commit();

            list.forEach(x->System.out.println(x));

        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Test
    //排序
    public void testOrderBy(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            //多级排序可以用逗号分隔
            String hql = "from User u where id > 10"+
                         "order by id desc , name asc";
            Query query = session.createQuery(hql);
            List list = query.list();
            transaction.commit();

            list.forEach(x->System.out.println(x));

        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Test
    //测试group by
    public void testGroupBy(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            String hql = "select max(u.birthday) from User u " +
                         "group by u.name";
            Query query = session.createQuery(hql);
            List list = query.list();
            transaction.commit();

            list.forEach(x->System.out.println(x));

        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Test
    public void testParamName(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            //功能:灵活,可以防止SQL注入
            //命名格式:属性名 = :自己命名的字符串
            // 注意:冒号和自己命名的字符串之间不能有空格,他们是一个整体
            String hql = "select u.birthday from User u where u.id = :uid";
            Query query = session.createQuery(hql);
            //设置自己命名的字符串对应的值
            query.setParameter("uid",10);
            List list = query.list();
            transaction.commit();

            list.forEach(x->System.out.println(x.getClass()+ ":" +x));

        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Test
    public void testUpdate(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            String hql = "update User set birthday = :birthday " +
                         "where id = :id";
            Query query = session.createQuery(hql);
            query.setParameter("birthday",new Date(2001 - 1900,5 - 1,10));
            query.setParameter("id",10);

            int i = query.executeUpdate();
            transaction.commit();

            System.out.println("success number "+i);
            this.testParamName();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Test
    public void testDistinct(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            String hql = "select count(distinct u.name) from User u";
            Query query = session.createQuery(hql);
            List list = query.getResultList();
            transaction.commit();

            list.forEach(x->System.out.println(x));
        }catch(HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Test
    //测试分页操作
    public void testLimit(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            String hql = "select u.name from User u";
            Query query = session.createQuery(hql);
            //从第几行开始,相当于limit的第一个参数
            query.setFirstResult(1);
            //总共几行,相当于limit的第二个参数
            query.setMaxResults(3);
            List list = query.getResultList();
            transaction.commit();

            list.forEach(x->System.out.println(x));
        }catch(HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    @Test
    //辅助用,使用程序修改自动提交并提交
    public  void insertUserInfo(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer userId = null;
        try {
            /*开启一个事务*/
            transaction = session.beginTransaction();
            session.doWork(new Work(){

                @Override
                public void execute(Connection connection) throws SQLException {
                    //这里的connection为jdbc中的数据库连接对象
                    //将自动提交设置为true
                    connection.setAutoCommit(true);
                }
            });
            User user = new User("dangbenben",new Date(1996-1900,5-1,26),"test");
            userId = (Integer) session.save(user);
            session.flush();
            System.out.println("insert into end!");
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }
}
