import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicReference;

import static org.lwjgl.opengl.GL11.*;

public class LW2_AWT_Canvas_Buttons extends LW2_AWT_Canvas {



    public LW2_AWT_Canvas_Buttons(FPSUpdater fps) throws LWJGLException {
        super(fps);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JButton startStopBtn = new JButton("Start/Stop Gears");
        JButton vsync = new JButton("Toggle VSync");

        vsync.addActionListener((e) -> {this.toggleVSync();});

        panel.add(startStopBtn, BorderLayout.EAST);
        panel.add(vsync, BorderLayout.WEST);

        frame.add(panel, BorderLayout.SOUTH);
    }

}