package Control;

import Model.User;
import Services.Users;

/**
 *
 * @author Java-Shell
 */
public class Controller {

    Users us = new Users();

    //Subir firma
    //Subir PDF
    //Solicitar firmas
    //Mostrar todos los PDFs
    //Firmar PDF
    //Descargar PDF
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
