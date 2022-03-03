package Principal;

import Control.Controller;
import Model.Pdf;
import Model.User;
import Services.Conexion;
import Services.PDFs;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

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

    //Start menu
    //1. Iniciar sesión 
    public static boolean logIn() {
        boolean res = true;
        System.out.println("\nDigite código");
        int cod = sc.nextInt();
        System.out.println("Digite su contraseña");
        String pass = sc.next();
        if (cod != 0 && pass != "") {
            //call controller
            res = controller.login(cod, pass);
        }
        return res;
    }

    //2. Regitrar nuevo usuario
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
            //call controller
            controller.singUp(user);
        } else {
            System.out.println("Fallo al registrar");
        }
    }

    //Services menu
    //1. Subir firma
    public static void uploadSignature() {
    }

    //2. Subir PDF
    public static void uploadPDF(String file) {

        Pdf pa = new Pdf();
        File f;
        f = new File(file);
        System.out.println("Digite la coordenada X");
        float x = sc.nextFloat();
        pa.setPos_x(x);
        System.out.println("Digite la coordenada Y");
        float y = sc.nextFloat();
        pa.setPos_y(y);
        pa.setStatus(0);
        try {
            byte[] pdf = new byte[(int) f.length()];
            InputStream input = new FileInputStream(f);
            input.read(pdf);
            pa.setFile(pdf);
            System.out.println("siii");
        } catch (Exception e) {
            pa.setFile(null);
            System.out.println("noo");
        }
        controller.uploadPDF(pa);
    }

    //3. Solicitar firmas 
    public static void requestSignature() {
        String em = "linavanessavepu@ufps.edu.co";
        String pass = "linaca01";

        SimpleEmail email = new SimpleEmail();

        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(em, pass));
        email.setSSLOnConnect(true);

        try {
            email.setFrom(em);
            email.setSubject("prueba");
            email.setMsg("Enviando email desde java");
            email.addTo(em);
            email.send();
            System.out.println("Envio exitoso");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //4. Mostrar todos los PDFs 
    public static void showPDF() {
    }
    //read document

    //5. Firmar PDF
    public static void signPDF() {
        try {
            // path where the pdf is to be created.
            String path = "C:\\Users\\Lina\\OneDrive\\Escritorio\\Calendario2022-1.pdf";
            PdfWriter pdfwriter = new PdfWriter(path);

            //Creating a PdfDocument object.
            //passing PdfWriter object constructor
            PdfDocument pdfdocument
                    = new PdfDocument(pdfwriter);

            // Creating a Document and
            // passing pdfDocument object
            Document document = new Document(pdfdocument);

            // Create an ImageData object
            String imageFile = "C:\\Users\\Lina\\OneDrive\\Imágenes\\ink.png";
            ImageData data
                    = ImageDataFactory.create(imageFile);
            // Creating an Image object
            Image image = new Image(data);

            // Set the position of the image.
            image.setFixedPosition(50, 100);
            image.setWidth(64);
            image.setHeight(64);

            // Adding image to the document
            document.add(image);
            // Closing the document
            document.close();

            System.out.println(
                    "Image  position set successfully in pdf");
        } catch (Exception e) {
            System.out.println(
                    "unable to set image position due to " + e);
        }
    }

    //6. Descargar PDF
    public static void downloadPDF() {
    }
/////////////////////////////////////////////////////////////////////
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

    public static void main(String[] args) throws IOException {

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
                            //Services menu
                            System.out.println("************Servicios************* \n"
                                    + "    1. Subir firma \n"
                                    + "    2. Subir PDF \n"
                                    + "    3. Solicitar firmas \n"
                                    + "    4. Mostrar todos los PDFs \n"
                                    + "    5. Firmar PDF \n"
                                    + "    6. Descargar PDF \n"
                                    + "    0. Cerrar sesión \n"
                                    + "********************************");
                            System.out.println("Ingresa el número del servicio");
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
                                    String file;
                                    System.out.println("Introduzca la ruta del fichero que desea leer");
                                    String saltoDeLinea = sc.nextLine();
                                    file = sc.nextLine();
                                    uploadPDF(file);
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
                                    signPDF();
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
