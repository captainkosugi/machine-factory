package com.machine_factotry.kursovaya.dto;

import com.machine_factotry.kursovaya.entity.Equipment;

import java.time.format.DateTimeFormatter;

public class EquipmentDTO {

    private Long id;
    private String equipmentName;
    private String equipmentType;
    private String departmentName;
    private String equipmentStatus;
    private String installationDate;

    public static EquipmentDTO toDTO(Equipment equipment, String departmentName) {
        EquipmentDTO dto = new EquipmentDTO();
        dto.setId(equipment.getId());
        dto.setEquipmentName(equipment.getQuipmentName());
        dto.setEquipmentType(equipment.getEquipmentType());
        dto.setDepartmentName(departmentName);
        dto.setEquipmentStatus(equipment.getEquipmentStatus());
        dto.setInstallationDate(equipment.getInstallationDate()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyy")));
        return dto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getEquipmentStatus() {
        return equipmentStatus;
    }

    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public String getInstallationDate() {
        return installationDate;
    }

    public void setInstallationDate(String installationDate) {
        this.installationDate = installationDate;
    }
}
