package com.tennis.service.impl;

import com.tennis.entity.Referee;
import com.tennis.repository.RefereeRepository;
import com.tennis.service.RefereeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefereeServiceImpl implements RefereeService {

    private final RefereeRepository refereeRepository;

    @Override
    public Referee createReferee(Referee referee) {
        return refereeRepository.save(referee);
    }

    @Override
    public List<Referee> getAllReferees() {
        return refereeRepository.findAll();
    }
}
