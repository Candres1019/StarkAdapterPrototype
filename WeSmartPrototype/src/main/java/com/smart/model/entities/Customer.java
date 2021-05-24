package com.smart.model.entities;

import java.util.Date;

public class Customer {

	private Integer id;
	private String idType;
	private String name;
	private String lastName;
	private String email;
	private String phone;
	private String address;
	private Date creationDate;
	private Date modificationDate;

	public Customer() {

	}

	public Customer(final Integer id, final String idType, final String name, final String lastName, final String email, final String phone,
					final String address,
					final Date creationDate, final Date modificationDate) {

		this.id = id;
		this.idType = idType;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
	}

	public Integer getId() {

		return id;
	}

	public void setId(final Integer id) {

		this.id = id;
	}

	public String getIdType() {

		return idType;
	}

	public void setIdType(final String idType) {

		this.idType = idType;
	}

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getLastName() {

		return lastName;
	}

	public void setLastName(final String lastName) {

		this.lastName = lastName;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(final String email) {

		this.email = email;
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

	public Date getCreationDate() {

		return creationDate;
	}

	public void setCreationDate(final Date creationDate) {

		this.creationDate = creationDate;
	}

	public Date getModificationDate() {

		return modificationDate;
	}

	public void setModificationDate(final Date modificationDate) {

		this.modificationDate = modificationDate;
	}
}
