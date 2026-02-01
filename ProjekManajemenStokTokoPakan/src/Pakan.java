class Pakan extends Produk {
    private double berat;

    public Pakan(String kode, String nama, double harga, int stok, Supplier sup, double berat, KategoriProduk kat) {
        super(kode, nama, harga, stok, sup, kat);
        this.berat = berat;
    }

    @Override
    public void tampilkanInfo() {
        System.out.printf("| %-6s | %-15s | %-12s | %-4d | %-13s | %-8s |\n",
                getKode(), (getNama().length() > 15 ? getNama().substring(0,12)+".." : getNama()), 
                formatRupiah(getHarga()), stok, kategori.getLabel(), berat + " kg");
    }

    @Override
    public void updateStok(int jumlah) { this.stok += jumlah; }

    @Override
    public String toFileString() {
        return "PAKAN;" + getKode() + ";" + getNama() + ";" + getHarga() + ";" + stok + ";" + berat + ";" + kategori.name();
    }
}