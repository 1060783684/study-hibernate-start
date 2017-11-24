package test;

import test.dao.UserInfoDao;
import test.pojo.User;

import java.util.Date;
import java.util.List;

/**
 * Created by root on 17-11-24.
 */
public class TestDao {
    public static void main(String[] args){
        try {
            /*
             * 这里要注意Date的构造方法里的year参数，这个参数是以1900开始加上传入的参数值得到的时间，所以若想得到正确的year需要用想要的值减去1900
             * mouth是从1开始的所以mouth要减去1
             * 例:得到时间2017-10-29
             * Date date = new Date(2017-1900,10-1,29)
             **/
            User user01 = new User("wangwei", new Date(2017 - 1900, 10 - 1, 29), "I'm wangwei");
            //这个赋值语句对插入的数据没有影响，hibernate依然使用自己生成的自增id
            //user01.setId(100);

            /*insert into*/
            Integer i = UserInfoDao.insertUserInfo(user01);
            System.out.println("user " + user01.getName() + " id is :" + i);

            /*select by id*/
            User user1 = UserInfoDao.selectUserInfoById(i);
            System.out.println(user1);

            /*select all*/
            List<User> list = UserInfoDao.selectAllUser();
            list.forEach(x->System.out.println(x));

            /*update by id*/
            UserInfoDao.updateUserInfo(i, "dangbenben", new Date(1996 - 1900, 5 - 1, 26));
            User user2 = UserInfoDao.selectUserInfoById(i);
            System.out.println(user2);

            /*delete by id*/
            UserInfoDao.deleteUserInfo(i);
            User user3 = UserInfoDao.selectUserInfoById(i);
            System.out.println(user3);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            UserInfoDao.close();
        }

    }
}
