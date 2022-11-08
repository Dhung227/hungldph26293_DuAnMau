package hungldph26293.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hungldph26293.fpoly.pnlib.DTO.PhieuMuon;
import hungldph26293.fpoly.pnlib.DbHelper.DbHelper;

public class PhieuMuon_DAO {

    DbHelper dbHelper;

    public PhieuMuon_DAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public boolean insert(PhieuMuon pmObj){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT", pmObj.getMaTT());;
        contentValues.put("maTV", pmObj.getMaTV());
        contentValues.put("maS", pmObj.getMaS());
        contentValues.put("ngayThue", pmObj.getNgayThue());
        contentValues.put("ph26293gio", pmObj.getPh26293gio());
        contentValues.put("traSach", pmObj.getTraSach());
        contentValues.put("tienThue", pmObj.getTienThue());

        long kq =  sqLiteDatabase.insert("PhieuMuon", null, contentValues);
        if(kq == -1){
            return false;
        }
        return true;
    }
//
//    public int update(PhieuMuon pmObj){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("maTT", pmObj.getMaTT());;
//        contentValues.put("tenTV", pmObj.getTenTV());
//        contentValues.put("tenS", pmObj.getTenS());
//        contentValues.put("ngayTra", pmObj.getNgayTra());
//
//        return db.update("PhieuMuon", contentValues, "maPM=?", new String[]{pmObj.getMaPM()});
//    }

//    public int delete(String id){
//        return db.delete("PhieuMuon","maPM=?", new String[]{id});
//    }

    public ArrayList<PhieuMuon> getDataPM(){
        ArrayList<PhieuMuon> listPM = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.mapm, pm.matt, tt.tentt, pm.matv, tv.tentv, pm.mas, sc.tens, pm.ngaythue, pm.ph26293gio, pm.trasach, pm.tienthue FROM PHIEUMUON pm, THANHVIEN tv, THUTHU tt, SACH sc WHERE pm.matv = tv.matv and pm.matt = tt.matt AND pm.mas = sc.mas",
                null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do{
                listPM.add(new PhieuMuon(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8),cursor.getInt(9), cursor.getInt(10)));
            }while(cursor.moveToNext());
        }
        return listPM;
    }

    public ArrayList<PhieuMuon> findDataPM(String tenTV){
        ArrayList<PhieuMuon> listPM = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.mapm, pm.matt, tt.tentt, pm.matv, tv.tentv, pm.mas, sc.tens, pm.ngaythue, pm.ph26293gio, pm.trasach, pm.tienthue FROM PHIEUMUON pm  JOIN THANHVIEN tv ON pm.matv = tv.matv JOIN THUTHU tt ON pm.matt = tt.matt JOIN SACH sc on pm.mas = sc.mas WHERE tv.tentv Like ?",
                new String[]{tenTV});
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do{
                listPM.add(new PhieuMuon(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getInt(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6), cursor.getString(7),
                        cursor.getString(8),cursor.getInt(9), cursor.getInt(10)));
            }while(cursor.moveToNext());
        }
        return listPM;
    }

    public boolean updateTrangThai(int maPM){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("traSach", 1);

        long check = sqLiteDatabase.update("PhieuMuon", contentValues, "maPM=?", new String[]{String.valueOf(maPM)});
        if(check == -1){
            return false;
        }
        return true;
    }
//    String sql, String...selectionArgs
//    public List<PhieuMuon> getAll(){
//        String sql = "SELECT * FROM PhieuMuon";
//        return getDataPM(sql);
//    }
//
//    public PhieuMuon getID(String id){
//        String sql = "SELECT * FROM PhieuMuon WHERE maPM";
//        List<PhieuMuon> listPM = getDataPM(sql, id);
//        return listPM.get(0);
//    }
}
