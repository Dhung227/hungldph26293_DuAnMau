package hungldph26293.fpoly.pnlib.fragment;

import android.content.Context;
import android.content.SharedPreferences;
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

import hungldph26293.fpoly.pnlib.DAO.ThuThu_DAO;
import hungldph26293.fpoly.pnlib.DTO.ThuThu;
import hungldph26293.fpoly.pnlib.R;

public class DoiMatKhauFragment extends Fragment {

    EditText edtOldPass, edtNewPass, edtRePass;
    Button btnSave, btnCancel;

    public DoiMatKhauFragment() {
        // Required empty public constructor
    }

    public static DoiMatKhauFragment newInstance() {
        DoiMatKhauFragment fragment = new DoiMatKhauFragment();
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
        return inflater.inflate(R.layout.fragment_doi_mat_khau, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtNewPass = view.findViewById(R.id.edt_NewPass);
        edtOldPass = view.findViewById(R.id.edt_OldPass);
        edtRePass = view.findViewById(R.id.edt_ReNewPass);
        btnSave = view.findViewById(R.id.btnSaveChangePass);
        btnCancel = view.findViewById(R.id.btnCancelChangePass);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
                String maTT = sharedPreferences.getString("matt", "" );
                String oldPass = edtOldPass.getText().toString();
                String newPass = edtNewPass.getText().toString();
                String rePass = edtRePass.getText().toString();
                if(!oldPass.equals(rePass)){
                    ThuThu_DAO thuThu_dao = new ThuThu_DAO(getContext());
                    boolean check = thuThu_dao.updatePass(maTT, oldPass, newPass);
                    if(check){
                        Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getActivity(), "Kiểm tra lại thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtOldPass.setText("");
                edtNewPass.setText("");
                edtRePass.setText("");
            }
        });
    }

    public int validate(){
        int check = 1;
        String strOldPass = edtOldPass.getText().toString();
        String strNewPass = edtNewPass.getText().toString();
        String strRePass = edtRePass.getText().toString();

        if(strOldPass.isEmpty() || edtNewPass.getText().toString().isEmpty()
        || edtRePass.getText().toString().isEmpty()){
            Toast.makeText(getContext(), "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else{
            if(!strRePass.equals(strNewPass)) {
                Toast.makeText(getContext(), "Nhập lai mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                check = -1;
            }
//            }else{
//                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("account", Context.MODE_PRIVATE);
//                strOldPass = sharedPreferences.getString("matkhau", ""  );
//                if(!strOldPass.equals(strNewPass)){
//                    Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
//                    check = -1;
//                }
//            }
            check = 1;
        }
        return check;
    }
}