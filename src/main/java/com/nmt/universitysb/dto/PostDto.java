package com.nmt.universitysb.dto;

import com.nmt.universitysb.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PostDto {
    private Integer id;
    private String title;
    private String content;
    private Date postTime;
    private User user;
}
