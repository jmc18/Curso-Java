package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.dao.IProductoDao;
import models.dao.ProductoDaoJDBC;
import models.entity.Producto;

@WebServlet("/catalogo/form")
public class FormController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = (request.getParameter("id") == null)
                ? 0
                : Integer.parseInt(request.getParameter("id"));

        Producto producto;
        String titulo;

        IProductoDao dao = new ProductoDaoJDBC();

        if (id > 0) {
            producto = dao.buscarPorId(id);
            titulo = "Modificar Producto";
        } else {
            producto = new Producto();
            titulo = "Crear Producto";
        }

        request.setAttribute("producto", producto);
        request.setAttribute("titulo", titulo);

        request.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = (request.getParameter("id") == null)
                ? 0
                : Integer.parseInt(request.getParameter("id"));

        List<String> errores = new ArrayList<String>();

        String nombre = request.getParameter("nombre");

        int precio = 0;

        try {
            precio = Integer.parseInt(request.getParameter("precio"));
        } catch (NumberFormatException e) {
            errores.add("precio vacio o formato incorrecto");
        }

        int cantidad = 0;
        try {
            cantidad = Integer.parseInt(request.getParameter("cantidad"));
        } catch (NumberFormatException e) {
            errores.add("cantidad vacia o formato incorrecto");
        }

        if (nombre.isEmpty()) {
            errores.add("nombre es requerido");
        }

        if (precio == 0) {
            errores.add("precio es requerido");
        }

        if (cantidad == 0) {
            errores.add("cantidad es requerida");
        }

        IProductoDao dao = new ProductoDaoJDBC();

        Producto producto = new Producto();
        producto.setId(id);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setCantidad(cantidad);

        if (errores.isEmpty()) {
            dao.guardar(producto);

            String url = request.getContextPath() + "/catalogo/listado";
            response.sendRedirect(url);
        } else {

            request.setAttribute("producto", producto);
            request.setAttribute("titulo", "Validando Producto");
            request.setAttribute("errores", errores);

            request.getRequestDispatcher("/WEB-INF/views/form.jsp").forward(request, response);

        }
    }
}
