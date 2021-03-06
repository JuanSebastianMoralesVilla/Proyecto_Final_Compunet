package com.taller1SM.model.sales;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * The persistent class for the store database table.
 *
 */
@Entity
@NamedQuery(name = "Store.findAll", query = "SELECT s FROM Store s")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "STORE_BUSINESSENTITYID_GENERATOR", allocationSize = 1, sequenceName = "STORE_SEQ")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STORE_BUSINESSENTITYID_GENERATOR")
	private Integer businessentityid;

	@NotNull
	@Size(min = 3)
	private String demographics;

	@FutureOrPresent
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate modifieddate;
	@NotNull
	@Size(min = 5)
	private String name;
    
	
	@Min(1)
	private Integer rowguid;

	// bi-directional many-to-one association to Customer
	@OneToMany(mappedBy = "store")
	@JsonIgnore
	private List<Customer> customers;

	// bi-directional many-to-one association to Salesperson
	@ManyToOne
	@JoinColumn(name = "salespersonid")
	private Salesperson salesperson;

	public Store() {
	}

	public Customer addCustomer(Customer customer) {
		getCustomers().add(customer);
		customer.setStore(this);

		return customer;
	}

	public Integer getBusinessentityid() {
		return this.businessentityid;
	}

	public List<Customer> getCustomers() {
		return this.customers;
	}

	public String getDemographics() {
		return this.demographics;
	}

	public LocalDate getModifieddate() {
		return this.modifieddate;
	}

	public String getName() {
		return this.name;
	}

	public Integer getRowguid() {
		return this.rowguid;
	}

	public Salesperson getSalesperson() {
		return this.salesperson;
	}

	public Customer removeCustomer(Customer customer) {
		getCustomers().remove(customer);
		customer.setStore(null);

		return customer;
	}

	public void setBusinessentityid(Integer businessentityid) {
		this.businessentityid = businessentityid;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public void setDemographics(String demographics) {
		this.demographics = demographics;
	}

	public void setModifieddate(LocalDate modifieddate) {
		this.modifieddate = modifieddate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRowguid(Integer rowguid) {
		this.rowguid = rowguid;
	}

	public void setSalesperson(Salesperson salesperson) {
		this.salesperson = salesperson;
	}

}