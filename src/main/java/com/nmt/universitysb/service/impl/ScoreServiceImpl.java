package com.nmt.universitysb.service.impl;
import com.nmt.universitysb.dto.*;
import com.nmt.universitysb.model.*;
import com.nmt.universitysb.repository.*;
import com.nmt.universitysb.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreRepository scoreRepo;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private ScoreColumnRepository scoreColumnRepository;
    @Autowired
    private ScoreValueRepository scoreValueRepository;
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;
    @Autowired
    private ScorePercentRepository scorePercentRepository;

    @Override
    public List<Score> findAll() {
        return this.scoreRepo.findAll();
    }

    @Override
    public Page<Score> findAll(Pageable pageable) {
        return this.scoreRepo.findAll(pageable);
    }

    @Override
    public Optional<Score> findById(int id) {
        return this.scoreRepo.findById(id);
    }

    @Override
    public Page<Score> findAllByIdContaining(String keyword, Pageable pageable) {
        return this.scoreRepo.findAllByIdContaining(keyword, pageable);
    }

    @Override
    public Score save(Score f) {
        return this.scoreRepo.save(f);
    }

    @Override
    public boolean deleteScore(int id) {
        this.scoreRepo.deleteById(id);
        return true;
    }

    @Override
    public List<StudentScoreDTO> getStudentScores(String lecturerId, String semesterId, String subjectId) {
        return this.scoreRepo.getStudentScores(lecturerId, semesterId, subjectId);
    }

    @Override
    public List<ScoreDto> getScoreByStudentId(String studentId, String subjectId, String semesterId) {
        return this.scoreRepo.getScoreByStudentId(studentId, subjectId, semesterId);
    }

    @Override
    public List<ScoreListDto> getListScoreStudent(String studentId, String semesterId) {
        List<ScoreListDto> scoreListDtos = new ArrayList<>();
        List<SubjectDto> subjects = this.subjectRepository.getSubjectByStudentAndSemesterId(studentId, semesterId);
        Optional<Semester> semesters = this.semesterRepository.findById(semesterId);
        if (semesters.isPresent()) {
            Semester semester = semesters.get();
            String semesterName = semester.getName();
            int schoolYear = semester.getSchoolYear();

            for (int i = 0; i < subjects.size(); i++) {
                SubjectDto subject = subjects.get(i);
                List<ScoreDto> scoreDtos = this.scoreRepo.getScoreByStudentId(studentId, subject.getId(), semesterId);

                ScoreDto scoreDto = getFinalScoreForSubject(studentId, subject.getId(), semesterId);
                scoreDtos.add(scoreDto);
                ScoreListDto scoreListDto = new ScoreListDto();
                scoreListDto.setScoreDto(scoreDtos);
                scoreListDto.setSubjectId(subject.getId());
                scoreListDto.setSubjectName(subject.getName());
                scoreListDto.setCredit(subject.getCredit());
                scoreListDto.setSemesterName(semesterName);
                scoreListDto.setFromDate(semester.getFromDate());
                scoreListDto.setToDate(semester.getToDate());
                scoreListDto.setSchoolYear(String.valueOf(schoolYear));
                scoreListDtos.add(scoreListDto);
            }
        }

        return scoreListDtos;
    }

    @Override
    public List<String> getListAcademicWarning(String studentId, String semesterId) {
        List<String> academicWarningList = new ArrayList<>();
        Set<String> addedWarnings = new HashSet<>(); // Sử dụng Set để lưu trữ các môn học đã được cảnh báo

        List<ScoreListDto> scoreList = getListScoreStudent(studentId, semesterId);
        ScoreDto accumulateScore = getAccumulateScoreForSemester(studentId, semesterId);
        if(accumulateScore.getScoreValue() != null && accumulateScore.getScoreValue() < 4)
            academicWarningList.add("Điểm tích lũy học kì ở mức thấp.");
        else if (accumulateScore.getScoreValue() != null && accumulateScore.getScoreValue() < 7) {
            academicWarningList.add("Điểm tích lũy học kì ở mức trung bình.");
        } else {
            academicWarningList.add("Điểm tích lũy học kì ở mức khá.");
        }
        for (ScoreListDto score : scoreList) {
            boolean warningAddedForSubject = false; // Biến để kiểm tra xem đã thêm thông báo cảnh báo cho môn học này chưa
            for (ScoreDto scoreDto : score.getScoreDto()) {
                if (scoreDto.getScoreColumnName().equals("Giữa kì") || scoreDto.getScoreColumnName().equals("Cuối kì")) {
                    Double scoreValue = scoreDto.getScoreValue();
                    if (scoreValue != null && scoreValue < 4 && !warningAddedForSubject) {
                        academicWarningList.add("Cần chú ý Điểm môn học " + score.getSubjectName() + " thấp, nguy cơ rớt môn, hãy cố gắng cải thiện điểm nhé!");
                        addedWarnings.add(score.getSubjectName()); // Đánh dấu rằng đã thêm thông báo cảnh báo cho môn học này
                        warningAddedForSubject = true;
                    } else if (scoreValue != null && scoreValue < 7 && !warningAddedForSubject) {
                        academicWarningList.add("Môn học " + score.getSubjectName() + " có mức điểm trung bình, cần cải thiện thêm điểm số!");
                        addedWarnings.add(score.getSubjectName()); // Đánh dấu rằng đã thêm thông báo cảnh báo cho môn học này
                        warningAddedForSubject = true;
                    }
                }
            }
        }

        return academicWarningList;
    }


    @Override
    public List<Score_ScoreValueDto> addScore(List<Map<String, String>> scoreParamsList) {
        List<Score_ScoreValueDto> scoreValueDtoList = new ArrayList<>();
        for (Map<String, String> params : scoreParamsList) {
            Optional<Subject> subject = this.subjectRepository.findById(params.get("subjectId"));
            Optional<Semester> semester = this.semesterRepository.findById(params.get("semesterId"));
            Optional<Student> student = this.studentRepository.findById(params.get("studentId"));
            Optional<ScoreColumn> scoreColumn = this.scoreColumnRepository.findById(Integer.parseInt(params.get("scoreColumnId")));
            Optional<StudentSubject> studentSubject = this.studentSubjectRepository.getStudentSubjectByStudentAndSubjectId(student.get().getId(), subject.get().getId());

            Optional<Score> existingScore = scoreRepo.findByStudentSubjectIdAndSemesterId(
                    studentSubject.get().getId(),
                    semester.get().getId()
            );

                ScoreValue scoreValue = new ScoreValue();
                String scoreValueStr = params.get("scoreValue");
                if (scoreValueStr != null) {
                    scoreValue.setValue(Double.parseDouble(scoreValueStr));
                } else {
                    scoreValue.setValue(0.0);
                }
                scoreValue.setScoreColumnId(scoreColumn.get());
                scoreValue.setScoreId(existingScore.get());

                ScoreValue scoreValue1 = this.scoreValueRepository.save(scoreValue);

                Score_ScoreValueDto scoreValueDto = new Score_ScoreValueDto();

                scoreValueDto.setSubjectId(existingScore.get().getStudentSubjectId().getSubjectId().getId());
                scoreValueDto.setSemesterId(existingScore.get().getSemesterId().getId());
                scoreValueDto.setStudentId(existingScore.get().getStudentSubjectId().getStudentId().getId());
                scoreValueDto.setColumnId(scoreValue1.getScoreColumnId().getId());
                scoreValueDto.setValue(Double.parseDouble(String.valueOf(scoreValue1.getValue())));

                scoreValueDtoList.add(scoreValueDto);

        }

        return scoreValueDtoList;
    }

    @Override
    public List<Score_ScoreValueDto> addScoreByExcel(String subjectId, String semesterId, String studentId, List<ScoreDto> scoreDtoList) {
        List<Score_ScoreValueDto> scoreValueDtoList = new ArrayList<>();
        Optional<Subject> subjectOptional = this.subjectRepository.findById(subjectId);
        Optional<Semester> semesterOptional = this.semesterRepository.findById(semesterId);
        Optional<Student> studentOptional = this.studentRepository.findById(studentId);
        Student student = studentOptional.orElse(null);
        Subject subject = subjectOptional.orElse(null);
        Semester semester = semesterOptional.orElse(null);
        if (student != null && subject != null && semester != null) {
            Optional<StudentSubject> studentSubjectOptional = this.studentSubjectRepository.getStudentSubjectByStudentAndSubjectId(student.getId(), subject.getId());
            if (studentSubjectOptional.isPresent()) {
                StudentSubject studentSubject = studentSubjectOptional.get();
                Optional<Score> existingScore = scoreRepo.findByStudentSubjectIdAndSemesterId(
                        studentSubject.getId(),
                        semester.getId()
                );
                if (existingScore.isPresent()) {
                    for (ScoreDto scoreDto : scoreDtoList) {
                        ScoreValue scoreValueNew = new ScoreValue();
                        double scoreValueStr = scoreDto.getScoreValue();

                        int scoreColumnId = this.scoreColumnRepository.getScoreColumnByName(scoreDto.getScoreColumnName()).getId();
                        Optional<ScoreColumn> scoreColumn = this.scoreColumnRepository.findById(scoreColumnId);

                        scoreValueNew.setValue(scoreValueStr);
                        scoreValueNew.setScoreColumnId(scoreColumn.orElse(null));
                        scoreValueNew.setScoreId(existingScore.get());

                        ScoreValue scoreValue1 = this.scoreValueRepository.save(scoreValueNew);

                        Score_ScoreValueDto scoreValueDto = new Score_ScoreValueDto();

                        scoreValueDto.setSubjectId(existingScore.get().getStudentSubjectId().getSubjectId().getId());
                        scoreValueDto.setSemesterId(existingScore.get().getSemesterId().getId());
                        scoreValueDto.setStudentId(existingScore.get().getStudentSubjectId().getStudentId().getId());
                        scoreValueDto.setColumnId(scoreValue1.getScoreColumnId().getId());
                        scoreValueDto.setValue(Double.parseDouble(String.valueOf(scoreValue1.getValue())));

                        scoreValueDtoList.add(scoreValueDto);
                    }
                } else {
                    // Xử lý trường hợp không tìm thấy điểm của sinh viên cho môn học và kỳ học tương ứng
                }
            } else {
                // Xử lý trường hợp không tìm thấy thông tin sinh viên cho môn học
            }
        } else {
            // Xử lý trường hợp không tìm thấy thông tin về sinh viên, môn học hoặc kỳ học
        }
        return scoreValueDtoList;
    }


    @Override
    public ScoreDto getFinalScoreForSubject(String studentId, String subjectId, String semesterId) {
        List<ScoreDto> scoreDtoList = this.scoreRepo.getScoreByStudentId(studentId, subjectId, semesterId);
        ScorePercentDto scorePercent = this.scorePercentRepository.findAllBySubjectId(subjectId);
        double finalScore = 0.0;
        for(int i = 0; i < scoreDtoList.size(); i++){
            ScoreDto scoreDto = scoreDtoList.get(i);
            if(Objects.equals(scoreDto.getScoreColumnName(), "Giữa kì")) {
                finalScore += scorePercent.getPercentGK()*scoreDto.getScoreValue();
            } else if (Objects.equals(scoreDto.getScoreColumnName(), "Cuối kì")) {
                finalScore += scorePercent.getPercentCK()*scoreDto.getScoreValue();
            }
        }
        finalScore = (double) Math.round(finalScore * 100) / 100;
        ScoreDto finalScoreDto = new ScoreDto("Điểm TK", finalScore);
        return finalScoreDto;
    }

    @Override
    public ScoreDto getAccumulateScoreForSemester(String studentId, String semesterId) {
        List<SubjectDto> subjectInSemester = this.subjectRepository.getSubjectByStudentAndSemesterId(studentId, semesterId);
        int totalCredit = 0;
        double totalScore = 0.0;
        for (int i = 0; i < subjectInSemester.size(); i++) {
            ScoreDto scoreDto = getFinalScoreForSubject(studentId, subjectInSemester.get(i).getId(), semesterId);
            if (scoreDto != null && scoreDto.getScoreValue() != null) {
                totalScore += scoreDto.getScoreValue() * subjectInSemester.get(i).getCredit();
                totalCredit += subjectInSemester.get(i).getCredit();
            }
        }
        if (totalCredit == 0) {
            // Handle the case where totalCredit is 0 to avoid division by zero.
            // You may throw an exception or handle it differently based on your application's requirements.
            return null;
        }
        double accumulateScore = totalScore / totalCredit;

        accumulateScore = (double) Math.round(accumulateScore * 100) / 100;

        ScoreDto accumulateScoreAdd = new ScoreDto("Điểm tích lũy học kỳ", accumulateScore);
        return accumulateScoreAdd;
    }

    @Override
    public ScoreDto getFinalAccumulateScoreForStudent(String studentId) {
        List<SubjectDto> subjects = this.subjectRepository.getSubjectByStudentId(studentId);

        int totalCredit = 0;
        double totalScore = 0.0;
        for (int i = 0; i < subjects.size(); i++) {
            Semester semester = this.semesterRepository.getSemesterBySubjectId(subjects.get(i).getId());
            ScoreDto scoreDto = getFinalScoreForSubject(studentId, subjects.get(i).getId(), semester.getId());
            if (scoreDto != null && scoreDto.getScoreValue() != null) {
                totalScore += scoreDto.getScoreValue() * subjects.get(i).getCredit();
                totalCredit += subjects.get(i).getCredit();
            }
        }
        if (totalCredit == 0) {
            // Handle the case where totalCredit is 0 to avoid division by zero.
            // You may throw an exception or handle it differently based on your application's requirements.
            return null;
        }
        double accumulateScore = totalScore / totalCredit;

        accumulateScore = (double) Math.round(accumulateScore * 100) / 100;

        ScoreDto accumulateScoreAdd = new ScoreDto("Điểm tích lũy tổng kết", accumulateScore);

        return accumulateScoreAdd;
    }


}
