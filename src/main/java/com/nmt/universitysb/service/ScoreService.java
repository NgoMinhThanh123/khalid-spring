package com.nmt.universitysb.service;

import com.nmt.universitysb.dto.*;
import com.nmt.universitysb.model.Score;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ScoreService {
    List<Score> findAll();
    Page<Score> findAll(Pageable pageable);

    Optional<Score> findById(int id);

    Page<Score> findAllByIdContaining(String keyword, Pageable pageable);
    Score save(Score f);
    boolean deleteScore(int id);

    List<ScoreDto> getScoreByStudentId(String studentId, String subjectId, String semesterId);
    List<ScoreListDto> getListScoreStudent(String studentId, String semesterId);
    List<String> getListAcademicWarning(String studentId, String semesterId);
    List<StudentScoreDTO> getStudentScores(String lecturerId, String semesterId, String subjectId);
    List<Score_ScoreValueDto> addScore(List<Map<String, String>> scoreParamsList);
    List<Score_ScoreValueDto> addScoreByExcel(String subjectId, String semesterId, String studentId, List<ScoreDto> scoreDtoList);

    ScoreDto getFinalScoreForSubject(String studentId, String subjectId, String semesterId);
    ScoreDto getAccumulateScoreForSemester(String studentId, String semesterId);
    ScoreDto getFinalAccumulateScoreForStudent(String studentId);
}
