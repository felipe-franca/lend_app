package com.example.lend_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lend_app.R;

import java.util.List;

import com.example.lend_app.model.Restaurant;
import com.example.lend_app.ui.MenuListActivity;
import com.example.lend_app.ui.RestaurantListActivity;

public class RestaurantsListAdapter extends BaseAdapter {
  private final List<Restaurant> restaurants;
  private final Context context;

  public RestaurantsListAdapter(List<Restaurant> restaurants, Context context) {
    this.restaurants = restaurants;
    this.context = context;
  }

  @Override
  public int getCount() {
    return restaurants.size();
  }

  @Override
  public Object getItem(int pos) {
    return restaurants.get(pos);
  }

  @Override
  public long getItemId(int pos) {
    Restaurant restaurant = restaurants.get(pos);
    return restaurant.getId();
  }

  @Override
  public View getView(int pos, View view, ViewGroup viewGroup) {
    View aView = LayoutInflater.from(context).inflate(R.layout.card_item, viewGroup, false);
    Restaurant anRestaurant = restaurants.get(pos);

    bindTitle(aView, anRestaurant);
    bindDescription(aView, anRestaurant);
    bindImage(aView, anRestaurant);

    return aView;
  }

  private void bindTitle(View view, Restaurant restaurant) {
    TextView title = view.findViewById(R.id.card_title);
    title.setText(restaurant.getTitle());
  }

  private void bindDescription(View view, Restaurant restaurant) {
    TextView description = view.findViewById(R.id.card_description);
    description.setText(restaurant.getDescription());
  }

  private void bindImage(View view, Restaurant restaurant) {
    ImageView image = view.findViewById(R.id.card_image);
    Resources res = context.getResources();

    int drawableId = res.getIdentifier(restaurant.getImage(), "drawable", context.getPackageName());
    Drawable imagePackage = res.getDrawable(drawableId);

    image.setImageDrawable(imagePackage);
  }
}
