package org.catalog.domain;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Moorka on 11.06.2017.
 */
@Entity
public class PriceSelection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(notes = "PriceSelection ID")
    private long id;

    @ApiModelProperty(notes = "Product name")
    String name;
    @ApiModelProperty(notes = "Count of product")
    int count;

    public PriceSelection() {
        super();
    }

    public PriceSelection(String name, int count) {
        super();
        this.name = name;
        this.count = count;
    }
}
