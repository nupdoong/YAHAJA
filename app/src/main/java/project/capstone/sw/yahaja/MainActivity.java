package project.capstone.sw.yahaja;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private android.support.v4.app.Fragment fragment;
    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();

        tabLayout = findViewById(R.id.tabLayoutMain);
        viewPager = findViewById(R.id.viewPagerMain);
        FragmentAdapter adapter = new FragmentAdapter(fragmentManager);

        adapter.addFragment(new MatchTap(), "MATCH", false);
        adapter.addFragment(new HomeTap(), "HOME", false);
        adapter.addFragment(new RankTap1(), "RANK", false);
        adapter.addFragment(new ClanTap(), "CLAN", false);

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
