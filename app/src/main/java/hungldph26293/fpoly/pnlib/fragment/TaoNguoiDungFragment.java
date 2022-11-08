package hungldph26293.fpoly.pnlib.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import hungldph26293.fpoly.pnlib.DAO.PhieuMuon_DAO;
import hungldph26293.fpoly.pnlib.DAO.ThuThu_DAO;
import hungldph26293.fpoly.pnlib.DTO.ThuThu;
import hungldph26293.fpoly.pnlib.R;

public class TaoNguoiDungFragment extends Fragment {

    EditText edtUserName, edtMaTT, edtPass, edtRePass;
    Button btnSave, btnCancel;
    ThuThu_DAO ttDao;
    ArrayList<ThuThu> listTT;

    public TaoNguoiDungFragment() {
        // Required empty public constructor
    }

    public static TaoNguoiDungFragment newInstance() {
        TaoNguoiDungFragment fragment = new TaoNguoiDungFragment();
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
        return inflater.inflate(R.layout.fragment_tao_nguoi_dung, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtUserName = view.findViewById(R.id.edtUserName);
        edtMaTT = view.findViewById(R.id.edtmaTT);
        edtPass = view.findViewById(R.id.edtPassAddUser);
        edtRePass = view.findViewById(R.id.edtRePassAddUser);
        btnSave = view.findViewById(R.id.btnSaveUser);
        btnCancel = view.findViewById(R.id.btnCancelAddUser);

        ttDao = new ThuThu_DAO(getActivity());
        listTT = ttDao.getDataTT();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtMaTT.setText("");
                edtUserName.setText("");
                edtPass.setText("");
                edtRePass.setText("");
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu ttObj = new ThuThu();
                ttObj.setMaTT(edtMaTT.getText().toString());
                ttObj.setTenTT(edtUserName.getText().toString());
                ttObj.setMatKhau(edtPass.getText().toString());
                ttObj.setChucVu("nhân viên");

                boolean check = ttDao.insert(ttObj);

                if(validate() > 0){
                    if(check){
                        listTT.clear();
                        listTT.addAll(ttDao.getDataTT());
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Kiểm tra lại thông tin nhập", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public int validate(){
        String strUserName = edtUserName.getText().toString();
        String strmaTT = edtMaTT.getText().toString();
        String strPass = edtPass.getText().toString();
        String strRePass = edtRePass.getText().toString();
        int check = 1;
        if(strmaTT.isEmpty() || strUserName.isEmpty() || strPass.isEmpty()|| strRePass.isEmpty()){
            Toast.makeText(getContext(), "Nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            if(!strRePass.equals(strPass)) {
                Toast.makeText(getContext(), "Nhập lại mật khảu sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}