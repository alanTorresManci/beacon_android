package com.kontakt.sample.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.FacebookSdk;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

import static com.kontakt.sample.R.id;
import static com.kontakt.sample.R.layout;
import static com.kontakt.sample.R.string;

public class MainActivity extends BaseActivity {

    private static final int REQUEST_CODE_ENABLE_BLUETOOTH = 121;

    @InjectView(id.toolbar)
    Toolbar toolbar;
    public TextView login_but;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(layout.main_activity);
        ButterKnife.inject(this);
        login_but = (TextView) findViewById(id.login_click);
        setUpActionBar(toolbar);
        setUpActionBarTitle(getString(string.app_name));
        /*if (BluetoothUtils.isBluetoothEnabled()) {
            startActivity(new Intent(MainActivity.this, BackgroundScanActivity.class));
        } else {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_CODE_ENABLE_BLUETOOTH);
        }*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_ENABLE_BLUETOOTH && resultCode == RESULT_OK) {
            startActivity(new Intent(MainActivity.this, BackgroundScanActivity.class));
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    @OnClick(id.login_click)
    void loggeando(){
        EditText usuario;
        EditText contraseña;
        String getUsuario;
        String getContra;
        usuario = (EditText) findViewById(id.usuario);
        contraseña = (EditText) findViewById(id.contraseña);
        getUsuario = usuario.getText().toString();
        getContra = contraseña.getText().toString();
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost post = new HttpPost("https://dvlop.mx/dev/beacons/public/api/login");
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("email", getUsuario));
        params.add(new BasicNameValuePair("password", getContra));
        try {
            URL url = new URL("https://dvlop.mx/dev/beacons/public/api/login");
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            String urlParameters = params.toString();
            connection.setRequestMethod("POST");

            String output = "URL" + url;
            output += System.getProperty("line.separator") + "Requested Parameters" + urlParameters;
            Log.d(output, "aa");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }
    /*@OnClick(R.id.range_beacons)
    void startRanging() {
        startActivity(new Intent(MainActivity.this, IBeaconRangeActivity.class));
    }

    @OnClick(R.id.monitor_beacons)
    void startMonitoring() {
        startActivity(new Intent(MainActivity.this, IBeaconMonitorActivity.class));
    }

    @OnClick(R.id.multiple_proximity_manager)
    void startSimultaneousScans() {
        startActivity(new Intent(MainActivity.this, SimultaneousScanActivity.class));
    }

    @OnClick(R.id.syncable_connection)
    void startRangeWithSyncableConnection() {
        startActivity(new Intent(MainActivity.this, BeaconRangeSyncableActivity.class));
    }

    @OnClick(R.id.background_scan)
    void startForegroundBackgroundScan() {

        if (BluetoothUtils.isBluetoothEnabled()) {
            startActivity(new Intent(MainActivity.this, BackgroundScanActivity.class));
        } else {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_CODE_ENABLE_BLUETOOTH);
        }
    }

    @OnClick(R.id.range_eddystone)
    void startRangingEddystone() {
        startActivity(new Intent(MainActivity.this, EddystoneBeaconRangeActivity.class));
    }

    @OnClick(R.id.monitor_eddystone)
    void startMonitorEddystone() {
        startActivity(new Intent(MainActivity.this, EddystoneMonitorActivity.class));
    }

    @OnClick(R.id.range_all_beacons)
    void startRangingAllBeacons() {
        startActivity(new Intent(MainActivity.this, AllBeaconsMonitorActivity.class));
    }*/
}
