package project.capstone.sw.yahaja;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RankTapActivity extends Fragment {
  private TabLayout tabLayout;
  private ViewPager viewPager;
  private android.support.v4.app.Fragment fragment;
  private android.support.v4.app.FragmentManager rankFragmentManager;

  public RankTapActivity(){

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    View view = inflater.inflate(R.layout.activity_rank_tap, container, false);

    rankFragmentManager = getChildFragmentManager();
    tabLayout = view.findViewById(R.id.tabLayout);
    viewPager = view.findViewById(R.id.viewPager);

    FragmentAdapter rankAdapter = new FragmentAdapter(rankFragmentManager);

    rankAdapter.addFragment(new UserRankTap(), "USER", false);
    rankAdapter.addFragment(new ClanRankTap(), "CLAN", false);

    viewPager.setAdapter(rankAdapter);

    tabLayout.setTabMode(TabLayout.MODE_FIXED);
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    tabLayout.setupWithViewPager(viewPager);

// Set TabSelectedListener
    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });
    return view;
  }
}
