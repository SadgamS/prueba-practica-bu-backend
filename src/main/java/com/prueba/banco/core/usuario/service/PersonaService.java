package com.prueba.banco.core.usuario.service;

import org.springframework.stereotype.Service;

import com.prueba.banco.core.usuario.dto.CrearPersonaDto;
import com.prueba.banco.core.usuario.entity.PersonaEntity;
import com.prueba.banco.core.usuario.repository.PersonaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonaService {
    private final PersonaRepository personaRepository;

    public PersonaEntity crearPersona(CrearPersonaDto crearPersonaDto) {
        var persona = PersonaEntity.builder()
                .nombres(crearPersonaDto.getNombres())
                .apellidoPaterno(crearPersonaDto.getApellidoPaterno())
                .apellidoMaterno(crearPersonaDto.getApellidoMaterno())
                .tipoDocumento(crearPersonaDto.getTipoDocumento())
                .documentoIdentidad(crearPersonaDto.getDocumentoIdentidad())
                .fechaNacimiento(crearPersonaDto.getFechaNacimiento())
                .genero(crearPersonaDto.getGenero())
                .build();

        return personaRepository.save(persona);
    }
}
