package io.smart_agro.backend.service;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import io.smart_agro.backend.domain.Cooperative;
import io.smart_agro.backend.model.CooperativeDTO;
import io.smart_agro.backend.repos.CooperativeRepository;
import io.smart_agro.backend.util.NotFoundException;


@Service
public class CooperativeService {

    private final CooperativeRepository cooperativeRepository;

    public CooperativeService(final CooperativeRepository cooperativeRepository) {
        this.cooperativeRepository = cooperativeRepository;
    }

    public List<CooperativeDTO> findAll() {
        final List<Cooperative> cooperatives = cooperativeRepository.findAll(Sort.by("cooperativeId"));
        return cooperatives.stream()
                .map(cooperative -> mapToDTO(cooperative, new CooperativeDTO()))
                .toList();
    }

    public CooperativeDTO get(final Long cooperativeId) {
        return cooperativeRepository.findById(cooperativeId)
                .map(cooperative -> mapToDTO(cooperative, new CooperativeDTO()))
                .orElseThrow(NotFoundException::new);
    }


    
 public CooperativeDTO getByCoName(String coName) {
    Cooperative cooperative=cooperativeRepository.getByCoName(coName);
    if(cooperative != null){
   return  mapToDTO(cooperative, new CooperativeDTO());
    }  
  throw new NotFoundException();
    }


    public Long create(final CooperativeDTO cooperativeDTO) {
        final Cooperative cooperative = new Cooperative();
        mapToEntity(cooperativeDTO, cooperative);
        return cooperativeRepository.save(cooperative).getCooperativeId();
    }

    public void update(final Long cooperativeId, final CooperativeDTO cooperativeDTO) {
        final Cooperative cooperative = cooperativeRepository.findById(cooperativeId)
                .orElseThrow(NotFoundException::new);
        mapToEntity(cooperativeDTO, cooperative);
        cooperativeRepository.save(cooperative);
    }

    public void delete(final Long cooperativeId) {
        cooperativeRepository.deleteById(cooperativeId);
    }

    private CooperativeDTO mapToDTO(final Cooperative cooperative,
            final CooperativeDTO cooperativeDTO) {
        cooperativeDTO.setCooperativeId(cooperative.getCooperativeId());
        cooperativeDTO.setCoName(cooperative.getCoName());
        cooperativeDTO.setDistrict(cooperative.getDistrict());
        cooperativeDTO.setSector(cooperative.getSector());
        cooperativeDTO.setCell(cooperative.getCell());
        cooperativeDTO.setStatus(cooperative.getStatus());
        cooperativeDTO.setProvince(cooperative.getProvince());
        cooperativeDTO.setCropCategory(cooperative.getCropCategory());
        return cooperativeDTO;
    }

    private Cooperative mapToEntity(final CooperativeDTO cooperativeDTO,
            final Cooperative cooperative) {
        cooperative.setCoName(cooperativeDTO.getCoName());
        cooperative.setDistrict(cooperativeDTO.getDistrict());
        cooperative.setSector(cooperativeDTO.getSector());
        cooperative.setCell(cooperativeDTO.getCell());
        cooperative.setStatus(cooperativeDTO.getStatus());
        cooperative.setProvince(cooperativeDTO.getProvince());
        cooperative.setCropCategory(cooperativeDTO.getCropCategory());
        return cooperative;
    }

}
