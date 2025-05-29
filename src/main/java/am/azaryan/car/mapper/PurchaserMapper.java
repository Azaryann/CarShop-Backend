package am.azaryan.car.mapper;

import am.azaryan.car.dto.PurchaserDto;
import am.azaryan.car.model.Purchaser;
import org.springframework.stereotype.Component;

@Component
public class PurchaserMapper {

    public PurchaserDto toDto(Purchaser purchaser) {
        PurchaserDto dto = new PurchaserDto();
        dto.setId(purchaser.getId());
        dto.setName(purchaser.getName());
        dto.setEmail(purchaser.getEmail());
        return dto;
    }

    public Purchaser toEntity(PurchaserDto dto) {
        Purchaser purchaser = new Purchaser();
        purchaser.setId(dto.getId());
        purchaser.setName(dto.getName());
        purchaser.setEmail(dto.getEmail());
        return purchaser;
    }
}

