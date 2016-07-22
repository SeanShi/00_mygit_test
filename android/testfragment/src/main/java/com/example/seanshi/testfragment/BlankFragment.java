package com.example.seanshi.testfragment;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlankFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(".................................................... blankFragment.onCreate()");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    static int _counter = 1;
    Chronometer _chronometer;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
        System.out.println(".................................................... blankFragment.onCreateView()");
        Button button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(".................................................... blankFragment.click()");
                mListener.onBlankFragmentInteraction("hello, world!...." + _counter++);
                _chronometer.start();
            }
        });

        Button stop = (Button) view.findViewById(R.id.stop);
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _chronometer.stop();
            }
        });
        _chronometer = (Chronometer)view.findViewById(R.id.chronometer);
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String uri) {
        if (mListener != null) {
            mListener.onBlankFragmentInteraction(uri);
        }
    }

    @Override
    public void onInflate (Context context, AttributeSet attrs, Bundle savedInstanceState)
    {
        super.onInflate(context, attrs, savedInstanceState);
        System.out.println(".................................................... blankFragment.onInfalte()");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        System.out.println(".................................................... blankFragment.onAttach()");
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onViewCreated (View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        System.out.println(".................................................... blankFragment.onViewCreated()");
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        System.out.println(".................................................... blankFragment.onActivityCreated()");
    }

    @Override
    public void onViewStateRestored (Bundle savedInstanceState)
    {
        super.onViewStateRestored(savedInstanceState);
        System.out.println(".................................................... blankFragment.onViewStateRestored()");
    }

    @Override
    public void onStart()
    {
        super.onStart();
        System.out.println(".................................................... blankFragment.onStart()");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        System.out.println(".................................... BlankFragment.onResume()");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        System.out.println(".................................... BlankFragment.onPause()");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        System.out.println(".................................... BlankFragment.onStop()");
    }

    @Override
    public void onDestroyView()
    {
        super.onStop();
        System.out.println(".................................... BlankFragment.onDestroyView()");
    }

    @Override
    public void onDestroy()
    {
        super.onStop();
        System.out.println(".................................... BlankFragment.onDestroy()");
    }

    @Override
    public void onHiddenChanged(boolean hidden)
    {
        super.onHiddenChanged(hidden);
        System.out.println(".................................... BlankFragment.onHiddenChanged()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
        System.out.println(".................................... BlankFragment.onDetach()");
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onBlankFragmentInteraction(String string);
    }
}
