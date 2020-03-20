package com.company.edu.model;

import com.company.edu.dto.PersonDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@NoArgsConstructor
public abstract class Person extends AbstractEntity {

    private String name;

    private String surname;

    public PersonDto toDto() {
        return new PersonDto(this.getId(), this.name, this.surname);
    }
}
