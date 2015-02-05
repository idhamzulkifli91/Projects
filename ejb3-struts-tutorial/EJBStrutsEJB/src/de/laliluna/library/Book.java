/**
 * 
 * @author Sebastian Hennebrueder
 * created Feb 27, 2006
 * copyright 2006 by http://www.laliluna.de
 */
package de.laliluna.library;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author hennebrueder
 * 
 */
@Entity
@Table(name = "book")
@SequenceGenerator(name = "book_sequence", sequenceName = "book_id_seq")
public class Book implements Serializable {
	private Integer id;

	private String title;

	private String author;

	private Customer customer;

	public Book() {
		super();
	}

	public Book(Integer id, String title, String author) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
	}

	@ManyToOne
	@JoinColumn(name = "customer_id")
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_sequence")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {

		return "Book: " + getId() + " Title " + getTitle() + " Author "
				+ getAuthor();
	}

}
