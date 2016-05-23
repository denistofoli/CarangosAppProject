package br.com.caelum.fj59.carangos.GCM;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

/**
 * Created by android5853 on 21/05/16.
 */
public class InformacoesDoUsuario {
    public static String getEmail(Context context){
        AccountManager accountManager = AccountManager.get(context);
        Account account = getAccount(accountManager);

        if (account == null){
            return null;
        }
        return account.name;
    }

    private static Account getAccount(AccountManager accountManager){
        Account[] accounts = accountManager.getAccountsByType("com.google");
        Account account = null;

        if (accounts.length > 0) {
            account = accounts[0];
        }
        return account;
    }
}
