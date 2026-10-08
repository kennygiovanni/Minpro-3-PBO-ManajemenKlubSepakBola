# Sistem Manajemen Klub Sepak Bola

## Deskripsi Singkat Program

Program ini adalah aplikasi berbasis console (command line) yang dibuat menggunakan bahasa pemrograman Java dengan penerapan konsep Pemrograman Berorientasi Objek (PBO). Program ini berfungsi sebagai sistem manajemen sederhana untuk klub sepak bola, yang dapat digunakan oleh seorang manajer klub untuk mengelola data pemain, data pelatih, serta riwayat pertandingan klub.

Program ini membantu manajer klub dalam:
- **Mencatat skuad klub**: data pemain dan pelatih terorganisir, termasuk pemain mana yang sedang cedera.
- **Memantau hasil pertandingan**: riwayat pertandingan tersimpan beserta hasilnya (Menang/Kalah/Seri) yang dihitung otomatis dari skor.

Program ini dibangun dengan menerapkan beberapa konsep dasar PBO secara menyeluruh, di antaranya:
- Class dan Object
- Constructor (termasuk constructor overloading)
- Encapsulation (access modifier `private` dan `public`, getter & setter, keyword `final`)
- Inheritance (pewarisan)
- Abstraction (abstract class dan abstract method)
- Interface
- Polymorphism (method overriding dan overloading)
- ArrayList untuk menyimpan kumpulan data
- Struktur MVC (Model-View-Controller) untuk pengorganisasian package
- Validasi input pada setiap data yang dimasukkan pengguna

> Saat program dijalankan, data awal dimuat otomatis: **7 pemain, 3 pelatih, dan 4 pertandingan**.

## Struktur Package

```
src/
├── com/mycompany/miniproject/
│   └── Main.java
├── controller/
│   └── ManajemenKlub.java
├── model/
│   ├── Informasi.java
│   ├── AnggotaKlub.java
│   ├── Pemain.java
│   ├── Pelatih.java
│   └── Pertandingan.java
└── view/
    └── TampilanView.java
```

| Package | Isi | Peran |
|---|---|---|
| `model` | `Informasi`, `AnggotaKlub`, `Pemain`, `Pelatih`, `Pertandingan` | Menyimpan data dan aturan validasi |
| `view` | `TampilanView` | Menampilkan data dan menu ke layar |
| `controller` | `ManajemenKlub` | Mengelola logika CRUD dan menghubungkan Model dengan View |
| `com.mycompany.miniproject` | `Main` | Entry point program, menangani input pengguna |

## Penjelasan Alur Program

1. **Program dimulai** dengan membuat satu objek `ManajemenKlub` dan satu `TampilanView`. Konstruktor `ManajemenKlub` langsung mengisi data awal (7 pemain, 3 pelatih, 4 pertandingan), sehingga fitur dapat langsung dicoba tanpa harus input manual dari awal.

2. **Menu utama ditampilkan** secara berulang menggunakan perulangan `do-while`, sehingga program akan terus berjalan selama pengguna belum memilih menu keluar. Menu yang tersedia adalah:
   - Menu Pemain
   - Menu Pelatih
   - Menu Pertandingan
   - Keluar

3. **Pengguna memilih menu** dengan memasukkan angka melalui keyboard (`Scanner`). Program akan memvalidasi input agar hanya menerima angka; jika input bukan angka, program akan meminta pengguna memasukkan ulang.

4. **Setiap menu memiliki submenu** dengan perulangan sendiri yang berakhir saat pengguna memilih opsi "Kembali ke Menu Utama":
   - **Menu Pemain**: Tambah Pemain, Lihat Semua Pemain, Hapus Pemain, Update Status Pemain, Kembali.
   - **Menu Pelatih**: Tambah Pelatih, Lihat Semua Pelatih, Kembali.
   - **Menu Pertandingan**: Tambah Pertandingan, Lihat Riwayat Pertandingan, Kembali.

