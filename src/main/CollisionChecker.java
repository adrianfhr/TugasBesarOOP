package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity){ //gak bisa lewat tile

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;  

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;

                tileNum1 = gamePanel.tileManager.mapTileNump[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNump[gamePanel.currentMap][entityRightCol][entityTopRow];

                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }

                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;

                tileNum1 = gamePanel.tileManager.mapTileNump[gamePanel.currentMap][entityLeftCol][entityBottomRow];
                tileNum2 = gamePanel.tileManager.mapTileNump[gamePanel.currentMap][entityRightCol][entityBottomRow];

                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                
                tileNum1 = gamePanel.tileManager.mapTileNump[gamePanel.currentMap][entityLeftCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNump[gamePanel.currentMap][entityLeftCol][entityBottomRow];

                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }

                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;

                tileNum1 = gamePanel.tileManager.mapTileNump[gamePanel.currentMap][entityRightCol][entityTopRow];
                tileNum2 = gamePanel.tileManager.mapTileNump[gamePanel.currentMap][entityRightCol][entityBottomRow];

                if(gamePanel.tileManager.tile[tileNum1].collision || gamePanel.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
             
                break;
            default:
            
            
                break;
        }
    }

    public void checkObject(Entity entity, boolean player){ // objek


        for(int i = 0; i < gamePanel.obj.length; i++){
            if(gamePanel.obj[i] != null){

                //get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
                entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;
                //get object solid area position
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].worldX + gamePanel.obj[i].solidAreaDefaultX; 
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].worldY + gamePanel.obj[i].solidAreaDefaultY;
                
                //sd

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)){ //ngecek apakah bersentuhan
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                                entity.isInteracting = true;
                            }
                         ;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)){
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                                entity.isInteracting = true;
                            }
                            
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)){
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                                entity.isInteracting = true;
                            }
                            
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[i].solidArea)){
                            if(gamePanel.obj[i].collision == true){
                                entity.collisionOn = true;
                                entity.isInteracting = true;
                            }
                        }
                        break;
                    default:
                        break;
                }
                
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidAreaDefaultX;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidAreaDefaultY;
            }

        }


    }
    
}
