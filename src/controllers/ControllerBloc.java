
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
    public void guardarArchivo() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    JFileChooser filtro = new JFileChooser();
    
    
    if (JFileChooser.APPROVE_OPTION == fileChooser.showSaveDialog(viewbloc)) {
        try {
            File file = fileChooser.getSelectedFile();
            FileWriter filewriter = new FileWriter(file, false);
            try(PrintWriter printwriter = new PrintWriter(filewriter)){
                printwriter.println(modelbloc.getTexto());
            }
            /*
            escritor = new FileWriter(archivo);
            escritor.write(notas.getText());*/
        } catch (FileNotFoundException err) {
            System.err.println("File not found: " + err.getMessage());
        } catch (IOException err) {
            System.err.println("Error on I/O operation: " + err.getMessage());
        } 
    }
}
    
    
    public void enviarTexto(){
        modelbloc.setTexto(viewbloc.jta_espacio.getText());
    }

    
    
    public void abrirArchivo() {
    JFileChooser jfc = new JFileChooser(); //CREAMOS UN OBJETO PARA ACCEDER AL FILECHOSER
    jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);//definimos el modo de seleccion para el obejto
    FileNameExtensionFilter filtro = new FileNameExtensionFilter(null, "txt");//agregamos el filtro txt
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
