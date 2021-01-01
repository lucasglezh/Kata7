package kata7.app;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import kata7.control.BlockPresenter;
import kata7.control.Command;
import kata7.control.DownCommand;
import kata7.control.LeftCommand;
import kata7.control.RightCommand;
import kata7.control.UpCommand;
import kata7.model.Block;
import kata7.view.BlockDisplay;

public class Main extends JFrame{

    
    public static void main(String[] args) {
        new Main().execute();
    }
    private Block block;
    private BlockDisplay blockDisplay;
    private BlockPresenter blockPresenter;
    private HashMap<String,Command> commands;
    
    public Main() {
        this.setTitle("Block Shifter");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 740);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
    }
    
    private void execute() {
        this.block = new Block(4, 4);
        this.blockPresenter = new BlockPresenter(block, blockDisplay);
        this.commands = createCommands();
        this.setVisible(true);
    }

    private JPanel blockPanel() {
        BlockPanel panel = new BlockPanel(Block.MAX, BlockPresenter.SIZE);
        this.blockDisplay = panel;
        return panel;
    }

    private HashMap<String, Command> createCommands() {
        HashMap<String,Command> commands = new HashMap<>();
        commands.put("L", new LeftCommand(block));
        commands.put("R", new RightCommand(block));
        commands.put("U", new UpCommand(block));
        commands.put("D", new DownCommand(block));
        return commands;
    }
    
    private Component toolbar() {
        JMenuBar bar = new JMenuBar();
        bar.setLayout(new FlowLayout(FlowLayout.CENTER));  
        bar.add(button("L"));
        bar.add(button("R"));
        bar.add(button("U"));
        bar.add(button("D"));
        return bar;
    }

    private JButton button(String name) {
        JButton button =  new JButton(name);
        button.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                commands.get(name).execute();
            }
        });
        return button;
    }
}
