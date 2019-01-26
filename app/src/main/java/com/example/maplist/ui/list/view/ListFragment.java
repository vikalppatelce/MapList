package com.example.maplist.ui.list.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.example.maplist.R;
import com.example.maplist.api.ViewModelFactory;
import com.example.maplist.base.BaseFragment;
import com.example.maplist.databinding.FragmentListBinding;
import com.example.maplist.ui.list.adapter.ListAdapter;
import com.example.maplist.ui.list.viewmodel.ListViewModel;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import javax.inject.Inject;

import static android.widget.LinearLayout.HORIZONTAL;

public class ListFragment extends BaseFragment<FragmentListBinding> {

  private ListAdapter adapter;
  private ListViewModel viewModel;
  private String[] latLong = new String[] { "53.694865", "9.757589", "53.394655", "10.099891" };

  @Inject
  ViewModelFactory viewModelFactory;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    AndroidSupportInjection.inject(this);
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);
  }

  @Override
  public int getLayout() {
    return R.layout.fragment_list;
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    adapter = new ListAdapter(getContext(), new ArrayList<>());
    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    binding.recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), HORIZONTAL));
    binding.recyclerView.setAdapter(adapter);
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    observeDataChange();
    viewModel.fetchList(latLong[0], latLong[1], latLong[2], latLong[3]);
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
        .apiListData()
        .observe(getViewLifecycleOwner(), list -> adapter.addPoiList(list.getPoiList()));
  }
}
