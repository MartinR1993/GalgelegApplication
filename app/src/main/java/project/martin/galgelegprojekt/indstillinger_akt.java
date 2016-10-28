package project.martin.galgelegprojekt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class indstillinger_akt extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_indstillinger_akt);

        setTitle("Indstillinger");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String[] indstillinger = {"Hjælp", "Skift sprog", "Om appen"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, indstillinger);

        ListView listView = new ListView(this);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        setContentView(listView);
    }


    public void onItemClick(AdapterView<?> liste, View v, int position, long id) {
        if(position == 1){

        }
        Toast.makeText(this, "Ikke implementeret endnu", Toast.LENGTH_SHORT).show();
    }

    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
