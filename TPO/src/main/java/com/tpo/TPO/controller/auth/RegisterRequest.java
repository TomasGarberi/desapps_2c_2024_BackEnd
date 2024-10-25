
package com.tpo.TPO.controller.auth;

import com.tpo.TPO.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private Role role;
}
