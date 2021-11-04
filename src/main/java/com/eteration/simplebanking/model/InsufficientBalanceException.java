package com.eteration.simplebanking.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// This class is a place holder you can change the complete implementation
@AllArgsConstructor
public class InsufficientBalanceException extends Exception {

    @Getter
    @Setter
    private String message;
}
