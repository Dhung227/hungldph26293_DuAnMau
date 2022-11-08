package hungldph26293.fpoly.pnlib.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import hungldph26293.fpoly.pnlib.Adapter.Top10Adapter;
import hungldph26293.fpoly.pnlib.DAO.ThongKe_DAO;
import hungldph26293.fpoly.pnlib.DTO.Sach;
import hungldph26293.fpoly.pnlib.R;

public class Top10SachFragment extends Fragment {

    RecyclerView recyclerTop10;
    ArrayList<Sach> listS;
    ThongKe_DAO thongKe_dao;

    public Top10SachFragment() {
        // Required empty public constructor
    }

    public static Top10SachFragment newInstance() {
        Top10SachFragment fragment = new Top10SachFragment();
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
        return inflater.inflate(R.layout.fragment_top10_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerTop10 = view.findViewById(R.id.recycler_Top10Sach);

        thongKe_dao = new ThongKe_DAO(getContext());
        listS = thongKe_dao.getTop10Sach();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerTop10.setLayoutManager(layoutManager);
        Top10Adapter top10Adapter = new Top10Adapter(listS,getContext());
        recyclerTop10.setAdapter(top10Adapter);
    }
}