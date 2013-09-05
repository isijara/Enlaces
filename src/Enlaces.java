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
			
			//System.out.println(instruccion);
		}
		
		/*
			System.out.println(Enlaces.contenedor_torres.containsKey("Hermosillo2"));
			System.out.println("Tama�o del hashmap torre " + Enlaces.contenedor_torres.size());
			Enumeration <String> claves = Enlaces.contenedor_torres.keys();
			while(claves.hasMoreElements()) {
				String claveActual = claves.nextElement();
				System.out.println(claveActual);
			}
		*/
		/*
			System.out.println();

			System.out.println("Estos son mis enlaces de mi torre Culiac�n ");
			for( Torre element : Enlaces.contenedor_torres.get("Culiacan1").get_vector_enlaces()){
				
				System.out.println(element.get_nombre());
				
			}
		*/
	}
	
	public Enlaces() {
		this.vector_torres     = new Vector<Torre>();
		this.contenedor_torres = new Hashtable<String,Torre>(); //tal vez tenga que cambiarlo por el mismo objeto
		this.ArchivoEntrada    = new ArchivoEntrada("Enlaces.txt");
	}
	
	/*
	 * M�todo que se encarga de ejecutar un tipo de acci�n en base a la instrucci�n y su tipo.
	 */
	private void realizar_accion(String tipo_instruccion, String instruccion) {
		if( tipo_instruccion.equals("EnunciadoCrearEnlace") ) {
			this.crear_enlace(instruccion);
		} else if( tipo_instruccion.equals("EnunciadoRemoverEnlace") ) {
			this.remover_enlace(instruccion);
		} else {
			this.buscar_torre(instruccion);
		}
	}
	
	/*
	 * M�todo destinado a la creaci�n de torres y de su agregaci�n al contenedor. 
	 */
	private void crear_torre(String nombre) {
		Torre torre = new Torre(nombre);
		this.contenedor_torres.put(nombre, torre);
	}
	
	
	/*
	 * crear_enlace(String instruccion) {}
	 * Recibe como par�metro una instrucci�n y se encarga de detectar la direci�n de creaci�n del enlace de comunicaci�n y lo crea.
	 * Si no existen las torres de comunicaci�n las almacena en el contenedor de torres
	 */
	private void crear_enlace( String instruccion ) {
		
		String torres [] = this.ArchivoEntrada.obtener_nombres_de_torres(instruccion);
		String torre_origen = torres[0], torre_destino = torres[1];

		for(String torre: torres) {
			if( !this.contenedor_torres.containsKey(torre)) {
				this.crear_torre(torre);
			}
		}
		
		int tipo_creacion = instruccion.indexOf("->");
		Torre TorreOrigen = ( tipo_creacion > 0) ? this.contenedor_torres.get(torre_origen) : this.contenedor_torres.get(torre_destino);
		Torre TorreDestino= ( tipo_creacion > 0) ? this.contenedor_torres.get(torre_destino) : this.contenedor_torres.get(torre_origen);
		
		TorreOrigen.get_vector_enlaces().add( TorreDestino );
	
	}
	
	private void remover_enlace( String instruccion ) {
		String torres [] = this.ArchivoEntrada.obtener_nombres_de_torres(instruccion);
		String torre_origen = torres[0], torre_destino = torres[1];
		if( this.existen_ambas_torres(torre_origen, torre_destino) ) {
			this.contenedor_torres.get(torre_origen).remover_enlace(this.contenedor_torres.get(torre_destino));			
		}
	}
	
	private boolean existen_ambas_torres(String torre_a, String torre_b) {
		return (this.contenedor_torres.containsKey(torre_a) && this.contenedor_torres.containsKey(torre_b));
	}
	
	private void buscar_torre( String instruccion ) {
		String torres [] = this.ArchivoEntrada.obtener_nombres_de_torres(instruccion);
		String torre_origen = torres[0], torre_destino = torres[1];
		int busqueda_de_izquierda_a_derecha= instruccion.indexOf("=>");
		Torre Origen = ( busqueda_de_izquierda_a_derecha > 0 ) ? this.contenedor_torres.get(torre_origen) : this.contenedor_torres.get(torre_destino);
		Torre Destino= ( busqueda_de_izquierda_a_derecha > 0 ) ? this.contenedor_torres.get(torre_destino) : this.contenedor_torres.get(torre_origen);  
		
		Vector <Torre> ruta_prueba = new Vector<Torre>(); 
		ruta_prueba.add(Origen);
		Origen.set_vector_ruta(Origen.buscar_torre(Destino, ruta_prueba) );
		
		String ruta = (Origen.get_estatus_busqueda() == true) ? "+" : "-";
		
		if( Origen.get_estatus_busqueda()) {
			for(int i = 0; i<Origen.get_vector_ruta().size(); i++) {
				ruta += ( (i+1) != Origen.get_vector_ruta().size()) ? Origen.get_vector_ruta().get(i).get_nombre() + "=>" :Origen.get_vector_ruta().get(i).get_nombre();
			}
		} else {
			ruta+= torre_origen+ " => "+torre_destino;
		}

		
		
		
		System.out.println(ruta);
	
	}
}
