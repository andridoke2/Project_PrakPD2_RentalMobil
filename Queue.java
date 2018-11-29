package tugaspd2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Queue {
    
    public static int ukuran = 100, top = 0;
    public static int[] queue = new int [ukuran];
    
    public static boolean isEmpty() {
        return top <= 0;
    }
    static boolean isFull() {
        return top >= ukuran;
    }
    
    public static void push(int i) {
        if (isFull()) {
            System.out.println("Maaf antrian penuh");
        } else {
            queue[top++] = i;
        }
    }
    public static void pop(int x) {
        if (cek2(x) == true) {
            if (isEmpty()) {
                System.out.println("Maaf antrian kosong");
            } else {
                int i = 1;
                String sql = "SELECT c.NAMA_DPN FROM unit u INNER JOIN detail_penyewaan d ON u.ID_UNIT=d.ID_UNIT "
                        + "INNER JOIN penyewaan p ON p.NO_SEWA=d.NO_SEWA "
                        + "INNER JOIN customer c ON c.ID_CUSTOMER=p.ID_CUSTOMER "
                        + "WHERE u.ID_UNIT='"+ x +"'";
                ResultSet rs = Koneksi.executeQuery(sql);
                try {
                    while (rs.next()) {
                        System.out.println("Terimakasih saudara " + rs.getString(1) +" atas kerjasamanya");
                        System.out.println();
                    }
                } catch (SQLException e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }
                sql = "SELECT NO_SEWA FROM detail_penyewaan where ID_UNIT='"+ x +"'";
                rs = Koneksi.executeQuery(sql);
                try {
                    while (rs.next()) {
                        sql = "DELETE FROM detail_penyewaan where ID_UNIT='"+ x +"'";
                        int status = Koneksi.execute(sql);
                        sql = "DELETE FROM penyewaan where NO_SEWA='"+ rs.getString(1) +"'";
                        int status2 = Koneksi.execute(sql);
                        if ((status & status2) == 1) {
                            while (i < top) {
                                queue[i - 1] = queue[i];
                                i++;
                            }
                            top--;
                        } else {
                            System.out.println("Data gagal dihapus");
                        }
                        rs.getString(1);
                    }
                } catch (SQLException e) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
    }
    public static boolean cek(int i) {
        boolean hasil = false;
        int temp = 0;
        while (temp < top) {
            if (i == queue[temp]) {
                hasil = true;
                break;
            }
            temp++;
        }
        if (hasil) {
            System.out.println("Unit sedang tidak tersedia");
        }
        return hasil;
    }
    
    public static boolean cek2(int i) {
        boolean hasil = false;
        int temp = 0;
        while (temp < top) {
            if (i == queue[temp]) {
                hasil = true;
                break;
            }
            temp++;
        }
        return hasil;
    }
}