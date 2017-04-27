package project.martin.galgelegprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Martin on 17-10-2016.
 */

public class singlePlayer_frag extends Fragment implements View.OnClickListener {
    Button gammeltSpilKnap, nytSpilKnap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.singleplayer, container, false);
        getActivity().setTitle("Singleplayer");

        gammeltSpilKnap = (Button) rod.findViewById(R.id.gammeltSpil_btn);
        nytSpilKnap = (Button) rod.findViewById(R.id.nytSpil_btn);

        gammeltSpilKnap.setOnClickListener(this);
        nytSpilKnap.setOnClickListener(this);

        return rod;
    }
    @Override
    public void onClick(View v) {
        if(v == nytSpilKnap){
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragmentindhold, new spil_frag())
                    .addToBackStack(null)
                    .commit();
        }
        else if(v == gammeltSpilKnap) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragmentindhold, new spil_frag())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
