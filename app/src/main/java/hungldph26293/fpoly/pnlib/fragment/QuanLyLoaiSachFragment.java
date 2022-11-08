package hungldph26293.fpoly.pnlib.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import hungldph26293.fpoly.pnlib.Adapter.LoaiSachAdapter;
import hungldph26293.fpoly.pnlib.Adapter.PhieuMuonAdapter;
import hungldph26293.fpoly.pnlib.DAO.LoaiSach_DAO;
import hungldph26293.fpoly.pnlib.DAO.PhieuMuon_DAO;
import hungldph26293.fpoly.pnlib.DTO.LoaiSach;
import hungldph26293.fpoly.pnlib.DTO.PhieuMuon;
import hungldph26293.fpoly.pnlib.R;

public class QuanLyLoaiSachFragment extends Fragment {

    LoaiSach_DAO loaiSach_dao;
    RecyclerView recyclerQLLS;
    ArrayList<LoaiSach> listLS;


    public QuanLyLoaiSachFragment() {
        // Required empty public constructor
    }

    public static QuanLyLoaiSachFragment newInstance() {
        QuanLyLoaiSachFragment fragment = new QuanLyLoaiSachFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_loai_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerQLLS = view.findViewById(R.id.recycleQLLS);
        FloatingActionButton fabQLLS = view.findViewById(R.id.fab_QLLS);

        loaiSach_dao = new LoaiSach_DAO(getContext());
        listLS = loaiSach_dao.getDataLS();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerQLLS.setLayoutManager(layoutManager);
        LoaiSachAdapter loaiSachAdapter = new LoaiSachAdapter(listLS,getContext());
        recyclerQLLS.setAdapter(loaiSachAdapter);

        fabQLLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowDialogAddLS();
            }
        });
    }


    private void ShowDialogAddLS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_loai_sach, null);

        EditText edt_LS = view.findViewById(R.id.dialg_edt_tenLS);

        builder.setView(view);
        builder.setTitle("Thêm Loại sách");

        builder.setPositiveButton("thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                LoaiSach lsObj = new LoaiSach();
                lsObj.setTenLS(edt_LS.getText().toString());
                boolean check = loaiSach_dao.insertLS(lsObj);
                if(check){
                    listLS.clear();
                    listLS.addAll(loaiSach_dao.getDataLS());
                    loadForm();
                    Toast.makeText(getContext(), "Đã thêm thành công", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "thêm không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void loadForm(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerQLLS.setLayoutManager(layoutManager);
        LoaiSachAdapter loaiSachAdapter = new LoaiSachAdapter(listLS,getContext());
        recyclerQLLS.setAdapter(loaiSachAdapter);
    }

//    private void addLS(String tenLS){
//        LoaiSach loaiSach = new LoaiSach(tenLS);
//        boolean check = loaiSach_dao.insertLS(loaiSach);
//        if (check) {
//            loadForm();
//            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
//        }else{
//            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
//        }
//    }
}