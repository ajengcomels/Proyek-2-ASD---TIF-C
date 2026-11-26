import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PengolahPrasyarat {

    private LinkedListPrasyarat daftarPrasyarat;
    private StringBuilder dataMentah;
    private boolean sudahDiparse;
    private int jumlahDataTerakhir;

    public PengolahPrasyarat() {
        this(new LinkedListPrasyarat());
    }

    public PengolahPrasyarat(
            LinkedListPrasyarat daftarPrasyarat) {

        this.daftarPrasyarat = daftarPrasyarat;
        this.dataMentah = new StringBuilder();
        this.sudahDiparse = false;
        this.jumlahDataTerakhir = 0;
    }

    public boolean bacaFilePrasyarat() {
        return bacaFilePrasyarat("prasyarat.csv");
    }

    public boolean bacaFilePrasyarat(String namaFile) {

        dataMentah.setLength(0);
        sudahDiparse = false;
        jumlahDataTerakhir = 0;

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(namaFile))) {

            String baris;

            while ((baris = reader.readLine()) != null) {
                dataMentah.append(baris).append("\n");
            }

            return true;

        } catch (IOException e) {
            System.out.println(
                "Gagal membaca file: " + e.getMessage()
            );
            return false;
        }
    }

    public int parseDataPrasyarat() {

        if (sudahDiparse) {
            return jumlahDataTerakhir;
        }

        if (dataMentah.length() == 0) {
            return 0;
        }

        int jumlahData = 0;

        String[] barisData =
            dataMentah.toString().split("\\r?\\n");

        for (int i = 0; i < barisData.length; i++) {

            String baris = barisData[i].trim();

            if (baris.isEmpty() ||
                baris.equalsIgnoreCase("prasyarat;matakuliah")) {
                continue;
            }

            String[] kolom = baris.split(";");

            if (kolom.length != 2) {
                continue;
            }

            String syarat = kolom[0].trim();
            String matakuliah = kolom[1].trim();

            if (syarat.isEmpty() || matakuliah.isEmpty()) {
                continue;
            }

            // Menyimpan hubungan ke Linked List Ajeng
            daftarPrasyarat.tambahPrasyarat(
                syarat, matakuliah
            );

            jumlahData++;
        }

        sudahDiparse = true;
        jumlahDataTerakhir = jumlahData;

        return jumlahData;
    }

    public boolean cekPrasyarat(
            String kodeMK, LinkedListKode daftarLulus) {

        LinkedListKode alasan =
            getAlasanBelumBisaDiambil(kodeMK, daftarLulus);

        return alasan.isEmpty();
    }

    public LinkedListKode getAlasanBelumBisaDiambil(
            String kodeMK, LinkedListKode daftarLulus) {

        LinkedListKode alasan = new LinkedListKode();
        LinkedListKode sudahDiperiksa = new LinkedListKode();

        kumpulkanAlasan(
            kodeMK,
            daftarLulus,
            alasan,
            sudahDiperiksa
        );

        return alasan;
    }

    private void kumpulkanAlasan(
            String kodeMK,
            LinkedListKode daftarLulus,
            LinkedListKode alasan,
            LinkedListKode sudahDiperiksa) {

        LinkedListKode syarat =
            daftarPrasyarat.getSyaratLangsung(kodeMK);

        NodeKode bantu = syarat.getHead();

        while (bantu != null) {

            String kodeSyarat = bantu.kode;

            if (!sudahDiperiksa.adaKode(kodeSyarat)) {

                sudahDiperiksa.tambahKode(kodeSyarat);

                if (daftarLulus == null ||
                    !daftarLulus.adaKode(kodeSyarat)) {

                    kumpulkanAlasan(
                        kodeSyarat,
                        daftarLulus,
                        alasan,
                        sudahDiperiksa
                    );

                    alasan.tambahKode(kodeSyarat);
                }
            }

            bantu = bantu.next;
        }
    }
}
