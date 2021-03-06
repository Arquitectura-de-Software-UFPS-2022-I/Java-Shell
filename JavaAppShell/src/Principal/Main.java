package Principal;

import java.io.File;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import models.FileDto;
import models.SignatureRequestDto;
import models.SignatureRequestUserDto;
import models.UserDto;
import services.impl.*;

/**
 *
 * @author Java-Shell
 */
public class Main {

    static Scanner sc = new Scanner(System.in);

    static UserServiceImpl us = new UserServiceImpl();
    static UserDto udto = new UserDto();

    static FileServiceImpl fs = new FileServiceImpl();
    static FileDto fdto = new FileDto();

    static SignatureRequestDto stDto = new SignatureRequestDto();
    static SignatureRequestServiceImpl srs = new SignatureRequestServiceImpl();

    static SignatureRequestUserDto srudto = new SignatureRequestUserDto();
    static SignatureRequestUserServiceImpl srus = new SignatureRequestUserServiceImpl();

    static int options = 1;
    static int option = 1;

    //Start menu
    //1. Iniciar sesión - R2. Inicio de sesion
    public static boolean logIn() {
        try {
            System.out.println("\nDigite nombre de usuario");
            String username = sc.next();
            System.out.println("Digite su contraseña");
            String pass = sc.next();
            //Llamo al UserServiceImpl
            udto = us.loginUser(pass, username);
            return (udto != null);
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
            return false;
        }
    }

