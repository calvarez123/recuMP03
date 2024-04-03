package com.project;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        AppData db = AppData.getInstance();
        
          // Crear taules
          crearTaulaDirectors();
          crearTaulaPelis();
          crearTaulaSales();
  
          // Afegir directors
          afegirDirector("Director A", "País X");
          afegirDirector("Director B", "País Y");
  
          // Afegir pel·lícules
          afegirPeli("Film A", 2020, 120, 1);
          afegirPeli("Film B", 2018, 110, 2);
  
          // Afegir sales
          afegirSala("Sala 1", 150, 1);
          afegirSala("Sala 2", 200, 2);
  
          System.out.println("\nDirectors:");
          llistarTaulaDirectors();
  
          System.out.println("\nPelis:");
          llistarTaulaPelis();
  
          System.out.println("\nSales:");
          llistarTaulaSales();
  
          int idPeli = 1;
          System.out.println("\nInformació de la Peli: " + idPeli);
          llistarInfoPeli(idPeli);



        db.close();
        
    }

    
    static void crearTaulaDirectors() {
        // Código para crear la tabla 'Directors' (y eliminar la existente si es necesario)
        AppData db = AppData.getInstance();
        db.update("DROP TABLE IF EXISTS directors");

        db.update("CREATE TABLE IF NOT EXISTS directors (" +
                        "id_director INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nom TEXT," +
                        "nacionalitat TEXT)");
    }
    
      static void crearTaulaPelis() {
        // Código para crear la tabla 'Pelis' (y eliminar la existente si es necesario)
        AppData db = AppData.getInstance();
        db.update("DROP TABLE IF EXISTS pelis");

        db.update("CREATE TABLE IF NOT EXISTS pelis (" +
                        "id_peli INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "titol TEXT," +
                        "any_estrena INTEGER," +
                        "durada INTEGER," +
                        "id_director INTEGER," +
                        "FOREIGN KEY (id_director) REFERENCES directors(id_director))");
    }
    
      static void crearTaulaSales() {
        // Código para crear la tabla 'Sales' (y eliminar la existente si es necesario)
        AppData db = AppData.getInstance();
        db.update("DROP TABLE IF EXISTS sales");

        db.update("CREATE TABLE IF NOT EXISTS sales (" +
                        "id_sala INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "nom_sala TEXT," +
                        "capacitat INTEGER," +
                        "id_peli INTEGER," +
                        "FOREIGN KEY (id_peli) REFERENCES pelis(id_peli))");
    }
    
      static void afegirDirector(String nom, String nacionalitat) {
        // Código para añadir un director a la tabla 'Directors' con el nombre y la nacionalidad proporcionados
        AppData db = AppData.getInstance();
        db.update("INSERT INTO directors (nom,nacionalitat) VALUES ('"+nom+"','"+nacionalitat+"')");
    }
    
      static void afegirPeli(String titol, int anyEstrena, int durada, int idDirector) {
        // Código para añadir una película a la tabla 'Pelis' con el título, año de estreno, duración e identificador de director proporcionados
        AppData db = AppData.getInstance();
        db.update("INSERT INTO pelis (titol,any_estrena,durada,id_director) VALUES ('"+titol+"','"+anyEstrena+"','"+durada+"','"+idDirector+"')");
    
    }
    
    static void afegirSala(String nomSala, int capacitat, int idPeli) {
    // Código para añadir una sala a la tabla 'Sales' con el nombre de la sala, capacidad e identificador de película proporcionados
    AppData db = AppData.getInstance();
    db.update("INSERT INTO sales (nom_sala,capacitat,id_peli) VALUES ('"+nomSala+"','"+capacitat+"','"+idPeli+"')");
    }
    
      static void llistarTaulaDirectors() {
        // Código para mostrar los elementos de la tabla 'Directors' en el formato especificado
        String sql = "SELECT * FROM directors";

        // Obtenir un apuntador al singleton de la base de dades
        AppData db = AppData.getInstance();

        List<Map<String, Object>> files = db.query(sql);

        // Llistar el nom i tipus de dades de cada columna (de la fila 0)
        String txt = "Columnes: ";
        Map<String, Object> fila0 = files.get(0);
        for (String key : fila0.keySet()) {
            Object value = fila0.get(key);
            txt += key + " (" + (value != null ? value.getClass().getSimpleName() : "null") + "), ";
        }
        if (files.size() > 0) {
            txt = txt.substring(0, txt.length() -2 );
        }
        System.out.println(txt); 

        // Llistar les files de la query
        System.out.println("Dades:");
        for (Map<String, Object> fila : files) {
            System.out.println("ID: "+fila.get("id_director") + ", Nom: " + fila.get("nom") + ", Nacionalitat: " + fila.get("nacionalitat") );
        }
  
    }
    
    static void llistarTaulaPelis() {
        // Código para mostrar los elementos de la tabla 'Pelis' en el formato especificado
      String sql = "SELECT * FROM pelis p inner join directors d on p.id_director=d.id_director";

      // Obtenir un apuntador al singleton de la base de dades
      AppData db = AppData.getInstance();

      List<Map<String, Object>> files = db.query(sql);

      // Llistar el nom i tipus de dades de cada columna (de la fila 0)
      String txt = "Columnes: ";
      Map<String, Object> fila0 = files.get(0);
      for (String key : fila0.keySet()) {
          Object value = fila0.get(key);
          txt += key + " (" + (value != null ? value.getClass().getSimpleName() : "null") + "), ";
      }
      if (files.size() > 0) {
          txt = txt.substring(0, txt.length() -2 );
      }
      System.out.println(txt); 

      // Llistar les files de la query
      System.out.println("Dades:");
      for (Map<String, Object> fila : files) {
          System.out.println("ID: "+fila.get("id_peli") + ", Titol: " + fila.get("titol") + ", Anyo Estreno: " + fila.get("any_estrena") + ", Duracion: " + fila.get("durada") + ", Director: " + fila.get("nom"));
      }
    }
    
      static void llistarTaulaSales() {
        // Código para mostrar los elementos de la tabla 'Sales' en el formato especificado
        String sql = "SELECT * FROM sales s inner join pelis p on p.id_peli=s.id_peli";

        // Obtenir un apuntador al singleton de la base de dades
        AppData db = AppData.getInstance();
  
        List<Map<String, Object>> files = db.query(sql);
  
        // Llistar el nom i tipus de dades de cada columna (de la fila 0)
        String txt = "Columnes: ";
        Map<String, Object> fila0 = files.get(0);
        for (String key : fila0.keySet()) {
            Object value = fila0.get(key);
            txt += key + " (" + (value != null ? value.getClass().getSimpleName() : "null") + "), ";
        }
        if (files.size() > 0) {
            txt = txt.substring(0, txt.length() -2 );
        }
        System.out.println(txt); 
  
        // Llistar les files de la query
        System.out.println("Dades:");
        for (Map<String, Object> fila : files) {
            System.out.println("ID: "+fila.get("id_sala") + ", Sala" + fila.get("nom_sala") + ", Capacidad" + fila.get("capacitat") + ", Peli: " + fila.get("titol"));
        }
    }
    
    static void llistarInfoPeli(int numero) {
        // Código para mostrar información detallada sobre una película

        String sql = "SELECT * FROM pelis p inner join sales s on s.id_peli=p.id_peli where p.id_peli='"+numero+"'";
        // Obtenir un apuntador al singleton de la base de dades
        AppData db = AppData.getInstance();

        List<Map<String, Object>> files = db.query(sql);

        // Llistar el nom i tipus de dades de cada columna (de la fila 0)
        String txt = "Columnes: ";
        Map<String, Object> fila0 = files.get(0);
        for (String key : fila0.keySet()) {
            Object value = fila0.get(key);
            txt += key + " (" + (value != null ? value.getClass().getSimpleName() : "null") + "), ";
        }
        if (files.size() > 0) {
            txt = txt.substring(0, txt.length() -2 );
        }
        System.out.println(txt); 

        // Llistar les files de la query
        System.out.println("Dades:");
        for (Map<String, Object> fila : files) {
            System.out.println("ID: "+fila.get("id_peli") + ", Titol: " + fila.get("titol") + ", Estreno: " + fila.get("any_estrena") + ", Durada: " + fila.get("durada") + ", Director: " + fila.get("id_director")+ ", Sala: " + fila.get("id_sala"));
        }

    }


}
