package com.project;
import java.sql.Date;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        AppData db = AppData.getInstance();
        
       // Crear les taules
       crearTaules();
       System.out.println("tablas creadas ");

       // Afegir Professors
       afegirProfessor("Maria Garcia", "Matemàtiques");
       int idMaria = obtenirIdProfessorPerNom("Maria Garcia");

       afegirProfessor("Jordi Pozo", "Literatura");
       int idJordi = obtenirIdProfessorPerNom("Jordi Pozo");

       afegirProfessor("Anna Molina", "Ciències");
       int idAnna = obtenirIdProfessorPerNom("Anna Molina");

       System.out.println(" profes");

       // Afegir Aules
       afegirAula("A101", 30);
       afegirAula("A102", 25);
       afegirAula("B201", 20);
       System.out.println("aules ");

       // Afegir Assignatures
       if (idMaria != -1) {
           afegirAssignatura("Algebra", 4, idMaria);
       }
       int idAlgebra = obtenirIdAssignaturaPerNom("Algebra");

       if (idJordi != -1) {
           afegirAssignatura("Literatura Catalana", 3, idJordi);
       }
       int idLiteraturaCatalana = obtenirIdAssignaturaPerNom("Literatura Catalana");

       if (idAnna != -1) {
           afegirAssignatura("Biologia", 5, idAnna);
       }
       int idBiologia = obtenirIdAssignaturaPerNom("Biologia");
       System.out.println(" asignaturas");
       
       // Afegir Alumnes
       afegirAlumne("Marc", "Soler", Date.valueOf("2005-03-15"));
       int idMarc = obtenirIdAlumnePerNom("Marc", "Soler");

       afegirAlumne("Laura", "Vidal", Date.valueOf("2004-07-22"));
       int idLaura = obtenirIdAlumnePerNom("Laura", "Vidal");

       afegirAlumne("Iván", "Coll", Date.valueOf("2004-06-10"));
       int idIvan = obtenirIdAlumnePerNom("Iván", "Coll");
       System.out.println(" alumnos");

       // Inscripcions, comprovant que tant els alumnes com les assignatures existeixen
       if (idMarc != -1 && idAlgebra != -1) {
           inscriureAlumneAssignatura(idMarc, idAlgebra);
       }
       if (idLaura != -1 && idLiteraturaCatalana != -1) {
           inscriureAlumneAssignatura(idLaura, idLiteraturaCatalana);
       }
       if (idIvan != -1) {
           if (idAlgebra != -1) inscriureAlumneAssignatura(idIvan, idAlgebra);
           if (idLiteraturaCatalana != -1) inscriureAlumneAssignatura(idIvan, idLiteraturaCatalana);
           if (idBiologia != -1) inscriureAlumneAssignatura(idIvan, idBiologia);
       }

       
        // Llistar informació
        System.out.println("Professors:");
        llistarProfessors();

        System.out.println("\nAssignatures:");
        llistarAssignatures();

        System.out.println("\nAules:");
        llistarAules();

        System.out.println("\nAssignatures de alumno:");
        llistarAssignaturesAlumne(1);
        llistarAssignaturesAlumne(3);

        System.out.println("\nAlumnes per assignatura:");
        llistarAlumnesAssignatura(1);
        llistarAlumnesAssignatura(2);
        llistarAlumnesAssignatura(3);

      db.close();
        


    }

    static void crearTaules()  {

        AppData db = AppData.getInstance();
        // Eliminar restricciones de clave externa
    db.update("SET FOREIGN_KEY_CHECKS=0;"); 
    // Eliminar las tablas en el orden adecuado
    db.update("DROP TABLE IF EXISTS alumne_assignatura");
    db.update("DROP TABLE IF EXISTS assignatures");
    db.update("DROP TABLE IF EXISTS professors");
    db.update("DROP TABLE IF EXISTS aules");
    db.update("DROP TABLE IF EXISTS alumnes");


        db.update("CREATE TABLE IF NOT EXISTS alumnes (" +
                        "id_alumne INTEGER PRIMARY KEY AUTO_INCREMENT," +
                        "nom TEXT," +
                        "cognoms TEXT," +
                        "data_naixement Date)");
        //-----------------------------------------------------------------
        
        

        db.update("CREATE TABLE IF NOT EXISTS professors (" +
                        "id_professor INTEGER PRIMARY KEY AUTO_INCREMENT," +
                        "nom TEXT," +
                        "especialitat TEXT)");
         //-----------------------------------------------------------------
        
         

         db.update("CREATE TABLE IF NOT EXISTS assignatures (" +
                         "id_assignatura INTEGER PRIMARY KEY AUTO_INCREMENT," +
                         "nom TEXT," +
                         "hores_setmanals INTEGER," +
                         "id_professor INTEGER," +
                         "FOREIGN KEY (id_professor) REFERENCES professors(id_professor))");

        //-----------------------------------------------------------------
        
        

        db.update("CREATE TABLE IF NOT EXISTS aules (" +
                        "id_aula INTEGER PRIMARY KEY AUTO_INCREMENT," +
                        "nom TEXT," +
                        "capacitat INTEGER)");
        //-----------------------------------------------------------------
        
        db.update("CREATE TABLE IF NOT EXISTS alumne_assignatura (" + 
                        "id_alumne INTEGER," + 
                        "id_assignatura INTEGER," + 
                        "FOREIGN KEY (id_alumne) REFERENCES alumnes(id_alumne)," + 
                        "FOREIGN KEY (id_assignatura) REFERENCES assignatures(id_assignatura))");
        

        
    }
    static void afegirAlumne(String nom, String cognoms, Date dataNaixement){
        AppData db = AppData.getInstance();

        db.update("INSERT INTO alumnes (nom,cognoms,data_naixement) VALUES ('"+nom+"','"+cognoms+"','"+dataNaixement+"')");

    }
    static void afegirProfessor(String nom, String especialitat){
        AppData db = AppData.getInstance();

        db.update("INSERT INTO professors (nom,especialitat) VALUES ('"+nom+"','"+especialitat+"')");

    }
    static void afegirAssignatura(String nom, int horesSetmanals, int idProfessor) {
        AppData db = AppData.getInstance();

        db.update("INSERT INTO assignatures (nom,hores_setmanals,id_professor) VALUES ('"+nom+"','"+horesSetmanals+"','"+idProfessor+"')");

    }
    static void afegirAula(String nom, int capacitat){
        AppData db = AppData.getInstance();

        db.update("INSERT INTO aules (nom,capacitat) VALUES ('"+nom+"','"+capacitat+"')");

    }
    static void inscriureAlumneAssignatura(int idAlumne, int idAssignatura) {
        AppData db = AppData.getInstance();
        System.out.println(" aqui");
        db.update("INSERT INTO alumne_assignatura (id_alumne,id_assignatura) VALUES ('"+idAlumne+"','"+idAssignatura+"')");
    }
    static void llistarAlumnes() {
        AppData db = AppData.getInstance();
        // Llistar totes les taules
        List<Map<String, Object>> alumnes = db.query("Select * from alumnes;");
        /*
        //sacarlo de forma normal
        

        for (Map<String, Object> alumno : alumnes) {
            System.out.println(alumno.get("id_alumne") + ", " + alumno.get("nom") + ", " + alumno.get("cognoms") + ", " + alumno.get("data_naixement"));
        }
         */
        //con formato
        for (Map<String, Object> alumno : alumnes) {
            String idAlumno = alumno.get("id_alumne").toString();
            String nombre = alumno.get("nom").toString();
            String apellidos = alumno.get("cognoms").toString();
            String fechaNacimiento = alumno.get("data_naixement").toString();
        
            String formattedOutput = String.format("ID: %-5s | Nombre: %-20s | Apellidos: %-30s | Fecha de Nacimiento: %s",
                                                    idAlumno, nombre, apellidos, fechaNacimiento);
            System.out.println(formattedOutput);
        }
        
    
        
    }
    static void llistarProfessors() {

        AppData db = AppData.getInstance();
        // Llistar totes les taules
        List<Map<String, Object>> profesors = db.query("Select * from professors;");
  
        for (Map<String, Object> profesor : profesors) {
            String idAlumno = profesor.get("id_professor").toString();
            String nombre = profesor.get("nom").toString();
            String apellidos = profesor.get("especialitat").toString();
        
            String formattedOutput = String.format("ID: %-5s | Nombre: %-20s | Especialitat: %-30s|",
                                                    idAlumno, nombre, apellidos);
            System.out.println(formattedOutput);
        }
        
        
    }
    static void llistarAssignatures() {
        AppData db = AppData.getInstance();
        // Llistar totes les taules
        List<Map<String, Object>> profesors = db.query("Select * from assignatures;");
  
        for (Map<String, Object> profesor : profesors) {
            String idAlumno = profesor.get("id_assignatura").toString();
            String nombre = profesor.get("nom").toString();
            String horas = profesor.get("hores_setmanals").toString();
            String idprofessor = profesor.get("id_professor").toString();
           
        
            String formattedOutput = String.format("ID: %-5s | Nombre: %-20s | horas: %-10s| ID Professor: %-30s|",
                                                    idAlumno, nombre, horas,idprofessor);
            System.out.println(formattedOutput);
        }
        
        
    }
    static void llistarAules() {
        AppData db = AppData.getInstance();
        // Llistar totes les taules
        List<Map<String, Object>> profesors = db.query("Select * from aules;");
  
        for (Map<String, Object> profesor : profesors) {
            String idAlumno = profesor.get("id_aula").toString();
            String nombre = profesor.get("nom").toString();
            String horas = profesor.get("capacitat").toString();
           
        
            String formattedOutput = String.format("ID: %-5s | Nombre: %-20s | Capacitat: %-30s|",
                                                    idAlumno, nombre, horas);
            System.out.println(formattedOutput);
        }
        
        
    }
    
    static void llistarAssignaturesAlumne(int idAlumne){
        AppData db = AppData.getInstance();
        // Llistar totes les taules
        List<Map<String, Object>> profesors = 
        db.query("Select a.id_assignatura,a.nom,a.hores_setmanals,aa.id_alumne from assignatures a inner join alumne_assignatura aa on aa.id_assignatura=a.id_assignatura where aa.id_alumne = "+idAlumne+";");
        System.out.println("Assignatures per l'alumne ID "+idAlumne+" :");

        for (Map<String, Object> profesor : profesors) {
            String nombre = profesor.get("nom").toString();
            
           
        
            String formattedOutput = String.format("Nom : %-5s |",
                                                    nombre);
            System.out.println(formattedOutput);
        }
        
    } 
    
    static void llistarAlumnesAssignatura(int idAssignatura) {
        AppData db = AppData.getInstance();
        // Llistar totes les taules
        List<Map<String, Object>> profesors = 
        db.query("Select a.nom from alumnes a inner join alumne_assignatura aa on aa.id_alumne = a.id_alumne where id_assignatura = "+idAssignatura+";");
        System.out.println("Alumnes inscrits a l'assignatura ID "+idAssignatura+" :");
        for (Map<String, Object> profesor : profesors) {
            String idAlumno = profesor.get("nom").toString();
            
        
            String formattedOutput = String.format("| ID: %-5s|",
                                                    idAlumno);
            System.out.println(formattedOutput);
        }
        
    }
    static int obtenirIdAlumnePerNom(String nom, String cognoms) {
        AppData db = AppData.getInstance();
        // Lista todas las tablas
        List<Map<String, Object>> alumnos = db.query("SELECT id_alumne FROM alumnes WHERE nom = '" + nom + "' AND cognoms = '" + cognoms + "';");
        System.out.println(" nose");
        if (!alumnos.isEmpty()) {
            Map<String, Object> alumno = alumnos.get(0); 
            String idAlumno = alumno.get("id_alumne").toString();
            return Integer.parseInt(idAlumno);
        } else {
            return -1; 
        }
    }
    
    
    static int obtenirIdAssignaturaPerNom(String nomAssignatura) {
        AppData db = AppData.getInstance();
        // Lista todas las tablas
        List<Map<String, Object>> alumnos = db.query("SELECT id_assignatura FROM assignatures WHERE nom = '" + nomAssignatura+"';");
        
        if (!alumnos.isEmpty()) {
            Map<String, Object> alumno = alumnos.get(0); 
            String idAlumno = alumno.get("id_assignatura").toString();
            return Integer.parseInt(idAlumno);
        } else {
            return -1; 
        }
    }
    
    static int obtenirIdProfessorPerNom(String nomProfessor) {
        AppData db = AppData.getInstance();
        // Lista todas las tablas
        List<Map<String, Object>> alumnos = db.query("SELECT id_professor FROM professors WHERE nom = '" + nomProfessor+"';");
        
        if (!alumnos.isEmpty()) {
            Map<String, Object> alumno = alumnos.get(0); 
            String idAlumno = alumno.get("id_professor").toString();
            return Integer.parseInt(idAlumno);
        } else {
            return -1; 
        }
    }
    
   
}
