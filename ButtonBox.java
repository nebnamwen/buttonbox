import javax.sound.midi.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class ButtonBox extends JPanel implements Runnable, KeyListener {

    int split = 32;
    int[] transpose = {46, 46};

    public Synthesizer synth;
    public MidiChannel[] channels;
    public String[] instruments = new String[128];

    KeyboardPanel keyboardpanel;

    public ButtonBox() {
	setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

	try {
	    synth = MidiSystem.getSynthesizer();
	    synth.open();
	    Instrument[] getinstruments = synth.getLoadedInstruments();
	    for (int i = 0; i < 128; i++) {
		instruments[i] = getinstruments[i].getName();
	    }
	    channels = synth.getChannels();
	}
	catch (MidiUnavailableException e) {
	    System.err.println("Could not initialize midi!");
	    System.exit(1);
	}

	addKeyListener(this);

	keyboardpanel = new KeyboardPanel(this);
	add(keyboardpanel);

	JPanel controlpanels = new JPanel();
	controlpanels.setLayout(new BoxLayout(controlpanels, BoxLayout.X_AXIS));
	controlpanels.add(new ControlPanel(this,0));
	controlpanels.add(new ControlPanel(this,1));
	add(controlpanels);

	setFocusable(true);
    }

    public void keyTyped(KeyEvent e) {}

    public void keyPressed(KeyEvent e) {
	handleKeyEvent(e, true);
    }

    public void keyReleased(KeyEvent e) {
	handleKeyEvent(e, false);
    }

    public void handleKeyEvent(KeyEvent e, boolean pressed) {
	int keycode = e.getKeyCode();
	int[] grid = KeyGrid.KeyGrid(keycode);
	if (grid != null) {
	    KeyboardPanelLabel label = keyboardpanel.keys[grid[0]][grid[1]];
	    if (label.isPressed() && pressed) { return; }
	    else {
		label.setPressed(pressed);
		channels[keyboardpanel.channelForGrid(grid)].noteOn(keyboardpanel.noteForGrid(grid), pressed ? 100 : 0);
	    }
	}
    }

    public void allNotesOff() {
	for (int i = 0; i < 2; i++) {
	    channels[i].allNotesOff();
	}
    }

    public void run() {
	JFrame f = new JFrame("ButtonBox");
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	f.add(this);
	f.pack();
	f.setResizable(false);
	f.setVisible(true);
    }

    public static void main(String[] args) {
	SwingUtilities.invokeLater(new ButtonBox());
    }
}
