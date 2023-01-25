package com.abhishek.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "categories")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Size(min = 2, message = "Name must be 2 character long")
	private String name;
	private String slug;
	private int sorting;	

	public Category() {
	}

	public Category(int id, String name, String slug, int sorting) {
		this.id = id;
		this.name = name;
		this.slug = slug;
		this.sorting = sorting;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public int getSorting() {
		return sorting;
	}

	public void setSorting(int sorting) {
		this.sorting = sorting;
	}

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", slug=" + slug + ", sorting=" + sorting + "]";
	}
}
