package behavioral.memento.history;

import behavioral.memento.editor.Editor;

public class Memento {
    private Editor editor;
    private String backup;

    public Memento(Editor editor) {
        this.editor = editor;
        this.backup = editor.backup();
    }

    public void restore() {
        editor.restore(backup);
    }
}
