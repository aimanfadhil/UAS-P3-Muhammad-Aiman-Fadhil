package id.co.salamander.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * Created by AimanFadhil on 12/17/2015.
 */
public class AddActivity extends Activity {

    // JSON node keys

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final EditText npm = (EditText) findViewById(R.id.add_npm);
        final EditText nama = (EditText) findViewById(R.id.add_nama);
        final EditText alamat = (EditText) findViewById(R.id.add_alamat);
        final EditText mobile = (EditText) findViewById(R.id.add_mobile);
        final EditText email = (EditText) findViewById(R.id.add_email);
        final EditText tugas = (EditText) findViewById(R.id.add_tugas);
        final EditText kuis = (EditText) findViewById(R.id.add_kuis);
        final EditText uts = (EditText) findViewById(R.id.add_uts);
        final EditText uas = (EditText) findViewById(R.id.add_uas);

        Button bt_submit = (Button) findViewById(R.id.bt_submit);
        Button go = (Button) findViewById(R.id.a_back);
        go.setOnClickListener(new View.OnClickListener() {
            // TODO Auto-generated method stub
            public void onClick(View v) {
                finish();
            }
        });
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new InsertUser(AddActivity.this).execute(npm.getText().toString(),nama.getText().toString(),alamat.getText().toString(),mobile.getText().toString(),email.getText().toString(),tugas.getText().toString(), kuis.getText().toString(), uts.getText().toString(), uas.getText().toString());

            }
        });
    }

    private class InsertUser extends AsyncTask<String, Void, String> {

        private Context context1;
        ProgressDialog dialog;

        public InsertUser(Context context) {
            this.context1 = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(context1);
            dialog.setMessage("Tunggu sebentar ya..");
            dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String result;

            ArrayList<NameValuePair> post_parameter = new ArrayList<>();
            post_parameter.add(new BasicNameValuePair("npm", params[0]));
            post_parameter.add(new BasicNameValuePair("nama", params[1]));
            post_parameter.add(new BasicNameValuePair("alamat", params[2]));
            post_parameter.add(new BasicNameValuePair("mobile", params[3]));
            post_parameter.add(new BasicNameValuePair("email", params[4]));
            post_parameter.add(new BasicNameValuePair("tugas", params[5]));
            post_parameter.add(new BasicNameValuePair("kuis", params[6]));
            post_parameter.add(new BasicNameValuePair("uts", params[7]));
            post_parameter.add(new BasicNameValuePair("uas", params[8]));

            try {
                result = CustomHTTPClient.executeHttpPost("http://image.titaninfra.com/test/add_new.php", post_parameter);

            } catch (Exception e) {
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            super.onPostExecute(s);
            Toast.makeText(AddActivity.this, s,
                    Toast.LENGTH_LONG).show();
          finish();
        }
    }
}