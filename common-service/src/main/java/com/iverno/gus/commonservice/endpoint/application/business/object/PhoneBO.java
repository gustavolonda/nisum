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
public class PhoneBO {
	private String id;
	private String number;
	private String cityCode;
	private String contryCode;
}
