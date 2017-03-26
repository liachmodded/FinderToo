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

import com.mumfrey.liteloader.PostRenderListener;
import com.mumfrey.liteloader.Tickable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.math.AxisAlignedBB;

import java.io.File;

/**
 * Created by liach on 11/8/2015.
 *
 * @author liach
 */
@SuppressWarnings("unused")
public class LiteModFinderToo implements PostRenderListener, Tickable {

    public static final String NAME = "FinderToo";
    public static final String VERSION = "0.1";

    @Override
    public String getName() {
        return LiteModFinderToo.NAME;
    }

    @Override
    public String getVersion() {
        return LiteModFinderToo.VERSION;
    }

    @Override
    public void init(File configFile) {
    }

    @Override
    public void upgradeSettings(String version, File configPath, File oldConfigPath) {
    }

    @Override
    public void onPostRenderEntities(float partialTicks) {
        Minecraft.getMinecraft().world.loadedEntityList.stream()
            .filter(Entity::isEntityAlive)
            .filter(Entity::isInvisible).forEach(entity ->
            RenderGlobal.drawSelectionBoundingBox(entity.getEntityBoundingBox(), 1F, 0F, 0F, .5F));
    }

    @Override
    public void onPostRender(float partialTicks) {
        Minecraft.getMinecraft().world.loadedTileEntityList.stream()
            .filter(te -> te.getClass() == TileEntityMobSpawner.class)
            .forEach(te ->
                RenderGlobal.drawSelectionBoundingBox(new AxisAlignedBB(te.getPos()), 0F, 1F, 0F, .5F));
    }

    @Override
    public void onTick(Minecraft minecraft, float partialTicks, boolean inGame, boolean clock) {
        //        throw new UnsupportedOperationException("not implemented"); //TODO Implement this
    }
}
