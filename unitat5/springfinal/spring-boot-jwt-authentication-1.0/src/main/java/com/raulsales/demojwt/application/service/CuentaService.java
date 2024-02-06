package com.raulsales.demojwt.application.service;

import com.raulsales.demojwt.application.dto.CuentaDto;
import com.raulsales.demojwt.domain.entity.Cuenta;

import java.util.List;

public interface CuentaService {
    void insertAccount(CuentaDto cuentaDto);
    CuentaDto getAccountById(long id);
    List<CuentaDto> getAllAcounts();

    CuentaDto updateAccount(CuentaDto cuentaDto);
    void deleteAccount(long id)
;}
