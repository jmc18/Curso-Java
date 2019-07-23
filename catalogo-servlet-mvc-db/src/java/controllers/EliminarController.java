package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.dao.IProductoDao;
import models.dao.ProductoDaoJDBC;
import models.entity.Producto;

@WebServlet("/catalogo/eliminar")
public class EliminarController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = (request.getParameter("id") == null)
                ? 0
                : Integer.parseInt(request.getParameter("id"));

        Producto producto = new Producto();
        producto.setId(id);

        IProductoDao dao = new ProductoDaoJDBC();

        dao.eliminar(producto);

        String url = request.getContextPath() + "/catalogo/listado";
        response.sendRedirect(url);
    }
}
