package edu.wixze.com.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Admin {
    private Integer id;

    private String name;
    private String username;
    private String password;
    private String role;
}
