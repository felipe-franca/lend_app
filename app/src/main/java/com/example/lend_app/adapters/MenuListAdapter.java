package com.example.lend_app.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.view.LayoutInflater;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import com.example.lend_app.R;
import com.example.lend_app.model.Meal;
import com.example.lend_app.utils.CurrencyFormatter;

import java.util.List;

public class MenuListAdapter extends BaseAdapter {
  private final List<Meal> meals;
  private final Context context;

  public MenuListAdapter(List<Meal> meals, Context context) {
    this.context = context;
    this.meals = meals;
  }

  @Override
  public int getCount() {
    return meals.size();
  }

  @Override
  public Object getItem(int pos) {
    return meals.get(pos);
  }

  @Override
  public long getItemId(int pos) {
    Meal meal = meals.get(pos);
    return meal.getId();
  }

  @Override
  public View getView(int pos, View view, ViewGroup viewGroup) {
    View aView = LayoutInflater.from(context).inflate(R.layout.menu_item, viewGroup, false);
    Meal aMeal = meals.get(pos);

    bindTitle(aView, aMeal);
    bindDescription(aView, aMeal);
    bindPrice(aView, aMeal);
    bindImage(aView, aMeal);

    return aView;
  }

  private void bindTitle(View view, Meal meal) {
    TextView title = view.findViewById(R.id.menu_title);
    title.setText(meal.getName());
  }

  private void bindDescription(View view, Meal meal) {
    TextView description = view.findViewById(R.id.menu_description);
    description.setText(meal.getDescription());
  }

  private void bindPrice(View view, Meal meal) {
    TextView price = view.findViewById(R.id.menu_item_price);
    price.setText(CurrencyFormatter.formatToBrl(meal.getPrice()));
  }

  private void bindImage(View view, Meal meal) {
    ImageView imageView = view.findViewById(R.id.menu_image);
    Resources res = context.getResources();

    int drawableId = res.getIdentifier(meal.getImage(), "drawable", context.getPackageName());
    Drawable imagePack = res.getDrawable(drawableId);

    imageView.setImageDrawable(imagePack);
  }
}
