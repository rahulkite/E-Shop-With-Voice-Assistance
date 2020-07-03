package com.example.priya.finalprojectsdp.Common;

import java.util.List;

public class Request {

    private String Phone;
    private String Name;
    private String Address;
    private String Total;
    private String status;
    private List<Order> product;
    public Request(String phone, String name, String address, String total, List<Order> product) {
        Phone = phone;
        Name = name;
        Address = address;
        Total = total;
        this.product = product;
        this.status = "0";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public List<Order> getProduct() {
        return product;
    }

    public void setProduct(List<Order> product) {
        this.product = product;
    }
}
