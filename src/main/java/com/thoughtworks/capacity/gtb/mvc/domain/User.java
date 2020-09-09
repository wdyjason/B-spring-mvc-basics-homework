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

    @NotNull(message = "username can not be null")
    @Size(min = 3, max = 10, message = "username length should between 3 and 10")
    @Pattern(regexp = "^[a-zA-Z0-9_]{3,10}$", message ="username should include `a-z A-Z 0-9 _` only")
    private String username;

    @NotNull(message = "password can not be null")
    @Size(min = 5, max = 12, message = "password length should between 5 and 12")
    private String password;

    @Email(message = "email pattern is incorrect")
    private String email;
}
