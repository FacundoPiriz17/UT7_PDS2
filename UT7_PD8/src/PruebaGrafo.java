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

        TGrafoDirigido gd2 = UtilGrafos.cargarGrafo("./src/aeropuertos.txt","./src/conexiones.txt",
                false, TGrafoDirigido.class);
        Double[][] matriz2 = UtilGrafos.obtenerMatrizCostos(gd2.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz2, gd2.getVertices(), "Matriz");
        Double[][] mfloyd2 = gd2.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd2, gd2.getVertices(), "Matriz luego de FLOYD");
        System.out.println(gd2.centroDelGrafo());
        System.out.println();
        //Ejercicio 3, PD3
        gd2.bpf();
        System.out.println();
        gd2.bpf("Montevideo");
        TCaminos caminos = gd2.todosLosCaminos("Montevideo","Rio_de_Janeiro");
        System.out.println();
        caminos.imprimirCaminosConsola();
        System.out.println();
        TipoListaArcos arcosArbol = new TipoListaArcos();
        TipoListaArcos arcosRetroceso = new TipoListaArcos();
        TipoListaArcos arcosAvance = new TipoListaArcos();
        TipoListaArcos arcosCruzados = new TipoListaArcos();
        gd2.clasificarArcos("Rio_de_Janeiro", arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
        System.out.println("Imprimir Arcos de árbol: ");
        arcosArbol.imprimir();
        System.out.println("Imprimir Arcos de retroceso: ");
        arcosRetroceso.imprimir();
        System.out.println("Imprimir Arcos de avance: ");
        arcosAvance.imprimir();
        System.out.println("Imprimir Arcos de cruzados: ");
        arcosCruzados.imprimir();
    }
}
