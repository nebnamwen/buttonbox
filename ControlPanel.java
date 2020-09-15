import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ControlPanel extends JPanel implements ActionListener {

    final ButtonBox parent;
    final int channel;
    final JComboBox instrument;

    public ControlPanel(ButtonBox parent, int channel) {
	this.parent = parent;
	this.channel = channel;

	instrument = new JComboBox<String>(parent.instruments);
	instrument.setFocusable(false);
	instrument.addActionListener(this);
	add(instrument);
    }

    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == instrument) {
	    parent.channels[channel].programChange(instrument.getSelectedIndex());
	}
    }
}
