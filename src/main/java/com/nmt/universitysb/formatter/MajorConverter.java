package com.nmt.universitysb.formatter;

import com.nmt.universitysb.model.Major;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MajorConverter implements Converter<Major, String> {
    @Override
    public String convert(Major major) {
        return String.valueOf(major.getId());
    }
}
