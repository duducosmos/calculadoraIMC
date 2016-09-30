package br.com.bigdatatec.imccalc.calculadoradeimc;

/**
 * Created by eduardo on 25/08/16.
 */
public class TabelaIMC {

    public static double muitoAbaixoDoPeso = 17.0;
    public static double abaixoDoPeso = 18.49;
    public static double pesoNormal = 24.99;
    public static double acimaDoPeso = 29.99;
    public static double obesidadeI = 34.99;
    public static double obesidadeII = 39.99;
    public static double obesidadeMorbida = 40;

    public static double imc(double altura, double peso){
        double imc;
        imc = peso / (altura * altura);
        return imc;
    }

}
