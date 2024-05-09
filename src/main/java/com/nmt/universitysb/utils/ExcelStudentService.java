package com.nmt.universitysb.utils;

import com.nmt.universitysb.model.*;
import com.nmt.universitysb.repository.*;
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
public class ExcelStudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ClassesRepository classesRepository;
    @Autowired
    private FacultyRepository facultyRepository;
    @Autowired
    private MajorRepository majorRepository;

    public List<Student> readStudentsFromExcelFile(File file) {
        List<Student> students = new ArrayList<>();
        try {
            FileInputStream excelFile = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(excelFile);
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                if (currentRow.getRowNum() == 0) { // Bỏ qua dòng tiêu đề
                    continue;
                }

                Student student = new Student();

                Cell cellIdent = currentRow.getCell(4);
                String studentId, phone, identification;
                if (cellIdent.getCellType() == CellType.NUMERIC) {
                    // Nếu ô chứa số, chuyển đổi sang chuỗi
                    identification = String.valueOf((int) cellIdent.getNumericCellValue());
                } else {
                    // Nếu không phải kiểu số, sử dụng getStringCellValue() bình thường
                    identification = cellIdent.getStringCellValue();
                }
                boolean gender = currentRow.getCell(3).getNumericCellValue() == 1;
                Optional<Classes> classes = classesRepository.findById(currentRow.getCell(8).getStringCellValue());
                Optional<Faculty> faculty = facultyRepository.findById(currentRow.getCell(9).getStringCellValue());
                Optional<Major> major = majorRepository.findById(currentRow.getCell(10).getStringCellValue());

                student.setId(currentRow.getCell(0).getStringCellValue());
                student.setName(currentRow.getCell(1).getStringCellValue());
                student.setBirthday(currentRow.getCell(2).getDateCellValue());
                student.setGender(gender);
                student.setIdentification(identification);
                student.setPhone(currentRow.getCell(5).getStringCellValue());
                student.setAddress(currentRow.getCell(6).getStringCellValue());
                student.setUserId(null);
                student.setClassesId(classes.get());
                student.setFacultyId(faculty.get());
                student.setMajorId(major.get());

                students.add(student);
            }

            workbook.close();
            excelFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
