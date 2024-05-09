package com.nmt.universitysb.repository;

import com.nmt.universitysb.dto.StatisticsDto;
import com.nmt.universitysb.model.ScoreValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<ScoreValue, Integer>{
    @Query("select new com.nmt.universitysb.dto.StatisticsDto( sv.value) \n"
            + "FROM ScoreColumn sc \n"
            + "left join ScoreValue sv on sv.scoreColumnId.id = sc.id\n"
            + "left join Score s on sv.scoreId.id = s.id\n"
            + "join StudentSubject ss on s.studentSubjectId.id = ss.id\n"
            + "join Subject su on ss.subjectId = su.id\n"
            + "join Semester se on s.semesterId = se.id\n"
            + "left join Student st on ss.studentId = st.id\n"
            + "join Classes cl on st.classesId = cl.id\n"
            + "join LecturerSubject lb on lb.subjectId = su.id\n"
            + "join Lecturer l on lb.lecturerId = l.id\n"
            + "where cl.id = :classId and su.id = :subjectId and se.id = :semesterId and sc.id = :scoreColumnId\n"
            + "group by sv.value")
    List<StatisticsDto> StatsScoreByClassSubjectSemester(@Param("classId") String classId, @Param("subjectId") String subjectId, @Param("semesterId") String semesterId, @Param("scoreColumnId") Integer scoreColumnId);
}
