package com.nmt.universitysb.repository;
import com.nmt.universitysb.dto.SubjectDto;
import com.nmt.universitysb.dto.TuitionFeeAndSubjectDto;
import com.nmt.universitysb.model.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String> {
    Optional<Subject> findById(String id);

    Page<Subject> findAllByNameContaining(String keyword, Pageable pageable);
    Subject save(Subject f);
    void deleteById(String id);

    @Query("select new com.nmt.universitysb.dto.SubjectDto(a.id, a.name, a.credit, a.majorId ) " +
            "from Subject a " +
            "join LecturerSubject ls on a.id = ls.subjectId " +
            "join Lecturer l on l.id = ls.lecturerId " +
            "where l.id = :lecturerId")
    List<SubjectDto> getSubjectByLecturerId(@Param("lecturerId") String lecturerId);

    @Query("Select a.credit " +
            "from Subject a " +
            "where a.id = :SubjectId")
    Long getCreditBySubjectId(@Param("SubjectId") String SubjectId);

    @Query("select new com.nmt.universitysb.dto.SubjectDto(s.id, s.name, s.credit, s.majorId ) " +
            "from Subject s " +
            "join EducationProgram ss on s.id = ss.subjectId.id " +
            "join Semester se on se.id = ss.semesterId.id " +
            "where se.id = :semesterId")
    List<SubjectDto> getSubjectBySemesterId(@Param("semesterId") String semesterId);

    @Query("select new com.nmt.universitysb.dto.SubjectDto(a.id, a.name, a.credit, a.majorId ) " +
            "from Subject a " +
            "join StudentSubject sb on a.id = sb.subjectId.id " +
            "join Student s on s.id = sb.studentId.id " +
            "where s.id = :studentId")
    List<SubjectDto> getSubjectByStudentId(@Param("studentId") String studentId);

    @Query("select new com.nmt.universitysb.dto.SubjectDto(a.id, a.name, a.credit, a.majorId ) " +
            "from Subject a " +
            "where a.name = :subjectName")
    SubjectDto getSubjectByName(@Param("subjectName") String subjectName);

    @Query("select new com.nmt.universitysb.dto.SubjectDto(a.id, a.name, a.credit, a.majorId ) " +
            "from Subject a " +
            "join EducationProgram ss on a.id = ss.subjectId.id " +
            "join Semester s on s.id = ss.semesterId.id " +
            "where a.majorId.id = :majorId and s.id = :semesterId")
    List<SubjectDto> getSubjectByMajorId(@Param("majorId") String majorId, @Param("semesterId") String semesterId);

    @Query("select new com.nmt.universitysb.dto.SubjectDto(a.id, a.name, a.credit, a.majorId ) \n" +
            "from Subject a \n" +
            "join StudentSubject sb on a.id = sb.subjectId.id \n" +
            "join Student s on s.id = sb.studentId.id \n " +
            "join Score sc on sc.studentSubjectId.id = sb.id \n" +
            "join Semester se on sc.semesterId.id = se.id \n" +
            "where s.id = :studentId and se.id = :semesterId")
    List<SubjectDto> getSubjectByStudentAndSemesterId(@Param("studentId") String studentId, @Param("semesterId") String semesterId);

    @Query("select distinct new com.nmt.universitysb.dto.SubjectDto(s.id, s.name, s.credit, s.majorId ) " +
            "from Subject s \n" +
            "join StudentSubject ss on s.id = ss.subjectId.id \n" +
            "join Student st on st.id = ss.studentId.id \n" +
            "join EducationProgram se on s.id = se.subjectId.id " +
            "join Semester sr on sr.id = se.semesterId.id " +
            "where ss.status = false and st.id = :studentId and sr.id = :semesterId")
    List<SubjectDto> getSubjectTemporaryCourse(@Param("studentId") String studentId, @Param("semesterId") String semesterId);

    @Query("select distinct new com.nmt.universitysb.dto.SubjectDto(s.id, s.name, s.credit, s.majorId ) " +
            "from Subject s \n" +
            "join StudentSubject ss on s.id = ss.subjectId.id \n" +
            "join Student st on st.id = ss.studentId.id \n" +
            "join EducationProgram ed on s.id = ed.subjectId.id " +
            "join Semester sr on sr.id = ed.semesterId.id " +
            "where st.id = :studentId and sr.id = :semesterId and ed.majorId.id = :majorId ")
    List<SubjectDto> getSubjectByEducationProgram(@Param("studentId") String studentId, @Param("semesterId") String semesterId, @Param("majorId") String majorId);

    @Query(value ="select new com.nmt.universitysb.dto.TuitionFeeAndSubjectDto(s, cp.price*s.credit)\n" +
            "FROM Subject s\n" +
            "JOIN StudentSubject ss ON s.id = ss.subjectId.id\n" +
            "JOIN Student st ON st.id = ss.studentId.id\n" +
            "JOIN EducationProgram ed ON s.id = ed.subjectId.id\n" +
            "JOIN Semester sr ON sr.id = ed.semesterId.id \n" +
            "JOIN CreditPrice cp ON cp.majorId.id = s.majorId.id\n" +
            "WHERE st.id = :studentId and sr.id = :semesterId AND cp.semesterId = :semesterId \n")
    List<TuitionFeeAndSubjectDto> getTuitionFeeOfSemester(@Param("studentId") String studentId, @Param("semesterId") String semesterId);
}
