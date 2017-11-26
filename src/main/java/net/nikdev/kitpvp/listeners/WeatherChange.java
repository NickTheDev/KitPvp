package net.nikdev.kitpvp.listeners;

import net.nikdev.kitpvp.lang.ConfigKeys;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

import static net.nikdev.kitpvp.lang.ConfigKeys.ALLOW_RAIN;

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
        if(!ConfigKeys.getBool(ALLOW_RAIN)) {
            if(event.toWeatherState()) {
                event.setCancelled(true);
            }

        }

    }

}
