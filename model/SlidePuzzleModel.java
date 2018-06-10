package model;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

public class SlidePuzzleModel {
    private final Collection<IModelSubscriber> subscribers = new CopyOnWriteArrayList<>();

    private BlockState blockState;

    public SlidePuzzleModel(BlockState bs) {
        if (bs == null) {
            throw new NullPointerException("No parameter");
        }

        this.blockState = bs;
    }

    public BlockState getBlockState() {
        return blockState;
    }

    public void setBlockState(BlockState blockState) {
        if (blockState == null){
            throw new NullPointerException("No parameter");
        }

        this.blockState = blockState;
    }

    protected void notifySubscribers() {
        for (final IModelSubscriber subscriber : subscribers)
            notifySubscriber(subscriber);
    }

    private void notifySubscriber(IModelSubscriber subscriber) {
        assert subscriber != null;
        subscriber.modelChanged(this);
    }

    public void subscribe(IModelSubscriber subscriber) {
        if (subscriber == null)
            throw new NullPointerException("No parameter");
        if (subscribers.contains(subscriber))
            throw new IllegalArgumentException("Resubmit: " + subscriber);
        subscribers.add(subscriber);
        notifySubscriber(subscriber);
    }

    public void unsubscribe(IModelSubscriber subscriber) {
        if (subscriber == null)
            throw new NullPointerException("No parameter");
        if (!subscribers.contains(subscriber))
            throw new IllegalArgumentException("Unknown subscriber: " + subscriber);
        subscribers.remove(subscriber);
    }
}
