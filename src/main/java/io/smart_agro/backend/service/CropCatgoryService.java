package io.smart_agro.backend.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.smart_agro.backend.domain.CropCatgory;
import io.smart_agro.backend.model.CropCatgoryDTO;
import io.smart_agro.backend.repos.CropCatgoryRepository;
import io.smart_agro.backend.util.NotFoundException;


@Service
public class CropCatgoryService {

    private final CropCatgoryRepository cropCatgoryRepository;

    public CropCatgoryService(final CropCatgoryRepository cropCatgoryRepository) {
        this.cropCatgoryRepository = cropCatgoryRepository;
    }

    public List<CropCatgoryDTO> findAll() {
        final List<CropCatgory> cropCatgories = cropCatgoryRepository.findAll(Sort.by("categoryId"));
        return cropCatgories.stream()
                .map(cropCatgory -> mapToDTO(cropCatgory, new CropCatgoryDTO()))
                .toList();
    }

    public CropCatgoryDTO get(final Long categoryId) {
        return cropCatgoryRepository.findById(categoryId)
                .map(cropCatgory -> mapToDTO(cropCatgory, new CropCatgoryDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Long create(final CropCatgoryDTO cropCatgoryDTO) {
        final CropCatgory cropCatgory = new CropCatgory();
        mapToEntity(cropCatgoryDTO, cropCatgory);
        return cropCatgoryRepository.save(cropCatgory).getCategoryId();
    }

    public void update(final Long categoryId, final CropCatgoryDTO cropCatgoryDTO) {
        final CropCatgory cropCatgory = cropCatgoryRepository.findById(categoryId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(cropCatgoryDTO, cropCatgory);
        cropCatgoryRepository.save(cropCatgory);
    }

    public void delete(final Long categoryId) {
        cropCatgoryRepository.deleteById(categoryId);
    }

    private CropCatgoryDTO mapToDTO(final CropCatgory cropCatgory,
            final CropCatgoryDTO cropCatgoryDTO) {
        cropCatgoryDTO.setCategoryId(cropCatgory.getCategoryId());
        cropCatgoryDTO.setCropNames(cropCatgory.getCropNames());
        cropCatgoryDTO.setStatus(cropCatgory.getStatus());
        cropCatgoryDTO.setCreatedBy(cropCatgory.getCreatedBy());
        return cropCatgoryDTO;
    }

    private CropCatgory mapToEntity(final CropCatgoryDTO cropCatgoryDTO,
            final CropCatgory cropCatgory) {
        cropCatgory.setCropNames(cropCatgoryDTO.getCropNames());
        cropCatgory.setStatus(cropCatgoryDTO.getStatus());
        cropCatgory.setCreatedBy(cropCatgoryDTO.getCreatedBy());
        return cropCatgory;
    }

}