5. **Program memproses pilihan** menggunakan struktur percabangan `switch-case`, sesuai dengan menu yang dipilih:
   - Jika memilih **Tambah Pemain/Pelatih/Pertandingan**, program akan meminta pengguna memasukkan data satu per satu. `Main` lalu membuat objek (`Pemain`, `Pelatih`, atau `Pertandingan`) dan menyerahkannya ke `Controller`, yang menyimpannya ke dalam `ArrayList`.
   - Jika memilih **Lihat Data**, `Controller` meneruskan list ke `View`, yang menampilkan seluruh data menggunakan perulangan (`for-each`).
   - Jika memilih **Hapus/Update Pemain**, `Controller` akan mencari data berdasarkan nama yang dimasukkan (tidak membedakan huruf besar/kecil), lalu menghapus atau memperbarui data tersebut. Program menampilkan pesan yang sesuai tergantung apakah data ditemukan atau tidak.
   - Setiap data yang dimasukkan akan melalui proses **validasi** (misalnya posisi pemain harus sesuai daftar posisi yang valid, skor tidak boleh bernilai negatif, dan status kesehatan harus sesuai pilihan yang tersedia).

6. **Setelah satu proses menu selesai dijalankan**, program akan kembali menampilkan submenu yang sama (karena berada di dalam perulangan), sehingga pengguna dapat memilih aksi lain tanpa perlu menjalankan ulang program.

7. **Program akan berhenti** hanya ketika pengguna memilih menu **Keluar (angka 4)** pada menu utama, yang akan menghentikan perulangan `do-while`.

## Penerapan Encapsulation

Encapsulation diterapkan pada seluruh class di package `model` (`AnggotaKlub`, `Pemain`, `Pelatih`, `Pertandingan`), dengan cara:

- Seluruh atribut dideklarasikan dengan access modifier **`private`**, sehingga tidak bisa diakses secara langsung dari luar class.
- Akses terhadap atribut tersebut hanya bisa dilakukan melalui method **getter** (untuk membaca nilai) dan **setter** (untuk mengubah nilai). Setter yang hanya dipakai konstruktor dibuat `private`, sedangkan yang dipanggil dari luar (misalnya `setStatusKesehatan()` saat update status) dibuat `public`.
- Setiap method setter dilengkapi dengan **validasi**, sehingga data yang masuk ke dalam atribut selalu terjamin valid. Aturannya:

| Class | Atribut | Aturan | Jika tidak valid |
|---|---|---|---|
| `Pemain` | `posisi` | GK, CB, LB, RB, CDM, CM, CAM, LW, RW, ST | diset ke `ST` |
| `Pemain` | `nomorPunggung` | 1 sampai 99 | diset ke `99` |
| `Pemain` | `usia` | tidak boleh negatif | diset ke `0` |
| `Pemain` | `statusKesehatan` | `Tersedia` atau `Cedera` | diset ke `Tersedia` |
| `Pelatih` | `pengalaman` | 0 sampai 50 tahun | diset ke `0` |
| `Pertandingan` | `skorKlub`, `skorLawan` | tidak boleh negatif | diset ke `0` |
| `Pertandingan` | `lokasi` | `Kandang` atau `Tandang` | diset ke `Kandang` |

- Keyword **`final`** dipakai untuk memperkuat encapsulation: atribut yang hanya diisi sekali di konstruktor (`AnggotaKlub.nama`, `Pelatih.spesialisasi`, `Pertandingan.lawan`, `tanggal`, `kompetisi`) dideklarasikan `private final`, dan class `Pemain`, `Pelatih`, `Pertandingan` dideklarasikan `final class` karena tidak ada class yang mewarisinya.
- Method `private getHasil()` pada `Pertandingan` juga bagian dari encapsulation, karena hasil pertandingan hanya dihitung di dalam class itu sendiri.

Dengan encapsulation ini, integritas data selalu terjaga karena tidak ada cara untuk mengisi atribut dengan nilai sembarang dari luar class.

