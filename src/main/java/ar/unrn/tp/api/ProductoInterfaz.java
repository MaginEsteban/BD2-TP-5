package ar.unrn.tp.api;

import ar.unrn.tp.modelo.Category;
import ar.unrn.tp.modelo.Discount;
import ar.unrn.tp.modelo.Product;

import java.util.List;

public interface ProductoInterfaz {

        //validar que sea una categoría existente y que codigo no se repita
        void crearProducto(Long codigo, String descripcion, double precio, Category cat, Long dis);

        //validar que sea un producto existente
        void modificarProducto(Long idProducto,Long codigo,String descripcion,double precio,Discount dis,Category cat);

        //Devuelve todos los productos
        List<Product> listarProductos();

        List<String> listarCategorias();

        Product buscarProducto(Long idProd);
}
