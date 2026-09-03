package no.hvl.dat108.f04;

import no.hvl.dat108.f04.Person;

import java.util.stream.IntStream;

import static no.hvl.dat108.f04.People.people;

public class Eksempel8 {
	
	public static void main(String[] args) {

		int sum = 0;
		for (Person p : people) {
			int alder = p.age();
			sum = sum + alder;
		}
		
		/* Summen av aldrene til personene i people-listen */
		// Vi ser på 4 varianter:
		// 1) reduce med +
		int sumAlder = people.stream().map(p -> p.age()).reduce(0, (a, b) -> a + b);

		// 2) reduce med sum
		sumAlder = people.stream().map(p -> p.age()).reduce(0, (a, b) -> Integer.sum(a, b));

		// 3) reduce med sum og metodereferanse
		sumAlder = people.stream().map(p -> p.age()).reduce(0, Integer::sum);

		// 4) IntStream og sum
		sumAlder = people.stream().mapToInt(Person::age).sum();

		System.out.println("Sum alder er " + sumAlder);
		
		/* En streng med alle initialene, "CD LC TC CB MA" - reduce med + */
		// String inits = people.stream().map(p -> p.firstName())

		// System.out.println(inits);

		/* Alle forbokstavene i fornavnene i en streng "CLTCM" - reduce med concat */
		//System.out.println(forboks);

		/* Antall personer over 50 år - count() */
		long antallOver50 = people.stream().filter(p -> p.age() > 50).count();
		System.out.println(antallOver50);

		/* Om vi har data som matcher
			anyMatch(pred), allMatch(pred), noneMatch(pred) */

        //Er alle over 30 år?
		boolean alleOver30 = people.stream().allMatch(p -> p.age() > 30);
		System.out.println(alleOver30);

        //Er noen over 60 år?

		//System.out.println(noenOver60);
	}
}



