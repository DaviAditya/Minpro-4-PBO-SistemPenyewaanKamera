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
import model.Customer;

/**
 *
 * @author Dovs
 */
public class CustomerDao {
    public List<Customer> findAll() {
        try (EntityManager em = ambilEntityManager()) {
            return em.createQuery("from Customer order by idCust", Customer.class).getResultList();
        } catch (PersistenceException e) {
            throw new DatabaseException("Gagal membaca data customer: " + e.getMessage(), e);
        }
    }

    /** @return customer, atau null jika ID tidak ditemukan */
    public Customer findById(String idCust) {
        try (EntityManager em = ambilEntityManager()) {
            return em.find(Customer.class, idCust);
        } catch (PersistenceException e) {
            throw new DatabaseException("Gagal mencari customer: " + e.getMessage(), e);
        }
    }

    public void insert(Customer c) {
        EntityManager em = ambilEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DatabaseException("Gagal menyimpan customer: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    /** Simpan perubahan nama & no. telp. @return false jika ID tidak ditemukan */
    public boolean update(Customer c) {
        EntityManager em = ambilEntityManager();
        try {
            if (em.find(Customer.class, c.getIdCust()) == null) {
                return false;
            }
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new DatabaseException("Gagal memperbarui customer: " + e.getMessage(), e);
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