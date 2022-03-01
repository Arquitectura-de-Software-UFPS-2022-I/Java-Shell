package Model;

import java.io.File;

/**
 *
 * @author Java-Shell
 */
public class Pdf {

    int id_pdf;
    File file;
    float pos_x;
    float pos_y;
    int status;

    public Pdf() {
    }

    public Pdf(int id_pdf, File file, float pos_x, float pos_y, int status) {
        this.id_pdf = id_pdf;
        this.file = file;
        this.pos_x = pos_x;
        this.pos_y = pos_y;
        this.status = status;
    }

    public int getId_pdf() {
        return id_pdf;
    }

    public void setId_pdf(int id_pdf) {
        this.id_pdf = id_pdf;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public float getPos_x() {
        return pos_x;
    }

    public void setPos_x(float pos_x) {
        this.pos_x = pos_x;
    }

    public float getPos_y() {
        return pos_y;
    }

    public void setPos_y(float pos_y) {
        this.pos_y = pos_y;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Pdf{" + "id_pdf=" + id_pdf + ", file=" + file + ", pos_x=" + pos_x + ", pos_y=" + pos_y + ", status=" + status + '}';
    }

}
