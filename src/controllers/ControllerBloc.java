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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



public class ControllerBloc {
    
    ModelBloc modelbloc;
    ViewBloc viewbloc;
   
   
    public ControllerBloc(ModelBloc modelbloc, ViewBloc viewbloc) {
        this.modelbloc = modelbloc;
        this.viewbloc = viewbloc;
        this.viewbloc.jmi_abrir.addActionListener(actionlistener);
        this.viewbloc.jmi_guardar.addActionListener(actionlistener);
        initComponents();
    }
    
    ActionListener actionlistener = new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewbloc.jmi_abrir) {
                abrirArchivo();
                
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
    
    
      /*try{    ESTO ES EL CODIGO PARA LEER EL ARCHIVO EN LA PRIMERA VERSION SIN FILE CHOSER
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
    }*/
    public void abrirArchivo() {
    JFileChooser jfc = new JFileChooser(); //CREAMOS UN OBJETO PARA ACCEDER AL FILECHOSER
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);//definimos el modo de seleccion para el obejto
    FileNameExtensionFilter filtro = new FileNameExtensionFilter(null, "txt");
    jfc.setFileFilter(filtro);
    
    
    if (JFileChooser.APPROVE_OPTION == jfc.showOpenDialog(viewbloc)) { 
        File archivo = jfc.getSelectedFile(); //asignamos la seleccion del file choser a la variable archivo
        
        
        try {
            FileReader lector = new FileReader(archivo); //definimos una variable lector nulla para despues guardar el espacion en el buffered
            BufferedReader bufferedreader = new BufferedReader(lector);//le pasamos la variable lector al objeto de buffered para reservar espacio
            String linea; //definimos la variable linea q sera la que estemos almacenando en el objeto de la linea 103 de tipo StringBuilder
            StringBuilder contenido = new StringBuilder();

            while ((linea = bufferedreader.readLine()) != null) {
                contenido.append(linea);
                contenido.append("\n");
            }

      
            viewbloc.jta_espacio.setText(contenido.toString());

        } catch (FileNotFoundException err) {
            System.err.println("File not found: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error on I/O operation: " + err.getMessage());
        } 
    }
}

    
    
public void initComponents(){
    viewbloc.setVisible(true);
}
    
    
}
