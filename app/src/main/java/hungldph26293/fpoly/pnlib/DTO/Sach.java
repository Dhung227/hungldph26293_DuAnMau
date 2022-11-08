package hungldph26293.fpoly.pnlib.DTO;

public class Sach {
    private int maS;
    private String tenS;
    private int tienThue;
    private int maLS;
    private String tenLS;
    private int soLuong;

    public Sach(int maS, String tenS, int soLuong) {
        this.maS = maS;
        this.tenS = tenS;
        this.soLuong = soLuong;
    }

    //    s.mals, s.tens, s.mals, ls.tenls, s.tienthue
    public Sach(int maS, String tenS, int maLS, String tenLS,  int tienThue) {
        this.maS = maS;
        this.tenS = tenS;
        this.tienThue = tienThue;
        this.maLS = maLS;
        this.tenLS = tenLS;
    }

    public Sach(String tenS, int maLS, int tienThue) {
        this.tenS = tenS;
        this.tienThue = tienThue;
        this.maLS = maLS;
    }

    public Sach() {

    }

    public int getMaS() {
        return maS;
    }

    public void setMaS(int maS) {
        this.maS = maS;
    }

    public String getTenS() {
        return tenS;
    }

    public void setTenS(String tenS) {
        this.tenS = tenS;
    }

    public int getTienThue() {
        return tienThue;
    }

    public void setTienThue(int giaThue) {
        this.tienThue = giaThue;
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

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
