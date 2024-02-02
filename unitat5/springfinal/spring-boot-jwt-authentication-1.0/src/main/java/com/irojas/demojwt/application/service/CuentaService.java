package com.irojas.demojwt.application.service;

import com.irojas.demojwt.application.dto.CuentaDto;
import com.irojas.demojwt.domain.entity.Cuenta;

import java.util.List;

public interface CuentaService {
    void insertAccount(CuentaDto cuentaDto);
    CuentaDto getAccountById(long id);
    List<CuentaDto> getAllAcounts();

    CuentaDto updateAccount(CuentaDto cuentaDto);
    void deleteAccount(long id)
;}
