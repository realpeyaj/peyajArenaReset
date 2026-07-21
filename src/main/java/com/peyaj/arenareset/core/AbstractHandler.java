package com.peyaj.arenareset.core;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class AbstractHandler {

    private final JavaPlugin plugin;
    private final AsyncExecutor defaultAsyncExecutor;
    private final AsyncExecutor scheduledAsyncExecutor;

    public AbstractHandler(JavaPlugin plugin) {
        this.plugin = plugin;
        this.defaultAsyncExecutor = new AsyncExecutor();
        this.scheduledAsyncExecutor = new AsyncExecutor();
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }

    public abstract boolean initialize();

    public abstract boolean terminate();

    public AsyncExecutor getDefaultAsyncExecutor() {
        return defaultAsyncExecutor;
    }

    public AsyncExecutor getScheduledAsyncExecutor() {
        return scheduledAsyncExecutor;
    }

    public static class AsyncExecutor {
        public boolean executeFuture(Logger logger, FutureTask<Boolean> task, int timeout, TimeUnit unit) {
            try {
                new Thread(task).start();
                return task.get(timeout, unit);
            } catch (Exception e) {
                logger.severe("Task execution failed: " + e.getMessage());
                return false;
            }
        }

        public <T> T fetchExecutionResult(Logger logger, FutureTask<T> task, int timeout, TimeUnit unit) {
            try {
                new Thread(task).start();
                return task.get(timeout, unit);
            } catch (Exception e) {
                logger.severe("Task execution fetch failed: " + e.getMessage());
                return null;
            }
        }

        public <T> java.util.List<T> fetchExecutionResultAsList(Logger logger, FutureTask<java.util.List<T>> task,
                int timeout, TimeUnit unit) {
            try {
                new Thread(task).start();
                return task.get(timeout, unit);
            } catch (Exception e) {
                logger.severe("Task execution fetch list failed: " + e.getMessage());
                return new java.util.ArrayList<>();
            }
        }

        public void shutdown() {
            // Nothing to forcefully shutdown in this simple stub
        }
    }
}
