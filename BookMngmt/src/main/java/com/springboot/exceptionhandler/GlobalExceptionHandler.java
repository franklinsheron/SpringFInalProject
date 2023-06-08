package com.springboot.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.spring.dto.ResponseDto;
import com.springboot.exception.BadRequestException;

public class GlobalExceptionHandler 
{
	 private final Logger logger= 
		    	LoggerFactory.getLogger(GlobalExceptionHandler.class);

		    @ExceptionHandler({BadRequestException.class})
		    public ResponseEntity<ResponseDto> 
		    		handleBadRequestException(BadRequestException e)
		    {
		      logger.info("Bad Request Found {} ",e.getMsg(),e);
		      return new ResponseEntity<>(
		      	ResponseDto.builder().
		        	responseMsg(e.getMsg()).build(),
					HttpStatus.BAD_REQUEST);
		    }

		    @ExceptionHandler({Exception.class})
		    public ResponseEntity<ResponseDto> 
		    			handleException(Exception e)
		    {
		      logger.info("Unknown error occur {} ",
		      	e.getMessage(),e);
		      
		      return new ResponseEntity<>(
		      		ResponseDto.builder()
		            	.responseMsg(e.getMessage()).build(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		    }
}
