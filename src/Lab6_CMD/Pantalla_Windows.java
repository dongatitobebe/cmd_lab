
package Lab6_CMD;



import java.io.*;
import java.time.*;
public class Pantalla_Windows extends javax.swing.JFrame {

    
    
    Funciones cmd;
    public Pantalla_Windows() {
        initComponents();
        cmd = new Funciones("");
        Windows.setText("Bienvenido Al CMD :)"
                + "\n" + cmd.getCurrentPath() + ">");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Windows = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Windows.setEditable(false);
        Windows.setBackground(new java.awt.Color(0, 0, 0));
        Windows.setColumns(20);
        Windows.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        Windows.setForeground(new java.awt.Color(255, 255, 255));
        Windows.setLineWrap(true);
        Windows.setRows(5);
        Windows.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                WindowsKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(Windows);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void print(String msg) {
        String newText = Windows.getText();
        newText += "\n" + msg;
        Windows.setText(newText);
    }
    private String parseLine() {
        String lineNormal = "";
        String lineReverse = "";
        String consoleText = Windows.getText();         
        
        // Tomar de la consola la linea
        for (int i = consoleText.length() - 1; i >= 0; i--) {
            if (consoleText.charAt(i) == '>') break;
            lineReverse += consoleText.charAt(i);
        }
        
        // Invertir y guardar
        for (int i = lineReverse.length()-1;i >= 0; i--) {
            lineNormal += lineReverse.charAt(i);
        }
        
        return lineNormal;
        
    }
    
    private void parseCommand() {
        String line = parseLine();
        String[] args = line.trim().split(" ");
        System.out.println(args.length);
        
        switch (args[0].toLowerCase()) {
            case "cd":
                System.out.println("Se ejecuto comando CD");
                if (args.length == 1) {
                    print("Ingrese un directorio para cambiar.");
                    break;
                }
                cmd.CHDIR(args[1]);
                break;
                
            case "...":
                cmd.CHDIR("..");
                break;
                
            case "dir" :
                System.out.println("Se ejecuto comando dir");
                print(cmd.listarFiles(cmd.getCurrentPath()));
                break;
                
            case "mkdir":
                System.out.println("Se Ejucuto Comando Mkdir");
                if (args.length == 1) {
                    print("Ingrese un directorio para crear.");
                    break;
                }
                print(cmd.MKDIR(cmd.getCurrentPath() + "/" + args[1]));
                break;
                
            case "exit":
                dispose();
                break;
                
            case "mfile":
                if (args.length == 1) {
                    print("Ingrese el nombre del archivo.");
                    break;
                }
                print(cmd.MFILE(cmd.getCurrentPath() + "/" + args[1]));
                break;
                
            case "rm":
                if (args.length == 1) {
                    print("Ingrese la carpeta/archivo que desea eliminar.");
                    break;
                }
                
                File destino = new File(cmd.getCurrentPath() + "/" + args[1]);
                print(cmd.Eliminar(destino));
                break;
            case "date":
                LocalDate date = LocalDate.now();
                print(date.toString());
                break;
                
            case "time":
                LocalTime time = LocalTime.now();
                print(time.toString());
                break;
                
            case "rd":
                if (args.length == 1) {
                    print("Ingrese el archivo que desea leer.");
                    break;
                }
                
                print(cmd.leer(cmd.getCurrentPath() + "/" + args[1].trim()));
                break;
                
                
            case "wr":
                if (args.length < 3) {
                    print("Ingrese el archivo y contenido que desea escribir\nEjemplo: wr <archivo> <mensaje>");
                    break;
                }
                
                String mensaje = "";
                
                for (int i = 2; i<args.length;i++){
                    mensaje += args[i] + " ";
                }
                
                System.out.println(cmd.getCurrentPath() + "/" + args[1]);
                print(cmd.Escribir(mensaje, cmd.getCurrentPath() + "/" + args[1]));
                break;
        }
    }
    
    private void WindowsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_WindowsKeyPressed
        // TODO add your handling code here:
        
        int[] codProhibidos = {27, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 19, 155, 127, 36, 33, 34, 35, 16, 38, 37, 40, 38, 39, 17, 18};
        
        for (int codigo:codProhibidos) {
            if (evt.getKeyCode() == codigo) return;        
        }
        
        if (evt.getKeyCode() == 8) {
            String newText = Windows.getText();
            
            if (newText.charAt(newText.length() - 1) == '>') {
            } else {
                newText = newText.substring(0, newText.length()-1);
                Windows.setText(newText);
            }
            
            return;

        } else if (evt.getKeyCode() == 10) {
            parseCommand();
            Windows.setText(Windows.getText() + "\n" + cmd.getCurrentPath() + ">");
            return;
        }
        
        String newText = Windows.getText();
        newText += evt.getKeyChar();
        Windows.setText(newText);
    }//GEN-LAST:event_WindowsKeyPressed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Windows;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
