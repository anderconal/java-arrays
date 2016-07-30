
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author alumno
 */
public class GestionDVD {

    private static DVD[] dvd = new DVD[30];
    private static int numDvd = 0;
    private static String[] genero = new String[20];
    private static int[] cuenta = new int[20];
    private static int numGenero = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        inicializar();
        String respuesta;
        int opcion;

        do {

            respuesta = JOptionPane.showInputDialog(null, "1 - Nuevo DVD\n"
                    + "2 - Buscar DVD\n"
                    + "3 - Listar por género\n"
                    + "4 - Resumen\n"
                    + "5 - Salir\n\n"
                    + "Opción [1-5]:");

            opcion = Integer.parseInt(respuesta);

            switch (opcion) {
                case 1:
                    nuevoDvd();
                    break;
                case 2:
                    buscarDvd();
                    break;
                case 3:
                    listarGenero();
                    break;
                case 4:
                    Resumen();
                    break;
                case 5:

            }

        } while (opcion != 5);
    }

    private static void nuevoDvd() {
        String titulo, artista, genero;
        int i;
        titulo = JOptionPane.showInputDialog("Introducir título del DVD:");
        //BÚSQUEDA ORDENADA
        for (i = 0; i < numDvd && titulo.compareToIgnoreCase(dvd[i].getTitulo()) > 0; i++);

        if (i < numDvd && titulo.equalsIgnoreCase(dvd[i].getTitulo())) {
            JOptionPane.showMessageDialog(null, "PELÍCULA YA EXISTE", "ERROR", JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, "TÍTULO: " + dvd[i].getTitulo()
                    + "\nARTISTA: " + dvd[i].getArtista()
                    + "\nGÉNERO: " + dvd[i].getGenero());
        } else {
            artista = JOptionPane.showInputDialog("Introducir artista del DVD:");
            genero = JOptionPane.showInputDialog("Introducir genero del DVD:");

            dvd[i+1]=dvd[i];
            dvd[i] = new DVD(titulo, artista, genero);
            
            numDvd++;
        }
    }

    private static void buscarDvd() {
        String titulo, respuesta;
        int menor, mayor, medio;
        menor = 0;
        mayor = numDvd - 1;
        medio = (menor + mayor) / 2;

        titulo = JOptionPane.showInputDialog("Introducir título del DVD:");
        //BÚSQUEDA BINARIA       
        while (menor <= mayor && !titulo.equalsIgnoreCase(dvd[medio].getTitulo())) {

            if (titulo.compareToIgnoreCase(dvd[medio].getTitulo()) > 0) {
                menor = medio + 1;
            } else {
                mayor = medio - 1;
            }
            medio = (menor + mayor) / 2;

        }
        if (menor <= mayor) {
            JOptionPane.showMessageDialog(null, "PELÍCULA ENCONTRADA\n\n"
                    + "TÍTULO: " + dvd[medio].getTitulo()
                    + "\nARTISTA: " + dvd[medio].getArtista()
                    + "\nGÉNERO: " + dvd[medio].getGenero());
            respuesta = JOptionPane.showInputDialog(null, "¿DESEA REPETIR EL PROCESO?");

        } else {
            respuesta = JOptionPane.showInputDialog(null, "PELÍCULA NO ENCONTRADA\n\n"
                    + "¿DESEA REPETIR EL PROCESO?\n\n"
                    + "RESPUESTA [S/N]");
        }

        if (respuesta.equalsIgnoreCase("S") || respuesta.equalsIgnoreCase("SI")) {
            buscarDvd();
        }
    }

    private static void listarGenero() {
        String genero;
        int i;
        int contador = 0;
        genero = JOptionPane.showInputDialog("Introducir género a listar:");
        //BÚSQUEDA DESORDENADA
        for (i = 0; i < numDvd && !genero.equalsIgnoreCase(dvd[i].getGenero()); i++);

        if (i < numDvd) {
            for (i = 0; i < numDvd; i++) {
                if (dvd[i].getGenero().equalsIgnoreCase(genero)) {
                    System.out.println(
                            "TÍTULO: " + dvd[i].getTitulo()
                            + "\nARTISTA: " + dvd[i].getArtista()
                            + "\nGÉNERO: " + dvd[i].getGenero() + "\n");
                    contador++;
                }
            }
            System.out.println("NÚMERO TOTAL DE DVD DEL GÉNERO " + genero + " : " + contador + "\n");

        } else {
            JOptionPane.showMessageDialog(null, "GÉNERO NO ENCONTRADO", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void Resumen() {
        int i,j;
        
        for (i = 0; i < numDvd; i++) {
            
            for (j = 0;j< numGenero && !dvd[i].getGenero().equalsIgnoreCase(genero[j]); j++);

            if (j < numGenero) {
                cuenta[j] += 1;
            } else {
                genero[numGenero] = dvd[i].getGenero();
                cuenta[numGenero] = 1;
                numGenero++;
            }
        }
 
        System.out.println("GÉNERO\t\t"
                + "CUENTA\n"
                + "_ _ _ _ _ _ _\t"
                + "_ _ _ _ _ _ _ _ _\n");
        for (int l = 0; l < numGenero; l++) {
            System.out.println(genero[l]
                    +"\t\t" + cuenta[l]);
        }

    }

    private static void inicializar() {
        dvd[0] = new DVD("American Pie", "Jason Bigs", "Comedia");
        dvd[1] = new DVD("Death Proof", "Tarantino", "Terror");
        dvd[2] = new DVD("El Rey León", "Mufasa", "Animación");
        dvd[3] = new DVD("Insidious", "Patrick Wilson", "Terror");
        dvd[4] = new DVD("Kill Bill", "Uma Thurman", "Acción");
        dvd[5] = new DVD("Shrek", "José Mota", "Animación");
 
        numDvd = 6;
    }

}
