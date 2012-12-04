package com.example.upd;

import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.Menu;

public class MapViewActivity extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.activity_map_view);
		
		
		MapView mapview = (MapView)findViewById(R.id.themap);
		
		Intent intent = getIntent();
		
		mapview.setBuiltInZoomControls(true);
		
		final GeoPoint point = new GeoPoint((int) (38.897888*1E6), (int) (-77.050424*1E6));
		OverlayItem overlayitem = new OverlayItem(point, "47688507", "DEC-23 at 23:00  Ivory Tower");
		
		final GeoPoint point2 = new GeoPoint((int)(38.900084*1E6),(int)(-77.050509*1E6 ));
		OverlayItem overlayitem2 = new OverlayItem(point2, "47688507", "DEC-10 at 2:00  Himmelfarb library");
		
		final GeoPoint point3 = new GeoPoint((int)(38.896911*1E6),(int)(-77.045231*1E6 ));
		OverlayItem overlayitem3 = new OverlayItem(point3, "47688507", "DEC-22 at 23:00 Thurston");
		
			
		final GeoPoint point4 = new GeoPoint((int)(38.900168*1E6),(int)(-77.045982*1E6 ));
		OverlayItem overlayitem4 = new OverlayItem(point4, "47688507", "DEC-09 at 11:30 MPA");
		
		final MapController control = mapview.getController();
		control.setZoom(17);
		control.setCenter(point);
		LocationManager manager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
		LocationListener listener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				control.setCenter(point2);
				
			}
		};
		
		manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, listener);
		List<Overlay> mapOverlays = mapview.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.ic_launcher);
		MapItmeizedOverlay itemizedoverlay = new MapItmeizedOverlay(drawable, this);
		
			
		itemizedoverlay.addOverlay(overlayitem2);
		itemizedoverlay.addOverlay(overlayitem);
		itemizedoverlay.addOverlay(overlayitem3);
		itemizedoverlay.addOverlay(overlayitem4);
		mapOverlays.add(itemizedoverlay);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_map_view, menu);
		return true;
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}
