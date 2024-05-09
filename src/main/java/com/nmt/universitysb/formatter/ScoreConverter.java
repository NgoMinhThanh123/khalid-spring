package com.nmt.universitysb.formatter;

import com.nmt.universitysb.model.Score;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScoreConverter implements Converter<Score, String> {
    @Override
    public String convert(Score score) {
        return String.valueOf(score.getId());
    }
}
