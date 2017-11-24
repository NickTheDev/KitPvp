package net.nikdev.kitpvp.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * Listener implementation for the {@link WeatherChangeEvent}.
 *
 * @author NickTheDev
 * @since 1.0
 */
public class WeatherChange implements Listener {

    /**
     * Listens for the specified event.
     *
     * @param event Event instance.
     */
    @EventHandler
    public void weatherChange(WeatherChangeEvent event) {
        if(event.toWeatherState()) {
            event.setCancelled(true);
        }

    }

}
