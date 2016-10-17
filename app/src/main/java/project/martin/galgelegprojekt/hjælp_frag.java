package project.martin.galgelegprojekt;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

/**
 * Created by Martin on 17-10-2016.
 */

public class hjælp_frag extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        String hjælpHtml = "<html><body>"
                + "<h1>Hj&aelig;lpesk&aelig;rm</h1>"
                + "<p>Her kunne st&aring; noget hj&aelig;lp.<br>Men den er ikke skrevet endnu.</p>";

        WebView wv = new WebView(getActivity());
        wv.loadData(hjælpHtml, "text/html", null);
        return wv;
    }
}
