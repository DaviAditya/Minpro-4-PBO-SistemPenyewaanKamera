/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package konfig;
import jakarta.persistence.EntityManager;
import model.Kamera;
/**
 *
 * @author Dovs
 */
public class TesKoneksi {
    public static void main(String[] args){
        System.out.println("Tes koneksi");
        EntityManager em = Koneksi.getEntityManager();

        if (em == null) {
            System.out.println("Koneksi gagal");
            return;
        }
        try {
            int jumlah = em.createQuery("from Kamera", Kamera.class).getResultList().size();
            System.out.println("Koneksi Berhasil, jumlah kamera: " + jumlah);
        } catch (Exception e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        } finally {
            em.close();
            Koneksi.tutup();
        }
    }
}

