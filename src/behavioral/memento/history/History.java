package behavioral.memento.history;

import java.util.ArrayList;
import java.util.List;

import behavioral.memento.commands.Command;

public class History {
    private List<Pair> history = new ArrayList<Pair>();
    private int virtualSize;

    private class Pair {
        Command command;
        Memento memento;

        Pair(Command c, Memento m) {
            command = c;
            memento = m;
        }

        private Command getCommand() {
            return command;
        }

        private Memento getMemento() {
            return memento;
        }
    }

    public void push(Command c, Memento m) {
        if (virtualSize != history.size()) {
            if (virtualSize > 0) {
                // Truncate the history to the current virtualSize
                history = new ArrayList<>(history.subList(0, virtualSize - 1));
            } else {
                // Reset the history if virtualSize is 0
                history = new ArrayList<>();
            }
        }
        history.add(new Pair(c, m));
        virtualSize = history.size();
    }

    public boolean undo() {
        Pair pair = getUndo();
        if (pair == null) {
            return false;
        }
        System.out.println("Undoing: " + pair.getCommand().getName());
        pair.getMemento().restore();
        return true;
    }

    public boolean redo() {
        Pair pair = getRedo();
        if (pair == null) {
            return false;
        }
        System.out.println("Redoing: " + pair.getCommand().getName());
        pair.command.execute();
        return true;
    }

    private Pair getUndo() {
        if (virtualSize == 0) {
            return null;
        }
        // virtualSize = Math.max(0, virtualSize - 1);
        virtualSize--;
        return history.get(virtualSize);
    }

    private Pair getRedo() {
        if (virtualSize == history.size()) {
            return null;
        }
        // virtualSize = Math.min(history.size(), virtualSize + 1);
        virtualSize++;
        return history.get(virtualSize - 1);
    }
}
