import java.text.NumberFormat;
import java.util.Locale;

abstract class Produk implements IStokManajemen {
    private String kode;
    private String nama;
    private double harga;
    protected int stok;
    protected KategoriProduk kategori;
    protected Supplier supplier;
    String status;

    public Produk(String kode, String nama, double harga, int stok, Supplier supplier, KategoriProduk kategori) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
        this.supplier = supplier;
        this.kategori = kategori;
    }

    public String getKode() { return kode; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public int getStok() { return stok; }
    public void setNama(String nama) { this.nama = nama; }

    protected String formatRupiah(double nominal) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return nf.format(nominal);
    }

    public abstract String toFileString();
}