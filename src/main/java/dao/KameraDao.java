/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import java.util.List;
import konfig.DatabaseException;
import konfig.Koneksi;
import model.Kamera;

/**
 *
 * @author Dovs
 */
public class KameraDao {
    public List<Kamera> findAll() {
        try (EntityManager em = ambilEntityManager()) {
            return em.createQuery("from Kamera order by idKamera", Kamera.class).getResultList();
        } catch (PersistenceException e) {
            throw new DatabaseException("Gagal membaca data kamera: " + e.getMessage(), e);
        }
    }

    /** @return kamera, atau null jika ID tidak ditemukan */
    public Kamera findById(int idKamera) {
        try (EntityManager em = ambilEntityManager()) {
            return em.find(Kamera.class, idKamera);
        } catch (PersistenceException e) {
            throw new DatabaseException("Gagal mencari kamera: " + e.getMessage(), e);
        }
    }

    private EntityManager ambilEntityManager() {
        EntityManager em = Koneksi.getEntityManager();
        if (em == null) {
            throw new DatabaseException("Tidak dapat terhubung ke database. Pastikan MySQL aktif.", null);
        }
        return em;
    }
}