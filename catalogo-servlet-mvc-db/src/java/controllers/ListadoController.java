package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.dao.IProductoDao;
import models.dao.ProductoDaoJDBC;
import models.entity.Producto;

@WebServlet("/catalogo/listado")
public class ListadoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        IProductoDao dao = new ProductoDaoJDBC();

        List<Producto> lista = dao.listar();

        request.setAttribute("productos", lista);
        request.setAttribute("titulo", "Listado de Productos");

        request.getRequestDispatcher("/WEB-INF/views/listado.jsp").forward(request, response);
    }
}
