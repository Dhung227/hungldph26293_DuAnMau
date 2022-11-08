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

import hungldph26293.fpoly.pnlib.DAO.LoaiSach_DAO;
import hungldph26293.fpoly.pnlib.DTO.LoaiSach;
import hungldph26293.fpoly.pnlib.R;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.LoaiSachViewHolder>{

    ArrayList<LoaiSach> listLS;
    Context context;

    public LoaiSachAdapter(ArrayList<LoaiSach> listLS, Context context) {
        this.listLS = listLS;
        this.context = context;
    }

    @NonNull
    @Override
    public LoaiSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_loai_sach, null);
        return new LoaiSachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachViewHolder holder, int position) {
        holder.tv_malS.setText("Mã loại sách: "+ listLS.get(position).getMaLS());
        holder.tv_tenls.setText("Loại sách: "+ listLS.get(position).getTenLS());

        holder.img_deleteLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Xóa nhóm?");
//        builder.setIcon
                builder.setMessage("Chắn chắn muốn xóa loại sách: ");
                LoaiSach_DAO loaiSach_dao = new LoaiSach_DAO(context);
                LoaiSach lsObj = new LoaiSach();
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int check = loaiSach_dao.deleteLS(listLS.get(position).getMaLS());
                        switch (check){
                            case 1:
                                //load data
                                listLS.clear();
                                listLS = loaiSach_dao.getDataLS();
                                notifyDataSetChanged();
                                break;
                            case -1:
                                Toast.makeText(context, "Không thể xóa thì có sách thuộc thể loại", Toast.LENGTH_SHORT).show();
                                break;
                            case 0:
                                Toast.makeText(context, "xóa thất bại", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listLS.size();
    }

    public class LoaiSachViewHolder extends RecyclerView.ViewHolder {
        TextView tv_malS, tv_tenls;
        ImageView img_deleteLS;
        public LoaiSachViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_malS = itemView.findViewById(R.id.tv_item_maLS);
            tv_tenls = itemView.findViewById(R.id.tv_item_tenLS);
            img_deleteLS = itemView.findViewById(R.id.img_deleteLS);
        }
    }
}
