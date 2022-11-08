package hungldph26293.fpoly.pnlib.DTO;

public class PhieuMuon {
    private int maPM;
    private String maTT;
    private int maTV;
    private int maS;
    private String ngayThue;
    private String ph26293gio;
    private int traSach;
    private int tienThue;
    private String tenTV;
    private String tenTT;
    private String tenS;

    public PhieuMuon( String maTT, int maTV, int maS, String ngayThue, String ph26293gio, int traSach, int tienThue) {
        this.maTT = maTT;
        this.maTV = maTV;
        this.maS = maS;
        this.ngayThue = ngayThue;
        this.ph26293gio = ph26293gio;
        this.traSach = traSach;
        this.tienThue = tienThue;
    }

    public PhieuMuon(int maS, int tienThue) {
        this.maS = maS;
        this.tienThue = tienThue;
    }

    public PhieuMuon() {

    }

    public PhieuMuon(int maPM, String maTT, String tenTT, int maTV, String tenTV, int maS, String tenS, String ngayThue, String ph26293gio, int traSach, int tienThue) {
        this.maPM = maPM;
        this.maTT = maTT;
        this.maTV = maTV;
        this.maS = maS;
        this.ngayThue = ngayThue;
        this.ph26293gio = ph26293gio;
        this.traSach = traSach;
        this.tienThue = tienThue;
        this.tenTV = tenTV;
        this.tenTT = tenTT;
        this.tenS = tenS;
    }

    public int getMaPM() {
        return maPM;
    }

    public void setMaPM(int maPM) {
        this.maPM = maPM;
    }

    public String getMaTT() {
        return maTT;
    }

    public void setMaTT(String maTT) {
        this.maTT = maTT;
    }

    public int getMaTV() {
        return maTV;
    }

    public void setMaTV(int maTV) {
        this.maTV = maTV;
    }

    public int getMaS() {
        return maS;
    }

    public void setMaS(int maS) {
        this.maS = maS;
    }

    public String getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(String ngayThue) {
        this.ngayThue = ngayThue;
    }

    public String getPh26293gio() {
        return ph26293gio;
    }

    public void setPh26293gio(String ph26293gio) {
        this.ph26293gio = ph26293gio;
    }

    public int getTraSach() {
        return traSach;
    }

    public void setTraSach(int traSach) {
        this.traSach = traSach;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int tienThue) {
        this.tienThue = tienThue;
    }

    public String getTenTV() {
        return tenTV;
    }

    public void setTenTV(String tenTV) {
        this.tenTV = tenTV;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
    }

    public String getTenS() {
        return tenS;
    }

    public void setTenS(String tenS) {
        this.tenS = tenS;
    }
}