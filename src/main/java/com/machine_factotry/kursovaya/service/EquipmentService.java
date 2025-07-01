package com.machine_factotry.kursovaya.service;

import com.machine_factotry.kursovaya.dto.EquipmentDTO;
import com.machine_factotry.kursovaya.entity.Equipment;
import com.machine_factotry.kursovaya.repository.DepartmentRepository;
import com.machine_factotry.kursovaya.repository.EquipmentRepository;
import com.machine_factotry.kursovaya.util.DtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final DepartmentRepository departmentRepository;


    public EquipmentService(EquipmentRepository equipmentRepository,
                            DepartmentRepository departmentRepository) {
        this.equipmentRepository = equipmentRepository;
        this.departmentRepository = departmentRepository;

    }

    private EquipmentDTO convertToDto(Equipment equipment) {
        String departmentName = departmentRepository.getDepartmentNameById(
                equipment.getDepartmentIt()
        );
        return EquipmentDTO.toDTO(equipment, departmentName);
    }

    public List<EquipmentDTO> searchEquipment(String searchTerm) {
        return DtoConverter.convert(
                equipmentRepository.searchEquipment("%"+searchTerm+"%"),
                this::convertToDto
        );
    }

    public List<EquipmentDTO> getEquipmentData() {
        return DtoConverter.convert(
                equipmentRepository.getEquipmentData(),
                this::convertToDto
        );
    }

    public void addEquipment(Map<String, String> formData, int departmentId) {
        String equipmentName = formData.get("eq_name");
        String equipmentType = formData.get("eq_type");
        String equipmentStatus = formData.get("eq_status");
        String installationDate =  formData.get("installation_date");

        equipmentRepository.addEquipment(
                equipmentName,
                equipmentType,
                departmentId,
                equipmentStatus,
                LocalDate.parse(installationDate)
        );
    }

    public List<String> getEquipmentStatusData() {
        return equipmentRepository.getEquipmentStatus();
    }
}
