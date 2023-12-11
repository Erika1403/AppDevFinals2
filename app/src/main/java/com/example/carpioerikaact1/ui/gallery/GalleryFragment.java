package com.example.carpioerikaact1.ui.gallery;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpioerikaact1.R;
import com.example.carpioerikaact1.databinding.FragmentHomeBinding;
import com.example.carpioerikaact1.databinding.FragmentGalleryBinding;
import com.example.carpioerikaact1.ui.home.HomeViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import yuku.ambilwarna.AmbilWarnaDialog;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    SharedPreferences sharedPreferences;
    ConstraintLayout clayout;
    Button changeBG;
    Button changeTextColor;
    RecyclerView content;
    TextView txtName;
    TextView txtSection;
    int defaultcolor;
    ArrayList<VMTile> data;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel visionMissionViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        data = populateData();
        //final TextView textView = binding.txtName;
        content = binding.rvVM;
        txtName = binding.textView2;
        txtSection = binding.textView3;
        changeBG = binding.btnChangeBackground;
        changeTextColor = binding.btnChangeTextColor;
        Drawable backgroundDrawable = root.getBackground();
        // Check if the background is a ColorDrawable (solid color)
        if (backgroundDrawable instanceof ColorDrawable) {
            // Cast to ColorDrawable
            ColorDrawable colorDrawable = (ColorDrawable) backgroundDrawable;

            // Get the color as an integer
            defaultcolor = colorDrawable.getColor();

            // Now 'backgroundColor' holds the integer representation of the background color
        }
        changeBG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenColorPicker("BGColor");
            }
        });
        changeTextColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenColorPicker("TextColor");
            }
        });
        clayout = binding.VMLayout;
        setAdapter();
        int textColor = txtName.getCurrentTextColor();
        sharedPreferences = getContext().getSharedPreferences("", Context.MODE_PRIVATE);
        if(loadColor() != defaultcolor){
            clayout.setBackgroundColor(loadColor());
        }
        if(loadTextColor() != textColor){
            txtName.setTextColor(loadTextColor());
            txtSection.setTextColor(loadTextColor());
        }
        //visionMissionViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    public void OpenColorPicker(String Ctgry){
        AmbilWarnaDialog ambilWarnaDialog = new AmbilWarnaDialog(getContext(), defaultcolor, new AmbilWarnaDialog.OnAmbilWarnaListener() {
            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                defaultcolor = color;
                if(Ctgry.equals("BGColor")){
                    clayout.setBackgroundColor(defaultcolor);
                }
                else if(Ctgry.equals("TextColor")){
                    txtName.setTextColor(defaultcolor);
                    txtSection.setTextColor(defaultcolor);
                }
                storeColor(defaultcolor, Ctgry);
            }
        });
        ambilWarnaDialog.show();
    }
    private void storeColor(int color, String Category){
        sharedPreferences = requireContext().getSharedPreferences("CarpioAppDev2", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Category, color);
        editor.commit();
    }
    private  int loadColor(){
        SharedPreferences preferences = requireContext().getSharedPreferences("CarpioAppDev2",Context.MODE_PRIVATE);
        int selectedColor =  preferences.getInt("BGColor", R.color.white);
        return  selectedColor;
    }
    private  int loadTextColor(){
        SharedPreferences preferences = requireContext().getSharedPreferences("CarpioAppDev2",Context.MODE_PRIVATE);
        int selectedColor =  preferences.getInt("TextColor", R.color.RedO);
        return  selectedColor;
    }
    private void setAdapter() {
        recyclerAdapter adapter = new recyclerAdapter(data);
        RecyclerView.LayoutManager mng = new LinearLayoutManager(content.getContext());
        content.setLayoutManager(mng);
        content.setItemAnimator(new DefaultItemAnimator());
        content.setAdapter(adapter);
    }
    private ArrayList<VMTile> populateData(){
        ArrayList<VMTile> datum = new ArrayList<>();
        datum.add(new VMTile(
                "Laguna University Vision", "Laguna University shall be a " +
                "socially responsive educational institution of choice providing holistically " +
                "developed individuals in the Asia-Pacific Region."));
        datum.add(new VMTile("Laguna University Mission", "Laguna University is committed" +
                " to produce academically prepared and technically skilled individuals who " +
                "are socially and morally upright."));
        return  datum;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
