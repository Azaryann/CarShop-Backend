package am.azaryan.car.service.impl;

import am.azaryan.car.dto.PurchaserDto;
import am.azaryan.car.exception.ResourceNotFoundException;
import am.azaryan.car.mapper.PurchaserMapper;
import am.azaryan.car.model.Purchaser;
import am.azaryan.car.repository.CarRepository;
import am.azaryan.car.repository.PurchaserRepository;
import am.azaryan.car.service.PurchaserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaserServiceImpl implements PurchaserService {

    private final PurchaserRepository purchaserRepository;
    private final PurchaserMapper purchaserMapper;
    private final CarRepository carRepository;

    @Override
    public List<PurchaserDto> getAllPurchasers() {
        return purchaserRepository.findAll()
                .stream()
                .map(purchaserMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaserDto getPurchaserById(Long id) {
        Purchaser purchaser = purchaserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchaser not found with id: " + id));
        return purchaserMapper.toDto(purchaser);
    }

    @Override
    public PurchaserDto createPurchaser(PurchaserDto purchaserDto) {
        Purchaser purchaser = purchaserMapper.toEntity(purchaserDto);
        Purchaser savedPurchaser = purchaserRepository.save(purchaser);
        return purchaserMapper.toDto(savedPurchaser);
    }

    @Override
    public PurchaserDto updatePurchaser(Long id, PurchaserDto purchaserDto) {
        Purchaser purchaser = purchaserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Purchaser not found with id: " + id));
        purchaser.setName(purchaserDto.getName());
        purchaser.setEmail(purchaserDto.getEmail());
        Purchaser updatedPurchaser = purchaserRepository.save(purchaser);
        return purchaserMapper.toDto(updatedPurchaser);
    }


}