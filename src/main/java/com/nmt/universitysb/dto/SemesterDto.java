package com.nmt.universitysb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemesterDto {
    private String id;
    private String name;
    private int schoolYear;
}
