package hungldph26293.fpoly.pnlib.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hungldph26293.fpoly.pnlib.DTO.Sach;
import hungldph26293.fpoly.pnlib.R;

public class Top10Adapter extends RecyclerView.Adapter<Top10Adapter.Top10ViewHolder>{

    ArrayList<Sach> listS;
    Context context;

    public Top10Adapter(ArrayList<Sach> listS, Context context) {
        this.listS = listS;
        this.context = context;
    }

    @NonNull
    @Override
    public Top10ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_top10sach, null);
        return new Top10ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Top10ViewHolder holder, int position) {
        holder.tv_maS.setText("Mã sách: " + String.valueOf(listS.get(position).getMaS()));
        holder.tv_tenS.setText("tên Sách" + listS.get(position).getTenS());
        holder.tv_soLuong.setText("Số lượng: " + String.valueOf(listS.get(position).getSoLuong()));
    }

    @Override
    public int getItemCount() {
        return listS.size();
    }

    public class Top10ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_maS, tv_tenS, tv_soLuong;

        public Top10ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_maS = itemView.findViewById(R.id.tv_item_maS_top10);
            tv_tenS = itemView.findViewById(R.id.tv_item_tenS_top10);
            tv_soLuong = itemView.findViewById(R.id.tv_item_soLuong_top10);
        }
    }
}
