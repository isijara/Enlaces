import java.util.*;

public class Torre {

	private String nombre;
	private Vector <Torre>  vector_enlaces;// = new Vector<Torre> ();
	private Vector <String> vector_ruta ;// = new Vector<String>();
	private boolean estatus_busqueda;
	
	public Torre ( String nombre ) {
		this.nombre = nombre;
		this.vector_enlaces = new Vector<Torre> ();
		this.vector_ruta   = new Vector<String>();
		this.estatus_busqueda = false;
	}
	
	private boolean buscar_enlace(){
		
		return true;
	}
	
	public Vector <Torre> get_vector_enlaces() {
		return this.vector_enlaces;
	}
	
	public Vector <String> get_vector_ruta() {
		return this.vector_ruta;
	}
	
	public String get_nombre() {
		return this.nombre;
	}
	
	public void set_estatus_busqueda(boolean estatus) {
		this.estatus_busqueda = estatus;
	}
	
	public boolean get_estatus_busqueda() {
		return this.estatus_busqueda;
	}
	
}
