package hungldph26293.fpoly.pnlib.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.icu.util.ULocale;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import hungldph26293.fpoly.pnlib.Adapter.PhieuMuonAdapter;
import hungldph26293.fpoly.pnlib.DAO.PhieuMuon_DAO;
import hungldph26293.fpoly.pnlib.DAO.Sach_DAO;
import hungldph26293.fpoly.pnlib.DAO.ThanhVien_DAO;
import hungldph26293.fpoly.pnlib.DTO.PhieuMuon;
import hungldph26293.fpoly.pnlib.DTO.Sach;
import hungldph26293.fpoly.pnlib.DTO.ThanhVien;
import hungldph26293.fpoly.pnlib.R;

public class QuanLyPhieuMuonFragment extends Fragment {

    PhieuMuon_DAO phieuMuon_dao;
    ArrayList<PhieuMuon> listPM;
    RecyclerView recyclerQLOM;

    public QuanLyPhieuMuonFragment() {
        // Required empty public constructor
    }

    public static QuanLyPhieuMuonFragment newInstance() {
        QuanLyPhieuMuonFragment fragment = new QuanLyPhieuMuonFragment();
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
        return inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerQLOM = view.findViewById(R.id.recycleQLPM);
        FloatingActionButton fabQLPM = view.findViewById(R.id.fab_QLPM);

        EditText edt_tenTV = view.findViewById(R.id.edt_timPM);
        Button btn_timPM = view.findViewById(R.id.btn_timPM);

        phieuMuon_dao = new PhieuMuon_DAO(getContext());
        listPM = phieuMuon_dao.getDataPM();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerQLOM.setLayoutManager(layoutManager);
        PhieuMuonAdapter phieuMuonAdapter = new PhieuMuonAdapter(listPM,getContext());
        recyclerQLOM.setAdapter(phieuMuonAdapter);

        fabQLPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogAdd();
            }
        });

        btn_timPM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_tenTV.getText().toString().isEmpty()){
                    listPM.clear();
                    listPM.addAll(phieuMuon_dao.getDataPM());
                    loadform();
                }else{
                    listPM.clear();
                    listPM.addAll(phieuMuon_dao.findDataPM(edt_tenTV.getText().toString()));
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recyclerQLOM.setLayoutManager(layoutManager);
                    PhieuMuonAdapter phieuMuonAdapter = new PhieuMuonAdapter(listPM,getContext());
                    recyclerQLOM.setAdapter(phieuMuonAdapter);
                }
            }
        });
    }

    private void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_phieu_muon, null);

        Spinner spnTV, spnS;

        spnTV = view.findViewById(R.id.spn_thanhvienPM);
        spnS = view.findViewById(R.id.spn_sachPM);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("account", Context.MODE_PRIVATE);
        String matt = sharedPreferences.getString("matt","");

        Date currentTime = Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngay = simpleDateFormat.format(currentTime);
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        String gio = simpleDateFormat1.format(currentTime);

        getDataTV(spnTV);
        getDataS((spnS));
        builder.setView(view);
        builder.setTitle("Thêm phiếu mượn");

        builder.setPositiveButton("Thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                PhieuMuon pmObj = new PhieuMuon();

                HashMap<String, Object> hsTV = (HashMap<String, Object>) spnTV.getSelectedItem();
                int maTV = (int) hsTV.get("matv");
                HashMap<String, Object> hsS = (HashMap<String, Object>) spnS.getSelectedItem();
                int maS = (int) hsS.get("mas");
                int tien = (int) hsS.get("tienthue");

                pmObj.setMaTV(maTV);
                pmObj.setMaS(maS);
                pmObj.setMaTT(matt);
                pmObj.setNgayThue(ngay);
                pmObj.setPh26293gio(gio);
                pmObj.setTraSach(0);
                pmObj.setTienThue(tien);

                boolean check = phieuMuon_dao.insert(pmObj);
                if(check) {
                    listPM.clear();
                    listPM.addAll(phieuMuon_dao.getDataPM());
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                    recyclerQLOM.setLayoutManager(layoutManager);
                    PhieuMuonAdapter phieuMuonAdapter = new PhieuMuonAdapter(listPM,getContext());
                    recyclerQLOM.setAdapter(phieuMuonAdapter);
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

    private void loadform(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerQLOM.setLayoutManager(layoutManager);
        PhieuMuonAdapter phieuMuonAdapter = new PhieuMuonAdapter(listPM,getContext());
        recyclerQLOM.setAdapter(phieuMuonAdapter);
    }

    private void getDataTV (Spinner spnTV){
        ThanhVien_DAO thanhVien_dao = new ThanhVien_DAO(getContext());
        ArrayList<ThanhVien> listTV = thanhVien_dao.getDataTV();
        // đổi danh sách thành hashmap
        ArrayList<HashMap<String, Object>> listHMTV = new ArrayList<>();
        for (ThanhVien tv:listTV) {
            HashMap<String, Object> hsTV = new HashMap<>();
            hsTV.put("matv", tv.getMaTV());
            hsTV.put("tentv", tv.getTenTV());
            listHMTV.add(hsTV);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listHMTV,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                new String[]{"tentv"},
                new int[]{android.R.id.text1});

        spnTV.setAdapter(simpleAdapter);
    }

    private void getDataS (Spinner spnS){
        Sach_DAO sach_dao = new Sach_DAO(getContext());
        ArrayList<Sach> listS = sach_dao.getDataS();
        // đổi danh sách thành hashmap
        ArrayList<HashMap<String, Object>> listHMS = new ArrayList<>();
        for (Sach s:listS) {
            HashMap<String, Object> hsS = new HashMap<>();
            hsS.put("mas", s.getMaS());
            hsS.put("tens", s.getTenS());
            hsS.put("tienthue", s.getTienThue());
            listHMS.add(hsS);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(getContext(), listHMS,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                new String[]{"tens"},
                new int[]{android.R.id.text1});

        spnS.setAdapter(simpleAdapter);
    }

    }
