package io.smart_agro.backend.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.smart_agro.backend.domain.Supervisor;
import io.smart_agro.backend.model.SupervisorDTO;
import io.smart_agro.backend.repos.SupervisorRepository;
import io.smart_agro.backend.util.NotFoundException;


@Service
public class SupervisorService {

    private final SupervisorRepository supervisorRepository;

    public SupervisorService(final SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    public List<SupervisorDTO> findAll() {
        final List<Supervisor> supervisors = supervisorRepository.findAll(Sort.by("id"));
        return supervisors.stream()
                .map(supervisor -> mapToDTO(supervisor, new SupervisorDTO()))
                .toList();
    }

    public SupervisorDTO get(final Long id) {
        return supervisorRepository.findById(id)
                .map(supervisor -> mapToDTO(supervisor, new SupervisorDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final SupervisorDTO supervisorDTO) {
        final Supervisor supervisor = new Supervisor();
        mapToEntity(supervisorDTO, supervisor);
        return supervisorRepository.save(supervisor).getId();
    }

    public void update(final Long id, final SupervisorDTO supervisorDTO) {
        final Supervisor supervisor = supervisorRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(supervisorDTO, supervisor);
        supervisorRepository.save(supervisor);
    }

    public void delete(final Long id) {
        supervisorRepository.deleteById(id);
    }

    private SupervisorDTO mapToDTO(final Supervisor supervisor, final SupervisorDTO supervisorDTO) {
        supervisorDTO.setId(supervisor.getId());
        supervisorDTO.setFullname(supervisor.getFullname());
        supervisorDTO.setUsername(supervisor.getUsername());
        supervisorDTO.setPassword(supervisor.getPassword());
        supervisorDTO.setProvince(supervisor.getProvince());
        supervisorDTO.setCreatedBy(supervisor.getCreatedBy());
        supervisorDTO.setStatus(supervisor.getStatus());
        supervisorDTO.setRoleId(supervisor.getRoleId());
        supervisorDTO.setDistrict(supervisor.getDistrict());
        supervisorDTO.setSector(supervisor.getSector());
        supervisorDTO.setCell(supervisor.getCell());
        return supervisorDTO;
    }

    private Supervisor mapToEntity(final SupervisorDTO supervisorDTO, final Supervisor supervisor) {
        supervisor.setFullname(supervisorDTO.getFullname());
        supervisor.setUsername(supervisorDTO.getUsername());
        supervisor.setPassword(supervisorDTO.getPassword());
        supervisor.setProvince(supervisorDTO.getProvince());
        supervisor.setCreatedBy(supervisorDTO.getCreatedBy());
        supervisor.setStatus(supervisorDTO.getStatus());
        supervisor.setRoleId(supervisorDTO.getRoleId());
        supervisor.setDistrict(supervisorDTO.getDistrict());
        supervisor.setSector(supervisorDTO.getSector());
        supervisor.setCell(supervisorDTO.getCell());
        return supervisor;
    }

}
