package com.example.suwa_arana;

public class ModelD {

    String id, name, license, email, gender, pnumber, speciality, language;
    public ModelD(){}

     public  ModelD(String id, String name, String license, String email, String gender, String pnumber, String speciality, String language){
        this.id=id;
        this.name=name;
        this.license= license;
        this.email=email;
        this.gender=gender;
        this.pnumber=pnumber;
        this.speciality=speciality;
        this.language=language;
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

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
