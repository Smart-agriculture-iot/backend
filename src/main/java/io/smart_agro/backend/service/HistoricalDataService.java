package io.smart_agro.backend.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.smart_agro.backend.domain.HistoricalData;
import io.smart_agro.backend.model.HistoricalDataDTO;
import io.smart_agro.backend.repos.HistoricalDataRepository;
import io.smart_agro.backend.util.NotFoundException;


@Service
public class HistoricalDataService {

    private final HistoricalDataRepository historicalDataRepository;

    public HistoricalDataService(final HistoricalDataRepository historicalDataRepository) {
        this.historicalDataRepository = historicalDataRepository;
    }

    public List<HistoricalDataDTO> findAll() {
        final List<HistoricalData> historicalDatas = historicalDataRepository.findAll(Sort.by("id"));
        return historicalDatas.stream()
                .map(historicalData -> mapToDTO(historicalData, new HistoricalDataDTO()))
                .toList();
    }

    public HistoricalDataDTO get(final Long id) {
        return historicalDataRepository.findById(id)
                .map(historicalData -> mapToDTO(historicalData, new HistoricalDataDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final HistoricalDataDTO historicalDataDTO) {
        final HistoricalData historicalData = new HistoricalData();
        mapToEntity(historicalDataDTO, historicalData);
        return historicalDataRepository.save(historicalData).getId();
    }

    public void update(final Long id, final HistoricalDataDTO historicalDataDTO) {
        final HistoricalData historicalData = historicalDataRepository.findById(id)
                .orElseThrow(NotFoundException::new);
        mapToEntity(historicalDataDTO, historicalData);
        historicalDataRepository.save(historicalData);
    }

    public void delete(final Long id) {
        historicalDataRepository.deleteById(id);
    }

    private HistoricalDataDTO mapToDTO(final HistoricalData historicalData,
            final HistoricalDataDTO historicalDataDTO) {
        historicalDataDTO.setId(historicalData.getId());
        historicalDataDTO.setTemperature(historicalData.getTemperature());
        historicalDataDTO.setIsRaining(historicalData.getIsRaining());
        historicalDataDTO.setHumidity(historicalData.getHumidity());
        historicalDataDTO.setSoilmoisture(historicalData.getSoilmoisture());
        return historicalDataDTO;
    }

    private HistoricalData mapToEntity(final HistoricalDataDTO historicalDataDTO,
            final HistoricalData historicalData) {
        historicalData.setTemperature(historicalDataDTO.getTemperature());
        historicalData.setIsRaining(historicalDataDTO.getIsRaining());
        historicalData.setHumidity(historicalDataDTO.getHumidity());
        historicalData.setSoilmoisture(historicalDataDTO.getSoilmoisture());
        return historicalData;
    }

}
