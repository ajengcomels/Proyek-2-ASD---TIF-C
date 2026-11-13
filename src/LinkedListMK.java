/**
 * Class LinkedListMK
 * Struktur data Single Linked List untuk menyimpan data mata kuliah.
 * Dibuat sendiri tanpa memakai struktur data bawaan Java (ArrayList, LinkedList, dll).
 * Dibuat oleh: Ajeng
 */
public class LinkedListMK {
    private NodeMK head; // node pertama
    private NodeMK tail; // node terakhir (supaya tambah di belakang cepat, O(1))
    private int size;    // jumlah mata kuliah

    // Constructor: list kosong
    public LinkedListMK() {
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

    // Dipakai anggota lain untuk menelusuri list (traversal)
    public NodeMK getHead() {
        return head;
    }

    // Menambahkan data mata kuliah di akhir list.
    // Return false jika kode sudah ada (supaya tidak dobel).
    public boolean tambahMK(MataKuliah mk) {
        if (cariMK(mk.getKode()) != null) {
            return false;
        }
        NodeMK baru = new NodeMK(mk);
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

    // Mencari mata kuliah berdasarkan kode MK.
    // Return objek MataKuliah, atau null jika tidak ditemukan.
    public MataKuliah cariMK(String kode) {
        NodeMK bantu = head;
        while (bantu != null) {
            if (bantu.data.getKode().equalsIgnoreCase(kode)) {
                return bantu.data;
            }
            bantu = bantu.next;
        }
        return null;
    }

    // Menghapus mata kuliah berdasarkan kode MK.
    // Return true jika berhasil dihapus, false jika kode tidak ditemukan.
    public boolean hapusMK(String kode) {
        if (head == null) {
            return false;
        }

        // Kasus 1: yang dihapus node pertama
        if (head.data.getKode().equalsIgnoreCase(kode)) {
            head = head.next;
            if (head == null) {
                tail = null; // list jadi kosong
            }
            size--;
            return true;
        }

        // Kasus 2: yang dihapus di tengah atau akhir
        NodeMK sebelum = head;
        while (sebelum.next != null) {
            if (sebelum.next.data.getKode().equalsIgnoreCase(kode)) {
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

    // Menampilkan seluruh data mata kuliah
    public void tampilSemuaMK() {
        if (head == null) {
            System.out.println("Data mata kuliah kosong.");
            return;
        }
        NodeMK bantu = head;
        int no = 1;
        while (bantu != null) {
            System.out.println(no + ". " + bantu.data);
            bantu = bantu.next;
            no++;
        }
    }
}