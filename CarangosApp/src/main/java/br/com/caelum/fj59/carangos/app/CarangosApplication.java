package br.com.caelum.fj59.carangos.app;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.modelo.Publicacao;
import br.com.caelum.fj59.carangos.tasks.RegistraAparelhoTask;

/**
 * Created by android5853 on 07/05/16.
 */
public class CarangosApplication extends Application{
    private List<AsyncTask<?, ?, ?>> tasks = new ArrayList<AsyncTask<?, ?, ?>>();
    private List<Publicacao> publicacoes = new ArrayList<>();
    private static final String REGISTRADO = "registradoNoGcm";
    private static final String ID_DO_REGISTRO = "idDoRegistro";
    private SharedPreferences preferences;


    @Override
    public void onCreate() {
        super.onCreate();

        preferences = getSharedPreferences("config", Activity.MODE_PRIVATE);
        registraNoGcm();
    }

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

    public void registraNoGcm(){
        if (!usuarioRegistrado()){
            new RegistraAparelhoTask(this).execute();
        } else {
            MyLog.i("Aparelho ja cadastrado! Seu Id é: " + preferences.getString(ID_DO_REGISTRO,null));
        }
    }

    private boolean usuarioRegistrado(){
        return preferences.getBoolean(REGISTRADO,false);
    }

    public void lidaComRespostaDoRegistroNoServidor(String registro){
        if (registro != null){
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(REGISTRADO,true);
            editor.putString(ID_DO_REGISTRO,registro);
            editor.commit();
        }
    }

}
