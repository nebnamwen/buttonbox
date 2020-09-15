import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;

public class KeyboardPanel extends JPanel implements ChangeListener {

    final static int rows = KeyGrid.GRIDKEY.length;

    static final Color[][] colors = {
	{
	    new Color(220, 165, 165),
	    new Color(135, 80, 80)
	},
	{
	    new Color(165, 165, 220),
	    new Color(80, 80, 135)
	}
    };

    public final int wide = 20;
    public final int high = 40;

    public final ButtonBox parent;

    public final KeyboardPanelLabel[][] keys = new KeyboardPanelLabel[rows][];

    // public final KeyboardPanelLabel space;
    // public final KeyboardPanelLabel[] shift = new KeyboardPanelLabel[2];

    public final JSlider split;
    public final JSlider[] transpose = new JSlider[2];

    public KeyboardPanel(ButtonBox parent) {
	super(new GridBagLayout());

	this.parent = parent;

        int maxX = 0;

        for (int i = 0; i < rows; i++) {
	    int w = KeyGrid.GRIDKEY[rows - 1 - i].length;
	    keys[rows - 1 - i] = new KeyboardPanelLabel[w];

            for (int j = 0; j < w; j++) {
		int x = i + 2*j;
		if (x > maxX) { maxX = x; }

                KeyboardPanelLabel label = new KeyboardPanelLabel(this, (rows - 1 - i) + "," + j, x, i + 2, 2);
		keys[rows - 1 - i][j] = label;
            }
        }

        for (int x = 0; x <= maxX; x++) {
	    new KeyboardPanelLabel(this, "", x, 0, 1);
        }

	// shift[0] = new KeyboardPanelLabel(this, "SHIFT", 0, rows + 1, rows - 1); 
	// shift[1] = new KeyboardPanelLabel(this, "SHIFT", rows - 1 + 2 * KeyGrid.GRIDKEY[0].length, rows + 1, rows - 1); 
	// space = new KeyboardPanelLabel(this, "SPACE", 6, rows + 2, 12);

	split = new KeyboardPanelSlider(this, 5, 12, 8, "Split", 8, 1, 8);
	transpose[0] = new KeyboardPanelSlider(this, 12, 78, 46, "Transpose", 0, 1, 8);
	transpose[1] = new KeyboardPanelSlider(this, 12, 78, 46, "Transpose", 16, 1, 8);

	Recalculate();
    }

    public void add(JComponent child, int x, int y, int width) {
	GridBagConstraints c = new GridBagConstraints();
	c.gridx = x;
	c.gridy = y;
	c.gridwidth = width;
	add(child, c);
    }

    public int channelForGrid(int[] grid) {
	int horizon = grid[1] * 2 - grid[0];
	int channel = (horizon > (int)split.getValue()) ? 1 : 0;
	return channel;
    }

    public int noteForGrid(int[] grid) {
	int offset = grid[0] * 5 + grid[1] * 2;
	return (int)transpose[channelForGrid(grid)].getValue() + offset;
    }

    public void stateChanged(ChangeEvent e) {
	parent.allNotesOff();
	Recalculate();
    }

    public void Recalculate() {
	for (int i = 0; i < rows; i++) {
	    for (int j = 0; j < KeyGrid.GRIDKEY[i].length; j++) {
		int[] grid = { i, j };
		int channel = channelForGrid(grid);
		String name = Notes.noteName(noteForGrid(grid));
		int sharp = name.contains("#") ? 1 : 0;

		keys[i][j].setText(name);
		keys[i][j].setBackground(colors[channel][sharp]);
	    }
	}
	repaint();
    }
}