    //2. Regitrar nuevo usuario - R1. Registro de usuario
    public static void singUp() {
        try {
            System.out.println("--Registrar--");
            System.out.println("Digite su nombre");
            String name = sc.next();
            udto.setFull_name(name);
            System.out.println("Digite su nombre de usuario");
            String username = sc.next();
            udto.setUsername(username);

            System.out.println("Digite su email");
            String mail = sc.next();
            udto.setEmail(mail);
            System.out.println("Digite su contraseña");
            String pass = sc.next();
            udto.setPassword(pass);
            us.saveUser(udto);
            System.out.println("Usuario registrado");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    //Services menu
    //1. Subir firma - R3. Subir firmas
    public static void uploadSignature() {
        try {
            System.out.println("Digite la ruta de la firma a registrar");
            String path = sc.next();
            path = path.replace("\\", "/");
            String[] parts = path.split("/");
            String nameIMG = parts[parts.length - 1];
            File file = new File(path);
            fs.saveFile(nameIMG, file);
            System.out.println("Firma registrada exitosamente");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    //2. Subir PDF - R5. Subir documento
    public static void uploadPDF() {
        try {
            System.out.println("Digite la ruta del PDF a guardar");
            String path = sc.next();
            path = path.replace("\\", "/");
            String[] parts = path.split("/");
            String namePDF = parts[parts.length - 1];
            File file = new File(path);
            fs.saveFile(namePDF, file);
            System.out.println("PDF registrado exitosamente");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    //3. Solicitar firmas - R7.	Solicitud de firma
    public static void requestSignature() {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String date = dtf.format(LocalDateTime.now());
            stDto.setCreate_date(date);

            System.out.println("Digite el asunto de la solicitud");
            String subject = sc.next();
            stDto.setSubject(subject);

            System.out.println("Digite el ID del documento");
            int document_id = sc.nextInt();
            stDto.setDocument(document_id);

            System.out.println("Digite posicion X del documento");
            int x = sc.nextInt();
            srudto.setPos_x(x);
            System.out.println("Digite posicion Y del documento");
            int y = sc.nextInt();
            srudto.setPos_y(y);
            System.out.println("Digite el numero de página del documento a firmar");
            int num_page = sc.nextInt();
            srudto.setNum_page(num_page);
            srudto.setSigned(false);
            srudto.setCreated_date(date);

            System.out.println("Digite del usuario a enviar solicitud");
            int user_id = sc.nextInt();
            stDto.setUser(user_id);

            srudto.setUser(user_id);

            stDto = srs.saveRequest(stDto);
            srudto.setRequest(stDto.getId());

            srudto = srus.saveRequest(srudto);
            System.out.println("Solicitud enviada");
        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }

    //4. Mostrar todos los PDFs - R6. Listado de documentos, R13. Listado historico de firmas
    public static void list_all_signature_history() throws Exception {
        List<SignatureRequestUserDto> list = srus.getRequestUser(udto.getId());

        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %10s %5s %5s", "ID USUARIO ENVIADO - ", "¿FIRMADO? - ", "FECHA DE CREACCIÓN - ", "FECHA DE LA FIRMA");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        for (SignatureRequestUserDto us : list) {
            System.out.format("%10d %10b %5s %5s",
                    us.getUser(), us.isSigned(), us.getCreated_date(), us.getSignature_date());
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------");
    }

    //5. Firmar PDF - R8. Firmar documento
    public static void signPDF() {
        try {
            //pedir la request y y aprobar la firma""
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            String date = dtf.format(LocalDateTime.now());
            System.out.println("Digite el ID de la solicitud que desea firmar");
            int request_id = sc.nextInt();
            srudto = srus.getRequestId(request_id);
            srudto.setSigned(true);
            srudto.setSignature_date(date);
            srus.updateRequest(srudto);
            System.out.println("Documento firmado exitosamente");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //6. Descargar PDF - R9. Generar PDF firmado
    public static void downloadPDF() {
        try {
            System.out.println("Digite el codigo del PDF a descargar");
            int cod = sc.nextInt();
            System.out.println("Digite dirección");
            String dir = sc.next();
            fs.downloadFileSigned(dir, cod);
            System.out.println("Documento guardado en: " + dir);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //8. Mostrar el listado de solicitudes de firmas aprobadas - R11. Listado de solicitudes de firmas aprobadas
    public static void list_all_signature_requests() throws Exception {
        List<SignatureRequestDto> list = srs.getRequestUser(udto.getId());

        System.out.println("-----------------------------------------------------------------------------");
        System.out.printf("%10s %10s %10s %5s", "ID USUARIO- ", "ASUNTO - ", "FECHA DE CREACCIÓN - ", "ID DOCUMENTO");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------");
        list.stream().map(re -> {
            System.out.format("%10d %20s %5s %5d",
                    re.getUser(), re.getSubject(), re.getCreate_date(), re.getDocument());
            return re;
        }).forEachOrdered(_item -> {
            System.out.println();
        });
        System.out.println("-----------------------------------------------------------------------------");
    }
    /////////////////////////////////////////////////////////////////////

    public static void showOptions() {
        //Services menu
        System.out.println("************Servicios************* \n"
                + "    1. Subir firma \n"
                + "    2. Subir PDF \n"
                + "    3. Solicitar firmas \n"
                + "    4. Mostrar el listado de historico de firmas \n"
                + "    5. Firmar PDF \n"
                + "    6. Descargar PDF \n"
                + "    7. Mostrar el listado de solicitudes de firmas aprobadas \n"
                + "    0. Cerrar sesión \n"
                + "********************************");
    }

    public static void options() throws Exception {
        while (option != 0) {
            showOptions();
            System.out.println("Ingresa el número del servicio");
            option = sc.nextInt();
            switch (option) {
                case 0:
                    //End
                    option = 0;
                    System.out.println("Sesión cerrada");
                    break;
                case 1:
                    //Upload signature
                    System.out.println("Subir firma");
                    uploadSignature();
                    break;
                case 2:
                    //Upload PDF
                    System.out.println("Subir PDF");
                    uploadPDF();
                    break;
                case 3:
                    //Request signature
                    System.out.println("Solicitar firmas");
                    requestSignature();
                    break;
                case 4:
                    //Show PDFs
                    System.out.println("Mostrar PDFs");
                    list_all_signature_history();
                    break;
                case 5:
                    //Sing a PDF
                    System.out.println("Firmar PDF");
                    signPDF();
                    break;
                case 6:
                    //Download a PDF
                    System.out.println("Descargar PDFs");
                    downloadPDF();
                    break;
                case 7:
                    //list_all_signature_requests
                    System.out.println("Listado de solicitudes de firmas aprobadas");
                    list_all_signature_requests();
                    break;
                default:
                    break;
            }
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
                        System.out.println("---Bienvenido---");
                        options();
                    } else {
                        System.out.println("Usuario no registrado");
                    }
                } else if (options == 2) {
                    //Sing up
                    singUp();
                } else {
                    System.out.println("Número digitado erroneo");
                }
            }
            options = 1;
            option = 1;
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
    }
}
