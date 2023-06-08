package com.spring.dto;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class ResponseDto<T>
{
	 private String responseMsg;

	 private T json;


}
