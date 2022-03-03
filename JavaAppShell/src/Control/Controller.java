package Control;

import Model.Pdf;
import Model.User;
import Services.PDFs;
import Services.Users;

/**
 *
 * @author Java-Shell
 */
public class Controller {

    Users us = new Users();
    PDFs pdfs = new PDFs();

    //Subir firma
    public void uploadSignature() {
    }

    //Subir PDF
    public void uploadPDF(Pdf pdf) {
        pdfs.uploadPDF(pdf);
    }

    //Solicitar firmas
    public void requestSignature() {
    }

    //Mostrar todos los PDFs
    public void showPDF() {
    }

    //Firmar PDF
    public void signPDF() {
    }

    //Descargar PDF
    public void downloadPDF() {
    }

    //Log in
    public boolean login(int id_user, String password) {
        try {
            return us.login(id_user, password);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return false;
    }

    //Regitrar nuevo usuario
    public void singUp(User user) {
        try {
            us.singUp(user);
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }

    //Log out
    public void logOut() {
        us.logOut();
    }

}
