<%@page import="models.entity.Producto"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>

<%
    List<Producto> lista = (List<Producto>) request.getAttribute("productos");
    String titulo = (String) request.getAttribute("titulo");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><%=titulo%></title>
    </head>
    <body>

        <h3><%=titulo%></h3>

        <a href="<%=request.getContextPath()%>/catalogo/form">crear producto (+)</a>

        <table border="0">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
            <% for (Producto producto : lista) {%>
            <tr>
                <td><%=producto.getId()%></td>

                <td><%=producto.getNombre()%></td>
                <td><%=producto.getPrecio()%></td>
                <td><%=producto.getCantidad()%></td>
                <td>
                    <a href="<%=request.getContextPath()%>/catalogo/form?id=<%=producto.getId()%>">
                        editar
                    </a>
                </td>
                <td>
                    <a onclick="return confirm('¿Seguro que desea eliminar?');" 
                       href="<%=request.getContextPath()%>/catalogo/eliminar?id=<%=producto.getId()%>">
                        eliminar
                    </a>
                </td>
            </tr>
            <%}%>
        </table>

    </body>
</html>