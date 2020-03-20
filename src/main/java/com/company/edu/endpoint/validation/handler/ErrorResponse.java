package com.company.edu.endpoint.validation.handler;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Setter
public class ErrorResponse {

    private int status;
    private String message;
}
