package appdev.ncsu.feddapp_androidv6;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import appdev.ncsu.feddapp_androidv6.Helper.Project;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    View mRootView;

    private RecyclerView recyclerView;
    private ProjectAdapter adapter;
    private List<Project> projectList;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView =  inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view);

        projectList = new ArrayList<>();
        adapter = new ProjectAdapter(getContext(), projectList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareProjects();

        /*try {
            Glide.with(this).load(R.drawable.cover).into((ImageView) mfindViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        return mRootView;
    }

    /**
     * Adding few albums for testing
     */
    private void prepareProjects() {
        int[] covers = new int[]{
                R.mipmap.ic_project_logo,
                R.mipmap.ic_project_logo,
                R.mipmap.ic_project_logo,
                R.mipmap.ic_project_logo,
                R.mipmap.ic_project_logo,
                R.mipmap.ic_project_logo,
                R.mipmap.ic_project_logo,
                R.mipmap.ic_project_logo,
                R.mipmap.ic_project_logo,};

        Project a = new Project("3D Printing", covers[0]);
        projectList.add(a);

        a = new Project("Animatronics",  covers[1]);
        projectList.add(a);

        a = new Project("Arcade Game", covers[2]);
        projectList.add(a);

        a = new Project("Collapsible Bridge", covers[3]);
        projectList.add(a);

        a = new Project("Bubble Blowing Machine", covers[4]);
        projectList.add(a);


        adapter.notifyDataSetChanged();
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

        /**
         * Converting dp to pixel
         */
        private int dpToPx(int dp) {
            Resources r = getResources();
            return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
        }
}
