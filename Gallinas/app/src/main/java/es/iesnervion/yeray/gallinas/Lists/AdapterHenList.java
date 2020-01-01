package es.iesnervion.yeray.gallinas.Lists;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.iesnervion.yeray.gallinas.Entities.Gallina;
import es.iesnervion.yeray.gallinas.R;

public class AdapterHenList extends BaseAdapter {
    private Context _context;
    private int _layout;
    private ArrayList<Gallina> _items;
    private Gallina _item;

    public AdapterHenList(Context context, int layout, ArrayList<Gallina> textos){
        _context = context;
        _layout = layout;
        _items = textos;
    }

    @Override
    public int getCount(){
        return _items.size();
    }

    @Override
    public Gallina getItem(int position){
        return _items.get(position);
    }

    @Override
    public long getItemId(int id){
        return id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup){
        View v = convertView;
        ViewHolder holder;
        TextView name, pedigree;
        _item = getItem(position);

        if(v == null){
            //Inflamos la vista con nuestro propio layout
            LayoutInflater layoutInflater = LayoutInflater.from(this._context);
            v = layoutInflater.inflate(_layout, null);

            name = v.findViewById(R.id.TextViewNombreGallina);
            pedigree = v.findViewById(R.id.TextViewPedigriGallina);

            //Almacenamos los datos en el holder
            holder = new ViewHolder(name, pedigree);
            //Metemos el objeto en el tag de la vista
            v.setTag(holder);
        }else{
            holder = (ViewHolder) v.getTag();
        }

        holder.get_name().setText(_item.getNombre());
        holder.get_pedigree().setText(_item.getPedigri());
        return v;
    }

    public class ViewHolder{
        TextView _name, _pedigree, _weight, _height;

        public ViewHolder(){
            _name = null;
            _pedigree = null;
        }

        public ViewHolder(TextView name, TextView pedigree) {
            this._name = name;
            this._pedigree = pedigree;
        }

        public TextView get_name() {
            return _name;
        }

        public TextView get_pedigree() {
            return _pedigree;
        }
    }

}
