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


        for(int i = 0; i < gamePanel.obj[gamePanel.currentMap].length; i++){
            if(gamePanel.obj[gamePanel.currentMap][i] != null){

                //get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
                entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;
                //get object solid area position
                gamePanel.obj[gamePanel.currentMap][i].solidArea.x = gamePanel.obj[gamePanel.currentMap][i].worldX + gamePanel.obj[gamePanel.currentMap][i].solidAreaDefaultX; 
                gamePanel.obj[gamePanel.currentMap][i].solidArea.y = gamePanel.obj[gamePanel.currentMap][i].worldY + gamePanel.obj[gamePanel.currentMap][i].solidAreaDefaultY;
                
                //sd

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[gamePanel.currentMap][i].solidArea)){ //ngecek apakah bersentuhan
                            if(gamePanel.obj[gamePanel.currentMap][i].collision == true){
                                entity.collisionOn = true;
                                
                            }
                            if (player) {
                                entity.targetIndex = i;
                                entity.isInteracting = true;
                            } 
                         ;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[gamePanel.currentMap][i].solidArea)){
                            if(gamePanel.obj[gamePanel.currentMap][i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player) {
                                entity.targetIndex = i;
                                entity.isInteracting = true;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[gamePanel.currentMap][i].solidArea)){
                            if(gamePanel.obj[gamePanel.currentMap][i].collision == true){
                                entity.collisionOn = true;
                                
                            }
                            if (player) {
                                entity.targetIndex = i;
                                entity.isInteracting = true;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gamePanel.obj[gamePanel.currentMap][i].solidArea)){
                            if(gamePanel.obj[gamePanel.currentMap][i].collision == true){
                                entity.collisionOn = true;
                            }
                            if (player) {
                                entity.targetIndex = i;
                                entity.isInteracting = true;
                            }
                        }
                        break;
                    default:
                        break;
                }
                
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.obj[gamePanel.currentMap][i].solidArea.x = gamePanel.obj[gamePanel.currentMap][i].solidAreaDefaultX;
                gamePanel.obj[gamePanel.currentMap][i].solidArea.y = gamePanel.obj[gamePanel.currentMap][i].solidAreaDefaultY;
            }

        }

    }


    public int checkEntity(Entity entity, Entity[] target) {

        for(int i = 0; i < target.length; i++){
            if(target[i] != null){

                //get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
                entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;
                //get object solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidAreaDefaultX; 
                target[i].solidArea.y = target[i].worldY + target[i].solidAreaDefaultY;
                
                //sd

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){ //ngecek apakah bersentuhan
                            entity.collisionOn = true;
                                
                            entity.targetIndex = i;
                            entity.isInteracting = true;
                            gamePanel.isNPC = true;
                         ;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                             
                            entity.collisionOn = true;
                            
                            entity.targetIndex = i;
                            entity.isInteracting = true;
                            gamePanel.isNPC = true;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                             
                            entity.collisionOn = true;
                                
                            
                            entity.targetIndex = i;
                            entity.isInteracting = true;
                            gamePanel.isNPC = true;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                             
                            entity.collisionOn = true;
                            
                            entity.targetIndex = i;
                            entity.isInteracting = true;
                            gamePanel.isNPC = true;
                        }
                        break;
                    default:
                        break;
                }
                
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }

        }
        return 0;
    }

    public int checkKucing(Entity entity, Entity[] target) {

        for(int i = 0; i < target.length; i++){
            if(target[i] != null){

                //get entity solid area position
                entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
                entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;
                //get object solid area position
                target[i].solidArea.x = target[i].worldX + target[i].solidAreaDefaultX; 
                target[i].solidArea.y = target[i].worldY + target[i].solidAreaDefaultY;
                
                //sd

                switch(entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){ //ngecek apakah bersentuhan
                             
                            entity.collisionOn = true;
                                
                            entity.targetIndex = i;
                            entity.isInteracting = true;
                            gamePanel.isCat = true;
                         ;
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                             
                            entity.collisionOn = true;
                            
                            entity.targetIndex = i;
                            entity.isInteracting = true;
                            gamePanel.isCat = true;
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                             
                            entity.collisionOn = true;
                                
                            
                            entity.targetIndex = i;
                            entity.isInteracting = true;
                            gamePanel.isCat = true;
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(target[i].solidArea)){
                             
                            entity.collisionOn = true;
                            
                            entity.targetIndex = i;
                            entity.isInteracting = true;
                            gamePanel.isCat = true;
                        }
                        break;
                    default:
                        break;
                }
                
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }

        }
        return 0;
    }
    
    public void checkPlayer(Entity entity) {
         //get entity solid area position
        entity.solidArea.x = entity.worldX + entity.solidAreaDefaultX;
        entity.solidArea.y = entity.worldY + entity.solidAreaDefaultY;
        //get object solid area position
        gamePanel.player[gamePanel.currentPlayer].solidArea.x = gamePanel.player[gamePanel.currentPlayer].worldX + gamePanel.player[gamePanel.currentPlayer].solidAreaDefaultX; 
        gamePanel.player[gamePanel.currentPlayer].solidArea.y = gamePanel.player[gamePanel.currentPlayer].worldY + gamePanel.player[gamePanel.currentPlayer].solidAreaDefaultY;
                    
        //sd

        switch(entity.direction){
            case "up":
                entity.solidArea.y -= entity.speed;
                if(entity.solidArea.intersects(gamePanel.player[gamePanel.currentPlayer].solidArea)){ //ngecek apakah bersentuhan
                    entity.collisionOn = true;
                                            
                    entity.isInteracting = true;
                    ;
                }
                break;
            case "down":
                entity.solidArea.y += entity.speed;
                if(entity.solidArea.intersects(gamePanel.player[gamePanel.currentPlayer].solidArea)){
                    entity.collisionOn = true;

                    entity.isInteracting = true;
                }
                break;
            case "left":
                entity.solidArea.x -= entity.speed;
                if(entity.solidArea.intersects(gamePanel.player[gamePanel.currentPlayer].solidArea)){
                    entity.collisionOn = true;

                    entity.isInteracting = true;
                }
                break;
                case "right":
                    entity.solidArea.x += entity.speed;
                    if(entity.solidArea.intersects(gamePanel.player[gamePanel.currentPlayer].solidArea)){
                        entity.collisionOn = true;
                                
                        entity.isInteracting = true;
                    }
                    break;
                default:
                    break;
                }
                    
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gamePanel.player[gamePanel.currentPlayer].solidArea.x = gamePanel.player[gamePanel.currentPlayer].solidAreaDefaultX;
                gamePanel.player[gamePanel.currentPlayer].solidArea.y = gamePanel.player[gamePanel.currentPlayer].solidAreaDefaultY;
    }
        
    
}
