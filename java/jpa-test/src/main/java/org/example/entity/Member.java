package org.example.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Member {

	@Id
	private Long id;

	private String name;

	private int age;

	public Member(long id, String name, int age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}

	protected Member() {
	}

	// Getter and Setter


	public Long getId() {
		return id;
	}
}
