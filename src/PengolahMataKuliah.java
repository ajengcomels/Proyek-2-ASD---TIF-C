import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PengolahMataKuliah {

    private LinkedListMK daftarMK;

    public PengolahMataKuliah() {
        this.daftarMK = new LinkedListMK();
    }

    public PengolahMataKuliah(LinkedListMK daftarMK) {
        this.daftarMK = daftarMK;
    }

    // 1. Membaca file matakuliah.csv baris demi baris
    public boolean bacaFileMataKuliah(String namaFile) {
        try (BufferedReader br = new BufferedReader(new FileReader(namaFile))) {
            String baris = br.readLine(); 
            baris = br.readLine();

            while (baris != null) {
                baris = baris.trim();
                if (!baris.isEmpty()) {
                    MataKuliah mk = parseDataMataKuliah(baris);
                    if (mk != null) {
                        simpanDataMataKuliah(mk);
                    }
                }
                baris = br.readLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Gagal membaca file " + namaFile + ": " + e.getMessage());
            return false;
        }
    }

    // 2. Memisahkan 1 baris CSV (pemisah ;) menjadi objek MataKuliah
    public MataKuliah parseDataMataKuliah(String baris) {
        String[] data = baris.split(";");
        if (data.length < 5) {
            return null;
        }

        String kode = data[0].trim();
        String nama = data[1].trim();
        int sks = Integer.parseInt(data[2].trim());
        int semester = Integer.parseInt(data[3].trim());
        String ganjilGenap = data[4].trim();

        return new MataKuliah(kode, nama, sks, semester, ganjilGenap);
    }

    // 3. Menyimpan objek MataKuliah ke LinkedListMK buatan Ajeng
    public boolean simpanDataMataKuliah(MataKuliah mk) {
        if (mk == null) {
            return false;
        }
        return daftarMK.tambahMK(mk);
    }

    // 4a. Mengecek apakah objek MataKuliah ditawarkan di semester berikutnya
    public boolean cekSemesterDitawarkan(MataKuliah mk, String semesterBerikutnya) {
        if (mk == null || semesterBerikutnya == null) {
            return false;
        }

        String jadwal = mk.getGanjilGenap().trim();
        if (jadwal.equalsIgnoreCase("Keduanya")) {
            return true;
        }
        return jadwal.equalsIgnoreCase(semesterBerikutnya.trim());
    }

    // 4b. Overload: Mengecek berdasarkan kode MK 
    public boolean cekSemesterDitawarkan(String kodeMK, String semesterBerikutnya) {
        MataKuliah mk = daftarMK.cariMK(kodeMK);
        return cekSemesterDitawarkan(mk, semesterBerikutnya);
    }
}
