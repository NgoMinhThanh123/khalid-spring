package com.nmt.universitysb.controller;

import com.nmt.universitysb.dto.StatisticsDto;
import com.nmt.universitysb.service.StatsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Statistics Controller")
@RestController
@RequestMapping("/api")
public class ApiStatisticsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/stats_by_class_subject_semester/")
    public ResponseEntity<List<StatisticsDto>> list(@RequestParam String classId,
                                                   @RequestParam String subjectId,
                                                   @RequestParam String semesterId,
                                                   @RequestParam  Integer scoreColumnId){
        List<StatisticsDto> list = this.statsService.StatsScoreByClassSubjectSemester(classId, subjectId, semesterId, scoreColumnId);
        if (list.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(list, HttpStatus.OK);

    }
}
