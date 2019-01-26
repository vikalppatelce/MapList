package com.example.maplist.ui.list.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.maplist.api.model.Poi;
import com.example.maplist.databinding.ItemsBinding;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.PoiViewHolder> {

  private List<Poi> list;
  private Context context;
  private LayoutInflater layoutInflater;

  public ListAdapter(Context context, List<Poi> list) {
    this.list = list;
    this.context = context;
    layoutInflater = LayoutInflater.from(context);
  }

  public void addPoiList(List<Poi> list) {
    if (!this.list.isEmpty()) {
      this.list.clear();
    }
    this.list.addAll(list);
    notifyDataSetChanged();
  }

  public List<Poi> getPoiList(){
    return list;
  }

  @NonNull
  @Override
  public PoiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    return new PoiViewHolder(ItemsBinding.inflate(layoutInflater, viewGroup, false));
  }

  @Override
  public void onBindViewHolder(@NonNull PoiViewHolder viewHolder, int i) {
    Poi item = list.get(i);
    viewHolder.binding.setItem(item);
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  static final class PoiViewHolder extends RecyclerView.ViewHolder {

    private final ItemsBinding binding;

    public PoiViewHolder(ItemsBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }
  }
}
