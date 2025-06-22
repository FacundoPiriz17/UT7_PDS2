
import java.util.*;

public class TGrafoDirigido implements IGrafoDirigido {

    private Map<Comparable, TVertice> vertices;

    public TGrafoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     */
    public boolean eliminarArista(Comparable nomVerticeOrigen, Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }


    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeArista(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     * <p>
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    public boolean existeVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     * <p>
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    private TVertice buscarVertice(Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     * <p>
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override

    public boolean insertarVertice(TVertice vertice) {
        Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    public Comparable centroDelGrafo() {
        Iterator<TVertice> it = vertices.values().iterator();
        Comparable[] excentricidades = new Comparable[vertices.size()];
        Comparable centro = Double.MAX_VALUE;
        Comparable etiqueta_centro = null;
        int i = 0;
        while (it.hasNext()) {
            Comparable a = excentricidades[i];
            Comparable etiqueta_vertice = it.next().getEtiqueta();

            a = this.obtenerExcentricidad(etiqueta_vertice);
            if (a.compareTo(centro) == -1) {
                centro = a;
                etiqueta_centro = etiqueta_vertice;
            }
        }
        return etiqueta_centro + " (" + centro.toString().trim() + ")";
    }

    @Override
    public Double[][] floyd() {
        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(vertices);
        for (int k = 0; k < matriz.length; k++) {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz.length; j++) {
                    if (i != j && i != k && k != j) {
                        if (matriz[i][k] + matriz[k][j] < matriz[i][j]) {
                            matriz[i][j] = matriz[i][k] + matriz[k][j];
                            //matrizRetroceso[i][j]=Double.parseDouble(Integer.toString(k));
                        }
                    }
                }
            }
        }


        return matriz;
    }

    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Double[][] matriz = this.floyd();
        Set<Comparable> etiquetasVertices = this.vertices.keySet();
        Comparable[] array = new Comparable[matriz.length];
        array = etiquetasVertices.toArray(array);
        int columna = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == etiquetaVertice) {
                columna = i;
                break;
            }
        }
        Double ex = 0.0;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][columna] > ex && matriz[i][columna] < Double.MAX_VALUE && matriz[i][columna] > 0.0) {
                ex = matriz[i][columna];
            }
        }
        if (ex == 0.0) {
            ex = Double.MAX_VALUE;
        }
        return ex;
    }

    @Override
    public boolean[][] warshall() {
        Double[][] matrizCostos = UtilGrafos.obtenerMatrizCostos(vertices);
        int n = matrizCostos.length;

        // Paso 1: construir la matriz de adyacencia
        boolean[][] matrizAdyacencia = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (matrizCostos[i][j] != null && !matrizCostos[i][j].equals(Double.MAX_VALUE)) {
                    matrizAdyacencia[i][j] = true;
                }
            }
        }

        // Paso 2: inicializar matrizWarshall con la matriz de adyacencia
        boolean[][] matrizWarshall = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrizWarshall[i][j] = matrizAdyacencia[i][j];
            }
        }

        // Paso 3: aplicar el algoritmo de Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!matrizWarshall[i][j]) {
                        matrizWarshall[i][j] = matrizWarshall[i][k] && matrizWarshall[k][j];
                    }
                }
            }
        }

        return matrizWarshall;
    }

    @Override
    public boolean eliminarVertice(Comparable nombreVertice) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void desvisitarVertices() {
        for (TVertice vertice : this.vertices.values()) {
            vertice.setVisitado(false);
        }
    }

    public Collection<TVertice> bpf(TVertice vertice) {
        this.desvisitarVertices();
        Collection<TVertice> visitados = new LinkedList<TVertice>();

        if (this.existeVertice(vertice.getEtiqueta())) {
            TVertice vert = this.buscarVertice(vertice.getEtiqueta());
            vert.bpf(visitados);
        }
        return visitados;
    }

    public Collection<TVertice> bpf() {
        Collection<TVertice> listaBpf = new LinkedList<TVertice>();
        this.desvisitarVertices();

        if (vertices.isEmpty()) {
            System.out.println("El grafo está vacio");
        } else {
            for (TVertice vertV : vertices.values()) {
                if (!vertV.getVisitado()) {
                    vertV.bpf(listaBpf);
                }
            }
        }
        return listaBpf;
    }

    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        this.desvisitarVertices();
        Collection<TVertice> visitados = new LinkedList<TVertice>();

        if (this.existeVertice(etiquetaOrigen)) {
            TVertice vertice = this.buscarVertice(etiquetaOrigen);
            vertice.bpf(visitados);
        }
        return visitados;
    }

    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        desvisitarVertices();
        TVertice v = buscarVertice(etiquetaOrigen);
        TVertice u = buscarVertice(etiquetaDestino);
        if ((v != null) && (u != null)) {
            TCaminos todosLosCaminos = new TCaminos();
            TCamino caminoPrevio = new TCamino(v);
            v.todosLosCaminos(etiquetaDestino, caminoPrevio, todosLosCaminos);
            return todosLosCaminos;
        }
        return null;
    }

    public boolean tieneCiclo() {
        desvisitarVertices();
        boolean res = false;

        for (TVertice vertV : vertices.values()) {
            if (!vertV.getVisitado()) {
                LinkedList camino = new LinkedList();
                camino.add(vertV.getEtiqueta());
                res = vertV.tieneCiclo(camino);
                if (res) {
                    return true;
                }
            }
        }
        return res;
    }

    public LinkedList<TVertice> ordenTopologico() {
        desvisitarVertices();
        LinkedList lista = new LinkedList();
        for (TVertice vertV : vertices.values()) {
            if (!vertV.getVisitado()) {
                vertV.ordenTopologico(lista);
            }
        }
        return lista;
    }

    public boolean esFuertementeConexo() {
        Comparable etiquetaInicial = vertices.keySet().iterator().next();
        desvisitarVertices();
        this.buscarVertice(etiquetaInicial).bpf(new LinkedList<>());

        for (TVertice v : vertices.values()) {
            if (!v.getVisitado()) {
                return false;
            }
        }

        TGrafoDirigido transpuesto = this.obtenerGrafoTranspuesto();
        transpuesto.desvisitarVertices();
        transpuesto.buscarVertice(etiquetaInicial).bpf(new LinkedList<>());

        for (TVertice v : transpuesto.getVertices().values()) {
            if (!v.getVisitado()) {
                return false;
            }
        }

        return true;
    }

    public List<List<Comparable>> obtenerComponentesFuertementeConexos() {
        Stack<TVertice> pila = new Stack<>();
        desvisitarVertices();

        for (TVertice v : vertices.values()) {
            if (!v.getVisitado()) {
                v.llenarPilaFinExploracion(pila);
            }
        }

        TGrafoDirigido transpuesto = this.obtenerGrafoTranspuesto();
        transpuesto.desvisitarVertices();

        List<List<Comparable>> componentes = new ArrayList<>();
        while (!pila.isEmpty()) {
            TVertice v = transpuesto.buscarVertice(pila.pop().getEtiqueta());
            if (!v.getVisitado()) {
                List<Comparable> componente = new ArrayList<>();
                v.extraerComponente(componente);
                componentes.add(componente);
            }
        }

        return componentes;
    }

    public TGrafoDirigido obtenerGrafoTranspuesto() {
        List<TVertice> nuevosVertices = new ArrayList<>();
        for (TVertice v : this.vertices.values()) {
            nuevosVertices.add(new TVertice(v.getEtiqueta()));
        }

        List<TArista> aristasTranspuestas = new ArrayList<>();
        for (TVertice<?> v : this.vertices.values()) {
            for (TAdyacencia ady : v.getAdyacentes()) {
                Comparable origen = ady.getDestino().getEtiqueta();
                Comparable destino = v.getEtiqueta();
                double costo = ady.getCosto();

                aristasTranspuestas.add(new TArista(origen, destino, costo));
            }
        }

        return new TGrafoDirigido(nuevosVertices, aristasTranspuestas);
    }




}