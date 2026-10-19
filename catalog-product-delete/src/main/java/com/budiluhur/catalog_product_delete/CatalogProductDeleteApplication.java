
package com.budiluhur.catalog_product_delete;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.budiluhur.catalog_product_delete.exception.ProductNotFoundException;
import com.budiluhur.catalog_product_delete.model.Product;
import com.budiluhur.catalog_product_delete.service.ProductService;

@SpringBootApplication
public class CatalogProductDeleteApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                CatalogProductDeleteApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ProductService productService) {
        return args -> {
            System.out.println("== SPRING BOOT PRODUCT CATALOG IS RUNNING ==");

            productService.createProduct(new Product("PRD-03","Monitor Gaming 24 Inch", 2100000.0));

            System.out.println("Daftar Produk di Spring Container:");

            productService.getAllProducts()
                    .forEach(System.out::println);
			System.out.println();
          
            System.out.println("== INFORMASI HARGA & DISKON ==");

            try {

                Product keyboard = productService.getProductById("PRD-01");

                double diskonKeyboard = 10.0;

                double hargaKeyboard = productService.getDiscountedProductPrice( "PRD-01", diskonKeyboard);

                System.out.println("[PRD-01] Keyboard Mechanical");
                System.out.printf("- Harga Normal : Rp%,.2f%n", keyboard.getPrice());
                System.out.printf("- Diskon : %.1f%% (Ada Diskon)%n", diskonKeyboard);
                System.out.printf("- Harga Akhir : Rp%,.2f%n", hargaKeyboard);
				System.out.println();
				
                Product mouse = productService.getProductById("PRD-02");

                double diskonMouse = 0.0;

                double hargaMouse = productService.getDiscountedProductPrice( "PRD-02", diskonMouse);

                System.out.println("[PRD-02] Mouse Wireless Silent");
                System.out.printf("- Harga Normal : Rp%,.2f%n", mouse.getPrice());
                System.out.printf("- Diskon : %.0f%% (Tidak Ada Diskon)%n", diskonMouse);
                System.out.printf("- Harga Akhir : Rp%,.2f%n",  hargaMouse);
				 System.out.println();

                Product monitor = productService.getProductById("PRD-03");

                double diskonMonitor = 15.0;

                double hargaMonitor = productService.getDiscountedProductPrice( "PRD-03", diskonMonitor);

                System.out.println("[PRD-03] Monitor Gaming 24 Inch");
                System.out.printf("- Harga Normal : Rp%,.2f%n", monitor.getPrice());
                System.out.printf("- Diskon : %.1f%% (Ada Diskon)%n", diskonMonitor);
                System.out.printf("- Harga Akhir : Rp%,.2f%n", hargaMonitor);
                System.out.println();

            } catch (ProductNotFoundException e) { System.out.println(e.getMessage()); }

        };
    }
}
