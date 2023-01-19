package io.github.askmeagain.endoflinehint;

import com.intellij.codeInsight.hints.*;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class EndOfLineHintProvider implements InlayHintsProvider<EndOfLineHintState> {
  private static final String EndOfLineHint = "EndOfLineHint";
  private static final SettingsKey<EndOfLineHintState> KEY = new SettingsKey<>(EndOfLineHint);

  @Nullable
  @Override
  public InlayHintsCollector getCollectorFor(@NotNull PsiFile file,
                                             @NotNull Editor editor,
                                             @NotNull EndOfLineHintState settingsState,
                                             @NotNull InlayHintsSink inlayHintsSink) {
    return new HintCollector(editor, settingsState);
  }

  @NotNull
  @Override
  public EndOfLineHintState createSettings() {
    return EndOfLineHintState.getInstance();
  }

  @Nls(capitalization = Nls.Capitalization.Sentence)
  @NotNull
  @Override
  public String getName() {
    return EndOfLineHint;
  }

  @NotNull
  @Override
  public SettingsKey<EndOfLineHintState> getKey() {
    return KEY;
  }

  @Override
  public String getPreviewText() {
    return "public class Foo {\n" +
        "\tvoid main() {\n" +
        "\t\tif(123 > 333){\n" +
        "\t\t\tint i = 0;\n" +
        "\t\t}\n" +
        "\t}\n" +
        "}";
  }

  @Override
  public @NotNull ImmediateConfigurable createConfigurable(@NotNull EndOfLineHintState settings) {
    return new EndOfLineConfig(settings);
  }

  @Override
  public boolean isLanguageSupported(@NotNull Language language) {
    return "JAVA".equals(language.getID());
  }

  @Override
  public boolean isVisibleInSettings() {
    return true;
  }
}