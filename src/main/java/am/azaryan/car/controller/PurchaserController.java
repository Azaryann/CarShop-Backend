package am.azaryan.car.controller;

import am.azaryan.car.dto.PurchaserDto;
import am.azaryan.car.service.PurchaserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchasers")
@RequiredArgsConstructor
public class PurchaserController {

    private final PurchaserService purchaserService;

    @GetMapping
    public ResponseEntity<List<PurchaserDto>> getAllPurchasers() {
        return ResponseEntity.ok(purchaserService.getAllPurchasers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchaserDto> getPurchaserById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaserService.getPurchaserById(id));
    }

    @PostMapping
    public ResponseEntity<PurchaserDto> createPurchaser(@RequestBody PurchaserDto purchaserDto) {
        return ResponseEntity.ok(purchaserService.createPurchaser(purchaserDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchaserDto> updatePurchaser(@PathVariable Long id, @RequestBody PurchaserDto purchaserDto) {
        return ResponseEntity.ok(purchaserService.updatePurchaser(id, purchaserDto));
    }

}

