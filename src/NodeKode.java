/**
 * Class NodeKode
 * Satu node untuk menyimpan sebuah kode MK (String), misalnya "SO".
 * Dibuat oleh: Ajeng
 */
public class NodeKode {
    String kode;    // kode mata kuliah
    NodeKode next;  // penunjuk ke node selanjutnya

    public NodeKode(String kode) {
        this.kode = kode;
        this.next = null;
    }
}