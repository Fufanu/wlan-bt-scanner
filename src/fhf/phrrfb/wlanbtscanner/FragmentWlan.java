package fhf.phrrfb.wlanbtscanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class FragmentWlan extends Fragment implements OnClickListener {
	
	private Button buttonScan;
	private Context context;
	private ListView wlanList;
	private WifiManager wifi;
	int size = 0;
	List<ScanResult> scanResults;
	
	String ITEM_KEY = "key";
	ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
	ScanResultsAdapter  adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		context = inflater.getContext();
		
		View view = inflater.inflate(R.layout.fragment_wlan, container, false);
		buttonScan = (Button)view.findViewById(R.id.button_wlan_scan);
		wlanList = (ListView)view.findViewById(R.id.listView_wlan);
		buttonScan.setOnClickListener(this);
		
		//wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		wifi = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
		
        if (wifi.isWifiEnabled() == false)
        {
            Toast.makeText(context.getApplicationContext(), "wifi is disabled..making it enabled", Toast.LENGTH_LONG).show();
            wifi.setWifiEnabled(true);
        }
        //adapter = new ScanResultsAdapter(context, scanResults);
        wlanList.setAdapter(this.adapter);
        
        context.registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context c, Intent intent) 
            {
            	scanResults = wifi.getScanResults();
            	//size = scanResults.size();
            	adapter.notifyDataSetChanged();
            }

        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
        
		return view;
	}
	
	public void scanNetworks() {
		boolean scan = wifi.startScan();

		if(scan) {
			scanResults = wifi.getScanResults();
			Toast.makeText(context, getString(R.string.networks_found_msg, scanResults.size()), Toast.LENGTH_LONG).show();
		} else
			switch(wifi.getWifiState()) {
			case WifiManager.WIFI_STATE_DISABLING:
				Toast.makeText(context, R.string.wifi_disabling_msg, Toast.LENGTH_LONG).show();
				break;
			case WifiManager.WIFI_STATE_DISABLED:
				Toast.makeText(context, R.string.wifi_disabled_msg, Toast.LENGTH_LONG).show();
				break;
			case WifiManager.WIFI_STATE_ENABLING:
				Toast.makeText(context, R.string.wifi_enabling_msg, Toast.LENGTH_LONG).show();
				break;
			case WifiManager.WIFI_STATE_ENABLED:
				Toast.makeText(context, R.string.wifi_enabled_msg, Toast.LENGTH_LONG).show();
				break;
			case WifiManager.WIFI_STATE_UNKNOWN:
				Toast.makeText(context, R.string.wifi_unknown_state_msg, Toast.LENGTH_LONG).show();
				break;
			}

	}

	@Override
	public void onClick(View view) {
		arraylist.clear();          
        wifi.startScan();

        Toast.makeText(context, "Scanning...." + size, Toast.LENGTH_SHORT).show();
        try 
        {
        	scanNetworks();
            /*size = size - 1;
            while (size >= 0) 
            {   
                HashMap<String, String> item = new HashMap<String, String>();                       
                item.put(ITEM_KEY, scanResults.get(size).SSID + "  " + scanResults.get(size).capabilities);

                arraylist.add(item);
                size--;
                adapter.notifyDataSetChanged();               
            } */  
        }
        catch (Exception e)
        { } 
		
	}

}
