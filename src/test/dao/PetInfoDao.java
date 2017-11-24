package test.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import test.pojo.Pet;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 17-11-24.
 */
public class PetInfoDao {
    private static SessionFactory sessionFactory;

    static{
        System.out.println("Hibernate init start...");
        sessionFactory = new Configuration().configure().buildSessionFactory();
        System.out.println("Hibernate init end!");
    }

    public static void close(){
        sessionFactory.close();
    }

    public static Integer insertPetInfo(Pet pet){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Integer petId = null;
        try {
            transaction = session.beginTransaction();
            /*将对象转换成对应的表数据并插入*/
            petId = (Integer) session.save(pet);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }

        return petId;
    }

    /*查询user_info的所有的数据*/
    public static List<Pet> selectAllPet(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        List<Pet> pets = null;
        try{
            transaction = session.beginTransaction();

            //注意:这里的from语句后面的参数是对应的java类，不是表名
            Query query = session.createQuery("from Pet");
            pets = query.list();
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
        return pets;
    }

    /*查询对应id的user对象*/
    public static Pet selectPetInfoById(int petId){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Pet pet = null;
        try{
            transaction = session.beginTransaction();
            //这里的Class参数是用来需找对应的表的
            pet = session.get(Pet.class,petId);
            transaction.commit();
        }catch (HibernateException e){
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return pet;
    }

    /*修改对应id的行的数据*/
    public static void updatePetInfo(int petId,String name, Date birth){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Pet pet = session.get(Pet.class,petId);

            if(name != null)
                pet.setName(name);
            if(birth != null)
                pet.setBrith(birth);

            /*将修改后的对象数据更新到表中*/
            session.update(pet);
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
    public static void deletePetInfo(int petId){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Pet pet = session.get(Pet.class,petId);
            session.delete(pet);
            transaction.commit();
        }catch(HibernateException e){
            if(transaction != null)
                transaction.rollback();
        }finally {
            session.close();
        }
    }
}
