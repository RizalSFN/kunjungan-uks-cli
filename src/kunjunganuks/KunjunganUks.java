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
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.TimeZone;
import static kunjunganuks.Kunjungan.menuKunjunganUks;
import static kunjunganuks.Surat.menuSurat;

/**
 *
 * @author RIZAL
 */
public class KunjunganUks {

    /**
     * @param args the command line arguments
     */
    static boolean isLogin = false;
    static boolean isMenuUtama = true;

    static ArrayList<List<String>> kunjungan = new ArrayList<>();
    static List<String> suratRujukan = new ArrayList<>();
    static List<String> suratPulang = new ArrayList<>();
    static ArrayList<List<String>> rekap = new ArrayList<>();

    static Scanner scanner = new Scanner(System.in);

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

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

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
        System.out.println("2. Surat & Rekap");
        System.out.println("3. Logout");
        System.out.println("4. Keluar dari program");
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
                        menuSurat();
                        break;
                    case 3:
                        isLogin = false;
                        System.out.println("============================================");
                        System.out.println("Logout berhasil!");
                        System.out.println("============================================");
                        scanner.nextLine();
                        index();
                        break;
                    case 4:
                        System.exit(0);
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
}
