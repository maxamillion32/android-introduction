package com.haw.introduction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ExampleAdapter extends ArrayAdapter<String> {

    public ExampleAdapter(Context context, List<String> objects) {
        super(context, 0, objects);
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String title = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item, parent, false);
        }
        ((TextView) convertView.findViewById(R.id.textView3)).setText(title);
        return convertView;
    }
}
