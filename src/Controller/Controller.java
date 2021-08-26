package src.Controller;

import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.awt.event.MouseEvent;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import src.View.View;
import src.Model.Model;

public class Controller implements MouseListener, ActionListener {

    private static JMenuItem ItemMenuOpen;
    private static JMenuItem ItemMenuSave;

    
    private static JButton botaoComecaNovoJogo;
    private static JButton botaoContinuaJogoSalvo;

    private static JPopupMenu popupMenuPromocaoPeao;
    private static JMenuItem itemPeao;
    private static JMenuItem itemCavalo;
    private static JMenuItem itemTorre;
    private static JMenuItem itemBispo;
    private static JMenuItem itemRainha;

    public static void iniciaJogo() {
    	
        Controller controller = new Controller();

        Model.setBoard();

        View.criaTelaInicial();
        View.criaTelaJogo();

        botaoComecaNovoJogo = View.getBotaoComecaNovoJogo();
        botaoComecaNovoJogo.addActionListener(controller);
        botaoContinuaJogoSalvo = View.getBotaoContinuaJogoSalvo();
        botaoContinuaJogoSalvo.addActionListener(controller);
        
        View.getPainelTabuleiro().addMouseListener(controller);
        Model.addObserverToObservableBoard(View.getPainelTabuleiro());

        ItemMenuOpen = View.getMenuItemOpen();
        ItemMenuSave = View.getMenuItemSave();
        ItemMenuSave.addActionListener(controller);
        ItemMenuOpen.addActionListener(controller);

        popupMenuPromocaoPeao = new JPopupMenu("Escolha Sua Peça");
        itemPeao = new JMenuItem("Peao");
        itemCavalo = new JMenuItem("Cavalo");
        itemTorre = new JMenuItem("Torre");
        itemBispo = new JMenuItem("Bispo");
        itemRainha = new JMenuItem("Rainha");
        popupMenuPromocaoPeao.add(itemPeao);
        popupMenuPromocaoPeao.add(itemCavalo);
        popupMenuPromocaoPeao.add(itemTorre);
        popupMenuPromocaoPeao.add(itemBispo);
        popupMenuPromocaoPeao.add(itemRainha);
        itemPeao.addActionListener(controller);
        itemCavalo.addActionListener(controller);
        itemTorre.addActionListener(controller);
        itemBispo.addActionListener(controller);
        itemRainha.addActionListener(controller);
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

        int coluna = (e.getLocationOnScreen().x - View.getTelaJogo().getX()) / 60;
        int linha = (530 - (e.getLocationOnScreen().y - View.getTelaJogo().getY())) / 60;

        if (0 <= coluna && coluna <= 7 && 0 <= linha && linha <= 7)
            Model.processaClique(linha, coluna);

    }

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();

        if (source == ItemMenuOpen) {
            JFileChooser fc = new JFileChooser();

        	FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file (*.txt)", "txt");
       		fc.addChoosableFileFilter(filter);
       		fc.setFileFilter(filter);
       		
        	int returnVal = fc.showOpenDialog(null);
        	if(returnVal == JFileChooser.APPROVE_OPTION) {
        		File file = fc.getSelectedFile();
        		try {
        			FileInputStream in = new FileInputStream(file);
        			Model.openBin(in);
        			in.close();
        		} catch (IOException error) {
        			error.printStackTrace();
        		}
                View.iniciaVisualJogoNovo();
                View.paint();
        	}
        }

        if (source == ItemMenuSave) {
        	
        	JFileChooser fc = new JFileChooser();
    		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file (*.txt)", "txt");
    		fc.addChoosableFileFilter(filter);
    		fc.setFileFilter(filter);
    		if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
    			File file = fc.getSelectedFile();
    			String fileName = file.getAbsolutePath();
    			if(!fileName.endsWith(".txt"))
    				file = new File(fileName + ".txt");
    			try {
    				FileOutputStream save = new FileOutputStream(file);
    				
    				Model.saveBin(save);
    				
    				save.close();
    			} catch (IOException e1) {
    				e1.printStackTrace();
    			}
    		}
        }

        else if (source == botaoComecaNovoJogo) {
            View.iniciaVisualJogoNovo();

        }

        else if (source == botaoContinuaJogoSalvo) {
        	
            JFileChooser fc = new JFileChooser();

        	FileNameExtensionFilter filter = new FileNameExtensionFilter("Text file (*.txt)", "txt");
       		fc.addChoosableFileFilter(filter);
       		fc.setFileFilter(filter);
       		
        	int returnVal = fc.showOpenDialog(null);
        	if(returnVal == JFileChooser.APPROVE_OPTION) {
        		File file = fc.getSelectedFile();
        		try {
        			FileInputStream in = new FileInputStream(file);
        			Model.openBin(in);
        			in.close();
        		} catch (IOException error) {
        			error.printStackTrace();
        		}
        		View.iniciaVisualJogoNovo();
        	}
        	
        }

        else if (source == itemPeao){
        }

        else if (source == itemCavalo){
            Model.promovePeaoParaCavalo();
        }
        else if (source == itemTorre){
            Model.promovePeaoParaTorre();
        }
        else if (source == itemBispo){
            Model.promovePeaoParaBispo();
        }
        else if (source == itemRainha){
            Model.promovePeaoParaRainha();
        }

    }

    public static void mostraMenuPopupPromocaoPeao() {
        popupMenuPromocaoPeao.show(View.getPainelTabuleiro(), 0, 0);
    }
    public static void mostraMensagemXeque() {
        JOptionPane.showMessageDialog(null, "Xeque");
    }
    public static void mostraMensagemXequeMate() {
        JOptionPane.showMessageDialog(null, "Xeque Mate");
    }
    public static void mostraMensagemEmpate() {
        JOptionPane.showMessageDialog(null, "Empate");
    }
}

