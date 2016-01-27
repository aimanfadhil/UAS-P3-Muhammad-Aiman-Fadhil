package id.co.salamander.myapplication;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/**
 * Created by AimanFadhil on 12/17/2015.
 */
public class RulesActivity extends Activity {

    // JSON node keys

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        final EditText tugas = (EditText) findViewById(R.id.tugas);
        final EditText kuis = (EditText) findViewById(R.id.kuis);
        final EditText uts = (EditText) findViewById(R.id.uts);
        final EditText uas = (EditText) findViewById(R.id.uas);

        Button bt_submit = (Button) findViewById(R.id.save_rules);
        Button go = (Button) findViewById(R.id.r_back);
        go.setOnClickListener(new View.OnClickListener() {
            // TODO Auto-generated method stub
            public void onClick(View v) {
                finish();
            }
        });
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new InsertUser(RulesActivity.this).execute(tugas.getText().toString(), kuis.getText().toString(), uts.getText().toString(), uas.getText().toString());

            }
        });
        new GetRules(RulesActivity.this).execute("");

    }

    private class GetRules extends AsyncTask<String, Void, String> {

        private Context context1;
        ProgressDialog dialog;

        public GetRules(Context context) {
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

            post_parameter.add(new BasicNameValuePair("id", params[0]));

            try {
                result = CustomHTTPClient.executeHttpPost("http://image.titaninfra.com/test/get_rules.php", post_parameter);

            } catch (Exception e) {
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            super.onPostExecute(s);

            EditText ed_tugas= (EditText) findViewById(R.id.tugas);
            EditText ed_kuis= (EditText) findViewById(R.id.kuis);
            EditText ed_uas= (EditText) findViewById(R.id.uas);
            EditText ed_uts= (EditText) findViewById(R.id.uts);


            if (s.equals("ga ada") || s.equals("failed")) {
                AlertDialog d = new AlertDialog.Builder(context1).create();
                d.setMessage(s);
                d.show();
            } else {
                try {
                    JSONArray ja = new JSONArray(s);
                    for (int i=0;i<ja.length();i++) {
                        JSONObject jo = ja.getJSONObject(i);
                        ed_uts.setText(jo.getString("uts"));
                        ed_tugas.setText(jo.getString("tugas"));
                        ed_kuis.setText(jo.getString("kuis"));
                        ed_uas.setText(jo.getString("uas"));
                    }
                } catch (JSONException e) {}
            }
        }
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

            post_parameter.add(new BasicNameValuePair("tugas", params[0]));
            post_parameter.add(new BasicNameValuePair("kuis", params[1]));
            post_parameter.add(new BasicNameValuePair("uts", params[2]));
            post_parameter.add(new BasicNameValuePair("uas", params[3]));

            try {
                result = CustomHTTPClient.executeHttpPost("http://image.titaninfra.com/test/set_rules.php", post_parameter);

            } catch (Exception e) {
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            super.onPostExecute(s);
            Toast.makeText(RulesActivity.this, s,
                    Toast.LENGTH_LONG).show();
            finish();
        }
    }
}