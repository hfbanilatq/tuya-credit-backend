package co.edu.eafit.tuya.controller;

import co.edu.eafit.tuya.dto.SimulationDto;
import co.edu.eafit.tuya.service.simulation.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulation")
@CrossOrigin("*")
public class SimulationController {
    private SimulationService simulationService;

    @Autowired
    public void setSimulationService(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    @PostMapping("/simulate")
    public ResponseEntity<?> simulate(@RequestBody SimulationDto simulationDto) {
        return new ResponseEntity<>(this.simulationService.simulate(simulationDto), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SimulationDto simulationDto) {
        return new ResponseEntity<>(this.simulationService.save(simulationDto), HttpStatus.OK);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<?> getSimulations(@PathVariable int userId) {
        return new ResponseEntity<>(this.simulationService.getSimulations(userId), HttpStatus.OK);
    }

    @GetMapping("/list/paginate")
    public ResponseEntity<?> getSimulationsPaginate(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "3") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return new ResponseEntity<>(this.simulationService.getSimulationsPage(pageable), HttpStatus.OK);
    }

}
