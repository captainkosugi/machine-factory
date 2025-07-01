package com.machine_factotry.kursovaya.dto;

import com.machine_factotry.kursovaya.entity.GoodsMovement;

import java.time.format.DateTimeFormatter;

public class GoodsMovementDTO {

    private long movementId;
    private String productName;
    private int partnerId;
    private String movementType;
    private int quantity;
    private String movementDate;
    private boolean processed;

    public static GoodsMovementDTO toDTO(GoodsMovement goodsMovement, String productName) {
        GoodsMovementDTO dto = new GoodsMovementDTO();
        dto.setMovementId(goodsMovement.getMovementId());
        dto.setProductName(productName);
        dto.setPartnerId(goodsMovement.getPartnerId());
        dto.setMovementType(goodsMovement.getMovementType());
        dto.setQuantity(goodsMovement.getQuantity());
        dto.setMovementDate(goodsMovement.getMovementDate()
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
        return dto;
    }

    public long getMovementId() {
        return movementId;
    }

    public void setMovementId(long movementId) {
        this.movementId = movementId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMovementDate() {
        return movementDate;
    }

    public void setMovementDate(String movementDate) {
        this.movementDate = movementDate;
    }

    public boolean isProcessed() {
        return processed;
    }

    public void setProcessed(boolean processed) {
        this.processed = processed;
    }
}
