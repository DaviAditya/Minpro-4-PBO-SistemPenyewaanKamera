/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package model;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

/**
 *
 * @author Dovs
 */
@Entity
@Table(name = "kamera")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "kategori")
public abstract class Kamera implements Disewakan {
    public static final int PENGALI_HARIAN = 3;

    @Id
    @Column(name = "id")
    private int idKamera;

    @Column(name = "nama_kamera")
    private String namaKamera;

    @Column(name = "harga_sewa_per3jam")
    private double hargaSewa;

    // Isinya beda tiap jenis: tipe lensa (DSLR), fitur 4K (Mirrorless), efek retro (DigiCam)
    @Column(name = "atribut_tambahan")
    private String atribut;

    private int stok;

    // Wajib ada untuk Hibernate
    protected Kamera() {
    }

    public Kamera(int idKamera, String namaKamera, double hargaSewa) {
        this.idKamera = idKamera;
        this.namaKamera = namaKamera;
        setHargaSewa(hargaSewa);
    }
    public Kamera(int idKamera, String namaKamera, double hargaSewa, String atribut) {
        this.idKamera = idKamera;
        this.namaKamera = namaKamera;
        setHargaSewa(hargaSewa);
        this.atribut = atribut;
    }
    // Getter dan Setter
    public int getIdKamera() {
        return idKamera;
    }

    public String getNamaKamera() {
        return namaKamera;
    }

    public void setNamaKamera(String namaKamera) {
        this.namaKamera = namaKamera;
    }

    public double getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(double hargaSewa) {
        if (hargaSewa < 0) {
            throw new IllegalArgumentException("Harga sewa tidak boleh negatif!");
        }
        this.hargaSewa = hargaSewa;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        if (stok < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif!");
        }
        this.stok = stok;
    }

    protected String getAtribut() {
        return atribut;
    }

    protected void setAtribut(String atribut) {
        this.atribut = atribut;
    }

    public abstract String getJenisKamera();
    public abstract String getSpesifikasi();

    public double hitungBiayaSewa(int durasi3Jam) {
        return hitungBiayaSewa(durasi3Jam, TipeSewa.per3jam);
    }

    @Override
    public double hitungBiayaSewa(int durasi, TipeSewa tipe) {
        if (tipe == TipeSewa.Harian) {
            return hargaSewa * PENGALI_HARIAN * durasi;
        }
        return hargaSewa * durasi;
    }

    @Override
    public String getInfo() {
        return "ID: " + idKamera + " | Nama: " + namaKamera;
    }

    @Override
    public String toString() {
        return "[" + getJenisKamera() + "] ID: " + idKamera
                + " | " + namaKamera
                + " | Rp" + hargaSewa + " / 3 jam"
                + " | " + getSpesifikasi()
                + " | Stok: " + stok;
    }
}