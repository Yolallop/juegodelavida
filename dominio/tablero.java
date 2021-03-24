package dominio;

import java.io.File.*;
import java.util.Scanner;
import java.io.IOException.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileReader.*;
import java.io.IOException;
	
	/**
	 * Esta clase es responsable de leer el tablero de un
	 * fichero en forma de ceros y unos, ir transitando de
	 * estados e ir mostrando dichos estados.
	 */
	 
public class tablero {

	private int DIMENSION = 32;
	private int[][] estadoActual;
	private int celdas;

	private int[][] estadoSiguiente = new int[DIMENSION][DIMENSION];

	/** La secuencia de ceros y unos del fichero es guardada
	 * en ‘estadoActual‘ y, utilizando las reglas del juego
	 * de la vida, se insertan los ceros y unos
	 * correspondientes en ‘estadoSiguiente‘.
	 * Complejidad espacial: constante 
	 * Compejidad temporal: 0(n)
	 */
	

	
	public void leerEstadoActual() {

		estadoActual = new int[DIMENSION][DIMENSION];
		

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
		/**La secuencia de ceros y unos generada es guardada
		 *en ‘estadoActual‘ y, utilizando las reglas del juego
		 * de la vida, se insertan los ceros y unos
		 *correspondientes en ‘estadoSiguiente‘.
		 * Complejidad espacial: constante 
		 * Compejidad temporal : 0(n^2)
		 */

	public void generarEstadoActualPorMontecarlo() {

		estadoActual = new int[DIMENSION][DIMENSION]; 
		for (int i = 0; i < (DIMENSION); i++) {

			for (int j = 0; j < (DIMENSION); j++) {
				estadoActual[i][j] = 0;

			}
		}

		for (int i = 1; i < DIMENSION - 1; i++) {
		
			
			for (int j = 1; j < DIMENSION - 1; j++) {
				if (Math.random() < 0.5) { 
					estadoActual[i][j] = 0;
				}

				else {
					estadoActual[i][j] = 1;
				}

			}
		}

	}
	
	/**La variable ‘estadoActual‘ pasa a tener el contenido
	* de ‘estadoSiguiente‘ y, éste útimo atributo pasar a
  	*reflejar el siguiente estado.
  	* * Complejidad espacial constante 
	 * Compejidad temporal 0(n^2)
  	*/


	public void transitarAlEstadoSiguiente() {

		for (int i = 1; i < DIMENSION - 1; i++) { 
			for (int j = 1; j < DIMENSION - 1; j++) {
				int vecinasvivas = 0;
				estadoSiguiente[i][j] = estadoActual[i][j];
				if (estadoActual[i - 1][j] == 1) {
					vecinasvivas++;
				} 
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
				
			
				
				if (estadoActual[i][j] == 0) {

					if (vecinasvivas == 3) {
						estadoSiguiente[i][j] = 1;
					}
				} else {
					if (vecinasvivas != 2 && vecinasvivas != 3) {
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

	
	@Override
	public String toString() {

		String abc = "\n *** TABLERO ***\n";
	

		for (int i = 0; i < DIMENSION; i++) {
			for (int j = 0; j < DIMENSION; j++) {
				abc = abc + estadoActual[i][j];

			}
			abc = abc + "\n";
		}

		return abc; 

	}
}
