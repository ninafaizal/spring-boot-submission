package com.demo.util.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exceptions.DemoAppException;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class TestController {

	@GetMapping("/")
	public String hello() {
		throw new DemoAppException("test exception");
	}

}