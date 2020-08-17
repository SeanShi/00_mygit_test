package com.example.testmemory;

import android.app.ActivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import androidx.navigation.fragment.NavHostFragment;

import com.google.gson.Gson;

import static android.content.Context.ACTIVITY_SERVICE;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    int i = 1;
    char arr[];
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView usage = view.findViewById(R.id.memory_usage);
        final EditText editText = view.findViewById(R.id.memory);

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
*/
                String text = editText.getText().toString();
                int memory = Integer.parseInt(text);
                arr = new char[memory*1024*1024];
                ActivityManager.MemoryInfo memoryInfo = getAvailableMemory();

                usage.setText(new Gson().toJson(getAvailableMemory()));

            }
        });
    }

    private ActivityManager.MemoryInfo getAvailableMemory() {
        ActivityManager activityManager = (ActivityManager) this.getActivity().getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo;
    }
}
