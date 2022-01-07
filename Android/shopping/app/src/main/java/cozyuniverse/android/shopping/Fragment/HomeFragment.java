package cozyuniverse.android.shopping.Fragment;

import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.GridView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

import cozyuniverse.android.shopping.DTO.ProductBean;
import cozyuniverse.android.shopping.Recycler.HomeGridAdapter;

public class HomeFragment extends Fragment {
    private static final int INTERVAL_TIME = 3800;

    private View view;
    private ViewFlipper viewFlipper;
    private GridView gridView;
    private HomeGridAdapter adapter;
    private ArrayList<ProductBean> data;

}
