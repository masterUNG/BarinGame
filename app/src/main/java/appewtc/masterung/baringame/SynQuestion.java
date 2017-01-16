package appewtc.masterung.baringame;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by masterUNG on 1/15/2017 AD.
 */

public class SynQuestion extends AsyncTask<String, Void, String>{

    private Context context;
    private static final String urlPHP = "http://swiftcodingthai.com/poy1/get_question.php";
    public ProgressDialog progressDialog;

    public SynQuestion(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,
                "Please Wait few Minus", "Continue Load Data");
    }   // onPre

    @Override
    protected String doInBackground(String... strings) {

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Category", strings[0])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(urlPHP).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            String result = response.body().string();

            return result;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }   // doInBack



}   // Main Class
