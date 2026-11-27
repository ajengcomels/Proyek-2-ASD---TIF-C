/**
 * Main - kerangka program utama untuk menggabungkan bagian semua anggota.
 * Dibuat oleh: Ajeng (integrasi)
 *
 * Cara jalan:
 *   java Main matakuliah.csv prasyarat.csv mhs_01.txt
 *
 * File ini baru KERANGKA. Bagian bertanda TODO diisi setelah class
 * Refi, Ulfa, dan Zabrina selesai. Nama class/method mengikuti pembagian kelompok.
 */
public class Main {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Cara pakai: java Main <matakuliah.csv> <prasyarat.csv> <mhs_xx.txt>");
            return;
        }
        String fileMK = args[0];
        String filePrasyarat = args[1];
        String fileMhs = args[2];

        // Penyimpanan data utama (struktur data buatan Ajeng)
        LinkedListMK daftarMK = new LinkedListMK();
        LinkedListPrasyarat daftarPrasyarat = new LinkedListPrasyarat();
        LinkedListKode daftarLulus = new LinkedListKode();

        prosesRekomendasi(fileMK, filePrasyarat, fileMhs, daftarMK, daftarPrasyarat, daftarLulus);
    }

    /**
     * Mengatur seluruh alur. Penanggung jawab: Zabrina dan Ajeng.
     * Urutan kerja:
     *  1. Refi   : bacaFileMataKuliah -> parseDataMataKuliah -> simpanDataMataKuliah (isi daftarMK)
     *  2. Ulfa   : bacaFilePrasyarat  -> parseDataPrasyarat (isi daftarPrasyarat)
     *  3. Zabrina: bacaFileMahasiswa  -> parseDataMahasiswa (isi daftarLulus + semester berikutnya)
     *  4. Telusuri daftarMK dari getHead(), untuk setiap MK:
     *       a. lewati jika sudah lulus                  -> daftarLulus.adaKode(kode)
     *       b. lewati jika tidak ditawarkan semester berikutnya (Refi: cekSemesterDitawarkan;
     *          nilai "Keduanya" dianggap selalu ditawarkan)
     *       c. aturan CAP, PKL, SKR (lihat catatan di bawah)
     *       d. cek prasyarat (Ulfa: cekPrasyarat) -> masuk daftar "bisa diambil"
     *          atau "belum bisa diambil" beserta alasannya (Ulfa: getAlasanBelumBisaDiambil)
     *  5. tampilkanHasil
     *
     * Catatan aturan CAP, PKL, SKR (kesepakatan kelompok):
     *   - CAP dan PKL hanya tampil di daftar "bisa diambil" jika SEMUA MK semester 1-5 sudah lulus.
     *     Jika belum, tidak ditampilkan di kedua daftar.
     *   - SKR hanya bisa diambil jika SEMUA MK wajib lain sudah lulus.
     */
    public static void prosesRekomendasi(String fileMK, String filePrasyarat, String fileMhs,
            LinkedListMK daftarMK, LinkedListPrasyarat daftarPrasyarat, LinkedListKode daftarLulus) {
        // TODO: sambungkan method Refi, Ulfa, dan Zabrina sesuai urutan di atas

        // method PengolahMataKuliah 
        PengolahMataKuliah pengolahMK = new PengolahMataKuliah(daftarMK);
        pengolahMK.bacaFileMataKuliah(fileMK);
    }

    /**
     * Menampilkan hasil akhir. Penanggung jawab: Zabrina.
     * Format mengikuti contoh dosen:
     *   Daftar MK yang bisa diambil:
     *   1. SO Sistem Operasi 5 SKS
     *   ...
     *   Daftar MK yang belum bisa diambil semester depan:
     *   PAW: belum lulus SO, IMK
     */
    public static void tampilkanHasil() {
        // TODO: diisi Zabrina
    }
}
