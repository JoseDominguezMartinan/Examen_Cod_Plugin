/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jose.controlDePlugin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "File",
        id = "com.jose.controlDePlugin.crearEjecutable"
)
@ActionRegistration(
        iconBase = "com/jose/controlDePlugin/iconos.png",
        displayName = "#CTL_crearEjecutable"
)
@ActionReference(path = "Toolbars/File",position = 0)
@Messages("CTL_crearEjecutable=Ejecutable")
public final class crearEjecutable implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // TODO implement action body
         /** primero para poder realizar la acción de empaquetamiento, pedimos por ventanas de dialogo una serie de datos**/
        
        String categoria=JOptionPane.showInputDialog("Introduce la categoria de la aplicación"); // insertamos la categoria
        String directorioSalida=JOptionPane.showInputDialog("Introduce el directorio de salida"); // directorio de salida de la aplicación a empaquetar 
        String nombreAplicacion=JOptionPane.showInputDialog("introduce el nombre que vas a dar a la aplicacion"); // nombre que le vamos a dar a la nueva app
        String srcfiles=JOptionPane.showInputDialog("Introduce directorio del .jar"); // directorio del ejecutable java que queremos empaquetar 
        String appclass=JOptionPane.showInputDialog("Introduce paquete.clase"); //paquete de la clase mas clase 
        String icono=JOptionPane.showInputDialog("Introduce la ruta del incono para la aplicación"); // icono que le vamos a poner a la app 
        String title=JOptionPane.showInputDialog("Introduce el titulo para la aplicación"); // titulo de la aplicación
        // metemos en un string la linea de comando que vamos a ejecutar
        String cmd="cmd /c javapackager -deploy -native deb -Bcategory="+categoria+" -outdir "+directorioSalida+" -outfile "+nombreAplicacion+" -srcdir "+directorioSalida+
                " -srcfiles "+srcfiles+" -appclass "+appclass+" -title "+title+" -Bicon="+icono;
        
        try {
                Runtime rt = Runtime.getRuntime();
                Process pr = rt.exec(cmd); // ejecutamos el string con la linea de comandos 
              
 
                BufferedReader input = new BufferedReader(new InputStreamReader(pr.getInputStream()));
 
                String line=null;
 
                while((line=input.readLine()) != null) {
                    System.out.println(line);
                }
 
                int exitVal = pr.waitFor();
                System.out.println("Exited with error code "+exitVal);
 
            } catch(Exception ex) {
                System.out.println(e.toString());
                ex.printStackTrace();
            }
    
    }
}
