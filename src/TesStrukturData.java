import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * File uji untuk struktur data buatan Ajeng. BUKAN program utama kelompok.
 * Cara jalan: java TesStrukturData matakuliah.csv prasyarat.csv
 */
public class TesStrukturData {
    public static void main(String[] args) {
        String fileMK = "matakuliah.csv";
        String filePra = "prasyarat.csv";
        if (args.length >= 2) {
            fileMK = args[0];
            filePra = args[1];
        }

        LinkedListMK daftarMK = new LinkedListMK();
        LinkedListPrasyarat daftarPra = new LinkedListPrasyarat();

        try {
            // Baca matakuliah.csv (pemisah ;, baris pertama header)
            BufferedReader br = new BufferedReader(new FileReader(fileMK));
            String baris = br.readLine();
            baris = br.readLine();
            while (baris != null) {
                baris = baris.trim();
                if (!baris.equals("")) {
                    String[] p = baris.split(";");
                    daftarMK.tambahMK(new MataKuliah(p[0].trim(), p[1].trim(),
                            Integer.parseInt(p[2].trim()), Integer.parseInt(p[3].trim()),
                            p[4].trim()));
                }
                baris = br.readLine();
            }
            br.close();

            // Baca prasyarat.csv (kolom 1 = syarat, kolom 2 = MK yang membutuhkan)
            br = new BufferedReader(new FileReader(filePra));
            baris = br.readLine();
            baris = br.readLine();
            while (baris != null) {
                baris = baris.trim();
                if (!baris.equals("")) {
                    String[] p = baris.split(";");
                    daftarPra.tambahPrasyarat(p[0].trim(), p[1].trim());
                }
                baris = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Gagal membaca file: " + e.getMessage());
            return;
        }

        System.out.println("Jumlah MK       : " + daftarMK.getSize());
        System.out.println("Jumlah prasyarat: " + daftarPra.getSize());

        System.out.println("\n--- LinkedListMK ---");
        System.out.println("cariMK(ASD)  : " + daftarMK.cariMK("ASD"));
        System.out.println("cariMK(XXX)  : " + daftarMK.cariMK("XXX"));
        System.out.println("hapusMK(RPL) : " + daftarMK.hapusMK("RPL"));
        System.out.println("hapusMK(SKR) : " + daftarMK.hapusMK("SKR"));
        System.out.println("hapusMK(XXX) : " + daftarMK.hapusMK("XXX"));
        System.out.println("Jumlah MK    : " + daftarMK.getSize());
        daftarMK.tambahMK(new MataKuliah("TES", "MK Percobaan", 2, 1, "Ganjil"));
        System.out.println("tambah TES   : " + daftarMK.cariMK("TES"));

        System.out.println("\n--- LinkedListPrasyarat ---");
        System.out.println("Syarat PAW : " + daftarPra.getSyaratLangsung("PAW"));
        System.out.println("Syarat IMK : " + daftarPra.getSyaratLangsung("IMK"));
        System.out.println("Syarat BD  : [" + daftarPra.getSyaratLangsung("BD") + "] (kosong = tanpa syarat)");
        System.out.println("punyaSyarat(CAP): " + daftarPra.punyaSyarat("CAP"));

        System.out.println("\n--- LinkedListKode (daftar lulus) ---");
        LinkedListKode lulus = new LinkedListKode();
        lulus.tambahKode("RPL");
        lulus.tambahKode("PDAS");
        System.out.println("tambah RPL lagi : " + lulus.tambahKode("RPL") + " (false = tidak dobel)");
        System.out.println("adaKode(PDAS)   : " + lulus.adaKode("PDAS"));
        System.out.println("adaKode(SO)     : " + lulus.adaKode("SO"));
        System.out.println("isi             : " + lulus);
        lulus.hapusKode("RPL");
        System.out.println("setelah hapus RPL: " + lulus + " (size " + lulus.getSize() + ")");
    }
}