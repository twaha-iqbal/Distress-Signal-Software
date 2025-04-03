package org.example.models;

import java.util.List;

public class User {
    private String nid;  // Changed from username to nid
    private String password;
    private String email;
    private String name;
    private String phoneNumber;
    private String bloodGroup;
    private int age;
    private String sex;
    private List<String> emergencyNumbers;
    private List<String> emergencyMails;

    // Constructors
    public User() {}

    public User(String nid, String password, String email, String name, String phoneNumber, String bloodGroup, int age,
                String sex, List<String> emergencyNumbers, List<String> emergencyMails) {
        this.nid = nid;
        this.password = password;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.bloodGroup = bloodGroup;
        this.age = age;
        this.sex = sex;
        this.emergencyNumbers = emergencyNumbers;
        this.emergencyMails = emergencyMails;
    }

    // Getters and Setters
    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public List<String> getEmergencyNumbers() {
        return emergencyNumbers;
    }

    public void setEmergencyNumbers(List<String> emergencyNumbers) {
        this.emergencyNumbers = emergencyNumbers;
    }

    public List<String> getEmergencyMails() {
        return emergencyMails;
    }

    public void setEmergencyMails(List<String> emergencyMails) {
        this.emergencyMails = emergencyMails;
    }

    @Override
    public String toString() {
        return "User{" +
                "nid='" + nid + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", bloodGroup='" + bloodGroup + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", emergencyNumbers=" + emergencyNumbers +
                ", emergencyMails=" + emergencyMails +
                '}';
    }
}
