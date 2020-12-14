package com.onlineshop.onlineshop.services;

import com.onlineshop.onlineshop.entity.ProducatorEntity;
import com.onlineshop.onlineshop.entity.ProdusEntity;
import com.onlineshop.onlineshop.exceptions.ResourceNotFoundException;
import com.onlineshop.onlineshop.model.ProdusDto;
import com.onlineshop.onlineshop.repository.ProducatorRepository;
import com.onlineshop.onlineshop.repository.ProdusRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ProdusService {

    private final ProdusRepository produsRepository;
    private final ProducatorRepository producatorRepository;

    public ProdusService(ProdusRepository produsRepository, ProducatorRepository producatorRepository) {
        this.produsRepository = produsRepository;
        this.producatorRepository = producatorRepository;
    }

    public List<ProdusDto> getProductsByCategory(String category){
        return produsRepository.getProdusEntitiesByCategorie(category).stream().map(ProdusEntity::convertToDto).collect(Collectors.toList());
    }

    private List<ProdusDto> getNumberElemsFromList(List<ProdusDto> produse) {

        List<ProdusDto> produsDtos = new ArrayList<>();
        produse.forEach(produs -> {
            if (produsDtos.size() < 5) produsDtos.add(produs);
        });
        return produsDtos;
    }

    public List<ProdusDto> getProductsForDiscount() {

        List<ProdusDto> produseDto = produsRepository.getProductsOrderedByQuantity().stream().map(ProdusEntity::convertToDto).collect(Collectors.toList());
        if (produseDto.size() < 5) return produseDto;
        else return getNumberElemsFromList(produseDto);
    }

    public List<ProdusDto> getAll() {
        List<ProdusEntity> produse = new ArrayList<>();
        produsRepository.findAll().forEach(produse::add);
        return produse.stream().map(ProdusEntity::convertToDto).collect(Collectors.toList());
    }

    public ProdusDto add(ProdusDto produsDto) {

        ProducatorEntity producatorEntity = producatorRepository.findById(produsDto.getIdProducator()).orElseThrow(() -> {
            throw new ResourceNotFoundException("Producator entity not found", HttpStatus.NOT_FOUND);
        });

        ProdusEntity produsEntity = produsDto.convertToEntity();
        produsEntity.setProducatorEntity(producatorEntity);

        return produsRepository.save(produsEntity).convertToDto();
    }

    public ProdusDto delete(Long id) {

        ProdusEntity produs = produsRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("entity not found", HttpStatus.NOT_FOUND);
        });
        produsRepository.delete(produs);
        return produs.convertToDto();
    }

    public ProdusDto update(ProdusDto produsDto) {

        ProdusEntity produs = produsRepository.findById(produsDto.getId()).orElseThrow(() -> {
            throw new ResourceNotFoundException("entity not found", HttpStatus.NOT_FOUND);
        });

        produs.setPret(produsDto.getPret());
        produs.setUrlPoza(produsDto.getUrlPoza());
        produs.setNume(produsDto.getNume());
        produs.setDescriere(produsDto.getDescriere());
        produs.setCategorie(produsDto.getCategorie());
        produs.setCantitate(produsDto.getCantitate());

        return produs.convertToDto();
    }

    public ProdusDto findById(Long id) {
        ProdusEntity produs = produsRepository.findById(id).orElseThrow(() -> {
            throw new ResourceNotFoundException("entity not found", HttpStatus.NOT_FOUND);
        });
        return produs.convertToDto();
    }
}



