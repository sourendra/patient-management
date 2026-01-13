package com.lc.patient_service.dto;

import com.lc.patient_service.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.*;

public class PatientRequestDTO {
    @NotBlank(message = "Name should not be blank!")
    @Size(max = 100, message = "Name should not be more than 100 characters!")
    private String name;

    @NotBlank(message = "Email should not be blank!")
    @Email(message = "Email is not valid!")
    private String email;

    @NotBlank(message = "Address should not be blank!")
    private String address;

    @NotBlank(message = "Date of Birth should not be blank!")
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Date of Birth must be in yyyy-MM-dd format"
    )
    private String dateOfBirth;

    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered Date is required!")
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Registered Date must be in yyyy-MM-dd format"
    )
    private String registeredDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(String registeredDate) {
        this.registeredDate = registeredDate;
    }
}
