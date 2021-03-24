package dominio;

import java.io.File.*;
import java.util.Scanner;
import java.io.IOException.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileReader.*;
import java.io.IOException;

public class tablero {

	private int DIMENSION = 32;
	private int[][] estadoActual;
	private int celdas;

	private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];

	/********************************************************
	 * Lee el estado inicial de un fichero llamado ‘matriz‘.
	 ********************************************************/
	public void leerEstadoActual() {

		estadoActual = new int[DIMENSION][DIMENSION];
		// int [][] declarar vacio
		// new crear objeto reservas espacio

		try {
			File ficherodatos = new File("matriz");

			Scanner fichero = new Scanner(ficherodatos);
			String line = "";
			for (int i = 0; i < (DIMENSION -1); i++) {
				
				if (i != 0 && i != DIMENSION ) {
					 line = fichero.nextLine();
				}
				for (int j = 0; j < (DIMENSION); j++) {

					if (i == 0 || j == 0 || i == DIMENSION-1|| j == DIMENSION-1) {
						estadoActual[i][j] = 0;

					} else {
						estadoActual[i][j] = line.charAt(j-1) -'0';
					}

					

				}
			}
			fichero.close();
		}
		

		catch (IOException e) {
			System.out.println("Ha saltado una excepción");
			System.out.println(e.getMessage());
		}

	}

	// La secuencia de ceros y unos del fichero es guardada
	// en ‘estadoActual‘ y, utilizando las reglas del juego
	// de la vida, se insertan los ceros y unos
	// correspondientes en ‘estadoSiguiente‘.

	/********************************************************
	 * Genera un estado inicial aleatorio. Para cada celda genera un número
	 * aleatorio en el intervalo [0, 1). Si el número es menor que 0,5, entonces la
	 * celda está inicialmente viva. En caso contrario, está muerta.
	 *******************************************************/
	public void generarEstadoActualPorMontecarlo() {

		estadoActual = new int[DIMENSION][DIMENSION]; // reservar espacio para una matriz dimvimensional
		for (int i = 0; i < (DIMENSION); i++) {

			for (int j = 0; j < (DIMENSION); j++) {
				estadoActual[i][j] = 0;

			}
		}

		for (int i = 1; i < DIMENSION - 1; i++) {
			// solo una vez i= 0 despeus i=1 recorer todads las posiciones de la
			// matriz
			for (int j = 1; j < DIMENSION - 1; j++) {
				if (Math.random() < 0.5) { // numero aleatorio entre o i 1
					estadoActual[i][j] = 0;
				}

				else {
					estadoActual[i][j] = 1;
				}

			}
		}

	}

//celula muera
	// o viva
	// La secuencia de ceros y unos generada es guardada
	// en ‘estadoActual‘ y, utilizando las reglas del juego
	// de la vida, se insertan los ceros y unos
	// correspondientes en ‘estadoSiguiente‘.

	/********************************************************
	 * Transita al estado siguiente según las reglas del juego de la vida.
	 * 
	 *******************************************************/
	public void transitarAlEstadoSiguiente() {

		for (int i = 1; i < DIMENSION - 1; i++) { // i=0; j<Dimension;j++ esto es con las de la esquina
			for (int j = 1; j < DIMENSION - 1; j++) {// y lo que ponemos es solo el tablero de dentro por eso el -1
				int vecinasvivas = 0;
				estadoSiguiente[i][j] = estadoActual[i][j];
				if (estadoActual[i - 1][j] == 1) {
					vecinasvivas++;
				} // inc para contar-
				if (estadoActual[i][j + 1] == 1) {
					vecinasvivas++;
				}
				if (estadoActual[i + 1][j] == 1) {
					vecinasvivas++;
				}
				if (estadoActual[i][j - 1] == 1) {
					vecinasvivas++;
				}
				//
				if (estadoActual[i-1][j + 1] == 1) {
					vecinasvivas++;
				}
				if (estadoActual[i+1][j - 1] == 1) {
					vecinasvivas++;
				}
				if (estadoActual[i+1][j + 1] == 1) {
					vecinasvivas++;
				}
				if (estadoActual[i-1][j - 1] == 1) {
					vecinasvivas++;
				}
				
			
				
				if (estadoActual[i][j] == 0) {// si esta muerta 3 vcias vivas vive

					if (vecinasvivas == 3) {
						estadoSiguiente[i][j] = 1;
					}
				} else {// Si una célula está viva y dos o tres de sus vecinas también lo están,
						// entonces continúa viva en el estado siguiente estado actual 1 else ya lo hace
						// pq despues d eif
					if (vecinasvivas != 2 && vecinasvivas != 3) {// diferente de 2 y de 3
						estadoSiguiente[i][j] = 0;

					}
				}
			}
		}
		for (int i = 1; i < DIMENSION; i++) { // i=0; j<Dimension;j++ esto es con las de la esquina
			for (int j = 1; j < DIMENSION; j++) {
				estadoActual[i][j] = estadoSiguiente[i][j];

			}
		}
	}

	// La variable ‘estadoActual‘ pasa a tener el contenido
	// de ‘estadoSiguiente‘ y, éste útimo atributo pasar a
	// reflejar el siguiente estado.
	/*******************************************************
	 * Devuelve, en modo texto, el estado actual.
	 * 
	 * @return el estado actual.
	 *******************************************************/
	@Override
	public String toString() {

		String abc = "\n *** TABLERO ***\n";
		// cabecera

		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				abc = abc + estadoActual[i][j];

			}
			abc = abc + "\n";
		}

		return abc; // Esta línea hay que modificarla.// te genera un string con la informacion de
					// el objeo lo que quieres imprimir y lo devuelve

	}
}




