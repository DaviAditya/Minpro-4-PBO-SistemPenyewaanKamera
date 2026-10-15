/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import controller.Service;
import java.util.List;
import java.util.Scanner;
import konfig.DatabaseException;
import model.Kamera;
import model.Penyewaan;
import model.TipeSewa;
/**
 *
 * @author Dovs
 */
public class View {
    private final Service service;
    private final Scanner scanner = new Scanner(System.in);

    public View(Service service) {
        this.service = service;
    }

    public void jalankan() {
        boolean running = true;
        while (running) {
            tampilkanMenu();
            int pilihan = inputAngka("Silahkan Input menu (1-5): ");

            try {
                switch (pilihan) {
                    case 1:
                        menuBooking();
                        break;
                    case 2:
                        tampilkanSemuaSewa();
                        break;
                    case 3:
                        menuBatal();
                        break;
                    case 4:
                        menuUpdate();
                        break;
                    case 5:
                        running = false;
                        System.out.println("\nTerima Kasih telah menggunakan sistem ini! See uu nekstaym");
                        break;
                    default:
                        System.out.println("\nError!! Pilihan tidak valid!");
                        break;
                }
            } catch (DatabaseException e) {
                // Error database tidak menghentikan program
                System.out.println("\nError Database!! " + e.getMessage());
            }
        }
        scanner.close();
    }

    // Tampilan
    private void tampilkanMenu() {
        System.out.println("==================================================");
        System.out.println("              ____________________                ");
        System.out.println("             /                    \\               ");
        System.out.println("      ______/                      \\______        ");
        System.out.println("     /      |      __________      |      \\       ");
        System.out.println("    |       |     /          \\     |       |      ");
        System.out.println("    |       |    |     ()     |    |       |      ");
        System.out.println("    |       |     \\__________/     |       |      ");
        System.out.println("     \\______|______________________|______/       ");
        System.out.println("             \\____________________/               ");
        System.out.println("                                                  ");
        System.out.println("              SISTEM PENYEWAAN KAMERA             ");
        System.out.println("==================================================");
        System.out.println("1. Booking Kamera");
        System.out.println("2. Data Penyewaan & Stok");
        System.out.println("3. Batal Penyewaan");
        System.out.println("4. Update Penyewaan");
        System.out.println("5. Keluar");
    }

    private void tampilkanKatalogKamera() {
        System.out.println("\n=== KATALOG KAMERA TERSEDIA ===");
        for (Kamera k : service.getDaftarKamera()) {
            System.out.println(k);
        }
    }

    private void tampilkanSemuaSewa() {
        System.out.println("\n=== DAFTAR TRANSAKSI PENYEWAAN ===");
        List<Penyewaan> daftarSewa = service.getDaftarSewa(); // satu kali query ke database
        if (daftarSewa.isEmpty()) {
            System.out.println("Belum ada data penyewaan.");
            return;
        }
        for (Penyewaan p : daftarSewa) {
            System.out.println("Kode Sewa   : " + p.getKodeSewa());
            System.out.println("ID Cust     : " + p.getIdCust());
            System.out.println("Nama Cust   : " + p.getNamaCust());
            System.out.println("No. Telp    : " + p.getNoTelp());
            System.out.println("Kamera      : " + p.getKamera().getNamaKamera());
            System.out.println("Jenis Sewa  : " + p.getTipeSewa().getLabel());
            System.out.println("Durasi      : " + p.getDeskripsiDurasi());
            System.out.println("Total Biaya : Rp" + p.hitungTotalBiaya());
            System.out.println("----------------------------------------");
        }
    }

    // Menu
    private void menuBooking() {
        System.out.println("\n=== INPUT TRANSAKSI BARU ===");
        String kodeSewa = inputTeks("Kode Sewa (S001): ");
        String idCust = inputTeks("ID Customer (C02): ");
        String namaCust = inputTeks("Nama Customer: ");
        String noTelp = inputTelepon("No. Telepon: ");

        tampilkanKatalogKamera();
        int idKamera = inputAngka("Pilih ID Kamera: ");

        TipeSewa tipe = inputTipeSewa();
        int durasi = inputDurasi(tipe);

        try {
            Penyewaan sewa = service.tambahPenyewaan(kodeSewa, idCust, namaCust, noTelp, idKamera, tipe, durasi);
            System.out.println(">> Sukses << Transaksi sewa berhasil ditambahkan!");
            tampilkanStruk(sewa);
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal Input: " + e.getMessage());
        }
    }

    private void menuBatal() {
        System.out.println("\n=== BATAL PENYEWAAN ===");
        String kodeBatal = inputTeks("Masukkan Kode Sewa yang DIBATALKAN (S001): ");
        if (service.batalPenyewaan(kodeBatal)) {
            System.out.println(">> Sukses << Penyewaan dengan kode " + kodeBatal + " berhasil dibatalkan.");
        } else {
            System.out.println("Error!! Kode sewa " + kodeBatal + " tidak ditemukan!");
        }
    }

