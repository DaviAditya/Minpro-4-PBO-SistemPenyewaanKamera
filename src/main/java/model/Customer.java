/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 *
 * @author Dovs
 */
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @Column(name = "id_cust")
    private String idCust;

    @Column(name = "nama_cust")
    private String namaCust;

    @Column(name = "no_telp")
    private String noTelp;

    // Wajib ada untuk Hibernate
    protected Customer() {
    }

    public Customer(String idCust, String namaCust, String noTelp) {
        this.idCust = validasiTeks(idCust, "ID customer");
        setNamaCust(namaCust);
        setNoTelp(noTelp);
    }

    private static String validasiTeks(String nilai, String namaField) {
        if (nilai == null || nilai.trim().isEmpty()) {
            throw new IllegalArgumentException(namaField + " tidak boleh kosong!");
        }
        return nilai.trim();
    }

    public String getIdCust() {
        return idCust;
    }

    public String getNamaCust() {
        return namaCust;
    }

    public void setNamaCust(String namaCust) {
        this.namaCust = validasiTeks(namaCust, "Nama customer");
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        if (noTelp == null || !noTelp.trim().matches("\\d+")) {
            throw new IllegalArgumentException("Nomor telepon harus berupa angka!");
        }
        this.noTelp = noTelp.trim();
    }
}