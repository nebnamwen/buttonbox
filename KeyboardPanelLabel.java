import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class KeyboardPanelLabel extends JLabel {

    static final Border raised;
    static final Border lowered;

    static final int wide = 20;
    static final int high = 40;

    boolean pressed = false;

    static {
	Border e, r, r2, r3, l, l2, l3;

	e = BorderFactory.createEmptyBorder(2,2,1,1);

	r = BorderFactory.createRaisedBevelBorder();
	r2 = BorderFactory.createCompoundBorder(r, r);
	r3 = BorderFactory.createCompoundBorder(r, r2);
	raised = BorderFactory.createCompoundBorder(e,r3);

	l = BorderFactory.createLoweredBevelBorder();
	l2 = BorderFactory.createCompoundBorder(l, l);
	l3 = BorderFactory.createCompoundBorder(l, l2);
	lowered = BorderFactory.createCompoundBorder(e,l3);
    }

    public KeyboardPanelLabel(KeyboardPanel parent, String text, int x, int y, int width) {
	super(text, SwingConstants.CENTER);

	if (text == "") {
	    setPreferredSize(new Dimension(parent.wide * width, 1));
	}
	else {
	    setPreferredSize(new Dimension(parent.wide * width, parent.high));
	    setOpaque(true);
	    setBorder(raised);
	}

	parent.add(this, x, y, width);
    }

    public boolean isPressed() {
	return pressed;
    }

    public void setPressed(boolean p) {
	pressed = p;
	if (p) { setBorder(lowered); }
	else { setBorder(raised); }
    }
}