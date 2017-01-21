/*
 * Author: Falconerd
 * Date: 2017/01/21
 * 
 * Eitr is released under the MIT license.
 *
 * Source @ https://github.com/Falconerd/eitr
 */
package com.falconerd.eitr.util;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextComponentString;

public class Chat {

    public static void sendMessage(String message) {
        Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(new TextComponentString(message), 2340972);
    }

}
