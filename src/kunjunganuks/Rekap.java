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
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import java.io.IOException;
import java.util.List;
import static kunjunganuks.KunjunganUks.kunjungan;
import static kunjunganuks.KunjunganUks.rekap;
import static kunjunganuks.KunjunganUks.scanner;
import static kunjunganuks.Surat.endDate;
import static kunjunganuks.Surat.menuSurat;
import static kunjunganuks.Surat.startDate;

/**
 *
 * @author RIZAL
 */
public class Rekap {
    
    public static void rekapKunjunganPerMinggu(String tanggalAwal, String tanggalAkhir) {
        for (List<String> kunjunganData : kunjungan) {
            String tanggal = kunjunganData.get(7);
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
                    table.addCell(kunjunganData.get(7)); // Tanggal Masuk
                    table.addCell(kunjunganData.get(10).toUpperCase()); // Dirujuk
                    table.addCell(kunjunganData.get(11).toUpperCase()); // Pulang
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
