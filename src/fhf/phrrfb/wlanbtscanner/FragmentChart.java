package fhf.phrrfb.wlanbtscanner;

import android.content.Context;
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

public class FragmentChart extends Fragment implements OnClickListener {
	
	private NetworkObject network; // Zentraler DatenContainer
	private Context context;
	MainActivity activity;
	
	private Button buttonScan;
	private ListView bluetoothList;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		context = inflater.getContext();
		activity = (MainActivity) getActivity();
		network = activity.network;
		
		View view = inflater.inflate(R.layout.fragment_chart, container, false);
		buttonScan = (Button)view.findViewById(R.id.button_bluetooth_scan);
		bluetoothList = (ListView)view.findViewById(R.id.listView_bluetooth);
		buttonScan.setOnClickListener(this);
		
		return view;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}