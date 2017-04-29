package kr.soen.practice6;

/**
 * Created by manggi on 2017. 4. 29..
 */
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class adapter extends BaseAdapter implements Filterable{

    ArrayList<information> data = null;
    ArrayList<information> data2 = null;

    Context context;

    private boolean checkboxstate = false;
    public void turn(boolean state){
        checkboxstate = state;
        notifyDataSetChanged();
    }


    public adapter(Context context,ArrayList<information> saveddata ){
        this.context = context;
        this.data = saveddata;
        this.data2 = saveddata;
    }
    @Override
    public int getCount() {
        return data2.size();
    }

    @Override
    public Object getItem(int position) {
        return data2.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    ViewHolder viewHolder = new ViewHolder();
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem, null);
            viewHolder.title = (TextView) convertView.findViewById(R.id.textname);
            viewHolder.tel = (TextView) convertView.findViewById(R.id.texttel);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        information one = data2.get(position);  //데이타 가져오기
        viewHolder.title.setText(one.getName());
        viewHolder.tel.setText(one.getTelnum());
        if (one.getPic() == 1) {
            viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.chicken));
        } else if(one.getPic() == 2 ){
            viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.hambergur));
        } else if(one.getPic() == 3){
            viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.pizza));
        }
        viewHolder.checkBox = (CheckBox)convertView.findViewById(R.id.checkBox);
        if( checkboxstate ) viewHolder.checkBox.setVisibility(View.VISIBLE);
        else viewHolder.checkBox.setVisibility(View.INVISIBLE);

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                data2.get(position).turnChecked();
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String filterString = constraint.toString();
                FilterResults results = new FilterResults();
                ArrayList<information> list = data;
                int count = list.size();
                ArrayList<information> nlist = new ArrayList<information>();
                for (int i = 0; i < count; i++) {
                    if (list.get(i).getName().contains(filterString)) {
                        nlist.add(list.get(i));
                    }
                }
                results.values = nlist;
                results.count = nlist.size();

                return results;
            }
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                data2 = (ArrayList<information>) results.values;
                notifyDataSetChanged();
            }
        };
        return filter;
    }
    public ArrayList<information> return2(){
        return data2;
    }

    class ViewHolder {
        TextView title;
        TextView tel;
        ImageView imageView;
        CheckBox checkBox;
    }
    Comparator<information> PicAsc = new Comparator<information>() {
        @Override
        public int compare(information o1, information o2) {
            return Integer.toString(o1.getPic()).compareTo(Integer.toString(o2.getPic()));
        }
    };

    public void setPicAscSort(){
        Collections.sort(data2,PicAsc);
        this.notifyDataSetChanged();
    }

    Comparator<information> nameAsc = new Comparator<information>() {
        @Override
        public int compare(information o1, information o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public void setNameAscSort(){
        Collections.sort(data2,nameAsc);
        this.notifyDataSetChanged();
    }

    public void filter(String searchText) {

        data2.clear();
        if (0 >= searchText.getBytes().length) {
            data2 = data;
        } else {
            for (int i = 0 ; i < data.size() ; i++) {
                if (data.get(i).getName().contains(searchText)) {
                    data2.add(data.get(i));
                }
            }
        }
        this.notifyDataSetChanged();
    }



}