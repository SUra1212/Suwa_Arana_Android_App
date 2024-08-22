package com.example.suwa_arana;

public class Model {

    String id, name, gender, age, address, email, phone;
    public Model(){}

    public Model(String id, String name, String gender, String age, String address, String email, String phone){
        this.id=id;
        this.name=name;
        this.gender=gender;
        this.age=age;
        this.address=address;
        this.email=email;
        this.phone=phone;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

