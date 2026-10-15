/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dovs
 */
public enum TipeSewa {
    Harian("Harian"),
    per3jam("Per 3 Jam");

    private final String label;

    TipeSewa(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}