package com.raulsales.demojwt.application.service.impl;

import com.raulsales.demojwt.application.service.CajeroService;
import com.raulsales.demojwt.domain.entity.Cajero;
import com.raulsales.demojwt.domain.persistence.CajeroRepository;
import com.raulsales.demojwt.application.dto.CajeroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CajeroServiceImpl implements CajeroService {

    private final CajeroRepository cajeroRepository;

    @Autowired
    public CajeroServiceImpl(CajeroRepository cajeroRepository) {
        this.cajeroRepository = cajeroRepository;
    }

    @Override
    public List<CajeroDTO> getAllCajeros() {
        List<Cajero> cajeros = cajeroRepository.findAll();
        return cajeros.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CajeroDTO getCajeroById(String cajeroId) {
        Cajero cajero = cajeroRepository.findById(cajeroId)
                .orElseThrow(() -> new RuntimeException("Cajero not found with id: " + cajeroId));
        return convertToDTO(cajero);
    }

    @Override
    public CajeroDTO createCajero(CajeroDTO cajeroDTO) {
        Cajero newCajero = convertToEntity(cajeroDTO);
        cajeroRepository.save(newCajero);
        return convertToDTO(newCajero);
    }

    @Override
    public CajeroDTO updateCajero(String cajeroId, CajeroDTO cajeroDTO) {
        Cajero existingCajero = cajeroRepository.findById(cajeroId)
                .orElseThrow(() -> new RuntimeException("Cajero not found with id: " + cajeroId));

        // Update fields manually
        existingCajero.setLocation(cajeroDTO.getLocation());
        existingCajero.setAddresses(cajeroDTO.getAddresses());

        cajeroRepository.save(existingCajero);

        return convertToDTO(existingCajero);
    }

    @Override
    public void deleteCajero(String cajeroId) {
        cajeroRepository.deleteById(cajeroId);
    }

    private CajeroDTO convertToDTO(Cajero cajero) {
        CajeroDTO cajeroDTO = new CajeroDTO();
        cajeroDTO.set_id(cajero.get_id());
        cajeroDTO.setLocation(cajero.getLocation());
        cajeroDTO.setAddresses(cajero.getAddresses());
        return cajeroDTO;
    }

    private Cajero convertToEntity(CajeroDTO cajeroDTO) {
        Cajero cajero = new Cajero();
        cajero.set_id(cajeroDTO.get_id());
        cajero.setLocation(cajeroDTO.getLocation());
        cajero.setAddresses(cajeroDTO.getAddresses());
        return cajero;
    }
}
