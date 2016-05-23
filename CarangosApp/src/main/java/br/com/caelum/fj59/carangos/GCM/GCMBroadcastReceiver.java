package br.com.caelum.fj59.carangos.GCM;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import java.util.List;

import br.com.caelum.fj59.carangos.R;
import br.com.caelum.fj59.carangos.activity.LeilaoActivity;
import br.com.caelum.fj59.carangos.infra.MyLog;

/**
 * Created by android5853 on 21/05/16.
 */
public class GCMBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MyLog.i("Chegou a mensagem do GCM!");

        //if (appEstaRodando(context)){
            //AlertDialog.Builder builder = new AlertDialog.Buil
            //builder.setMessage("Desafio").show();
        //} else {
            String mensagem = (String) intent.getExtras().getSerializable("message");

            Intent irParaLeilao = new Intent(context, LeilaoActivity.class);

            PendingIntent acaoPendente = PendingIntent.getActivity(context, 0, irParaLeilao, PendingIntent.FLAG_CANCEL_CURRENT);

            Notification notificacao = new Notification.Builder(context)
                    .setContentTitle("Um novo leilão começou!")
                    .setContentText("Leilão: " + mensagem)
                    .setSmallIcon(R.drawable.ic_launcher)
                    .setContentIntent(acaoPendente)
                    .setAutoCancel(true)
                    .build();

            NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
            manager.notify(Constantes.ID_NOTIFICACAO,notificacao);
        //}
    }

    private boolean appEstaRodando(Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks =am.getRunningTasks(1);

        if (tasks.isEmpty()){
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())){
                return false;
            }
        }
        return true;
    }
}
