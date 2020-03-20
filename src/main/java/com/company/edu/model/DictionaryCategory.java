package com.company.edu.model;

import com.company.edu.dto.DictionaryCategoryDto;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "DICTIONARY_CATEGORY")
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class DictionaryCategory extends AbstractEntity {

    private String name;

    public DictionaryCategoryDto toDto() {
        return new DictionaryCategoryDto(this.getId(), this.name);
    }
}
