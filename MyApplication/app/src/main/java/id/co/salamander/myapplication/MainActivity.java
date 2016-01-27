package id.co.salamander.myapplication;
import id.co.salamander.myapplication.R;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;



public class MainActivity extends ListActivity {

    private ProgressDialog pDialog;

    // URL untuk mengambil data kontak JSON
    private static String url = "http://image.titaninfra.com/test/kontak.json";

    //Nama-nama Node pada JSON
    private static final String TAG_NPM = "id";
    private static final String TAG_KONTAK = "kontak";
    private static final String TAG_NAMA = "nama";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ALAMAT = "alamat";
    private static final String TAG_JK = "jk";
    private static final String TAG_HP = "hp";
    private static final String TAG_GPA = "gpa";
    private static final String TAG_UAS = "uas";
    private static final String TAG_TUGAS = "tugas";
    private static final String TAG_KUIS = "kuis";
    // kontak JSONArray
    JSONArray kontak = null;

    // Hashmap for ListView
    ArrayList<HashMap<String, String>> contactList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button go = (Button)findViewById(R.id.rules);
        Button add = (Button)findViewById(R.id.add_new);
        Button b_refresh = (Button)findViewById(R.id.b_refresh);
        ArrayList<NameValuePair> post_parameter = new ArrayList<>();
        try {
            CustomHTTPClient.executeHttpPost("http://image.titaninfra.com/test/get_kontak.php", post_parameter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        go.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity.this, RulesActivity.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity.this,AddActivity.class);
                startActivity(i);
            }
        });

        contactList = new ArrayList<HashMap<String, String>>();

        ListView lv = getListView();

        // Action klik pada Listview
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // Mengambil data dari ListView yang dipilih
                String npm = ((TextView) view.findViewById(R.id.npm)).getText().toString();
                String nama = ((TextView) view.findViewById(R.id.nama)).getText().toString();
                String email = ((TextView) view.findViewById(R.id.email)).getText().toString();
                String hp = ((TextView) view.findViewById(R.id.hp)).getText().toString();
                String gpa = ((TextView) view.findViewById(R.id.gpa)).getText().toString();
                // Memulai memanggil ke class KontakActivity dengan beberapa data
                Intent in = new Intent(getApplicationContext(), KontakActivity.class);
                in.putExtra(TAG_NPM, npm);
                in.putExtra(TAG_NAMA, nama);
                in.putExtra(TAG_EMAIL, email);
                in.putExtra(TAG_HP, hp);
                in.putExtra(TAG_GPA, gpa);
                startActivity(in);
            }
        });
        // Menggunakan async task untuk "ngeload" data JSON
        new GetContacts().execute();
        b_refresh.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                contactList = new ArrayList<HashMap<String, String>>();

                ListView lv = getListView();

                // Action klik pada Listview
                lv.setOnItemClickListener(new OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {

                        // Mengambil data dari ListView yang dipilih
                        String npm = ((TextView) view.findViewById(R.id.npm)).getText().toString();
                        String nama = ((TextView) view.findViewById(R.id.nama)).getText().toString();
                        String email = ((TextView) view.findViewById(R.id.email)).getText().toString();
                        String hp = ((TextView) view.findViewById(R.id.hp)).getText().toString();
                        String gpa = ((TextView) view.findViewById(R.id.gpa)).getText().toString();
                        // Memulai memanggil ke class KontakActivity dengan beberapa data
                        Intent in = new Intent(getApplicationContext(), KontakActivity.class);
                        in.putExtra(TAG_NPM, npm);
                        in.putExtra(TAG_NAMA, nama);
                        in.putExtra(TAG_EMAIL, email);
                        in.putExtra(TAG_HP, hp);
                        in.putExtra(TAG_GPA, gpa);
                        startActivity(in);
                    }
                });
                // Menggunakan async task untuk "ngeload" data JSON
                new GetContacts().execute();
            }
        });
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Tolong Tunggu...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            // Creating service handler class instance
            ServiceHandler sh = new ServiceHandler();
            // Making a request to url and getting response
            sh.makeServiceCall("http://image.titaninfra.com/test/get_kontak.php", ServiceHandler.GET);
            String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    kontak = jsonObj.getJSONArray(TAG_KONTAK);

                    // looping through All Contacts
                    for (int i = 0; i < kontak.length(); i++) {
                        JSONObject c = kontak.getJSONObject(i);

                        String id = c.getString(TAG_NPM);
                        String name = c.getString(TAG_NAMA);
                        String email = c.getString(TAG_EMAIL);
                        String address = c.getString(TAG_ALAMAT);
                        String gender = c.getString(TAG_JK);
                        String hp = c.getString(TAG_HP);
                        String gpa = c.getString(TAG_GPA);

                        // tmp hashmap for single contact
                        HashMap<String, String> contact = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        contact.put(TAG_NPM, id);
                        contact.put(TAG_NAMA, name);
                        contact.put(TAG_EMAIL, email);
                        contact.put(TAG_HP, hp);
                        contact.put(TAG_GPA, gpa);

                        // adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Updating parsed JSON data into ListView
             * */
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, contactList,
                    R.layout.list_item, new String[] { TAG_NPM,TAG_NAMA, TAG_EMAIL,TAG_HP,TAG_GPA },
                    new int[] {  R.id.npm,R.id.nama,R.id.email, R.id.hp, R.id.gpa });

            setListAdapter(adapter);

        }

    }

    private class GetUser extends AsyncTask<String, Void, String> {

        private Context context1;
        ProgressDialog dialog;

        public GetUser(Context context) {
            this.context1 = context;
        }

        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(String... params) {
            String result;

            ArrayList<NameValuePair> post_parameter = new ArrayList<>();

            post_parameter.add(new BasicNameValuePair("id", params[0]));

            try {
                result = CustomHTTPClient.executeHttpPost("http://image.titaninfra.com/test/get_kontak.php", post_parameter);

            } catch (Exception e) {
                result = e.toString();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
        }
    }

}