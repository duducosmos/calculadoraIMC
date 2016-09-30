package br.com.bigdatatec.imccalc.calculadoradeimc;

import android.content.Context;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ViewPropertyAnimatorCompatSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button calcular;
    private Button limpar;
    private TextView resposta;
    private TextView situacao;
    private EditText peso;
    private EditText altura;
    private ImageView personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calcular = (Button) findViewById(R.id.calcular);
        limpar = (Button) findViewById(R.id.limpar);
        resposta = (TextView) findViewById(R.id.resultadoIMC);
        situacao = (TextView) findViewById(R.id.situacao);
        peso = (EditText) findViewById(R.id.peso);
        altura = (EditText) findViewById(R.id.altura);
        personagem = (ImageView) findViewById(R.id.personagem);
        peso.requestFocus();

        altura.setOnLongClickListener(cliqueLongo);


    }

    public View.OnLongClickListener cliqueLongo = new View.OnLongClickListener(){
        @Override
        public boolean onLongClick(View view){
            Context context = getApplicationContext();
            String erroEntrada = getText(R.string.erro_entrada).toString();
            int duration = Toast.LENGTH_SHORT;
            if(altura.getText().length() == 0){
                Toast toast = Toast.makeText(context, erroEntrada,duration);
                toast.show();

            }
            else {
                String altToas = getString(R.string.sua_altura) + altura.getText().toString()
                        + " cm";

                Toast toast = Toast.makeText(context, altToas,duration);
                toast.show();

            }
            return true;
        };
    };

    public void calcularimc(View view){

        if(altura.getText().length() == 0 || peso.getText().length() == 0){
            String erroEntrada = getText(R.string.erro_entrada).toString();
            int duration = Toast.LENGTH_SHORT;
            Context context = getApplicationContext();
            Toast toast = Toast.makeText(context, erroEntrada,duration);
            toast.show();

        }
        else {
            double alt = Double.parseDouble(altura.getText().toString()) / 100.0;
            double pes = Double.parseDouble(peso.getText().toString());
            double imc = TabelaIMC.imc(alt, pes);
            resposta.setText(String.format("%.2f", imc));
            situacao.setText(estado(imc));
        }


    }

    public void limparimc(View view){
        situacao.setText("");
        resposta.setText("00");
        peso.setText("");
        peso.requestFocus();
        altura.setText("");
        resposta.setTextColor(Color.BLACK);
        personagem.setImageResource(R.drawable.imc03);

    }

    public String estado(double imc){
        if(imc < TabelaIMC.muitoAbaixoDoPeso){
            personagem.setImageResource(R.drawable.imc04);
            resposta.setTextColor(Color.RED);
            return getString(R.string.muito_abaixo_do_peso);
        }
        else if(imc >= TabelaIMC.muitoAbaixoDoPeso && imc <= TabelaIMC.abaixoDoPeso){
            personagem.setImageResource(R.drawable.imc04);
            resposta.setTextColor(Color.MAGENTA);
            return getString(R.string.abaixo_do_peso);
        }
        else if(imc > TabelaIMC.abaixoDoPeso && imc <= TabelaIMC.pesoNormal ){
            personagem.setImageResource(R.drawable.imc03);
            resposta.setTextColor(Color.BLUE);
            return getString(R.string.peso_normal);
        }
        else if(imc > TabelaIMC.pesoNormal && imc <= TabelaIMC.acimaDoPeso){
            resposta.setTextColor(Color.MAGENTA);
            personagem.setImageResource(R.drawable.imc02);
            return getString(R.string.acima_do_peso);
        }
        else if(imc > TabelaIMC.acimaDoPeso && imc <= TabelaIMC.obesidadeI){
            resposta.setTextColor(Color.RED);
            personagem.setImageResource(R.drawable.imc01);
            return  getString(R.string.obesidadei);
        }
        else if(imc > TabelaIMC.obesidadeI && imc <= TabelaIMC.obesidadeII){
            resposta.setTextColor(Color.RED);
            personagem.setImageResource(R.drawable.imc01);
            return getString(R.string.obesidade_severa);
        }
        else{
            resposta.setTextColor(Color.RED);
            personagem.setImageResource(R.drawable.imc01);
            return getString(R.string.obesidade_morbida);
        }
    }
}
