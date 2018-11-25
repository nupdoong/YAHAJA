package project.capstone.sw.yahaja;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class RankTapActivity extends AppCompatActivity {
  private TabLayout tabLayout;
  private ViewPager viewPager;
  private android.support.v4.app.Fragment fragment;
  private android.support.v4.app.FragmentManager fragmentManager;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rank_tap);

    fragmentManager = getSupportFragmentManager();

    tabLayout = findViewById(R.id.tabLayout);
    viewPager = findViewById(R.id.viewPager);
    FragmentAdapter adapter = new FragmentAdapter(fragmentManager);

    adapter.addFragment(new RankTap1(), "TOP 100", false);
    adapter.addFragment(new RankTap2(), "NEAR 100", false);
    adapter.addFragment(new RankTap3(), "PEER 100", false);

    viewPager.setAdapter(adapter);

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
  }
}
