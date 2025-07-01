package com.machine_factotry.kursovaya.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Table(name = "equipment")
public class Equipment {

    @Id
    private Long id;
    private String quipmentName;
    private String equipmentType;
    private int departmentIt;
    private String equipmentStatus;
    private LocalDate installationDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuipmentName() {
        return quipmentName;
    }

    public void setQuipmentName(String quipmentName) {
        this.quipmentName = quipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public int getDepartmentIt() {
        return departmentIt;
    }

    public void setDepartmentIt(int departmentIt) {
        this.departmentIt = departmentIt;
    }

    public String getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public LocalDate getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(LocalDate installationDate) {
        this.installationDate = installationDate;
    }
}
