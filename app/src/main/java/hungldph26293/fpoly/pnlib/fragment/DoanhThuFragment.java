package hungldph26293.fpoly.pnlib.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

import hungldph26293.fpoly.pnlib.DAO.ThongKe_DAO;
import hungldph26293.fpoly.pnlib.R;

public class DoanhThuFragment extends Fragment {

    public DoanhThuFragment() {
        // Required empty public constructor
    }


    public static DoanhThuFragment newInstance() {
        DoanhThuFragment fragment = new DoanhThuFragment();
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
        return inflater.inflate(R.layout.fragment_doanh_thu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText edt_start, edt_end;
        edt_start = view.findViewById(R.id.edt_startDay);
        edt_end = view.findViewById(R.id.edt_endDay);
        Button btn_doanhthu = view.findViewById(R.id.thongke);
        ImageView img_startDay = view.findViewById(R.id.img_StartDay);
        ImageView img_endDay = view.findViewById(R.id.img_endDay);
        TextView tv_doanhthu = view.findViewById(R.id.tv_doanhthu);

        Calendar calendar = Calendar.getInstance();

        img_startDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";
                                if(dayOfMonth < 10){
                                    ngay = "0" + dayOfMonth;
                                }else{
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if((month + 1) < 10){
                                    thang = "0" + (month + 1);
                                }else{
                                    thang = String.valueOf(month + 1);
                                }
                                edt_start.setText(year + "/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        img_endDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                String ngay = "";
                                String thang = "";
                                if(dayOfMonth < 10){
                                    ngay = "0" + dayOfMonth;
                                }else{
                                    ngay = String.valueOf(dayOfMonth);
                                }
                                if((month + 1) < 10){
                                    thang = "0" + (month + 1);
                                }else{
                                    thang = String.valueOf(month + 1);
                                }
                                edt_end.setText(year + "/" + thang + "/" + ngay);
                            }
                        },
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        btn_doanhthu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThongKe_DAO top10SachDao = new ThongKe_DAO(getContext());
                String ngayBatDau = edt_start.getText().toString();
                String ngayKetThuc = edt_end.getText().toString();
                int doanhThu = top10SachDao.getDoanhThu(ngayBatDau, ngayKetThuc);
                tv_doanhthu.setText("Tổng doanh thu: "+doanhThu + "VNĐ");
            }
        });
    }
}