package project.capstone.sw.yahaja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class MatchTap extends Fragment {
    public MatchTap(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_match, container, false);

        Button btn = view.findViewById(R.id.button3);

        //btn.setOnClickListener(this);
        return view;

    }

    //@Override
    public void onClick(View v){

    }


}
