package com.reviksedy.foodmania.screen;

import com.reviksedy.foodmania.Foodmania;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.CONTAINERS, Foodmania.MOD_ID);

    public static final RegistryObject<MenuType<GrillMenu>> GRILL_MENU =
            registerMenuType(GrillMenu::new, "grill_menu");

    public static final RegistryObject<MenuType<PotMenu>> POT_MENU =
            registerMenuType(PotMenu::new, "pot_menu");



    private static <T extends AbstractContainerMenu>RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
                                                                                                 String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}