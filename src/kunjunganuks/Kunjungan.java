/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kunjunganuks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

import static kunjunganuks.KunjunganUks.getCurrentDate;
import static kunjunganuks.KunjunganUks.getCurrentTime;
import static kunjunganuks.KunjunganUks.index;
import static kunjunganuks.KunjunganUks.isLogin;
import static kunjunganuks.Surat.isMenuSuratPulang;
import static kunjunganuks.Surat.isMenuSuratRujukan;
import static kunjunganuks.Surat.menuSurat;
import static kunjunganuks.KunjunganUks.kunjungan;
import static kunjunganuks.KunjunganUks.menuUtama;
import static kunjunganuks.KunjunganUks.scanner;

/**
 *
 * @author RIZAL
 */
public class Kunjungan {

    static boolean isMenuKunjungan = true;
    static boolean isMenuTambahKunjungan = true;
    static boolean isMenuEditKunjungan = false;
    static boolean isMenuSelesaiKunjungan = false;
    static boolean isMenuCariKunjungan = true;
    static boolean isMenuHapusKunjungan = false;
    static boolean rujukanStatus = true;
    static boolean pulangStatus = true;

    public static void menuKunjunganUks() {
        System.out.println("\n============================================");
        System.out.println("=         MENU - MENU KUNJUNGAN UKS        =");
        System.out.println("============================================");
        System.out.println("1. Lihat Daftar Kunjungan");
        System.out.println("2. Tambah Kunjungan");
        System.out.println("3. Edit Kunjungan");
        System.out.println("4. Selesai kunjungan");
        System.out.println("5. Hapus Kunjungan");
        System.out.println("6. Cari Kunjungan");
        System.out.println("7. Kembali");
        System.out.println("8. Logout");
        System.out.println("============================================");

        isMenuKunjungan = true;
        while (isMenuKunjungan) {
            try {
                System.out.print("Pilihan menu (1-8) : ");
                int pilihan = scanner.nextInt();

                switch (pilihan) {
                    case 1:
                        isMenuKunjungan = false;
                        lihatKunjungan();
                        break;
                    case 2:
                        isMenuKunjungan = false;
                        tambahKunjungan();
                        break;
                    case 3:
                        isMenuCariKunjungan = true;
                        isMenuEditKunjungan = true;
                        scanner.nextLine();
                        cariKunjungan();
                        break;
                    case 4:
                        isMenuCariKunjungan = true;
                        isMenuSelesaiKunjungan = true;
                        scanner.nextLine();
                        cariKunjungan();
                        break;
                    case 5:
                        isMenuCariKunjungan = true;
                        isMenuHapusKunjungan = true;
                        scanner.nextLine();
                        cariKunjungan();
                        break;
                    case 6:
                        isMenuCariKunjungan = true;
                        isMenuHapusKunjungan = false;
                        isMenuSelesaiKunjungan = false;
                        isMenuEditKunjungan = false;
                        scanner.nextLine();
                        cariKunjungan();
                        break;
                    case 7:
                        menuUtama();
                        break;
                    case 8:
                        isLogin = false;
                        System.out.println("============================================");
                        System.out.println("Logout berhasil!");
                        System.out.println("============================================");
                        scanner.nextLine();
                        index();
                        break;
                    default:
                        System.out.println("============================================");
                        System.out.println("Pilihan tidak tersedia, masukkan angka yang valid");
                        System.out.println("============================================");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Pilihan tidak valid, masukkan pilihan yang valid");
                System.out.println("============================================");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
    }

    public static void lihatKunjungan() {
        System.out.println("\n============================================");
        System.out.println("=            DAFTAR KUNJUNGAN UKS          =");
        System.out.println("============================================");

        if (kunjungan.size() == 0) {
            System.out.println("Tidak ada data kunjungan");
            System.out.println("============================================");
        } else {
            for (int i = 0; i <= kunjungan.size() - 1; i++) {
                List<String> data = kunjungan.get(i);

                System.out.println("Data Kunjungan ke-" + (i + 1));
                System.out.println("ID               : " + data.get(0));
                System.out.println("Nama             : " + data.get(1));
                System.out.println("Kelas            : " + data.get(2));
                System.out.println("Keluhan          : " + data.get(3));
                System.out.println("Tindakan         : " + data.get(4));
                System.out.println("Obat             : " + data.get(5));
                System.out.println("Jumlah digunakan : " + data.get(6));
                System.out.println("Tanggal Masuk    : " + data.get(7));
                System.out.println("Waktu Masuk      : " + data.get(8));
                System.out.println("Waktu Keluar     : " + data.get(9));
                System.out.println("Dirujuk          : " + data.get(10));
                System.out.println("Pulang           : " + data.get(11));

                System.out.println("============================================");
            }
        }
        menuKunjunganUks();
    }

    public static void tambahKunjungan() {
        String id;

        if (!kunjungan.isEmpty()) {
            int size = kunjungan.size() - 1;
            List<String> lastData = kunjungan.get(size);
            int incrementId = Integer.parseInt(lastData.get(0)) + 1;
            String newId = Integer.toString(incrementId);
            id = newId;
        } else {
            id = "1";
        }

        while (isMenuTambahKunjungan) {
            System.out.println("\n============================================");
            System.out.println("=              TAMBAH KUNJUNGAN            =");
            System.out.println("============================================");
            System.out.println("Id Kunjungan : " + id);

            scanner.nextLine();
            System.out.print("Nama Siswa : ");
            String nama = scanner.nextLine();

            System.out.print("Kelas : ");
            String kelas = scanner.nextLine();

            System.out.print("Keluhan : ");
            String keluhan = scanner.nextLine();

            System.out.print("Tindakan : ");
            String tindakan = scanner.nextLine();

            System.out.print("Obat : ");
            String obat = scanner.nextLine();

            System.out.print("Jumlah digunakan : ");
            String jumlah = scanner.nextLine();

            System.out.println("Tanggal Masuk : " + getCurrentDate());
            String tanggal_masuk = getCurrentDate();

            System.out.println("Waktu Masuk : " + getCurrentTime());
            String waktu_masuk = getCurrentTime();

            String waktu_keluar = "-";
            String isRujukan = "-";
            String isPulang = "-";

            kunjungan.add(Arrays.asList(id, nama, kelas, keluhan, tindakan, obat, jumlah, tanggal_masuk, waktu_masuk,
                    waktu_keluar, isRujukan, isPulang));
            System.out.println("============================================");
            System.out.println("Kunjungan baru berhasil ditambahkan");
            System.out.println("============================================");
            boolean tambahKunjunganLagi = true;
            while (tambahKunjunganLagi) {
                System.out.print("Ingin menambah kunjungan lagi? (ya/tidak) : ");
                String konfirmasiTambahLagi = scanner.next();

                switch (konfirmasiTambahLagi.toLowerCase()) {
                    case "ya":
                        tambahKunjunganLagi = false;
                        isMenuTambahKunjungan = true;
                        tambahKunjungan();
                        break;
                    case "tidak":
                        isMenuTambahKunjungan = true;
                        rujukanStatus = true;
                        tambahKunjunganLagi = false;
                        menuKunjunganUks();
                        break;
                    default:
                        System.out.println("============================================");
                        System.out.println("Input tidak valid, masukkan ya atau tidak");
                        System.out.println("============================================");
                        tambahKunjunganLagi = true;
                        break;
                }
            }
        }
    }

    public static void selesaiKunjungan(List<List<String>> dataKunjungan) {
        if (isMenuSelesaiKunjungan) {
            cariKunjungan();
        } else {
            System.out.println("\n============================================");
            System.out.println("=            KUNJUNGAN UKS SELESAI         =");
            System.out.println("============================================");

            while (!isMenuSelesaiKunjungan) {
                try {
                    boolean idCheck = true;
                    List<String> data = new ArrayList<>();
                    while (idCheck) {
                        System.out.print("Masukkan ID Kunjungan yang akan diselesaikan : ");
                        int idData = scanner.nextInt();
                        String id = Integer.toString(idData);

                        for (List<String> dataHasil : dataKunjungan) {
                            if (dataHasil.get(0).contains(id)) {
                                data = dataHasil;
                                idCheck = false;
                            } else {
                                System.out.println("============================================");
                                System.out.println("pilihan tidak tersedia, masukkan ID yang valid");
                                System.out.println("============================================");
                            }
                        }
                        scanner.nextLine();
                    }

                    System.out.println("============================================");
                    System.out.print("Apakah kunjungan atas nama " + data.get(1) + " sudah selesai? (ya/tidak) : ");
                    String selesai = scanner.nextLine();

                    switch (selesai.toLowerCase()) {
                        case "ya":
                            System.out.println("============================================");
                            data.set(9, getCurrentTime());

                            String isRujukan = "";
                            rujukanStatus = true;
                            while (rujukanStatus) {
                                System.out.print("Apakah pasien akan dirujuk? (ya/tidak) : ");
                                String rujuk = scanner.next();

                                switch (rujuk.toLowerCase()) {
                                    case "ya":
                                        isRujukan = "ya";
                                        rujukanStatus = false;
                                        break;
                                    case "tidak":
                                        isRujukan = "tidak";
                                        rujukanStatus = false;
                                        break;
                                    default:
                                        System.out.println("============================================");
                                        System.out.println("Masukkan pilihan yang valid, pilih ya atau tidak");
                                        System.out.println("============================================");
                                }
                            }

                            String isPulang = "";
                            pulangStatus = true;
                            while (pulangStatus) {
                                System.out.println("============================================");
                                System.out.print("Apakah pasien akan pulang? (ya/tidak) : ");
                                String pulang = scanner.next();

                                switch (pulang.toLowerCase()) {
                                    case "ya":
                                        isPulang = "ya";
                                        pulangStatus = false;
                                        break;
                                    case "tidak":
                                        isPulang = "tidak";
                                        pulangStatus = false;
                                        break;
                                    default:
                                        System.out.println("============================================");
                                        System.out.println("Masukkan pilihan yang valid, pilih ya atau tidak");
                                        System.out.println("============================================");
                                }
                            }

                            data.set(10, isRujukan);
                            data.set(11, isPulang);
                            System.out.println("============================================");
                            System.out.println("Kunjungan berhasil diselesaikan");
                            System.out.println("============================================");
                        case "tidak":
                            menuKunjunganUks();
                        default:
                            System.out.println("============================================");
                            System.out.println("Masukkan pilihan yang valid, pilih ya atau tidak");
                            System.out.println("============================================");
                            isMenuSelesaiKunjungan = false;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("============================================");
                    System.out.println("ID tidak valid, masukkan ID yang valid");
                    System.out.println("============================================");
                    scanner.nextLine();
                }
            }
            menuKunjunganUks();
        }
    }

    public static void cariKunjungan() {
        while (isMenuCariKunjungan) {
            System.out.println("============================================");
            System.out.print("Masukkan kata kunci pencarian data (id,nama,kelas) : ");
            String cari = scanner.nextLine();

            List<List<String>> hasil = new ArrayList<>();
            if (kunjungan.size() > 0) {
                for (List<String> data : kunjungan) {
                    if (data.get(0).toLowerCase().contains(cari.toLowerCase())
                            || data.get(1).toLowerCase().contains(cari.toLowerCase())
                            || data.get(2).toLowerCase().contains(cari.toLowerCase())) {
                        hasil.add(data);
                    }
                }

                if (hasil.isEmpty()) {
                    System.out.println("============================================");
                    System.out.println("Tidak ada data yang cocok dengan kata kunci : " + cari);
                } else {
                    System.out.println("============================================");
                    System.out.println("Hasil pencarian untuk : " + cari);
                    System.out.println("============================================");
                    for (List<String> dataCari : hasil) {
                        System.out.println("ID            : " + dataCari.get(0));
                        System.out.println("Nama          : " + dataCari.get(1));
                        System.out.println("Kelas         : " + dataCari.get(2));
                        System.out.println("Keluhan       : " + dataCari.get(3));
                        System.out.println("Tindakan      : " + dataCari.get(4));
                        System.out.println("Obat          : " + dataCari.get(5));
                        System.out.println("Banyaknya     : " + dataCari.get(6));
                        System.out.println("Tanggal Masuk : " + dataCari.get(7));
                        System.out.println("Waktu Masuk   : " + dataCari.get(8));
                        System.out.println("Waktu Keluar  : " + dataCari.get(9));
                        System.out.println("Dirujuk       : " + dataCari.get(10));
                        System.out.println("Pulang        : " + dataCari.get(11));

                        System.out.println("============================================");
                    }

                    if (isMenuEditKunjungan) {
                        isMenuEditKunjungan = false;
                        editKunjungan(hasil);
                    } else if (isMenuSelesaiKunjungan) {
                        isMenuSelesaiKunjungan = false;
                        selesaiKunjungan(hasil);
                    } else if (isMenuHapusKunjungan) {
                        isMenuHapusKunjungan = false;
                        hapusKunjungan(hasil);
                    } else if (isMenuSuratRujukan) {
                        Surat.suratRujukan(hasil);
                    } else if (isMenuSuratPulang) {
                        Surat.suratPulang(hasil);
                    } else {
                        boolean cariLagi = true;
                        while (cariLagi) {

                            System.out.print("Apakah ingin mencari lagi? (ya/tidak) : ");
                            String cariLagiMenu = scanner.next();

                            switch (cariLagiMenu.toLowerCase()) {
                                case "ya":
                                    cariLagi = false;
                                    isMenuCariKunjungan = true;
                                    scanner.nextLine();
                                    break;
                                case "tidak":
                                    isMenuCariKunjungan = false;
                                    menuKunjunganUks();
                                    break;
                                default:
                                    System.out.println("Masukkan pilihan yang valid, pilih ya atau tidak");
                                    break;
                            }
                        }
                    }
                }
            } else {
                System.out.println("Pencarian gagal, data kunjungan masih kosong");

                if (isMenuSuratRujukan || isMenuSuratPulang) {
                    isMenuCariKunjungan = false;
                    menuSurat();
                } else if (isMenuCariKunjungan) {
                    isMenuCariKunjungan = false;
                    menuKunjunganUks();
                }
            }
        }
    }

    public static void editKunjungan(List<List<String>> dataKunjungan) {
        if (isMenuEditKunjungan) {
            cariKunjungan();
        } else {
            System.out.println("\n============================================");
            System.out.println("=             EDIT KUNJUNGAN UKS           =");
            System.out.println("============================================");

            while (!isMenuEditKunjungan) {
                boolean idCheck = true;
                List<String> data = new ArrayList<>();

                boolean inputIdCheck = true;
                while (inputIdCheck) {
                    try {
                        while (idCheck) {
                            System.out.print("Masukkan ID kunjungan yang akan diedit : ");
                            int idEdit = scanner.nextInt();
                            String id = Integer.toString(idEdit);

                            for (List<String> dataHasil : dataKunjungan) {
                                if (dataHasil.get(0).contains(id)) {
                                    data = dataHasil;
                                    idCheck = false;
                                } else {
                                    System.out.println("============================================");
                                    System.out.println("pilihan tidak tersedia, masukkan ID yang valid");
                                    System.out.println("============================================");
                                }
                            }
                            scanner.nextLine();
                        }
                        inputIdCheck = false;
                    } catch (InputMismatchException e) {
                        System.out.println("============================================");
                        System.out.println("ID tidak valid, masukkan ID yang valid");
                        System.out.println("============================================");
                        scanner.nextLine();
                    }
                }

                boolean pilihanCheck = true;
                int index = 0;
                String newData = "";
                boolean ubahData = true;
                while (ubahData) {
                    System.out.println("============================================");
                    System.out.println("ID               : " + data.get(0));
                    System.out.println("Nama             : " + data.get(1));
                    System.out.println("Kelas            : " + data.get(2));
                    System.out.println("Keluhan          : " + data.get(3));
                    System.out.println("Tindakan         : " + data.get(4));
                    System.out.println("Obat             : " + data.get(5));
                    System.out.println("Jumlah digunakan : " + data.get(6));
                    System.out.println("Tanggal Masuk    : " + data.get(7));
                    System.out.println("Waktu Masuk      : " + data.get(8));
                    System.out.println("Waktu Keluar     : " + data.get(9));
                    System.out.println("Dirujuk          : " + data.get(10));
                    System.out.println("Pulang           : " + data.get(11));
                    System.out.println("============================================");
                    System.out.println("1. Nama");
                    System.out.println("2. Kelas");
                    System.out.println("3. Keluhan");
                    System.out.println("4. Tindakan");
                    System.out.println("5. Obat");
                    System.out.println("6. Rujukan");
                    System.out.println("7. Pulang");
                    System.out.println("============================================");

                    pilihanCheck = true;
                    while (pilihanCheck) {
                        boolean inputCheck = true;
                        while (inputCheck) {
                            try {
                                System.out.print("Pilih data apa yang akan diubah? (1-7) : ");
                                int pilihan = scanner.nextInt();

                                scanner.nextLine();
                                switch (pilihan) {
                                    case 1:
                                        index = 1;
                                        System.out.println("============================================");
                                        System.out.print("Masukkan nama baru : ");
                                        newData = scanner.nextLine();

                                        ubahData = false;
                                        inputCheck = false;
                                        break;
                                    case 2:
                                        index = 2;
                                        System.out.print("Masukkan kelas baru : ");
                                        newData = scanner.nextLine();

                                        ubahData = false;
                                        inputCheck = false;
                                        break;
                                    case 3:
                                        index = 3;
                                        System.out.print("Masukkan keluhan baru : ");
                                        newData = scanner.nextLine();

                                        ubahData = false;
                                        inputCheck = false;
                                        break;
                                    case 4:
                                        index = 4;
                                        System.out.print("Masukkan tindakan baru : ");
                                        newData = scanner.nextLine();

                                        ubahData = false;
                                        inputCheck = false;
                                        break;
                                    case 5:
                                        boolean obat = true;
                                        while (obat) {
                                            System.out.print("Ingin mengubah nama obat? (ya/tidak) : ");
                                            String namaObat = scanner.nextLine();

                                            switch (namaObat.toLowerCase()) {
                                                case "ya":
                                                    index = 5;
                                                    System.out.print("Masukkan nama obat baru : ");
                                                    newData = scanner.nextLine();
                                                    obat = false;
                                                    break;
                                                case "tidak":
                                                    boolean penggunaanObat = true;
                                                    while (penggunaanObat) {
                                                        System.out.print("Ingin mengubah jumlah penggunaan obat? (ya/tidak) : ");
                                                        String jumlahPenggunaan = scanner.nextLine();

                                                        switch (jumlahPenggunaan.toLowerCase()) {
                                                            case "ya":
                                                                index = 6;
                                                                System.out.print("Masukkan jumlah penggunaan obat baru : ");
                                                                newData = scanner.nextLine();
                                                                penggunaanObat = false;
                                                                obat = false;
                                                                break;
                                                            case "tidak":
                                                                penggunaanObat = false;
                                                                obat = false;
                                                                scanner.nextLine();
                                                                break;
                                                            default:
                                                                System.out.println("============================================");
                                                                System.out.println(
                                                                        "Masukkan pilihan yang valid, pilih ya atau tidak");
                                                                System.out.println("============================================");
                                                                break;
                                                        }
                                                    }
                                                    obat = false;
                                                    break;
                                                default:
                                                    System.out.println("============================================");
                                                    System.out.println(
                                                            "Masukkan pilihan yang valid, pilih ya atau tidak");
                                                    System.out.println("============================================");
                                                    break;
                                            }
                                        }

                                        ubahData = false;
                                        inputCheck = false;
                                        break;
                                    case 6:
                                        index = 10;
                                        System.out.println("Masukkan status rujukan baru");
                                        rujukanStatus = true;
                                        while (rujukanStatus) {
                                            System.out.print("Apakah pasien akan dirujuk? (ya/tidak) : ");
                                            String rujuk = scanner.next();

                                            switch (rujuk.toLowerCase()) {
                                                case "ya":
                                                    newData = "ya";
                                                    rujukanStatus = false;
                                                    break;
                                                case "tidak":
                                                    newData = "tidak";
                                                    rujukanStatus = false;
                                                    break;
                                                default:
                                                    System.out.println("============================================");
                                                    System.out.println(
                                                            "Masukkan pilihan yang valid, pilih ya atau tidak");
                                                    System.out.println("============================================");
                                                    break;
                                            }
                                        }
                                        scanner.nextLine();
                                        ubahData = false;
                                        inputCheck = false;
                                        break;
                                    case 7:
                                        index = 11;
                                        System.out.print("Masukkan status pulang baru");
                                        pulangStatus = true;
                                        while (pulangStatus) {
                                            System.out.print("Apakah pasien akan pulang? (ya/tidak) : ");
                                            String pulang = scanner.next();

                                            switch (pulang.toLowerCase()) {
                                                case "ya":
                                                    newData = "ya";
                                                    pulangStatus = false;
                                                    break;
                                                case "tidak":
                                                    newData = "tidak";
                                                    pulangStatus = false;
                                                    break;
                                                default:
                                                    System.out.println("============================================");
                                                    System.out.println(
                                                            "Masukkan pilihan yang valid, pilih ya atau tidak");
                                                    System.out.println("============================================");
                                                    break;
                                            }
                                        }
                                        scanner.nextLine();
                                        ubahData = false;
                                        inputCheck = false;
                                        break;
                                    default:
                                        System.out.println("============================================");
                                        System.out.println("Pilihan tidak tersedia, pilih (1-7)");
                                        System.out.println("============================================");
                                        scanner.nextLine();
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("============================================");
                                System.out.println("Pilihan tidak tersedia, pilih (1-7)");
                                System.out.println("============================================");
                                scanner.nextLine();
                            }
                        }

                        data.set(index, newData);

                        System.out.println("============================================");
                        System.out.println("Berhasil mengubah data kunjungan");
                        System.out.println("============================================");

                        boolean konfirmasiUbah = true;
                        while (konfirmasiUbah) {
                            System.out.print("Apakah ingin mengubah data lagi? (ya/tidak) : ");
                            String ubahLagi = scanner.nextLine();

                            switch (ubahLagi.toLowerCase()) {
                                case "ya":
                                    pilihanCheck = false;
                                    ubahData = true;
                                    konfirmasiUbah = false;
                                    break;
                                case "tidak":
                                    boolean kunjunganLain = true;
                                    while (kunjunganLain) {
                                        System.out.println("============================================");
                                        System.out.print("Apakah ingin mengubah data kunjungan lain? (ya/tidak) : ");
                                        String ubahLain = scanner.nextLine();

                                        switch (ubahLain.toLowerCase()) {
                                            case "ya":
                                                konfirmasiUbah = false;
                                                isMenuEditKunjungan = true;
                                                cariKunjungan();
                                                break;
                                            case "tidak":
                                                ubahData = false;
                                                konfirmasiUbah = false;
                                                isMenuCariKunjungan = true;
                                                menuKunjunganUks();
                                                break;
                                            default:
                                                System.out.println("============================================");
                                                System.out.println("Masukkan pilihan yang valid, pilih ya atau tidak");
                                        }
                                    }

                                    break;
                                default:
                                    System.out.println("============================================");
                                    System.out.println("Masukkan pilihan yang valid, pilih ya atau tidak");
                                    System.out.println("============================================");
                                    break;
                            }
                        }
                    }
                }
            }
        }
    }

    public static void hapusKunjungan(List<List<String>> dataKunjungan) {
        if (isMenuHapusKunjungan) {
            cariKunjungan();
        } else {
            System.out.println("\n============================================");
            System.out.println("=               HAPUS KUNJUNGAN            =");
            System.out.println("============================================");

            while (!isMenuHapusKunjungan) {
                boolean idCheck = true;
                List<String> data = null;
                try {
                    while (idCheck) {
                        System.out.print("Masukkan ID Kunjungan yang akan dihapus : ");
                        int idData = scanner.nextInt();
                        String id = Integer.toString(idData);

                        boolean isFound = false;
                        for (List<String> dataHasil : kunjungan) {
                            if (dataHasil.get(0).equals(id)) {
                                data = dataHasil;
                                idCheck = false;
                                isFound = true;
                            } else {
                                System.out.println("============================================");
                                System.out.println("pilihan tidak tersedia, masukkan ID yang valid");
                                System.out.println("============================================");
                            }
                        }

                        if (!isFound) {
                            System.out.println("============================================");
                            System.out.println("Data tidak ditemukan, masukkan ID yang valid");
                            System.out.println("============================================");
                        }
                        scanner.nextLine();
                    }
                } catch (InputMismatchException e) {
                    System.out.println("============================================");
                    System.out.println("ID tidak valid, masukkan ID yang valid");
                    System.out.println("============================================");
                    scanner.nextLine();
                }

                boolean hapusData = true;
                while (hapusData) {

                    if (data != null) {
                        System.out.println("============================================");
                        System.out.println("ID               : " + data.get(0));
                        System.out.println("Nama             : " + data.get(1));
                        System.out.println("Kelas            : " + data.get(2));
                        System.out.println("Keluhan          : " + data.get(3));
                        System.out.println("Tindakan         : " + data.get(4));
                        System.out.println("Obat             : " + data.get(5));
                        System.out.println("Jumlah digunakan : " + data.get(6));
                        System.out.println("Tanggal Masuk    : " + data.get(7));
                        System.out.println("Waktu Masuk      : " + data.get(8));
                        System.out.println("Waktu Keluar     : " + data.get(9));
                        System.out.println("Dirujuk          : " + data.get(10));
                        System.out.println("Pulang           : " + data.get(11));
                        System.out.println("============================================");

                        System.out.print("Apakah anda yakin ingin menghapus data ini? (ya/tidak) : ");
                        String hapus = scanner.nextLine();

                        switch (hapus.toLowerCase()) {
                            case "ya":
                                kunjungan.remove(data);
                                System.out.println("============================================");
                                System.out.println("Berhasil menghapus data kunjungan");
                                System.out.println("============================================");

                                boolean konfirmasiHapus = true;
                                while (konfirmasiHapus) {
                                    System.out.print("Apakah ingin menghapus data lain? (ya/tidak) : ");
                                    String hapusLagi = scanner.nextLine();

                                    switch (hapusLagi.toLowerCase()) {
                                        case "ya":
                                            konfirmasiHapus = false;
                                            isMenuHapusKunjungan = true;
                                            cariKunjungan();
                                            break;
                                        case "tidak":
                                            hapusData = false;
                                            konfirmasiHapus = false;
                                            isMenuHapusKunjungan = false;
                                            menuKunjunganUks();
                                            break;
                                        default:
                                            System.out.println("============================================");
                                            System.out.println("Masukkan pilihan yang valid, pilih ya atau tidak");
                                            System.out.println("============================================");
                                            break;
                                    }
                                }
                                break;
                            case "tidak":
                                hapusData = false;
                                isMenuHapusKunjungan = false;
                                menuKunjunganUks();
                                break;
                            default:
                                System.out.println("============================================");
                                System.out.println("Masukkan pilihan yang valid, pilih ya atau tidak");
                                System.out.println("============================================");
                                break;
                        }
                    }
                }
            }
            scanner.nextLine();
        }

    }

}
