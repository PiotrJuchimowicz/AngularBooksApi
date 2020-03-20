package com.company.edu.service;

import com.company.edu.dto.PersonDto;

import java.util.List;

public interface UserService {
    List<PersonDto> getUsers();

    boolean doesUserExist(Long userId);
}
