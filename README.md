# Sistem Penyewaan Kamera 📸
### Nama: Muhammad Davi Aditya Pratama
### NIM: 2509116070

## Deskripsi Singkat Program 📝
Program ini merupakan sistem penyewaan kamera berbasis bahasa java yang dibuat untuk mempermudah proses pengelolaan pada Penyewaan Kamera. 
Program ini mengelola transaksi penyewaan kamera, katalog stok kamera, serta data pelanggan. Program ini ditulis dengan Java dan disusun menggunakan pola MVC 
(Model-View-Controller). Kode dipisah ke dalam beberapa package agar data, logika bisnis, dan tampilan tidak bercampur.

<img width="340" height="793" alt="Screenshot 2026-10-15 182607" src="https://github.com/user-attachments/assets/1949d949-4d8f-4ce9-b65c-7244e266e007" />  

1. Package Controller
   Berisi logika bisnis dan pemrosesan data sistem.    
   Service.java	Mengelola seluruh operasi manipulasi data (CRUD) di dalam ArrayList: menyimpan daftar katalog kamera, menampilkan transaksi,
   menambah transaksi baru, membatalkan transaksi, serta memperbarui (update) data penyewaan.

2. Package dao  
   - CustomerDao.java: Kelas Data Access Object (DAO) yang menangani komunikasi dengan database untuk mengelola data pelanggan (Customer),
     termasuk proses pencarian, penyimpanan, dan pengambilan data pelanggan yang terdaftar dalam sistem penyewaan.
   - KameraDao.java: Kelas Data Access Object (DAO) yang bertindak sebagai perantara komunikasi antara aplikasi dan database untuk entitas kamera.
     Kelas ini menyediakan method seperti findAll() untuk mengambil seluruh daftar data kamera dan findById()
     untuk mencari data kamera berdasarkan ID secara spesifik menggunakan EntityManager, serta menangani validasi koneksi dan exception database.
   - PenyewaanDao.java: Kelas Data Access Object (DAO) yang bertanggung jawab atas pengelolaan data transaksi penyewaan. Kelas ini menghubungkan
     entitas transaksi penyewaan ke database untuk mencatat data penyewaan, relasi pelanggan, objek kamera yang disewa, serta durasi dan tipe sewa.

3. Package konfig  
   - DatabaseException.java: Kelas custom exception turunan dari RuntimeException yang digunakan untuk menangani dan membungkus pesan kesalahan
     terkait kegagalan operasi database atau koneksi.
   - Koneksi.java: Kelas utilitas untuk mengatur dan menyediakan objek EntityManager (berdasarkan konfigurasi JPA/Hibernate di persistence.xml)
     guna menghubungkan aplikasi dengan database MySQL.
   - TesKoneksi.java: Kelas pengujian (testing) untuk memeriksa apakah koneksi ke database berhasil terhubung dengan melakukan inisialisasi
     EntityManager dan menjalankan kueri uji coba ke tabel kamera.

4. Package main  
   Berisi kelas utama sebagai titik masuk (entry point) program.  
   Main.java	Method main() yang menjalankan program. Menjalankan menu dalam perulangan while hingga pengguna memilih keluar.
   
5. Package model   
   Berisi entitas data, kontrak interface, enum, dan logika dasar objek yang digunakan dalam sistem:
   - Kamera.java:	Superclass utama yang mendefinisikan atribut umum kamera (ID, nama, dan harga sewa) serta method penghitungan biaya
   - KameraDslr.java:	Subclass turunan Kamera untuk kamera jenis DSLR, dengan atribut spesifik tipe lensa.
   - KameraMirorrless.java:	Subclass turunan Kamera untuk kamera mirrorless, dengan atribut spesifik ketersediaan fitur 4K.
   - DigiCam.java	Subclass: turunan Kamera untuk kamera digital (digicam), dengan atribut spesifik pilihan efek retro.
   - Penyewaan.java:	Class entitas yang menyimpan data transaksi penyewaan: kode sewa, data pelanggan, objek kamera yang disewa, dan durasi sewa.
   - Disewakan.java: adalah interface yang menentukan method-method yang harus dimiliki oleh objek yang dapat disewakan, sehingga memastikan bahwa proses
     penyewaan dapat dijalankan secara konsisten pada semua jenis kamera.
   - TipeSewa.java: Enumerasi yang mendefinisikan tipe sewa yang tersedia.
     
6. Package view  
   Berisi tampilan antarmuka pengguna berbasis teks.  
   View.java Menampilkan menu interaktif, menerima inputan pengguna menggunakan Scanner, serta memanggil fungsi-fungsi dari Service.

