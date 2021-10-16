package by.ita.je.model;

import javax.validation.constraints.NotBlank;

public class User {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String matchingPassword;
    @NotBlank
    private String email;
}
