/**
 * Class NodePrasyarat
 * Menyimpan satu baris prasyarat.csv:
 *   syarat;matakuliah  -> "syarat" harus lulus dulu sebelum mengambil "matakuliah"
 * Contoh: KAL;AL berarti KAL adalah syarat AL.
 * Dibuat oleh: Ajeng
 */
public class NodePrasyarat {
    String syarat;       // kode MK yang harus lulus lebih dulu
    String matakuliah;   // kode MK yang membutuhkan syarat tersebut
    NodePrasyarat next;

    public NodePrasyarat(String syarat, String matakuliah) {
        this.syarat = syarat;
        this.matakuliah = matakuliah;
        this.next = null;
    }
}