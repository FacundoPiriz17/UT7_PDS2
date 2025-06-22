package uy.edu.ucu.aed.tas;

import uy.edu.ucu.aed.tdas.*;

import java.util.Collection;

public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Collection<IVertice> vertices = new java.util.ArrayList<>();
        vertices.add(new TVertice("A"));
        vertices.add(new TVertice("B"));
        vertices.add(new TVertice("C"));
        vertices.add(new TVertice("D"));

        Collection<IArista> aristas = new java.util.ArrayList<IArista>() {
            {
                add(new TArista("A", "B", 1.0));
                add(new TArista("A", "C", 2.0));
                add(new TArista("B", "C", 3.0));
                add(new TArista("C", "D", 4.0));
                add(new TArista("B", "A", 5.0));
            }
        };
        TGrafoDirigido grafo = new TGrafoDirigido(vertices, aristas);

        UtilGrafos.imprimirMatrizMejorado(grafo.floyd(), grafo.getVertices(), "Floyd");

        System.out.println("Excentricidad: \n" + grafo.obtenerExcentricidad("A"));
        UtilGrafos.imprimirMatrizWarshall(grafo.warshall(), grafo.getVertices(), "Warshall");

        System.out.println("Vertices visitados en BPF:");
        for (IVertice vertice : grafo.bpf("A")) {
            System.out.println(vertice.getEtiqueta());
        }
        System.out.println("Vertices visitados en BPF:");
        for (IVertice vertice : grafo.bpf()) {
            System.out.println(vertice.getEtiqueta());
        }

        System.out.println();

        TGrafoDirigido gd = UtilGrafos.cargarGrafo("./src/aeropuertos_2.txt", "./src/conexiones_2.txt",
                false, TGrafoDirigido.class);
        // TCaminos caminos = gd.todosLosCaminos("Rio_de_Janeiro", "Rio_de_Janeiro");
        /* System.out.println(caminos.imprimirCaminos()); */
        System.out.println(gd.tieneCiclo());
        System.out.println(gd.centroDelGrafo());
        UtilGrafos.imprimirMatrizMejorado(gd.floyd(), gd.getVertices(), "Floyd gd");

    }
}
