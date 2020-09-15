public class Notes {

    static final String[] NOTELETTER = {
	"C", null,
	"D", null,
	"E",
	"F", null,
	"G", null,
	"A", null,
	"B"
    };

    public static String noteLetter(int note) {
	return (NOTELETTER[note] != null) ?
	    NOTELETTER[note] :
	    NOTELETTER[note - 1] + "#";
    }

    public static int Octave(int note) {
	return (note / 12) - 1;
    }

    public static String noteName(int note) {
	int octave = Octave(note);
	if (octave < 0 || octave > 9) {
	    return "";
	}
	return noteLetter(note % 12) + octave;
    }
}