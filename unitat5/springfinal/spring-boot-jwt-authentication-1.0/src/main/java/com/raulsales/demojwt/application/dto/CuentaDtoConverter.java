package com.raulsales.demojwt.application.dto;

import com.raulsales.demojwt.domain.entity.Cuenta;

public class CuentaDtoConverter {

    public static CuentaDto convertToDto(Cuenta cuenta) {
        CuentaDto cuentaDto = new CuentaDto();
        cuentaDto.setId(cuenta.getId());
        cuentaDto.setBanco(cuenta.getBanco());
        cuentaDto.setSucursal(cuenta.getSucursal());
        cuentaDto.setDc(cuenta.getDc());
        cuentaDto.setNumerocuenta(cuenta.getNumerocuenta());
        cuentaDto.setSaldoactual(cuenta.getSaldoactual());

        return cuentaDto;
    }

    public static Cuenta convertToEntity(CuentaDto cuentaDto) {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(cuentaDto.getId());
        cuenta.setBanco(cuentaDto.getBanco());
        cuenta.setSucursal(cuentaDto.getSucursal());
        cuenta.setDc(cuentaDto.getDc());
        cuenta.setNumerocuenta(cuentaDto.getNumerocuenta());
        cuenta.setSaldoactual(cuentaDto.getSaldoactual());

        return cuenta;
    }
}

