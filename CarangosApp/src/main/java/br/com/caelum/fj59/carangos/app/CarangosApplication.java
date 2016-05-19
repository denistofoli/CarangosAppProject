package br.com.caelum.fj59.carangos.app;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj59.carangos.modelo.Publicacao;

/**
 * Created by android5853 on 07/05/16.
 */
public class CarangosApplication extends Application{
    private List<AsyncTask<?, ?, ?>> tasks = new ArrayList<AsyncTask<?, ?, ?>>();
    private List<Publicacao> publicacoes = new ArrayList<>();


    public List<Publicacao> getPublicacoes() {
        return this.publicacoes;
    }

    public void registra(AsyncTask<?, ?, ?> task){
        tasks.add(task);
    }

    public void desregistra(AsyncTask<?, ?, ?> task){
        tasks.remove(task);
    }

    public void finalizaTasks(){
        for (AsyncTask task : tasks){
            task.cancel(true);
        }

        tasks.clear();

        Log.i("Carangos","*** TASKS FINALIZADAS ***");
    }

    @Override
    public void onTerminate() {
        finalizaTasks();

        super.onTerminate();
    }
}
