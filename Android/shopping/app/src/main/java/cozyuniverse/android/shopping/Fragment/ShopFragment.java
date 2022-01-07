package cozyuniverse.android.shopping.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import cozyuniverse.android.shopping.DTO.ProductBean;
import cozyuniverse.android.shopping.R;
import cozyuniverse.android.shopping.Recycler.ItemClickListener;
import cozyuniverse.android.shopping.Recycler.SelectRecyclerAdapter;

public class ShopFragment extends Fragment implements ItemClickListener {

//    private static final String TYPE_TOP = "Top";
//    private static final String TYPE_BOTTOM = "Bottom";
//    private static final String TYPE_ACC = "Acc";
//
//    private View view;
//    private RecyclerView recyclerView;
//    private SelectRecyclerAdapter tAdapter;
//    private String[] tData;
//    private ShopRecyclerAdapter pAdapter;
//    private ArrayList<ProductBean> pData;
//
//
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        view = inflater.inflate(R.layout.activity_shop_fragment, container, false);
//
//        showTypeSelecter();
//        showProduct();
//
//        return view;
//    }
//
//    private void showTypeSelecter() {
//        tData = getContext().getResources().getStringArray(R.array.type);
//
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView = view.findViewById(R.id.typeSelectRecycler);
//        recyclerView.setLayoutManager(layoutManager);
//        tAdapter = new SelectRecyclerAdapter(tData, this);
//        recyclerView.setAdapter(tAdapter);
//    }
//
//    private void showProduct() {
//        dbHelper = ProductDBHelper.getInstance(getContext());
//        pData = dbHelper.getAllProduct();
//
//        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
//        recyclerView = view.findViewById(R.id.productRecycler);
//        recyclerView.setLayoutManager(layoutManager);
//        pAdapter = new ShopRecyclerAdapter(pData, this);
//        recyclerView.setAdapter(pAdapter);
//    }
//
//    private void showProduct(String type) {
//        pData.clear();
//        pData = dbHelper.getProductbyType(type);
//        pAdapter.updateData(pData);
//    }

    @Override
    public void onItemClick(View v, int position) {
        String type = String.valueOf(((TextView)(v.findViewById(R.id.typeSelectTv))).getText());

//        if(type.equals(TYPE_TOP)) {
//            showProduct(type);
//        } else if(type.equals(TYPE_BOTTOM)) {
//            showProduct(type);
//        } else if(type.equals(TYPE_ACC)) {
//            showProduct(type);
//        }
    }
}
