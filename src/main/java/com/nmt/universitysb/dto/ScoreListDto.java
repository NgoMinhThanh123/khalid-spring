/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.universitysb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoreListDto {
    private String subjectId;
    private String subjectName;
    private int credit;
    private String semesterName;
    private String schoolYear;
    private Date fromDate;
    private Date toDate;
    private List<ScoreDto> scoreDto;
}
