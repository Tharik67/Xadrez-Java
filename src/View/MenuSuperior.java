package src.View;

import javax.swing.*;

class MenuSuperior {

    private static JMenuBar mb;
    private static JMenuItem ItemMenuOpen;
    private static JMenuItem ItemMenuSave;


    private static void setMenuSuperior() {
        mb = new JMenuBar();
        JMenu menu = new JMenu("File");
        ItemMenuOpen = new JMenuItem("Open");
        ItemMenuSave = new JMenuItem("Save");
        menu.add(ItemMenuSave);
        menu.add(ItemMenuOpen);
        mb.add(menu);
    }

    static JMenuBar getMenuSuperior(){
        if (mb == null)
            setMenuSuperior();
        return mb;
    }

    static JMenuItem getMenuItemOpen(){
        return ItemMenuOpen;
    }
    
    static JMenuItem getMenuItemSave(){
        return ItemMenuSave;
    }
}