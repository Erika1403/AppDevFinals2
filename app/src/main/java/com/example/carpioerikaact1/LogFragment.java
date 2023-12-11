package com.example.carpioerikaact1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogFragment extends Fragment {

    private ViewGroup container;
    private LayoutInflater inflater;
    private EditText etName;
    private EditText etSection;
    private Button btnProceed;
    public LogFragment(){

    }
    public View InitializeUserInterface(){
        View view;

        //check if layout was already loaded
        if(container != null){
            container.removeAllViewsInLayout();
        }
        //Getting the screen orientation
        int orientation = getActivity().getResources().getConfiguration().orientation;

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            view = inflater.inflate(R.layout.fragment_log, container, false);
        }
        else if(orientation == Configuration.ORIENTATION_LANDSCAPE){
            view = inflater.inflate(R.layout.fragment_log_horizontal, container, false);
        }
        else{
            view = inflater.inflate(R.layout.fragment_log, container, false);
        }

        //Instatiate controls in fragments
        etName = view.findViewById(R.id.etName);
        etSection = view.findViewById(R.id.etSection);
        btnProceed = view.findViewById(R.id.btnProceed);
        btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = checkDetails(etName.getText().toString().replace(' ', '_'), etSection.getText().toString());
                if(result){
                    //openVisionMission();
                    openToNavigation();
                }
                else{
                    Toast.makeText(getContext(), "Wrong Credentials", Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }
    public boolean checkDetails(String name, String section){
        return (name.equalsIgnoreCase("erika_b._carpio") || name.equalsIgnoreCase("erika_carpio") ) && section.equalsIgnoreCase("bscs - ds 3a");
    }
    public void openToNavigation(){
        if(isAdded()){
            Intent intent = new Intent(requireContext(), ActivityNav.class);
            startActivity(intent);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceStates){
        this.container = container;
        this.inflater = inflater;
        Log.d("logfragmentMain", "onCreateView called");
        return InitializeUserInterface();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        String preserveName = etName.getText().toString();
        String preserveSec = etSection.getText().toString();

        View view = InitializeUserInterface();
        container.addView(view);
        etName.setText(preserveName);
        etSection.setText(preserveSec);


        super.onConfigurationChanged(newConfig);
    }
}