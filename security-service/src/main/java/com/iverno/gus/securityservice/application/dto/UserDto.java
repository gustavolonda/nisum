package com.iverno.gus.securityservice.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	private String id;
    private String email;
    private String password;
    private String role;
}
