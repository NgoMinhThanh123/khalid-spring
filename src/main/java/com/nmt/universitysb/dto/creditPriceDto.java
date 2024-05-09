package com.nmt.universitysb.dto;

import com.nmt.universitysb.model.Major;
import com.nmt.universitysb.model.Semester;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class creditPriceDto {
    private double price;
    private Major majorId;
    private Semester semesterId;
}
