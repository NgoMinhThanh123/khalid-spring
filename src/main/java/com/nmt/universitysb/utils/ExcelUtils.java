package com.nmt.universitysb.utils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.nmt.universitysb.dto.StudentScoreDTO;

import java.io.*;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExcelUtils {
    public void exportToCustomExcel(List<StudentScoreDTO> studentScores, String outputFilePath) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Student Scores"); // Tạo sheet mới

            // Tạo hàng đầu tiên với các tiêu đề cột Subject Name - Semester Name, School Year, Student ID, Student Name, Quá trình, Giữa kì, Cuối kì
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Subject Name - Semester Name - School Year");
            headerRow.createCell(1).setCellValue("Student ID");
            headerRow.createCell(2).setCellValue("Student Name");
            headerRow.createCell(3).setCellValue("Quá trình");
            headerRow.createCell(4).setCellValue("Giữa kì");
            headerRow.createCell(5).setCellValue("Cuối kì");

            // Sắp xếp danh sách studentScores theo sinh viên
            Collections.sort(studentScores, Comparator.comparing(StudentScoreDTO::getStudentId));

            int rowNum = 1;
            String currentStudentId = null;
            Row currentRowObj = null;

            for (StudentScoreDTO studentScore : studentScores) {
                String studentId = studentScore.getStudentId();
                if (!studentId.equals(currentStudentId)) {
                    currentRowObj = sheet.createRow(rowNum++);
                    currentStudentId = studentId;

                    // Điền thông tin chung cho sinh viên mới (Subject Name - Semester Name, School Year, Student ID, Student Name)
                    String subjectSemesterSchoolyear = studentScore.getSubjectName() + " - " + studentScore.getSemesterName() + " - " + studentScore.getSchoolYear();
                    currentRowObj.createCell(0).setCellValue(subjectSemesterSchoolyear);
                    currentRowObj.createCell(1).setCellValue(studentScore.getStudentId());
                    currentRowObj.createCell(2).setCellValue(studentScore.getStudentName());
                }

                // Điền thông tin cụ thể cho sinh viên (Quá trình, Giữa kì, Cuối kì)
                if ("Quá trình".equals(studentScore.getScoreColumnName())) {
                    currentRowObj.createCell(3).setCellValue(studentScore.getScoreValue());
                } else if ("Giữa kì".equals(studentScore.getScoreColumnName())) {
                    currentRowObj.createCell(4).setCellValue(studentScore.getScoreValue());
                } else if ("Cuối kì".equals(studentScore.getScoreColumnName())) {
                    currentRowObj.createCell(5).setCellValue(studentScore.getScoreValue());
                }
            }

            try (OutputStream outputStream = new FileOutputStream(outputFilePath)) {
                workbook.write(outputStream);
            }
        }
    }
}