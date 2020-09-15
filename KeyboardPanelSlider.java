import javax.swing.*;
import java.awt.Dimension;

public class KeyboardPanelSlider extends JSlider {

    public KeyboardPanelSlider(KeyboardPanel parent, int min, int max, int init,
			       String title, int x, int y, int width) {
	super(JSlider.HORIZONTAL, min, max, init);
	setPreferredSize(new Dimension(parent.wide * width - 10, parent.high * 3 / 4));
	setFocusable(false);

	addChangeListener(parent);

	JPanel panel = new JPanel();
	JLabel label = new JLabel(title, SwingConstants.CENTER);

	panel.add(label);
	panel.add(this);
	panel.setPreferredSize(new Dimension(parent.wide * width, parent.high * 3 / 2));
	parent.add(panel, x, y, width);
    }

}