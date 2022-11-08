package hungldph26293.fpoly.pnlib.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

import hungldph26293.fpoly.pnlib.DAO.Sach_DAO;
import hungldph26293.fpoly.pnlib.DAO.ThanhVien_DAO;
import hungldph26293.fpoly.pnlib.DTO.ThanhVien;
import hungldph26293.fpoly.pnlib.R;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.TVViewHolder>{

    Context context;
    ArrayList<ThanhVien> listTV;

    public ThanhVienAdapter(Context context, ArrayList<ThanhVien> listTV) {
        this.context = context;
        this.listTV = listTV;
    }

    @NonNull
    @Override
    public TVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.item_thanh_vien,null);
        return new TVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVViewHolder holder, int position) {
        holder.tvMaTV.setText("Mã thành viên: "+listTV.get(position).getMaTV());
        holder.tvTenTv.setText("Tên thành viên: "+listTV.get(position).getTenTV());
        holder.tvNamSinh.setText("Năm sinh: "+listTV.get(position).getNamSinh());
        holder.img_delTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa sách");
                builder.setMessage("Bạn có chắc chắc muốn xóa không?");
                ThanhVien_DAO thanhVien_dao = new ThanhVien_DAO(context);
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        int check = thanhVien_dao.deleteTV(listTV.get(holder.getAdapterPosition()).getMaTV());
                        if( check == 1){
                            listTV.clear();
                            listTV = thanhVien_dao.getDataTV();
                            notifyDataSetChanged();
                            Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context, "Không thể xóa", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTV.size();
    }

    public class TVViewHolder extends RecyclerView.ViewHolder {
        TextView tvMaTV, tvTenTv, tvNamSinh;
        ImageView img_delTV;
        public TVViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaTV = itemView.findViewById(R.id.item_maTV);
            tvTenTv = itemView.findViewById(R.id.item_tenTV);
            tvNamSinh = itemView.findViewById(R.id.item_namSinh);
            img_delTV = itemView.findViewById(R.id.item_deleteTV);
        }
    }


}
