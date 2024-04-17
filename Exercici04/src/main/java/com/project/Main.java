package com.project;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    private static String URL = "jdbc:mysql://localhost:3308/videogame_park?useSSL=false&allowPublicKeyRetrieval=true";
    private static String USER = "root";
    private static String PASSWORD = "pwd";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            conn.setAutoCommit(false); // Important per controlar manualment les transaccions
            crearTaules(conn);
            afegirArea(conn, "Zona Arcade", "Arcade", 100);
            afegirArea(conn, "Zona VR", "Realitat Virtual", 50);
            afegirTarifa(conn, "Passi Bàsic", new BigDecimal("19.99"), 1);
            afegirTarifa(conn, "Passi Premium", new BigDecimal("39.99"), 3);

            int idZonaArcade = obtenirIdAreaPerNom(conn, "Zona Arcade");
            int idZonaVR = obtenirIdAreaPerNom(conn, "Zona VR");
            int idPassiBasic = obtenirIdTarifaPerNom(conn, "Passi Bàsic");
            int idPassiPremium = obtenirIdTarifaPerNom(conn, "Passi Premium");

            definirAccesAreaTarifa(conn, idZonaArcade, idPassiBasic);
            definirAccesAreaTarifa(conn, idZonaArcade, idPassiPremium);
            definirAccesAreaTarifa(conn, idZonaVR, idPassiPremium);

            conn.commit(); // Confirmar totes les operacions al final

            llistarArees(conn);
            llistarTarifes(conn);
            llistarTarifesPerAccesArea(conn, idZonaArcade);
            llistarAreesAccesiblesPerTarifa(conn, idPassiBasic);
            llistarAreesAccesiblesPerTarifa(conn, idPassiPremium);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Forçar la sortida del programa per no esperar a tancar la connexió amb 'MySQL'
        // Assegura't que en aquest punt totes les dades s'han guardat correctament
        if (!"test".equals(System.getProperty("enviroment"))) {
            System.exit(0);
        }
    }

    public static void crearTaules(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            // Esborrar la taula 'persones' si existeix
            stmt.execute("SET FOREIGN_KEY_CHECKS=0;"); 

            stmt.execute("DROP TABLE IF EXISTS arees");
            stmt.execute("DROP TABLE IF EXISTS tarifes");
            stmt.execute("DROP TABLE IF EXISTS acces_area_tarifa");
            // Crear la taula 'persones'
            stmt.execute("CREATE TABLE IF NOT EXISTS arees (" +
                "id_area INT AUTO_INCREMENT PRIMARY KEY," +
                "nom TEXT ," +
                "tematica TEXT ," +
                "capacitat_maxima integer," +
                "edat INTEGER)");

            stmt.execute("CREATE TABLE IF NOT EXISTS tarifes (" +
            "id_tarifa INT AUTO_INCREMENT PRIMARY KEY," +
            "nom TEXT ," +
            "preu DECIMAL ," +
            "durada INTEGER)");

            stmt.execute("CREATE TABLE IF NOT EXISTS acces_area_tarifa (" +
            "id_area INTEGER," +
            "id_tarifa INTEGER ," +
            "FOREIGN KEY (id_area) REFERENCES arees(id_area),"+
            "FOREIGN KEY (id_tarifa) REFERENCES tarifes(id_tarifa))");

            conn.commit(); // Confirma els canvis
            System.out.println("La base de dades ha estat inicialitzada amb èxit.");
        } catch (SQLException e) {
            try {
                conn.rollback(); // Reverteix els canvis
                System.out.println("Rollback realitzat degut a un error.");
            } catch (SQLException ex) {
                System.out.println("Error en intentar fer rollback.");
                ex.printStackTrace();
            }
            System.out.println(e.getMessage());
        }
    }
    
    public static void afegirArea(Connection conn, String nom, String tematica, int capacitatMaxima) {
        // Código para agregar un área al sistema
        
        try (Statement stmt = conn.createStatement()) {
            // Convertir capacitatMaxima y edat a String
            String capacitatMaximaStr = String.valueOf(capacitatMaxima);
            
            // Inserir dades a la taula 'persones'
            stmt.execute("INSERT INTO arees (nom, tematica, capacitat_maxima, edat) VALUES ('"+nom+"', '"+tematica+"', '"+capacitatMaximaStr+"')");
            conn.commit(); // Confirma els canvis
            System.out.println("La base de dades ha estat inicialitzada amb èxit.");
        } catch (SQLException e) {
            try {
                conn.rollback(); // Reverteix els canvis
                System.out.println("fallo insert.");
            } catch (SQLException ex) {
                System.out.println("fallo insert 2.");
                ex.printStackTrace();
            }
            System.out.println(e.getMessage());
        }
    }
    
    
    public static void afegirTarifa(Connection conn,String nom, BigDecimal preu, int durada) {
        // Código para agregar una tarifa al sistema
        try (Statement stmt = conn.createStatement()) {
            // Inserir dades a la taula 'persones'
            stmt.execute("INSERT INTO tarifes (nom, preu,durada) VALUES ('"+nom+"',"+preu+", "+durada+")");
            conn.commit(); // Confirma els canvis
            System.out.println("La base de dades ha estat inicialitzada amb èxit.");
        } catch (SQLException e) {
            try {
                conn.rollback(); // Reverteix els canvis
                System.out.println("Rollback realitzat degut a un error.");
            } catch (SQLException ex) {
                System.out.println("Error en intentar fer rollback.");
                ex.printStackTrace();
            }
            System.out.println(e.getMessage());
        }
    }
    
    public static void definirAccesAreaTarifa(Connection conn,int idArea, int idTarifa) {
        // Código para definir el acceso de un área a una tarifa específica
        try (Statement stmt = conn.createStatement()) {
            // Inserir dades a la taula 'persones'
            stmt.execute("INSERT INTO acces_area_tarifa (id_area, id_tarifa) VALUES ("+idArea+","+idTarifa+")");
            conn.commit(); // Confirma els canvis
            System.out.println("La base de dades ha estat inicialitzada amb èxit.");
        } catch (SQLException e) {
            try {
                conn.rollback(); // Reverteix els canvis
                System.out.println("Rollback realitzat degut a un error.");
            } catch (SQLException ex) {
                System.out.println("Error en intentar fer rollback.");
                ex.printStackTrace();
            }
            System.out.println(e.getMessage());
        }
    }
    
    public static void llistarArees(Connection conn) {
        String query = "SELECT * FROM arees";
    
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
    
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
    
            // Construir encabezado
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(String.format("%-20s", rsmd.getColumnName(i)));
            }
            System.out.println();
    
            // Construir filas
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(String.format("%-20s", rs.getString(i)));
                }
                System.out.println();
            }
    
            conn.commit();
            rs.close();
    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void llistarTarifes(Connection conn) {
        String query = "SELECT * FROM tarifes";
    
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
    
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
    
            // Construir encabezado
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(String.format("%-20s", rsmd.getColumnName(i)));
            }
            System.out.println();
    
            // Construir filas
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(String.format("%-20s", rs.getString(i)));
                }
                System.out.println();
            }
    
            conn.commit();
            rs.close();
    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void llistarAreesAccesiblesPerTarifa(Connection conn, int idTarifa) {
        String query = "SELECT * FROM tarifes t INNER JOIN acces_area_tarifa ac ON ac.id_tarifa=t.id_tarifa WHERE t.id_tarifa = " + idTarifa;
    
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
    
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
    
            // Construir encabezado
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(String.format("%-20s", rsmd.getColumnName(i)));
            }
            System.out.println();
    
            // Construir filas
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(String.format("%-20s", rs.getString(i)));
                }
                System.out.println();
            }
    
            conn.commit();
            rs.close();
    
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public static void llistarTarifesPerAccesArea(Connection conn,int idArea) {
        // Código para listar todas las tarifas que permiten el acceso a un área específica

         // Código para listar todas las áreas accesibles mediante una tarifa específica
         String query = "SELECT * FROM arees t inner join acces_area_tarifa ac on ac.id_area=t.id_area where t.id_area = "+idArea+"";
    
         try (Statement stmt = conn.createStatement();
              ResultSet rs = stmt.executeQuery(query)) {
      
             // Obtenir metadades del ResultSet per saber el nombre de columnes
             ResultSetMetaData rsmd = rs.getMetaData();
             int columnCount = rsmd.getColumnCount();
     
             // Mostrar noms de columnes
             for (int i = 1; i <= columnCount; i++) {
                 System.out.print(rsmd.getColumnName(i) + "\t");
             }
             System.out.println();
             
             // Recorrer i mostrar les files
             while (rs.next()) {
                 for (int i = 1; i <= columnCount; i++) {
                     String value = rs.getString(i);
                     System.out.print(value + "\t");
                 }
                 System.out.println(); // Salta a la següent línia després de cada fila
             }
 
             conn.commit(); // Confirma els canvis
             rs.close();
 
         } catch (SQLException e) {
             System.out.println(e.getMessage());
         }
    }
    
    public static int obtenirIdAreaPerNom(Connection conn,String nomArea) {
        String query = "SELECT id_area FROM arees where nom = '"+nomArea+"'";
    
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
     
            // Obtenir metadades del ResultSet per saber el nombre de columnes
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
    
            // Mostrar noms de columnes
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            System.out.println();
            
            // Recorrer i mostrar les files
            while (rs.next()) {
                System.out.println("el id es");

                System.out.println(rs.getInt(1));
                return rs.getInt(1);
            }

            conn.commit(); // Confirma els canvis
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
    public static int obtenirIdTarifaPerNom(Connection conn,String nomTarifa) {
        String query = "SELECT id_tarifa FROM tarifes where nom = '"+nomTarifa+"'";
    
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
     
            // Obtenir metadades del ResultSet per saber el nombre de columnes
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
    
            // Mostrar noms de columnes
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            System.out.println();
            
            // Recorrer i mostrar les files
            while (rs.next()) {
                System.out.println("el id es");

                System.out.println(rs.getInt(1));
                return rs.getInt(1);
            }

            conn.commit(); // Confirma els canvis
            rs.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }
    
}
