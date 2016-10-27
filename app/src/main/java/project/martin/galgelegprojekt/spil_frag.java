package project.martin.galgelegprojekt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Martin on 17-10-2016.
 */

public class spil_frag extends Fragment implements View.OnClickListener {
    Galgelogik logik = new Galgelogik();
    private ImageView iw;
    private TextView info;
    private Button gætKnap, spilIgen;
    private EditText edit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        TableLayout tl = new TableLayout(getActivity());

        info = new TextView(getActivity());
        info.setText("Velkommen til Galgelegen!" +
                "\nDu skal gætte dette ord: "+logik.getSynligtOrd() +
                "\nHeld og lykke :)\n");
        tl.addView(info);

        iw = new ImageView(getActivity());
        iw.setImageResource(R.drawable.galge);
        tl.addView(iw);

        edit = new EditText(getActivity());
        edit.setHint("Skriv ét bogstav...");
        tl.addView(edit);

        gætKnap = new Button(getActivity());
        gætKnap.setText("Gæt");
        tl.addView(gætKnap);

        spilIgen = new Button(getActivity());
        spilIgen.setText("Spil igen");
        spilIgen.setVisibility(View.INVISIBLE);
        tl.addView(spilIgen);

        gætKnap.setOnClickListener(this);
        spilIgen.setOnClickListener(this);

        return tl;
    }

    @Override
    public void onClick(View v) {
        if(v == gætKnap) {
            String bogstav = edit.getText().toString();
            if (bogstav.length() != 1) {
                edit.setError("Skriv præcis ét bogstav");
                return;
            }

            logik.gætBogstav(bogstav);
            edit.setText("");
            edit.setError(null);
            opdaterSkærm();
        }
        else if(v == spilIgen){
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, new spil_frag())
                    .addToBackStack(null)
                    .commit();
        }

    }

    private void opdaterSkærm(){
        info.setText("Gæt ordet: " + logik.getSynligtOrd());
        info.append("\nDu har " + logik.getAntalForkerteBogstaver() + " forkerte:" + logik.getBrugteBogstaver());

        if(logik.getAntalForkerteBogstaver() != 0) {
            if (logik.getAntalForkerteBogstaver() == 1){
                iw.setImageResource(R.drawable.forkert1);
                info.append("\nDu må kun lave 6 fejl mere");
            }
            else if(logik.getAntalForkerteBogstaver() == 2){
                iw.setImageResource(R.drawable.forkert2);
                info.append("\nDu må kun lave 5 fejl mere");
            }
            else if(logik.getAntalForkerteBogstaver() == 3){
                iw.setImageResource(R.drawable.forkert3);
                info.append("\nDu må kun lave 4 fejl mere");
            }
            else if(logik.getAntalForkerteBogstaver() == 4){
                iw.setImageResource(R.drawable.forkert4);
                info.append("\nDu må kun lave 3 fejl mere");
            }
            else if(logik.getAntalForkerteBogstaver() == 5){
                iw.setImageResource(R.drawable.forkert5);
                info.append("\nDu må kun lave 2 fejl mere");
            }
            else if(logik.getAntalForkerteBogstaver() == 6){
                iw.setImageResource(R.drawable.forkert6);
                info.append("\nSidste chance!!");
            }
            else if(logik.getAntalForkerteBogstaver() == 7)
                iw.setImageResource(R.drawable.forkert7);
        }
        if (logik.erSpilletVundet()) {
            info.append("\nDu har vundet");
            Toast.makeText(getActivity(), "Du har vundet", Toast.LENGTH_SHORT).show();
            gætKnap.setVisibility(View.INVISIBLE);
            spilIgen.setVisibility(View.VISIBLE);
        }
        if (logik.erSpilletTabt()) {
            info.append("\nDu har tabt, ordet var : " + logik.getOrdet());
            Toast.makeText(getActivity(), "Du har tabt", Toast.LENGTH_SHORT).show();
            gætKnap.setVisibility(View.INVISIBLE);
            spilIgen.setVisibility(View.VISIBLE);
        }
    }
}
