package com.learner.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.learner.persistence.configuration.CrudService;
import com.learner.persistence.entities.Person;
import com.learner.persistence.harness.JpaRule;

public class PersonTest {

	@Rule
	public JpaRule jpaRule = new JpaRule("unit-testing-pu");
	private CrudService crudService;

	@Before
	public void setUp() {
		crudService = jpaRule.createCrudService();
	}

	@Test
	public void testAddPerson() {
		final Person person;
		{
			person = new Person("Tony", "Stark");
			crudService.create(person);
			jpaRule.changeTransaction();
		}
		{
			final Person personInDb = crudService.find(person.getId(), Person.class);
			assertNotNull(personInDb);
			assertEquals(person, personInDb);
		}

	}

	@Test
	public void testDeletePerson() {
		final Person person;
		{
			person = new Person("Tony", "Stark");
			crudService.create(person);
			jpaRule.changeTransaction();
		}

		{
			final Person personInDb = crudService.find(person.getId(), Person.class);
			assertNotNull(personInDb);
			assertEquals(person, personInDb);
		}

		crudService.delete(person);

		{
			final Person personInDb = crudService.find(person.getId(), Person.class);
			assertNull(personInDb);
		}
	}

	@Test
	public void testGetPersons() {
		final Person person1;
		{
			person1 = new Person("Tony", "Stark");
			crudService.create(person1);
			jpaRule.changeTransaction();
		}

		final Person person2;
		{
			person2 = new Person("Tony", "Stark");
			crudService.create(person2);
			jpaRule.changeTransaction();
		}

		final List<Person> persons = new PersonQueries(crudService).getPersons();
		assertEquals(2, persons.size());
		assertEquals(person1, persons.get(0));
		assertEquals(person2, persons.get(1));
	}
}
