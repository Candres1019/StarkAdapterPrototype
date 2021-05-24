package com.fake.stark.acquirer.entities;

public class User {

	private Integer identification;
	private String name;
	private String phone;
	private String address;
	private String email;

	public User() {

	}

	public User(final Integer identification, final String name, final String phone, final String address, final String email) {

		this.identification = identification;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
	}

	public Integer getIdentification() {

		return identification;
	}

	public void setIdentification(final Integer identification) {

		this.identification = identification;
	}

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getPhone() {

		return phone;
	}

	public void setPhone(final String phone) {

		this.phone = phone;
	}

	public String getAddress() {

		return address;
	}

	public void setAddress(final String address) {

		this.address = address;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(final String email) {

		this.email = email;
	}
}