    private void menuUpdate() {
        System.out.println("\n=== PERBARUI DATA PENYEWAAN ===");
        String kodeSewa = inputTeks("Masukkan Kode Sewa yang ingin diUPDATE (S001): ");
        if (service.cariSewa(kodeSewa) == null) {
            System.out.println("Error!! Kode sewa tidak ditemukan!");
            return;
        }

        System.out.println("\nPilih Bagian yang Ingin DiUPDATE: ");
        System.out.println("1. ID Customer");
        System.out.println("2. Nama Customer");
        System.out.println("3. No. Telepon");
        System.out.println("4. Kamera");
        System.out.println("5. Durasi");
        System.out.println("6. Kembali");
        int pilihan = inputAngka("Pilih menu yang ingin di UPDATE: ");

        boolean berhasil;
        String bagian;
        try {
            switch (pilihan) {
                case 1:
                    bagian = "ID Customer";
                    berhasil = service.updateIdCustomer(kodeSewa, inputTeks("ID Customer Baru (C02): "));
                    break;
                case 2:
                    bagian = "Nama Customer";
                    berhasil = service.updateNamaCustomer(kodeSewa, inputTeks("Nama Customer Baru: "));
                    break;
                case 3:
                    bagian = "No. Telepon";
                    berhasil = service.updateNoTelepon(kodeSewa, inputTelepon("No. Telepon Baru: "));
                    break;
                case 4:
                    bagian = "Kamera";
                    tampilkanKatalogKamera();
                    berhasil = service.updateKamera(kodeSewa, inputAngka("Pilih ID Kamera Baru: "));
                    if (!berhasil) {
                        System.out.println("Error!! ID Kamera baru tidak ditemukan!");
                        return;
                    }
                    break;
                case 5:
                    bagian = "Durasi";
                    TipeSewa tipe = inputTipeSewa();
                    berhasil = service.updateDurasi(kodeSewa, tipe, inputDurasi(tipe));
                    break;
                case 6:
                    System.out.println("Kembali ke menu utama.");
                    return;
                default:
                    System.out.println("Error!! Pilihan update tidak valid!");
                    return;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Gagal Update: " + e.getMessage());
            return;
        }

        if (berhasil) {
            System.out.println(">> Sukses << " + bagian + " berhasil diupdate!");
        } else {
            System.out.println("Error!! Kode sewa tidak ditemukan!");
        }
    }

    // User gabole isi kosong 
        private String inputTeks(String pesan) {
        while (true) {
            System.out.print(pesan);
            String teks = scanner.nextLine().trim();
            if (!teks.isEmpty()) {
                return teks;
            }
            System.out.println("Error! Input tidak boleh kosong!");
        }
    }

    private String inputTelepon(String pesan) {
        while (true) {
            String telp = inputTeks(pesan);
            if (telp.matches("\\d+")) {
                return telp;
            }
            System.out.println("Error! Nomor telepon harus berupa angka!");
        }
    }

    private int inputAngka(String pesan) {
        while (true) {
            String teks = inputTeks(pesan);
            try {
                return Integer.parseInt(teks);
            } catch (NumberFormatException e) {
                System.out.println("Error! Input harus berupa angka!");
            }
        }
    }

    private TipeSewa inputTipeSewa() {
        System.out.println("\nPilih jenis sewa:");
        System.out.println("1. Per 3 Jam");
        System.out.println("2. Harian");
        while (true) {
            int pilihan = inputAngka("Pilihan (1-2): ");
            if (pilihan == 1) {
                return TipeSewa.per3jam;
            } else if (pilihan == 2) {
                return TipeSewa.Harian;
            }
            System.out.println("Error! Pilih 1 atau 2!");
        }
    }

    private int inputDurasi(TipeSewa tipe) {
        if (tipe == TipeSewa.per3jam) {
            System.out.println("Paket: 1 = 3 jam, 2 = 6 jam");
            while (true) {
                int paket = inputAngka("Pilih paket (1-2): ");
                if (paket == 1 || paket == 2) {
                    return paket;
                }
                System.out.println("Error! Pilih 1 atau 2!");
            }
        }
        while (true) {
            int hari = inputAngka("Jumlah hari: ");
            if (hari > 0) {
                return hari;
            }
            System.out.println("Error! Minimal 1 hari!");
        }
    }
    private void tampilkanStruk(Penyewaan p) {
    System.out.println("\n==========================================");
    System.out.println("            STRUK PENYEWAAN KAMERA        ");
    System.out.println("==========================================");
    System.out.println("Kode Sewa   : " + p.getKodeSewa());
    System.out.println("ID Cust     : " + p.getIdCust());
    System.out.println("Nama Cust   : " + p.getNamaCust());
    System.out.println("No. Telp    : " + p.getNoTelp());
    System.out.println("------------------------------------------");
    System.out.println("Kamera      : " + p.getKamera().getNamaKamera());
    System.out.println("Jenis       : " + p.getKamera().getJenisKamera());
    System.out.println("Jenis Sewa  : " + p.getTipeSewa().getLabel());
    System.out.println("Durasi      : " + p.getDeskripsiDurasi());
    System.out.println("------------------------------------------");
    System.out.println("TOTAL BIAYA : Rp" + p.hitungTotalBiaya());
    System.out.println("==========================================");
    System.out.println("      Terima kasih telah menyewa!         ");
    System.out.println("==========================================\n");
}
}