package hungldph26293.fpoly.pnlib.DTO;

public class ThanhVien {
    private int maTV;
    private String tenTV;
    private int namSinh;

    public ThanhVien(int maTV, String tenTV, int namSinh) {
        this.maTV = maTV;
        this.tenTV = tenTV;
        this.namSinh = namSinh;
    }

    public ThanhVien() {

    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }
}
