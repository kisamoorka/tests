package org.catalog.domain;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Moorka on 09.06.2017.
 */
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "Product ID")
    private long id;
    @ApiModelProperty(notes = "Product name")
    private String name;
    @ApiModelProperty(notes = "Product price")
    private int price;

    public Product() {
        super();
    }

    public Product(String name, int price) {
        super();
        this.name = name;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


