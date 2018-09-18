/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelBloc;
import views.ViewBloc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



public class ControllerBloc {
    
    ModelBloc modelbloc;
    ViewBloc viewbloc;
   
   
    public ControllerBloc(ModelBloc modelbloc, ViewBloc viewbloc) {
        this.modelbloc = modelbloc;
        this.viewbloc = viewbloc;
        this.viewbloc.jmi_leer.addActionListener(actionlistener);
        this.viewbloc.jmi_guardar.addActionListener(actionlistener);
        initComponents();
    }
    
    ActionListener actionlistener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewbloc.jmi_leer) {
                leerArchivo();
                
            }else if (e.getSource() == viewbloc.jmi_guardar) {
                enviarTexto();
                guardarArchivo();
                
            }
        }
        
    };
    
    
    public void enviarTexto(){
        modelbloc.setTexto(viewbloc.jta_espacio.getText());
    }
    
    public void guardarArchivo(){
        try{
            File file = new File(modelbloc.getPath());
            FileWriter filewriter = new FileWriter(file, false);
            try(PrintWriter printwriter = new PrintWriter(filewriter)){
                printwriter.println(modelbloc.getTexto());
            }
        }catch(FileNotFoundException err){
            System.err.println("File not found: " + err.getMessage());
        }catch(IOException err){
            System.err.println("Error on I/O operation: " + err.getMessage());
        }
        
    }
    
    public void leerArchivo(){
      try{
            String row;
            StringBuilder contenido = new StringBuilder();
            try(FileReader file = new FileReader(modelbloc.getPath())){
                BufferedReader bufferedreader = new BufferedReader(file);
                while ((row = bufferedreader.readLine()) != null) {                    
                    contenido.append(row);
                    contenido.append("\n");
                }
            viewbloc.jta_espacio.setText(String.valueOf(contenido));
            }
        }catch(FileNotFoundException err){
            System.err.println("File not found: " + err.getMessage());
        }catch(IOException err){
            System.err.println("Error on I/O operation: " + err.getMessage());
        }
    }

    
    
public void initComponents(){
    viewbloc.setVisible(true);
}
    
    
}
