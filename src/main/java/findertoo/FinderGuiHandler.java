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

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by liach on 5/16/2015.
 *
 * @author liach
 */
@SideOnly(Side.CLIENT)
public class FinderGuiHandler implements IGuiHandler {

    /**
     * A list of GUIs in FinderToo.
     */
    public enum FinderGuis {
        FINDER_GUI(new FinderGui());

        private Gui ownedGui;
        FinderGuis(Gui gui) {
            this.ownedGui = gui;
        }

        protected static Gui getGuiFromId(int id) {
            try {
                return FinderGuis.values()[id].ownedGui;
            } catch (ArrayIndexOutOfBoundsException ex) {
                return null;
            }
        }
    }

    /**
     * Returns a instance of GuiScreen to be displayed to the user. Only used on the client side.
     * The only matter is the GUI ID.
     *
     * @param id The Gui ID Number
     * @param player The player viewing the Gui
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @return A GuiScreen to be displayed to the user, null if none.
     */
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return FinderGuis.getGuiFromId(id);
    }

    /**
     * This is a client-only mod. Do never use it!
     *
     * @param id The Gui ID Number
     * @param player The player viewing the Gui
     * @param world The current world
     * @param x X Position
     * @param y Y Position
     * @param z Z Position
     * @return null.
     */
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        return null;
    }
}
