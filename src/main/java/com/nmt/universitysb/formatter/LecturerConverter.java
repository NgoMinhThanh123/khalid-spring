package com.nmt.universitysb.formatter;

import com.nmt.universitysb.model.Lecturer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class LecturerConverter implements Converter<Lecturer, String> {

    @Override
    public String convert(Lecturer lecturer) {
        return String.valueOf(lecturer.getId());
    }
}