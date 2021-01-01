package kata7.app;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
import kata7.view.BlockDisplay;

class BlockPanel extends JPanel implements BlockDisplay{
    private final MouseHandler handler;
    private final int max;
    private final int size;
    private int x;
    private int y;
    private Moved moved;
    
    public BlockPanel(int max, int size) {
        this.handler = new MouseHandler();
        this.max = max;
        this.size = size;
        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }
    
    @Override
    public void paint(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0,0,getWidth(),getWidth());
        
        int d = max * size;
        g.setColor(Color.black);
        for (int i = 0; i <= max; i++) {
            int c = i * size;
            g.drawLine(0, c, d, c);
            g.drawLine(c, 0, c, d);   
        }
        
        g.setColor(Color.orange);
        g.fillRect(x, y, size, size);
    }

    @Override
    public void paintBlock(int x, int y) {
        this.x = x;
        this.y = y;
        repaint();
    }

    @Override
    public void on(Moved moved) {
        this.moved = moved;
    }
    
    private class MouseHandler implements MouseListener, MouseMotionListener{

        private boolean grabbeed;

        @Override
        public void mouseClicked(MouseEvent me) {
        }

        @Override
        public void mousePressed(MouseEvent me) {
            if((me.getX() < x || me.getX() > x + size)) return;
            if((me.getY() < y || me.getY() > y + size)) return;
            grabbeed = true;
        }

        @Override
        public void mouseReleased(MouseEvent me) {
            grabbeed = false;
        }

        @Override
        public void mouseEntered(MouseEvent me) {
        }

        @Override
        public void mouseExited(MouseEvent me) {
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            moved.to(me.getX(), me.getY());
        }

        @Override
        public void mouseMoved(MouseEvent me) {
        }
        
    }
}
