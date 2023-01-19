package io.github.askmeagain.endoflinehint;

import com.intellij.codeInsight.hints.FactoryInlayHintsCollector;
import com.intellij.codeInsight.hints.InlayHintsSink;
import com.intellij.codeInsight.hints.presentation.PresentationFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.JavaRecursiveElementVisitor;
import com.intellij.psi.PsiCodeBlock;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIfStatement;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;

public final class HintCollector extends FactoryInlayHintsCollector {

  private final PresentationFactory factory;
  private final EndOfLineHintState endOfLineHintState;

  public HintCollector(@NotNull Editor editor, EndOfLineHintState state) {
    super(editor);
    endOfLineHintState = state;
    factory = getFactory();
  }

  @Override
  public boolean collect(@NotNull PsiElement element, @NotNull Editor editor, @NotNull InlayHintsSink sink) {
    element.accept(new JavaRecursiveElementVisitor() {
      @Override
      public void visitIfStatement(PsiIfStatement statement) {
        var text = statement.getText();
        var index = Math.min(endOfLineHintState.getNumberOfLetters(), text.length() - 1);
        var base = getFactory().smallText(text.substring(0, index));
        var inlayPresentation = factory.roundWithBackgroundAndSmallInset(base);

        var offset = new AtomicInteger();
        statement.accept(new JavaRecursiveElementVisitor() {
          @Override
          public void visitCodeBlock(PsiCodeBlock block) {
            if (offset.get() == 0) {
              offset.set(block.getLastChild().getTextOffset());
            }
          }
        });

        sink.addInlineElement(offset.get(), true, inlayPresentation, true);
        super.visitIfStatement(statement);
      }
    });

    return false;
  }
}