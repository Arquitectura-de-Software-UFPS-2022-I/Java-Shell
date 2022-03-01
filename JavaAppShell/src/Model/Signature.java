package Model;

import java.io.File;

/**
 *
 * @author Java-Shell
 */
public class Signature {

    int id_signature;
    File img;
    String date;

    public Signature() {
    }

    public Signature(int id_signature, File img, String date) {
        this.id_signature = id_signature;
        this.img = img;
        this.date = date;
    }

    public int getId_signature() {
        return id_signature;
    }

    public void setId_signature(int id_signature) {
        this.id_signature = id_signature;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Signature{" + "id_signature=" + id_signature + ", img=" + img + ", date=" + date + '}';
    }
    
}
