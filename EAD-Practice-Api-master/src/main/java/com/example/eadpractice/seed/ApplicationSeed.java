package com.example.eadpractice.seed;

import com.example.eadpractice.entity.Product;
import com.example.eadpractice.entity.Sale;
import com.example.eadpractice.repository.ProductRepository;
import com.example.eadpractice.repository.SaleRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
//@RequiredArgsConstructor
public class ApplicationSeed implements CommandLineRunner {
    boolean seed = false;
    final ProductRepository productRepository;
    final SaleRepository saleRepository;
    Faker faker;
    Random random = new Random();

    public ApplicationSeed(ProductRepository productRepository, SaleRepository saleRepository) {
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
        this.faker = new Faker();
    }


    @Override
    public void run(String... args) throws Exception {
        if (seed) {
            productRepository.deleteAll();
            saleRepository.deleteAll();
            seedProduct();
            seedSale();
        }
    }

    private void seedProduct() {
        List<Product> productList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Product product = new Product();
            product.setName(faker.name().name());
            product.setDescription(faker.lorem().sentence());
            product.setDateOfManf(LocalDateTime.now());
            product.setPrice(1000);
            productList.add(product);
        }
        productRepository.saveAll(productList);
    }

    private void seedSale() {
        List<Sale> saleList = new ArrayList<>();
        List<Product> productList = productRepository.findAll();
        for (int i = 0; i < 50; i++) {
            Sale sale = new Sale();
            Product product = productList.get(random.nextInt(productList.size()));
            sale.setSalesmanId(UUID.randomUUID().toString());
            sale.setProductId(product);
            sale.setSalesmanName(faker.name().firstName());
            sale.setDOS(faker.name().fullName());
            saleList.add(sale);
        }
        saleRepository.saveAll(saleList);
    }

    public static void main(String[] args) {

    }
}
