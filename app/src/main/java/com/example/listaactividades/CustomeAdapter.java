package com.example.listaactividades;

import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
        import java.util.HashMap;

/**
 * Created by Parsania Hardik on 03-Jan-17.
 */
public class CustomeAdapter  extends BaseAdapter {

    private Context context;
    private ArrayList<ProductsTreeModel> imageModelArrayList;


    public CustomeAdapter(Context context, ArrayList<ProductsTreeModel> imageModelArrayList) {

        this.context = context;
        this.imageModelArrayList = imageModelArrayList;

    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return imageModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder(); LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.name);
            holder.tvdescr = (TextView) convertView.findViewById(R.id.descr);
            holder.tvprice = (TextView) convertView.findViewById(R.id.price);
            holder.iv = (ImageView) convertView.findViewById(R.id.imgView);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }
        DecimalFormat formatter = new DecimalFormat("###,###,##0.00");
        holder.tvname.setText(imageModelArrayList.get(position).getName());
        holder.tvdescr.setText(imageModelArrayList.get(position).getDescr());
        String valor = formatter.format(imageModelArrayList.get(position).getPrice());
        holder.tvprice.setText(valor);
        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname;
        protected TextView tvdescr;
        protected TextView tvprice;
        private ImageView iv;

    }

}
