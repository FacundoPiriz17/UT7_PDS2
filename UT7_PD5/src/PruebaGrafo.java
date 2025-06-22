import jdk.jshell.execution.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido gd = UtilGrafos.cargarGrafo("./src/Vertices.txt","./src/Aristas.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        Double[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");

        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());
        System.out.println("Tiene ciclo: " + gd.tieneCiclo());
        System.out.println();

        TGrafoDirigido gd2 = UtilGrafos.cargarGrafo("./src/aeropuertos.txt","./src/conexiones.txt",
                false, TGrafoDirigido.class);
        Double[][] matriz2 = UtilGrafos.obtenerMatrizCostos(gd2.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz2, gd2.getVertices(), "Matriz");
        Double[][] mfloyd2 = gd2.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd2, gd2.getVertices(), "Matriz luego de FLOYD");
        System.out.println(gd2.centroDelGrafo());
        System.out.println();
        System.out.println("Tiene ciclo: " + gd.tieneCiclo());
        System.out.println();
        //Ejercicio 3, PD3
        gd2.bpf();
        System.out.println();
        gd2.bpf("Montevideo");
        TCaminos caminos = gd2.todosLosCaminos("Montevideo","Rio_de_Janeiro");
        System.out.println();
        caminos.imprimirCaminosConsola();
        //Prueba TieneCiclo
        List<TVertice> vertices = Arrays.asList(new TVertice<>("1"),new TVertice<>("2"),new TVertice<>("3"));
        List<TArista> aristas = Arrays.asList(new TArista("1","2",1), new TArista("2","3",2));
        TGrafoDirigido gd3 = new TGrafoDirigido(vertices,aristas);
        System.out.println(gd3.tieneCiclo());
        System.out.println("---------------");
        System.out.println(gd2.obtenerComponentesFuertementeConexos());
        System.out.println(gd2.esFuertementeConexo());
    }
}
