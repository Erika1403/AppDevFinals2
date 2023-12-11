package com.example.carpioerikaact1.ui.game;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.carpioerikaact1.databinding.FragmentGameBinding;

import java.util.ArrayList;
import java.util.Random;

public class GameFragment extends Fragment {

    private FragmentGameBinding binding;
    private Button btnGuess;
    private EditText etGuess;
    private TextView hint;
    private RecyclerView rvGuess;
    int randnumber;
    Random random = new Random();
    public recyclerAdapterGame adapterGame;
    ArrayList<GuessTile> data = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GameViewModel gameViewModel =
                new ViewModelProvider(this).get(GameViewModel.class);

        binding = FragmentGameBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        randnumber = createNumber();
        btnGuess = binding.btnGuess;
        etGuess = binding.etGuess;
        hint = binding.txtHint;
        rvGuess = binding.rvGuesses;
        setAdapter();
        btnGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnGuess.getText().toString().equals("Guess")){
                    int numguess = Integer.parseInt(etGuess.getText().toString());
                    if(randnumber == numguess){
                        hint.setText("You got it!");
                        addHistory("Correct", numguess);
                        btnGuess.setText("Try Again");
                    }
                    else if(numguess > randnumber){
                        hint.setText("Try Lower");
                        addHistory("Higher", numguess);
                    }
                    else if(numguess < randnumber){
                        hint.setText("Try Higher");
                        addHistory("Lower", numguess);
                    }
                    etGuess.setText("");
                }
                else{
                    randnumber = createNumber();
                    etGuess.setText("");
                    hint.setText("Start Guessing!");
                    btnGuess.setText("Guess");
                }
            }
        });
        Log.d("GameFragment", "onCreateView called");
        return root;
    }
    private void setAdapter() {
        adapterGame = new recyclerAdapterGame(data);
        RecyclerView.LayoutManager mng = new LinearLayoutManager(rvGuess.getContext());
        rvGuess.setLayoutManager(mng);
        rvGuess.setItemAnimator(new DefaultItemAnimator());
        rvGuess.setAdapter(adapterGame);
    }
    private void addHistory(String status, int num){
        data.add(0,new GuessTile(status, num));
        adapterGame.notifyItemInserted(0);
    }
    private int createNumber(){
        // Generate a random integer
        int randomNumber = random.nextInt(101);
        return randomNumber;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}