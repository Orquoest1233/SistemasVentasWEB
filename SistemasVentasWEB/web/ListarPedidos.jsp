<%@page import="Modelo.Pedido"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <title>Lista de Pedidos</title>
</head>
<body class="p-4 bg-light">

<div class="container border p-4 bg-white rounded">
    <h3 class="text-center mb-4">Lista de Pedidos Registrados</h3>
    
    <% List<Pedido> lista = (List<Pedido>)request.getAttribute("pedidos"); %>

    <table class="table table-bordered table-striped table-sm text-center mt-3">
        <thead class="bg-info text-white">
            <tr>
                <th>Cód. Temp</th>
                <th>Cód. Cliente</th>
                <th>Nombre Cliente</th>
                <th>Descripción Producto</th>
                <th>Precio</th>
                <th>Cant.</th>
                <th>IGV</th>
                <th>Sub Total</th>
                <th>Total</th>
                <th>ACCIONES</th> </tr>
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
                         <a class="btn btn-danger btn-sm" href="Controlador?menu=Pedido&accion=Delete&id=<%= p.getId_producto() %>" onclick="return confirm('¿Está seguro de eliminar este pedido?');">Delete</a>
                    </td>
                </tr>
            <% } 
            } else { %>
            <tr>
                <td colspan="10">No hay pedidos registrados.</td> </tr>
            <% } %>
        </tbody>
    </table>
    
    <div class="text-center mt-4">
        <a href="Controlador?menu=Pedido" class="btn btn-primary">Volver al Registro</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>