package br.com.caelum.fj59.carangos.activity;


import android.support.v7.app.ActionBarActivity;
import br.com.caelum.fj59.carangos.app.CarangosApplication;

/**
 * Created by android5853 on 07/05/16.
 */
public class BaseActivity extends ActionBarActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //((CarangosApplication) getApplication()).finalizaTasks();
    }
}
