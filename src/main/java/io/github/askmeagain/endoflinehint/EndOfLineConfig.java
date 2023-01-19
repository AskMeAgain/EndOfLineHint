package io.github.askmeagain.endoflinehint;

import com.intellij.codeInsight.hints.ChangeListener;
import com.intellij.codeInsight.hints.ImmediateConfigurable;
import com.intellij.util.ui.FormBuilder;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@RequiredArgsConstructor
public class EndOfLineConfig implements ImmediateConfigurable {

  private final EndOfLineHintState state;

  @NotNull
  @Override
  public JComponent createComponent(@NotNull ChangeListener changeListener) {

    var jTextField = new JTextField();
    jTextField.setSize(300, 50);
    jTextField.setText(state.getNumberOfLetters() + "");

    jTextField.getDocument().addDocumentListener(new DocumentListener() {
      public void changedUpdate(DocumentEvent e) {
      }

      public void removeUpdate(DocumentEvent e) {
        update();
      }

      public void insertUpdate(DocumentEvent e) {
        update();
      }

      public void update() {
        state.setNumberOfLetters(Integer.parseInt(jTextField.getText()));
        changeListener.settingsChanged();
      }
    });

    return FormBuilder.createFormBuilder()
        .addLabeledComponent("Number of letters", jTextField)
        .addComponentFillVertically(new JPanel(), 0)
        .getPanel();
  }
}
