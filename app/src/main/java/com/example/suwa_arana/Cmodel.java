package com.example.suwa_arana;



public class Cmodel {

    String id, date, name, gender, age, phone, disease, doctor;
    public Cmodel(){}

    public Cmodel(String id, String date, String name, String gender, String age, String phone, String disease, String doctor){
        this.id=id;
        this.date=date;
        this.name=name;
        this.gender=gender;
        this.age=age;
        this.phone=phone;
        this.disease=disease;
        this.doctor=doctor;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
