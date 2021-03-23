package dominio;

import java.util.concurrent.TimeUnit;



	public class principal{
	public static void main(String[] args){
	try
	{
	tablero t = new tablero();
	System.out.println("SIMULACIÓN CON TABLERO LEÍDO");
	t.leerEstadoActual();
	System.out.println(t);
	for(int i = 0; i <= 5; i++)
	{
	TimeUnit.SECONDS.sleep(1);
	t.transitarAlEstadoSiguiente();
	System.out.println(t);
	}
	System.out.println("SIMULACIÓN CON TABLERO GENERADO MEDIANTE MONTECARLO");
	t.generarEstadoActualPorMontecarlo();
	System.out.println(t);
	for(int i = 0; i <= 15; i++)
	{
	TimeUnit.SECONDS.sleep(1);
	t.transitarAlEstadoSiguiente();
	System.out.println(t);
	}
	}catch(InterruptedException e)
	{
	System.out.println(e);
	}
	}
	}


