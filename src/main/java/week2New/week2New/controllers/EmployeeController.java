package week2New.week2New.controllers;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import week2New.week2New.advices.ApiResponse;
import week2New.week2New.dto.EmployeeDTO;
import week2New.week2New.entities.EmployeeEntity;
import week2New.week2New.repositories.EmployeeRepository;
import week2New.week2New.services.EmployeeService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{employeeId}")
    private ResponseEntity<EmployeeDTO> getEmployeeId(@PathVariable(name = "employeeId") Long id){
        Optional<EmployeeDTO> employeeDTO = employeeService.getEmployeeId(id);
        return employeeDTO.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping()
    private ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false) Integer age,
                                   @RequestParam(required = false) String sortBy){
       List<EmployeeDTO> employeeDTOS = employeeService.getAllEmployees();
       return ResponseEntity.ok(employeeDTOS);

    }
    @PostMapping
    private ResponseEntity<EmployeeDTO> createNewEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        return new ResponseEntity<>(employeeService.createNewEmployee(employeeDTO),HttpStatus.CREATED);
    }

    @PutMapping(path = "/{employeeId}")
    private ResponseEntity<EmployeeDTO> updateEmployeeById(@RequestBody EmployeeDTO employeeDTO,@PathVariable Long employeeId){
        EmployeeDTO employeeDTO1 =  employeeService.updateEmployeeById(employeeId,employeeDTO);
        return new ResponseEntity<>(employeeDTO1,HttpStatus.CREATED);
    }

    @DeleteMapping("/{employeeId}")
    private ResponseEntity<String> deleteEmployeeById(@PathVariable Long employeeId){
        String deleteMsg = employeeService.deleteEmployeeById(employeeId);
        return ResponseEntity.ok(deleteMsg);
    }

    @PatchMapping("/{employeeId}")
    private ResponseEntity<EmployeeDTO> updateDetailsOfEmployee(@PathVariable Long employeeId, @RequestBody Map<String, Object> fieldsToUpdate){
        EmployeeDTO employeeDTO = employeeService.updateDetailsOfEmployee(employeeId,fieldsToUpdate);
        if (employeeDTO == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employeeDTO);
    }


}
