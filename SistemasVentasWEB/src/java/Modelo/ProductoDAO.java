package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    // Listar todos los productos
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM producto";  // Asegúrate de que la consulta SQL esté correcta
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId(rs.getInt("IdProducto"));
                p.setNom(rs.getString("Nombres"));
                p.setPrecio(rs.getInt("Precio"));
                p.setStock(rs.getInt("Stock"));
                p.setEstado(rs.getString("Estado"));
                lista.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();  // Para detectar cualquier error en la ejecución
        }
        return lista;
    }

    // Agregar un nuevo producto
    public void agregar(Producto prod) {
        String sql = "INSERT INTO producto(Nombres, Precio, Stock, Estado) VALUES(?, ?, ?, ?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, prod.getNom());
            ps.setInt(2, prod.getPrecio());
            ps.setInt(3, prod.getStock());
            ps.setString(4, prod.getEstado());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();  // Verifica si hay errores al insertar el producto
        }
    }

    // Obtener un producto por ID
    public Producto listarId(int id) {
        Producto p = new Producto();
        String sql = "SELECT * FROM producto WHERE IdProducto = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                p.setId(rs.getInt("IdProducto"));
                p.setNom(rs.getString("Nombres"));
                p.setPrecio(rs.getInt("Precio"));
                p.setStock(rs.getInt("Stock"));
                p.setEstado(rs.getString("Estado"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return p;
    }

    // Actualizar un producto
    public void actualizar(Producto p) {
        String sql = "UPDATE producto SET Nombres = ?, Precio = ?, Stock = ?, Estado = ? WHERE IdProducto = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, p.getNom());
            ps.setInt(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setString(4, p.getEstado());
            ps.setInt(5, p.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Eliminar un producto
    public void delete(int id) {
        String sql = "DELETE FROM producto WHERE IdProducto = ?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}