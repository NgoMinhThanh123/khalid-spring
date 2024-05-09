package com.nmt.universitysb.formatter;

import com.nmt.universitysb.model.StudentSubject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StudentSubjectConverter implements Converter<StudentSubject, String> {
    @Override
    public String convert(StudentSubject studentSubject) {
        return String.valueOf(studentSubject.getId());
    }
}
