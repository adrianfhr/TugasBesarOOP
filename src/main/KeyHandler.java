package main;


import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, ePressed, enterPressed, iPressed;
    public int limit = 1;
     
    public KeyHandler(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }
    @Override
    // public void keyPressed(KeyEvent e) {
    //     // TODO Auto-generated method stub
    //     int code = e.getKeyCode();
    //     if(code == KeyEvent.VK_W){
    //         upPressed = true;
    //     }
    //     if(code == KeyEvent.VK_S){
    //         downPressed = true;
    //     }
    //     if(code == KeyEvent.VK_A){
    //         leftPressed = true;
    //     }
    //     if(code == KeyEvent.VK_D){
    //         rightPressed = true;
    //     }
    //     if(code == KeyEvent.VK_E){
    //         ePressed = true;
    //     }

    // }

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

        } else if (gamePanel.getGameState() == gamePanel.characterState) {
            checkCharacterStateKeyPressed(code);

        } else if (gamePanel.getGameState() == gamePanel.optionState) {
            checkOptionStateKeyPressed(code);

        } else if (gamePanel.getGameState() == gamePanel.interactObjState){
            if(code == KeyEvent.VK_E ){
                ePressed = false;
                gamePanel.isActiveAction = false;
            }
        } else if (gamePanel.getGameState() == gamePanel.interactObjState){
            if(code == KeyEvent.VK_E ){
                ePressed = false;
                gamePanel.isActiveAction = false;
            }
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
        }
    }

    private void checkCharacterStateKeyPressed(int code) {
        if (code == KeyEvent.VK_I) {
            gamePanel.setGameState(gamePanel.playState);
        }

        if (code == KeyEvent.VK_ENTER) {
            gamePanel.player.selectItem();
        }

        playerInventoryMovement(code);
    }

    private void playerInventoryMovement(int code) {
        if (code == KeyEvent.VK_W) {
            if (gamePanel.ui.getPlayerSlotRow() != 0) {
                // gamePanel.playSoundEffect(9);
                gamePanel.ui.setPlayerSlotRow(gamePanel.ui.getPlayerSlotRow() - 1);
            }
        }

        if (code == KeyEvent.VK_A) {
            if (gamePanel.ui.getPlayerSlotCol() != 0) {
                // gamePanel.playSoundEffect(9);
                gamePanel.ui.setPlayerSlotCol(gamePanel.ui.getPlayerSlotCol() - 1);
            }
        }

        if (code == KeyEvent.VK_S) {
            if (gamePanel.ui.getPlayerSlotRow() != 3) {
                // gamePanel.playSoundEffect(9);
                gamePanel.ui.setPlayerSlotRow(gamePanel.ui.getPlayerSlotRow() + 1);
            }
        }

        if (code == KeyEvent.VK_D) {
            if (gamePanel.ui.getPlayerSlotCol() != 4) {
                // gamePanel.playSoundEffect(9);
                gamePanel.ui.setPlayerSlotCol(gamePanel.ui.getPlayerSlotCol() + 1);
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
        }
        if(code == KeyEvent.VK_S){
            downPressed = false;
        }
        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if(code == KeyEvent.VK_E ){
            ePressed = false;
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
}
