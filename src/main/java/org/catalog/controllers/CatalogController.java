package org.catalog.controllers;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.catalog.domain.PriceSelection;
import org.catalog.domain.Product;
import org.catalog.domain.ProductCategory;
import org.catalog.repositories.CategoryRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.catalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Moorka on 09.06.2017.
 */

@RestController
@RequestMapping("/catalog")
@Api(value="name", description="Products catalog controller")
public class CatalogController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    //@ApiOperation(value = "View a list of available products",response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT FOUND")
    }
    )

    @ApiOperation(value = "Search all category")
    @RequestMapping(value = "/categories", method= RequestMethod.GET, produces = "application/json")
    public  @ResponseBody Iterable<ProductCategory> allProductCategories(Model model){
        return categoryRepository.findAll();
    }

    @ApiOperation(value = "Search category by ID",response = ProductCategory.class)
    @RequestMapping(value = "/category/{id}", method= RequestMethod.GET, produces = "application/json")
    public  @ResponseBody ProductCategory categoryById(@PathVariable Long id, Model model){
        return categoryRepository.findOne(id);
    }

    @ApiOperation(value = "Add new category")
    @RequestMapping(value = "/category/add", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody HttpStatus saveCategory(@RequestParam String name, Model model){
        ProductCategory productCategory = new ProductCategory(name, Arrays.asList(new Product[] {}));
        categoryRepository.save(productCategory);
        return HttpStatus.OK;
    }

    @ApiOperation(value = "Delete category by ID")
    @RequestMapping(value = "/category/del", method = RequestMethod.DELETE, produces = "application/json")
    public  @ResponseBody HttpStatus deleteCategory(@RequestParam Long id, Model model){
        if (categoryRepository.findOne(id) != null) {
            categoryRepository.delete(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    @ApiOperation(value = "Search all products")
    @RequestMapping(value = "/products", method= RequestMethod.GET, produces = "application/json")
    public  @ResponseBody Iterable<Product> allProducts(Model model){
        return productRepository.findAll();
    }


    @ApiOperation(value = "Search product by ID",response = ProductCategory.class)
    @RequestMapping(value = "/product/{id}", method= RequestMethod.GET, produces = "application/json")
    public @ResponseBody Product productById(@PathVariable Long id, Model model){
        return productRepository.findOne(id);
    }

    @ApiOperation(value = "Search product by category",response = Iterable.class)
    @RequestMapping(value = "/category/products", method= RequestMethod.GET, produces = "application/json")
    public  @ResponseBody Iterable<Product> productsByCategory(@RequestParam Long id, Model model){

        ProductCategory productCategory = categoryRepository.findOne(id);
        if (productCategory != null) {
            return productCategory.getProducts();
        } else return  Arrays.asList( new Product[] {});
    }

    @ApiOperation(value = "Delete product by ID")
    @RequestMapping(value = "/product/del", method = RequestMethod.DELETE, produces = "application/json")
    public @ResponseBody  HttpStatus deleteProduct(@RequestParam Long id, Model model){
        if (productRepository.findOne(id) != null) {
            productRepository.delete(id);
            return HttpStatus.OK;
        }
        return HttpStatus.NOT_FOUND;
    }

    @ApiOperation(value = "Add new product")
    @RequestMapping(value = "/product/add", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody HttpStatus addProduct(@RequestParam Long categoriId, @RequestParam String name,  @RequestParam int price, Model model){

        ProductCategory productCategory = categoryRepository.findOne(categoriId);
        if ( productCategory != null) {

            Product product = new Product(name, price);
            productRepository.save(product);
            productCategory.addProduct(product);
            categoryRepository.save(productCategory);
            return HttpStatus.OK;
        }

        return HttpStatus.NOT_FOUND;
    }


    @ApiOperation(value = "Search product by category and price",response = Iterable.class)
    @RequestMapping(value = "/category/priceselection", method= RequestMethod.GET, produces = "application/json")
    public  @ResponseBody Iterable<PriceSelection> productsByCategory(@RequestParam  Long categoryId, @RequestParam long price, Model model){

        ProductCategory productCategory = categoryRepository.findOne(categoryId);
        if (productCategory != null) {

            List<PriceSelection> priceSelections = new LinkedList<PriceSelection>();
            List<Product> products = productCategory.getProducts();

            for (Product product: products){
                long count = price/ product.getPrice();
                if (count > 0) {
                    priceSelections.add(new PriceSelection( product.getName(), count));
                }
            }

            return priceSelections;
        } else return  Arrays.asList( new PriceSelection[] {});
    }
}
