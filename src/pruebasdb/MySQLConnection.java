package pruebasdb;
import java.sql.*;


/**
 *
 * @author Jesus Antonio Lopez Bandala
 */
public class MySQLConnection {
    final private static String nameDB = "myDB";
    final private static String user = "root";
    final private static String password = "";
    private static Connection conexion = null;
    
    public static boolean conectarBD() {
        //Parametros de conexion de la base de datos
        String url = "jdbc:mysql://localhost:3306/" + nameDB;
        String usuario = user;
        String pass = password;
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Establecer la conexión
            conexion = DriverManager.getConnection(url, usuario, pass);
            if(conexion != null){
                return true;
            }
        } catch(ClassNotFoundException e){
            System.out.println("Error: No se encontró el driver de MySQL");
            e.printStackTrace();
            return false;
        } catch(SQLException e){
            System.out.println("Error: Fallo en la conexión a la base de datos");
            e.printStackTrace();
            return false;
        }
        return false;
    }
    
    public static Connection getConexion(){
        return conexion;
    }
    
    public static void cerrarConexion(){
        try{
            if(conexion != null){
                conexion.close();
            }
        } catch(SQLException e){
            System.out.println("Error al cerrar la conexión");
            e.printStackTrace();
        }
    }
}
