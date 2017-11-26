package test.pojo;

import java.util.Date;

/**
 * Created by root on 17-11-23.
 *
 * 持久化类必须遵循java bean原则
 *
 * java bean原则
 * 1.类是public的
 * 2.有一个无参的构造方法
 * 3.属性都是private的
 * 4.属性都有对应的get/set方法
 */
public class User {
    /*对应user_info表的主键,若设置为自增列，则就算为它赋值了也不会插入数据库*/
    private int id;

    /*其他映射属性*/
    private String name;
    private Date birthday;

    /*未添加到映射的主键,hibernate不会对它做处理*/
    private String info;

    public User(){

    }

    public User(String name, Date birthday,String info) {
        this.name = name;
        this.birthday = birthday;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString(){
        return "User{ id:"+id+" , name:"+name+" , birthday:"+birthday +" }";
    }
}
