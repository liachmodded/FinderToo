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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;

import java.util.Comparator;

import javax.vecmath.Color3f;
import javax.vecmath.Point3f;

/**
 * Created by liach on 5/16/2015.
 *
 * @author liach
 */
public class RgbPoint3f extends Point3f {
    public Color3f color;
    public boolean isBlock;
    public BlockPos blockPos;

    public RgbPoint3f(Point3f point, Color3f color) {
        super(point);
        this.color = color;
        this.isBlock = false;
    }

    public RgbPoint3f(BlockPos point, Color3f color) {
        super(point.getX(), point.getY(), point.getZ());
        this.blockPos = point;
        this.isBlock = true;
        this.color = color;
    }

    public RgbPoint3f(BlockPos point) {
        super(point.getX(), point.getY(), point.getZ());
        this.blockPos = point;
        this.isBlock = true;
        this.color = new Color3f();
    }

    public RgbPoint3f() {
        super();
        this.isBlock = false;
        this.color = new Color3f();
    }

    public RgbPoint3f(float x, float y, float z) {
        super(x, y, z);
        this.isBlock = false;
        this.color = new Color3f();
    }

    public int intX() {
        return (int) x;
    }

    public int intY() {
        return (int) y;
    }

    public int intZ() {
        return (int) z;
    }

    public void setColor(float red, float green, float blue) {
        this.color = new Color3f(red, green, blue);
    }

    public static class CompareToPlayer implements Comparator<Point3f> {
        public static CompareToPlayer instance = new CompareToPlayer();
        public static boolean firstCloser(Point3f arg1, Point3f arg2) {
            int result = CompareToPlayer.instance.compare(arg1, arg2);
            if (result > 0) {
                return true;
            }
            return false;
        }
        public int compare(Point3f arg1, Point3f arg2) {
            Point3f playerPos = new Point3f(
                    (float) Minecraft.getMinecraft().thePlayer.posX,
                    (float) Minecraft.getMinecraft().thePlayer.posY,
                    (float) Minecraft.getMinecraft().thePlayer.posZ
            );
            if (playerPos.distance(arg1) > playerPos.distance(arg2)) {
                return 1;
            } else {
                return -1;
            }
        }
    }
}
