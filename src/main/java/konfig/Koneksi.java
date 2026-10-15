/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
/**
 *
 * @author Dovs
 */
public class Koneksi {
    private static final String NAMA_UNIT = "penyewaankameraPU";

    private static EntityManagerFactory emf;

    // Buka EntityManager baru (jalur untuk ngobrol dengan database)
    public static EntityManager getEntityManager() {
        try {
            if (emf == null || !emf.isOpen()) {
                emf = Persistence.createEntityManagerFactory(NAMA_UNIT);
            }
            return emf.createEntityManager();
        } catch (Exception e) {
            System.out.println("Gagal Koneksi: " + e.getMessage());
            return null;
        }
    }

    // Dipanggil saat program selesai
    public static void tutup() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}