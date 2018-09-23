/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import models.ModelBloc;
import views.ViewBloc;
import controllers.ControllerBloc;

/**
 *
 * @author Oscar
 */
public class Main {
    private static ModelBloc modelbloc;
    private static ViewBloc viewbloc;
    
    
    public static void main(String[] args) {
        modelbloc = new ModelBloc();
        viewbloc = new ViewBloc();
        ControllerBloc controllerbloc = new ControllerBloc(modelbloc, viewbloc);
    }
    
}
