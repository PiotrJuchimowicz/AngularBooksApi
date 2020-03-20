package com.company.edu.dto;

import com.company.edu.endpoint.validation.validator.BookExists;
import com.company.edu.endpoint.validation.validator.UserExists;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class BorrowRequestDto {

    @UserExists
    private Long borrowerId;

    @BookExists
    private Long bookId;
}
