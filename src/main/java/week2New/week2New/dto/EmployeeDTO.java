package week2New.week2New.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import week2New.week2New.validations.Even;
import week2New.week2New.validations.PhoneNoValidation;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotNull(message = "Required field in employee: name")
    @NotBlank(message = "The employee's name should not be blank")
    @Size(min = 3, max = 40,message = "The maximum number of character in a name should be in the range of 3 to 40")
    private String name;

    @Email(message = "Not a valid email id")
    private String email;

    @Max(value = 60, message = "The age of the employee should not be greater than 60")
    @Min(value = 18,message = "The age of the employee should be greater than or equal to 18")
    private Integer age;

    @Pattern(regexp = "^(ADMIN|USER)$",message = "The role of the employee can only be an admin or an user")
    @NotBlank(message = "The role of the employee cannot be blank")
    @NotNull(message = "The role of the employee should be provided")
    private String role;

    @NotNull(message = "The salary of the employee should be provided")
    @Positive(message = "The salary of employee should be greater than zero")
    @Digits(integer = 6,fraction = 2)
    @DecimalMin(value = "1000.99")
    @DecimalMax(value = "100000.99")
    private Double salary;

    @FutureOrPresent
    private LocalDate dateOfJoining;

    @PhoneNoValidation
    @NotBlank(message = "The phone no can't be blank")
    private String phoneNo;

    @AssertTrue
    @JsonProperty("isActive")
    private Boolean isActive;

    @Even
    private Integer inputNo;
}
