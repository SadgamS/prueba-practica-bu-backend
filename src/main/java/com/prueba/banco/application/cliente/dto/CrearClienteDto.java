package com.prueba.banco.application.cliente.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrearClienteDto {
 @NotBlank(message = "El nombre es obligatorio")
    @NotNull(message = "El nombre es obligatorio")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El nombre solo puede contener letras")
    private String nombres;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @NotNull(message = "El apellido paterno es obligatorio")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "El apellido paterno solo puede contener letras")
    private String apellidoPaterno;

    private String apellidoMaterno;

    @NotBlank(message = "El tipo de documento es obligatorio")
    @NotNull(message = "El tipo de documento es obligatorio")
    private String tipoDocumento;

    @NotBlank(message = "El documento de identidad es obligatorio")
    @NotNull(message = "El documento de identidad es obligatorio")
    @Pattern(regexp = "^[0-9 a-zA-Z]+$", message = "El documento de identidad solo puede contener números y letras")
    private String documentoIdentidad;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Past(message = "La fecha de nacimiento debe ser menor a la fecha actual")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El género es obligatorio")
    @NotNull(message = "El género es obligatorio")
    @Pattern(regexp = "^[MF]{1}$", message = "El género solo puede ser M o F")
    private String genero;
}
