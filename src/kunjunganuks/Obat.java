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

import static kunjunganuks.KunjunganUks.dataObat;
import static kunjunganuks.KunjunganUks.index;
import static kunjunganuks.KunjunganUks.isLogin;
import static kunjunganuks.KunjunganUks.menuUtama;
import static kunjunganuks.KunjunganUks.scanner;

/**
 *
 * @author RIZAL
 */
public class Obat {

    static boolean isMenuObatUks = true;
    static boolean isMenuTambahObat = true;
    static boolean isMenuEditObat = false;
    static boolean isMenuCariObat = true;
    static boolean isMenuHapusObat = false;

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

}
