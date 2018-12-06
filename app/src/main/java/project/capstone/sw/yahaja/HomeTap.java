package project.capstone.sw.yahaja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Button;

public class HomeTap extends Fragment {
    public HomeTap(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton btn_custom_match = view.findViewById(R.id.btn_custom_match);

        btn_custom_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeTap.super.getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }



}
