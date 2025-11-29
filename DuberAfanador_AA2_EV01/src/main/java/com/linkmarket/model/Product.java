package com.linkmarket.model;

public class Product {
    private int idProducto;
    private String nombre;
    private double precio;
    private int stock;
    private Integer idUsuario;
    private Integer idCategoria;

    public Product() {}

    public Product(String nombre, double precio, int stock, Integer idUsuario, Integer idCategoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.idUsuario = idUsuario;
        this.idCategoria = idCategoria;
    }

    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public Integer getIdCategoria() { return idCategoria; }
    public void setIdCategoria(Integer idCategoria) { this.idCategoria = idCategoria; }

    @Override
    public String toString() {
        return "Product{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", idUsuario=" + idUsuario +
                ", idCategoria=" + idCategoria +
                '}';
    }
}
