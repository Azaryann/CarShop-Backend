package am.azaryan.car.service;

import am.azaryan.car.dto.PurchaserDto;

import java.util.List;

public interface PurchaserService {
    List<PurchaserDto> getAllPurchasers();
    PurchaserDto getPurchaserById(Long id);
    PurchaserDto createPurchaser(PurchaserDto purchaserDto);
    PurchaserDto updatePurchaser(Long id, PurchaserDto purchaserDto);
}


