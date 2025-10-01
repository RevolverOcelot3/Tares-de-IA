// App.java
public class App {

    public static void main(String[] args) {
        Arbol arbol = new Arbol();

        arbol.insertar("Lucas");
        arbol.insertar("Ana");
        arbol.insertar("Sofia");
        arbol.insertar("Pedro");
        arbol.insertar("Camila");
        arbol.insertar("martin");
        arbol.insertar("Zoe");
        arbol.insertar("Bruno");
        arbol.insertar("ana"); 

        arbol.imprimirArbol();

        String[] pruebas = {"Camila", "camila", "Ximena", "Lucas", "zoe"};
        for (String nombre : pruebas) {
            Nodo n = arbol.buscarNodo(nombre);
            System.out.println("buscarNodo(\"" + nombre + "\") -> " + (n == null ? "no encontrado" : "encontrado: " + n));
        }

        System.out.println("vacio() -> " + arbol.vacio());
    }

    static class Nodo {
        String nombre;
        Nodo izquierdo;
        Nodo derecho;

        Nodo(String nombre) {
            this.nombre = nombre;
        }

        @Override
        public String toString() {
            return nombre;
        }
    }

    static class Arbol {
        private Nodo raiz;

        public boolean vacio() {
            return raiz == null;
        }

        public void insertar(String nombre) {
            raiz = insertarRec(raiz, nombre);
        }

        private Nodo insertarRec(Nodo actual, String nombre) {
            if (actual == null) return new Nodo(nombre);

            int cmp = nombre.compareToIgnoreCase(actual.nombre);
            if (cmp < 0) {
                actual.izquierdo = insertarRec(actual.izquierdo, nombre);
            } else if (cmp > 0) {
                actual.derecho = insertarRec(actual.derecho, nombre);
            } 
            return actual;
        }

        public Nodo buscarNodo(String nombre) {
            return buscarRec(raiz, nombre);
        }

        private Nodo buscarRec(Nodo actual, String nombre) {
            if (actual == null) return null;
            int cmp = nombre.compareToIgnoreCase(actual.nombre);
            if (cmp == 0) return actual;
            if (cmp < 0) return buscarRec(actual.izquierdo, nombre);
            return buscarRec(actual.derecho, nombre);
        }

        public void imprimirArbol() {
            if (raiz == null) {
                System.out.println("(árbol vacío)");
                return;
            }
            System.out.println("Árbol (vista lateral, ramas derechas arriba):");
            imprimirEstructura(raiz, "", true);

            System.out.println("\nRecorrido in-orden:");
            imprimirInOrden(raiz);
            System.out.println();
        }

        private void imprimirEstructura(Nodo nodo, String prefijo, boolean esDerecha) {
            if (nodo == null) return;

            imprimirEstructura(nodo.derecho, prefijo + (esDerecha ? "    " : "│   "), true);

            System.out.println(prefijo + (esDerecha ? "└── " : "┌── ") + nodo.nombre);

            imprimirEstructura(nodo.izquierdo, prefijo + (esDerecha ? "    " : "│   "), false);
        }

        private void imprimirInOrden(Nodo nodo) {
            if (nodo == null) return;
            imprimirInOrden(nodo.izquierdo);
            System.out.print(nodo.nombre + " ");
            imprimirInOrden(nodo.derecho);
        }
    }
}
