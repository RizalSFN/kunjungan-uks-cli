/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kunjunganuks;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.InputMismatchException;
import java.util.List;

import static kunjunganuks.KunjunganUks.getCurrentDate;
import static kunjunganuks.KunjunganUks.index;
import static kunjunganuks.KunjunganUks.isLogin;
import static kunjunganuks.KunjunganUks.scanner;
import static kunjunganuks.Kunjungan.isMenuCariKunjungan;
import static kunjunganuks.Kunjungan.isMenuEditKunjungan;
import static kunjunganuks.Kunjungan.isMenuHapusKunjungan;
import static kunjunganuks.KunjunganUks.kunjungan;
import static kunjunganuks.KunjunganUks.menuUtama;
import static kunjunganuks.KunjunganUks.rekap;
import static kunjunganuks.KunjunganUks.suratPulang;
import static kunjunganuks.KunjunganUks.suratRujukan;



/*
TODO :
1. Mengatur width tabel rekap jadi 100% / full - SOLVED
2. Mengatur nama menu surat rujukan & pulang menjadi surat & rekap - SOLVED
3. Mengatur tanggal yang tampil pada rekap menjadi (misalnya : 01 Januari 2025) - SOLVED
*/

/**
 *
 * @author RIZAL
 */
public class Surat {

    static boolean isMenuSurat = true;
    static boolean isMenuSuratRujukan = false;
    static boolean isMenuSuratPulang = false;
    static String tanggalAwal = "";
    static String tanggalAkhir = "";
    static String startDate = "";
    static String endDate = "";

    public static void menuSurat() {
        System.out.println("\n============================================");
        System.out.println("=            MENU - MENU SURAT          =");
        System.out.println("============================================");
        System.out.println("1. Surat Rujukan");
        System.out.println("2. Surat Pulang");
        System.out.println("3. Rekap Kunjungan");
        System.out.println("4. Kembali");
        System.out.println("5. Logout");
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
                        Kunjungan.cariKunjungan();
                        break;
                    case 2:
                        isMenuSurat = false;
                        isMenuCariKunjungan = true;
                        isMenuEditKunjungan = false;
                        isMenuHapusKunjungan = false;
                        isMenuSuratRujukan = false;
                        isMenuSuratPulang = true;
                        scanner.nextLine();
                        Kunjungan.cariKunjungan();
                        break;
                    case 3:
                        scanner.nextLine();
                        rekap();
                        break;
                    case 4:
                        menuUtama();
                        break;
                    case 5:
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
                                System.out.println("Lokasi file : C:\\Surat_Pulang_UKS_SMA_Angkasa\\" + fileName);

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

    public static void rekap() {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormatNew = new SimpleDateFormat("dd MMMM yyyy");
        
        while (true) {
            System.out.println("============================================");
            System.out.print("Masukkan tanggal awal (yyyy-mm-dd) : ");
            tanggalAwal = scanner.nextLine();

            try {
                dateFormat.parse(tanggalAwal);
                Date date = dateFormat.parse(tanggalAwal);
                startDate = dateFormatNew.format(date);
                break;
            } catch (Exception e) {
                System.out.println("============================================");
                System.out.println("Tanggal tidak valid, pastikan formatnya yyyy-mm-dd.");
            }
        }
        
        while (true) {
            System.out.println("============================================");
            System.out.print("Masukkan tanggal akhir (yyyy-mm-dd) : ");
            tanggalAkhir = scanner.nextLine();

            try {
                dateFormat.parse(tanggalAkhir);
                Date date = dateFormat.parse(tanggalAkhir);
                endDate = dateFormatNew.format(date);
                break;
            } catch (Exception e) {
                System.out.println("============================================");
                System.out.println("Tanggal tidak valid, pastikan formatnya yyyy-mm-dd.");
            }
        }

        rekapKunjunganPerMinggu(tanggalAwal, tanggalAkhir);
    }

    public static void rekapKunjunganPerMinggu(String tanggalAwal, String tanggalAkhir) {
        for (List<String> kunjunganData : kunjungan) {
            String tanggal = kunjunganData.get(6);
            if (isTanggalInRange(tanggal, tanggalAwal, tanggalAkhir)) {
                rekap.add(kunjunganData);
            }
        }
        
        if (rekap.size() > 0) {
            System.out.println("============================================");
            System.out.println("Membuat rekap data...");
            buatRekap(rekap);
        } else {
            System.out.println("============================================");
            System.out.println("Data kunjungan pada periode tersebut kosong");
            System.out.println("============================================");
            menuSurat();
        }
    }

    // Fungsi untuk memeriksa apakah tanggal dalam rentang yang diberikan
    public static boolean isTanggalInRange(String tanggal, String tanggalAwal, String tanggalAkhir) {
        return tanggal.compareTo(tanggalAwal) >= 0 && tanggal.compareTo(tanggalAkhir) <= 0;
    }

    public static void buatRekap(List<List<String>> dataRekap) {
        boolean periode = true;
        while (periode) {
            try {
                String fileName = "Kunjungan UKS (" + startDate + " - " + endDate + ").pdf";
                // Membuat PdfWriter dan PdfDocument
                PdfWriter writer = new PdfWriter("C:\\Rekap_Data_Kunjungan_UKS\\" + fileName);
                PdfDocument pdf = new PdfDocument(writer);

                // Membuat dokumen layout untuk menambahkan elemen
                Document document = new Document(pdf);

                // Membuat font dengan menangani IOException
                PdfFont font = PdfFontFactory.createFont();  // font default

                // Judul PDF
                document.add(new Paragraph("Rekap Kunjungan UKS")
                        .setFont(font)
                        .setTextAlignment(TextAlignment.CENTER)
                        .setFontSize(18));

                document.add(new Paragraph("\nPeriode : " + startDate + " s.d. " + endDate + "\n\n")
                        .setTextAlignment(TextAlignment.LEFT)
                        .setFont(font)
                        .setFontSize(12));

                // Membuat tabel untuk data kunjungan
                Table table = new Table(7);
                
                table.addHeaderCell("No");
                table.addHeaderCell("Nama Siswa");
                table.addHeaderCell("Kelas");
                table.addHeaderCell("Keluhan");
                table.addHeaderCell("Tanggal Masuk");
                table.addHeaderCell("Dirujuk");
                table.addHeaderCell("Pulang");

                int nomor = 1;
                // Menambahkan data ke dalam tabel
                for (List<String> kunjunganData : dataRekap) {
                    table.addCell(Integer.toString(nomor) + "."); // Nomor
                    table.addCell(kunjunganData.get(1)); // Nama Siswa
                    table.addCell(kunjunganData.get(2)); // Kelas
                    table.addCell(kunjunganData.get(3)); // Keluhan
                    table.addCell(kunjunganData.get(6)); // Tanggal Masuk
                    table.addCell(kunjunganData.get(9).toUpperCase()); // Dirujuk
                    table.addCell(kunjunganData.get(10).toUpperCase()); // Pulang
                    nomor++;
                }
                
                table.setWidth(UnitValue.createPercentValue(100));

                // Menambahkan tabel tanda tangan ke dokumen
                document.add(table); // Menutup dokumen PDF
                document.close();

                periode = false;
                System.out.println("============================================");
                System.out.println("Rekap data Berhasil dibuat");
                System.out.println("============================================");
                System.out.println("Lokasi file : C:\\Rekap_Data_Kunjungan_UKS\\" + fileName);
                menuSurat();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        scanner.nextLine();
    }
}
