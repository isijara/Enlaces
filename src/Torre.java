import java.util.*;

/**
 * Encargada de operaciones b&aacutesicas de las torres como agregar o quitar enlaces, realizar la b&uacutesqueda de una torre.
 * @author Ram&oacuten Isijara / Margarita Aranda
 */
public class Torre {
	/**
	 * Variable que almacena el nombre de una torre.
	 */
	private String nombre;
	
	/**
	 * Variable encargada de almacenar las diferentes Torres con las que tiene comunicaci&oacuten la instancia. 
	 */
	private Vector <Torre>  vector_enlaces;
	
	/**
	 * Variable encargada de almacenar la ruta de comunicaci&oacuten de la TorreA a la TorreB en caso de existir la ruta.
	 */
	private Vector <Torre> vector_ruta ;
	
	/**
	 * Variable encargada de almacenar el estatus de la b&uacutesqueda de comunicaci&oacuten de una torre a otra. Almacenar&aacute verdadero
	 * si la ruta existe, falso de lo contrario.
	 */
	private boolean estatus_busqueda;
	
	
	/**
	 * Constructor Torre
	 * @param nombre Indica el nombre que tendr&aacute la torre.
	 */
	public Torre ( String nombre ) {
		this.nombre = nombre;
		this.vector_enlaces = new Vector<Torre> ();
		this.vector_ruta   = new Vector<Torre>();
		this.estatus_busqueda = false;
		this.vector_ruta.add(this);
	}
	
	
	/**
	 * M&eacutetodo encargado de realizar la b&uacutesqueda de comunicaci&oacuten de una torre a otra.
	 * @param objetivo Es el objeto torre objetivo de b&uacutesqueda.
	 * @param ruta_principal Par&aacutemetro auxiliar para almacenar la ruta de comunicaci&oacuten de una torre a otra.
	 * @return ruta_principal
	 */
	

	
	public Vector<Torre> buscar_torre(Torre objetivo, Vector<Torre> ruta_principal) { // este es el conejillo de indias			
		Torre torre_origen = ruta_principal.get(0);
		boolean enlace_repetido = false;
		int ultimo_indice;
		Torre ultima_torre;
		
		for( Torre T : this.vector_enlaces ) {
			ultimo_indice = ruta_principal.size()-1;
			ultima_torre = ruta_principal.get(ultimo_indice);
			
			if(ultima_torre == objetivo) {
				return ruta_principal;
			}
			
			if(  T == torre_origen && T != objetivo || ruta_principal.contains(T)) { 
				enlace_repetido = true;
				continue;
			}
			
			if(objetivo == T) {
				ruta_principal.add(T);
				torre_origen.estatus_busqueda = true;
				return ruta_principal;
			} else {
				ruta_principal.add(T);
				T.buscar_torre(objetivo, ruta_principal);
			}
		}
		
		ultimo_indice = ruta_principal.size()-1;
		ultima_torre= ruta_principal.get(ultimo_indice);
		
		if(ultima_torre == objetivo) {
			return ruta_principal;
		}
		
		if(this.vector_enlaces.size() == 0 ){
			ruta_principal.remove(ruta_principal.size()-1);
		} else{
			if(enlace_repetido) {
				ruta_principal.remove(ruta_principal.size()-1);
			}else
				if(!torre_origen.estatus_busqueda && ruta_principal.get(ruta_principal.size()-1) != objetivo ) { //agregué el negado de estatus busqueda
					ruta_principal.remove(ruta_principal.size()-1);
				}
		}
		
		return ruta_principal;
	}
	
	
		
	/**
	 * M&eacutetodo encargado de agregar una torre al vector de enlaces de un objeto torre.
	 * @param TorreParaEnlazar Es la torre que en caso de no existir en vector_enlaces se agrega a este.
	 */
	public void agregar_enlace( Torre TorreParaEnlazar ) {
		if(this != TorreParaEnlazar) {
			this.vector_enlaces.add(TorreParaEnlazar);
		}
	}
	
	/**
	 * M&eacutetodo encargado de remover una Torre del vector de enlaces de un objeto torre.
	 * @param TorreParaRemover Es la torre que en caso de existir en el vector_enlaces ser&aacute removida de este.
	 */
	public void remover_enlace(Torre TorreParaRemover) {
		if( this.vector_enlaces.contains(TorreParaRemover) ) {
			this.vector_enlaces.remove(TorreParaRemover);
		}
	}
	
	/**
	 * M&eacutetodo para obtener el vector_enlaces de una Torre.
	 * @return Torre.vector_enlaces
	 */
	public Vector <Torre> get_vector_enlaces() {
		return this.vector_enlaces;
	}
	
	/**
	 * M&eacutetodo para obtener el vector_ruta de una Torre. Se genera apartir de la b&uacutesqueda de una torre a otra.
	 * @return Torre.vector_ruta
	 */
	public Vector <Torre> get_vector_ruta() {
		return this.vector_ruta;
	}
	
	/**
	 * M&eacutetodo que se encarga de asignar un valor a Torre.vector_ruta
	 * @param ruta Vector de torres
	 */
	public void  set_vector_ruta(Vector<Torre> ruta) {
		this.vector_ruta = ruta;
	}
	
	/**
	 * M&eacutetodo para obtener el nombre de una Torre.
	 * @return Torre.nombre
	 */
	public String get_nombre() {
		return this.nombre;
	}
	
	/**
	 * M&eacutetodo para asignar el estatus de b&uacutesqueda de una Torre a otra.
	 * @param estatus Es el valor obtenido de la b&uacutesqueda.
	 */
	public void set_estatus_busqueda(boolean estatus) {
		this.estatus_busqueda = estatus;
	}
	
	/**
	 * M&eacutetodo para obtener el estatus de b&uacutesqueda de una Torre a otra.
	 * @return boolean Es el estatus de la b&uacutesqueda.
	 */
	public boolean get_estatus_busqueda() {
		return this.estatus_busqueda;
	}
		
}
