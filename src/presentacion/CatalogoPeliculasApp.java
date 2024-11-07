package presentacion;


import Servicio.ServicioPeliculasArchivos;
import Servicio.ServicioPeliculasLista;
import Servicio.iServicioPeliculas;
import dominio.Pelicula;

import java.util.Scanner;

public class CatalogoPeliculasApp {
    public static void main(String[] args) {
        var salir = false;
        var consola = new Scanner(System.in);
        //Agregamos la implementacion del servicio

        //iServicioPeliculas serviciosPeliculas = new ServicioPeliculasLista();
        iServicioPeliculas serviciosPeliculas = new ServicioPeliculasArchivos();
        while (!salir){
            try{
                mostrarMenu();
                salir = ejecutarOpciones(consola, serviciosPeliculas);
            } catch (Exception e) {
                System.out.println("Ocurrio un error: " + e.getMessage());
            }
            System.out.println("");

        }//fin while

    }

    private static void mostrarMenu(){
        System.out.println("""
                ***Catalogo de peliculas***
                1. Agregar Pelicula
                2. Listar Pelicula
                3. Buscar Pelicula
                4. Salir
                Elige una opcion:
                """);
    }

    private static boolean ejecutarOpciones(Scanner consola, iServicioPeliculas servicioPeliculas){
     var opcion = Integer.parseInt(consola.nextLine());
     var salir = false;
     switch(opcion){
         case 1 -> {
             System.out.println("Introduce el nombre de la pelicula");
             var nombrePelicula = consola.nextLine();
             servicioPeliculas.agregarPelicula(new Pelicula(nombrePelicula));
         }//fin case 1
         case 2 ->{
             servicioPeliculas.listarPeliculas();
         }//fin case2
         case 3 ->{
             System.out.println("Introduce la pelicula a buscar: ");
             var buscar = consola.nextLine();
             servicioPeliculas.buscarPelicula(new Pelicula(buscar));

         }//fin case 3
         case 4-> {
             System.out.println("Hasta pronto");
             salir = true;

         }//fin case 4
         default -> System.out.println("Opcion no reconocida: " + opcion);
     }
     return salir;
    }
}