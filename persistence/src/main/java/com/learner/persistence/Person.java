package com.learner.persistence;

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
	private int yearOfBirth;
	private int monthOfBirth;
	private int dateOfBirth;

	public Person(@Nonnull final String firstName, @Nonnull final String lastName, final int yearOfBirth, final int monthOfBirth, final int dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.yearOfBirth = yearOfBirth;
		this.monthOfBirth = monthOfBirth;
		this.dateOfBirth = dateOfBirth;
	}

	public Person() {
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", yearOfBirth=" + yearOfBirth +
				", monthOfBirth=" + monthOfBirth +
				", dateOfBirth=" + dateOfBirth +
				'}';
	}
}