## Penerapan Inheritance

Inheritance diterapkan antara class `AnggotaKlub` sebagai **superclass (parent class)**, dengan `Pemain` dan `Pelatih` sebagai **subclass (child class)**.

```
AnggotaKlub (superclass, abstract)
├── atribut: nama
│
├── Pemain extends AnggotaKlub
│   └── tambahan atribut: posisi, nomorPunggung, usia, statusKesehatan
│
└── Pelatih extends AnggotaKlub
    └── tambahan atribut: spesialisasi, pengalaman
```

Alasan penerapan inheritance ini adalah karena `Pemain` dan `Pelatih` sama-sama merupakan **anggota klub** yang memiliki kesamaan atribut, yaitu `nama`. Daripada menulis atribut dan method `nama` secara berulang di kedua class, atribut tersebut cukup didefinisikan satu kali di `AnggotaKlub`, lalu diwariskan ke `Pemain` dan `Pelatih` menggunakan kata kunci `extends`.

Pada constructor masing-masing subclass, digunakan `super(nama)` untuk memanggil constructor dari `AnggotaKlub`. Pada method `getInfo()`, subclass memanggil `super.getInfo()` untuk memakai informasi dasar milik parent (peran dan nama), lalu menambahkan informasi khususnya sendiri sehingga tidak ada kode yang duplikat:

```java
public final class Pelatih extends AnggotaKlub {
    private final String spesialisasi;
    private int pengalaman;

    public Pelatih(String nama, String spesialisasi, int pengalaman) {
        super(nama);                       // memanggil constructor parent
        this.spesialisasi = spesialisasi;
        setPengalaman(pengalaman);
    }

    @Override
    public String getInfo() {
        return super.getInfo()             // memakai info dari parent
             + "\nSpesialisasi     : " + spesialisasi
             + "\nPengalaman       : " + pengalaman + " tahun";
    }
}
```

## Penerapan Abstraction

Abstraction diterapkan lewat **abstract class `AnggotaKlub`** yang memiliki **abstract method `getPeran()`**, yaitu method tanpa isi yang wajib diimplementasikan oleh setiap subclass:

```java
// di AnggotaKlub
public abstract String getPeran();

// di Pemain
@Override
public String getPeran() { return "Pemain"; }

// di Pelatih
@Override
public String getPeran() { return "Pelatih"; }
```

Manfaat penerapannya:
- `AnggotaKlub` **tidak bisa diinstansiasi** (`new AnggotaKlub(...)` akan error), karena "anggota klub" hanyalah konsep umum. Yang nyata adalah pemain atau pelatih.
- Jika ada subclass baru yang lupa mengimplementasikan `getPeran()`, compiler langsung menolaknya.
- `getInfo()` di parent memanggil `getPeran()` tanpa tahu isinya. Detail diserahkan ke subclass.

## Penerapan Interface

Interface yang digunakan adalah **`Informasi`**, yang berisi satu kontrak:

```java
public interface Informasi {
    String getInfo();
}
```

- `AnggotaKlub` dan `Pertandingan` sama-sama `implements Informasi`. Kewajiban memiliki `getInfo()` otomatis diturunkan ke `Pemain` dan `Pelatih`.
- `Pertandingan` bukan anggota klub sehingga tidak bisa mewarisi `AnggotaKlub`. Lewat interface ini, ia tetap bisa diperlakukan sama dengan `Pemain` dan `Pelatih` saat ditampilkan.
- `TampilanView` hanya mengenal `Informasi` dan tidak perlu meng-import `Pemain`, `Pelatih`, atau `Pertandingan`. Jika nanti ada data baru yang mengimplementasikan `Informasi`, data tersebut langsung bisa ditampilkan tanpa mengubah `TampilanView`.

## Penerapan Struktur MVC

Program ini menerapkan struktur **Model-View-Controller (MVC)** dengan pembagian tanggung jawab sebagai berikut:

