package com.eteration.simplebanking.model;


// This class is a place holder you can change the complete implementation

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionStatus {
    private String status;
    private String approvalCode;
}
