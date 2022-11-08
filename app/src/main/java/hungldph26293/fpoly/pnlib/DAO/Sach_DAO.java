package hungldph26293.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hungldph26293.fpoly.pnlib.DTO.Sach;
import hungldph26293.fpoly.pnlib.DTO.ThanhVien;
import hungldph26293.fpoly.pnlib.DbHelper.DbHelper;

public class Sach_DAO {

    DbHelper dbHelper;
    public Sach_DAO (Context context){
        dbHelper = new DbHelper(context);
    }

    // đọc dữ liệu
    public ArrayList<Sach> getDataS(){
        ArrayList<Sach> listS = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT s.mas, s.tens, s.mals, ls.tenls, s.tienthue  FROM Sach s, LoaiSach ls where s.mals = ls.mals", null);
        if(cursor.getCount() != 0){ // kiểm tra con trỏ
            cursor.moveToFirst(); // chạy về đầu
            do{
                listS.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getString(3), cursor.getInt(4)));
            }while(cursor.moveToNext());
        }
        return listS;
    }

    // ghi dữ liệu
    public boolean insert(Sach sObj){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenS", sObj.getTenS());
        contentValues.put("maLS", sObj.getMaLS());
        contentValues.put("tienthue", sObj.getTienThue());

        long check = sqLiteDatabase.insert("Sach", null, contentValues);

        if(check == -1 ){
            return false;
        }
        return true;
    }

    public int delete(int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        int kq = sqLiteDatabase.delete("Sach", "maS=?", new String[]{String.valueOf(id)});
        if(kq == -1){
            return 0;
        }
        return 1;
    }
}
