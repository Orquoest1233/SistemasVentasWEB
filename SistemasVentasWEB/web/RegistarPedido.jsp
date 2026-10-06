<%@page import="Modelo.Cliente"%>
<%@page import="Modelo.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%> 
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Obtener la fecha actual
    Date fechaActual = new Date();
    // Definir el formato deseado (Día/Mes/Año)
    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
    // Aplicar el formato
    String fechaHoy = formatoFecha.format(fechaActual);
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <title>Registro de Pedidos</title>
</head>
<body class="p-4 bg-light">

<div class="container border p-4 bg-white rounded">
    <h3 class="text-center mb-4">CONSULTA PARA EL REGISTRO Y GESTIÓN DE PEDIDOS</h3>
        
    <% if(request.getAttribute("mensaje") != null) { %>
        <div class="alert alert-info text-center">
            <%= request.getAttribute("mensaje") %>
        </div>
    <% } %>

    <%
        // 1. Obtener los objetos de la solicitud
        Pedido pedidoEditar = (Pedido)request.getAttribute("pedidoEdit"); 
        Cliente cl = (Cliente)request.getAttribute("cliente");       
        
        String idProductoVal = pedidoEditar != null ? pedidoEditar.getId_producto() : "";
        
        String codClienteVal = "";
        String nombreClienteVal = "";
        String apellidosVal = "";
        
        String descripcionVal = "";
        double precioVal = 0.00;
        int cantidadVal = 1;
        
        double subTotalVal = 0.00;
        double igvVal = 0.00;
        double totalVal = 0.00;

        if (pedidoEditar != null) {
            // Modo Edición: Usar datos del pedido a editar
            codClienteVal = pedidoEditar.getCodigo_cliente();
            nombreClienteVal = pedidoEditar.getNombres();
            apellidosVal = pedidoEditar.getApellidos();
            descripcionVal = pedidoEditar.getDescripcion();
            precioVal = pedidoEditar.getPrecio();
            cantidadVal = pedidoEditar.getCantidad();
            subTotalVal = pedidoEditar.getSubTotal();
            igvVal = pedidoEditar.getIGV();
            totalVal = pedidoEditar.getTotal();
        } else {
            // Modo Registro: Usar datos de búsqueda de cliente o valores por defecto
            String codClienteAttr = request.getAttribute("codCliente") == null ? "" : (String)request.getAttribute("codCliente"); 
            codClienteVal = cl != null ? cl.getDni() : codClienteAttr;
            nombreClienteVal = cl != null ? cl.getNom() : "";
            apellidosVal = ""; // Se rellena manualmente en Registro si el cliente existe
            
            // Si hay reenvío después de un error o búsqueda parcial, mantener los valores.
            descripcionVal = request.getAttribute("descripcion") == null ? "" : (String)request.getAttribute("descripcion");
            
            try {
                if (request.getAttribute("precio") != null) { precioVal = Double.parseDouble(request.getAttribute("precio").toString()); }
                if (request.getAttribute("cantidad") != null) { cantidadVal = Integer.parseInt(request.getAttribute("cantidad").toString()); }
                if (request.getAttribute("subtotal") != null) { subTotalVal = Double.parseDouble(request.getAttribute("subtotal").toString()); }
                if (request.getAttribute("igv") != null) { igvVal = Double.parseDouble(request.getAttribute("igv").toString()); }
                if (request.getAttribute("total") != null) { totalVal = Double.parseDouble(request.getAttribute("total").toString()); }
            } catch (NumberFormatException e) {
                // Manejar error de parseo si es necesario
            }
        }
    %>
   
    <form action="Controlador?menu=Pedido" method="post">
        
        <input type="hidden" name="idProducto" value="<%= idProductoVal %>"> 
        
        <div class="form-row">
            
            <div class="row align-items-start">
                <div class="form-group col-md-3">
                <label>Código</label>
                <div class="input-group">
                    <input type="text" name="codCliente" class="form-control" value="<%= codClienteVal %>" <%= pedidoEditar != null ? "readonly" : "required" %>>
                    <div class="input-group-append">
                    </div>
                </div>
                </div>
            
            <div class="form-group col-md-4">
                <label>Nombres</label>
                <input type="text" name="nombreCliente" class="form-control" value="<%= nombreClienteVal %>" required>
            </div>
            
            <div class="form-group col-md-5">
                <label>Apellidos</label>
                <input type="text" name="apellidos" class="form-control" value="<%= apellidosVal %>" required> 
            </div>
            </div>   

            <div class="form-group col-md-5">
               <label>Fecha</label>
                <input type="text" name="fecha" class="form-control" value="<%= fechaHoy %>" readonly>
            </div>   
        </div>
  
            
            <div class="form-row mt-3">

        <div class="row align-items-start">
      
            <div class="form-group col-md-6">
                <label>Descripción Producto</label>
                <input type="text" name="descripcion" class="form-control" value="<%= descripcionVal %>" required>
            </div>
            <div class="form-group col-md-3">
                <label>Precio S/.</label>
                <input type="number" step="0.01" name="precio" class="form-control" value="<%= String.format("%.2f", precioVal) %>" required>
            </div>
            <div class="form-group col-md-3">
                <label>Cantidad</label>
                <input type="number" name="cantidad" class="form-control" value="<%= cantidadVal %>" required>
            </div>
             
            <div class="form-group col-md-3">
                <label>SubTotal</label>
                <input type="text" name="sumSubtotal" class="form-control text-right" 
                       value="<%= String.format("%.2f", subTotalVal) %>" readonly>
            </div>
            <div class="form-group col-md-3">
                <label>IGV (18%)</label>
                <input type="text" name="sumIGV" class="form-control text-right" 
                       value="<%= String.format("%.2f", igvVal) %>" readonly>
            </div>
            <div class="form-group col-md-3">
                <label>Total a Pagar</label>
                <input type="text" name="total" class="fw-bold form-control text-right" 
                       value="<%= String.format("%.2f", totalVal) %>" readonly>
            </div>
        </div>
        </div>
        
       
