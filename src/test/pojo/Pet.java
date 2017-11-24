package test.pojo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by root on 17-11-24.
 */

/*使用注释配置映射*/
/*
 * @Entity 注释，标志着这个类为一个实体 bean，所以它必须含有一个没有参数的构造函数并且在可保护范围是可见的
 * @table 注释,name属性的值表示当前类对应数据库中的哪一个表
 * @Id  注释,属性为表的主键
 * @GeneratedValue 注释
 * @Column 注释用于指定某一列与某一个字段或是属性映射的细节信息
 *
 *   name 属性允许显式地指定列的名称。
 *   length 属性为用于映射一个值，特别为一个字符串值的列的大小。
 *
 *
 */
@Entity
@Table(name = "pet_info")
public class Pet {
    @Id
    //不写strategy相当于GenerationType.AUTO这样的话主键就需要由程序设置，而不是自增,
    //GenerationType.IDENTITY代表SQL Server和MySQL的自增
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="brith")
    private Date brith;

    public Pet(){

    }

    public Pet(String name, Date brith) {
        this.name = name;
        this.brith = brith;
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

    public Date getBrith() {
        return brith;
    }

    public void setBrith(Date brith) {
        this.brith = brith;
    }

    @Override
    public String toString(){
        return "Pet{ id:" + id + " , name:" + name+" , birth:" + brith + " }";
    }
}
