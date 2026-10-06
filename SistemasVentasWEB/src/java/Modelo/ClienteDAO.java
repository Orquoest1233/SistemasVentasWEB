package Modelo;

import config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class ClienteDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    // ******* CORRECCIÓN CRÍTICA DEL MÉTODO BUSCAR *******
    // Se usa '?' para PreparedStatement y se recuperan los datos por nombre de columna (Dni, Nombres, Direccion, Estado)
    public Cliente buscar(String dni){
        Cliente c=new Cliente();
        String sql="SELECT * FROM cliente WHERE Dni=?"; 
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, dni); // Establece el parámetro Dni
            rs=ps.executeQuery();
            
            // Si encuentra el registro, mapea los datos
            if (rs.next()) {
                c.setId(rs.getInt(1)); // Asumiendo que IdCliente es la columna 1
                c.setDni(rs.getString("Dni")); 
                c.setNom(rs.getString("Nombres"));
                c.setDir(rs.getString("Direccion")); // Corregido: usando "Direccion" en lugar de "txtTel"
                c.setEs(rs.getString("Estado")); // Corregido: usando "Estado" en lugar de "txtEstado"
            }
        } catch (Exception e) {
             System.out.println("Error en ClienteDAO.buscar: " + e.getMessage());
             e.printStackTrace();
        }
        return c;
    }

//*******Operaciones CRUD***************//
    public List listar(){
        String sql="select * from cliente";
        List<Cliente>lista=new ArrayList<>();
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                Cliente cl=new Cliente();
                cl.setId(rs.getInt(1));
                cl.setDni(rs.getString(2));
                cl.setNom(rs.getString(3));
                cl.setDir(rs.getString(4));
                cl.setEs(rs.getString(5));               
                lista.add(cl);
            }
        } catch (Exception e) {
            System.out.println("Error en ClienteDAO.listar: " + e.getMessage());
        }
        return lista;
    }
    public int agregar(Cliente cl){ 
        String sql="insert into cliente(Dni, Nombres, Direccion,Estado)values(?,?,?,?)";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cl.getDni());
            ps.setString(2, cl.getNom());
            ps.setString(3, cl.getDir());
            ps.setString(4, cl.getEs());           
            ps.executeUpdate();
            r = 1; // Suponemos éxito si no hay excepción
        } catch (Exception e) {
            System.out.println("Error en ClienteDAO.agregar: " + e.getMessage());
            r = 0;
        }
        return r;
        
    }
    public Cliente listarId(int id){
        Cliente cli=new Cliente();
        String sql="select * from cliente where IdCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
                cli.setDni(rs.getString(2));
                cli.setNom(rs.getString(3));
                cli.setDir(rs.getString(4));
                cli.setEs(rs.getString(5));              
            }
        } catch (Exception e) {
            System.out.println("Error en ClienteDAO.listarId: " + e.getMessage());
        }
        return cli;
    }
    public int actualizar(Cliente cli){ //esta bien
        String sql="update cliente set Dni=?, Nombres=?, Direccion=?,Estado=? where IdCliente=?";
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.setString(1, cli.getDni());
            ps.setString(2, cli.getNom());
            ps.setString(3, cli.getDir());
            ps.setString(4, cli.getEs());           
            ps.setInt(5, cli.getId());
            ps.executeUpdate();
             r = 1; // Suponemos éxito si no hay excepción
        } catch (Exception e) {
            System.out.println("Error en ClienteDAO.actualizar: " + e.getMessage());
            r = 0;
        }
        return r;
    }
    public void delete(int id){
        String sql="delete from cliente where IdCliente="+id;
        try {
            con=cn.Conexion();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en ClienteDAO.delete: " + e.getMessage());
        }
    }
    
}