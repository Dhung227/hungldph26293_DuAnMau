package hungldph26293.fpoly.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class SplashScreenActivity extends AppCompatActivity {

    TextInputEditText edt_maSV;
    Button btn_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        edt_maSV = findViewById(R.id.edt_maSV);
        btn_join = findViewById(R.id.btn_join);
        
        
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edt_maSV.getText().toString().equalsIgnoreCase("PH26293")){
                    startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                    finish();
                }else {
                    Toast.makeText(SplashScreenActivity.this, "Mã sinh viên không đúng vui lòng nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
                
                
//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                
//            }
//        },5000);


    }
}