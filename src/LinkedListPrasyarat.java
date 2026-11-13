/**
 * Class LinkedListPrasyarat
 * Linked List untuk menyimpan seluruh isi prasyarat.csv.
 * Ulfa memakai class ini di PengolahPrasyarat:
 *   - parseDataPrasyarat() memanggil tambahPrasyarat(syarat, matakuliah)
 *   - cekPrasyarat() dan getAlasanBelumBisaDiambil() memakai getSyaratLangsung(...)
 * Dibuat oleh: Ajeng
 */
public class LinkedListPrasyarat {
    private NodePrasyarat head;
    private NodePrasyarat tail;
    private int size;

    public LinkedListPrasyarat() {
        head = null;
        tail = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public NodePrasyarat getHead() {
        return head;
    }

    // Menambahkan satu pasangan (syarat -> matakuliah) di akhir list
    public void tambahPrasyarat(String syarat, String matakuliah) {
        NodePrasyarat baru = new NodePrasyarat(syarat, matakuliah);
        if (head == null) {
            head = baru;
            tail = baru;
        } else {
            tail.next = baru;
            tail = baru;
        }
        size++;
    }

    // Mengambil semua syarat LANGSUNG dari sebuah MK.
    // Contoh: getSyaratLangsung("PAW") -> berisi "IMK"
    // Jika MK tidak punya syarat, hasilnya list kosong (bukan null).
    public LinkedListKode getSyaratLangsung(String kodeMK) {
        LinkedListKode hasil = new LinkedListKode();
        NodePrasyarat bantu = head;
        while (bantu != null) {
            if (bantu.matakuliah.equalsIgnoreCase(kodeMK)) {
                hasil.tambahKode(bantu.syarat);
            }
            bantu = bantu.next;
        }
        return hasil;
    }

    // Memeriksa apakah sebuah MK punya syarat atau tidak
    public boolean punyaSyarat(String kodeMK) {
        NodePrasyarat bantu = head;
        while (bantu != null) {
            if (bantu.matakuliah.equalsIgnoreCase(kodeMK)) {
                return true;
            }
            bantu = bantu.next;
        }
        return false;
    }
}