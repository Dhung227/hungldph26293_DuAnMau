package hungldph26293.fpoly.pnlib.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import hungldph26293.fpoly.pnlib.Adapter.PhieuMuonAdapter;
import hungldph26293.fpoly.pnlib.Adapter.SachAdapter;
import hungldph26293.fpoly.pnlib.DAO.LoaiSach_DAO;
import hungldph26293.fpoly.pnlib.DAO.PhieuMuon_DAO;
import hungldph26293.fpoly.pnlib.DAO.Sach_DAO;
import hungldph26293.fpoly.pnlib.DTO.LoaiSach;
import hungldph26293.fpoly.pnlib.DTO.PhieuMuon;
import hungldph26293.fpoly.pnlib.DTO.Sach;
import hungldph26293.fpoly.pnlib.R;
public class QuanLySachFragment extends Fragment {

    Sach_DAO sach_dao;
    RecyclerView recyclerQLS;
    ArrayList<Sach> listS;
    public QuanLySachFragment() {
        // Required empty public constructor
    }

    public static QuanLySachFragment newInstance() {
        QuanLySachFragment fragment = new QuanLySachFragment();
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
        return inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
        recyclerQLS = view.findViewById(R.id.recycleQLS);
        FloatingActionButton fabQLS = view.findViewById(R.id.fab_QLS);

        sach_dao = new Sach_DAO(getContext());
        listS = sach_dao.getDataS();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerQLS.setLayoutManager(layoutManager);
        SachAdapter sachAdapter = new SachAdapter(listS,getContext());
        recyclerQLS.setAdapter(sachAdapter);

        fabQLS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogadd();
            }
        });
    }

    private void loadForm(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerQLS.setLayoutManager(layoutManager);
        SachAdapter sachAdapter = new SachAdapter(listS,getContext());
        recyclerQLS.setAdapter(sachAdapter);
    }

    private void showDialogadd() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_sach, null);

        Spinner spn_tenLS = view.findViewById(R.id.dia_spnLoaiSach_S);
        EditText edt_tenS, edt_tienThue;
        edt_tenS = view.findViewById(R.id.dia_tenS_S);
        edt_tienThue = view.findViewById(R.id.dia_edt_tienthue_S);

        getDataLS(spn_tenLS);

        builder.setView(view);
        builder.setTitle("Thêm Sách");

        builder.setPositiveButton("thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Sach sObj = new Sach();
                HashMap<String, Object> hsLS = (HashMap<String, Object>) spn_tenLS.getSelectedItem();
                int maLS = (int) hsLS.get("maLS");

                sObj.setTenS(edt_tenS.getText().toString());
                sObj.setMaLS(maLS);
                sObj.setTienThue(Integer.parseInt(edt_tienThue.getText().toString()));

                boolean check = sach_dao.insert(sObj);
                if(check){
                    listS.clear();
                    listS.addAll(sach_dao.getDataS());
                    loadForm();
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
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



    private void getDataLS(Spinner spn_LS){
        LoaiSach_DAO loaiSach_dao = new LoaiSach_DAO(getContext());
        ArrayList<LoaiSach> listLS = loaiSach_dao.getDataLS();

        ArrayList<HashMap<String, Object>> listHMLS = new ArrayList<>();
        for (LoaiSach lsObj : listLS) {
            HashMap<String,Object> hsLS = new HashMap<>();
            hsLS.put("maLS", lsObj.getMaLS());
            hsLS.put("tenLS", lsObj.getTenLS());
            listHMLS.add(hsLS);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listHMLS,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                new String[]{"tenLS"},
                new int[] {android.R.id.text1});

        spn_LS.setAdapter(simpleAdapter);
    }


}