7. Package resources/META-INF/persistence.xml  
   File konfigurasi XML yang mendefinisikan persistence-unit bernama penyewaankameraPU dengan menggunakan penyedia ORM Hibernate (HibernatePersistenceProvider).
   File ini mendaftarkan seluruh kelas model entitas (Customer, Kamera, KameraDslr, KameraMirorrless, DigiCam, dan Penyewaan) agar dapat dipetakan ke database,
   serta mengatur properti koneksi JDBC ke MySQL (localhost:3306/penyewaankamera), dialect database, hingga pengaturan perilaku Hibernate seperti hbm2ddl.auto.
   
## Alur Program ⏳  
Dokumentasi dibawah merupakan output yang akan tertampil ketika file dijalankan (Run File).  Tampilan dibawah merupakan tampilan awal dari Sistem Penyewaan Kamera. Menu yang ada pada sistem ini antara lain: 
  1. Booking Kamera
  2. Data Penyewaan & Stok
  3. Batal Penyewaan
  4. Update Penyewaan
  5. Keluar  
<img width="465" height="375" alt="image" src="https://github.com/user-attachments/assets/4ccc64d3-00ff-466d-9562-154f5c260e16" />

  1. Booking Kamera  
     <img width="807" height="443" alt="image" src="https://github.com/user-attachments/assets/073a5853-696b-4cc0-a677-4c5691c9180d" />  
     Pada pilihan menu ini, akan dilakukan inputasi mengenai booking kamera, mulai dari data customer hingga lama penyewaan.
     - Kode Sewa: Identitas unik untuk transaksi dengan format (S004).
     - ID Customer: Identitas unik pelanggan dengan format (C04).
     - Nama Customer: Identitas nama penyewa dengan contoh (Hanif)
     - No. Telepon: Nomor kontak yang bisa dihubungi.
     - Pilih id kamera: Pemilihan unit kamera yang ingin di sewa, contoh (101)
     - Pilih jenis sewa: Pemilihan jenis sewa, per 3 jam atau per hari
     - Pilih paket: Pemilihan paket, jika per 3 jam cukup input 1 untuk 3 jam dan 2 untuk 6 jam. Jika per hari cukup input angka harinya.
      Setelah itu semua diinput, akan muncul pesan sukses transaksi telah ditambahkan disertai dengan struk penyewaannya.  
      <img width="361" height="325" alt="image" src="https://github.com/user-attachments/assets/6edb348e-e556-43fd-9dfa-7e8b08225331" />  
  
  2. Data Penyewaan & Stok  
     <img width="350" height="698" alt="image" src="https://github.com/user-attachments/assets/61a56eb4-bbdc-431e-b40c-00540c6e5b55" />  
     Pilihan menu ini berfungsi untuk menampilkan seluruh riwayat transaksi penyewaan yang tersimpan di dalam sistem (ArrayList).
     Informasi yang ditampilkan seperti nama customer, kamera yang disewa dan tipe sewa yang dipilih serta total biaya penyewaan.
     
  3. Batal Penyewaan  
     <img width="496" height="220" alt="image" src="https://github.com/user-attachments/assets/0f24cb3d-e510-4925-937c-4ae159164bef" />  
     Pilihan menu ini berfungsi untuk pembatalan sewa. Pengguna cukup memasukkan kode sewa yang ingin dibatalkan penyewaannya,
     lalu sistem akan mencari data tersebut dan menghapusnya.   
     <img width="336" height="527" alt="image" src="https://github.com/user-attachments/assets/0b2924a7-4a6b-439b-8e54-8841f2d01f27" />  
     Terlihat pada dokumentasi diatas bahwa penghapusan data sewa dengan kode S003 dengan nama Hniaf sudah tidak ada dalam data penyewaan.  
  
  4. Update Penyewaan    
     <img width="867" height="427" alt="image" src="https://github.com/user-attachments/assets/5e7a2235-23ea-4936-82ab-107f05a20438" />  
     Pilihan menu ini berfungsi untuk memperbarui data penyewaan, di mana pengguna memasukkan kode sewa, memilih bagian data yang ingin diubah (seperti ID, nama, nomor telepon, kamera, atau durasi),
     lalu memasukkan data baru hingga sistem berhasil mengupdate transaksi tersebut.  
     <img width="357" height="191" alt="image" src="https://github.com/user-attachments/assets/606d6bc8-9363-4e63-93b8-6d07c1f63a06" />  
     Terlihat pada dokumentasi diatas, untuk data dengan kode sewa S004 dibagian kamera udah terganti sesuai pembaruan yang dimasukkan sebelumnya.
     
  5. Keluar  
     <img width="620" height="137" alt="image" src="https://github.com/user-attachments/assets/7c66cdeb-dc16-4d9a-8f33-b32b858658c1" />  
      Pilihan menu ini berfungsi untuk mengakhiri program, di mana pengguna memilih opsi nomor 5 lalu sistem akan menampilkan pesan penutup dan menghentikan jalannya aplikasi.

