package project.martin.galgelegprojekt;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Martin on 17-10-2016.
 */

public class spil_frag extends Fragment implements View.OnClickListener {
    Galgelogik logik = new Galgelogik();
    private ImageView iw;
    private TextView info, scoreWin, scoreLoose;
    private Button gætKnap, spilIgen, resetScore;
    private EditText edit;

    SharedPreferences prefs;

    public spil_frag (){
        System.out.println("spilfrag oprettet");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        System.out.println("spilfragment oncreateview");
        ScrollView sw = new ScrollView(getActivity());
        TableLayout tl = new TableLayout(getActivity());
        LinearLayout ll = new LinearLayout(getActivity());
        LinearLayout ll2 = new LinearLayout(getActivity());
        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        System.out.println("winscore = " + prefs.getInt("Score", prefs.getInt("Score", 0)));
        System.out.println("loosescore = " + prefs.getInt("ScoreLoose", prefs.getInt("ScoreLoose", 0)));

        new AsyncTask() {
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    logik.hentOrdFraDr();
                    return "ordene blev hentet";
                } catch (Exception e) {
                    e.printStackTrace();
                    return e;
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                System.out.println("resultat: \n" + resultat);
                opdaterSkærm();
            }
        }.execute();

        info = new TextView(getActivity());
        info.setText("Velkommen til Galgelegen!" +
                "\nHeld og lykke :)\n");
        tl.addView(info);

        iw = new ImageView(getActivity());
        iw.setImageResource(R.drawable.galge);
        tl.addView(iw);

        edit = new EditText(getActivity());
        edit.setHint("Skriv ét bogstav...");
        edit.setLayoutParams(new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        ll.addView(edit);

        gætKnap = new Button(getActivity());
        gætKnap.setText("Gæt");
        gætKnap.setBackgroundColor(Color.parseColor("#03A9F4"));
        gætKnap.setLayoutParams(new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 5f));
        ll.addView(gætKnap);

        tl.addView(ll);

        spilIgen = new Button(getActivity());
        spilIgen.setText("Spil igen");
        spilIgen.setVisibility(View.INVISIBLE);
        tl.addView(spilIgen);

        scoreWin = new TextView(getActivity());
        scoreWin.setText("Antal vundne spil: " + prefs.getInt("Score", 0) );
        scoreWin.setLayoutParams(new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        scoreWin.setTextColor(Color.RED);
        ll2.addView(scoreWin);

        scoreLoose = new TextView(getActivity());
        scoreLoose.setText("Antal tabte spil: " + prefs.getInt("ScoreLoose", 0) );
        scoreLoose.setTextColor(Color.RED);
        scoreWin.setLayoutParams(new TableLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
        ll2.addView(scoreLoose);

        tl.addView(ll2);


        /*//Knap til at nultille score
        resetScore = new Button(getActivity());
        resetScore.setText("Nulstil scorer");
        tl.addView(resetScore);
        resetScore.setOnClickListener(this);*/

        gætKnap.setOnClickListener(this);
        spilIgen.setOnClickListener(this);

        sw.addView(tl);
        return sw;
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
            getFragmentManager().popBackStack();

            getFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentindhold, new spil_frag())
                    .addToBackStack(null)
                    .commit();

        }
        /*//Onclick metode til at nulstille scoren
        else if(v == resetScore){
            prefs.edit().putInt("ScoreLoose", 0).commit();
            prefs.edit().putInt("Score", 0).commit();
            System.out.println("Scorene er blevet nulstillet!");
            opdaterSkærm();
        }*/

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
            prefs.edit().putInt("Score", prefs.getInt("Score", 0)+1).commit();
            info.append("\nDu har vundet");
            Toast.makeText(getActivity(), "Du har vundet", Toast.LENGTH_SHORT).show();
            edit.setVisibility(View.INVISIBLE);
            gætKnap.setVisibility(View.INVISIBLE);
            spilIgen.setVisibility(View.VISIBLE);
        }
        if (logik.erSpilletTabt()) {
            prefs.edit().putInt("ScoreLoose", prefs.getInt("ScoreLoose", 0)+1).commit();
            info.append("\nDu har tabt, ordet var : " + logik.getOrdet());
            Toast.makeText(getActivity(), "Du har tabt", Toast.LENGTH_SHORT).show();
            edit.setVisibility(View.INVISIBLE);
            gætKnap.setVisibility(View.INVISIBLE);
            spilIgen.setVisibility(View.VISIBLE);
        }
    }
}
