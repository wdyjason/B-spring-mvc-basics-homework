package com.thoughtworks.capacity.gtb.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;

    @NotNull
    @Size(min = 3, max = 10)
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,10}$")
    private String username;

    @NotNull
    @Size(min = 5, max = 12)
    private String password;

    @Email
    private String email;
}
