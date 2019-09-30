package matc89.exercicio3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Tarefa> tarefas;
    private TarefaAdapter adapter;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        tarefas = new ArrayList<Tarefa>();

        adapter = new TarefaAdapter(this,  R.layout.adapter_view_layout, tarefas);
        listView.setAdapter(adapter);
        setupListViewListener();
    }

    public void adicionar(View v) {
        int erro = 0;

        EditText editDescricao = (EditText) findViewById(R.id.editDescricao);
        String itemText = editDescricao.getText().toString();
        EditText prioridade = (EditText) findViewById(R.id.editPrioridade);
        String prioridadeText = prioridade.getText().toString();
        int pr = Integer.valueOf(prioridadeText).intValue();
        if(pr<1 || pr>10){
            Toast.makeText(this, "A prioridade deve estar entre 1 e 10.", Toast.LENGTH_SHORT).show();
            erro=1;
        }

        for (Tarefa tarefa: tarefas) {
            // Now you have the product. Just get the Id
            if (tarefa.getDescricao().equals(itemText)){
                Toast.makeText(this, "Tarefa já cadastrada.", Toast.LENGTH_SHORT).show();
                erro = 1;
            }
        }
        if(erro == 0) {
            Tarefa tarefa = new Tarefa(itemText, pr);
            adapter.add(tarefa);
        }

        editDescricao.setText("");
        prioridade.setText("");
    }

    private void setupListViewListener() {
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter1,
                                                   View item, int pos, long id) {
                        // Remove the item within array at position
                        tarefas.remove(pos);
                        adapter.notifyDataSetChanged();

                    }

                });
    }

    public void remover(View v) {
        tarefas.remove(0);
        adapter.notifyDataSetChanged();
    }

}
