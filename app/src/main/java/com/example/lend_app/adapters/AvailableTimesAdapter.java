package com.example.lend_app.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.content.Context;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;

import com.example.lend_app.R;
import com.example.lend_app.model.AvailableTimes;

import java.util.List;

public class AvailableTimesAdapter extends BaseAdapter {
  private final List<AvailableTimes> availableTimes;
  private final Context context;

  public AvailableTimesAdapter(List<AvailableTimes> availableTimes, Context context) {
    this.availableTimes = availableTimes;
    this.context = context;
  }

  @Override
  public int getCount() {
    return availableTimes.size();
  }

  @Override
  public Object getItem(int i) { return availableTimes.get(i); }

  @Override
  public long getItemId(int i) {
    return 0;
  }

  @Override
  public View getView(int pos, View view, ViewGroup viewGroup) {
    View aView = LayoutInflater.from(context).inflate(R.layout.available_times, viewGroup, false);
    AvailableTimes aAvailableTimes = availableTimes.get(pos);

    bindCheckBox(aView, aAvailableTimes);

    return aView;
  }

  private void bindCheckBox(View view, AvailableTimes av) {
    CheckBox cb = view.findViewById(R.id.available_times_check_box);
    cb.setText(av.toString());
    cb.setId(av.getId());
  }
}
