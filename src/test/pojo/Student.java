package test.pojo;

import java.util.Date;

/**
 * Created by wangwei on 17-11-26.
 */
public class Student {
    private int id;
    private String name;
    private Date birth;
    //组件属性
    private Address address;

    public Student() {
    }

    public Student(String name, Date birth, Address address) {
        this.name = name;
        this.birth = birth;
        this.address = address;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birth=" + birth +
                ", address=" + address +
                '}';
    }
}