## Nilai Tambah ➕  
  Sistem ini menerapkan konsep Object-Relational Mapping (ORM) menggunakan Jakarta Persistence API (JPA) dengan engine Hibernate, serta menggunakan arsitektur Data Access Object (DAO) 
  untuk memisahkan logika akses data dari logika antarmuka pengguna.  
  - Pemetaan Entitas (Model): Kelas-kelas Java seperti Customer, Kamera, dan turunannya dipetakan langsung sebagai tabel database melalui file konfigurasi persistence.xml.
  - Manipulasi Data Tanpa SQL Rumit: Di dalam kelas DAO (CustomerDao, KameraDao, dll.), operasi CRUD dilakukan menggunakan method objek bawaan seperti em.find(), em.persist(), dan em.merge()
  - atau query berbasis objek (JPQL), sehingga Anda tidak perlu menulis sintaks SQL mentah secara manual.

  Bukti penggunaan nilai tambah: 
  1. persistence.xml
  ```java
       <?xml version="1.0" encoding="UTF-8"?>
  <!--
  Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
  Click nbfs://nbhost/SystemFileSystem/Templates/XML/XMLDocument.xml to edit this template
  -->
  <persistence xmlns="https://jakarta.ee/xml/ns/persistence" version="3.0">
      <persistence-unit name="penyewaankameraPU">
          <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
  
          <!-- Daftar class model yang jadi tabel -->
          <class>model.Customer</class>
          <class>model.Kamera</class>
          <class>model.KameraDslr</class>
          <class>model.KameraMirorrless</class>
          <class>model.DigiCam</class>
          <class>model.Penyewaan</class>
  
          <properties>
              <!-- Konfigurasi Database JDBC -->
              <property name="jakarta.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
              <property name="jakarta.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/penyewaankamera"/>
              <property name="jakarta.persistence.jdbc.user" value="root"/>
              <property name="jakarta.persistence.jdbc.password" value=""/>
  
              <!-- Hibernate Settings -->
              <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect"/>
              <!-- update: otomatis bikin tabel jika belum ada di database -->
              <property name="hibernate.hbm2ddl.auto" value="none"/>
              <!-- show_sql: menampilkan SQL yang di-generate ORM di terminal -->
              <property name="hibernate.show_sql" value="false"/>
              <property name="hibernate.format_sql" value="false"/>
          </properties>
      </persistence-unit>
  </persistence>
       
       
</persistence>
```
2. Anotasi JPA di model
   Fungsi utama dari anotasi JPA (Java Persistence API) di dalam kelas model (entitas) adalah untuk menghubungkan (memetakan) antara struktur objek Java dengan struktur tabel di dalam database relasional (MySQL)
   secara deklaratif tanpa perlu menulis perintah SQL secara manual. Berikut contoh penggunaannya:
   
   A. Customer
   ```java
   @Entity
   @Table(name = "customer")
   ```
   B. Kamera
   ```java
   @Entity
   @Table(name = "kamera")
   @Inheritance(strategy = InheritanceType.SINGLE_TABLE)
   ```
   C. Digicam
   ```java
   @Entity
   @DiscriminatorValue("DigiCam")
   ```
   D. KameraMirrorless
   ```java
   @Entity
   @DiscriminatorValue("Mirrorless")
   ```
   E. KameraDslr
   ```java
   @Entity
   @DiscriminatorValue("DSLR")
   ```
   F. Penyewaan
   ```java
   @Entity
   @Table(name = "penyewaan")
   ```
4. Implementasi pada Dao  
   Lapisan DAO memanfaatkan EntityManager untuk mengeksekusi berbagai operasi manipulasi data ke database secara objek (object-oriented). Dengan menggunakan method bawaan JPA (seperti em.find(), em.merge())
   maupun kueri berbasis objek (JPQL), proses pengelolaan data seperti pencarian, pembaruan, hingga pengambilan daftar data dapat dilakukan secara terstruktur lengkap dengan manajemen transaksi
   dan penanganan kesalahan (exception handling). Berikut adalah contoh penerapannya pada kode program:
   
   A. Contoh pada CustomerDao.java
   ```java
   if (em.find(Customer.class, c.getIdCust()) == null) {
                return false;
            }
            em.getTransaction().begin();
            em.merge(c);
            em.getTransaction().commit();
            return true;
   ```
   B. Contoh pada KameraDao.java
   ```java
   try (EntityManager em = ambilEntityManager()) {
            return em.createQuery("from Kamera order by idKamera", Kamera.class).getResultList();
        } catch (PersistenceException e) {
            throw new DatabaseException("Gagal membaca data kamera: " + e.getMessage(), e);
        }
   ```
