/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;
import controller.Service;
import dao.CustomerDao;
import dao.KameraDao;
import dao.PenyewaanDao;
import konfig.Koneksi;
import view.View;


/**
 *
 * @author Dovs
 */
public class Main {
    public static void main(String[] args) {
        System.setProperty("java.util.logging.config.file", "");
        java.util.logging.LogManager.getLogManager().reset();
        java.util.logging.Logger rootLogger = java.util.logging.Logger.getLogger("");
        rootLogger.setLevel(java.util.logging.Level.SEVERE);
        
        final Service service = new Service(new KameraDao(), new CustomerDao(), new PenyewaanDao());
        final View view = new View(service);
        view.jalankan();
        Koneksi.tutup();
    }
}
