package fr.raphoulfifou.cyan.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import fr.raphoulfifou.cyan.config.options.CyanOptions;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;

import java.io.IOException;

@Environment(EnvType.CLIENT)
public class ClothConfigScreenFactory implements ConfigScreenFactory<Screen>
{
	private final CyanOptions config;
	private final CyanOptions.GeneralSettings generalSettings = new CyanOptions.GeneralSettings();

	public ClothConfigScreenFactory(CyanOptions configArg)
	{
		this.config = configArg;
	}

	@Override
	public Screen create(Screen parent)
	{
		SavingRunnable savingRunnable = new SavingRunnable();

		ConfigBuilder builder = ConfigBuilder.create()
				.setParentScreen(parent)
				.setTitle(new TranslatableText("screen.cyan.config.title"))
				.setSavingRunnable(savingRunnable);
		ConfigEntryBuilder entryBuilder = builder.entryBuilder();

		ConfigCategory general = builder.getOrCreateCategory(new TranslatableText("category.cyan.general"));
		general.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("options.cyan.allowBed"), generalSettings.allowBed)
				.setTooltip(new TranslatableText("mm.msg.allowBed"))
				.setDefaultValue(generalSettings.allowBed)
				.setSaveConsumer((value) -> {
					if (generalSettings.allowBed != value) {
						savingRunnable.reloadResources = true;
					}
					generalSettings.allowBed = value;
				})
				.build());

		return builder.build();
	}

	private class SavingRunnable implements Runnable
	{
		public boolean reloadResources = false;

		@Override
		public void run()
		{
			try {
				config.writeChanges();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (reloadResources)
			{
				MinecraftClient.getInstance().reloadResources();
			}
			reloadResources = false;
		}
	}
}
