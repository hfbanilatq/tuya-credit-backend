package co.edu.eafit.tuya.repository;

import co.edu.eafit.tuya.model.Product;
import co.edu.eafit.tuya.model.Simulation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimulationRepository extends JpaRepository<Simulation, Long> {
    Page<Simulation> findAll(Pageable pageable);
    List<Simulation> findAllByUser_Id(int id);
}
