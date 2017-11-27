package test.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by root on 17-11-27.
 */
//    @Column 注释用于指定某一列与某一个字段或是属性映射的细节信息,这个注释可以注释在属性对应的get方法上
//
//          name属性:允许显式地指定列的名称。注:当属性名和数据库中的字段名相同时可以没有name属性
//          length属性:为用于映射一个值，特别为一个字符串值的列的大小。
//          nullable属性:true/false,表示属性能否为null
@Entity
@Table(name="player_info")
public class Player extends PlayerBase{
    private int id;
    private String name;
    private Date birth;
    private byte[] picture;

    public Player(){

    }

    public Player(int id,String name, Date birth, byte[] picture) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.picture = picture;
    }

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(nullable = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//     @Temporal 注解:指定详细的时间类型

    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

//    @Lob 注解,表示属性将被持久化为Blob或者Clob类型
//           java.sql.Clob, Character[],char[],java.lang.String-->Clob对应mysql中的Text类型
//           java.sql.Blob,Byte[], byte[], serializable-->Blob
    @Column
    @Lob
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
