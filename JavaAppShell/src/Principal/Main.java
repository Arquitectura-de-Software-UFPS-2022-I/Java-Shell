package Principal;

import Conexion.Conexion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 *
 * @author Java-Shell
 */
public class Main {

    Conexion c = new Conexion();

    public static void archivo() {
        try {
            Scanner scs = new Scanner(System.in);
            String ruta;
            System.out.println("Introduzca la ruta del fichero que desea leer");
            ruta = scs.nextLine();
            ruta = ruta.replace("\\", "\\\\");
            System.out.println(ruta);
            BufferedReader br = new BufferedReader(new FileReader(ruta));
            String linea = "";
            while (linea != null) {
                linea = br.readLine();
                if (linea != null) {
                    System.out.println(linea);
                }
            }
        } catch (IOException ex) {
            System.out.println("No se ha encontrado el archivo." + ex);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int options = 1;
        int option = 1;
        try {
            //Start menu
            while (options != 0) {
                System.out.println("************Menú***************** \n"
                        + "    1. Iniciar sesión \n"
                        + "    2. Regitrar nuevo usuario \n"
                        + "    0. Salir \n"
                        + "********************************");

                System.out.println("Ingresa el número de la opción");
                options = sc.nextInt();

                switch (options) {
                    case 0:
                        break;
                    case 1:
                        //Log in
                        System.out.println("Iniciar sesion");
                        while (option != 0) {
                            System.out.println("************Acciones************* \n"
                                    + "    1. Subir firma \n"
                                    + "    2. Subir PDF \n"
                                    + "    3. Solicitar firmas \n"
                                    + "    4. Mostrar todos los PDFs \n"
                                    + "    5. Firmar PDF \n"
                                    + "    6. Descargar PDF \n"
                                    + "    0. Cerrar sesión \n"
                                    + "********************************");
                            System.out.println("Ingresa el número de la opción");
                            option = sc.nextInt();
                            switch (option) {
                                case 0:
                                    //End
                                    System.out.println("Sesión cerrada");
                                    break;
                                case 1:
                                    //Upload signature
                                    System.out.println("Subir firma");
                                    archivo();
                                    break;
                                case 2:
                                    //Upload PDF
                                    System.out.println("Subir PDF");
                                    Conexion conec = new Conexion();
                                    
                                    System.out.println(conec);
                                    break;
                                case 3:
                                    //Request signature
                                    System.out.println("Solicitar firmas");
                                    break;
                                case 4:
                                    //Show PDFs
                                    System.out.println("Mostrar PDFs");
                                    break;
                                case 5:
                                    //Sing a PDF
                                    System.out.println("Firmar PDF");
                                    break;
                                case 6:
                                    //Download a PDF
                                    System.out.println("Descargar PDFs");
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                    case 2:
                        //Sing in
                        System.out.println("Registrar");
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }

}
