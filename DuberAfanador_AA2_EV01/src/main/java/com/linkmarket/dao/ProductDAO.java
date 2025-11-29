package com.linkmarket.dao;

import com.linkmarket.config.DBConnection;
import com.linkmarket.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public boolean createProduct(Product p) {
        String sql = "INSERT INTO producto (nombre, precio, stock, id_usuario, id_categoria) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            if (p.getIdUsuario() != null) ps.setInt(4, p.getIdUsuario()); else ps.setNull(4, Types.INTEGER);
            if (p.getIdCategoria() != null) ps.setInt(5, p.getIdCategoria()); else ps.setNull(5, Types.INTEGER);
            int affected = ps.executeUpdate();
            if (affected == 0) return false;
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) p.setIdProducto(rs.getInt(1));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT id_producto, nombre, precio, stock, id_usuario, id_categoria FROM producto";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product p = new Product();
                p.setIdProducto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setStock(rs.getInt("stock"));
                int iu = rs.getInt("id_usuario"); if (!rs.wasNull()) p.setIdUsuario(iu);
                int ic = rs.getInt("id_categoria"); if (!rs.wasNull()) p.setIdCategoria(ic);
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public Product getById(int id) {
        String sql = "SELECT id_producto, nombre, precio, stock, id_usuario, id_categoria FROM producto WHERE id_producto = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Product p = new Product();
                    p.setIdProducto(rs.getInt("id_producto"));
                    p.setNombre(rs.getString("nombre"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setStock(rs.getInt("stock"));
                    int iu = rs.getInt("id_usuario"); if (!rs.wasNull()) p.setIdUsuario(iu);
                    int ic = rs.getInt("id_categoria"); if (!rs.wasNull()) p.setIdCategoria(ic);
                    return p;
                }
            }
        } catch (Exception e) { e.printStackTrace(); }
        return null;
    }

    public boolean updateProduct(Product p) {
        String sql = "UPDATE producto SET nombre = ?, precio = ?, stock = ?, id_usuario = ?, id_categoria = ? WHERE id_producto = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, p.getNombre());
            ps.setDouble(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            if (p.getIdUsuario() != null) ps.setInt(4, p.getIdUsuario()); else ps.setNull(4, Types.INTEGER);
            if (p.getIdCategoria() != null) ps.setInt(5, p.getIdCategoria()); else ps.setNull(5, Types.INTEGER);
            ps.setInt(6, p.getIdProducto());
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean deleteProduct(int id) {
        String sql = "DELETE FROM producto WHERE id_producto = ?";
        try (Connection c = DBConnection.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            int affected = ps.executeUpdate();
            return affected > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
}
