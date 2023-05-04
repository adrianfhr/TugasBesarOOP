package main;


import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, enterPressed, iPressed, spacePressed;
    public int limit = 1;
    private boolean zPressed;
     
    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (gamePanel.getGameState() == gamePanel.titleState) {
            checkTitleStateKeyPressed(code);

        } else if (gamePanel.gameState == gamePanel.playState) {
            checkPlayStateKeyPressed(code);

        } else if (gamePanel.getGameState() == gamePanel.pauseState) {
            checkPauseStateKeyPressed(code);

        } else if (gamePanel.getGameState() == gamePanel.dialogueState) {
            checkDialogueStateKeyPressed(code);

        } else if (gamePanel.getGameState() == gamePanel.characterState || gamePanel.getGameState() == gamePanel.makanState) {
            checkCharacterStateKeyPressed(code);

        } else if (gamePanel.getGameState() == gamePanel.optionState) {
            checkOptionStateKeyPressed(code);

        } else if (gamePanel.getGameState() == gamePanel.interactObjState){
            if(code == KeyEvent.VK_E ){
                ePressed = false;
                gamePanel.isActiveAction = false;
            }
        } else if (gamePanel.getGameState() == gamePanel.masakState){
            checkMasakStateKeys(code);
        } else if (gamePanel.getGameState() == gamePanel.gameEventState){
            checkGameEventStateKeys(code);
        } else if(gamePanel.getGameState() == gamePanel.useBarangState){
            checkUseBarangStateKeyPressed(code);
        } else if (gamePanel.getGameState() == gamePanel.wifeState){
            checkWifeStateKeyPressed(code);
        } else if (gamePanel.getGameState() == gamePanel.catState){
            checkCatStateKeyPressed(code);
        }
        // } else if (gamePanel.getGameState() == gamePanel.getGameOverState()) {
        //     checkGameOverStateKeyPressed(code);

        // } else if (gamePanel.getGameState() == gamePanel.getTradeState()) {
        //     checkTradeStateKeyPressed(code);
        // }
    }

    private void checkPlayStateKeyPressed(int code) {
        checkMovementKeys(code);
        checkGameStateKeys(code);
        checkInteractionKeys(code);
        if(code == KeyEvent.VK_M){
            // String x = JOptionPane.showInputDialog("Masukkan X: ");
            // String y = JOptionPane.showInputDialog("Masukkan Y: ");
            // int intX = Integer.parseInt(x);
            // int intY = Integer.parseInt(y); 
            // gamePanel.player[gamePanel.currentPlayer].teleport(intX,intY, 1);
            gamePanel.pindahRuangan();
        }
    }

    private void checkUseBarangStateKeyPressed(int code){
        if (code == KeyEvent.VK_ENTER && limit == 1) {
            enterPressed = true;
            limit = 0;
            gamePanel.ui.addMessage("Barang berhasil diletakkan!");
            gamePanel.playSoundEffect(2);
        } else if(code == KeyEvent.VK_W && limit == 1){
            upPressed = true;
            limit = 0;
            gamePanel.playSoundEffect(8);
        } else if(code == KeyEvent.VK_S && limit == 1){
            downPressed = true;
            limit = 0;
            gamePanel.playSoundEffect(8);
        } else if(code == KeyEvent.VK_A && limit == 1){
            leftPressed = true;
            limit = 0;
            gamePanel.playSoundEffect(8);
        } else if(code == KeyEvent.VK_D && limit == 1){
            rightPressed = true;
            limit = 0;
            gamePanel.playSoundEffect(8);
        }
    }

    private void checkGameStateKeys(int code) {
        if (code == KeyEvent.VK_P) {
            gamePanel.setGameState(gamePanel.pauseState);
        }

        if (code == KeyEvent.VK_I) {
            gamePanel.setGameState(gamePanel.characterState);
        }

        if (code == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.optionState);
        }
        if (code == KeyEvent.VK_E) {
            gamePanel.setGameState(gamePanel.interactObjState);
            gamePanel.isActiveAction = false;
        }
        if (code == KeyEvent.VK_SPACE){
            gamePanel.setGameState(gamePanel.gameEventState);
        }
    }

    private void checkMasakStateKeys(int code){
        masakMovement(code);
        if (code == KeyEvent.VK_E) {
            gamePanel.setGameState(gamePanel.playState);
            gamePanel.isActiveAction = false;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
            gamePanel.player[gamePanel.currentPlayer].selectMenu();
            gamePanel.playSoundEffect(12);
        }
    }

    private void masakMovement(int code){
        if (code == KeyEvent.VK_A){
            if (gamePanel.ui.getKomporSlotCol() != 0) {
                gamePanel.playSoundEffect(8);
                gamePanel.ui.setKomporSlotCol(gamePanel.ui.getKomporSlotCol() - 1);
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gamePanel.ui.getKomporSlotCol() != 4) {
                gamePanel.playSoundEffect(8);
                gamePanel.ui.setKomporSlotCol(gamePanel.ui.getKomporSlotCol() + 1);
            }
        }
    }

    private void checkWifeStateKeyPressed(int code) {
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (gamePanel.ui.getSubState() == 0) {
            if (code == KeyEvent.VK_W) {
                gamePanel.ui.setCommandNumber(gamePanel.ui.getCommandNumber() - 1);
                if (gamePanel.ui.getCommandNumber() < 0) {
                    gamePanel.ui.setCommandNumber(3);
                }
                gamePanel.playSoundEffect(8);
            }

            if (code == KeyEvent.VK_S) {
                gamePanel.ui.setCommandNumber(gamePanel.ui.getCommandNumber() + 1);
                if (gamePanel.ui.getCommandNumber() > 3) {
                    gamePanel.ui.setCommandNumber(0);
                }
                gamePanel.playSoundEffect(8);
            }
        }
    }

    private void checkCatStateKeyPressed(int code) {
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (gamePanel.ui.getSubState() == 0) {
            if (code == KeyEvent.VK_W) {
                gamePanel.ui.setCommandNumber(gamePanel.ui.getCommandNumber() - 1);
                if (gamePanel.ui.getCommandNumber() < 0) {
                    gamePanel.ui.setCommandNumber(2);
                }
                gamePanel.playSoundEffect(8);
            }

            if (code == KeyEvent.VK_S) {
                gamePanel.ui.setCommandNumber(gamePanel.ui.getCommandNumber() + 1);
                if (gamePanel.ui.getCommandNumber() > 2) {
                    gamePanel.ui.setCommandNumber(0);
                }
                gamePanel.playSoundEffect(8);
            }
        }
    }

    private void checkInteractionKeys(int code) {
        if (code == KeyEvent.VK_E && limit == 1) {
            ePressed = true;
            limit = 0;
            gamePanel.setGameState(gamePanel.playState);
        }
    }

    private void checkPauseStateKeyPressed(int code) {
        if (code == KeyEvent.VK_P) {
            gamePanel.setGameState(gamePanel.playState);
        }
    }

    private void checkDialogueStateKeyPressed(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gamePanel.setGameState(gamePanel.playState);
            gamePanel.isNPC = false;
            gamePanel.isCat = false;
        }
    }

    private void checkCharacterStateKeyPressed(int code) {
        if (code == KeyEvent.VK_I) {
            gamePanel.setGameState(gamePanel.playState);
        }

        if (code == KeyEvent.VK_ENTER) {
            if (gamePanel.getGameState() == gamePanel.makanState){
                enterPressed = true;
                gamePanel.isActiveAction = true;
            } else {
                gamePanel.player[gamePanel.currentPlayer].selectBarang();
            }
        }

        playerInventoryMovement(code);
    }

    private void playerInventoryMovement(int code) {
        if (code == KeyEvent.VK_W) {
            if (gamePanel.ui.getPlayerSlotRow() != 0) {
                gamePanel.playSoundEffect(8);
                gamePanel.ui.setPlayerSlotRow(gamePanel.ui.getPlayerSlotRow() - 1);
            }
        }

        if (code == KeyEvent.VK_A) {
            if (gamePanel.ui.getPlayerSlotCol() != 0) {
                gamePanel.playSoundEffect(8);
                gamePanel.ui.setPlayerSlotCol(gamePanel.ui.getPlayerSlotCol() - 1);
            }
        }

        if (code == KeyEvent.VK_S) {
            if (gamePanel.ui.getPlayerSlotRow() != 3) {
                gamePanel.playSoundEffect(8);
                gamePanel.ui.setPlayerSlotRow(gamePanel.ui.getPlayerSlotRow() + 1);
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gamePanel.ui.getPlayerSlotCol() != 4) {
                gamePanel.playSoundEffect(8);
                gamePanel.ui.setPlayerSlotCol(gamePanel.ui.getPlayerSlotCol() + 1);
            }
        }
    }

    private void daganganInventoryMovement(int code) {
        if (code == KeyEvent.VK_W) {
            if (gamePanel.ui.getDaganganSlotRow() != 0) {
                gamePanel.playSoundEffect(8);
                gamePanel.ui.setDaganganSlotRow(gamePanel.ui.getDaganganSlotRow() - 1);
            }
        }

        if (code == KeyEvent.VK_A) {
            if (gamePanel.ui.getDaganganSlotCol() != 0) {
                gamePanel.playSoundEffect(8);
                gamePanel.ui.setDanganganSlotCol(gamePanel.ui.getDaganganSlotCol() - 1);
            }
        }

        if (code == KeyEvent.VK_S) {
            if (gamePanel.ui.getDaganganSlotRow() != 3) {
                gamePanel.playSoundEffect(8);
                gamePanel.ui.setDaganganSlotRow(gamePanel.ui.getDaganganSlotRow() + 1);
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gamePanel.ui.getDaganganSlotCol() != 4) {
                gamePanel.playSoundEffect(8);
                gamePanel.ui.setDanganganSlotCol(gamePanel.ui.getDaganganSlotCol() + 1);
            }
        }
    }

    private void checkTitleStateKeyPressed(int code) {
        if (gamePanel.ui.getTitleScreenState() == 0) {
            checkMainTitleScreenKeyPressed(code);
        }
    }


    private void checkMainTitleScreenKeyPressed(int code) {
        if (code == KeyEvent.VK_W) {
            gamePanel.ui.setCommandNumber(gamePanel.ui.getCommandNumber() - 1);
            gamePanel.playSoundEffect(8);
            if (gamePanel.ui.getCommandNumber() < 0) {
                gamePanel.ui.setCommandNumber(2);
            }
            
        }

        if (code == KeyEvent.VK_S) {
            gamePanel.ui.setCommandNumber(gamePanel.ui.getCommandNumber() + 1);
            gamePanel.playSoundEffect(8);
            if (gamePanel.ui.getCommandNumber() > 2) {
                gamePanel.ui.setCommandNumber(0);
            }

        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
    }

    public void checkMovementKeys(int code){
        if(code == KeyEvent.VK_W){
            upPressed = true;
        }
        if(code == KeyEvent.VK_S){
            downPressed = true;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = true;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W){
            upPressed = false;
            limit = 1;
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
            limit = 1;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
            limit = 1;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
            limit = 1;
        }
        if(code == KeyEvent.VK_E ){
            ePressed = false;
            limit = 1;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = false;
            limit = 1;
        }

        
    }

    private void checkOptionStateKeyPressed(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gamePanel.setGameState(gamePanel.playState);
        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        int maxCommandNumber;

        switch (gamePanel.ui.getSubState()) {
            case 0 -> maxCommandNumber = 5;
            case 3 -> maxCommandNumber = 1;
            default -> maxCommandNumber = 5;
        }

        if (code == KeyEvent.VK_W) {
            gamePanel.ui.setCommandNumber(gamePanel.ui.getCommandNumber() - 1);
            gamePanel.playSoundEffect(8);
            if (gamePanel.ui.getCommandNumber() < 0) {
                gamePanel.ui.setCommandNumber(maxCommandNumber);
            }
        }

        if (code == KeyEvent.VK_S) {
            gamePanel.ui.setCommandNumber(gamePanel.ui.getCommandNumber() + 1);
            gamePanel.playSoundEffect(8);
            if (gamePanel.ui.getCommandNumber() > maxCommandNumber) {
                gamePanel.ui.setCommandNumber(0);
            }
        }

        if (code == KeyEvent.VK_A) {
            if (gamePanel.ui.getSubState() == 0) {
                if (gamePanel.ui.getCommandNumber() == 1 && gamePanel.getMusic().getVolumeScale() > 0) {
                    gamePanel.getMusic().setVolumeScale(gamePanel.getMusic().getVolumeScale() - 1);
                    gamePanel.getMusic().checkVolume();
                    gamePanel.playSoundEffect(8);
                }

                if (gamePanel.ui.getCommandNumber() == 2 && gamePanel.getSoundEffect().getVolumeScale() > 0) {
                    gamePanel.getSoundEffect().setVolumeScale(gamePanel.getSoundEffect().getVolumeScale() - 1);
                    gamePanel.playSoundEffect(8);
                }
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gamePanel.ui.getSubState() == 0) {
                if (gamePanel.ui.getCommandNumber() == 1 && gamePanel.getMusic().getVolumeScale() < 5) {
                    gamePanel.getMusic().setVolumeScale(gamePanel.getMusic().getVolumeScale() + 1);
                    gamePanel.getMusic().checkVolume();
                    gamePanel.playSoundEffect(8);
                }

                if (gamePanel.ui.getCommandNumber() == 2 && gamePanel.getSoundEffect().getVolumeScale() < 5) {
                    gamePanel.getSoundEffect().setVolumeScale(gamePanel.getSoundEffect().getVolumeScale() + 1);
                    gamePanel.playSoundEffect(8);
                }
            }
        }
    }

    private void checkGameEventStateKeys(int code) {
        if (code == KeyEvent.VK_SPACE) {
            gamePanel.setGameState(gamePanel.playState);
            gamePanel.ui.setSubState(0);
        }

        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }

        if (gamePanel.ui.getSubState() == 1) {
            daganganInventoryMovement(code);
            if (code == KeyEvent.VK_ESCAPE) {
                gamePanel.ui.setSubState(0);
            }
            if (code == KeyEvent.VK_ENTER) {
                gamePanel.player[gamePanel.currentPlayer].beliBarang();
                gamePanel.playSoundEffect(12);
            }
        
        if (gamePanel.ui.getSubState() == 2){
            if (code == KeyEvent.VK_ESCAPE) {
                gamePanel.ui.setSubState(0);
            }
            // if (code == KeyEvent.VK_ENTER) {
            //     enterPressed = true;
            //     gamePanel.player[gamePanel.currentPlayer].jualBarang();
            //     gamePanel.playSoundEffect(12);
            // }
        }
        }

        int maxCommandNumber;

        switch (gamePanel.ui.getSubState()) {
            case 0 -> maxCommandNumber = 6;
            case 2 -> maxCommandNumber = 5;
            case 3 -> maxCommandNumber = 3;
            default -> maxCommandNumber = 6;
        }

        if (code == KeyEvent.VK_W) {
            gamePanel.ui.setCommandNumber(gamePanel.ui.getCommandNumber() - 1);
            gamePanel.playSoundEffect(8);
            if (gamePanel.ui.getCommandNumber() < 0) {
                gamePanel.ui.setCommandNumber(maxCommandNumber);
            }
        }

        if (code == KeyEvent.VK_S) {
            gamePanel.ui.setCommandNumber(gamePanel.ui.getCommandNumber() + 1);
            gamePanel.playSoundEffect(8);
            if (gamePanel.ui.getCommandNumber() > maxCommandNumber) {
                gamePanel.ui.setCommandNumber(0);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
       
    }

    public boolean isEnterPressed() {
        return enterPressed;
    }

    public KeyHandler setEnterPressed(boolean enterPressed) {
        this.enterPressed = enterPressed;
        return this;
    }

    public boolean isZPressed() {
        return zPressed;
    }

    public KeyHandler setZPressed(boolean zPressed) {
        this.zPressed = zPressed;
        return this;
    }
}
