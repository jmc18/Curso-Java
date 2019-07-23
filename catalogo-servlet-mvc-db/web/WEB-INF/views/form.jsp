<%@page import="java.util.List"%>
<%@page import="models.entity.Producto"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%
    String titulo = (String) request.getAttribute("titulo");
    Producto producto = (Producto) request.getAttribute("producto");
    List<String> errores = (List<String>) request.getAttribute("errores");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title><%=titulo%></title>
    </head>
    <body>
        <h3><%=titulo%></h3>

        <% if (errores != null && errores.size() > 0) {%>
        <ul>
            <% for (String error : errores) {%>
            <li style="color:red; font-weight: bold;"><%=error%></li>
                <% }%>
        </ul>
        <% }%>

        <a href="<%=request.getContextPath()%>/catalogo/listado">&lt;&lt;volver</a>

        <form method="post">

            <input name="id" type="hidden" value="<%=producto.getId()%>" />

            <table>

                <tr>
                    <td>Nombre:</td>
                    <td><input name="nombre" type="text" value="<%=(producto.getNombre() == null) ? "" : producto.getNombre()%>" />
                    </td>
                </tr>

                <tr>
                    <td>Precio:</td>
                    <td><input name="precio" type="text" value="<%=(producto.getPrecio() == 0) ? "" : producto.getPrecio()%>" />
                    </td>
                </tr>

                <tr>
                    <td>Cantidad</td>
                    <td><input name="cantidad" type="text" value="<%=(producto.getCantidad() == 0) ? "" : producto.getCantidad()%>" />
                    </td>
                </tr>
                <tr>
                    <td colspan="2"><input name="enviar" type="submit" value="<%=titulo%>" />
                    </td>
                </tr>

            </table>


        </form>

    </body>
</html>