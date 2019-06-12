package com.dub.spring.site.chat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.session.SessionDestroyedEvent;
import org.springframework.stereotype.Service;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

@Service
public class SessionDestroyedListener
        implements ApplicationListener<SessionDestroyedEvent>
{
    private final Set<Consumer<SessionDestroyedEvent>> callbacks =
            new HashSet<>();
    private final Set<Consumer<SessionDestroyedEvent>> callbacksAddedWhileLocked =
            new HashSet<>();
    
    private static final Logger log = LogManager.getLogger();

    @Override
    public void onApplicationEvent(SessionDestroyedEvent event)
    {
    	log.info("onApplicationEvent");
        synchronized(this.callbacks)
        {
            this.callbacksAddedWhileLocked.clear();
            this.callbacks.forEach(c -> c.accept(event));
            try {
                this.callbacksAddedWhileLocked.forEach(c -> c.accept(event));
            } catch(ConcurrentModificationException ignore) { }
        }
    }

    public void registerOnRemoveCallback(Consumer<SessionDestroyedEvent> callback)
    {
        this.callbacksAddedWhileLocked.add(callback);
        synchronized(this.callbacks)
        {
            this.callbacks.add(callback);
        }
    }

    public void deregisterOnRemoveCallback(Consumer<SessionDestroyedEvent> callback)
    {
        synchronized(this.callbacks)
        {
            this.callbacks.remove(callback);
        }
    }
}
