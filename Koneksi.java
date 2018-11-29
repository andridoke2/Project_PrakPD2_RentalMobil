
package tugaspd2;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Koneksi {
    public static Connection setKoneksi() {
        String conStr = "jdbc:mysql://localhost/tugaspd";
        Connection koneksi = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            koneksi = (Connection) DriverManager.getConnection(conStr, "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, e);
            System.out.println("Koneksi gagal");
        }
        return koneksi;
    }
    public static int execute(String sql) {
        int status = 0;
        Connection koneksi = setKoneksi();
        try {
            Statement st = koneksi.createStatement();
            status = st.executeUpdate(sql);
        } catch (SQLException e) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, e);
        }
        return status;
    }
    public static ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        Connection koneksi = setKoneksi();
        try {
            Statement st = koneksi.createStatement();
            rs = st.executeQuery(sql);
        } catch (SQLException e) {
            Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, e);
        }
        return rs;
        }
    }