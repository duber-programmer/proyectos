package com.linkmarket.app;

import com.linkmarket.dao.ProductDAO;
import com.linkmarket.model.Product;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();

        // Create
        Product p1 = new Product("Auriculares Bluetooth", 89.99, 10, null, null);
        boolean created = dao.createProduct(p1);
        System.out.println("Created: " + created + " -> " + p1);

        // Read All
        List<Product> all = dao.getAll();
        System.out.println("All products:");
        all.forEach(System.out::println);

        // Read by id
        Product found = dao.getById(p1.getIdProducto());
        System.out.println("Found: " + found);

        // Update
        if (found != null) {
            found.setPrecio(79.99);
            found.setStock(8);
            boolean updated = dao.updateProduct(found);
            System.out.println("Updated: " + updated);
        }

        // Optionally Delete
        // boolean deleted = dao.deleteProduct(p1.getIdProducto());
        // System.out.println("Deleted: " + deleted);
    }
}
