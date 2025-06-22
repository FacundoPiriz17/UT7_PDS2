import java.util.LinkedList;

public class TipoListaArcos {

    private LinkedList<TArista> listaArcos;

    public TipoListaArcos() {
        listaArcos = new LinkedList<>();
    }

    public void insertar(TArista arco) {
        listaArcos.add(arco);
    }

    public void imprimir() {
        System.out.println(imprimir(listaArcos));
    }

    private String imprimir(LinkedList<TArista> listaArcos) {
        String res = "";
        for (TArista arco : listaArcos) {
            res += "(" + arco.getEtiquetaOrigen() + "," + arco.getEtiquetaDestino()+")" + " ";
        }
        return res;
    }
}
