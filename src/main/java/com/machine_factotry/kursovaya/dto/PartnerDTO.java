package com.machine_factotry.kursovaya.dto;


public class PartnerDTO {

    private long uniqueId;
    private String partnerName;
    private String partnerType;
    private String phone;
    private String email;

    public PartnerDTO(long uniqueId, String partnerName, String partnerType, String phone, String email) {
        this.uniqueId = uniqueId;
        this.partnerName = partnerName;
        this.partnerType = partnerType;
        this.phone = phone;
        this.email = email;
    }

    public long getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(long uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getPartnerName() {
        return partnerName;
    }

    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }

    public String getPartnerType() {
        return partnerType;
    }

    public void setPartnerType(String partnerType) {
        this.partnerType = partnerType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
