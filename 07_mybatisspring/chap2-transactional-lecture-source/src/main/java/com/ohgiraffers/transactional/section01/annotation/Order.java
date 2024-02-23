package com.ohgiraffers.transactional.section01.annotation;

// 엔티티 개념으로 만드는거 ( DTO X)
// 불변 객체라고 도 한다.

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {
    private int orderCode;
    private String orderDate;
    private String orderTime;
    private int totalOrderPrice;

    public Order() {
    }

    public Order(int orderCode, String orderDate, String orderTime, int totalOrderPrice) {
        this.orderCode = orderCode;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.totalOrderPrice = totalOrderPrice;
    }

    /* 설명. insert 의 편리함을 위해 Order의 개념인 OrderMenu 엔티티까지 포함해서 필드를 작성한다.(insert 시 추가) */
    private List<OrderMenu> orderMenus;

    public Order(LocalDate orderDate, LocalTime orderTime, int totalOrderPrice, List<OrderMenu> orderMenus) {

        /* 설명. LocalDate 도는 LocalTime형을 DB에 저장하고 싶은 문자열 형태로 변환하는 작업 응용해보기 */
        this.orderDate = orderDate.format((DateTimeFormatter.ofPattern("yyyyMMdd")));
        this.orderTime = orderTime.format((DateTimeFormatter.ofPattern("HH:mm:ss")));
        this.totalOrderPrice = totalOrderPrice;
        this.orderMenus = orderMenus;
    }

    public int getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(int orderCode) {
        this.orderCode = orderCode;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public int getTotalOrderPrice() {
        return totalOrderPrice;
    }

    public void setTotalOrderPrice(int totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderCode=" + orderCode +
                ", orderDate='" + orderDate + '\'' +
                ", orderTime='" + orderTime + '\'' +
                ", totalOrderPrice=" + totalOrderPrice +
                '}';
    }
}
