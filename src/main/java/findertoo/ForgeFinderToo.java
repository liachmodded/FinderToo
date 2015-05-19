/*
 * This file is part of FinderToo, licensed under MIT License (MIT).
 *
 * Copyright (c) 2015 liachmodded <http://github.com/liachmodded>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package findertoo;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLModDisabledEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * The main file of FinderToo.
 *
 * <p>Created by liach on 5/16/2015.
 *
 * @author liach
 */
@SideOnly(Side.CLIENT)
@Mod(
        modid = ForgeFinderToo.NAME,
        name = ForgeFinderToo.NAME,
        clientSideOnly = true,
        canBeDeactivated = true,
        useMetadata = true,
        version = "%VERSION%",
        guiFactory = "findertoo.FinderGuiFactory"
)
public class ForgeFinderToo {
    public static final String NAME = "FinderToo";
    @Mod.Metadata(ForgeFinderToo.NAME)
    private ModMetadata ftMeta = new ModMetadata();
    @Mod.Instance(ForgeFinderToo.NAME)
    private static ForgeFinderToo instance;
    public static Logger finderLog;
    public static File finderConfig;
    public static float searchDistance;

    private void initMeta() {
        ftMeta.name = ForgeFinderToo.NAME;
        ftMeta.modId = ForgeFinderToo.NAME;
        ftMeta.description = "A success of FinderMod.";
        ftMeta.url = "https://github.com/liachmodded/FinderToo";
        ftMeta.updateUrl = ftMeta.url;
        ftMeta.version = "%VERSION%";
        ftMeta.credits = "contributors";
        ftMeta.authorList.add("liach");
        ftMeta.screenshots=new String[0];
        ftMeta.logoFile="";
    }

    @Mod.InstanceFactory
    public static ForgeFinderToo getInstance() {
        if (instance == null) {
            instance = new ForgeFinderToo();
        }
        return instance;
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        initMeta();
        finderLog = event.getModLog();
        MinecraftForge.EVENT_BUS.register(new EventListener());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new FinderGuiHandler());
    }

    @Mod.EventHandler
    public void disable(FMLModDisabledEvent event) {
        finderLog.info("Mod disabled");
    }
}
