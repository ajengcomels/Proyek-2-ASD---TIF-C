/**
 * Class MataKuliah
 * Menyimpan data satu mata kuliah (sesuai kolom di matakuliah.csv).
 * Dibuat oleh: Ajeng
 */
public class MataKuliah {
    private String kode;        // contoh: ASD
    private String nama;        // contoh: Algoritma dan Struktur Data
    private int sks;            // contoh: 3
    private int semester;       // contoh: 3
    private String ganjilGenap; // "Ganjil", "Genap", atau "Keduanya"

    // Constructor untuk membuat objek mata kuliah
    public MataKuliah(String kode, String nama, int sks, int semester, String ganjilGenap) {
        this.kode = kode;
        this.nama = nama;
        this.sks = sks;
        this.semester = semester;
        this.ganjilGenap = ganjilGenap;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getSks() {
        return sks;
    }

    public int getSemester() {
        return semester;
    }

    public String getGanjilGenap() {
        return ganjilGenap;
    }

    // Format tampilan sesuai contoh output di instruksi: "SO Sistem Operasi 5 SKS"
    public String toString() {
        return kode + " " + nama + " " + sks + " SKS";
    }
}