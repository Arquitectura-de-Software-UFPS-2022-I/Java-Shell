package Model;

/**
 *
 * @author Java-Shell
 */
public class Request {
    int id_request;
    int id_user;
    int id_signature;
    int id_pdf;
    String date;
    int status;

    public Request() {
    }

    public Request(int id_request, int id_user, int id_signature, int id_pdf, String date, int status) {
        this.id_request = id_request;
        this.id_user = id_user;
        this.id_signature = id_signature;
        this.id_pdf = id_pdf;
        this.date = date;
        this.status = status;
    }

    public int getId_request() {
        return id_request;
    }

    public void setId_request(int id_request) {
        this.id_request = id_request;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_signature() {
        return id_signature;
    }

    public void setId_signature(int id_signature) {
        this.id_signature = id_signature;
    }

    public int getId_pdf() {
        return id_pdf;
    }

    public void setId_pdf(int id_pdf) {
        this.id_pdf = id_pdf;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Request{" + "id_request=" + id_request + ", id_user=" + id_user + ", id_signature=" + id_signature + ", id_pdf=" + id_pdf + ", date=" + date + ", status=" + status + '}';
    }
    
}
