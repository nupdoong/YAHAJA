package project.capstone.sw.yahaja;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class RankTapActivity extends AppCompatActivity {
  private TabLayout tabLayout;
  private android.support.v4.app.Fragment fragment;
  private android.support.v4.app.FragmentManager fragmentManager;

  @Override
  protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rank_tap);

    fragmentManager = getSupportFragmentManager();

    tabLayout = findViewById(R.id.tabLayout);
    tabLayout.addTab(tabLayout.newTab().setText("TOP 100"));
    tabLayout.addTab(tabLayout.newTab().setText("NEAR 100"));
    tabLayout.addTab(tabLayout.newTab().setText("PEER 100"));
    tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

    fragment = new TabFragment1();
    fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();

// Set TabSelectedListener
    tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
          case 0:
            fragment = new TabFragment1();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();

            break;
          case 1:
            fragment = new TabFragment2();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
            break;
          case 2:
            fragment = new TabFragment3();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
            break;
          default:
            fragment = new TabFragment1();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragment).commit();
        }

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
