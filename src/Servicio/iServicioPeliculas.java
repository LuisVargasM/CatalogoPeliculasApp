package Servicio;

import dominio.Pelicula;

public interface iServicioPeliculas {
    public void listarPeliculas();

    public void agregarPelicula(Pelicula pelicula);

    public void buscarPelicula(Pelicula pelicula);

}
