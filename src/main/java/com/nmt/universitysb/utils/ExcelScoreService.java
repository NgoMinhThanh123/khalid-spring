package com.nmt.universitysb.utils;

import com.nmt.universitysb.dto.ScoreDto;
import com.nmt.universitysb.dto.Score_ScoreValueDto;
import com.nmt.universitysb.dto.SubjectDto;
import com.nmt.universitysb.model.*;
import com.nmt.universitysb.repository.*;
import com.nmt.universitysb.service.ScoreService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class ExcelScoreService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private ScoreColumnRepository scoreColumnRepository;

    public List<Score_ScoreValueDto> readScoreFromExcelFile(File file) {
        List<Score_ScoreValueDto> scoreScoreValueDtos = new ArrayList<>();
        try {
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            // Trích xuất thông tin từ hàng thứ hai để gán cứng cho subject và semester
            Row infoRow = sheet.getRow(1);
            String subjectName = infoRow.getCell(1).getStringCellValue();
            SubjectDto subject = subjectRepository.getSubjectByName(subjectName);
            String semesterName = infoRow.getCell(3).getStringCellValue();
            Optional<Semester> semester = semesterRepository.findById(semesterName);

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                if (currentRow.getRowNum() == 0 || currentRow.getRowNum() == 1 || currentRow.getRowNum() == 2) { // Bỏ qua dòng tiêu đề
                    continue;
                }


               String studentId = currentRow.getCell(0).getStringCellValue();
                Cell qtCell = currentRow.getCell(4);
                Cell gkCell = currentRow.getCell(5);
                Cell ckCell = currentRow.getCell(6);

                double scoreValueQT = 0.0;
                double scoreValueGK = 0.0;
                double scoreValueCK = 0.0;

                if (qtCell == null || qtCell.getCellType() == CellType.BLANK) {
                    scoreValueQT = 0.0;
                }else {
                    scoreValueQT = qtCell.getNumericCellValue();
                }
                if (gkCell == null || gkCell.getCellType() == CellType.BLANK ) {
                    scoreValueGK = 0.0;
                }else {
                    scoreValueGK = gkCell.getNumericCellValue();
                }
                if (ckCell == null || ckCell.getCellType() == CellType.BLANK) {
                    scoreValueCK = 0.0;
                }else {
                    scoreValueCK = ckCell.getNumericCellValue();
                }


               List<ScoreDto> scoreDtoList = new ArrayList<>();
                if (scoreValueQT != 0.0) {
                    ScoreDto scoreDtoGK = new ScoreDto("Quá trình", scoreValueQT);
                    scoreDtoList.add(scoreDtoGK);
                }
                if (scoreValueGK != 0.0) {
                    ScoreDto scoreDtoGK = new ScoreDto("Giữa kì", scoreValueGK);
                    scoreDtoList.add(scoreDtoGK);
                }
                if (scoreValueCK != 0.0) {
                    ScoreDto scoreDtoCK = new ScoreDto("Cuối kì", scoreValueCK);
                    scoreDtoList.add(scoreDtoCK);
                }

                scoreScoreValueDtos = this.scoreService.addScoreByExcel(subject.getId(), semester.get().getId(), studentId, scoreDtoList);

            }

            workbook.close();
            excelFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreScoreValueDtos;
    }
}
