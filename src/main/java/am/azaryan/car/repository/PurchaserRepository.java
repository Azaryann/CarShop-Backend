package am.azaryan.car.repository;

import am.azaryan.car.model.Purchaser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaserRepository extends JpaRepository<Purchaser, Long> {
}
