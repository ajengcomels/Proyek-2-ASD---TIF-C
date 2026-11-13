/**
 * Class NodeMK
 * Satu node pada Linked List. Berisi data MataKuliah dan penunjuk ke node berikutnya.
 * Dibuat oleh: Ajeng
 */
public class NodeMK {
    MataKuliah data; // data mata kuliah yang disimpan
    NodeMK next;     // penunjuk ke node selanjutnya

    // Constructor untuk membuat node
    public NodeMK(MataKuliah data) {
        this.data = data;
        this.next = null;
    }
}