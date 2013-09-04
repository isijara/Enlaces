import java.util.*;
public class Enlaces {
	
	private Vector<Torre> vector_torres;
	private Hashtable< String, Torre > contenedor_torres;
	
	private ArchivoEntrada ArchivoEntrada;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Enlaces Enlaces = new Enlaces();
		
		for ( String instruccion : Enlaces.ArchivoEntrada.get_instrucciones()) {
			Enlaces.realizar_accion(Enlaces.ArchivoEntrada.obtener_tipo_instruccion(instruccion), instruccion);
			
			System.out.println(instruccion);
		}
		
		
		/*
			System.out.println(Enlaces.contenedor_torres.containsKey("Hermosillo2"));
			System.out.println("Tama–o del hashmap torre " + Enlaces.contenedor_torres.size());
			
			Enumeration <String> claves = Enlaces.contenedor_torres.keys();
			while(claves.hasMoreElements()) {
				String claveActual = claves.nextElement();
				System.out.println(claveActual);
			}
		*/
	}
	
	public Enlaces() {
		this.vector_torres     = new Vector<Torre>();
		this.contenedor_torres = new Hashtable<String,Torre>(); //tal vez tenga que cambiarlo por el mismo objeto
		this.ArchivoEntrada    = new ArchivoEntrada("Enlaces.txt");
	}
	
	private void realizar_accion(String tipo_instruccion, String instruccion) {
		
		if( tipo_instruccion.equals("EnunciadoCrearEnlace") ) {
			this.crear_enlace(instruccion);
		} else if( tipo_instruccion.equals("EnunciadoRemoverEnlace") ) {
			this.remover_enlace(instruccion);
		} else{
			this.buscar_torre(instruccion);
		}
	}
	
	
	
	private void crear_torre(String nombre) {
		Torre torre = new Torre(nombre);
		this.contenedor_torres.put(nombre, torre);
		System.out.println("He creado la torre "+ nombre +" "+this.contenedor_torres.containsKey(nombre));
	}
	
	
	private void crear_enlace( String instruccion ) {
		String torres [] = this.ArchivoEntrada.obtener_nombres_de_torres(instruccion);
		
		
		for(String torre: torres) {
			if( !this.contenedor_torres.containsKey(torre)) {
				this.crear_torre(torre);
			}
		}
	}
	
	private void remover_enlace( String instruccion ) {
		
	}

	private void buscar_torre( String instruccion ) {
		
	}
}
