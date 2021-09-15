
package dao;



import java.sql.Connection;
import java.sql.DriverManager;
public class Conexion {
    public static Connection cnx = null;
    
    public static Connection conectar() throws Exception{
        
        
        try {
//           InputStream input = Conexion.class.getClassLoader().getResourceAsStream("properties/db.properties");
//        Properties properties = new Properties();
//        properties.load(input);
        String driver = "oracle.jdbc.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521/XE";
        String user = "PESFLET";
        String pwd = "1234"; 
            
            Class.forName(driver);
            cnx = DriverManager.getConnection(url,user,pwd);
        } catch (Exception e) {
            System.out.println("Error de Conexión" + "  " + e.getMessage() + "  "+ e.getStackTrace());
        }
        return cnx;
    }
//     public static Connection conectar() throws Exception {
//        try {
//            forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            cnx = getConnection("jdbc:sqlserver://localhost:53591;databaseName=dbPESFLET", "sa", "1234");
//        } catch (ClassNotFoundException | SQLException e) {
//            out.println("Error: " + e);
//        }
//        return cnx;
        
//        public static Connection conectar() throws Exception {
//        try {
//            forName("oracle.jdbc.OracleDriver");
//            cnx = getConnection("jdbc:oracle:thin@localhost:1521/XE", "PESFLET", "1234");
//        } catch (ClassNotFoundException | SQLException e) {
//            out.println("Error: " + e);
//        }
//        return cnx;
//        
//    }
    public static void main(String[] args) throws Exception {
        try {
             Conexion.conectar();
        if(Conexion.cnx != null) System.out.println("Estoy Conectado");
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());    
        }
    }
    
    public void cerrar() throws Exception{
        if(cnx != null){
            cnx.close();
        }
    }
    
}
