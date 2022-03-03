package Services;

import Model.Pdf;
import com.mysql.jdbc.Blob;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.io.InputStream;

/**
 *
 * @author Java-Shell
 */
public class PDFs {

    private Conexion conexion;

    public PDFs() {
        this.conexion = Conexion.getConexion();
    }

    //Subir PDF
    public void uploadPDF(Pdf pdf) {
        String sql = "INSERT INTO pdf (id_pdf, file, pos_x, pos_y, status) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(sql);
            preparedStatement.setInt(1, pdf.getId_pdf());
            preparedStatement.setBytes(2, pdf.getFile());
            preparedStatement.setDouble(3, pdf.getPos_x());
            preparedStatement.setDouble(4, pdf.getPos_y());
            preparedStatement.setInt(5, pdf.getStatus());
            conexion.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
}
