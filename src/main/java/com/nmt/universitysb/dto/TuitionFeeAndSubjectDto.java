package com.nmt.universitysb.dto;

import com.nmt.universitysb.model.Subject;
import com.nmt.universitysb.model.TuitionFee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TuitionFeeAndSubjectDto {
    private Subject subjectId;
    private double price;
}
