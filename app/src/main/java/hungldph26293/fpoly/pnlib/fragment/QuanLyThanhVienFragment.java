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

import hungldph26293.fpoly.pnlib.Adapter.PhieuMuonAdapter;
import hungldph26293.fpoly.pnlib.Adapter.ThanhVienAdapter;
import hungldph26293.fpoly.pnlib.DAO.PhieuMuon_DAO;
import hungldph26293.fpoly.pnlib.DAO.ThanhVien_DAO;
import hungldph26293.fpoly.pnlib.DTO.PhieuMuon;
import hungldph26293.fpoly.pnlib.DTO.ThanhVien;
import hungldph26293.fpoly.pnlib.R;
public class QuanLyThanhVienFragment extends Fragment {
    RecyclerView recyclerQLTV;
    ThanhVien_DAO thanhVien_dao;
    ArrayList<ThanhVien> listTV;

    public QuanLyThanhVienFragment() {
        // Required empty public constructor
    }

    public static QuanLyThanhVienFragment newInstance() {
        QuanLyThanhVienFragment fragment = new QuanLyThanhVienFragment();
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
        View v = inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerQLTV = view.findViewById(R.id.recycleQLTV);
        FloatingActionButton fabQLTV = view.findViewById(R.id.fab_QLTV);

        thanhVien_dao = new ThanhVien_DAO(getContext());
        listTV = thanhVien_dao.getDataTV();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerQLTV.setLayoutManager(layoutManager);
        ThanhVienAdapter thanhVienAdapter = new ThanhVienAdapter(getContext(), listTV);
        recyclerQLTV.setAdapter(thanhVienAdapter);
        fabQLTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });
    }

    private void showDialogAdd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_thanh_vien, null);

        EditText edt_tenTV, edt_namSinh;
        edt_tenTV = view.findViewById(R.id.dia_tenTV);
        edt_namSinh = view.findViewById(R.id.dia_namSinh);

        builder.setView(view);
        builder.setTitle("Thêm thành viên");


        builder.setPositiveButton("thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ThanhVien tvObj = new ThanhVien();
                tvObj.setTenTV(edt_tenTV.getText().toString());
                tvObj.setNamSinh(Integer.parseInt(edt_namSinh.getText().toString()));

                boolean check = thanhVien_dao.insertTV(tvObj);
                if(check){
                    listTV.clear();
                    listTV.addAll(thanhVien_dao.getDataTV());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recyclerQLTV.setLayoutManager(layoutManager);
                    ThanhVienAdapter thanhVienAdapter = new ThanhVienAdapter(getContext(), listTV);
                    recyclerQLTV.setAdapter(thanhVienAdapter);
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
}