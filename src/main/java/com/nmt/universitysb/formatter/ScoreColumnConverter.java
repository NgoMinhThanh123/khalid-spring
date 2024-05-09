package com.nmt.universitysb.formatter;

import com.nmt.universitysb.model.ScoreColumn;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScoreColumnConverter implements Converter<ScoreColumn, String> {
    @Override
    public String convert(ScoreColumn scoreColumn) {
        return String.valueOf(scoreColumn);
    }
}
