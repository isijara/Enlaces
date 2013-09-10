import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Clase encargada de la detecci&oacuten y extracci&oacuten de las instrucciones del archivo de entrada. 
 * @author Ram&oacute;n Isijara / Margarita Aranda
 */

public class ArchivoEntrada {
	/**
	 * Variable de instancia que se encarga de almacenar las instrucciones recibidas de un archivo de entrada.
	*/
	private Vector<String> instrucciones = new Vector<String>();
	
	/**
	 * Variable de instancia que contiene la ruta del archivo de entrada.
	 */
	private String ruta_archivo; 
	/**
	 * Variable que almacena la expresi&oacuten regular para la validaci&oacuten de instrucciones de creaci&oacuten de enlaces.
	 */
	private String pattern_enlace = "\\s*[[a-z|A-Z]+\\w]{1,100}\\s*(->|<-)\\s*[[a-z|A-Z]+\\w]{1,100}\\s*\\.";
	/**
	 * Variable que almacena la expresi&oacuten regular para la validaci&oacuten de instrucciones de eliminaci&oacuten de enlaces.
	 */
	private String pattern_remover_enlace = "\\s*[[a-z|A-Z]+\\w]{1,100}\\s*-\\s*[[a-z|A-Z]+\\w]{1,100}\\s*\\.";
	/**
	 * Variable que almacena la expresi&oacuten regular para la validaci&oacuten de instrucciones de b&uacutesqueda de torres de comunicaci&oacuten.
	 */
	private String pattern_pregunta = "\\s*[[a-z|A-Z]+\\w]{1,100}\\s*(=>|<=)\\s*[[a-z|A-Z]+\\w]{1,100}\\s*\\?";

	/**
	 * Constructor
	 * @param ruta_archivo
	 * Indica el nombre y ubicaci&oacuten del archivo que contiene las instrucciones de entrada.
	 */
	public ArchivoEntrada(String ruta_archivo) {
			this.ruta_archivo = ruta_archivo;
			this.obtener_instrucciones_de_programa();
	}
	
	/**
	 * M&eacutetodo que se encarga de obtener las instrucciones v&aacutelidas del archivo de entrada. 
	 * @return Vector String.
	 */
    public Vector<String> obtener_instrucciones_de_programa( ) {
    	
    	String line;
    	
    	File file = new File( this.ruta_archivo );

        BufferedReader in = null;
    	try {
    		in = new BufferedReader(new FileReader(file));
    	} catch (FileNotFoundException e) {
    		System.out.println("El archivo de entrada no existe.");
    		return new Vector<String>();
    		// TODO Auto-generated catch block
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
    
   
    /**
   	 * Este m&eacutetodo se encarga de eliminar el contenido que existe despu&eacutes de un . o ?
   	 * Busca el &iacutendice de la primer existencia de ? o . y borra el contenido despu&eacutes de ese caracter. 
   	 * @param line Variable de tipo String que contiene una instrucci&oacuten del archivo de entrada.
   	 */
    private String eliminar_contenido_innecesario(String line) {
   
    	int index_question = line.indexOf('?');
		int index_point = line.indexOf('.');
		
		line = ( index_question > 0 ) ? line.substring(0, index_question+1) : line;
		line = ( index_point    > 0 ) ? line.substring(0, index_point+1) : line;
		
    	return line;
    }
    
    
    /** 
	 * Este m&eacutetodo se encarga de revisar que el enunciado sea v&aacutelido, es decir, que se respeten las 
	 * reglas de nombramiento de las torres, que el tipo de instrucci&oacuten sea v&aacutelido: una pregunta, creaci&oacuten 
	 * o destrucci&oacuten de enlaces.
   	 * @param line Variable de tipo String que contiene una instrucci&oacuten del archivo de entrada.
   	 * @return boolean
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
    
    /**
     * Este m&eacutetodo se encarga de extraer los nombres de las torres que se encuentran involucradas en una
     * instrucci&oacuten del archivo de entrada. Sustituye los caracteres ? o . de la instrucci&oacuten y extrae los nombres
     * haciendo un split en base al tipo de instrucci&oacuten que se encuentre en proceso.
   	 * @param line Variable de tipo String que contiene una instrucci&oacuten del archivo de entrada.
   	 * @return nombre Es un String array que contiene los nombres de las torres.
     */
    public String[] obtener_nombres_de_torres(String line) {
		String tipo_enunciado = this.obtener_tipo_instruccion(line);
		String nombres[];
		line = line.replaceAll("\\.|\\?|\\s", "");
		
    	if( tipo_enunciado.equals("EnunciadoCrearEnlace")){
    		int index_creacion= line.indexOf('>');
    		if(index_creacion > 0 ){
    			nombres = line.split("->");
    		} else {
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
    
    /**
     * M&eacutetodo que se encarga de detectar el tipo de enunciado que se est&eacute
   	 * @param line Variable de tipo String que contiene una instrucci&oacuten del archivo de entrada.
     * @return String Es el tipo de instrucci&oacuten al que pertenece el enunciado. Creaci&oacuten/destrucci&oacuten de enlace o b&uacutesqueda de torre.
     */
    public String obtener_tipo_instruccion(String line) {
    	if( line.matches(this.pattern_enlace) ) {
    		return "EnunciadoCrearEnlace";
    	} else if( line.matches(this.pattern_remover_enlace) ){
    		return "EnunciadoRemoverEnlace";
    	} else {
    		return "EnunciadoPregunta";
    	}
    }
    
    /**
     * M&eacutetodo que se encarga de obtener las instrucciones que han sido almacenadas en la variable ArchivoEntrada.instrucciones
     * @return Vector de Strings
     */
    public Vector<String> get_instrucciones () {
    	return this.instrucciones;
    }
}

