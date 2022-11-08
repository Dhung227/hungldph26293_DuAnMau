package hungldph26293.fpoly.pnlib.DAO;

import static android.content.Context.MODE_PRIVATE;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.core.database.sqlite.SQLiteDatabaseKt;

import java.util.ArrayList;
import java.util.List;

import hungldph26293.fpoly.pnlib.DTO.ThanhVien;
import hungldph26293.fpoly.pnlib.DTO.ThuThu;
import hungldph26293.fpoly.pnlib.DbHelper.DbHelper;

public class ThuThu_DAO {

    DbHelper dbHelper;
    SharedPreferences sharedPreferences;

    public ThuThu_DAO (Context context){
        dbHelper = new DbHelper(context);
        sharedPreferences = context.getSharedPreferences("account", MODE_PRIVATE);
    }
    // dăng nhập
    public boolean checkLogin(String maTT, String matKhau){
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?" ,
                new String[]{maTT, matKhau});
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("matt", cursor.getString(0));
            editor.putString("tentt", cursor.getString(1));
            editor.putString("matkhau", cursor.getString(2));
            editor.putString("chucvu", cursor.getString(3));
            editor.commit();
            return true;
        }else{
            return false;
        }
    }

    public boolean insert(ThuThu ttObj){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("maTT", ttObj.getMaTT());
        contentValues.put("tenTT", ttObj.getTenTT());
        contentValues.put("matKhau", ttObj.getMatKhau());
        contentValues.put("chucVu", ttObj.getChucVu());

        long check = sqLiteDatabase.insert("ThuThu", null, contentValues);
        if(check == -1){
            return false;
        }
        return true;
    }
//
    public boolean updatePass(String username, String oldPass, String newPass){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?",
                new String[]{username, oldPass});
        if(cursor.getCount() > 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put("matkhau", newPass);
            long check = sqLiteDatabase.update("ThuThu", contentValues, "matt=?", new String[]{username});
            if(check == -1){
                return false;
            }
            return true;
        }
        return false;
    }

//    public int delete(String id){
//        return db.delete("ThuThu", "maTT=?", new String[]{id});
//    }
//
    // truyeenf vaof cau lenhj sql vaf 1 tham so tuy bien
    public ArrayList<ThuThu> getDataTT(){
        ArrayList<ThuThu> listTT = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM ThuThu", null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listTT.add(new ThuThu(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        return listTT;
    }
//
//    public List<ThuThu> getAll(){
//        String sql = "SELECT * FROM ThuThu";
//        return getData(sql);
//    }
//
//    public ThuThu getID(String id){
//        String sql = "SELECT * FROM ThuThu WHERE maTV";
//        List<ThuThu> listTV = getData(sql, id);
//        return listTV.get(0);
//    }


}
