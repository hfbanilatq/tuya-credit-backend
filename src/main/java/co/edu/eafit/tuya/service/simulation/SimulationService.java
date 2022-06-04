package co.edu.eafit.tuya.service.simulation;

import co.edu.eafit.tuya.dto.GenericResponseDto;
import co.edu.eafit.tuya.dto.SimulationDto;
import org.springframework.data.domain.Pageable;

public interface SimulationService {
    public GenericResponseDto simulate(SimulationDto simulationDto);
    public GenericResponseDto save(SimulationDto simulationDto);
    public GenericResponseDto getSimulations(int id);
    public GenericResponseDto getSimulationsPage(Pageable pageable);
}