- **Model** (`model.Informasi`, `model.AnggotaKlub`, `model.Pemain`, `model.Pelatih`, `model.Pertandingan`)
  Berfungsi untuk mengelola data, yang meliputi atribut, constructor, getter, setter, serta validasi data. Model berfokus pada pengelolaan dan representasi data dalam program.

- **View** (`view.TampilanView`)
  Bertanggung jawab untuk mengatur tampilan yang ditampilkan kepada pengguna, seperti menu, pesan, serta daftar data pemain, pelatih, dan pertandingan. Bagian ini berfokus pada kebutuhan antarmuka dan tampilan program.

- **Controller** (`controller.ManajemenKlub`)
  Berfungsi sebagai penghubung antara Model dan View. Controller menyimpan dan mengelola data dalam bentuk ArrayList, menjalankan proses CRUD (Create, Read, Update, Delete), serta mengatur proses ketika data perlu ditampilkan melalui TampilanView.

- **Main** (`com.mycompany.miniproject.Main`)
  Berperan sebagai entry point program. Class ini menangani proses awal program dan membaca input dari pengguna menggunakan Scanner, kemudian meneruskan perintah yang diberikan kepada Controller.

Dengan struktur ini, perubahan pada satu lapisan (misalnya perubahan format tampilan di `View`) tidak akan memengaruhi lapisan lain (`Model` dan `Controller`), sehingga program menjadi lebih mudah dipelihara dan dikembangkan.

## Penerapan Polymorphism

Polymorphism diterapkan melalui **method overriding** pada method `getInfo()` dan **method overloading** pada constructor `Pertandingan`.

- Method `getInfo()` pertama kali didefinisikan di interface `Informasi`, lalu diimplementasikan di `AnggotaKlub` (informasi dasar berupa peran dan nama) dan `Pertandingan`.
- Method ini kemudian di-**override** (ditandai dengan anotasi `@Override`) di class `Pemain` dan `Pelatih`, masing-masing menambahkan informasi khusus miliknya sendiri (posisi, nomor punggung, usia, status kesehatan untuk `Pemain`; spesialisasi, pengalaman untuk `Pelatih`).

Pemanfaatan polymorphism ini terlihat jelas pada class `TampilanView`, yang cukup memanfaatkan satu method untuk menampilkan ketiga jenis daftar:

```java
public void tampilkanDaftar(String judul, List<? extends Informasi> daftar) {
    if (daftar.isEmpty()) {
        System.out.println("Belum ada data " + judul.toLowerCase());
        return;
    }
    System.out.println("=== DAFTAR " + judul.toUpperCase() + " ===");
    for (Informasi i : daftar) {
        tampilkanInfo(i);          // i.getInfo() dipilih saat program berjalan
    }
}
```

Method ini dipanggil untuk list `Pemain`, `Pelatih`, maupun `Pertandingan`. Meskipun ketiganya dilewatkan ke method yang sama persis, hasil keluarannya berbeda, karena Java secara otomatis menjalankan versi `getInfo()` sesuai dengan tipe objek yang sebenarnya pada saat program berjalan (*dynamic binding*). Inilah inti dari pemanfaatan polymorphism: satu method penerima, banyak bentuk perilaku, tergantung objek yang dilewatkan kepadanya.

Selain itu, class `Pertandingan` memiliki dua constructor dengan parameter berbeda (**overloading**). Versi tanpa parameter kompetisi otomatis mengisi "Pertandingan Persahabatan":

```java
public Pertandingan(String lawan, String tanggal,
                    int skorKlub, int skorLawan, String lokasi) {
    this(lawan, tanggal, "Pertandingan Persahabatan", skorKlub, skorLawan, lokasi);
}
```

## Cara Menjalankan Program

1. Pastikan struktur folder/package sudah sesuai:

```
model/Informasi.java
model/AnggotaKlub.java
model/Pemain.java
model/Pelatih.java
model/Pertandingan.java
view/TampilanView.java
controller/ManajemenKlub.java
com/mycompany/miniproject/Main.java
```