</div>
        <div class="text-center mt-4">
            <% if (pedidoEditar != null) { %>
                <button type="submit" name="accion" value="Actualizar" class="btn btn-primary">Actualizar Pedido</button>
                <a href="Controlador?menu=Pedido&accion=Listar" class="btn btn-secondary">Cancelar Edición</a>
            <% } else { %>
                <button type="submit" name="accion" value="Registrar" class="btn btn-success">Registrar Pedido</button>
            <% } %>
            
        </div>
        
    </form>
</div>

<div class="container border p-4 bg-white rounded mt-4">
    <h4 class="text-center mb-4">Lista de Pedidos Registrados</h4>
    
    <% List<Pedido> lista = (List<Pedido>)request.getAttribute("pedidos"); %>

    <table class="table table-hover">
        <thead class="thead-dark">
            <tr>
                <th>ID</th>
                <th>Cód. Cliente</th>
                <th>Cliente</th>
                <th>Descripción</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>IGV (18%)</th>
                <th>SubTotal</th>
                <th>Total</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody>
            <% if (lista != null) { 
                for(Pedido p : lista) { %>
                <tr>
                    <td><%= p.getId_producto() %></td>
                    <td><%= p.getCodigo_cliente() %></td>
                    <td><%= p.getNombres() + " " + p.getApellidos() %></td>
                    <td><%= p.getDescripcion() %></td>
                    <td><%= String.format("%.2f", p.getPrecio()) %></td>
                    <td><%= p.getCantidad() %></td>
                    <td><%= String.format("%.2f", p.getIGV()) %></td>
                    <td><%= String.format("%.2f", p.getSubTotal()) %></td>
                    <td><%= String.format("%.2f", p.getTotal()) %></td>
                    <td>
                        <a class="btn btn-warning btn-sm" href="Controlador?menu=Pedido&accion=Editar&id=<%= p.getId_producto() %>">Editar</a> 
                        <a class="btn btn-danger btn-sm" href="Controlador?menu=Pedido&accion=Delete&id=<%= p.getId_producto() %>" onclick="return confirm('¿Está seguro de eliminar este pedido?');">Eliminar</a>
                    </td>
                </tr>
            <% } 
            } else { %>
            <tr>
                <td colspan="10" class="text-center">No hay pedidos registrados o hubo un error al cargar.</td> </tr>
            <% } %>
        </tbody>
    </table>
    
</div>                           
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>