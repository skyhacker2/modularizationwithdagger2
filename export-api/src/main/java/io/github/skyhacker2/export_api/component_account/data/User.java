package io.github.skyhacker2.export_api.component_account.data;

public class User {
    private String userName;
    private String phone;
    private int age;

    public User(String userName, String phone, int age) {
        this.userName = userName;
        this.phone = phone;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                '}';
    }
}
