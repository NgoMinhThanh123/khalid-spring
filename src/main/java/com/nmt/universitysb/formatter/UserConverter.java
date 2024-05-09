package com.nmt.universitysb.formatter;

import com.nmt.universitysb.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserConverter implements Converter<User, String> {

    @Override
    public String convert(User user) {
        return String.valueOf(user.getId());
    }
}
