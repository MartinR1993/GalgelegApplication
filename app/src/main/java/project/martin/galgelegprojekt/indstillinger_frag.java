package project.martin.galgelegprojekt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

/**
 * Created by Martin on 27-10-2016.
 */

public class indstillinger_frag extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TableLayout tl = new TableLayout(getActivity());

        TextView text = new TextView(getActivity());
        text.setText("Hjælpsiden");

        return tl;
    }
}

