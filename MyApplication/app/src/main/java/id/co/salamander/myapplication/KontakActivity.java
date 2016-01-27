package id.co.salamander.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.*;
import android.view.View;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class KontakActivity  extends Activity {
	
	// JSON node keys

    private static final String ar_uas = "uas";
    private static final String ar_tugas = "tugas";
    private static final String ar_UTS = "uts";
    private static final String ar_KUIS = "kuis";
    private static final String TAG_NPM = "id";
	private static final String TAG_NAMA = "nama";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_HP = "hp";

    ArrayList<HashMap<String, String>> resarray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kontak);
        
        // getting intent data
        Intent in = getIntent();
        // Get JSON values from previous intent
        final String npm = in.getStringExtra(TAG_NPM);
        String name = in.getStringExtra(TAG_NAMA);
        String email = in.getStringExtra(TAG_EMAIL);
        String mobile = in.getStringExtra(TAG_HP);

        // Displaying all values on the screen
        TextView lblnpm = (TextView) findViewById(R.id.npm_label);
        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblEmail = (TextView) findViewById(R.id.email_label);
        TextView lblMobile = (TextView) findViewById(R.id.mobile_label);


        final EditText ed_tugas= (EditText) findViewById(R.id.tugas_label);
        final EditText ed_kuis= (EditText) findViewById(R.id.kuis_label);
        final EditText ed_uas= (EditText) findViewById(R.id.uas_label);
        final EditText ed_uts= (EditText) findViewById(R.id.uts_label);

        lblnpm.setText(npm);
        lblName.setText(name);
        lblEmail.setText(email);
        lblMobile.setText(mobile);

        Button submit = (Button)findViewById(R.id.submit_menu);
        Button del = (Button)findViewById(R.id.delete);
        Button go = (Button)findViewById(R.id.back_menu);
        new GetUser(KontakActivity.this).execute(npm.toString());

        go.setOnClickListener(new View.OnClickListener() {
            // TODO Auto-generated method stub
            public void onClick(View v) {
                finish();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            // TODO Auto-generated method stub
            public void onClick(View v) {
                new InsertUser(KontakActivity.this).execute(ed_tugas.getText().toString(), ed_kuis.getText().toString(), ed_uts.getText().toString(), ed_uas.getText().toString(), npm.toString());
                finish();
            }
        });

        del.setOnClickListener(new View.OnClickListener() {
            // TODO Auto-generated method stub
            public void onClick(View v) {
                new DeleteUser(KontakActivity.this).execute(npm.toString());
                finish();
            }
        });
    }
    private class GetUser extends AsyncTask<String, Void, String> {

        private Context context1;
        ProgressDialog dialog;

        public GetUser(Context context) {
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
                result = CustomHTTPClient.executeHttpPost("http://image.titaninfra.com/test/get_nilai.php", post_parameter);

            } catch (Exception e) {
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            super.onPostExecute(s);

            EditText ed_tugas= (EditText) findViewById(R.id.tugas_label);
            EditText ed_kuis= (EditText) findViewById(R.id.kuis_label);
            EditText ed_uas= (EditText) findViewById(R.id.uas_label);
            EditText ed_uts= (EditText) findViewById(R.id.uts_label);


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

    private class DeleteUser extends AsyncTask<String, Void, String> {

        private Context context1;
        ProgressDialog dialog;

        public DeleteUser(Context context) {
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
                result = CustomHTTPClient.executeHttpPost("http://image.titaninfra.com/test/delete_user.php", post_parameter);

            } catch (Exception e) {
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            super.onPostExecute(s);
            AlertDialog d = new AlertDialog.Builder(context1).create();
            String msg = "";
            setContentView(R.layout.activity_kontak);

            if (s.equals("1")) {
                d.setMessage(s);
                d.show();
            }
            else{
                d.setMessage(s);
                d.show();
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

            post_parameter.add(new BasicNameValuePair("kuis", params[0]));
            post_parameter.add(new BasicNameValuePair("tugas", params[1]));
            post_parameter.add(new BasicNameValuePair("uts", params[2]));
            post_parameter.add(new BasicNameValuePair("uas", params[3]));
            post_parameter.add(new BasicNameValuePair("id", params[4]));

            try {
                result = CustomHTTPClient.executeHttpPost("http://image.titaninfra.com/test/set_nilai.php", post_parameter);

            } catch (Exception e) {
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            dialog.dismiss();
            super.onPostExecute(s);
            AlertDialog d = new AlertDialog.Builder(context1).create();
            String msg = "";
            setContentView(R.layout.activity_kontak);

            if (s.equals("1")) {
                d.setMessage(s);
                d.show();
            }
            else{
                d.setMessage(s);
                d.show();
            }
        }
    }
}
