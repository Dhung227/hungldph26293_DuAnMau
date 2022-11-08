package hungldph26293.fpoly.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import hungldph26293.fpoly.pnlib.DAO.ThuThu_DAO;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_TenDangNhap;
    private TextInputEditText edt_pass;
    private Button btn_DangNhap, btn_Huy;
    private CheckBox ckb_LuuMatKhau;
    private Context context;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = this;
        edt_pass = findViewById(R.id.edt_pass);
        edt_TenDangNhap = findViewById(R.id.edt_tenDangNhap);
        btn_DangNhap = findViewById(R.id.btn_DangNhap);
        btn_Huy = findViewById(R.id.btn_Huy);
        ckb_LuuMatKhau = findViewById(R.id.ckb_LuuMatKhau);
        ReadSharedPref();
        ThuThu_DAO thuThu_dao = new ThuThu_DAO(this);

        btn_DangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edt_TenDangNhap.getText().toString();
                String pass = edt_pass.getText().toString();
                if(thuThu_dao.checkLogin(user, pass)){
                    if(ckb_LuuMatKhau.isChecked() == true){
                        sharedPreferences = context.getSharedPreferences("LuuMatKhau", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("tenDangNhap", user);
                        editor.putString("matKhau", pass);
                        editor.commit();
                    }
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(context, "Kiểm tra lại mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_pass.setText("");
                edt_TenDangNhap.setText("");
                ckb_LuuMatKhau.setChecked(false);
            }
        });
    }

    public void ReadSharedPref(){

        // đọc dữ liệu từ shared pref
        sharedPreferences = getSharedPreferences("LuuMatKhau", MODE_PRIVATE);
        String user = sharedPreferences.getString("tenDangNhap","");
        String pass = sharedPreferences.getString("matKhau","");

        edt_TenDangNhap.setText(user);
        edt_pass.setText(pass);
        ckb_LuuMatKhau.setChecked(true);
    }

}