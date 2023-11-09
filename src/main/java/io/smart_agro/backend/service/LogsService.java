package io.smart_agro.backend.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.smart_agro.backend.domain.Logs;
import io.smart_agro.backend.model.LogsDTO;
import io.smart_agro.backend.repos.LogsRepository;
import io.smart_agro.backend.util.NotFoundException;


@Service
public class LogsService {

    private final LogsRepository logsRepository;

    public LogsService(final LogsRepository logsRepository) {
        this.logsRepository = logsRepository;
    }

    public List<LogsDTO> findAll() {
        final List<Logs> logses = logsRepository.findAll(Sort.by("id"));
        return logses.stream()
                .map(logs -> mapToDTO(logs, new LogsDTO()))
                .toList();
    }

    public LogsDTO get(final Long id) {
        return logsRepository.findById(id)
                .map(logs -> mapToDTO(logs, new LogsDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final LogsDTO logsDTO) {
        final Logs logs = new Logs();
        mapToEntity(logsDTO, logs);
        return logsRepository.save(logs).getId();
    }

    public void update(final Long id, final LogsDTO logsDTO) {
        final Logs logs = logsRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(logsDTO, logs);
        logsRepository.save(logs);
    }

    public void delete(final Long id) {
        logsRepository.deleteById(id);
    }

    private LogsDTO mapToDTO(final Logs logs, final LogsDTO logsDTO) {
        logsDTO.setId(logs.getId());
        logsDTO.setUserId(logs.getUserId());
        logsDTO.setActivity(logs.getActivity());
        return logsDTO;
    }

    private Logs mapToEntity(final LogsDTO logsDTO, final Logs logs) {
        logs.setUserId(logsDTO.getUserId());
        logs.setActivity(logsDTO.getActivity());
        return logs;
    }

}
