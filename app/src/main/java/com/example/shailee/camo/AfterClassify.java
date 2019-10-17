package com.example.shailee.camo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class AfterClassify extends Fragment {

    public  AfterClassify(){

    }
    Leave result;
    @SuppressLint("ValidFragment")
    public AfterClassify(Leave result) {
        this.result =result;
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.after_classify,container,false);
        if(result!=null){
            ((TextView) root.findViewById(R.id.common_name)).setText(String.format("Common Name:%s", result.common_name));
            ((TextView) root.findViewById(R.id.scientific_name)).setText( result.scientific_name);
            ((TextView) root.findViewById(R.id.genus)).setText( result.genus);

            ((TextView) root.findViewById(R.id.Benefits)).setText(result.benefits);
        }
        return  root;

    }

    // TODO: Rename method, update argument and hook method into UI event

}
