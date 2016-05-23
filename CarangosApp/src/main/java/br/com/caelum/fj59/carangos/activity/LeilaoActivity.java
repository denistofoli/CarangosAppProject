package br.com.caelum.fj59.carangos.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.app.CarangosApplication;
import br.com.caelum.fj59.carangos.modelo.Lance;
import br.com.caelum.fj59.carangos.tasks.BuscaLeiloesTask;
import br.com.caelum.fj59.carangos.tasks.CustomHandler;

/**
 * Created by android5853 on 21/05/16.
 */
public class LeilaoActivity extends ActionBarActivity {
    private List<Lance> lancesAteMomento = new ArrayList<Lance>();
    private Calendar horarioUltimaBusca = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leilao);

        ListView lanceList = (ListView) findViewById(R.id.lances_list);

        ArrayAdapter<Lance> adapter = new ArrayAdapter<Lance>(this, android.R.layout.simple_expandable_list_item_1,lancesAteMomento);

        lanceList.setAdapter(adapter);

        CustomHandler handler = new CustomHandler(adapter, lancesAteMomento);

        new BuscaLeiloesTask(handler, horarioUltimaBusca,(CarangosApplication) this.getApplication()).executa();
    }
}
