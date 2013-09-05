import java.util.*;

public class Torre {

	private String nombre;
	private Vector <Torre>  vector_enlaces;// = new Vector<Torre> ();
	private Vector <Torre> vector_ruta ;// = new Vector<String>();
	private boolean estatus_busqueda;
	
	public Torre ( String nombre ) {
		this.nombre = nombre;
		this.vector_enlaces = new Vector<Torre> ();
		this.vector_ruta   = new Vector<Torre>();
		this.estatus_busqueda = false;
		this.vector_ruta.add(this);
	}
	
	public Vector<Torre> buscar_torre(Torre objetivo, Vector<Torre> ruta_principal){
		
		Torre torre_origen = ruta_principal.get(0);
		torre_origen.estatus_busqueda = false;
		for( Torre T : this.vector_enlaces ) {
			if( this.vector_enlaces.contains(T) && T == torre_origen && T == objetivo ) {
				continue;
			}
			
			if( T == objetivo) {
				ruta_principal.add(T);
				torre_origen.estatus_busqueda = true;
			} else{
				ruta_principal.add(T);
				T.buscar_torre(objetivo, ruta_principal);
				//if( T.get_estatus_busqueda() ) { ruta_principal.remove(T);}
				
			}
		}
		if(this.vector_enlaces.size() == 0 ){
			ruta_principal.remove(this);
		}
		return ruta_principal;
	}
	
	public void remover_enlace(Torre torre_para_remover) {
		if( this.vector_enlaces.contains(torre_para_remover) ) {
			this.vector_enlaces.remove(torre_para_remover);
		}
	}
	
	public Vector <Torre> get_vector_enlaces() {
		return this.vector_enlaces;
	}
	
	public Vector <Torre> get_vector_ruta() {
		return this.vector_ruta;
	}
	
	public void  set_vector_ruta(Vector<Torre> ruta) {
		this.vector_ruta = ruta;
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
