package audio.player;

public interface Notifiable {
    /**
     * This will notify an object on the same thread so the receiver should not lock the thread or take too long.
     * @param sender the origin of the notification
     */
    void notifyObject(Object sender);
}
