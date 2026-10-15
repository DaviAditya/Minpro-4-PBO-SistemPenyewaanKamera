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
@DiscriminatorValue("DigiCam")
public class DigiCam extends Kamera {

    protected DigiCam() {
    }

    public DigiCam(int idKamera, String namaKamera, double hargaSewa, String efekRetro) {
        super(idKamera, namaKamera, hargaSewa, efekRetro);
    }

    public String getEfekRetro() {
        return getAtribut();
    }

    public void setEfekRetro(String efekRetro) {
        setAtribut(efekRetro);
    }

    @Override
    public String getJenisKamera() {
        return "Digicam";
    }

    @Override
    public String getSpesifikasi() {
        return "Efek Retro: " + getEfekRetro();
    }
}