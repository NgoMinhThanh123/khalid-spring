package com.nmt.universitysb.formatter;

import com.nmt.universitysb.model.Subject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubjectConverter implements Converter<Subject, String> {
    @Override
    public String convert(Subject subject) {
        return String.valueOf(subject.getId());
    }
}
