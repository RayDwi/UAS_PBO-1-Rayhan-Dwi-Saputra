enum KategoriProduk {
    PAKAN_AYAM("Pakan Ayam"),
    PAKAN_IKAN("Pakan Ikan"),
    PAKAN_BURUNG("Pakan Burung"),
    OBAT_OBATAN("Obat-obatan");

    private String label;

    KategoriProduk(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}