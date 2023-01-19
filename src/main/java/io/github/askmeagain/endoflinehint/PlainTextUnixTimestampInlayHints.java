package io.github.askmeagain.endoflinehint;

import com.intellij.codeInsight.hints.*;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiPlainTextFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlainTextUnixTimestampInlayHints implements InlayHintsProvider<NoSettings> {
  private static final String UNIX_TIMESTAMP_HINTS = "UnixTimestampHints";
  private static final SettingsKey<NoSettings> KEY = new SettingsKey<>(UNIX_TIMESTAMP_HINTS);

  @Nullable
  @Override
  public InlayHintsCollector getCollectorFor(@NotNull PsiFile file,
                                             @NotNull Editor editor,
                                             @NotNull NoSettings settingsState,
                                             @NotNull InlayHintsSink inlayHintsSink) {
    return new BaseInlayHintsCollector(editor);
  }

  @NotNull
  @Override
  public NoSettings createSettings() {
    return new NoSettings();
  }

  @Nls(capitalization = Nls.Capitalization.Sentence)
  @NotNull
  @Override
  public String getName() {
    return UNIX_TIMESTAMP_HINTS;
  }

  @NotNull
  @Override
  public SettingsKey<NoSettings> getKey() {
    return KEY;
  }

  @Override
  public String getPreviewText() {
    return "abc";
  }

  @Override
  public ImmediateConfigurable createConfigurable(@NotNull NoSettings settings) {
    return null;
  }

  @Override
  public boolean isLanguageSupported(@NotNull Language language) {
    return "JAVA".equals(language.getID());
  }

  @Override
  public boolean isVisibleInSettings() {
    return false;
  }
}