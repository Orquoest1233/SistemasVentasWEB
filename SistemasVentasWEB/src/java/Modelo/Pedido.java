package Modelo;

public class Pedido {
    // La tabla 'pedido' en bd_ventas1.sql usa id_producto como PK y es varchar
    String id_producto; 
    String codigo_cliente;
    String nombres; 
    String apellidos;
    String descripcion; 
    double precio;
    int cantidad;
    double IGV;
    double SubTotal;
    double Total;

    // Constructor vacío
    public Pedido() {
    }

    // Constructor con todos los campos
    public Pedido(String id_producto, String codigo_cliente, String nombres, String apellidos, String descripcion, double precio, int cantidad, double IGV, double SubTotal, double Total) {
        this.id_producto = id_producto;
        this.codigo_cliente = codigo_cliente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.IGV = IGV;
        this.SubTotal = SubTotal;
        this.Total = Total;
    }

    // Getters y Setters
    public String getId_producto() { return id_producto; }
    public void setId_producto(String id_producto) { this.id_producto = id_producto; }
    
    public String getCodigo_cliente() { return codigo_cliente; }
    public void setCodigo_cliente(String codigo_cliente) { this.codigo_cliente = codigo_cliente; }
    
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public double getIGV() { return IGV; }
    public void setIGV(double IGV) { this.IGV = IGV; }
    
    public double getSubTotal() { return SubTotal; }
    public void setSubTotal(double SubTotal) { this.SubTotal = SubTotal; }
    
    public double getTotal() { return Total; }
    public void setTotal(double Total) { this.Total = Total; }
}