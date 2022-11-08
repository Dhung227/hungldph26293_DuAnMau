package hungldph26293.fpoly.pnlib.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hungldph26293.fpoly.pnlib.DTO.LoaiSach;
import hungldph26293.fpoly.pnlib.DTO.ThanhVien;
import hungldph26293.fpoly.pnlib.DbHelper.DbHelper;

public class LoaiSach_DAO {
    DbHelper dbHelper;

    public LoaiSach_DAO (Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<LoaiSach> getDataLS(){
        ArrayList<LoaiSach> listLS = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM LoaiSach", null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listLS.add(new LoaiSach(cursor.getInt(0), cursor.getString(1)));
            }while(cursor.moveToNext());
        }
        return listLS;
    }

    public boolean insertLS(LoaiSach lsObj){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tenLS", lsObj.getTenLS());

        long check = sqLiteDatabase.insert("LoaiSach", null, contentValues);
        if(check == -1){
            return false;
        }
        return true;
    }

    public int deleteLS(int id){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Sach Where maLS=?", new String[]{String.valueOf(id)});
        if(cursor.getCount() != 0){
            return -1;
        }else{
            int check = sqLiteDatabase.delete("LoaiSach", "maLS=?",new String[]{String.valueOf(id)});
            if(check == -1)
                return 0;
            return 1;
        }
    }
}
