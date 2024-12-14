package week2New.week2New.services;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import week2New.week2New.dto.EmployeeDTO;
import week2New.week2New.entities.EmployeeEntity;
import week2New.week2New.exceptions.ResourceNotFoundException;
import week2New.week2New.repositories.EmployeeRepository;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;
    private final DateTimeFormatter dateTimeFormatter;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper, DateTimeFormatter dateTimeFormatter) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
        this.dateTimeFormatter = dateTimeFormatter;
    }

    public Optional<EmployeeDTO> getEmployeeId(Long employeeId){
        doesEmployeeExist(employeeId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);
        return employeeEntity.map(employeeEntity1 -> modelMapper.map(employeeEntity1,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        isAnyEmployeePresentInRecords();
        List<EmployeeEntity> employeeEntities= employeeRepository.findAll();
        return employeeEntities
                .stream()
                .map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createNewEmployee(EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity,EmployeeDTO.class);
    }

    public boolean isEmployeeExists(Long employeeId){
        return employeeRepository.existsById(employeeId);

    }

    public void doesEmployeeExist(Long employeeId){
        boolean isEmployeeExist = employeeRepository.existsById(employeeId);
        if(!isEmployeeExist){
            throw new ResourceNotFoundException("Employee Not Found with the id - "+employeeId);
        }
    }
    public void isAnyEmployeePresentInRecords(){
        long recordCount = employeeRepository.count();
        if(recordCount == 0){
            throw new ResourceNotFoundException("None employee exists in the record");
        }
    }


    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO,EmployeeEntity.class);
        if(isEmployeeExists(employeeId)){
            employeeEntity.setId(employeeId);
        }
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        return modelMapper.map(savedEntity,EmployeeDTO.class);
    }

    public String deleteEmployeeById(Long employeeId) {
        if(isEmployeeExists(employeeId)){
            employeeRepository.deleteById(employeeId);
            return "Employee with the id- " + employeeId + " has been deleted";
        }
        return "Employee with the id- " + employeeId + " does not exist";
    }

    public EmployeeDTO updateDetailsOfEmployee(Long employeeId, Map<String, Object> fieldsToUpdate) {
        doesEmployeeExist(employeeId);
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);
        fieldsToUpdate.forEach((field, value) -> {
            if(field.equals("dateOfJoining")){
                value = LocalDate.parse(value.toString(),dateTimeFormatter);
            }
            Field fieldsToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);
            if (fieldsToBeUpdated != null) {
                fieldsToBeUpdated.setAccessible(true);
                ReflectionUtils.setField(fieldsToBeUpdated, employeeEntity, value);
            }
        });
        EmployeeEntity savedEntity;
        if(employeeEntity != null) {
            savedEntity = employeeRepository.save(employeeEntity);
            return modelMapper.map(savedEntity,EmployeeDTO.class);
        }
        else
            return null;
    }
}
