package pruebasdb;
import java.util.Scanner;
import java.sql.*;

/**
 *
 * @author Jesus Antonio Lopez Bandala
 */
public class PruebasDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        boolean seguir = true;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("MENU");
            System.out.print("\n1. Insertar");
            System.out.print("\n2. Borrar");
            System.out.print("\n3. Actualizar");
            System.out.print("\n4. Mostrar");
            System.out.print("\n5. Salir");
            System.out.print("\n\nTeclee una opcion: ");
            int op = scanner.nextInt();
            switch(op){
                case 1 -> {
                    System.out.println("\n\n **** INSERTAR ****");
                    System.out.print("\n\nIngrese su nombre: ");
                    scanner.nextLine();
                    String nom = scanner.nextLine();
                    System.out.print("\nIngrese su apellido paterno: ");
                    String ap_pat = scanner.nextLine();
                    System.out.print("\nIngrese su apellido materno: ");
                    String ap_mat = scanner.nextLine();
                    System.out.println("\nIngrese su color favorito: ");
                    String color_fav = scanner.nextLine();
                    try{
                        if(MySQLConnection.conectarBD()){
                            Connection conexion = MySQLConnection.getConexion();
                            conexion.setAutoCommit(false);
                            String sql = "INSERT INTO mi_tabla VALUES (DEFAULT, ?, ?, ?, ?)";
                            PreparedStatement st = conexion.prepareStatement(sql);
                            st.setString(1, nom);
                            st.setString(2, ap_pat);
                            st.setString(3, ap_mat);
                            st.setString(4, color_fav);
                            st.executeUpdate();
                            conexion.commit();
                            conexion.setAutoCommit(true);
                            System.out.println("Registro completado :D");
                            conexion.close();
                        } else{
                            System.out.println("No hubo conexion con la BD");
                        }
                    } catch(SQLException e){
                        System.out.println("Error al insertar los datos :(");
                        e.printStackTrace();
                    }
                }
                case 2 -> {
                    System.out.println("\n\n **** BORRAR ****");
                    System.out.print("\n\nIngrese el ID a eliminar: ");
                    int id = scanner.nextInt();
                    try{
                        if(MySQLConnection.conectarBD()){
                            Connection conexion = MySQLConnection.getConexion();
                            conexion.setAutoCommit(false);
                            String sql = "DELETE FROM mi_tabla WHERE id = " + id;
                            Statement st = conexion.createStatement();
                            st.executeUpdate(sql);
                            System.out.println("Registro eliminado :D");
                            conexion.commit();
                            conexion.setAutoCommit(true);
                            conexion.close();
                        } else{
                            System.out.println("No hubo conexion con la BD");
                        }
                    } catch(SQLException e){
                        System.out.println("Error al eliminar, verifica el ID");
                        e.printStackTrace();
                    }
                }
                case 3 -> {
                    System.out.println("\n\n **** ACTUALIZAR ****");
                    System.out.print("\n\nIngrese el ID a modificar: ");
                    int id = scanner.nextInt();
                    System.out.print("\n\n\nIngrese el nuevo nombre: ");
                    scanner.nextLine();
                    String nom = scanner.nextLine();
                    System.out.println("\nIngrese el nuevo apellido paterno: ");
                    String ap_pat = scanner.nextLine();
                    System.out.println("\nIngrese el nuevo apellido materno: ");
                    String ap_mat = scanner.nextLine();
                    System.out.println("\nIngrese el nuevo color favorito: ");
                    String color_fav = scanner.nextLine();
                    try{
                        if(MySQLConnection.conectarBD()){
                            Connection conexion = MySQLConnection.getConexion();
                            conexion.setAutoCommit(false);
                            String sql = "UPDATE mi_tabla SET nombre = '" + nom + "', "
                                    + "ap_pat = '" + ap_pat + "', "
                                    + "ap_mat = '" + ap_mat + "', "
                                    + "color_fav = '" + color_fav + "' "
                                    + "WHERE id = " + id;
                            Statement st = conexion.createStatement();
                            st.executeUpdate(sql);
                            System.out.println("Registro actualizado :D");
                            conexion.commit();
                            conexion.setAutoCommit(true);
                            conexion.close();
                        }
                    } catch(SQLException e){
                        System.out.println("No se pudo hacer la actualización, verifica el ID");
                        e.printStackTrace();
                    }
                }
                case 4 -> {
                    System.out.println("\n\n **** MOSTRAR ****");
                    System.out.println("\n\n ==== REGISTROS ====");
                    try{
                        if(MySQLConnection.conectarBD()){
                            Connection conexion = MySQLConnection.getConexion();
                            conexion.setAutoCommit(false);
                            String sql = "SELECT * FROM mi_tabla";
                            Statement st = conexion.createStatement();
                            ResultSet rs = st.executeQuery(sql);
                            while(rs.next()){
                                System.out.println(rs.getInt(1) + " | " 
                                        + rs.getString(2) + " | "
                                        + rs.getString(3) + " | "
                                        + rs.getString(4) + " | "
                                        + rs.getString(5));
                            }
                            System.out.println("\n\n");
                            conexion.commit();
                            conexion.setAutoCommit(true);
                        }
                    } catch(SQLException e){
                        System.out.println("No se pudo hacer la consulta");
                        e.printStackTrace();
                    }
                }
                case 5 -> {
                    seguir = false;
                }
            }
        } while(seguir);
    }
}
