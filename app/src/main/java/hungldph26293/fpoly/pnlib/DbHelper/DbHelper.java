package hungldph26293.fpoly.pnlib.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    static final String dbName = "PBLib";
//    static final int dbVersion = 6;

    public DbHelper(Context context){
        super(context, dbName, null, 11);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableThuThu = "CREATE TABLE ThuThu ( maTT TEXT NOT NULL, tenTT TEXT NOT NULL, matKhau TEXT NOT NULL, chucVu Text NOT NULL, PRIMARY KEY(maTT))";
        db.execSQL(createTableThuThu);

        String createTableThanhVien = "CREATE TABLE ThanhVien ( maTV INTEGER NOT NULL, tenTV TEXT NOT NULL, namSinh INTEGER NOT NULL, PRIMARY KEY(maTV AUTOINCREMENT))";
        db.execSQL(createTableThanhVien);

        String createTableSach = "CREATE TABLE Sach ( maS INTEGER NOT NULL, tenS TEXT NOT NULL, maLS INTEGER NOT NULL references LoaiSach(maLS), tienThue INTEGER NOT NULL, PRIMARY KEY(maS AUTOINCREMENT))";
        db.execSQL(createTableSach);

        String createTableLoaiSach = "CREATE TABLE LoaiSach ( maLS INTEGER NOT NULL, tenLS TEXT NOT NULL, PRIMARY KEY(maLS AUTOINCREMENT))";
        db.execSQL(createTableLoaiSach);

        String createTablePhieuMuon = "CREATE TABLE PhieuMuon ( maPM INTEGER NOT NULL, maTT TEXT NOT NULL, maTV INTEGER NOT NULL, maS INTEGER NOT NULL, ngayThue TEXT NOT NULL, ph26293gio TEXT NOT NULL, traSach INTEGER NOT NULL, tienThue INTEGER NOT NULL, PRIMARY KEY(maPM AUTOINCREMENT))";
        db.execSQL(createTablePhieuMuon);

        String createTableTopSach = "CREATE TABLE TopSach ( tenS TEXT NOT NULL, soLuong TEXT NOT NULL, PRIMARY KEY(tenS) )";
        db.execSQL(createTableTopSach);

        db.execSQL("INSERT INTO THUTHU VALUES ('Admin','Nguyễn Văn Anh','admin','admin'),('thuthu01','Trần Văn Hùng','TT01','nhân viên')");
        //trả sách: 1: đã trả - 0: chưa trả
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(oldVersion != newVersion){
        String dropTableThuThu = "drop table if exists ThuThu";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists ThanhVien";
        db.execSQL(dropTableThanhVien);
        String dropTableSach = "drop table if exists Sach";
        db.execSQL(dropTableSach);
        String dropTableLoaiSach = "drop table if exists LoaiSach";
        db.execSQL(dropTableLoaiSach);
        String dropTablePhieuMuon = "drop table if exists PhieuMuon";
        db.execSQL(dropTablePhieuMuon);
            String dropTableTopSach = "drop table if exists TopSach";
            db.execSQL(dropTableTopSach);
        onCreate(db);
        }

    }
}
