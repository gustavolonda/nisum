package com.iverno.gus.securityservice.application.model;


import java.util.List;

import com.iverno.gus.commonservice.endpoint.application.business.object.PhoneBO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequest {

    private String email;
    private String password;
    private String name;
    private List<PhoneBO> phones;
}