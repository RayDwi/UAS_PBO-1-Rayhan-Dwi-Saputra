class Vitamin extends Produk {
    private String dosis;

    public Vitamin(String kode, String nama, double harga, int stok, Supplier sup, String dosis) {
        super(kode, nama, harga, stok, sup, KategoriProduk.OBAT_OBATAN);
        this.dosis = dosis;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("| %-6s | %-15s | %-12s | %-4d | %-13s | %-8s |\n",
                getKode(), (getNama().length() > 15 ? getNama().substring(0,12)+".." : getNama()), 
                formatRupiah(getHarga()), stok, kategori.getLabel(), dosis);
    }

    @Override
    public void updateStok(int jumlah) { this.stok += jumlah; }

    @Override
    public String toFileString() {
        return "VITAMIN;" + getKode() + ";" + getNama() + ";" + getHarga() + ";" + stok + ";" + dosis + ";" + kategori.name();
    }
}