package com.learner.persistence.entities;

import javax.annotation.Nonnull;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;


	public Person(@Nonnull final String firstName, @Nonnull final String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Person() {
	}

	public long getId() {
		return id;
	}

	@Nonnull
	public String getFirstName() {
		return firstName;
	}

	@Nonnull
	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}
}
