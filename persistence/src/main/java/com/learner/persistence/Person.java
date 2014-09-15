package com.learner.persistence;

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
