package project.capstone.sw.yahaja;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Button;

import project.capstone.sw.yahaja.http.*;

public class HomeTap extends Fragment {
    public HomeTap(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ImageButton btn_custom_match = view.findViewById(R.id.btn_custom_match);

        ImageButton btn_random_match = view.findViewById(R.id.btn_random_match);

        btn_custom_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeTap.super.getActivity(), MapActivity.class);
                startActivity(intent);
            }
        });

        btn_random_match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //query
                RequestHttp requestHttp = new RequestHttp();

                StoredUserSession storedUserSession = new StoredUserSession(getActivity().getApplicationContext());
                String u_id = storedUserSession.getUserSession();

                String randomMatchRequest = "http://13.59.95.38:3000/random_matching?account_id=" + u_id;

                requestHttp.requestGet(randomMatchRequest);

                Toast.makeText(getActivity(), "매칭이 수락되었습니다!", Toast.LENGTH_SHORT).show();
            }
        });

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.spinnerItem, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(
                new NothingSelectedSpinnerAdapter(adapter, R.layout.contact_spinner_row_nothing_selected, this.getContext()));


        return view;
    }



}
