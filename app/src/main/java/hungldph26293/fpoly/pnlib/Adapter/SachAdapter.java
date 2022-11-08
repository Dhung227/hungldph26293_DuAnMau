package hungldph26293.fpoly.pnlib.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hungldph26293.fpoly.pnlib.DAO.Sach_DAO;
import hungldph26293.fpoly.pnlib.DTO.Sach;
import hungldph26293.fpoly.pnlib.R;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {

    ArrayList<Sach> listS;
    Context context;
    Sach_DAO sach_dao;

    public SachAdapter(ArrayList<Sach> listS, Context context) {
        this.listS = listS;
        this.context = context;
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_sach, null);
        return new SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {

        holder.tv_maS.setText("Mã sách: "+listS.get(position).getMaS());
        holder.tv_tenS.setText("Tên sách: "+listS.get(position).getTenS());
        holder.tv_ls.setText("Loại sách: "+listS.get(position).getMaLS() + "- "+listS.get(position).getTenLS());
        holder.tv_gia.setText("giá Thuê: "+listS.get(position).getTienThue());

        holder.img_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa sách");
                builder.setMessage("Bạn có chắc chắc muốn xóa không?");
                Sach_DAO sach_dao = new Sach_DAO(context);
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        int check = sach_dao.delete(listS.get(holder.getAdapterPosition()).getMaS());
                        if( check == 1){
                            listS.clear();
                            listS = sach_dao.getDataS();
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
        return listS.size();
    }

    public class SachViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_maS, tv_tenS, tv_ls, tv_gia;
        private ImageView img_del;
        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_maS = itemView.findViewById(R.id.item_maS);
            tv_tenS = itemView.findViewById(R.id.item_tenS);
            tv_ls = itemView.findViewById(R.id.item_LS_S);
            tv_gia = itemView.findViewById(R.id.item_giaThueS);
            img_del = itemView.findViewById(R.id.item_deleteS   );
        }
    }
}
