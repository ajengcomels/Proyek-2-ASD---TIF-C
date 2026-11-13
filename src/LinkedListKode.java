/**
 * Class LinkedListKode
 * Linked List sederhana untuk menyimpan kumpulan kode MK.
 * Dipakai untuk:
 *  - daftar MK yang sudah lulus mahasiswa (Zabrina)
 *  - daftar syarat sebuah MK (Ulfa)
 *  - daftar alasan "belum lulus ..." (Ulfa)
 * Dibuat oleh: Ajeng
 */
public class LinkedListKode {
    private NodeKode head;
    private NodeKode tail;
    private int size;

    public LinkedListKode() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    // Untuk menelusuri list dari luar class
    public NodeKode getHead() {
        return head;
    }

    // Menambahkan kode di akhir list.
    // Return false jika kode sudah ada (tidak dobel).
    public boolean tambahKode(String kode) {
        if (adaKode(kode)) {
            return false;
        }
        NodeKode baru = new NodeKode(kode);
        if (head == null) {
            head = baru;
            tail = baru;
        } else {
            tail.next = baru;
            tail = baru;
        }
        size++;
        return true;
    }

    // Memeriksa apakah kode ada di dalam list
    public boolean adaKode(String kode) {
        NodeKode bantu = head;
        while (bantu != null) {
            if (bantu.kode.equalsIgnoreCase(kode)) {
                return true;
            }
            bantu = bantu.next;
        }
        return false;
    }

    // Menghapus kode dari list. Return true jika berhasil.
    public boolean hapusKode(String kode) {
        if (head == null) {
            return false;
        }
        if (head.kode.equalsIgnoreCase(kode)) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            size--;
            return true;
        }
        NodeKode sebelum = head;
        while (sebelum.next != null) {
            if (sebelum.next.kode.equalsIgnoreCase(kode)) {
                if (sebelum.next == tail) {
                    tail = sebelum;
                }
                sebelum.next = sebelum.next.next;
                size--;
                return true;
            }
            sebelum = sebelum.next;
        }
        return false;
    }

    // Hasil berupa teks dipisah koma, contoh: "SO, IMK"
    // Berguna untuk menampilkan alasan "belum lulus SO, IMK"
    public String toString() {
        String hasil = "";
        NodeKode bantu = head;
        while (bantu != null) {
            hasil = hasil + bantu.kode;
            if (bantu.next != null) {
                hasil = hasil + ", ";
            }
            bantu = bantu.next;
        }
        return hasil;
    }
}