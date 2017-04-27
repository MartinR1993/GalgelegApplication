package project.martin.galgelegprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Martin on 17-10-2016.
 */

public class hovedmenu_frag extends Fragment implements View.OnClickListener {
    Button singlePlayerKnap, multiPlayerKnap, indstillingerKnap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.hovedmenu, container, false);
        setHasOptionsMenu(true);
        getActivity().setTitle("Hovedmenu");

        multiPlayerKnap = (Button) rod.findViewById(R.id.multiPlayer_btn);
        singlePlayerKnap = (Button) rod.findViewById(R.id.singlePlayer_btn);

        multiPlayerKnap.setOnClickListener(this);
        singlePlayerKnap.setOnClickListener(this);

        return rod;
    }
    @Override
    public void onClick(View v) {
        if(v == multiPlayerKnap){
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragmentindhold, new multiPlayer_frag())
                    .addToBackStack(null)
                    .commit();
        }
        else if(v == singlePlayerKnap) {
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragmentindhold, new singlePlayer_frag())
                    .addToBackStack(null)
                    .commit();
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.settings){
            Intent i = new Intent(getActivity(), indstillinger_akt.class);
            startActivity(i);
            return true;
        }
        else if(id == R.id.logout){
            Fragment fragment = new logind_frag();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, fragment)
                    .commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
