package me.blueslime.slimelib.utils.consumer;

import java.util.function.Consumer;
import java.util.function.Predicate;

public interface PluginConsumer<T> {

    T executeConsumer() throws Exception;

    interface PluginOutConsumer {
        void executeConsumer() throws Exception;
    }

    interface PluginExecutableConsumer<T> {
        T accept();
    }

    interface ReturnablePluginConsumer<V, K> {
        V accept(K arg);
    }

    /**
     * ReturnablePluginConsumer works similar to {@link Predicate}
     * @param consumer to execute
     * @return V the created consumer
     * @param <V> value to return
     * @param <K> entry parameter to get the value
     */
    static <V, K> ReturnablePluginConsumer<V, K> ofReturnable(ReturnablePluginConsumer<V, K> consumer) {
        return consumer;
    }

    static void process(PluginOutConsumer consumer) {
        try {
            consumer.executeConsumer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void process(String message, PluginOutConsumer consumer) {
        try {
            consumer.executeConsumer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    static void process(PluginOutConsumer consumer, Consumer<Exception> exception) {
        try {
            consumer.executeConsumer();
        } catch (Exception ex) {
            exception.accept(ex);
        }
    }

    static <T> T ofUnchecked(final PluginConsumer<T> template) {
        T results = null;
        try {
            results = template.executeConsumer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    static <T> T ofUnchecked(final PluginConsumer<T> template, final Consumer<Exception> exception, PluginExecutableConsumer<T> defValue) {
        try {
            return template.executeConsumer();
        } catch (Exception ex) {
            exception.accept(ex);
        }
        return defValue.accept();
    }

    static <T> T ofUnchecked(final PluginConsumer<T> template, final Consumer<Exception> exception) {
        T results = null;
        try {
            results = template.executeConsumer();
        } catch (Exception ex) {
            exception.accept(ex);
        }
        return results;
    }

    static <T> T ofUnchecked(final PluginConsumer<T> template, PluginExecutableConsumer<T> defValue) {
        try {
            return template.executeConsumer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return defValue.accept();
    }

    static <T> T ofUnchecked(String message, final PluginConsumer<T> template) {
        T results = null;
        try {
            results = template.executeConsumer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    static <T> T ofUnchecked(String message, final PluginConsumer<T> template, T defValue) {
        T results = defValue;
        try {
            results = template.executeConsumer();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }

    static <T> PluginConsumer<T> of(PluginConsumer<T> c){ return c; }
}

