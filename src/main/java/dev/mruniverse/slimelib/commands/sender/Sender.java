package dev.mruniverse.slimelib.commands.sender;

public interface Sender {

    Object getObject();

    void sendMessage(String message);

    default <T> T getSender(Class<T> sender, String commandString) {
        final Object object = getObject();

        if (sender == null) {
            throw new IllegalArgumentException(commandString + ", the sender is null");
        }

        if (object == null) {
            throw new IllegalArgumentException(commandString + ", the sender.object is null");
        }

        if (!sender.isInstance(object)) {
            throw new ClassCastException(commandString + ", sender can't be casted.");
        }

        return sender.cast(object);
    }

}
