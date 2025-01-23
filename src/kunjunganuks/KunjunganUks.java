/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kunjunganuks;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.TimeZone;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.borders.Border;
import java.io.IOException;

import java.io.FileNotFoundException;

/**
 *
 * @author RIZAL
 */
public class KunjunganUks {

    /**
     * @param args the command line arguments
     */
    public static boolean isLogin = false;
    public static boolean isMenuUtama = true;

    public static boolean isMenuKunjungan = true;
    public static boolean isMenuTambahKunjungan = true;
    public static boolean isMenuEditKunjungan = false;
    public static boolean isMenuSelesaiKunjungan = false;
    public static boolean isMenuCariKunjungan = true;
    public static boolean isMenuHapusKunjungan = false;

    public static boolean isMenuObatUks = true;
    public static boolean isMenuTambahObat = true;
    public static boolean isMenuEditObat = false;
    public static boolean isMenuCariObat = true;
    public static boolean isMenuHapusObat = false;

    public static boolean isMenuSurat = true;
    public static boolean isMenuSuratRujukan = false;
    public static boolean isMenuSuratPulang = false;

    public static boolean rujukanStatus = true;
    public static boolean pulangStatus = true;
    public static boolean tambahKunjunganLagi = true;

    public static ArrayList<List<String>> kunjungan = new ArrayList<>();
    public static ArrayList<List<String>> dataObat = new ArrayList<>();
    public static List<String> suratRujukan = new ArrayList<>();
    public static List<String> suratPulang = new ArrayList<>();

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        index();
    }

    public static String getCurrentTime() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Jakarta");

        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

        Calendar cal = Calendar.getInstance(timeZone);
        dateFormat.setTimeZone(cal.getTimeZone());

        String currentTime = dateFormat.format(cal.getTime());
        return currentTime;
    }

    public static String getCurrentDate() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Jakarta");

        DateFormat dateFormat = new SimpleDateFormat("DD/MM/YYY");

        Calendar cal = Calendar.getInstance(timeZone);
        dateFormat.setTimeZone(cal.getTimeZone());

        String currentTime = dateFormat.format(cal.getTime());
        return currentTime;
    }

    public static void index() {
        System.out.println("\n============================================");
        System.out.println("= SELAMAT DATANG DI APLIKASI KUNJUNGAN UKS =");
        System.out.println("============================================");

        while (!isLogin) {
            System.out.print("Username : ");
            String username = scanner.nextLine();
            System.out.print("Password : ");
            String password = scanner.nextLine();
            System.out.println("============================================");
            login(username, password);
            System.out.println("============================================");
        }

        menuUtama();
    }

    public static void login(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            System.out.println("Login berhasil!");
            isLogin = true;
        } else {
            System.out.println("Login gagal, username atau password salah");
            isLogin = false;
        }
    }

    public static void menuUtama() {
        System.out.println("\n============================================");
        System.out.println("=            MENU - MENU UTAMA             =");
        System.out.println("============================================");
        System.out.println("1. Kunjungan UKS");
        System.out.println("2. Obat UKS");
        System.out.println("3. Surat Rujukan & Pulang");
        System.out.println("4. Logout");
        System.out.println("============================================");

        while (isMenuUtama) {
            try {
                System.out.print("Pilihan menu (1-4) : ");
                int pilihan = scanner.nextInt();

                switch (pilihan) {
                    case 1:
                        menuKunjunganUks();
                        break;
                    case 2:
                        menuObatUks();
                        break;
                    case 3:
                        menuSurat();
                        break;
                    case 4:
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
                        scanner.nextLine();
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
                System.out.println("ID            : " + data.get(0));
                System.out.println("Nama          : " + data.get(1));
                System.out.println("Kelas         : " + data.get(2));
                System.out.println("Keluhan       : " + data.get(3));
                System.out.println("Tindakan      : " + data.get(4));
                System.out.println("Obat          : " + data.get(5));
                System.out.println("Tanggal Masuk : " + data.get(6));
                System.out.println("Waktu Masuk   : " + data.get(7));
                System.out.println("Waktu Keluar  : " + data.get(8));
                System.out.println("Dirujuk       : " + data.get(9));
                System.out.println("Pulang        : " + data.get(10));

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

            System.out.println("Tanggal Masuk : " + getCurrentDate());
            String tanggal_masuk = getCurrentDate();

            System.out.println("Waktu Masuk : " + getCurrentTime());
            String waktu_masuk = getCurrentTime();

            String waktu_keluar = "-";
            String isRujukan = "-";
            String isPulang = "-";

            kunjungan.add(Arrays.asList(id, nama, kelas, keluhan, tindakan, obat, tanggal_masuk, waktu_masuk,
                    waktu_keluar, isRujukan, isPulang));
            System.out.println("============================================");
            System.out.println("Kunjungan baru berhasil ditambahkan");
            System.out.println("============================================");
            tambahKunjunganLagi = true;
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
                            data.set(8, getCurrentTime());

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

                            data.set(9, isRujukan);
                            data.set(10, isPulang);
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
                        System.out.println("Tanggal Masuk : " + dataCari.get(6));
                        System.out.println("Waktu Masuk   : " + dataCari.get(7));
                        System.out.println("Waktu Keluar  : " + dataCari.get(8));
                        System.out.println("Dirujuk       : " + dataCari.get(9));
                        System.out.println("Pulang        : " + dataCari.get(10));

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
                        suratRujukan(hasil);
                    } else if (isMenuSuratPulang) {
                        suratPulang(hasil);
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
                    System.out.println("ID            : " + data.get(0));
                    System.out.println("Nama          : " + data.get(1));
                    System.out.println("Kelas         : " + data.get(2));
                    System.out.println("Keluhan       : " + data.get(3));
                    System.out.println("Tindakan      : " + data.get(4));
                    System.out.println("Obat          : " + data.get(5));
                    System.out.println("Tanggal Masuk : " + data.get(6));
                    System.out.println("Waktu Masuk   : " + data.get(7));
                    System.out.println("Waktu Keluar  : " + data.get(8));
                    System.out.println("Dirujuk       : " + data.get(9));
                    System.out.println("Pulang        : " + data.get(10));
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
                                        index = 5;
                                        System.out.print("Masukkan obat baru : ");
                                        newData = scanner.nextLine();

                                        ubahData = false;
                                        inputCheck = false;
                                        break;
                                    case 6:
                                        index = 9;
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
                                        index = 10;
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
                        System.out.println("ID            : " + data.get(0));
                        System.out.println("Nama          : " + data.get(1));
                        System.out.println("Kelas         : " + data.get(2));
                        System.out.println("Keluhan       : " + data.get(3));
                        System.out.println("Tindakan      : " + data.get(4));
                        System.out.println("Obat          : " + data.get(5));
                        System.out.println("Tanggal Masuk : " + data.get(6));
                        System.out.println("Waktu Masuk   : " + data.get(7));
                        System.out.println("Waktu Keluar  : " + data.get(8));
                        System.out.println("Dirujuk       : " + data.get(9));
                        System.out.println("Pulang        : " + data.get(10));
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

    public static void menuObatUks() {
        System.out.println("\n============================================");
        System.out.println("=            MENU - MENU OBAT UKS          =");
        System.out.println("============================================");
        System.out.println("1. Lihat Daftar Obat");
        System.out.println("2. Tambah Obat");
        System.out.println("3. Edit Obat");
        System.out.println("4. Hapus Obat");
        System.out.println("5. Cari Obat");
        System.out.println("6. Kembali");
        System.out.println("7. Logout");
        System.out.println("============================================");

        isMenuObatUks = true;
        while (isMenuObatUks) {
            try {
                System.out.print("Pilihan menu (1-7) : ");
                int pilihan = scanner.nextInt();

                switch (pilihan) {
                    case 1:
                        isMenuObatUks = false;
                        lihatObat();
                        break;
                    case 2:
                        isMenuObatUks = false;
                        tambahObat();
                        break;
                    case 3:
                        isMenuCariObat = true;
                        isMenuEditObat = true;
                        scanner.nextLine();
                        cariObat();
                        break;
                    case 4:
                        isMenuCariObat = true;
                        isMenuHapusObat = true;
                        scanner.nextLine();
                        cariObat();
                        break;
                    case 5:
                        isMenuCariObat = true;
                        isMenuHapusObat = false;
                        isMenuEditObat = false;
                        scanner.nextLine();
                        cariObat();
                        break;
                    case 6:
                        menuUtama();
                        break;
                    case 7:
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

    public static void lihatObat() {
        System.out.println("\n============================================");
        System.out.println("=            DAFTAR OBAT UKS              =");
        System.out.println("============================================");
        if (dataObat.size() == 0) {
            System.out.println("Tidak ada data obat");
            System.out.println("============================================");
        } else {
            for (int i = 0; i <= dataObat.size() - 1; i++) {
                List<String> data = dataObat.get(i);

                System.out.println("Data Obat ke-" + (i + 1));
                System.out.println("ID            : " + data.get(0));
                System.out.println("Nama          : " + data.get(1));
                System.out.println("Deskripsi     : " + data.get(2));
                System.out.println("Stok          : " + data.get(3));
                System.out.println("Satuan        : " + data.get(4));

                System.out.println("============================================");
            }
        }
        menuObatUks();
    }

    public static void tambahObat() {
        String id;

        if (!dataObat.isEmpty()) {
            int size = dataObat.size() - 1;
            List<String> lastData = dataObat.get(size);
            int incrementId = Integer.parseInt(lastData.get(0)) + 1;
            String newId = Integer.toString(incrementId);
            id = newId;
        } else {
            id = "1";
        }

        while (isMenuTambahObat) {
            System.out.println("\n============================================");
            System.out.println("=                TAMBAH OBAT               =");
            System.out.println("============================================");
            System.out.println("Id Obat : " + id);

            scanner.nextLine();
            System.out.print("Nama Obat : ");
            String nama = scanner.nextLine();

            System.out.print("Deskripsi : ");
            String deskripsi = scanner.nextLine();

            String stok = "";
            boolean validasiStok = true;
            while (validasiStok) {
                try {
                    System.out.print("Stok : ");
                    int inputStok = scanner.nextInt();
                    stok = Integer.toString(inputStok);
                    validasiStok = false;
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("============================================");
                    System.out.println("Stok tidak valid, harap masukkan angka");
                    System.out.println("============================================");
                    validasiStok = true;
                    scanner.nextLine();
                }
            }

            System.out.print("Satuan : ");
            String satuan = scanner.nextLine();

            dataObat.add(Arrays.asList(id, nama, deskripsi, stok, satuan));

            System.out.println("============================================");
            System.out.println("Obat baru berhasil ditambahkan");
            System.out.println("============================================");
            boolean tambahObatLagi = true;
            while (tambahObatLagi) {
                System.out.print("Ingin menambah obat lagi? (ya/tidak) : ");
                String konfirmasiTambahLagi = scanner.next();

                switch (konfirmasiTambahLagi.toLowerCase()) {
                    case "ya":
                        tambahObatLagi = false;
                        isMenuTambahObat = true;
                        tambahObat();
                        break;
                    case "tidak":
                        isMenuTambahObat = true;
                        tambahObatLagi = false;
                        menuObatUks();
                        break;
                    default:
                        System.out.println("============================================");
                        System.out.println("Input tidak valid, masukkan ya atau tidak");
                        System.out.println("============================================");
                        tambahObatLagi = true;
                        break;
                }
            }
        }
    }

    public static void cariObat() {
        while (isMenuCariObat) {
            System.out.println("============================================");
            System.out.print("Masukkan kata kunci pencarian data (id,nama) : ");
            String cari = scanner.nextLine();

            List<List<String>> hasil = new ArrayList<>();
            if (dataObat.size() > 0) {
                for (List<String> data : dataObat) {
                    if (data.get(0).toLowerCase().contains(cari.toLowerCase())
                            || data.get(1).toLowerCase().contains(cari.toLowerCase())) {
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
                        System.out.println("Deskripsi     : " + dataCari.get(2));
                        System.out.println("Stok          : " + dataCari.get(3));
                        System.out.println("Satuan        : " + dataCari.get(4));

                        System.out.println("============================================");
                    }

                    if (isMenuEditObat) {
                        isMenuEditObat = false;
                        editObat(hasil);
                    } else if (isMenuHapusObat) {
                        isMenuHapusObat = false;
                        hapusObat(hasil);
                    } else {
                        boolean cariLagi = true;
                        while (cariLagi) {

                            System.out.print("Apakah ingin mencari lagi? (ya/tidak) : ");
                            String cariLagiMenu = scanner.next();

                            switch (cariLagiMenu.toLowerCase()) {
                                case "ya":
                                    cariLagi = false;
                                    isMenuCariObat = true;
                                    scanner.nextLine();
                                    break;
                                case "tidak":
                                    isMenuCariObat = false;
                                    menuObatUks();
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
            } else {
                System.out.println("============================================");
                System.out.println("Pencarian gagal, data masih kosong");
                isMenuCariObat = false;
                menuObatUks();
            }
        }
    }

    public static void editObat(List<List<String>> dataObatEdit) {
        if (isMenuEditObat) {
            cariObat();
        } else {
            System.out.println("\n============================================");
            System.out.println("=               EDIT OBAT UKS              =");
            System.out.println("============================================");

            while (!isMenuEditObat) {
                boolean idCheck = true;
                List<String> data = new ArrayList<>();
                try {
                    while (idCheck) {
                        System.out.print("Masukkan ID obat yang akan diedit : ");
                        int idEdit = scanner.nextInt();
                        String id = Integer.toString(idEdit);

                        for (List<String> dataHasil : dataObatEdit) {
                            if (dataHasil.get(0).contains(id)) {
                                data = dataHasil;
                                idCheck = false;
                                scanner.nextLine();
                            } else {
                                System.out.println("============================================");
                                System.out.println("pilihan tidak tersedia, masukkan ID yang valid");
                                System.out.println("============================================");
                                scanner.nextLine();
                            }
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("============================================");
                    System.out.println("ID tidak valid, masukkan ID yang valid");
                    System.out.println("============================================");
                    scanner.nextLine();
                }

                boolean pilihanCheck = true;
                int index = 0;
                String newData = "";
                boolean ubahData = true;
                while (ubahData) {
                    System.out.println("============================================");
                    System.out.println("ID            : " + data.get(0));
                    System.out.println("Nama          : " + data.get(1));
                    System.out.println("Deskripsi     : " + data.get(2));
                    System.out.println("Stok          : " + data.get(3));
                    System.out.println("Satuan        : " + data.get(4));
                    System.out.println("============================================");
                    System.out.println("1. Nama");
                    System.out.println("2. Deskripsi");
                    System.out.println("3. Stok");
                    System.out.println("4. Satuan");
                    System.out.println("============================================");

                    pilihanCheck = true;
                    while (pilihanCheck) {
                        boolean check = true;
                        while (check) {
                            try {
                                System.out.print("Pilih data apa yang akan diubah? (1-4) : ");
                                int pilihan = scanner.nextInt();

                                scanner.nextLine();
                                switch (pilihan) {
                                    case 1:
                                        index = 1;
                                        System.out.println("============================================");
                                        System.out.print("Masukkan nama baru : ");
                                        newData = scanner.nextLine();

                                        ubahData = false;
                                        check = false;
                                        break;
                                    case 2:
                                        index = 2;
                                        System.out.println("============================================");
                                        System.out.print("Masukkan deskripsi baru : ");
                                        newData = scanner.nextLine();

                                        ubahData = false;
                                        check = false;
                                        break;
                                    case 3:
                                        index = 3;
                                        boolean tambahStok = true;
                                        while (tambahStok) {
                                            System.out.print("Ingin menambah stok? (ya/tidak) : ");
                                            String konfirmasiStok = scanner.nextLine();

                                            switch (konfirmasiStok.toLowerCase()) {
                                                case "ya":
                                                    boolean validasiTambahStok = true;
                                                    while (validasiTambahStok) {
                                                        try {
                                                            System.out.println(
                                                                    "============================================");
                                                            System.out.print("Masukkan penambahan stok : ");
                                                            int inputStok = scanner.nextInt();

                                                            int currentStok = Integer.parseInt(data.get(3));
                                                            newData = Integer.toString(inputStok + currentStok);

                                                            validasiTambahStok = false;
                                                            tambahStok = false;
                                                            scanner.nextLine();
                                                        } catch (InputMismatchException e) {
                                                            System.out.println(
                                                                    "============================================");
                                                            System.out
                                                                    .println("Stok tidak valid, harap masukkan angka");
                                                            System.out.println(
                                                                    "============================================");
                                                            validasiTambahStok = true;
                                                            scanner.nextLine();
                                                        }
                                                    }
                                                    break;
                                                case "tidak":
                                                    boolean kurangiStok = true;
                                                    while (kurangiStok) {
                                                        System.out.println(
                                                                "============================================");
                                                        System.out.print("Ingin mengurangi stok? (ya/tidak) : ");
                                                        String konfirmasiPenguranganStok = scanner.nextLine();

                                                        switch (konfirmasiPenguranganStok.toLowerCase()) {
                                                            case "ya":
                                                                boolean validasiKurangStok = true;
                                                                while (validasiKurangStok) {
                                                                    try {
                                                                        System.out.println(
                                                                                "============================================");
                                                                        System.out
                                                                                .print("Masukkan pengurangan stok : ");
                                                                        int inputStok = scanner.nextInt();

                                                                        int currentStok = Integer.parseInt(data.get(3));
                                                                        int stokBaru = currentStok - inputStok;

                                                                        if (stokBaru < 0) {
                                                                            System.out.println(
                                                                                    "============================================");
                                                                            System.out.println(
                                                                                    "Gagal, pengurangan stok melebihi stok saat ini");
                                                                            System.out.println(
                                                                                    "============================================");
                                                                            validasiKurangStok = true;
                                                                        } else {
                                                                            newData = Integer
                                                                                    .toString(currentStok - inputStok);
                                                                            validasiKurangStok = false;
                                                                        }

                                                                        tambahStok = false;
                                                                        scanner.nextLine();
                                                                    } catch (InputMismatchException e) {
                                                                        System.out.println(
                                                                                "============================================");
                                                                        System.out
                                                                                .println(
                                                                                        "Stok tidak valid, harap masukkan angka");
                                                                        System.out.println(
                                                                                "============================================");
                                                                        validasiKurangStok = true;
                                                                        scanner.nextLine();
                                                                    }
                                                                }
                                                                kurangiStok = false;
                                                                break;
                                                            case "tidak":
                                                                newData = "";
                                                                tambahStok = false;
                                                                kurangiStok = false;
                                                                break;
                                                            default:
                                                                System.out.println(
                                                                        "============================================");
                                                                System.out.println(
                                                                        "Masukkan pilihan yang valid, pilih ya atau tidak");
                                                                System.out.println(
                                                                        "============================================");
                                                                break;
                                                        }
                                                    }
                                                    tambahStok = false;
                                                    break;
                                                default:
                                                    System.out.println("============================================");
                                                    System.out.println(
                                                            "Masukkan pilihan yang valid, pilih ya atau tidak");
                                                    System.out.println("============================================");
                                                    break;
                                            }

                                            ubahData = false;
                                            check = false;
                                        }
                                        break;
                                    case 4:
                                        index = 4;
                                        System.out.print("Masukkan satuan baru : ");
                                        newData = scanner.nextLine();

                                        ubahData = false;
                                        check = false;
                                        break;
                                    default:
                                        System.out.println("============================================");
                                        System.out.println("Pilihan tidak tersedia, pilih (1-4)");
                                        System.out.println("============================================");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("============================================");
                                System.out.println("Pilihan tidak tersedia, pilih (1-4)");
                                System.out.println("============================================");
                                scanner.nextLine();
                            }
                        }

                        if (newData == "") {
                            data.set(index, data.get(3));
                            System.out.println("============================================");
                            System.out.println("Tidak ada perubahan data obat");
                            System.out.println("============================================");
                        } else {
                            data.set(index, newData);
                            System.out.println("============================================");
                            System.out.println("Berhasil mengubah data obat");
                            System.out.println("============================================");
                        }

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
                                    boolean obatLain = true;
                                    while (obatLain) {
                                        System.out.println("============================================");
                                        System.out.print("Apakah ingin mengubah data obat lain? (ya/tidak) : ");
                                        String ubahLain = scanner.nextLine();

                                        switch (ubahLain.toLowerCase()) {
                                            case "ya":
                                                konfirmasiUbah = false;
                                                isMenuEditObat = true;
                                                cariObat();
                                                break;
                                            case "tidak":
                                                ubahData = false;
                                                konfirmasiUbah = false;
                                                isMenuCariObat = true;
                                                menuObatUks();
                                                break;
                                            default:
                                                System.out.println("============================================");
                                                System.out.println("Masukkan pilihan yang valid, pilih ya atau tidak");
                                                break;
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

    public static void hapusObat(List<List<String>> dataObatHapus) {
        if (isMenuHapusObat) {
            cariObat();
        } else {
            System.out.println("\n============================================");
            System.out.println("=                  HAPUS OBAT              =");
            System.out.println("============================================");

            while (!isMenuHapusObat) {
                boolean idCheck = true;
                List<String> data = null;
                try {
                    while (idCheck) {
                        System.out.print("Masukkan ID Obat yang akan dihapus : ");
                        int idData = scanner.nextInt();
                        String id = Integer.toString(idData);

                        boolean isFound = false;
                        for (List<String> dataHasil : dataObatHapus) {
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
                    }
                    scanner.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("============================================");
                    System.out.println("ID tidak valid, masukkan ID yang valid");
                    System.out.println("============================================");
                    scanner.nextLine();
                }

                boolean hapusData = true;
                while (hapusData) {
                    System.out.println("============================================");
                    System.out.println("ID            : " + data.get(0));
                    System.out.println("Nama          : " + data.get(1));
                    System.out.println("Deskripsi     : " + data.get(2));
                    System.out.println("Stok          : " + data.get(3));
                    System.out.println("Satuan        : " + data.get(4));
                    System.out.println("============================================");

                    boolean checkInput = true;
                    while (checkInput) {
                        System.out.print("Apakah anda yakin ingin menghapus data ini? (ya/tidak) : ");
                        String hapus = scanner.nextLine();

                        switch (hapus.toLowerCase()) {
                            case "ya":
                                dataObat.remove(data);
                                System.out.println("============================================");
                                System.out.println("Berhasil menghapus data obat");
                                System.out.println("============================================");

                                boolean konfirmasiHapus = true;
                                while (konfirmasiHapus) {
                                    System.out.print("Apakah ingin menghapus data lain? (ya/tidak) : ");
                                    String hapusLagi = scanner.nextLine();

                                    switch (hapusLagi.toLowerCase()) {
                                        case "ya":
                                            konfirmasiHapus = false;
                                            isMenuHapusObat = true;
                                            cariObat();
                                            break;
                                        case "tidak":
                                            hapusData = false;
                                            konfirmasiHapus = false;
                                            isMenuHapusObat = false;
                                            menuObatUks();
                                            break;
                                        default:
                                            System.out.println("============================================");
                                            System.out.println("Masukkan pilihan yang valid, pilih ya atau tidak");
                                            System.out.println("============================================");
                                            break;
                                    }
                                }
                                checkInput = false;
                                break;
                            case "tidak":
                                hapusData = false;
                                isMenuHapusObat = false;
                                checkInput = false;
                                menuObatUks();
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

    public static void menuSurat() {
        System.out.println("\n============================================");
        System.out.println("=            MENU - MENU SURAT          =");
        System.out.println("============================================");
        System.out.println("1. Surat Rujukan");
        System.out.println("2. Surat Pulang");
        System.out.println("3. Kembali");
        System.out.println("4. Logout");
        System.out.println("============================================");

        isMenuSurat = true;
        while (isMenuSurat) {
            try {
                System.out.print("Pilihan menu (1-4) : "); // TODO : BENERIN LOOP INI
                int pilihan = scanner.nextInt();

                switch (pilihan) {
                    case 1:
                        isMenuSurat = false;
                        isMenuEditKunjungan = false;
                        isMenuHapusKunjungan = false;
                        isMenuSuratPulang = false;
                        isMenuSuratRujukan = true;
                        isMenuCariKunjungan = true;
                        scanner.nextLine();
                        cariKunjungan();
                        break;
                    case 2:
                        isMenuSurat = false;
                        isMenuCariKunjungan = true;
                        isMenuEditKunjungan = false;
                        isMenuHapusKunjungan = false;
                        isMenuSuratRujukan = false;
                        isMenuSuratPulang = true;
                        scanner.nextLine();
                        cariKunjungan();
                        break;
                    case 3:
                        menuUtama();
                        break;
                    case 4:
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

    public static void suratRujukan(List<List<String>> dataSuratRujukan) {
        String nomor = "";
        if (suratRujukan.size() == 0) {
            nomor = "001";
        } else {
            String lastData = suratRujukan.get(suratRujukan.size() - 1);
            int increment = Integer.parseInt(lastData) + 1;
            String result = Integer.toString(increment);

            if (result.length() == 1) {
                nomor = "00" + result;
            } else if (result.length() == 2) {
                nomor = "0" + result;
            } else {
                nomor = result;
            }
        }

        System.out.println("\n============================================");
        System.out.println("=                SURAT RUJUKAN             =");
        System.out.println("============================================");

        while (isMenuSuratRujukan) {
            boolean idCheck = true;
            List<String> data = null;
            try {
                while (idCheck) {
                    System.out.print("Masukkan ID kunjungan yang akan dibuatkan surat rujukan : ");
                    int idData = scanner.nextInt();
                    String id = Integer.toString(idData);

                    boolean isFound = false;
                    for (List<String> dataHasil : dataSuratRujukan) {
                        if (dataHasil.get(0).equals(id)) {
                            if (dataHasil.get(9).equals("ya")) {
                                data = dataHasil;
                                idCheck = false;
                                isFound = true;
                            } else {
                                System.out.println("============================================");
                                System.out.println("Invalid status, status rujukan tidak valid");
                                System.out.println("============================================");
                                scanner.nextLine();
                                menuSurat();
                            }
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
                }
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("============================================");
                System.out.println("ID tidak valid, masukkan ID yang valid");
                System.out.println("============================================");
                scanner.nextLine();
            }

            boolean cetak = true;
            while (cetak) {
                System.out.println("============================================");
                System.out.println("ID            : " + data.get(0));
                System.out.println("Nama          : " + data.get(1));
                System.out.println("Kelas         : " + data.get(2));
                System.out.println("Keluhan       : " + data.get(3));
                System.out.println("Tindakan      : " + data.get(4));
                System.out.println("Obat          : " + data.get(5));
                System.out.println("Tanggal Masuk : " + data.get(6));
                System.out.println("Waktu Masuk   : " + data.get(7));
                System.out.println("Waktu Keluar  : " + data.get(8));
                System.out.println("============================================");

                String namaInstansi = "";
                String namaUnit = "";
                String tanggalDibuat = "";

                System.out.print("Masukkan nama instansi kesehatan yang dituju : ");
                namaInstansi = scanner.nextLine();

                System.out.print("Masukkan nama unit yang dituju : ");
                namaUnit = scanner.nextLine();

                tanggalDibuat = getCurrentDate();

                boolean checkInput = true;
                while (checkInput) {
                    System.out.print("Apakah anda yakin ingin mencetak data ini? (ya/tidak) : ");
                    String cetakPdf = scanner.nextLine();

                    switch (cetakPdf.toLowerCase()) {
                        case "ya":
                            try {
                                String fileName = data.get(0) + "_" + data.get(1) + "_" + data.get(2) + "_" + System.currentTimeMillis() + ".pdf";
                                // Membuat PdfWriter dan PdfDocument
                                PdfWriter writer = new PdfWriter("C:\\Surat_Rujukan_UKS_SMA_Angkasa\\" + fileName);
                                PdfDocument pdf = new PdfDocument(writer);

                                // Membuat dokumen layout untuk menambahkan elemen
                                Document document = new Document(pdf);

                                // Membuat font dengan menangani IOException
                                PdfFont font = PdfFontFactory.createFont();  // font default

                                // Menambahkan judul surat
                                document.add(new Paragraph("Surat Rujukan UKS")
                                        .setTextAlignment(TextAlignment.CENTER)
                                        .setFont(font)
                                        .setFontSize(18));

                                // Menambahkan nomor surat dan tanggal
                                document.add(new Paragraph("\nNomor Surat: " + nomor + "/UKS/2025")
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));
                                document.add(new Paragraph("Tanggal: " + tanggalDibuat)
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));

                                // Menambahkan informasi surat
                                document.add(new Paragraph("\nKepada,")
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));
                                document.add(new Paragraph(namaUnit + "\n" + namaInstansi)
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));

                                document.add(new Paragraph("\nDengan hormat,")
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));

                                // Isi surat
                                document.add(new Paragraph("Mohon pemeriksaan / tindakan lebih lanjut kepada : \n"
                                        + "Nama : " + data.get(1)
                                        + "\nKelas: " + data.get(2)
                                        + "\nKeluhan : " + data.get(3)
                                        + "\nKami rujuk untuk pemeriksaan lebih lanjut di klinik/rumah sakit Anda.\n\n"
                                        + "Demikian surat rujukan ini kami buat, atas perhatian dan kerjasamanya kami ucapkan terima kasih.\n\n\n\n")
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));

                                // Membuat tabel untuk tanda tangan
                                Table table = new Table(1); // 2 kolom untuk tanda tangan kiri dan kanan

                                // Menambahkan kolom kanan untuk tanda tangan wali kelas
                                table.addCell(new Cell().add(new Paragraph("Mengetahui\n\n\n\n\nWali Kelas " + data.get(2))
                                        .setTextAlignment(TextAlignment.CENTER)
                                        .setFont(font)
                                        .setFontSize(12))
                                        .setBorder(Border.NO_BORDER));

                                table.setHorizontalAlignment(HorizontalAlignment.CENTER);

                                // Menambahkan tabel tanda tangan ke dokumen
                                document.add(table); // Menutup dokumen PDF
                                document.close();

                                System.out.println("============================================");
                                System.out.println("File Rujukan Berhasil dibuat");
                                System.out.println("============================================");
                                System.out.println("Lokasi file : C:\\Surat_Rujukan_UKS_SMA_Angkasa\\" + fileName);

                                suratRujukan.add(nomor);
                                System.out.println(suratRujukan.size());
                                System.out.println(suratRujukan);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            checkInput = false;
                            menuSurat();
                            break;
                        case "tidak":
                            System.out.println("============================================");
                            System.out.println("Pembuatan surat rujukan dibatalkan");
                            System.out.println("============================================");
                            menuSurat();
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

    public static void suratPulang(List<List<String>> dataSuratPulang) {
        String nomor = "";
        if (suratPulang.size() == 0) {
            nomor = "001";
        } else {
            String lastData = suratPulang.get(suratPulang.size() - 1);
            int increment = Integer.parseInt(lastData) + 1;
            String result = Integer.toString(increment);

            if (result.length() == 1) {
                nomor = "00" + result;
            } else if (result.length() == 2) {
                nomor = "0" + result;
            } else {
                nomor = result;
            }
        }

        System.out.println("\n============================================");
        System.out.println("=                SURAT PULANG              =");
        System.out.println("============================================");

        while (isMenuSuratPulang) {
            boolean idCheck = true;
            List<String> data = null;
            try {
                while (idCheck) {
                    System.out.print("Masukkan ID kunjungan yang akan dibuatkan surat pulang : ");
                    int idData = scanner.nextInt();
                    String id = Integer.toString(idData);

                    boolean isFound = false;
                    for (List<String> dataHasil : dataSuratPulang) {
                        if (dataHasil.get(0).equals(id)) {
                            if (dataHasil.get(10).equals("ya")) {
                                data = dataHasil;
                                idCheck = false;
                                isFound = true;
                            } else {
                                System.out.println("============================================");
                                System.out.println("Invalid status, status pulang tidak valid");
                                System.out.println("============================================");
                                scanner.nextLine();
                                menuSurat();
                            }
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
                }
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("============================================");
                System.out.println("ID tidak valid, masukkan ID yang valid");
                System.out.println("============================================");
                scanner.nextLine();
            }

            boolean cetak = true;
            while (cetak) {
                System.out.println("============================================");
                System.out.println("ID            : " + data.get(0));
                System.out.println("Nama          : " + data.get(1));
                System.out.println("Kelas         : " + data.get(2));
                System.out.println("Keluhan       : " + data.get(3));
                System.out.println("Tindakan      : " + data.get(4));
                System.out.println("Obat          : " + data.get(5));
                System.out.println("Tanggal Masuk : " + data.get(6));
                System.out.println("Waktu Masuk   : " + data.get(7));
                System.out.println("Waktu Keluar  : " + data.get(8));
                System.out.println("============================================");

                String tanggalDibuat = getCurrentDate();

                boolean checkInput = true;
                while (checkInput) {
                    System.out.print("Apakah anda yakin ingin mencetak data ini? (ya/tidak) : ");
                    String cetakPdf = scanner.nextLine();

                    switch (cetakPdf.toLowerCase()) {
                        case "ya":
                            try {
                                String fileName = data.get(0) + "_" + data.get(1) + "_" + data.get(2) + "_" + System.currentTimeMillis() + ".pdf";
                                // Membuat PdfWriter dan PdfDocument
                                PdfWriter writer = new PdfWriter("C:\\Surat_Pulang_UKS_SMA_Angkasa\\" + fileName);
                                PdfDocument pdf = new PdfDocument(writer);

                                // Membuat dokumen layout untuk menambahkan elemen
                                Document document = new Document(pdf);

                                // Membuat font dengan menangani IOException
                                PdfFont font = PdfFontFactory.createFont();  // font default

                                // Menambahkan judul surat
                                document.add(new Paragraph("Surat Keterangan Pulang")
                                        .setTextAlignment(TextAlignment.CENTER)
                                        .setFont(font)
                                        .setFontSize(18));

                                document.add(new Paragraph("\nNomor Surat: " + nomor + "/UKS/2025")
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));
                                document.add(new Paragraph("Tanggal: " + tanggalDibuat)
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));

                                // Mengubah bagian tujuan surat untuk orang tua/wali siswa
                                document.add(new Paragraph("\nKepada Yth.")
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));
                                document.add(new Paragraph("Orang Tua/Wali Siswa")
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));
                                document.add(new Paragraph("Di Tempat")
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));

                                document.add(new Paragraph("\nDengan hormat,")
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));

                                document.add(new Paragraph("Dengan ini kami menyatakan bahwa siswa kami yang bernama : \n"
                                        + "Nama    : " + data.get(1) + "\n"
                                        + "Kelas   : " + data.get(2) + "\n"
                                        + "Keluhan : " + data.get(3) + "\n"
                                        + "telah mengikuti pemeriksaan di Unit Kesehatan Sekolah (UKS) dan dinyatakan dapat kembali ke rumah untuk beristirahat.\n\n"
                                        + "Demikian surat keterangan pulang ini kami buat dengan sebenar-benarnya. Terima kasih atas perhatian dan kerjasamanya.\n\n\n\n")
                                        .setTextAlignment(TextAlignment.LEFT)
                                        .setFont(font)
                                        .setFontSize(12));

                                // Membuat tabel untuk tanda tangan
                                Table table = new Table(1); // 2 kolom untuk tanda tangan kiri dan kanan

                                // Menambahkan kolom kanan untuk tanda tangan wali kelas
                                table.addCell(new Cell().add(new Paragraph("Mengetahui\n\n\n\n\nWali Kelas " + data.get(2))
                                        .setTextAlignment(TextAlignment.CENTER)
                                        .setFont(font)
                                        .setFontSize(12))
                                        .setBorder(Border.NO_BORDER));

                                table.setHorizontalAlignment(HorizontalAlignment.CENTER);

                                // Menambahkan tabel tanda tangan ke dokumen
                                document.add(table); // Menutup dokumen PDF
                                document.close();

                                System.out.println("============================================");
                                System.out.println("Surat Pulang Berhasil dibuat");
                                System.out.println("============================================");
                                System.out.println("Lokasi file : C:\\Surat_Rujukan_UKS_SMA_Angkasa\\" + fileName);

                                suratPulang.add(nomor);

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            checkInput = false;
                            menuSurat();
                            break;
                        case "tidak":
                            System.out.println("============================================");
                            System.out.println("Pembuatan surat pulang dibatalkan");
                            System.out.println("============================================");
                            menuSurat();
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
