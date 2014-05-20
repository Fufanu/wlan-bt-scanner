package fhf.phrrfb.wlanbtscanner;

import android.content.Context;
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

public class FragmentWlan extends Fragment implements OnClickListener {
	
	private Button buttonScan;
	private ListView wlanList;
	private WifiManager wifiManager;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		View view = inflater.inflate(R.layout.fragment_wlan, container, false);
		buttonScan = (Button)view.findViewById(R.id.button_wlan_scan);
		wlanList = (ListView)view.findViewById(R.id.listView_wlan);
		buttonScan.setOnClickListener(this);
		
		//wifiManager = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
