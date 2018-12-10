package project.capstone.sw.yahaja;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends NavigationActivity implements View.OnClickListener{
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private android.support.v4.app.FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView navigationIcon = findViewById(R.id.imageView_menuIcon);
        navigationIcon.setOnClickListener(this);

        fragmentManager = getSupportFragmentManager();

        tabLayout = findViewById(R.id.tabLayoutMain);
        viewPager = findViewById(R.id.viewPagerMain);
        FragmentAdapter adapter = new FragmentAdapter(fragmentManager);

        adapter.addFragment(new HomeTap(), "HOME", false);
        adapter.addFragment(new MatchTap(), "MATCH", false);
        adapter.addFragment(new RankTapActivity(), "RANK", false);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_menuIcon:
                super.drawerLayout.openDrawer(Gravity.START);
                StoredUserSession storedUserSession = new StoredUserSession(this);
                String u_id = storedUserSession.getUserSession();
                TextView textView = drawerLayout.findViewById(R.id.drawerHeader);
                textView.setText(u_id);
                break;
            default:
                break;
        }
    }
}
