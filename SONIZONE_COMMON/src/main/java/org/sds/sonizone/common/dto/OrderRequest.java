package org.sds.sonizone.common.dto;

import java.math.BigDecimal;

public class OrderRequest {
    private Long id;
    private String orderNumber;
    private BigDecimal totalAmount;

    @Override
    public String toString() {
        return "OrderRequest{" +
                "orderNumber='" + orderNumber + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
}
