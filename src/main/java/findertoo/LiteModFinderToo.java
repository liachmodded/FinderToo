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

import com.mumfrey.liteloader.LiteMod;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by liach on 11/8/2015.
 *
 * @author liach
 */
public class LiteModFinderToo implements LiteMod {

  public static final String NAME = "FinderToo";
  public static final String VERSION = "0.1";

  @Override public String getName() {
    return LiteModFinderToo.NAME;
  }

  @Override public String getVersion() {
    return LiteModFinderToo.VERSION;
  }

  @Override public void init(File configPath) {
    Path path = configPath.toPath();
  }

  @Override public void upgradeSettings(String var1, File var2, File var3) {

  }
}
