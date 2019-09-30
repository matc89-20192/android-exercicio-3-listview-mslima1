package matc89.exercicio3;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import matc89.exercicio3.Tarefa;

public class TarefaAdapter extends ArrayAdapter<Tarefa>{
    private Context mContext;
    private int mResource;
    private int lastPosition = -1;
    Activity context;
    ArrayList<Tarefa> tarefas;
    private static LayoutInflater inflater = null;

    private static class ViewHolder {
        TextView descricao;
        TextView prioridade;
    }

    public TarefaAdapter(Context context, int resource, ArrayList<Tarefa> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        String descricao = getItem(position).getDescricao();
        int prioridade = getItem(position).getPrioridade();


        Tarefa tarefa = new Tarefa(descricao,prioridade);


        final View result;


        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.descricao = (TextView) convertView.findViewById(R.id.descricao);
            holder.prioridade = (TextView) convertView.findViewById(R.id.prioridade);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        holder.descricao.setText(tarefa.getDescricao());
        int asa = tarefa.getPrioridade();
        String p = Integer.toString(asa);
        holder.prioridade.setText("Prioridade: " + p);




        return convertView;
    }
}