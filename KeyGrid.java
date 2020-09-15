import java.awt.event.*;

public class KeyGrid {

    public static final int[][] GRIDKEY = {
	{
	    KeyEvent.VK_Z,
	    KeyEvent.VK_X,
	    KeyEvent.VK_C,
	    KeyEvent.VK_V,
	    KeyEvent.VK_B,
	    KeyEvent.VK_N,
	    KeyEvent.VK_M,
	    KeyEvent.VK_COMMA,
	    KeyEvent.VK_PERIOD,
	    KeyEvent.VK_SLASH
	},
	{
	    KeyEvent.VK_A,
	    KeyEvent.VK_S,
	    KeyEvent.VK_D,
	    KeyEvent.VK_F,
	    KeyEvent.VK_G,
	    KeyEvent.VK_H,
	    KeyEvent.VK_J,
	    KeyEvent.VK_K,
	    KeyEvent.VK_L,
	    KeyEvent.VK_SEMICOLON,
	    KeyEvent.VK_QUOTE
	},
	{
	    KeyEvent.VK_Q,
	    KeyEvent.VK_W,
	    KeyEvent.VK_E,
	    KeyEvent.VK_R,
	    KeyEvent.VK_T,
	    KeyEvent.VK_Y,
	    KeyEvent.VK_U,
	    KeyEvent.VK_I,
	    KeyEvent.VK_O,
	    KeyEvent.VK_P,
	    KeyEvent.VK_OPEN_BRACKET,
	    KeyEvent.VK_CLOSE_BRACKET
	},
	{
	    KeyEvent.VK_1,
	    KeyEvent.VK_2,
	    KeyEvent.VK_3,
	    KeyEvent.VK_4,
	    KeyEvent.VK_5,
	    KeyEvent.VK_6,
	    KeyEvent.VK_7,
	    KeyEvent.VK_8,
	    KeyEvent.VK_9,
	    KeyEvent.VK_0,
	    KeyEvent.VK_MINUS,
	    KeyEvent.VK_EQUALS
	},
    };

    public static final int[][] KEYGRID;

    static {
	KEYGRID = new int[256][2];

	for (int k = 0; k < KEYGRID.length; k++) {
	    KEYGRID[k][0] = -1;
	    KEYGRID[k][1] = -1;
	}

	for (int i = 0; i < GRIDKEY.length; i++) {
	    for (int j = 0; j < GRIDKEY[i].length; j++) {
		KEYGRID[GRIDKEY[i][j]][0] = i;
		KEYGRID[GRIDKEY[i][j]][1] = j;
	    }
	}
    }

    public static int[] KeyGrid(int key) {
	if (KEYGRID[key][0] < 0) {
	    return null;
	}
	else {
	    return KEYGRID[key];
	}
    }

    public static void main(String[] args) {
	int[] somekeys = { KeyEvent.VK_D, KeyEvent.VK_B, KeyEvent.VK_3, 111 };
	for (int i = 0; i < somekeys.length; i++) {
	    int[] grid = KeyGrid(somekeys[i]);
	    if (grid == null) {
		System.out.println("null");
	    }
	    else {
		System.out.println(grid[0] + " " + grid[1]);
	    }
	}
    }
}
