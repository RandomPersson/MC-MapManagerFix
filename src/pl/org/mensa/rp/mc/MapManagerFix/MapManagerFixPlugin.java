package pl.org.mensa.rp.mc.MapManagerFix;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.bukkit.plugin.java.JavaPlugin;

public class MapManagerFixPlugin extends JavaPlugin {
	@Override
	public void onEnable() {
		try {
			Class<?> map_manager_timings_helper_class = Class.forName("org.inventivetalent.mapmanager.TimingsHelper");
			
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			Field server_version_field = map_manager_timings_helper_class.getField("PAPER_SPIGOT");
			boolean was_accessible = server_version_field.isAccessible();
			
			modifiersField.setAccessible(true);
			server_version_field.setAccessible(true);
			modifiersField.setInt(server_version_field, server_version_field.getModifiers() & ~Modifier.FINAL);
			
			server_version_field.set(null, true);
			
			modifiersField.setInt(server_version_field, server_version_field.getModifiers() & Modifier.FINAL);
			server_version_field.setAccessible(was_accessible);
			modifiersField.setAccessible(false);
			
		} catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e1) {
			e1.printStackTrace();
		}
	}
}
