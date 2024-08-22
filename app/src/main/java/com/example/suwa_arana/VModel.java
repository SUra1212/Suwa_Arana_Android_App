package com.example.suwa_arana;

public class VModel {

    String id, name, phone, address, district, nic, dose, allergie;
    public VModel(){}

        public VModel(String id, String name, String phone, String address, String district, String nic, String dose, String allergie){
            this.id = id;
            this.name = name;
            this.phone = phone;
            this.address = address;
            this.district = district;
            this.nic = nic;
            this.dose = dose;
            this.allergie = allergie;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getAllergie() {
        return allergie;
    }

    public void setAllergie(String allergie) {
        this.allergie = allergie;
    }
}
