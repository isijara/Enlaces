import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

public class ArchivoEntrada {
	
	private Vector<String> instrucciones = new Vector<String>();
	private String ruta_archivo; 
	private String pattern_enlace = "[[a-z|A-Z]+\\w]{1,100}\\s+(->|<-)\\s*[[a-z|A-Z]+\\w]{1,100}\\s*\\.";
	private String pattern_remover_enlace = "[[a-z|A-Z]+\\w]{1,100}\\s*-\\s*[[a-z|A-Z]+\\w]{1,100}\\s*\\.";
	private String pattern_pregunta = "[[a-z|A-Z]+\\w]{1,100}\\s+(=>|<=)\\s*[[a-z|A-Z]+\\w]{1,100}\\s*\\?";

	
	public ArchivoEntrada(String ruta_archivo) {
		this.ruta_archivo = ruta_archivo;
		this.obtener_instrucciones_de_programa();
	}
    public Vector<String> obtener_instrucciones_de_programa( ) {
    	
    	String line;
    	
    	File file = new File( this.ruta_archivo );
        BufferedReader in = null;
    	try {
    		in = new BufferedReader(new FileReader(file));
    	} catch (FileNotFoundException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
        try {
    		while ((line = in.readLine()) != null) {
    		    String[] lineArray = line.split("\n");
    		    if (lineArray.length > 0) {
    		    	
    		    	line = this.eliminar_contenido_innecesario(line);
    		    	if( this.es_una_instruccion_valida(line) ) {
    		    		this.instrucciones.add(line);
    		    	}
    		    }
    		}
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
        
        try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return this.instrucciones;
    }
    
    /*
	 * Este mŽtodo se encarga de eliminar el contenido que existe despuŽs de un . o ?
	 * Busca el ’ndice de la primer existencia de ? o . y borra el contenido despuŽs de ese caracter. 
	 * */
    private String eliminar_contenido_innecesario(String line) {
   
    	int index_question = line.indexOf('?');
		int index_point = line.indexOf('.');
		
		line = ( index_question > 0 ) ? line.substring(0, index_question+1) : line;
		line = ( index_point > 0    ) ? line.substring(0, index_point+1) : line;
	
    	return line;
    }
    
    
    /* es_una_instruccion_valida (String line) {}
	 * Este mŽtodo se encarga de revisar que el enunciado sea v‡lido, es decir, que se respeten las 
	 * reglas de nombramiento de las torres, que el tipo de instrucci—n sea v‡lido: una pregunta, creaci—n 
	 * o destrucci—n de enlaces.
	 */
    
    private boolean es_una_instruccion_valida(String line) {
 
    	if( line.matches(this.pattern_enlace) ) {
    		return true;
    	} else if( line.matches(this.pattern_remover_enlace) ){
    		return true;
    	} else if( line.matches(this.pattern_pregunta) ) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /*
     * obtener_nombres_de_torres(String str) {}
     * Este mŽtodo se encarga de extraer los nombres de las torres que se encuentran involucradas en una
     * instrucci—n del archivo de entrada. Sustituye los caracteres ? o . de la instrucci—n y extrae los nombres
     * haciendo un split en base al tipo de instrucci—n que se encuentre en proceso.
     */
    public String[] obtener_nombres_de_torres(String line) {
		String tipo_enunciado = this.obtener_tipo_instruccion(line);
		String nombres[];
		line = line.replaceAll("\\.|\\?|\\s", "");
		
    	if( tipo_enunciado.equals("EnunciadoCrearEnlace")){
    		int index_creacion= line.indexOf('>');
    		if(index_creacion > 0 ){
    			nombres = line.split("->");
    		} else{
    			nombres = line.split("<-");
    		}
    	} else if( tipo_enunciado.equals("EnunciadoRemoverEnlace") ){
    			nombres = line.split("-");
    	} else {
    		int index_question = line.indexOf("=>");
    		if( index_question > 0 ) {
    			nombres = line.split("=>");
    		} else {
    			nombres = line.split("<=");
    		}
    	}
    	return nombres;
    }
    
    public String obtener_tipo_instruccion(String line) {
    	if( line.matches(this.pattern_enlace) ) {
    		return "EnunciadoCrearEnlace";
    	} else if( line.matches(this.pattern_remover_enlace) ){
    		return "EnunciadoRemoverEnlace";
    	} else {
    		return "EnunciadoPregunta";
    	}
    }
    
    public Vector<String> get_instrucciones () {
    	return this.instrucciones;
    }
}

