package com.learner.presentation;

public class PersonPresentation {
	private String firstName;
	private String lastName;

	@SuppressWarnings("UnusedDeclaration")
	public PersonPresentation() {
	}

	public PersonPresentation(final String firstName, final String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
