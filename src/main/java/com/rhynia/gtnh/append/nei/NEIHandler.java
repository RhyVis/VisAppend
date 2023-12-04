package com.rhynia.gtnh.append.nei;

import net.minecraft.nbt.NBTTagCompound;

import com.rhynia.gtnh.append.Tags;

import cpw.mods.fml.common.event.FMLInterModComms;

public class NEIHandler {

    public static void IMCSender() {
        // Handler
        sendHandler("append.recipe.astraForge", "gregtech:gt.blockmachines:17501", 1);
        sendHandler("append.recipe.ultimateHeater", "gregtech:gt.blockmachines:17502", 2);
        sendHandler("append.recipe.assemblyMatrix", "gregtech:gt.blockmachines:17503", 2);
        sendHandler("append.recipe.microAssembly", "gregtech:gt.blockmachines:17503", 2);
        sendHandler("append.recipe.superconductingBinder", "gregtech:gt.blockmachines:17504", 2);
        // Catalyst
        sendCatalyst("append.recipe.astraForge", "gregtech:gt.blockmachines:17501");
        sendCatalyst("append.recipe.ultimateHeater", "gregtech:gt.blockmachines:17502");
        sendCatalyst("append.recipe.assemblyMatrix", "gregtech:gt.blockmachines:17503");
        sendCatalyst("append.recipe.microAssembly", "gregtech:gt.blockmachines:17503");
        sendCatalyst("append.recipe.superconductingBinder", "gregtech:gt.blockmachines:17504");
        sendCatalyst("gt.recipe.vacuumfreezer", "gregtech:gt.blockmachines:17505", -10);

    }

    private static void sendHandler(String aName, String aBlock, int aRecipePerPage) {
        NBTTagCompound aNBT = new NBTTagCompound();
        aNBT.setString("handler", aName);
        aNBT.setString("modName", Tags.MODNAME);
        aNBT.setString("modId", Tags.MODID);
        aNBT.setBoolean("modRequired", true);
        aNBT.setString("itemName", aBlock);
        aNBT.setInteger("handlerHeight", 135);
        aNBT.setInteger("handlerWidth", 166);
        aNBT.setInteger("maxRecipesPerPage", aRecipePerPage);
        aNBT.setInteger("yShift", 6);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerHandlerInfo", aNBT);
    }

    private static void sendCatalyst(String aName, String aStack) {
        NBTTagCompound aNBT = new NBTTagCompound();
        aNBT.setString("handlerID", aName);
        aNBT.setString("itemName", aStack);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerCatalystInfo", aNBT);
    }
    private static void sendCatalyst(String aName, String aStack, int aPriority) {
        NBTTagCompound aNBT = new NBTTagCompound();
        aNBT.setString("handlerID", aName);
        aNBT.setString("itemName", aStack);
        aNBT.setInteger("priority", aPriority);
        FMLInterModComms.sendMessage("NotEnoughItems", "registerCatalystInfo", aNBT);
    }

}
