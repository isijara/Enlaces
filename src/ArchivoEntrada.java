import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.*;

public class ArchivoEntrada {
	
    public Vector<String> obtener_instrucciones_de_programa( String ruta_archivo ) {
    	
    	Vector <String> instrucciones = new Vector<String> ();
    	String line;
    	
    	File file = new File( ruta_archivo );
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
    		    		instrucciones.add(line);
    		    	}
    		    	
    		    	System.out.println(line);
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
    	
    	return instrucciones;
    }
    
    private String eliminar_contenido_innecesario(String line) {
    	/*
    	 * Este mŽtodo deber‡ encargarse de eliminar el contenido que existe despuŽs de un . o ?
    	 * */
    	int index_question = line.indexOf('?');
		int index_point = line.indexOf('.');
		
		line = ( index_question > 0 ) ? line.substring(0, index_question+1) : line;
		line = ( index_point > 0    ) ? line.substring(0, index_point+1) : line;
	
    	return line;
    }
    
    private boolean es_una_instruccion_valida(String line) {
    	boolean es_valido = false;
    	/* 
    	 *Este mŽtodo se encargar‡ de validar que tengamos una instrucci—n v‡lida
    	 **/
    	
    	return es_valido;
    }
    
    private boolean es_nombre_valido( String nombre) {
    	boolean es_valido = true;
    	
    	Pattern p =Pattern.compile( "\\p{Alnum}" );
    	
		//   Esta es la expresi—n regular que necesito [a-zA-Z]++\w

    	
    	return es_valido;
    }
}


/*
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

/*
 * 
 * 
 * 
 * 
 *
 * 
 System.out.println( "Torre1".matches("[a-zA-Z]+\\w+") );
		String pattern = "([a-z|A-Z]+\\w+){1,100}";
		
		
		System.out.println( "Torre1".matches("[a-z|A-Z]+\\w+") );
		
		System.out.println( "Torraaaaaaaaaaaaaaaaaaaaaae1Torraaaaaaaaaaaaaaaaaaaaaae1Torraaaaaaaaaaaaaaaaaaaaaae1Torraaaaaaaaaaaaaaaaaaaaaae1Torraaaaaaaaaaaaaaaaaaaaaae1Torraaaaaaaaaaaaaaaaaaaaaae1Torraaaaaaaaaaaaaaaaaaaaaae1Torraaaaaaaaaaaaaaaaaaaaaae1".matches(pattern) );

 
 
 * 
 * 
 */

/*
public class ArchivoEntrada {

	
	BufferedReader in = new BufferedReader(new FileReader(file));
    String line;
    while ((line = in.readLine()) != null) {
        String[] lineArray = line.split("\s");
        if (lineArray.length > 0) {
            //Process line of input Here
        }
    }
}

*/
//Sample code to read in test cases:
