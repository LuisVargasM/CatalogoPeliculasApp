package Servicio;

import dominio.Pelicula;

import java.io.*;

public class ServicioPeliculasArchivos implements iServicioPeliculas {



    private final String  NOMBRE_ARCHIVO = "peliculas.txt";

    public ServicioPeliculasArchivos(){
        var archivo = new File(NOMBRE_ARCHIVO);
        //Si existe el archivo, no lo vuelve a crear
        try{
           if(archivo.exists()){
               System.out.println("El archivo ya existe");
           }
           else {
               //Si no existe, se crea vacio
               var salida = new PrintWriter(new FileWriter(archivo));
               salida.close();
               System.out.println("Se a creado el archivo");
           }
        }catch (Exception e){
            System.out.println("Ocurrio un error al abrir el archivo: " + e.getMessage());
        }
    }

    @Override
    public void listarPeliculas() {
        // volvemos a abrir el archivo
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            System.out.println("Listado de peliculas");
            var entrada = new BufferedReader(new FileReader(archivo));
            // Leemos linea a linea el archivo
            String linea;
            linea = entrada.readLine();
            //leamos las lineas
            while (linea != null){
                var pelicula = new Pelicula(linea);
                System.out.println(pelicula);
                // Antes de terminar el ciclo volvemos a leer la siguiente linea
                linea = entrada.readLine();
            }
            //Cerrar el archivo
            entrada.close();
        } catch (Exception e) {
            System.out.println("Ocurrio un error al leer el archivo: " + e.getMessage());
        }
    }

    @Override
    public void agregarPelicula(Pelicula pelicula) {
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            //Revisamos si existe el archivo
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            //Agregamos la pelicula a toString
            salida.println(pelicula);
            salida.close();
            System.out.println("Se agrego al archivo: " + pelicula);
        }catch (Exception e){
            System.out.println("Ocurrio un error al agregar pelicula: " + e.getMessage());
        }
    }

    @Override
    public void buscarPelicula(Pelicula pelicula) {
        var archivo = new File(NOMBRE_ARCHIVO);
        try{
            //Abrimos el archivo para lectura
            var entrada = new BufferedReader(new FileReader(archivo));
            String lineaTexto;
            lineaTexto= entrada.readLine();
            var indice = 1;
            var encontrada = false;
            var peliculaBuscar = pelicula.getNombre();
            while (lineaTexto != null){
                // Buscamos sin importar mayus/minus
                if(peliculaBuscar != null && peliculaBuscar.equalsIgnoreCase(lineaTexto)){
                    encontrada = true;
                    break;

                }
                // Leemos la siguiente linea antes de la siguiente interacion
                lineaTexto = entrada.readLine();
                indice++;

            }//fin while
            //imprimimos los resultados de la busqueda
            if (encontrada){
                System.out.println("Pelicula " + lineaTexto + " encontrada - linea: " + indice);

            }else{
                System.out.println("No se encontro en el archivo la pelicula: " + pelicula.getNombre());
                entrada.close();
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error al buscar en el archivo: " +e.getMessage());
        }
    }
}
