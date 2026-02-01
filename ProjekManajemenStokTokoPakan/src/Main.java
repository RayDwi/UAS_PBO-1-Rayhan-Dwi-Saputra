import java.util.*;
import java.io.*;

public class Main {
    private static List<Produk> database = new ArrayList<>();
    private static final String FILE_NAME = "data_toko.txt";
    private static Scanner scanner = new Scanner(System.in);
    private static Supplier supplierUtama = new Supplier("Global Feed");

    public static void main(String[] args) {
        loadData();
        int pilihan = 0;
        do {
            try {
                System.out.println("\n==============================");
            System.out.println("    TOKO PAKAN DUA PUTRA");
            System.out.println("==============================");
            System.out.println("1. Tambah Data");
            System.out.println("2. Tampilkan Data");
            System.out.println("3. Cari Data");
            System.out.println("4. Ubah Data");
            System.out.println("5. Cek Status Data");// Menu sesuai poin 4 di PDF 
            System.out.println("6. Keluar");
            System.out.print("Pilih Menu: ");
                pilihan = Integer.parseInt(scanner.nextLine());

                switch (pilihan) {
                    case 1 -> tambahData();
                    case 2 -> tampilkanSemua();
                    case 3 -> cariData();
                    case 4 -> ubahData();
                    case 5 -> cekStatus();
                    case 6 -> { simpanData(); System.out.println("Selesai."); }
                    default -> System.out.println("Pilihan salah!");
                }
            } catch (Exception e) { System.out.println("Input Error!"); }
        } while (pilihan != 6);
    }

    private static void tambahData() {
        System.out.print("Kode: "); String k = scanner.nextLine();
        System.out.print("Nama: "); String n = scanner.nextLine();
        System.out.print("Harga: "); double h = Double.parseDouble(scanner.nextLine());
        System.out.print("Stok: "); int s = Integer.parseInt(scanner.nextLine());
        
        System.out.println("Kategori: 1.Ayam 2.Ikan 3.Burung 4.Obat");
        int pil = Integer.parseInt(scanner.nextLine());

        if (pil == 4) {
            System.out.print("Dosis: "); String d = scanner.nextLine();
            database.add(new Vitamin(k, n, h, s, supplierUtama, d));
        } else {
            System.out.print("Berat (kg): "); double b = Double.parseDouble(scanner.nextLine());
            KategoriProduk kat = (pil == 2) ? KategoriProduk.PAKAN_IKAN : (pil == 3) ? KategoriProduk.PAKAN_BURUNG : KategoriProduk.PAKAN_AYAM;
            database.add(new Pakan(k, n, h, s, supplierUtama, b, kat));
        }
        simpanData();
    }

    private static void tampilkanSemua() {
        System.out.println("+--------+-----------------+--------------+------+---------------+----------+");
        System.out.println("| KODE   | NAMA PRODUK     | HARGA        | STOK | KATEGORI      | INFO     |");
        System.out.println("+--------+-----------------+--------------+------+---------------+----------+");
        for (Produk p : database) p.tampilkanInfo();
        System.out.println("+--------+-----------------+--------------+------+---------------+----------+");
    }

    private static void cekStatus() {
        System.out.println("\n--- LAPORAN STOK KRITIS ---");
        for (Produk p : database) {
            if (p.getStok() < 10) {
                System.out.println("⚠️ " + p.getNama() + " | Sisa: " + p.getStok() + " (" + p.kategori.getLabel() + ")");
            }
        }
    }

    private static void simpanData() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Produk p : database) pw.println(p.toFileString());
        } catch (IOException e) { System.out.println("Gagal simpan!"); }
    }

    private static void loadData() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] d = line.split(";");
                if (d[0].equals("PAKAN")) {
                    database.add(new Pakan(d[1], d[2], Double.parseDouble(d[3]), Integer.parseInt(d[4]), supplierUtama, Double.parseDouble(d[5]), KategoriProduk.valueOf(d[6])));
                } else {
                    database.add(new Vitamin(d[1], d[2], Double.parseDouble(d[3]), Integer.parseInt(d[4]), supplierUtama, d[5]));
                }
            }
        } catch (Exception e) { /* File belum ada */ }
    }

    private static void cariData() {
        System.out.print("Masukkan Kode: "); String c = scanner.nextLine();
        for (Produk p : database) if (p.getKode().equalsIgnoreCase(c)) p.tampilkanInfo();
    }

    private static void ubahData() {
        System.out.print("Kode yg diubah: "); String k = scanner.nextLine();
        for (Produk p : database) {
            if (p.getKode().equalsIgnoreCase(k)) {
                System.out.print("Nama Baru: "); p.setNama(scanner.nextLine());
                simpanData(); return;
            }
        }
    }
}