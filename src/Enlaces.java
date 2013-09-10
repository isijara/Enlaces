import java.util.*;

/**
 * Clase que contiene el main del programa. Esta clase tiene el contenedor de torres.
 * @author Ram&oacuten Isijara / Margarita Aranda
 */
public class Enlaces {
	//pops
	/**
	 * Variable que contiene las torres que se van registrando mediante las instrucciones del archivo de entrada.
	 */
	private Hashtable< String, Torre > contenedor_torres;
	
	/**
	 * Variable de instancia de tipo ArchivoEntrada
	 */
	private ArchivoEntrada ArchivoEntrada;

	/**
	 * Recorre todas las instrucciones de un archivo de entrada en caso de existir y realiza las acciones que indican
	 * las instrucciones.
	 * @param args
	 */
	public static void main(String[] args) {
		
		Enlaces Enlaces = new Enlaces();
		for ( String instruccion : Enlaces.ArchivoEntrada.get_instrucciones()) {
			Enlaces.realizar_accion(Enlaces.ArchivoEntrada.obtener_tipo_instruccion(instruccion), instruccion);
		}
	}
	
	/**
	 * Constructor Enlaces
	 */
	public Enlaces() {
		this.contenedor_torres = new Hashtable<String,Torre>();
		this.ArchivoEntrada    = new ArchivoEntrada("Enlaces 1 MCC 2013.txt");
	}
	
	/**
	 * M&eacutetodo que se encarga de ejecutar un tipo de acci&oacuten en base a la instrucci&oacuten y su tipo.
	 * @param tipo_instruccion Es el tipo de instrucci&oacuten, crear de enlace, remover enlace o realizar b&uacutesqueda.
	 * @param instruccion Es la isntrucci&oacuten del archivo de entrada que se encuentra en proceso.
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
	
	/**
	 * M&eacutetodo destinado a la creaci&oacuten de torres y de su agregaci&oacuten al contenedor. 
	 * @param nombre Es el nombre con el que se crear&aacute la torre
	 */
	private void crear_torre(String nombre) {
		Torre torre = new Torre(nombre);
		this.contenedor_torres.put(nombre, torre);
	}
	
	
	/**
	 * Recibe como par&aacutemetro una instrucci&oacuten y se encarga de detectar la direci&oacuten de creaci&oacuten del enlace de comunicaci&oacuten y lo crea.
	 * Si no existen las torres de comunicaci&oacuten las almacena en el contenedor de torres
	 * @param instruccion Es la isntrucci&oacuten del archivo de entrada que se encuentra en proceso.
	 */
	private void crear_enlace( String instruccion ) {
		
		int tipo_creacion = instruccion.indexOf("->");
		
		String torres [] = this.ArchivoEntrada.obtener_nombres_de_torres(instruccion);
		String torre_origen = ( tipo_creacion > 0 ) ? torres[0] : torres[1];
		String torre_destino= ( tipo_creacion > 0 ) ? torres[1] : torres[0];

		for(String torre: torres) {
			if( !this.contenedor_torres.containsKey(torre)) {
				this.crear_torre(torre);
			}
		}
		
		Torre TorreOrigen = this.contenedor_torres.get(torre_origen);
		Torre TorreDestino= this.contenedor_torres.get(torre_destino);
		
		TorreOrigen.agregar_enlace(TorreDestino);//.add( TorreDestino );
	
	}
	

	/**
	 * M&eacutetodo utiliz&eacutedo para delegar a un objeto Torre la responsabilidad de eliminar un enlace a otra torre.
	 * @param instruccion Es la isntrucci&oacuten del archivo de entrada que se encuentra en proceso.
	 */
	private void remover_enlace( String instruccion ) {
		String torres [] = this.ArchivoEntrada.obtener_nombres_de_torres(instruccion);
		String torre_origen = torres[0], torre_destino = torres[1];
		
		if( this.existen_ambas_torres(torre_origen, torre_destino) ) {
			Torre Destino = this.contenedor_torres.get(torre_destino);
			this.contenedor_torres.get(torre_origen).remover_enlace(Destino);			
		}
	}
	
	/**
	 * Este m&eacutetodo se encarga de identificar si existen las torres enviadas como par&aacutemetros en nuestro contenedor de torres.
	 * @param torre_a Variable tipo Torre
	 * @param torre_b Variable tipo Torre
	 * @return boolean
	 */
	private boolean existen_ambas_torres(String torre_a, String torre_b) {
		return (this.contenedor_torres.containsKey(torre_a) && this.contenedor_torres.containsKey(torre_b));
	}
	
	
	/**
	 * M&eacutetodo que se encarga de en base a una isntancia de Torre determinar si esta tiene comunicaci&oacuten 
	 * con otra Torre. Recibe como par&aacutemetro una instrucci&oacuten de b&uacutesqueda.
	 * Imprime en consola el resultado.
	 * @param instruccion Es la isntrucci&oacuten del archivo de entrada que se encuentra en proceso.
	 */
	private void buscar_torre( String instruccion ) {
		String torres [] = this.ArchivoEntrada.obtener_nombres_de_torres(instruccion);
		
		int busqueda_de_izquierda_a_derecha = instruccion.indexOf("=>");
		
		String torre_origen = ( busqueda_de_izquierda_a_derecha > 0 ) ? torres[0] : torres[1];
		String torre_destino= ( busqueda_de_izquierda_a_derecha > 0 ) ? torres[1] : torres[0];
		
		Torre Origen = this.contenedor_torres.get(torre_origen);
		Torre Destino= this.contenedor_torres.get(torre_destino);  
		
		Vector <Torre> ruta_prueba = new Vector<Torre>(); 
		ruta_prueba.add(Origen);
		
		if(Destino == null || Origen == null  ) {
			System.out.println("-"+torre_origen + " => " + torre_destino);
			return;
		}
		
		/* En caso de no querer hacer toda la b&uacutesqueda
			if( Origen == Destino ) {
				//System.out.println("+" + torre_origen);
				//return;
			}
		*/
		
		Origen.set_estatus_busqueda(false);
		Origen.set_vector_ruta(Origen.buscar_torre(Destino, ruta_prueba) );
		
		String ruta = (Origen.get_estatus_busqueda() == true) ? "+" : "-";
		
		if( Origen.get_estatus_busqueda()) {
			for(int i = 0; i<Origen.get_vector_ruta().size(); i++) {
				ruta += ( (i+1) != Origen.get_vector_ruta().size()) ? Origen.get_vector_ruta().get(i).get_nombre() + " => " :Origen.get_vector_ruta().get(i).get_nombre();
			}
		} else {
			ruta+= torre_origen+ " => "+torre_destino;
		}

		System.out.println(ruta);
	
	}
}
