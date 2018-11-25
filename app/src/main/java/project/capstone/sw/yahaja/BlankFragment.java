package project.capstone.sw.yahaja;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BlankFragment extends Fragment {
  public BlankFragment(){}

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
    return inflater.inflate(R.layout.activity_rank_near, container, false);
  }
}
