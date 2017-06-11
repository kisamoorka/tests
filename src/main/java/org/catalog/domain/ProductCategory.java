package org.catalog.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Moorka on 09.06.2017.
 */
@Entity
public  class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "ProductCategory ID")
    private long id;
    @ApiModelProperty(notes = "ProductCategory name")
    private String name;

    @OneToMany
    private List<Product> products;

    public ProductCategory(){
        super();
    }

    public ProductCategory(String name, List<Product> products){
        this.name = name;
        this.products = products;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean hasProduct(Product product) {
        for (Product itProduct: getProducts()) {
            if (itProduct.getId() == product.getId()) {
                return true;
            }
        }
        return false;
    }

}
