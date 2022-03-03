
import static Principal.Main.leerGrafico;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lina
 */
public class NewClass {
     public static File leerGrafico() throws IOException {
        File f = null;
        javax.swing.JFileChooser j = new javax.swing.JFileChooser();
        FileNameExtensionFilter fi = new FileNameExtensionFilter("pdf", "pdf");
        j.showOpenDialog(j);
        j.setFileFilter(fi);
        try {
            String path = j.getSelectedFile().getAbsolutePath();
            f = new File(path);
            System.out.println(f);
        } catch (NullPointerException e) {
            javax.swing.JOptionPane.showMessageDialog(j, "Solo seleccionar archivos PDF");
            System.exit(0);
        }
        return f;
    }
    public static void main(String[] args) throws IOException{
        //File f = leerGrafico();
        System.out.println(leerGrafico());
    
    }
}
