package org.catalog;

import org.catalog.domain.Product;
import org.catalog.domain.ProductCategory;
import org.catalog.repositories.CategoryRepository;
import org.catalog.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;


@SpringBootApplication
public class Application implements CommandLineRunner {


	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	public void run(String ... args) throws Exception {

		Product mazda3 = new Product("mazda 3", 1450000);
		Product fordf = new Product("ford focus", 780000);
		Product nissana = new Product("nissan almera", 495000);
		Product audi7 = new Product("mazda 3", 3320000);

		productRepository.save(mazda3);
		productRepository.save(fordf);
		productRepository.save(nissana);
		productRepository.save(audi7);

		ProductCategory cars= new ProductCategory("cars", Arrays.asList( new Product[] {mazda3, audi7, nissana, fordf }));
		categoryRepository.save(cars);

		Product iphone7 = new Product("iphone 7", 69000);
		Product xiaomimi5 = new Product("xiaomi mi5", 24000);
		Product meizu2 = new Product("meizu 2 mini", 9000);
		Product samsung = new Product("samsung galaxy note 3", 45000);

		productRepository.save(iphone7);
		productRepository.save(xiaomimi5);
		productRepository.save(meizu2);
		productRepository.save(samsung);

		ProductCategory phones = new ProductCategory("phones", Arrays.asList( new Product[] {xiaomimi5, meizu2, iphone7, samsung }));
		categoryRepository.save(phones);


		ProductCategory fruts = new ProductCategory("fruts", Arrays.asList( new Product[] {}));
		categoryRepository.save(fruts);

	}

	public static void main(String[] args) {


		SpringApplication.run(Application.class, args);
	}
}
