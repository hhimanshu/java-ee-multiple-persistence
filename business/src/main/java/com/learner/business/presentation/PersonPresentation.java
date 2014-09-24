package com.learner.business.presentation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

import com.learner.persistence.entities.Person;

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

	public Person toPerson() {
		return new Person(firstName, lastName);
	}

	public static PersonPresentation toPersonPresentation(@Nonnull final Person person) {
		return new PersonPresentation(person.getFirstName(), person.getLastName());
	}

	@Nonnull
	public static List<PersonPresentation> toPersonPresentations(@Nonnull final List<Person> persons) {
		final List<PersonPresentation> personPresentations = new ArrayList<>();
		for (final Person person : persons) {
			personPresentations.add(new PersonPresentation(person.getFirstName(), person.getLastName()));
		}
		return personPresentations;
	}
}
