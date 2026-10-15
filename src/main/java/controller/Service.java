/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import dao.CustomerDao;
import dao.KameraDao;
import dao.PenyewaanDao;
import java.util.List;
import model.Customer;
import model.Kamera;
import model.Penyewaan;
import model.TipeSewa;
/**
/**
 *
 * @author Dovs
 */
public class Service {
    private final KameraDao kameraDao;
    private final CustomerDao customerDao;
    private final PenyewaanDao penyewaanDao;
    public Service() {
        this(new KameraDao(), new CustomerDao(), new PenyewaanDao()); 
    }
    public Service(KameraDao kameraDao, CustomerDao customerDao, PenyewaanDao penyewaanDao) {
        this.kameraDao = kameraDao;
        this.customerDao = customerDao;
        this.penyewaanDao = penyewaanDao;
    }
    // Getter
    public List<Kamera> getDaftarKamera() {
        return kameraDao.findAll();
    }
    public List<Customer> getDaftarCustomer() {
        return customerDao.findAll();
    }
    public List<Penyewaan> getDaftarSewa() {
        return penyewaanDao.findAll();
    }
    // Pencarian
    public Kamera cariKamera(int idKamera) {
        return kameraDao.findById(idKamera);
    }

    public Customer cariCustomer(String idCust) {
        return customerDao.findById(idCust.trim());
    }

    public Penyewaan cariSewa(String kodeSewa) {
        return penyewaanDao.findByKode(kodeSewa.trim());
    }

    // Create
    public Penyewaan tambahPenyewaan(String kodeSewa, String idCust, String namaCust, String noTelp,
                                 int idKamera, TipeSewa tipeSewa, int durasi) {
        // Validasi sebelum ke database
        Kamera kameraDipilih = cariKamera(idKamera);
        if (kameraDipilih == null) {
            throw new IllegalArgumentException("ID Kamera tidak ditemukan!");
        }
        Penyewaan sewaBaru = new Penyewaan(kodeSewa, idCust, namaCust, noTelp, kameraDipilih, tipeSewa, durasi);
        if (penyewaanDao.findByKode(sewaBaru.getKodeSewa()) != null) {
            throw new IllegalArgumentException("Kode sewa " + sewaBaru.getKodeSewa() + " sudah dipakai!");
        }

        if (customerDao.findById(sewaBaru.getIdCust()) == null) {
            customerDao.insert(sewaBaru.getCustomer());
        } else {
            customerDao.update(sewaBaru.getCustomer());
        }
        penyewaanDao.insert(sewaBaru);
        return sewaBaru;
    }

    // Delete
    public boolean batalPenyewaan(String kodeSewa) {
        return penyewaanDao.delete(kodeSewa.trim());
    }

    // Update
    public boolean updateIdCustomer(String kodeSewa, String idCustBaru) {
        Penyewaan p = cariSewa(kodeSewa);
        if (p == null) {
            return false;
        }
        Customer baru = customerDao.findById(idCustBaru.trim());
        if (baru == null) {
            // ID belum terdaftar: buat customer baru dengan nama & no. telp yang sama
            baru = new Customer(idCustBaru, p.getNamaCust(), p.getNoTelp());
            customerDao.insert(baru);
        }
        p.setCustomer(baru);
        return penyewaanDao.update(p);
    }

    public boolean updateNamaCustomer(String kodeSewa, String namaCustBaru) {
        Penyewaan p = cariSewa(kodeSewa);
        if (p == null) {
            return false;
        }
        p.setNamaCust(namaCustBaru);
        return customerDao.update(p.getCustomer());
    }

    public boolean updateNoTelepon(String kodeSewa, String noTelpBaru) {
        Penyewaan p = cariSewa(kodeSewa);
        if (p == null) {
            return false;
        }
        p.setNoTelp(noTelpBaru);
        return customerDao.update(p.getCustomer());
    }

    public boolean updateKamera(String kodeSewa, int idKameraBaru) {
        Penyewaan p = cariSewa(kodeSewa);
        Kamera kameraBaru = cariKamera(idKameraBaru);
        if (p == null || kameraBaru == null) {
            return false;
        }
        p.setKamera(kameraBaru);
        return penyewaanDao.update(p);
    }

    public boolean updateDurasi(String kodeSewa, TipeSewa tipeBaru, int durasiBaru) {
        Penyewaan p = cariSewa(kodeSewa);
        if (p == null) {
            return false;
        }
        p.setTipeSewa(tipeBaru);
        p.setDurasi(durasiBaru);
        return penyewaanDao.update(p);
    }
}