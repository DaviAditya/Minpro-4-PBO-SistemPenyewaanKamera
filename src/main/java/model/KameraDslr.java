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
@DiscriminatorValue("DSLR")
public class KameraDslr extends Kamera {

    protected KameraDslr() {
    }

    public KameraDslr(int idKamera, String namaKamera, double hargaSewa, String tipeLensa) {
        super(idKamera, namaKamera, hargaSewa, tipeLensa);
    }

    public String getTipeLensa() {
        return getAtribut();
    }

    public void setTipeLensa(String tipeLensa) {
        setAtribut(tipeLensa);
    }

    @Override
    public String getJenisKamera() {
        return "DSLR";
    }

    @Override
    public String getSpesifikasi() {
        return "Tipe Lensa: " + getTipeLensa();
    }
}