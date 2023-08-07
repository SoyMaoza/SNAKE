
package snake;

import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;


public class juegoVentana extends JFrame{
    juegoVentana(){
        this.setTitle("SNAKE");
        this.add(new juegoContenido());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        
    }

}
