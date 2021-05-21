package com.wesmart.entities;

import java.util.Date;

public class Seller {

	private Integer id;
	private String name;
	private String nit;
	private String merchantCategoryCode;
	private String phone;
	private String email;
	private String address;
	private Date creationDate;
	private Date modificationDate;

	public Seller() {

	}

	public Seller(final Integer id, final String name, final String nit, final String merchantCategoryCode, final String phone,
				  final String email, final String address,
				  final Date creationDate, final Date modificationDate) {

		this.id = id;
		this.name = name;
		this.nit = nit;
		this.merchantCategoryCode = merchantCategoryCode;
		this.phone = phone;
		this.email = email;
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

	public String getName() {

		return name;
	}

	public void setName(final String name) {

		this.name = name;
	}

	public String getNit() {

		return nit;
	}

	public void setNit(final String nit) {

		this.nit = nit;
	}

	public String getMerchantCategoryCode() {

		return merchantCategoryCode;
	}

	public void setMerchantCategoryCode(final String merchantCategoryCode) {

		this.merchantCategoryCode = merchantCategoryCode;
	}

	public String getPhone() {

		return phone;
	}

	public void setPhone(final String phone) {

		this.phone = phone;
	}

	public String getEmail() {

		return email;
	}

	public void setEmail(final String email) {

		this.email = email;
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
