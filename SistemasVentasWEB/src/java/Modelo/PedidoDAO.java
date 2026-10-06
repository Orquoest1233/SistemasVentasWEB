package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class PedidoDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    // Genera un ID temporal para id_producto (clave primaria de la tabla pedido)
    private String generarIdProductoTemporal() {
        // CORRECCIÓN: Se añade un componente aleatorio para evitar colisiones de clave primaria
        // si se intenta registrar varios pedidos en el mismo milisegundo.
        return String.valueOf(System.currentTimeMillis()) + "-" + (int)(Math.random() * 1000); //
    }

    // Método para agregar un nuevo registro a la tabla 'pedido'
    public int agregar(Pedido ped) {
        // Asignar ID temporal antes de insertar
        ped.setId_producto(generarIdProductoTemporal()); 
        
        String sql = "INSERT INTO pedido(id_producto, codigo_cliente, nombres, apellidos, descripcion, precio, cantidad, IGV, SubTotal, Total) VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, ped.getId_producto());
            ps.setString(2, ped.getCodigo_cliente());
            ps.setString(3, ped.getNombres());
            ps.setString(4, ped.getApellidos());
            ps.setString(5, ped.getDescripcion());
            ps.setDouble(6, ped.getPrecio());
            ps.setInt(7, ped.getCantidad());
            ps.setDouble(8, ped.getIGV());
            ps.setDouble(9, ped.getSubTotal());
            ps.setDouble(10, ped.getTotal());
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en PedidoDAO.agregar: " + e.getMessage());
            e.printStackTrace();
        }
        return r;
    }
    
    // Método para listar todos los registros de la tabla 'pedido'
    public List<Pedido> listar() {
        String sql = "SELECT * FROM pedido";
        List<Pedido> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Pedido ped = new Pedido();
                ped.setId_producto(rs.getString(1));
                ped.setCodigo_cliente(rs.getString(2));
                ped.setNombres(rs.getString(3));
                ped.setApellidos(rs.getString(4));
                ped.setDescripcion(rs.getString(5));
                ped.setPrecio(rs.getDouble(6));
                ped.setCantidad(rs.getInt(7));
                ped.setIGV(rs.getDouble(8));
                ped.setSubTotal(rs.getDouble(9));
                ped.setTotal(rs.getDouble(10));
                lista.add(ped);
            }
        } catch (Exception e) {
            System.out.println("Error en PedidoDAO.listar: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    // Método para obtener un pedido por su ID (id_producto)
    public Pedido listarId(String id_producto) {
        Pedido ped = new Pedido();
        String sql = "SELECT * FROM pedido WHERE id_producto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, id_producto);
            rs = ps.executeQuery();
            if (rs.next()) {
                ped.setId_producto(rs.getString(1));
                ped.setCodigo_cliente(rs.getString(2));
                ped.setNombres(rs.getString(3));
                ped.setApellidos(rs.getString(4));
                ped.setDescripcion(rs.getString(5));
                ped.setPrecio(rs.getDouble(6));
                ped.setCantidad(rs.getInt(7));
                ped.setIGV(rs.getDouble(8));
                ped.setSubTotal(rs.getDouble(9));
                ped.setTotal(rs.getDouble(10));
            }
        } catch (Exception e) {
            System.out.println("Error en PedidoDAO.listarId: " + e.getMessage());
            e.printStackTrace();
        }
        return ped;
    }

    // Método para actualizar un registro en la tabla 'pedido'
    public int actualizar(Pedido ped) { 
        // Se actualizan los campos excepto la PK (id_producto) y el codigo_cliente (suponiendo que no deben cambiar)
        String sql = "UPDATE pedido SET nombres=?, apellidos=?, descripcion=?, precio=?, cantidad=?, IGV=?, SubTotal=?, Total=? WHERE id_producto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, ped.getNombres());
            ps.setString(2, ped.getApellidos());
            ps.setString(3, ped.getDescripcion());
            ps.setDouble(4, ped.getPrecio());
            ps.setInt(5, ped.getCantidad());
            ps.setDouble(6, ped.getIGV());
            ps.setDouble(7, ped.getSubTotal());
            ps.setDouble(8, ped.getTotal());
            ps.setString(9, ped.getId_producto()); // Usar id_producto para WHERE
            r = ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en PedidoDAO.actualizar: " + e.getMessage());
            e.printStackTrace();
            r = 0;
        }
        return r;
    }

    // Método para eliminar un registro de la tabla 'pedido'
    public void delete(String id_producto) {
        String sql = "DELETE FROM pedido WHERE id_producto=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, id_producto);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en PedidoDAO.delete: " + e.getMessage());
            e.printStackTrace();
        }
    }
}