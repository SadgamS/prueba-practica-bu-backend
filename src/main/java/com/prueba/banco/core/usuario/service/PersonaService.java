package com.prueba.banco.core.usuario.service;

import org.springframework.data.domain.Example;
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

    public Long editarPersona(PersonaEntity persona) {
        var personaExistente = personaRepository.findById(persona.getId()).orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        personaExistente.setNombres(persona.getNombres());
        personaExistente.setApellidoPaterno(persona.getApellidoPaterno());
        personaExistente.setApellidoMaterno(persona.getApellidoMaterno());
        personaExistente.setTipoDocumento(persona.getTipoDocumento());
        personaExistente.setDocumentoIdentidad(persona.getDocumentoIdentidad());
        personaExistente.setFechaNacimiento(persona.getFechaNacimiento());
        personaExistente.setGenero(persona.getGenero());
        return personaRepository.save(personaExistente).getId();
    }

    public Boolean existePersonaPorDocumentoIdentidad(String documentoIdentidad) {
        return personaRepository
                .exists(Example.of(PersonaEntity.builder().documentoIdentidad(documentoIdentidad).build()));
    }
}
