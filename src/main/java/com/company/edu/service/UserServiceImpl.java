package com.company.edu.service;

import com.company.edu.dto.PersonDto;
import com.company.edu.model.Person;
import com.company.edu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonDto> getUsers() {
        return userRepository.findAll().stream().map(Person::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public boolean doesUserExist(Long userId) {
        return userRepository.existsById(userId);
    }
}
