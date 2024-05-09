package com.nmt.universitysb.dto;

import com.nmt.universitysb.model.Faculty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzDto {
    private String id;
    private Faculty facultyId;
}
