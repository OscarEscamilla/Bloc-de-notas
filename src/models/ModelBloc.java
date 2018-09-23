/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Oscar
 */
public class ModelBloc {
    
    String texto;
    String path = "C:\\Users\\Oscar\\Documents\\archivo.txt";

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    
    public String cifrarDatos(String cadena){
        int index = 0;
        String cifrado = "";
        for (int i = 0; i < cadena.length(); i++) {
            char letra = cadena.charAt(i);
            int ascii = (int)letra;
            ascii = ascii + 1;
            letra = (char)ascii;
            String convertir = Character.toString(letra);
            //System.out.print(convertir);
            cifrado += convertir;
            index++;
        } 
        return cifrado;
    }
       
    public String decifrarDatos(String cadena){
        int index = 0;
        String decifrado = "";
        for (int i = 1; i <= cadena.length(); i++) {
            String caracter = cadena.substring(index, i);
            char letra = caracter.charAt(0);
            int ascii = (int)letra;
            ascii = ascii - 1;
            letra = (char)ascii;
            String convertir = Character.toString(letra);
            decifrado += convertir;
            index++;
        } 
        return decifrado;
    }
}
