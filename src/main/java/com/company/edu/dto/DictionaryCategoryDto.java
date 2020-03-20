package com.company.edu.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class DictionaryCategoryDto {

    private Long id;
    private String name;
}
