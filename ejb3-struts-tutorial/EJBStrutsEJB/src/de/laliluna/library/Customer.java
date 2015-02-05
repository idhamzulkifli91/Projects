/**
 * 
 * @author Sebastian Hennebrueder
 * created Mar 15, 2006
 * copyright 2006 by http://www.laliluna.de
 */
package de.laliluna.library;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author hennebrueder
 * 
 */
@Entity
@Table(name="customer")
@SequenceGenerator(name="customer_sequence", sequenceName="customer_id_seq")
public class Customer {
	private Integer id;

	private String name;

	private List books =new ArrayList();

	public Customer(){
		super();
	}
	public Customer(Integer id, String name){
		super();
		this.id=id;
		this.name=name;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="customer", targetEntity=Book.class)
	public List getBooks() {
		return books;
	}

	public void setBooks(List books) {
		this.books = books;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_sequence")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Customer: " + getId() + " Name " + getName();
	}

}
