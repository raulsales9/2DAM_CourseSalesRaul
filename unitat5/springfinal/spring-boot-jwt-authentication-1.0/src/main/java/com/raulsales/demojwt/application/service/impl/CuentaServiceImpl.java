package com.raulsales.demojwt.application.service.impl;

import com.raulsales.demojwt.application.dto.CuentaDto;
import com.raulsales.demojwt.application.dto.CuentaDtoConverter;
import com.raulsales.demojwt.application.service.CuentaService;
import com.raulsales.demojwt.domain.entity.Cuenta;
import com.raulsales.demojwt.domain.persistence.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CuentaServiceImpl implements CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;
    @Override
    public void insertAccount(CuentaDto cuentaDto) {
        Cuenta cuenta = CuentaDtoConverter.convertToEntity(cuentaDto);
        cuentaRepository.save(cuenta);
    }

    @Override
    public CuentaDto getAccountById(long id) {
        Cuenta user = cuentaRepository.findById(id).orElse(null);
        return (user != null) ? CuentaDtoConverter.convertToDto(user) : null;
    }

    @Override
    public List<CuentaDto> getAllAcounts() {
        List<Cuenta> cuentaList = cuentaRepository.findAll();
        List<CuentaDto> resultlist = new ArrayList<CuentaDto>();
        for (int i = 0; i < cuentaList.size(); ++i){
            resultlist.add(CuentaDtoConverter.convertToDto(cuentaList.get(i)));
        }
        return  resultlist;
    }

    @Override
    public CuentaDto updateAccount(CuentaDto cuentaDto) {
        Cuenta user = CuentaDtoConverter.convertToEntity(cuentaDto);
        cuentaRepository.save(user);
        return cuentaDto;
    }

    @Override
    public void deleteAccount(long id) {
        cuentaRepository.deleteById(id);
    }
}
