package project.martin.galgelegprojekt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Martin on 17-10-2016.
 */

public class logind_frag extends Fragment implements View.OnClickListener {
    TextView brugerNavnText, passwordText;
    EditText brugerNavnEdit, passwordEdit;
    Button logindKnap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rod = inflater.inflate(R.layout.logind, container, false);
        getActivity().setTitle("Log Ind");

        brugerNavnText = (TextView) rod.findViewById(R.id.brugernavn_text);
        brugerNavnEdit = (EditText) rod.findViewById(R.id.brugernavn_edit);

        passwordText = (TextView) rod.findViewById(R.id.password_text);
        passwordEdit = (EditText) rod.findViewById(R.id.password_edit);
        logindKnap = (Button) rod.findViewById(R.id.logind_btn);

        logindKnap.setOnClickListener(this);

        return rod;
    }
    @Override
    public void onClick(View v) {
        if(v == logindKnap){
            getFragmentManager().beginTransaction()
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    .replace(R.id.fragmentindhold, new hovedmenu_frag())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
