package com.raulsales.demojwt.application.service;

import com.raulsales.demojwt.application.dto.CajeroDTO;

import java.util.List;

public interface CajeroService {

    List<CajeroDTO> getAllCajeros();

    CajeroDTO getCajeroById(String cajeroId);

    CajeroDTO createCajero(CajeroDTO cajeroDTO);

    CajeroDTO updateCajero(String cajeroId, CajeroDTO cajeroDTO);

    void deleteCajero(String cajeroId);
}