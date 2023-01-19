package io.github.askmeagain.endoflinehint;

import com.intellij.codeInsight.hints.FactoryInlayHintsCollector;
import com.intellij.codeInsight.hints.InlayHintsSink;
import com.intellij.codeInsight.hints.presentation.PresentationFactory;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicInteger;

public final class BaseInlayHintsCollector extends FactoryInlayHintsCollector {

  private final PresentationFactory factory;

  public BaseInlayHintsCollector(@NotNull Editor editor) {
    super(editor);
    factory = getFactory();
  }

  @Override
  public boolean collect(@NotNull PsiElement element, @NotNull Editor editor, @NotNull InlayHintsSink sink) {
    element.accept(new JavaRecursiveElementVisitor() {
      @Override
      public void visitIfStatement(PsiIfStatement statement) {
        var inlayPresentation = factory.roundWithBackgroundAndSmallInset(getFactory().smallText("_" + statement.getText().substring(0, 10)));

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