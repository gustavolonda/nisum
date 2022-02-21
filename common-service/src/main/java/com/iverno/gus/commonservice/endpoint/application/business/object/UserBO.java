package com.iverno.gus.commonservice.endpoint.application.business.object;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBO {
	private String id;
	private String name;
	private String password;
	private String email;
	private String role;
    private List<PhoneBO> phones ;
}
