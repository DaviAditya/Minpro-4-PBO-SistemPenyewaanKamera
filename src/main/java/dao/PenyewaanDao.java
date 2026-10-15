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
import model.Penyewaan;
    
/**
 *
 * @author Dovs
 */
public class PenyewaanDao {
    public List<Penyewaan> findAll() {
        try (EntityManager em = ambilEntityManager()) {
            return em.createQuery("from Penyewaan order by kodeSewa", Penyewaan.class).getResultList();
        } catch (PersistenceException e) {
            throw new DatabaseException("Gagal membaca data penyewaan: " + e.getMessage(), e);
        }
    }

    /** @return penyewaan, atau null jika kode tidak ditemukan */
    public Penyewaan findByKode(String kodeSewa) {
        try (EntityManager em = ambilEntityManager()) {
            return em.find(Penyewaan.class, kodeSewa);
        } catch (PersistenceException e) {
            throw new DatabaseException("Gagal mencari penyewaan: " + e.getMessage(), e);
        }
    }

    public void insert(Penyewaan p) {
        EntityManager em = ambilEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DatabaseException("Gagal menyimpan penyewaan: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /** Simpan perubahan customer, kamera, tipe sewa, dan durasi. @return false jika kode tidak ditemukan */
    public boolean update(Penyewaan p) {
        EntityManager em = ambilEntityManager();
        try {
            if (em.find(Penyewaan.class, p.getKodeSewa()) == null) {
                return false;
            }
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DatabaseException("Gagal memperbarui penyewaan: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /** @return false jika kode tidak ditemukan */
    public boolean delete(String kodeSewa) {
        EntityManager em = ambilEntityManager();
        try {
            Penyewaan p = em.find(Penyewaan.class, kodeSewa);
            if (p == null) {
                return false;
            }
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DatabaseException("Gagal menghapus penyewaan: " + e.getMessage(), e);
        } finally {
            em.close();
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