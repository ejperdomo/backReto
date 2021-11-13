package com.usa.ciclo3.retociclo3.controller;

import com.usa.ciclo3.retociclo3.model.Machine;
import com.usa.ciclo3.retociclo3.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Machine")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class MachineController {

    @Autowired
    private MachineService machineService;

    @GetMapping("/all")
    public List<Machine> getMachine(){
        return machineService.getAll();
    }

    @GetMapping("/{idMachine}")
    public Optional<Machine> getMachine(@PathVariable("idMachine") int id){
        return  machineService.getMachine(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Machine save(@RequestBody Machine machine){
        return  machineService.save(machine);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Machine update(@RequestBody Machine machine){
        return machineService.update(machine);
    }

    @DeleteMapping("/{idMachine}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("idMachine")int id){
        return machineService.deleteMachine(id);
    }
}
