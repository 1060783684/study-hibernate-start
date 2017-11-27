package test.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import test.pojo.Player;

import java.io.*;
import java.util.Date;

/**
 * Created by root on 17-11-27.
 */
public class PlayerInfoDao {
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
    public void insertPlaryer() throws IOException {
        //将resource中的player1.jpg图片放在对应的路径下
        File file = new File("/data/player1.jpg");
        byte[] picture = new byte[(int) file.length()];
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
        in.read(picture,0,picture.length);

        Player player = new Player(2,"king",new Date(),picture);
        player.setAddress("BEIJING");
        session.save(player);
    }
}
