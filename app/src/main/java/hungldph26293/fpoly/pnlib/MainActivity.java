package hungldph26293.fpoly.pnlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import hungldph26293.fpoly.pnlib.DAO.Sach_DAO;
import hungldph26293.fpoly.pnlib.fragment.DoanhThuFragment;
import hungldph26293.fpoly.pnlib.fragment.DoiMatKhauFragment;
import hungldph26293.fpoly.pnlib.fragment.QuanLyLoaiSachFragment;
import hungldph26293.fpoly.pnlib.fragment.QuanLyPhieuMuonFragment;
import hungldph26293.fpoly.pnlib.fragment.QuanLySachFragment;
import hungldph26293.fpoly.pnlib.fragment.QuanLyThanhVienFragment;
import hungldph26293.fpoly.pnlib.fragment.TaoNguoiDungFragment;
import hungldph26293.fpoly.pnlib.fragment.Top10SachFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    TextView tv_TT, tv_emailTT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.draw_layout);
        toolbar = findViewById(R.id.tool_bar);


        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.opendrawer, 0);
        toggle.syncState();

        navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        View headerLayout = navigationView.getHeaderView(0);
        TextView tv_thuthuHD = headerLayout.findViewById(R.id.id_thuthuHD);

        listFragment(QuanLyLoaiSachFragment.newInstance());

        SharedPreferences sharedPreferences = getSharedPreferences("account", MODE_PRIVATE);
        String chucVu = sharedPreferences.getString("chucvu","");
        if(chucVu.equals("admin")){
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_TaoNguoiDung).setVisible(true);
        }else{
            Menu menu = navigationView.getMenu();
            menu.findItem(R.id.nav_TaoNguoiDung).setVisible(false);
        }
        String tenTT = sharedPreferences.getString("tentt","");
        String matt = sharedPreferences.getString("matt", "");
        tv_thuthuHD.setText("Xin chào " + matt +" - "+tenTT);

    }

    public void listFragment (Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        View view;
        switch (id){
            case R.id.nav_PhieuMuon:
                listFragment(QuanLyPhieuMuonFragment.newInstance());
                break;
            case R.id.nav_LoaiSach:
                listFragment(QuanLyLoaiSachFragment.newInstance());
                break;
            case R.id.nav_Sach:
                listFragment(QuanLySachFragment.newInstance());
                break;
            case R.id.nav_ThanhVien:
                listFragment(QuanLyThanhVienFragment.newInstance());
                break;
            case R.id.nav_Top10:
                listFragment(Top10SachFragment.newInstance());
                break;
            case R.id.nav_DoanhThu:
                listFragment(DoanhThuFragment.newInstance());
                break;
            case R.id.nav_DoiMatKhau:
                listFragment(DoiMatKhauFragment.newInstance());
                break;
            case R.id.nav_TaoNguoiDung:
                listFragment(TaoNguoiDungFragment.newInstance());
                break;
            case R.id.nav_DangXuat:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
                break;
        }

        drawerLayout.closeDrawer(navigationView);
        toolbar.setTitle(item.getTitle());
        return true;
    }
}