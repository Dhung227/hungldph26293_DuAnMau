package hungldph26293.fpoly.pnlib.DTO;

public class ThuThu {
    private String maTT;
    private String tenTT;
    private String matKhau;
    private String chucVu;

    public ThuThu(String maTT, String tenTT, String matKhau, String chucVu) {
        this.maTT = maTT;
        this.tenTT = tenTT;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }

    public ThuThu() {

    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}
