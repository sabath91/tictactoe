package org.czyz.ui;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Interaction {
    String action(Consumer<String> onError, Function<String, Boolean> function);
}
