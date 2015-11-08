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

import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerX;
import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerY;
import static net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher.staticPlayerZ;
import static org.lwjgl.opengl.GL11.*;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;

import javax.vecmath.Point3f;

/**
 * Created by liach on 5/17/2015.
 *
 * @author liach
 */
public class FinderUtil {

    public static Point3f posEntity(Entity entity) {
        return new Point3f((float) entity.posX, (float) entity.posY, (float) entity.posZ);
    }

    public static AxisAlignedBB getOffset(double x, double y, double z, double l, double h, double w) {
        return AxisAlignedBB.fromBounds(x, y, z, x + l, y + h, z + w);
    }

    public static void drawEsp(DrawTarget target) {
        float r = target.color.getRed() / 255F;
        float g = target.color.getGreen() / 255F;
        float b = target.color.getBlue() / 255F;
        //todo: use the distance to player to determine alpha
        Minecraft.getMinecraft().entityRenderer.disableLightmap();
        glPushMatrix();
        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
        glLineWidth(1.5F);
        glDisable(GL_LIGHTING);
        glDisable(GL_TEXTURE_2D);
        glEnable(GL_LINE_SMOOTH);
        glDisable(GL_DEPTH_TEST);
        glDepthMask(false);
        //todo: use the distance to player to determine alpha
        glColor4f(r, g, b, 0.1825F);
        drawBoundingBox(target.aabb);
        //todo: use the distance to player to determine alpha
        glColor4f(r, g, b, 1.0F);
        drawOutlinedBoundingBox(target.aabb);
        glLineWidth(2.0F);
        glDisable(GL_LINE_SMOOTH);
        glEnable(GL_TEXTURE_2D);
        glEnable(GL_LIGHTING);
        glEnable(GL_DEPTH_TEST);
        glDepthMask(true);
        glDisable(GL_BLEND);
        glPopMatrix();
        Minecraft.getMinecraft().entityRenderer.enableLightmap();
    }

    public static void drawBoundingBox(final AxisAlignedBB axisalignedbb) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer wr = tessellator.getWorldRenderer();
        wr.startDrawingQuads(); // starts x
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        tessellator.draw();
        wr.startDrawingQuads();
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        tessellator.draw(); // ends x
        wr.startDrawingQuads(); // starts y
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        tessellator.draw();
        wr.startDrawingQuads();
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        tessellator.draw(); // ends y
        wr.startDrawingQuads(); // starts z
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        tessellator.draw();
        wr.startDrawingQuads();
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        wr.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        tessellator.draw(); // ends z
    }

    /**
     * Draws lines for the edges of the bounding box.
     */
    public static void drawOutlinedBoundingBox(final AxisAlignedBB axisalignedbb) {
        Tessellator v2 = Tessellator.getInstance();
        WorldRenderer v3 = v2.getWorldRenderer();
        v3.startDrawing(3);
        v3.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        v2.draw();
        v3.startDrawing(3);
        v3.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        v2.draw();
        v3.startDrawing(1);
        v3.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.minZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.maxX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.minY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        v3.addVertex(axisalignedbb.minX - staticPlayerX, axisalignedbb.maxY
                - staticPlayerY, axisalignedbb.maxZ - staticPlayerZ);
        v2.draw();
    }

    /*public static class CompareToPlayer implements Comparator<Point3f> {
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
    }*/
}
