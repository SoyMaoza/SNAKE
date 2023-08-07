package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

public class juegoContenido extends JPanel implements ActionListener {

    //pantalla
    static final int PANTALLA = 600;
    static final int CUADRITO_SIZE = 25;
    static final int CUADRITOS_EN_PARALELO = (int) PANTALLA / CUADRITO_SIZE;

    //serpiente
    static final int TOTAL_CUERPO_SERPIENTE = PANTALLA * PANTALLA / CUADRITO_SIZE;
    int[] serpienteX = new int[TOTAL_CUERPO_SERPIENTE];
    int[] serpienteY = new int[TOTAL_CUERPO_SERPIENTE];
    int cuerpo_serpiente = 3;
    char direccion = 'd';//wasd

    //comida
    int comidaX;
    int comidaY;
    //timer
    static final int DELAY = 100;
    boolean running = true;
    Timer timer;
    //otros
    Random random = new Random();

    juegoContenido() {
        this.setPreferredSize(new Dimension(PANTALLA, PANTALLA));
        this.setBackground(Color.gray);
        this.setFocusable(true);
        this.addKeyListener(new Controles());
        iniciarJuego();
    }

    public void iniciarJuego() {
        agregarComida();
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void agregarComida() {
        comidaX = random.nextInt(CUADRITOS_EN_PARALELO) * CUADRITO_SIZE;
        comidaY = random.nextInt(CUADRITOS_EN_PARALELO) * CUADRITO_SIZE;
    }

    public void moverSerpiente() {
        for (int i = cuerpo_serpiente; i > 0; i--) {
            serpienteX[i] = serpienteX[i-1];
            serpienteY[i] = serpienteY[i-1];

        }

        switch (direccion){
            case 'd':
                serpienteX [0] = serpienteX[0] + CUADRITO_SIZE;
            break;
            case 'a':
                serpienteX [0] = serpienteX[0] - CUADRITO_SIZE;
            break;
            case 'w':
                serpienteY [0] = serpienteY[0] - CUADRITO_SIZE;
            break;
            case 's':
                serpienteY [0] = serpienteY[0] + CUADRITO_SIZE;
            break;
                
        }
    }

    public void checarComida() {
        if (serpienteX[0] == comidaX && serpienteY[0]==comidaY){
            cuerpo_serpiente++;
            agregarComida();
        }

    }

    public void checarColisiones() {
        if(serpienteX [0] <0 ) {
            running = false;
        }
        if (serpienteY [0]< 0){
            running = false;
        }
        if(serpienteX [0] >PANTALLA-CUADRITO_SIZE ) {
            running = false;
        }
        if (serpienteY [0]> PANTALLA-CUADRITO_SIZE){
            running = false;
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            moverSerpiente();
            checarComida();
            checarColisiones();

        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < CUADRITOS_EN_PARALELO; i++) {

            g.drawLine(0, CUADRITO_SIZE * i, PANTALLA, CUADRITO_SIZE * i);
            g.drawLine(CUADRITO_SIZE * i, 0, CUADRITO_SIZE * i, PANTALLA);
        }
        g.setColor(Color.red);
        g.fillOval(comidaX, comidaY, CUADRITO_SIZE, CUADRITO_SIZE);
        g.setColor(Color.green);
        for (int i = 0; i < cuerpo_serpiente; i++) {
            g.fillRect(serpienteX[i], serpienteY[i], CUADRITO_SIZE, CUADRITO_SIZE);
        }

    }

    public class Controles extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e){
            switch(e.getKeyChar()){
                case 'w':
                    direccion = 'w';
                    break;
                    case 'a':
                    direccion = 'a';
                    break;
                    case 's':
                    direccion = 's';
                    break;
                    case 'd':
                    direccion = 'd';
                    break;
            }
            
        }
    }
}
