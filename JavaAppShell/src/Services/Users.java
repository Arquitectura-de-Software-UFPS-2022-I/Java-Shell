package Services;

import Model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 *
 * @author Java-Shell
 */
public class Users {

    private Conexion conexion;

    public Users() {
        this.conexion = Conexion.getConexion();
    }

    //Log in
    public boolean login(int id_user, String password) throws SQLException {
        boolean status = true;
        Integer id_userc = 0;
        String sql = "SELECT * FROM user WHERE id_user = '" + id_user + "' AND password = '" + password + "'";
        try {
            PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(sql);
            ResultSet rs = conexion.query();
            while (rs.next()) {
                id_userc = rs.getInt("id_user");
            }
            status = Objects.equals(id_user, id_userc);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return status;
    }

    //Sing up
    public void singUp(User user) throws SQLException {
        String sql = "INSERT INTO user (id_user, name, email, password)"
                + " VALUES(?,?,?,?)";
        try {
            if (login(user.getId_user(), user.getPassword())) {
                System.out.println("Usuario ya registrado");
            } else {
                PreparedStatement preparedStatement = (PreparedStatement) conexion.setPreparedStatement(sql);
                preparedStatement.setInt(1, user.getId_user());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getEmail());
                preparedStatement.setString(4, user.getPassword());
                conexion.execute();
                System.out.println("Registro exitoso");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    //Log out
    public void logOut() {
        conexion.cerrarConexion();
    }

}