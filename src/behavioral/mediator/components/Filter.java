package behavioral.mediator.components;

import javax.swing.*;

import behavioral.mediator.mediator.Mediator;
import behavioral.mediator.mediator.Note;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Concrete components don't talk with each other. They have only one
 * communication channel–sending requests to the mediator.
 */
public class Filter extends JTextField implements Component {
    private Mediator mediator;
    private ListModel listModel;

    public Filter() {
    }

    // JTextField
    @Override
    protected void processComponentKeyEvent(KeyEvent keyEvent) {
        String start = getText();
        searchElements(start);
    }

    public void setList(ListModel listModel) {
        this.listModel = listModel;
    }

    private void searchElements(String s) {
        if (listModel == null) {
            return;
        }

        if (s.equals("")) {
            mediator.setElementsList(listModel);
            return;
        }

        ArrayList<Note> notes = new ArrayList<>();
        for (int i = 0; i < listModel.getSize(); i++) {
            notes.add((Note) listModel.getElementAt(i));
        }
        DefaultListModel<Note> listModel = new DefaultListModel<>();
        for (Note note : notes) {
            if (note.getName().contains(s)) {
                listModel.addElement(note);
            }
        }
        mediator.setElementsList(listModel);
    }

    // Component
    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public String getName() {
        return "Filter";
    }
}
