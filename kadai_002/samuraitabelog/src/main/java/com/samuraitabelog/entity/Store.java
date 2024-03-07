package com.samuraitabelog.entity;

import java.sql.Time;
import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "stores")
@Data
public class Store {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "description")
    private String description;

    @Column(name = "lowest_price")
    private Integer lowestPrice;
    
    @Column(name = "highest_price")
    private Integer highestPrice;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "address")
    private String address;

    @Column(name = "opening_time")
	private Time openingTime;
    
    @Column(name = "closing_time")
	private Time closingTime;
    
    @Column(name = "regular_holidays")
    private String regularHolidays;
    
    @Column(name = "seating_capacity")
    private Integer seatingCapacity;
    
    @ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

    @Column(name = "created_at", insertable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private Timestamp updatedAt;

}
