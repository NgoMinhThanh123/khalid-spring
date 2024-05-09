package com.nmt.universitysb.service.impl;

import com.nmt.universitysb.dto.StatisticsDto;
import com.nmt.universitysb.repository.StatsRepository;
import com.nmt.universitysb.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private StatsRepository statsRepository;

    @Override
    public List<StatisticsDto> StatsScoreByClassSubjectSemester(String classId, String subjectId, String semesterId, Integer scoreColumnId) {
        return this.statsRepository.StatsScoreByClassSubjectSemester(classId, subjectId, semesterId, scoreColumnId);
    }
}
