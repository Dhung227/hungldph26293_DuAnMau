package hungldph26293.fpoly.pnlib.DTO;

public class LoaiSach {
    private int maLS;
    private String tenLS;

    public LoaiSach(int maLS, String tenLS) {
        this.maLS = maLS;
        this.tenLS = tenLS;
    }

    public LoaiSach(String tenLS) {
        this.tenLS = tenLS;
    }

    public LoaiSach() {

    }

    public int getMaLS() {
        return maLS;
    }

    public void setMaLS(int maLS) {
        this.maLS = maLS;
    }

    public String getTenLS() {
        return tenLS;
    }

    public void setTenLS(String tenLS) {
        this.tenLS = tenLS;
    }
}
