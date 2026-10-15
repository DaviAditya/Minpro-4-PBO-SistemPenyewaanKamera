/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 *
 * @author Dovs
 */
@Entity
@DiscriminatorValue("Mirrorless")
public class KameraMirorrless extends Kamera {

    protected KameraMirorrless() {
    }

    public KameraMirorrless(int idKamera, String namaKamera, double hargaSewa, boolean adaFitur4K) {
        super(idKamera, namaKamera, hargaSewa, String.valueOf(adaFitur4K));
    }

    public boolean isAdaFitur4K() {
        String nilai = getAtribut() == null ? "" : getAtribut().trim();
        return nilai.equalsIgnoreCase("true");
    }

    public void setAdaFitur4K(boolean adaFitur4K) {
        setAtribut(String.valueOf(adaFitur4K));
    }

    @Override
    public String getJenisKamera() {
        return "Mirrorless";
    }

    @Override
    public String getSpesifikasi() {
        return "Fitur 4K: " + (isAdaFitur4K() ? "Ya" : "Tidak");
    }
}