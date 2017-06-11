package org.catalog.domain;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Moorka on 11.06.2017.
 */
//@Entity
@ResponseBody
public class PriceSelection {


    @ApiModelProperty(notes = "Product name")
    String name;
    @ApiModelProperty(notes = "Count of product")
    long count;

    public PriceSelection() {
        super();
    }

    public PriceSelection(String name, long count) {
        super();
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }
}
