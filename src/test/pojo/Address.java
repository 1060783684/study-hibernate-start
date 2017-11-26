package test.pojo;

/**
 * Created by wangwei on 17-11-26.
 */
public class Address {
    private String mail;
    private String address;

    public Address() {
    }

    public Address(String mail, String address) {
        this.mail = mail;
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "mail='" + mail + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
