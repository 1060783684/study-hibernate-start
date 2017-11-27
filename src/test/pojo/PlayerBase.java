package test.pojo;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Created by root on 17-11-27.
 */
//@MappedSuperclass:代表这个类一定是一个父类,不会为它单独生成一张表,而是成为子类的表的一部分
@MappedSuperclass
public class PlayerBase {
    private String Address;

    public PlayerBase(){

    }

    public PlayerBase(String address) {
        Address = address;
    }

    @Column
    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
