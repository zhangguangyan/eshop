package z.common;

public interface CommandHandler<T extends Command> {
    void handle(T command);
}
