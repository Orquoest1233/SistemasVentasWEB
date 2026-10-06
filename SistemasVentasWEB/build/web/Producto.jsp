<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Productos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-4">
        <h2>Gestión de Productos</h2>

        <c:choose>
            <c:when test="${producto != null}">
                <h4 class="mt-4">Editar Producto</h4>
                <form action="Controlador?menu=Producto&accion=Actualizar" method="post">
                    <input type="hidden" name="txtId" value="${producto.id}">
                    <input type="text" name="txtNom" value="${producto.nom}" placeholder="Nombre" class="form-control mb-2" required>
                    <input type="number" name="txtPrecio" value="${producto.precio}" placeholder="Precio" class="form-control mb-2" required>
                    <input type="number" name="txtStock" value="${producto.stock}" placeholder="Stock" class="form-control mb-2" required>
                    <select name="txtEstado" class="form-control mb-2">
                        <option value="1" ${producto.estado == '1' ? 'selected' : ''}>Activo</option>
                        <option value="0" ${producto.estado == '0' ? 'selected' : ''}>Inactivo</option>
                    </select>
                    <button type="submit" class="btn btn-success">Actualizar Producto</button>
                    <a href="Controlador?menu=Producto&accion=Listar" class="btn btn-secondary">Cancelar</a>
                </form>
            </c:when>
            <c:otherwise>
                <h4 class="mt-4">Agregar Nuevo Producto</h4>
                <form action="Controlador?menu=Producto&accion=Agregar" method="post">
                    <input type="text" name="txtNom" placeholder="Nombre" class="form-control mb-2" required>
                    <input type="number" name="txtPrecio" placeholder="Precio" class="form-control mb-2" required>
                    <input type="number" name="txtStock" placeholder="Stock" class="form-control mb-2" required>
                    <select name="txtEstado" class="form-control mb-2">
                        <option value="1">Activo</option>
                        <option value="0">Inactivo</option>
                    </select>
                    <button type="submit" class="btn btn-primary">Agregar Producto</button>
                </form>
            </c:otherwise>
        </c:choose>
        <h4 class="mt-4">Lista de Productos</h4>
        <table class="table table-hover mt-4">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Stock</th>
                    <th>Estado</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="prod" items="${productos}">
                    <tr>
                        <td>${prod.id}</td>
                        <td>${prod.nom}</td>
                        <td>${prod.precio}</td>
                        <td>${prod.stock}</td>
                        <td>${prod.estado}</td>
                        <td>
                            <a href="Controlador?menu=Producto&accion=Editar&id=${prod.id}" class="btn btn-warning">Editar</a>
                            <a href="Controlador?menu=Producto&accion=Delete&id=${prod.id}" class="btn btn-danger" " onclick="return confirm('¿Está seguro de eliminar este producto?');">Eliminar</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>