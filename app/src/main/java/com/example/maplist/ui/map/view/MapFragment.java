package com.example.maplist.ui.map.view;

import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.example.maplist.R;
import com.example.maplist.api.ViewModelFactory;
import com.example.maplist.api.model.Poi;
import com.example.maplist.base.BaseFragment;
import com.example.maplist.databinding.FragmentMapBinding;
import com.example.maplist.ui.activity.NavigationActivity;
import com.example.maplist.ui.list.adapter.ListAdapter;
import com.example.maplist.ui.list.viewmodel.ListViewModel;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import static android.widget.LinearLayout.HORIZONTAL;

public class MapFragment extends BaseFragment<FragmentMapBinding>
    implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

  private ListAdapter adapter;
  private ListViewModel viewModel;

  private SupportMapFragment supportMapFragment;
  private GoogleMap googleMap;

  private List<Poi> list;

  @Inject
  ViewModelFactory viewModelFactory;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidSupportInjection.inject(this);
    viewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(ListViewModel.class);
  }

  @Override
  public int getLayout() {
    return R.layout.fragment_map;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new ListAdapter(getContext(), new ArrayList<>());
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(
        getContext(),
        LinearLayoutManager.HORIZONTAL,
        false
    ));
    binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), HORIZONTAL));
    binding.recyclerView.setAdapter(adapter);

    supportMapFragment = SupportMapFragment.newInstance();

    ((NavigationActivity) getActivity()).addFragment(
        binding.mapView.getId(),
        supportMapFragment,
        "Map"
    );
    if (googleMap == null) {
      supportMapFragment.getMapAsync(this);
    }
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    observeDataChange();
  }

  private void observeDataChange() {
    viewModel
        .apiError()
        .observe(
            getViewLifecycleOwner(),
            error -> showToast(getContext(), getString(R.string.some_error))
        );

    viewModel.loading().observe(getViewLifecycleOwner(), isLoading -> {
      if (isLoading) {
        binding.setShowLoading(true);
      } else {
        binding.setShowLoading(false);
      }
    });

    viewModel
        .apiMapListData()
        .observe(getViewLifecycleOwner(), list -> {
          this.list = list.getPoiList();
          adapter.addPoiList(list.getPoiList());
          setUpMarkerWithList();
        });
  }

  @Override public boolean onMarkerClick(final Marker marker) {
    return false;
  }

  @Override public void onMapReady(final GoogleMap googleMap) {
    this.googleMap = googleMap;
    initMapWithMarker();
  }

  private void initMapWithMarker() {
    if (googleMap != null) {
      googleMap.getUiSettings().setCompassEnabled(false);
      googleMap.getUiSettings().setZoomControlsEnabled(false);
      googleMap.getUiSettings().setMyLocationButtonEnabled(false);
      googleMap.setOnMarkerClickListener(this);
    }
  }

  private void setUpMarkerWithList() {
    if (list == null && adapter != null) {
      list = adapter.getPoiList();
    }

    if (list != null && list.size() > 0) {
      for (Poi poi : list) {
        putMarker(poi);
      }

      if (googleMap != null) {
        googleMap.moveCamera(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(new LatLng(
            list.get(0).getCoordinate().getLatitude(),
            list.get(0).getCoordinate().getLongitude()
        ), 11.0f)));
      }
    }
  }

  private void putMarker(Poi poi) {
    MarkerOptions
        marker = new MarkerOptions()
        .position(new LatLng(poi.getCoordinate().getLatitude(), poi.getCoordinate().getLongitude()))
        .icon(getTaxiMarker(
            poi.getFleetType().equalsIgnoreCase("taxi") == true ? R.drawable.taxi_marker
                                                                : R.drawable.pooling_marker))
        .title(String.valueOf(poi.getId()))
        .snippet(poi.getFleetType());
    googleMap.addMarker(marker);
  }

  private BitmapDescriptor getTaxiMarker(int res) {
    Drawable vectorDrawable = ContextCompat.getDrawable(getActivity(), res);
    vectorDrawable.setBounds(
        0,
        0,
        vectorDrawable.getIntrinsicWidth(),
        vectorDrawable.getIntrinsicHeight()
    );
    Bitmap bitmap = Bitmap.createBitmap(
        vectorDrawable.getIntrinsicWidth(),
        vectorDrawable.getIntrinsicHeight(),
        Bitmap.Config.ARGB_8888
    );
    Canvas canvas = new Canvas(bitmap);
    vectorDrawable.draw(canvas);
    return BitmapDescriptorFactory.fromBitmap(bitmap);
  }
}
