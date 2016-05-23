package br.com.caelum.fj59.carangos.tasks;

import android.os.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import br.com.caelum.fj59.carangos.app.CarangosApplication;
import br.com.caelum.fj59.carangos.infra.MyLog;
import br.com.caelum.fj59.carangos.webservice.WebClient;

/**
 * Created by android5853 on 21/05/16.
 */
public class BuscaLeiloesTask extends TimerTask {
    private Calendar horarioUltimaBusca;
    private CustomHandler handler;
    private CarangosApplication app;

    public BuscaLeiloesTask(CustomHandler handler,Calendar horarioUltimaBusca, CarangosApplication app) {
        this.horarioUltimaBusca = horarioUltimaBusca;
        this.handler = handler;
        this.app = app;
    }

    @Override
    public void run() {
        MyLog.i("Efetuando nova busca");

        WebClient webClient = new WebClient("leilao/leilaoid54635" +
                new SimpleDateFormat("ddMMyy-HHmmss").format(horarioUltimaBusca.getTime()),app);

        String json = webClient.get();

        MyLog.i("Lances recebidos: " + json);

        Message message = handler.obtainMessage();
        message.obj = json;
        handler.sendMessage(message);

        horarioUltimaBusca = Calendar.getInstance();
    }

    public void executa(){
        Timer timer = new Timer();
        timer.schedule(this, 0, 30*1000);
    }
}
