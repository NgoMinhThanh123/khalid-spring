package com.nmt.universitysb.formatter;

import com.nmt.universitysb.model.Faculty;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FacultyConverter implements Converter<Faculty, String> {

    @Override
    public String convert(Faculty faculty) {
        return String.valueOf(faculty.getId());
    }
}