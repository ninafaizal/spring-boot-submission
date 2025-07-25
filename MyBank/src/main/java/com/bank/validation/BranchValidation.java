package com.bank.validation;

import com.demo.exceptions.DemoAppException;

public class BranchValidation {
	
	public static void validateBranchName(String branchName) {
		if (branchName == null || branchName.isBlank()) {
			throw new DemoAppException("Branch name cannot be empty or blank.");
		}
	}

}
