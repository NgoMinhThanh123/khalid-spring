package com.nmt.universitysb.formatter;

import com.nmt.universitysb.model.Classes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClassesConverter implements Converter<Classes, String> {
    @Override
    public String convert(Classes classes) {
        return String.valueOf(classes.getId());
    }
}
