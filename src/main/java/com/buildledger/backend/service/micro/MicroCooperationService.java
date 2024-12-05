package com.buildledger.backend.service.micro;

import com.buildledger.backend.model.building.Cooperation;
import com.buildledger.backend.repository.CooperationRepository;
import org.springframework.stereotype.Service;


@Service
public class MicroCooperationService {
    private final CooperationRepository cooperationRepository;

    public MicroCooperationService(CooperationRepository cooperationRepository) {
        this.cooperationRepository = cooperationRepository;
    }

    public Cooperation saveAndFlush(Cooperation cooperation) {
        return cooperationRepository.saveAndFlush(cooperation);
    }
    public Cooperation findById(Long id) {
        return cooperationRepository.findById(id).orElse(null);
    }
}
