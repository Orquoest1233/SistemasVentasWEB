/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Cliente;
import Modelo.ClienteDAO;
import Modelo.Empleado;
import Modelo.EmpleadoDAO;
import Modelo.Pedido;
import Modelo.PedidoDAO;
import Modelo.Producto;
import Modelo.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author WINDOWS10
 */
public class Controlador extends HttpServlet {
    
        Producto p = new Producto();  
        ProductoDAO pdao = new ProductoDAO();

        Cliente c = new Cliente();
        ClienteDAO cdao = new ClienteDAO();
        int idc;
        int idp;

        Empleado em = new Empleado();
        EmpleadoDAO edao = new EmpleadoDAO();
        int ide;
        
        Pedido pdo = new Pedido();
        PedidoDAO pedao = new PedidoDAO();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String menu =  request.getParameter("menu");
        String accion = request.getParameter("accion");
        
        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
  
        
        if (menu.equals("Empleado")) {
            switch(accion){
                case "Listar":
                    List lista = edao.listar();
                    request.setAttribute("empleados", lista);
                    break;
                case "Agregar":
                    String dni =  request.getParameter("txtDni");
                    String nom =  request.getParameter("txtNombres");
                    String tel =  request.getParameter("txtTel");
                    String est =  request.getParameter("txtEstado");
                    String user =  request.getParameter("txtUser");
                    em.setDni(dni);
                    em.setNom(nom);
                    em.setTel(tel);
                    em.setEstado(est);
                    em.setUser(user);
                    edao.agregar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
                    
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Empleado e = edao.listarId(ide);
                    request.setAttribute("empleado", e);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    
                    break;
                case "Actualizar":
                    String dni1 =  request.getParameter("txtDni");
                    String nom1 =  request.getParameter("txtNombres");
                    String tel1 =  request.getParameter("txtTel");
                    String est1 =  request.getParameter("txtEstado");
                    String user1 =  request.getParameter("txtUser");
                    em.setDni(dni1);
                    em.setNom(nom1);
                    em.setTel(tel1);
                    em.setEstado(est1);
                    em.setUser(user1);
                    em.setId(ide);
                    edao.actualizar(em);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    
                    break;
                    
                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    edao.delete(ide);
                    request.getRequestDispatcher("Controlador?menu=Empleado&accion=Listar").forward(request, response);
                    break;
            }
            
            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }
        
            
        if (menu.equals("Cliente")) {
            switch (accion) {
                case "Listar":
                    List lista = cdao.listar();
                    request.setAttribute("clientes", lista);
                    //System.out.println(cdao.listar());
                    break;
                case "Agregar":
                    String dni = request.getParameter("txtDni");
                    String nom = request.getParameter("txtNombres");
                    String tel = request.getParameter("txtTel");
                    String est = request.getParameter("txtEstado");
                    c.setDni(dni);
                    c.setNom(nom);
                    c.setDir(tel);
                    c.setEs(est);
                    cdao.agregar(c);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    idc = Integer.parseInt(request.getParameter("id"));
                    Cliente cl = cdao.listarId(idc);
                    request.setAttribute("cliente", cl);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                case "Actualizar":
                    String dni1 = request.getParameter("txtDni");
                    String nom1 = request.getParameter("txtNombres");
                    String tel1 = request.getParameter("txtTel");
                    String est1 = request.getParameter("txtEstado");
                    c.setDni(dni1);
                    c.setNom(nom1);
                    c.setDir(tel1);
                    c.setEs(est1);
                    c.setId(idc);
                    cdao.actualizar(c);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                case "Delete":
                    idc = Integer.parseInt(request.getParameter("id"));
                    cdao.delete(idc);
                    request.getRequestDispatcher("Controlador?menu=Cliente&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("Clientes.jsp").forward(request, response);
            
        }
            
        if ("Producto".equals(menu)) {
            // Manejo por defecto: si 'accion' es null/vacío, lo establecemos a "Listar"
            if (accion == null || accion.isEmpty()) {
                accion = "Listar";
            }
            
            switch (accion) {
                case "Listar":
                    // Listar productos desde la base de datos
                    List<Producto> listaProductos = pdao.listar();
                    request.setAttribute("productos", listaProductos);  // Pasar la lista a la JSP
                    break;

                case "Agregar":
                    // Recibir datos del formulario para agregar un nuevo producto
                    p.setNom(request.getParameter("txtNom"));
                    // Usamos try-catch para evitar NumberFormatException si los campos están vacíos
                    try {
                        p.setPrecio(Integer.parseInt(request.getParameter("txtPrecio")));
                        p.setStock(Integer.parseInt(request.getParameter("txtStock")));
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato de número en Agregar Producto: " + e.getMessage());
                        // Opcional: Establecer valores por defecto o mostrar un mensaje de error
                    }
                    p.setEstado(request.getParameter("txtEstado"));

                    // Insertar el nuevo producto en la base de datos
                    pdao.agregar(p);
                    // Redirigir al listar, no reenviar
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    return; // Detener la ejecución

                case "Editar":
                    // Recibir el id del producto que se quiere editar
                    idp = Integer.parseInt(request.getParameter("id"));
                    Producto prodEditar = pdao.listarId(idp);  // Obtener el producto por id
                    request.setAttribute("producto", prodEditar);  // Pasar el producto a la JSP
                    break; // Continuar al reenvío (forward) al final

                case "Actualizar":
                    // Recibir los datos del formulario para actualizar el producto
                    p.setId(Integer.parseInt(request.getParameter("txtId")));
                    p.setNom(request.getParameter("txtNom"));
                    // Usamos try-catch para evitar NumberFormatException si los campos están vacíos
                    try {
                        p.setPrecio(Integer.parseInt(request.getParameter("txtPrecio")));
                        p.setStock(Integer.parseInt(request.getParameter("txtStock")));
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato de número en Actualizar Producto: " + e.getMessage());
                        // Opcional: Establecer valores por defecto o mostrar un mensaje de error
                    }
                    p.setEstado(request.getParameter("txtEstado"));

                    // Actualizar el producto en la base de datos
                    pdao.actualizar(p);

                    // Redirigir al listar, no reenviar
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    return; // Detener la ejecución

                case "Delete":
                    // Recibir el id del producto que se quiere eliminar
                    idp = Integer.parseInt(request.getParameter("id"));
                    pdao.delete(idp);  // Eliminar el producto de la base de datos

                    // Redirigir al listar, no reenviar
                    request.getRequestDispatcher("Controlador?menu=Producto&accion=Listar").forward(request, response);
                    return; // Detener la ejecución
            }
            // Reenviar a Producto.jsp solo para las acciones Listar y Editar (donde se requiere mostrar la vista)
            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }
        
        if (menu.equals("Pedido")) {
            // Valor por defecto si la acción es nula o vacía (ej. desde Principal.jsp)
            if (accion == null || accion.isEmpty()) {
                accion = "Default"; 
            }
            
            // Constante para IGV (Impuesto General a las Ventas)
            final double IGV_RATE = 0.18; // 18%
            
            switch(accion){
                case "BuscarCliente":
                    // ... (Lógica de BuscarCliente)
                    String codCliente = request.getParameter("codCliente");
                    if (codCliente != null && !codCliente.trim().isEmpty()) { 
                        Cliente clienteEncontrado = cdao.buscar(codCliente);
                        if (clienteEncontrado.getNom() != null) {
                            request.setAttribute("cliente", clienteEncontrado);
                        } else {
                            request.setAttribute("mensaje", "Cliente no encontrado.");
                        }
                        request.setAttribute("codCliente", codCliente);
                    }
                    // Re-exponer otros campos si existen
                    request.setAttribute("descripcion", request.getParameter("descripcion"));
                    request.setAttribute("precio", request.getParameter("precio"));
                    request.setAttribute("cantidad", request.getParameter("cantidad"));
                    
                    // Asegurar que la lista se cargue también al buscar
                    List listaPedidosBusc = pedao.listar();
                    request.setAttribute("pedidos", listaPedidosBusc);
                    
                    request.getRequestDispatcher("RegistarPedido.jsp").forward(request, response);
                    break;
                    
                case "Registrar":
                    // ... (Lógica de Registrar)
                    String codClienteReg = request.getParameter("codCliente");
                    String nombreCliente = request.getParameter("nombreCliente");
                    String apellidos = request.getParameter("apellidos");
                    String descripcion = request.getParameter("descripcion");
                    double precio = 0.0;
                    int cantidad = 0;
                    
                    try {
                        precio = Double.parseDouble(request.getParameter("precio"));
                        cantidad = Integer.parseInt(request.getParameter("cantidad"));
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato numérico en Pedido: " + e.getMessage());
                    }
                    
                    double subtotal = precio * cantidad;
                    double igv = subtotal * IGV_RATE;
                    double total = subtotal + igv;

                    Pedido nuevoPedido = new Pedido();
                    nuevoPedido.setCodigo_cliente(codClienteReg);
                    nuevoPedido.setNombres(nombreCliente);
                    nuevoPedido.setApellidos(apellidos);
                    nuevoPedido.setDescripcion(descripcion);
                    nuevoPedido.setPrecio(precio);
                    nuevoPedido.setCantidad(cantidad);
                    nuevoPedido.setSubTotal(subtotal);
                    nuevoPedido.setIGV(igv);
                    nuevoPedido.setTotal(total);
                    
                    if (pedao.agregar(nuevoPedido) == 1) {
                        request.setAttribute("mensaje", "Pedido registrado con éxito. ID: " + nuevoPedido.getId_producto());
                        // Limpiar campos (o establecer valores iniciales para nuevo registro)
                        request.setAttribute("codCliente", "");
                        request.setAttribute("cliente", null); // Limpiar cliente
                        request.setAttribute("descripcion", "");
                        request.setAttribute("precio", "0.00");
                        request.setAttribute("cantidad", "1");
                        request.setAttribute("subtotal", 0.0);
                        request.setAttribute("igv", 0.0);
                        request.setAttribute("total", 0.0);
                    } else {
                        request.setAttribute("mensaje", "Error al registrar el pedido.");
                    }
                    
                    // Despues de registrar, listar para ver el resultado
                    response.sendRedirect("Controlador?menu=Pedido&accion=Listar"); 
                    return; // Detener la ejecución
                
                case "Editar": // Implementación para cargar datos en el formulario
                    String idPedidoEditar = request.getParameter("id"); // id es id_producto
                    Pedido pedidoEdit = pedao.listarId(idPedidoEditar);
                    
                    // Pasar el objeto Pedido a la JSP. Esto se puede usar para precargar el formulario
                    request.setAttribute("pedidoEdit", pedidoEdit);
                    
                    // También cargar la lista para que siga siendo visible
                    List listaPedidosEdit = pedao.listar();
                    request.setAttribute("pedidos", listaPedidosEdit);
                    
                    request.getRequestDispatcher("RegistarPedido.jsp").forward(request, response);
                    return;

                case "Actualizar": // Implementación para guardar los cambios
                    String idPedidoAct = request.getParameter("idProducto"); // Asume que este campo se añade al formulario
                    String nombreClienteAct = request.getParameter("nombreCliente");
                    String apellidosAct = request.getParameter("apellidos");
                    String descripcionAct = request.getParameter("descripcion");
                    double precioAct = 0.0;
                    int cantidadAct = 0;
                    
                    try {
                        precioAct = Double.parseDouble(request.getParameter("precio"));
                        cantidadAct = Integer.parseInt(request.getParameter("cantidad"));
                    } catch (NumberFormatException e) {
                        System.out.println("Error de formato numérico en Pedido Actualizar: " + e.getMessage());
                    }

                    double subtotalAct = precioAct * cantidadAct;
                    double igvAct = subtotalAct * IGV_RATE;
                    double totalAct = subtotalAct + igvAct;

                    Pedido pedidoActualizar = new Pedido();
                    pedidoActualizar.setId_producto(idPedidoAct); // PK
                    pedidoActualizar.setNombres(nombreClienteAct);
                    pedidoActualizar.setApellidos(apellidosAct);
                    pedidoActualizar.setDescripcion(descripcionAct);
                    pedidoActualizar.setPrecio(precioAct);
                    pedidoActualizar.setCantidad(cantidadAct);
                    pedidoActualizar.setSubTotal(subtotalAct);
                    pedidoActualizar.setIGV(igvAct);
                    pedidoActualizar.setTotal(totalAct);

                    if (pedao.actualizar(pedidoActualizar) == 1) {
                        request.setAttribute("mensaje", "Pedido actualizado con éxito. ID: " + idPedidoAct);
                    } else {
                        request.setAttribute("mensaje", "Error al actualizar el pedido.");
                    }
                    
                    response.sendRedirect("Controlador?menu=Pedido&accion=Listar");
                    return;

                case "Listar": // Implementación para hacer visible la lista
                    List listaPedidos = pedao.listar();
                    request.setAttribute("pedidos", listaPedidos);
                    request.getRequestDispatcher("RegistarPedido.jsp").forward(request, response); // Forward a la JSP principal
                    return; // Detener la ejecución para no caer en el forward final
                
                case "Delete": // Implementación de la acción Eliminar
                    String idPedidoDelete = request.getParameter("id"); // id es id_producto
                    pedao.delete(idPedidoDelete);
                    // Redirigir para refrescar la lista después de eliminar
                    response.sendRedirect("Controlador?menu=Pedido&accion=Listar");
                    return; // Detener la ejecución para no caer en el forward final
                
                case "Default":
                    // Si no se especifica acción, listar y mostrar el formulario.
                    List listaPedidosDef = pedao.listar();
                    request.setAttribute("pedidos", listaPedidosDef);
                    request.getRequestDispatcher("RegistarPedido.jsp").forward(request, response);
                    return;
                    
                default:
                    // Si la acción no es reconocida, listar y mostrar el formulario.
                    List listaPedidosDef2 = pedao.listar();
                    request.setAttribute("pedidos", listaPedidosDef2);
                    request.getRequestDispatcher("RegistarPedido.jsp").forward(request, response);
                    return;
            }
        } 
   }
  
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}