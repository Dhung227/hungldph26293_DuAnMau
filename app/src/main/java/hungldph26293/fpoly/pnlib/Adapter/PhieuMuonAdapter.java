package hungldph26293.fpoly.pnlib.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import hungldph26293.fpoly.pnlib.DAO.PhieuMuon_DAO;
import hungldph26293.fpoly.pnlib.DTO.PhieuMuon;
import hungldph26293.fpoly.pnlib.R;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.PMViewHoler>{

    ArrayList<PhieuMuon> listPM;
    Context context;

    public PhieuMuonAdapter(ArrayList<PhieuMuon> listPM, Context context) {
        this.listPM = listPM;
        this.context = context;
    }

    @NonNull
    @Override
    public PMViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // quản lý layout của từng item
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_recycle_pm, null);
        return new PMViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PMViewHoler holder, int position) {
        holder.tv_pm.setText("Mã phiếu mượn: "+listPM.get(position).getMaPM());
        holder.tvthuthu.setText("Thủ thư: "+listPM.get(position).getMaTT() +" - "+listPM.get(position).getTenTT());
        holder.tvthanhvien.setText("Thành viên: "+listPM.get(position).getMaTV() +" - "+listPM.get(position).getTenTV());
        holder.tvSach.setText("Sách: "+listPM.get(position).getMaS() +" - "+listPM.get(position).getTenS());
        holder.tvNgayThue.setText("Ngày thuê: "+listPM.get(position).getNgayThue() + "- " +listPM.get(position).getPh26293gio());
        String trangThai = "";
        if(listPM.get(position).getTraSach() == 1){
            holder.btn_trasach.setVisibility(View.GONE);
            holder.btn_daTraSach.setVisibility(View.VISIBLE);
            trangThai = "Hoàn thành";
        }else{
            trangThai = "chua trả sách";
            holder.btn_daTraSach.setVisibility(View.INVISIBLE);
        }
        if(listPM.get(position).getTienThue()>5000){
            holder.tv_titleRed.setVisibility(View.VISIBLE);
            holder.tv_titleGreen.setVisibility(View.INVISIBLE);
        }else {
            holder.tv_titleGreen.setVisibility(View.VISIBLE);
            holder.tv_titleRed.setVisibility(View.INVISIBLE);
        }
        holder.tvTrangThai.setText("Trạng thái: "+trangThai);
        holder.tvGiaThue.setText("Giá thuê: "+listPM.get(position).getTienThue());
        holder.btn_trasach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhieuMuon_DAO phieuMuon_dao = new PhieuMuon_DAO(context);
                boolean check = phieuMuon_dao.updateTrangThai(listPM.get(position).getMaPM());
                if(check){
                    listPM.clear();
                    listPM = phieuMuon_dao.getDataPM();
                    notifyDataSetChanged();
                }else{
                    Toast.makeText(context, "Thay đổi không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listPM.size();
    }

    public class PMViewHoler extends RecyclerView.ViewHolder {
        TextView tv_pm, tvthuthu, tvthanhvien, tvSach, tvNgayThue, tvTrangThai, tvGiaThue, tv_titleRed, tv_titleGreen;
        Button btn_trasach, btn_daTraSach;
        public PMViewHoler(@NonNull View itemView) {
            super(itemView);

            tv_pm = itemView.findViewById(R.id.tv_maPM);
            tvthuthu = itemView.findViewById(R.id.tv_thuthuPM);
            tvthanhvien = itemView.findViewById(R.id.tv_thanhvienPM);
            tvSach = itemView.findViewById(R.id.tv_sachPM);
            tvNgayThue = itemView.findViewById(R.id.tv_ngayThue);
            tvTrangThai = itemView.findViewById(R.id.tv_trangThai);
            tvGiaThue = itemView.findViewById(R.id.tv_giaThue);
            tv_titleRed = itemView.findViewById(R.id.tv_titlePMRed);
            tv_titleGreen = itemView.findViewById(R.id.tv_titlePMGreen);
            btn_daTraSach = itemView.findViewById(R.id.btn_datra);
            btn_trasach = itemView.findViewById(R.id.btn_trasach);
        }
    }
}
