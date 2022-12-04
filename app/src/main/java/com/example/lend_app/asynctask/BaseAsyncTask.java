package com.example.lend_app.asynctask;

//import android.app.ProgressDialog;
import android.os.AsyncTask;

public class BaseAsyncTask<T> extends AsyncTask<Void, Void, T> {
  private final ExecutaListener<T> executaListener;
  private final FinalizadaListener<T> finalizadaListener;

  public BaseAsyncTask(ExecutaListener<T> executaListener, FinalizadaListener<T> finalizadaListener) {
    this.executaListener = executaListener;
    this.finalizadaListener = finalizadaListener;
  }

  @Override
  protected T doInBackground(Void... voids) {
    return executaListener.quandoExecuta();
  }

  @Override
  protected void onPostExecute(T resultado) {
    super.onPostExecute(resultado);
    finalizadaListener.quandoFinalizada(resultado);
  }

//  @Override
//  protected void onPreExecute() {
//    super.onPreExecute();
//    progressDialog.setCancelable(true);
//    progressDialog.setMessage("Loading...");
//    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//    progressDialog.setProgress(0);
//    progressDialog.show();
//  }

  public interface ExecutaListener<T> {
    T quandoExecuta();
  }

  public interface FinalizadaListener<T> {
    void quandoFinalizada(T resultado);
  }
}
