package io.github.askmeagain.endoflinehint;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Data
@State(name = "EndOfLineHintState", storages = @Storage("end-of-line-hint.xml"))
public class EndOfLineHintState implements PersistentStateComponent<EndOfLineHintState> {

  private Integer numberOfLetters = 10;

  public static EndOfLineHintState getInstance() {
    return ApplicationManager.getApplication().getService(EndOfLineHintState.class);
  }

  @Nullable
  @Override
  public EndOfLineHintState getState() {
    return this;
  }

  @Override
  public void loadState(@NotNull EndOfLineHintState state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  @Override
  public void noStateLoaded() {
    PersistentStateComponent.super.noStateLoaded();
  }

}