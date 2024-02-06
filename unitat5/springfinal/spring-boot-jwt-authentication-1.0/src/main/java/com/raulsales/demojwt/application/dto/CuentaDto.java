package com.raulsales.demojwt.application.dto;

public class CuentaDto {
    private Long id;
    private String banco;
    private String sucursal;
    private String dc;
    private String numerocuenta;
    private double saldoactual;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getDc() {
        return dc;
    }

    public void setDc(String dc) {
        this.dc = dc;
    }

    public String getNumerocuenta() {
        return numerocuenta;
    }

    public void setNumerocuenta(String numerocuenta) {
        this.numerocuenta = numerocuenta;
    }

    public double getSaldoactual() {
        return saldoactual;
    }

    public void setSaldoactual(double saldoactual) {
        this.saldoactual = saldoactual;
    }
}
