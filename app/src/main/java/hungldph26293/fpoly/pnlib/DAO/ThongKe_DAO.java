package hungldph26293.fpoly.pnlib.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import hungldph26293.fpoly.pnlib.DTO.Sach;
import hungldph26293.fpoly.pnlib.DbHelper.DbHelper;

public class ThongKe_DAO {
    DbHelper dbHelper;

    public ThongKe_DAO(Context context){
        dbHelper = new DbHelper(context);
    }

    public ArrayList<Sach> getTop10Sach(){
        ArrayList<Sach> listS = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT pm.mas, sc.tens, COUNT(pm.mas) FROM PhieuMuon pm, Sach sc WHERE pm.mas = sc.mas GROUP by pm.mas, sc.tens ORDER BY COUNT(pm.mas) DESC LIMIT 10", null);
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            do {
                listS.add(new Sach(cursor.getInt(0), cursor.getString(1), cursor.getInt(2)));
            }while (cursor.moveToNext());
        }
        return listS;
    }

    public int getDoanhThu(String ngayBatDau, String ngayKetThuc){
        ngayBatDau = ngayBatDau.replace("/","");
        ngayKetThuc = ngayKetThuc.replace("/","");
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT SUM(tienthue) FROM PhieuMuon  WHERE substr (ngaythue,7) || substr(ngaythue,4,2) || substr (ngaythue,1,2) BETWEEN ? and ?",
                new String[]{ngayBatDau, ngayKetThuc}
        );
        if(cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
