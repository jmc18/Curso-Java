package models.dao;

import java.util.List;

import models.entity.Producto;

public interface IProductoDao {

    public List<Producto> listar();

    public Producto buscarPorId(int id);

    public void guardar(Producto producto);

    public void eliminar(Producto producto);
}
