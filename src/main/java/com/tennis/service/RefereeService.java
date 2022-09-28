package com.tennis.service;

import com.tennis.entity.Referee;

import java.util.List;

public interface RefereeService {

    Referee createReferee(Referee referee);
    List<Referee> getAllReferees();
}
