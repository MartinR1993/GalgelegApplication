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

public class hovedmenu_frag extends Fragment implements View.OnClickListener {
    Button tilfoejknap, spilKnap, indstillinger;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.hovedmenu, container, false);

        spilKnap = (Button) rod.findViewById(R.id.spilKnap);
        tilfoejknap = (Button) rod.findViewById(R.id.tilfoejOrd);
        indstillinger = (Button) rod.findViewById(R.id.indstillingerKnap);

        spilKnap.setOnClickListener(this);
        tilfoejknap.setOnClickListener(this);
        indstillinger.setOnClickListener(this);

        return rod;
    }
    @Override
    public void onClick(View v) {
        if(v == spilKnap){
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new spil_frag())
                    .addToBackStack(null)
                    .commit();
        }
        else if(v == tilfoejknap) {
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new tilfoejOrd_frag())
                    .addToBackStack(null)
                    .commit();
        }
        else{
            Intent i = new Intent(getActivity(), indstillinger_akt.class);
            startActivity(i);        }
    }
}
