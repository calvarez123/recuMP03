package com.project;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        /*/
        AppData db = AppData.getInstance();

        System.out.println("\nIniciar les dades de la base de dades:");
        initData();

        System.out.println("\nAnimals de l'espècie 'Gos':");
        llistarFiles("SELECT * FROM editorials");
        llistarllibres("SELECT * FROM llibres");
        llistarllibres("SELECT * FROM llibres where id_llibre= '1'");



        // Tancar la connexió amb la base de dades (del singleton)
        db.close();
        */
        AppData db = AppData.getInstance();

        // Crear taules
        crearTaulaEditorials();
        crearTaulaLlibres();

        // Afegir editorials
        afegirEditorial("Editorial Alpha");
        afegirEditorial("Beta Publishers");
        afegirEditorial("Gamma Books");
        afegirEditorial("Delta Literature");

        // Afegir llibres
        afegirLlibre("El primer llibre", "Autor A", 2020, 1);
        afegirLlibre("Segona obra", "Autor B", 2018, 2);
        afegirLlibre("Tercer volum", "Autor C", 2019, 3);
        afegirLlibre("Quart text", "Autor D", 2021, 4);
        afegirLlibre("Cinquè manuscrit", "Autor E", 2022, 1);
        afegirLlibre("Sisè document", "Autor F", 2023, 2);

        System.out.println("\nEditorials:");
        llistarTaulaEditorials();

        System.out.println("\nLlibres:");
        llistarTaulaLlibres();

        int idLlibre = 5;
        System.out.println("\nInformació del Llibre: " + idLlibre);
        llistarInfoLlibre(idLlibre);

        // Tancar la connexió amb la base de dades (del singleton)
        db.close();
        
        
    }

    public static void initData() {

        // Obtenir un apuntador al singleton de la base de dades
        AppData db = AppData.getInstance();

        // tabla editorials
        db.update("DROP TABLE IF EXISTS editorials");

        db.update("CREATE TABLE IF NOT EXISTS editorials (" +
                            "id_editorial INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "nom TEXT)");

        // tabla llibres
        db.update("DROP TABLE IF EXISTS llibres");

        db.update("CREATE TABLE IF NOT EXISTS llibres (" +
                        "id_llibre INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "titol TEXT," +
                        "autor TEXT," +
                        "any_publicacio INTEGER," +
                        "id_editorial INTEGER," +
                        "FOREIGN KEY (id_editorial) REFERENCES editorials(id_editorial))");




        // Inserir dades a la taula 'animals'
        db.update("INSERT INTO editorials (nom) VALUES ('hola')");
        db.update("INSERT INTO llibres (titol,autor,any_publicacio,id_editorial) VALUES ('titol','autor','any_publicacio',1)");
       
    }


    //------------------------- EJEMPLO CREAR TABLAS CON FK --------------------------------------

    static void crearTaulaEditorials(){
        AppData db = AppData.getInstance();
        db.update("DROP TABLE IF EXISTS editorials");

        db.update("CREATE TABLE IF NOT EXISTS editorials (" +
                            "id_editorial INTEGER PRIMARY KEY AUTOINCREMENT," +
                            "nom TEXT)");
    };
    static void crearTaulaLlibres(){
        AppData db = AppData.getInstance();
        db.update("DROP TABLE IF EXISTS llibres");

        db.update("CREATE TABLE IF NOT EXISTS llibres (" +
                        "id_llibre INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "titol TEXT," +
                        "autor TEXT," +
                        "any_publicacio INTEGER," +
                        "id_editorial INTEGER," +
                        "FOREIGN KEY (id_editorial) REFERENCES editorials(id_editorial))");
    }

        //------------------------- EJEMPLO AGREGAR INFO CON FK --------------------------------------

    static void afegirEditorial(String nom){
        AppData db = AppData.getInstance();
        db.update("INSERT INTO editorials (nom) VALUES ('"+nom+"')");
    }
    static void afegirLlibre(String titol, String autor, int anyPublicacio, int idEditorial){
        AppData db = AppData.getInstance();
        db.update("INSERT INTO llibres (titol,autor,any_publicacio,id_editorial) VALUES ('"+titol+"','"+autor+"','"+anyPublicacio+"',"+idEditorial+")");
    }

        //------------------------- EJEMPLO LISTAR INFORMACION --------------------------------------

    static void llistarTaulaEditorials(){
        String sql = "SELECT * FROM editorials";

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
            System.out.println(fila.get("id_editorial") + ", " + fila.get("nom") );
        }

    }
    static void llistarTaulaLlibres(){
        String sql = "SELECT * FROM llibres";
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
            System.out.println(fila.get("id_llibre") + ", " + fila.get("titol") + ", " + fila.get("any_publicacio") + ", " + fila.get("id_editorial"));
        }
    }
    static void llistarInfoLlibre(int idLlibre){
        String sql = "SELECT * FROM llibres where id_llibre ='"+idLlibre+"'";
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
            System.out.println(fila.get("id_llibre") + ", " + fila.get("titol") + ", " + fila.get("any_publicacio") + ", " + fila.get("id_editorial"));
        }


    }






    public static void llistarFiles(String sql) {

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
            System.out.println(fila.get("id_editorial") + ", " + fila.get("nom") );
        }
    }
    public static void llistarllibres(String sql) {

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
            System.out.println(fila.get("id_llibre") + ", " + fila.get("titol") + ", " + fila.get("any_publicacio") + ", " + fila.get("id_editorial"));
        }
    }

  
}
