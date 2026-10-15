/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author Dovs
 */
@Entity
@Table(name = "penyewaan")
public class Penyewaan {
    @Id
    @Column(name = "kode_sewa")
    private String kodeSewa;

    // Banyak penyewaan bisa dimiliki satu customer
    @ManyToOne
    @JoinColumn(name = "id_cust")
    private Customer customer;

    // Banyak penyewaan bisa memakai satu kamera
    @ManyToOne
    @JoinColumn(name = "id_kamera")
    private Kamera kamera;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipe_sewa")
    private TipeSewa tipeSewa;

    private int durasi;

    // Wajib ada untuk Hibernate
    protected Penyewaan() {
    }
    
    public Penyewaan(String kodeSewa, String idCust, String namaCust, String noTelp,
                     Kamera kamera, TipeSewa tipeSewa, int durasi) {
        this(kodeSewa, new Customer(idCust, namaCust, noTelp), kamera, tipeSewa, durasi);
    }

    public Penyewaan(String kodeSewa, Customer customer,
                     Kamera kamera, TipeSewa tipeSewa, int durasi) {
        this.kodeSewa = validasiTeks(kodeSewa, "Kode sewa");
        setCustomer(customer);
        setKamera(kamera);
        setTipeSewa(tipeSewa);
        setDurasi(durasi);
    }

    // Validasi jangan null
    private static String validasiTeks(String nilai, String namaField) {
        if (nilai == null || nilai.trim().isEmpty()) {
            throw new IllegalArgumentException(namaField + " tidak boleh kosong!");
        }
        return nilai.trim();
    }

    // Getter dan Setter
    public String getKodeSewa() {
        return kodeSewa;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer tidak boleh kosong!");
        }
        this.customer = customer;
    }

    // Pintasan ke data customer (dipakai View)
    public String getIdCust() {
        return customer.getIdCust();
    }

    public String getNamaCust() {
        return customer.getNamaCust();
    }

    public void setNamaCust(String namaCust) {
        customer.setNamaCust(namaCust);
    }

    public String getNoTelp() {
        return customer.getNoTelp();
    }

    public void setNoTelp(String noTelp) {
        customer.setNoTelp(noTelp);
    }

    public Kamera getKamera() {
        return kamera;
    }

    public void setKamera(Kamera kamera) {
        if (kamera == null) {
            throw new IllegalArgumentException("Kamera tidak boleh kosong!");
        }
        this.kamera = kamera;
    }

    public TipeSewa getTipeSewa() {
        return tipeSewa;
    }

    public void setTipeSewa(TipeSewa tipeSewa) {
        if (tipeSewa == null) {
            throw new IllegalArgumentException("Tipe sewa tidak boleh kosong!");
        }
        this.tipeSewa = tipeSewa;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        if (durasi <= 0) {
            throw new IllegalArgumentException("Durasi sewa minimal 1!");
        }
        this.durasi = durasi;
    }

    public String getDeskripsiDurasi() {
        if (tipeSewa == TipeSewa.Harian) {
            return durasi + " hari";
        }
        return durasi + " paket (" + (durasi * 3) + " jam)";
    }

    public double hitungTotalBiaya() {
        return kamera.hitungBiayaSewa(durasi, tipeSewa);
    }
}