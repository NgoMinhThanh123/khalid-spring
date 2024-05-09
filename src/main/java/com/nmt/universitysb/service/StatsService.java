package com.nmt.universitysb.service;

import com.nmt.universitysb.dto.StatisticsDto;
import java.util.List;

public interface StatsService {
    List<StatisticsDto> StatsScoreByClassSubjectSemester(String classId, String subjectId, String semesterId, Integer scoreColumnId);

}