2. Compile seluruh file dari dalam folder `src`:

```bash
javac model/*.java view/*.java controller/*.java com/mycompany/miniproject/Main.java
```

3. Jalankan program melalui class utama:

```bash
java com.mycompany.miniproject.Main
```

Atau jika memakai NetBeans/IDE lain, cukup jalankan (Run) file `Main.java`.

## Screenshots Program

Berikut adalah dokumentasi visual antarmuka dan keluaran program saat dijalankan di terminal/console:

### 1. Tampilan Menu Utama
> Menampilkan menu pilihan sistem manajemen klub saat pertama kali program dijalankan, dengan data awal (7 pemain, 3 pelatih, 4 pertandingan) sudah termuat.

![Tampilan Menu Utama](Screenshots/01-menu-utama.png](https://github.com/kennygiovanni/Minpro-3-PBO-ManajemenKlubSepakBola/blob/master/screenshots/Screenshot%202026-10-08%20223822.png)

---

### 2. Fitur Manajemen Pemain
#### A. Menu Pemain
> Submenu yang muncul setelah memilih angka 1, berisi pilihan tambah, lihat, hapus, update status, dan kembali.

![Menu Pemain](Screenshots/02-menu-pemain.png)

#### B. Tambah Data Pemain (Input & Validasi)
> Proses menginput data pemain baru.

![Tambah Pemain](Screenshots/03-tambah-pemain.png)

#### C. Lihat Daftar Pemain
> Output daftar seluruh pemain yang tersimpan di sistem, memanfaatkan method `getInfo()` lewat `tampilkanDaftar()`.

![Lihat Daftar Pemain](Screenshots/04-lihat-pemain.png)

#### D. Hapus Pemain
> Proses penghapusan data pemain dari sistem berdasarkan nama yang diinputkan pengguna.

![Hapus Pemain](Screenshots/05-hapus-pemain.png)

#### E. Update Status Pemain
> Pembaruan status kesehatan pemain (Tersedia/Cedera) berdasarkan nama pemain yang dicari.

![Update Status Pemain](Screenshots/06-update-status-pemain.png)

---

### 3. Fitur Manajemen Pelatih
#### A. Menu Pelatih
> Submenu yang muncul setelah memilih angka 2, berisi pilihan tambah pelatih, lihat daftar pelatih, dan kembali.
 
![Menu Pelatih](Screenshots/07-menu-pelatih.png)
 
#### B. Tambah Pelatih
> Menginput data pelatih baru beserta spesialisasi dan pengalaman melatih.
 
![Tambah Pelatih](Screenshots/08-tambah-pelatih.png)
 
#### C. Lihat Daftar Pelatih
> Menampilkan daftar pelatih yang ada di dalam klub.
 
![Lihat Daftar Pelatih](Screenshots/09-lihat-pelatih.png)
 
---

### 4. Fitur Manajemen Pertandingan
#### A. Menu Pertandingan
> Submenu yang muncul setelah memilih angka 3, berisi pilihan tambah pertandingan, lihat riwayat pertandingan, dan kembali.
 
![Menu Pertandingan](Screenshots/10-menu-pertandingan.png)
 
#### B. Tambah Pertandingan
> Mengisi riwayat pertandingan baru meliputi tim lawan, tanggal, kompetisi, skor, dan lokasi pertandingan.
 
![Tambah Pertandingan](Screenshots/11-tambah-pertandingan.png)
 
#### C. Lihat Riwayat Pertandingan
> Menampilkan riwayat seluruh pertandingan yang telah ditambahkan, lengkap dengan hasil (Menang/Kalah/Seri) yang dihitung otomatis dari skor.
 
![Lihat Riwayat Pertandingan](Screenshots/12-lihat-pertandingan.png)
 
---

### 5. Keluar dari Program
> Penutupan program ketika pengguna memilih menu angka 4 pada menu utama.

![Keluar Program](Screenshots/13-keluar.png)
