package hungldph26293.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hungldph26293.fpoly.pnlib.DTO.ThanhVien;
import hungldph26293.fpoly.pnlib.DbHelper.DbHelper;

public class ThanhVien_DAO {
    private DbHelper dbHelper;

    public ThanhVien_DAO (Context context){
        dbHelper = new DbHelper(context);
    }

    public boolean insertTV(ThanhVien tvObj){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenTV", tvObj.getTenTV());
        contentValues.put("namSinh", tvObj.getNamSinh());

        long check = sqLiteDatabase.insert("ThanhVien", null, contentValues);
        if(check == -1){
            return false;
        }
        return true;
    }
//
//    public int update(ThanhVien tvObj){
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("tenTV", tvObj.getTenTV());
//        contentValues.put("namSinh", tvObj.getNamSinh());
//
//        return db.update("ThanhVien", contentValues, "maTV=?",
//                new String[]{String.valueOf(tvObj.getMaTV())});
//    }
//

    // truyeenf vaof cau lenhj sql vaf 1 tham so tuy bien
    public ArrayList<ThanhVien> getDataTV(){
        ArrayList<ThanhVien> listTV = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThanhVien" ,null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listTV.add(new ThanhVien(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            }while (cursor.moveToNext());
        }

        return listTV;
    }

    public int deleteTV(int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int kq = sqLiteDatabase.delete("ThanhVien", "maTV=?", new String[]{String.valueOf(id)});
        if(kq == -1){
            return 0;
        }
        return 1;
    }

//    public List<ThanhVien> getAll(){
//        String sql = "SELECT * FROM ThanhVien";
//        return getData(sql);
//    }
//
//    public ThanhVien getID(String id){
//        String sql = "SELECT * FROM ThanhVien WHERE maTV";
//        List<ThanhVien> listTV = getData(sql, id);
//        if(listTV != null && listTV.size() != 0){
//            return listTV.get(0);
//        }else{
//            return null;
//        }
//
//    }
}
