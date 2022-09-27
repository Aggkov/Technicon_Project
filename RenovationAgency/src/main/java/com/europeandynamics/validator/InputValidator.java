package com.europeandynamics.validator;

import java.util.regex.Pattern;

import com.europeandynamics.exceptions.InvalidEmailException;

public class InputValidator {

	public static boolean checkEmail(String email) throws InvalidEmailException {
		if (Pattern.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}" + "~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\."
				+ "[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)" + "+[a-zA-Z]{2,}))$", email.trim())) {
			return true;
		} else {
			throw new InvalidEmailException("Email is not valid");
		}
	}

}
