package Principal;

import Control.Controller;
import Model.User;
import Services.Conexion;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Java-Shell
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    Conexion c = new Conexion();
    static Controller controller = new Controller();
    static User user = new User();
    static int options = 1;
    static int option = 1;

    public static boolean logIn() {
        boolean res = true;
        System.out.println("\nDigite código");
        int cod = sc.nextInt();
        System.out.println("Digite su contraseña");
        String pass = sc.next();
        if (cod != 0 && pass != "") {
            res = controller.login(cod, pass);
        }
        return res;
    }

    public static void singUp() {
        System.out.println("--Registrar--");
        System.out.println("\nDigite código");
        int cod = sc.nextInt();
        user.setId_user(cod);
        System.out.println("Digite su nombre");
        String name = sc.next();
        user.setName(name);
        System.out.println("Digite su email");
        String mail = sc.next();
        user.setEmail(mail);
        System.out.println("Digite su contraseña");
        String pass = sc.next();
        user.setPassword(pass);
        if (cod != 0 && name != "" && mail != "" && pass != "") {
            controller.singUp(user);
        } else {
            System.out.println("Fallo al registrar");
        }
    }

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

        try {
            //Start menu
            while (options != 0) {
                System.out.println("************Menú***************** \n"
                        + "    1. Iniciar sesión \n"
                        + "    2. Regitrar nuevo usuario \n"
                        + "    0. Salir \n"
                        + "******************************** \n");

                System.out.println("Ingresa el número de la opción");
                options = sc.nextInt();
                if (options == 1) {
                    //Log in
                    if (logIn()) {
                        option = 1;
                        System.out.println("---Bienvenido---");
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
                                    controller.logOut();
                                    option = 0;
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
                    } else {
                        System.out.println("Fallo al iniciar sesión");
                    }

                } else if (options == 2) {
                    //Sing up
                    singUp();
                } else {
                    System.out.println("Número digitado erroneo");
                }
            }
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }
}
