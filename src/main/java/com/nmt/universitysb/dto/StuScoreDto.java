/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.universitysb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class StuScoreDto {
    private String studentId;
    private String studentName;
    private Date studentBirthday;
    private List<ScoreDto> scoreDto;
}
