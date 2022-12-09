package com.bankheatmap;

import com.google.inject.Provides;
import javax.inject.Inject;

import com.sun.tools.javac.jvm.Items;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.ItemContainerChanged;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Example"
)
public class BankHeatmapPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private BankHeatmapConfig config;

	@Override
	protected void startUp() throws Exception
	{

	}

	@Override
	protected void shutDown() throws Exception
	{

	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded widgetLoaded) throws InterruptedException {

		Widget bankedItemsWidget = client.getWidget(WidgetID.BANK_GROUP_ID,13);
		bankedItemsWidget.getItemId();

		//just like col log, opacity is 0 when filled, 120 when not for placeholders.

	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event){


		//if the bank is open
		if(client.getWidget(WidgetID.BANK_GROUP_ID,13) != null){
			Item itemList[] = event.getItemContainer().getItems();
			for(int i = 0; i < itemList.length; i++){
				System.out.println(itemList[i].toString());
				int itemId = itemList[i].getId();
				System.out.println(client.getItemDefinition(itemId).getName());

			}
			System.out.println();
		}

	}


	@Provides
	BankHeatmapConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BankHeatmapConfig.class);
	}
}
