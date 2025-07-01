package com.machine_factotry.kursovaya.dto;

public class DashboardDTO {
    private int EmployeesCount;
    private int EquipmentCount;
    private int ProductsCount;
    private int PartnersCount;

    public int getEmployeesCount() {
        return EmployeesCount;
    }

    public void setEmployeesCount(int employeesCount) {
        EmployeesCount = employeesCount;
    }

    public int getEquipmentCount() {
        return EquipmentCount;
    }

    public void setEquipmentCount(int equipmentCount) {
        EquipmentCount = equipmentCount;
    }

    public int getProductsCount() {
        return ProductsCount;
    }

    public void setProductsCount(int productsCount) {
        ProductsCount = productsCount;
    }

    public int getPartnersCount() {
        return PartnersCount;
    }

    public void setPartnersCount(int partnersCount) {
        PartnersCount = partnersCount;
    }
}
