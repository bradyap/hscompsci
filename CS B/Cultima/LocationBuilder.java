//builds cities/dungeons/caves
import java.util.ArrayList;
import java.util.HashSet;
import java.awt.Point;
import javax.swing.ImageIcon;
public class LocationBuilder
{
   public static final byte NORTH = 0, EAST = 1, SOUTH = 2, WEST = 3;
   
   //template for a ship: 0 for blank, 1 is water, 2 is black floor, 3 is plank floor, 4 is counter, 5 is red floor, 6 is helm
   public static final byte[][] ship = {
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,0,0,0,2,2,2,2,2,2,2,3,3,2,2,2,2,2,2,2,2,0,0,0},
   {0,0,0,0,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,0,0,0},
   {0,0,0,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,0,0,0},
   {0,0,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,0},
   {0,2,3,3,3,3,3,3,4,4,3,3,3,3,3,3,4,4,6,3,3,2,2,2,0},
   {0,2,3,3,3,3,3,3,4,4,3,3,3,3,3,3,4,4,5,3,3,2,2,2,0},
   {0,0,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,2,0},
   {0,0,0,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,0,0,0},
   {0,0,0,0,2,2,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,2,0,0,0},
   {0,0,0,0,0,2,2,3,3,2,2,2,2,2,2,2,3,3,2,2,2,2,0,0,0},
   {0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0,3,3,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
   {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
   };
   
   //used to see if a spot is a good spot for the player to spawn on a ship
   //post: returns true if the spot at (r,c) in house is a wood plank floor adjacent to the ship's helm
   public static boolean adjacentToShipHelm(byte[][]house, int r, int c)
   {
      Terrain woodFloor = TerrainBuilder.getTerrainWithName("INR_$Wood_Plank_floor");
      Terrain helm = TerrainBuilder.getTerrainWithName("INR_L_3_$Ship_helm");
   
      if(r<0 || c<0 || r>=house.length || c>=house[0].length || house[r][c]!=woodFloor.getValue())
         return false;
      if(r-1>=0 && house[r-1][c]==helm.getValue())
         return true;
      if(c-1>=0 && house[r][c-1]==helm.getValue())
         return true;
      if(r+1<house.length && house[r+1][c]==helm.getValue())
         return true;
      if(c+1<house[0].length && house[r][c+1]==helm.getValue())
         return true;
   
      return false;
   }
   
   //pre:  Utilities.shipAlignedForGrapple(Player p, NPCPlayer npc) is true
   //      p and npc are 1 space away with side of npc presented to p the right way
   //post: returns an object array where the new map is at index 0
   //      and the player starting position is a 2-element int array at index 1
   //      and the number of enemy sailors at index 2
   public static Object[] buildShipBattle(Player p, NPCPlayer npc)
   {      
      Object [] ans = new Object[3];
   
      //clear out npcs and corpses that might be left over from another battle
      CultimaPanel.civilians.get(1).clear();
      CultimaPanel.corpses.get(1).clear();
   
      byte[][]house = new byte[ship.length*2][ship[0].length*2];
      int [] enterance = new int[2];   //row and col of where the player will spawn
      enterance[0] = -1;                //not assigned yet
      enterance[1] = -1;
   
      byte[][] ship2 = new byte[ship.length][ship[0].length];
      Terrain woodFloor = TerrainBuilder.getTerrainWithName("INR_$Wood_Plank_floor");
      Terrain redFloor = TerrainBuilder.getTerrainWithName("INR_$Red_floor");
      Terrain helm = TerrainBuilder.getTerrainWithName("INR_L_3_$Ship_helm");
      Terrain sand = TerrainBuilder.getTerrainWithName("TER_$Sand");
   
      for(int r=0; r<ship.length; r++)
      {
         for(int c=0; c<ship[0].length; c++)
         {//2 is black floor, 3 is plank floor, 4 is counter, 5 is red or blue floor
            if(ship[r][c]==2)
               ship2[r][c]=TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Tile_Wall").getValue();
            else if(ship[r][c]==3)
               ship2[r][c]=woodFloor.getValue();
            else if(ship[r][c]==4)
               ship2[r][c]=TerrainBuilder.getTerrainWithName("INR_I_L_2_$Counter_lighted").getValue();
            else if(ship[r][c]==5)
               ship2[r][c]=redFloor.getValue();
            else if(ship[r][c]==6)
               ship2[r][c]=helm.getValue(); 
         }
      }
      byte[][] topShipLeft,topShipRight,bottomShipLeft,bottomShipRight,leftShipDown,rightShipDown,leftShipUp,rightShipUp;
      topShipLeft = copyArray(ship2);
      topShipRight = mirrorFlip(copyArray(ship2));
      bottomShipLeft = copyArray(ship2);            
      flipUpsideDown(bottomShipLeft);
      bottomShipRight = mirrorFlip(copyArray(bottomShipLeft));
      leftShipDown = copyArray(ship2);
      rotateLeft(leftShipDown);
      rightShipDown = mirrorFlip(copyArray(leftShipDown));
      leftShipUp = copyArray(leftShipDown);
      flipUpsideDown(leftShipUp);
      rightShipUp = copyArray(rightShipDown);
      flipUpsideDown(rightShipUp);
      
      boolean sailing = p.isSailing();
      
      boolean pLeftOfNpc = false;
      boolean pRightOfNpc = false;   
      boolean pAboveNpc = false;
      boolean pBelowNpc = false;
      if(p.getCol() < npc.getCol() && p.getRow()==npc.getRow())
         pLeftOfNpc = true;
      else if(p.getCol() > npc.getCol() && p.getRow()==npc.getRow())
         pRightOfNpc = true;
      else if(p.getRow() < npc.getRow() && p.getCol()==npc.getCol())
         pAboveNpc = true;
      else //if(p.getRow() > npc.getRow() && p.getCol()==npc.getCol())
         pBelowNpc = true;
         
      boolean pFacingUp = false;
      boolean pFacingRight = false;
      boolean pFacingDown = false;
      boolean pFacingLeft = false;
      byte pDir = p.getLastDir();
      if(pDir==LocationBuilder.NORTH)
         pFacingUp = true;
      else if(pDir==LocationBuilder.EAST)
         pFacingRight = true;
      else if(pDir==LocationBuilder.SOUTH)
         pFacingDown = true;
      else //if(pDir==LocationBuilder.WEST)
         pFacingLeft = true;
         
      boolean npcFacingUp = false;
      boolean npcFacingRight = false;
      boolean npcFacingDown = false;
      boolean npcFacingLeft = false;
      String pic = npc.getCurrentPicture().toString().toLowerCase();
      if(pic.contains("shipup"))
         npcFacingUp = true;
      else if(pic.contains("shipright"))
         npcFacingRight = true;
      else if(pic.contains("shipdown"))
         npcFacingDown = true;
      else //if(pic.contains("shipleft"))
         npcFacingLeft = true;
    
      //fail-safe: force ships to be aligned if they are not
      if(pFacingUp || pFacingDown)
      {
         if(!npcFacingUp && !npcFacingDown)
         {
            npcFacingUp = false;
            npcFacingRight = false;
            npcFacingDown = false;
            npcFacingLeft = false;
            if(Math.random() < 0.5)
               npcFacingUp = true;
            else
               npcFacingDown = true;
         }
         if(!pLeftOfNpc && !pRightOfNpc)
         {   
            pLeftOfNpc = false;
            pRightOfNpc = false;   
            pAboveNpc = false;
            pBelowNpc = false;
            if(Math.random() < 0.5)
               pLeftOfNpc = true;
            else
               pRightOfNpc = true;
         }
      }
      if(pFacingLeft || pFacingRight)
      {
         if(!npcFacingLeft && !npcFacingRight)
         {
            npcFacingUp = false;
            npcFacingRight = false;
            npcFacingDown = false;
            npcFacingLeft = false;
            if(Math.random() < 0.5)
               npcFacingLeft = true;
            else
               npcFacingRight = true;
         }
         if(!pAboveNpc && !pBelowNpc)
         {   
            pLeftOfNpc = false;
            pRightOfNpc = false;   
            pAboveNpc = false;
            pBelowNpc = false;
            if(Math.random() < 0.5)
               pAboveNpc = true;
            else
               pBelowNpc = true;
         }
      }
      int npcMaxHealth = (npc.getMight()*5);   
      int npcDamage = npcMaxHealth - npc.getBody();
      int numCorpses = 0;
      if(npcDamage > npcMaxHealth/2)
         numCorpses = 3;
      else if(npcDamage > npcMaxHealth/3)
         numCorpses = 2;
      else if(npcDamage > npcMaxHealth/4)
         numCorpses = 1;
      int numCrew = Math.max(1, Math.min(8,npc.getBody()/100));
      if(npc.hasMet())
      {
         numCorpses = 0;
         numCrew = 0;
      }
      
      byte[][]currMap = (CultimaPanel.map.get(p.getMapIndex()));
      Terrain pTerr = CultimaPanel.allTerrain.get(Math.abs(currMap[p.getRow()][p.getCol()]));
      Terrain npcTerr = CultimaPanel.allTerrain.get(Math.abs(currMap[npc.getRow()][npc.getCol()]));
   
      ArrayList <Terrain> terrain = new ArrayList();
      ArrayList <Point> npcSpawns = new ArrayList();  //spots for enemy sailors to spawn on
                 
      ArrayList<Terrain> seedTypes = new ArrayList<Terrain>();
      seedTypes.add(pTerr);
      seedTypes.add(npcTerr);
      int shipUpperLeft = (house.length/2 - ship.length/2);
      if(sailing)
      {
         TerrainBuilder.seed(house, -1, seedTypes, ((house.length) / (CultimaPanel.SCREEN_SIZE)) * 2);         
         terrain = new ArrayList();
         for(Terrain t: CultimaPanel.allTerrain)
            if(t.getName().toLowerCase().contains("water"))
               terrain.add(t);
         TerrainBuilder.build(house, terrain, false);
         if(pLeftOfNpc)
         {
            if(pFacingUp && npcFacingDown)
            {
               for(int r=0; r<leftShipUp.length && r<house.length; r++)
                  for(int c=0; c<leftShipUp[0].length && c<house[0].length; c++)
                     if(leftShipUp[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = leftShipUp[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
               for(int r=0; r<rightShipDown.length && r<house.length; r++)
                  for(int c=0; c<rightShipDown[0].length && c<house[0].length; c++)
                     if(rightShipDown[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = rightShipDown[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            }
            else if(pFacingUp && npcFacingUp)
            {
               for(int r=0; r<leftShipUp.length && r<house.length; r++)
                  for(int c=0; c<leftShipUp[0].length && c<house[0].length; c++)
                     if(leftShipUp[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = leftShipUp[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
               for(int r=0; r<rightShipUp.length && r<house.length; r++)
                  for(int c=0; c<rightShipUp[0].length && c<house[0].length; c++)
                     if(rightShipUp[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = rightShipUp[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            }
            else if(pFacingDown && npcFacingUp)
            {
               for(int r=0; r<leftShipDown.length && r<house.length; r++)
                  for(int c=0; c<leftShipDown[0].length && c<house[0].length; c++)
                     if(leftShipDown[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = leftShipDown[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
               for(int r=0; r<rightShipUp.length && r<house.length; r++)
                  for(int c=0; c<rightShipUp[0].length && c<house[0].length; c++)
                     if(rightShipUp[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = rightShipUp[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            }
            else if(pFacingDown && npcFacingDown)
            {
               for(int r=0; r<leftShipDown.length && r<house.length; r++)
                  for(int c=0; c<leftShipDown[0].length && c<house[0].length; c++)
                     if(leftShipDown[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = leftShipDown[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
               for(int r=0; r<rightShipDown.length && r<house.length; r++)
                  for(int c=0; c<rightShipDown[0].length && c<house[0].length; c++)
                     if(rightShipDown[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = rightShipDown[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            }
         }
         else if(pRightOfNpc)
         {
            if(pFacingDown && npcFacingUp)
            {
               for(int r=0; r<leftShipUp.length && r<house.length; r++)
                  for(int c=0; c<leftShipUp[0].length && c<house[0].length; c++)
                     if(leftShipUp[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = leftShipUp[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
               for(int r=0; r<rightShipDown.length && r<house.length; r++)
                  for(int c=0; c<rightShipDown[0].length && c<house[0].length; c++)
                     if(rightShipDown[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = rightShipDown[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
            }
            else if(pFacingUp && npcFacingUp)
            {
               for(int r=0; r<leftShipUp.length && r<house.length; r++)
                  for(int c=0; c<leftShipUp[0].length && c<house[0].length; c++)
                     if(leftShipUp[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = leftShipUp[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
               for(int r=0; r<rightShipUp.length && r<house.length; r++)
                  for(int c=0; c<rightShipUp[0].length && c<house[0].length; c++)
                     if(rightShipUp[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = rightShipUp[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
            }
            else if(pFacingUp && npcFacingDown)
            {
               for(int r=0; r<leftShipDown.length && r<house.length; r++)
                  for(int c=0; c<leftShipDown[0].length && c<house[0].length; c++)
                     if(leftShipDown[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = leftShipDown[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
               for(int r=0; r<rightShipUp.length && r<house.length; r++)
                  for(int c=0; c<rightShipUp[0].length && c<house[0].length; c++)
                     if(rightShipUp[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = rightShipUp[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
            }
            else if(pFacingDown && npcFacingDown)
            {
               for(int r=0; r<leftShipDown.length && r<house.length; r++)
                  for(int c=0; c<leftShipDown[0].length && c<house[0].length; c++)
                     if(leftShipDown[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = leftShipDown[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
               for(int r=0; r<rightShipDown.length && r<house.length; r++)
                  for(int c=0; c<rightShipDown[0].length && c<house[0].length; c++)
                     if(rightShipDown[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = rightShipDown[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
            }
         }
         else if(pAboveNpc)
         {
            if(pFacingLeft && npcFacingLeft)
            {
               for(int r=0; r<topShipLeft.length && r<house.length; r++)
                  for(int c=0; c<topShipLeft[0].length && c<house[0].length; c++)
                     if(topShipLeft[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = topShipLeft[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
               for(int r=0; r<bottomShipLeft.length && r<house.length; r++)
                  for(int c=0; c<bottomShipLeft[0].length && c<house[0].length; c++)
                     if(bottomShipLeft[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = bottomShipLeft[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            }
            else if(pFacingLeft && npcFacingRight)
            {
               for(int r=0; r<topShipLeft.length && r<house.length; r++)
                  for(int c=0; c<topShipLeft[0].length && c<house[0].length; c++)
                     if(topShipLeft[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = topShipLeft[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
               for(int r=0; r<bottomShipRight.length && r<house.length; r++)
                  for(int c=0; c<bottomShipRight[0].length && c<house[0].length; c++)
                     if(bottomShipRight[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = bottomShipRight[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            }
            else if(pFacingRight && npcFacingLeft)
            {
               for(int r=0; r<topShipRight.length && r<house.length; r++)
                  for(int c=0; c<topShipRight[0].length && c<house[0].length; c++)
                     if(topShipRight[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = topShipRight[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
               for(int r=0; r<bottomShipLeft.length && r<house.length; r++)
                  for(int c=0; c<bottomShipLeft[0].length && c<house[0].length; c++)
                     if(bottomShipLeft[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = bottomShipLeft[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            }
            else if(pFacingRight && npcFacingRight)
            {
               for(int r=0; r<topShipRight.length && r<house.length; r++)
                  for(int c=0; c<topShipRight[0].length && c<house[0].length; c++)
                     if(topShipRight[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = topShipRight[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
               for(int r=0; r<bottomShipRight.length && r<house.length; r++)
                  for(int c=0; c<bottomShipRight[0].length && c<house[0].length; c++)
                     if(bottomShipRight[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = bottomShipRight[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            }
         }
         else if(pBelowNpc)
         {
            if(pFacingLeft && npcFacingLeft)
            {
               for(int r=0; r<topShipLeft.length && r<house.length; r++)
                  for(int c=0; c<topShipLeft[0].length && c<house[0].length; c++)
                     if(topShipLeft[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = topShipLeft[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
               for(int r=0; r<bottomShipLeft.length && r<house.length; r++)
                  for(int c=0; c<bottomShipLeft[0].length && c<house[0].length; c++)
                     if(bottomShipLeft[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = bottomShipLeft[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
            }
            else if(pFacingRight && npcFacingLeft)
            {
               for(int r=0; r<topShipLeft.length && r<house.length; r++)
                  for(int c=0; c<topShipLeft[0].length && c<house[0].length; c++)
                     if(topShipLeft[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = topShipLeft[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
               for(int r=0; r<bottomShipRight.length && r<house.length; r++)
                  for(int c=0; c<bottomShipRight[0].length && c<house[0].length; c++)
                     if(bottomShipRight[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = bottomShipRight[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
            }
            else if(pFacingLeft && npcFacingRight)
            {
               for(int r=0; r<topShipRight.length && r<house.length; r++)
                  for(int c=0; c<topShipRight[0].length && c<house[0].length; c++)
                     if(topShipRight[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = topShipRight[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
               for(int r=0; r<bottomShipLeft.length && r<house.length; r++)
                  for(int c=0; c<bottomShipLeft[0].length && c<house[0].length; c++)
                     if(bottomShipLeft[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = bottomShipLeft[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
            }
            else if(pFacingRight && npcFacingRight)
            {
               for(int r=0; r<topShipRight.length && r<house.length; r++)
                  for(int c=0; c<topShipRight[0].length && c<house[0].length; c++)
                     if(topShipRight[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = topShipRight[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
               for(int r=0; r<bottomShipRight.length && r<house.length; r++)
                  for(int c=0; c<bottomShipRight[0].length && c<house[0].length; c++)
                     if(bottomShipRight[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = bottomShipRight[r][c];
                        if(adjacentToShipHelm(house, r+shipUpperLeft, c+shipUpperLeft) && enterance[0]==-1)  //player spawn not found yet
                        {
                           enterance[0] = r+shipUpperLeft;
                           enterance[1] = c+shipUpperLeft;
                        }
                     }
            }
         }
      }
      else                 //player is on land
      {//split to the two halves (water and land), seed them individually and recombine into house array
         //see if we have any controled monsters in the world that can join us
         ArrayList<NPCPlayer>army = new ArrayList<NPCPlayer>();
         for(int i=CultimaPanel.worldMonsters.size()-1; i>=0; i--)
         {
            NPCPlayer minion = CultimaPanel.worldMonsters.get(i);      
            if(minion.hasEffect("control") && !minion.canSwim())
            {
               //make sure they are currently on our screen
               double dist = Display.wrapDistance(minion.getRow(), minion.getCol(), CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
               if(dist <= CultimaPanel.SCREEN_SIZE / 2 + 1)
               {
                  army.add(minion);
                  CultimaPanel.worldMonsters.remove(i);
               }
            }
         }
      
         byte [][] land, water;
         
         if(pLeftOfNpc || pRightOfNpc)    //player is to the left or right of npc
         {
            land = new byte[house.length][house[0].length/2];
            water = new byte[house.length][house[0].length/2];
         }
         else
         {
            water = new byte[house.length/2][house[0].length];
            land = new byte[house.length/2][house[0].length];
         }
         seedTypes = new ArrayList<Terrain>();
         seedTypes.add(pTerr);
         terrain = new ArrayList();
         for(Terrain t: CultimaPanel.allTerrain)
            if(t.getName().startsWith("TER") && !t.getName().contains("_I_"))
               terrain.add(t);
         TerrainBuilder.seed(land, -1, seedTypes, ((house.length) / (CultimaPanel.SCREEN_SIZE)));
         if(pLeftOfNpc)  
         {
            for(int r=0; r<house.length; r++)
            {
               land[r][land[0].length-1] = sand.getValue();
               if(Math.random() < 0.5)
                  land[r][land[0].length-1] = sand.getValue();
            }
         }
         else if(pRightOfNpc)  
         {
            for(int r=0; r<house.length; r++)
            {
               land[r][0] = sand.getValue();
               if(Math.random() < 0.5)
                  land[r][1] = sand.getValue();
            }
         }
         else if(pAboveNpc)
         {
            for(int c=0; c<house[0].length; c++)
            {
               land[land.length-1][c] = sand.getValue();
               if(Math.random() < 0.5)
                  land[land.length-2][c] = sand.getValue();
            }
         }
         else if(pBelowNpc)
         {
            for(int c=0; c<house[0].length; c++)
            {
               land[0][c] = sand.getValue();
               if(Math.random() < 0.5)
                  land[1][c] = sand.getValue();
            }
         }
         TerrainBuilder.build(land, terrain, false);
         seedTypes = new ArrayList<Terrain>();
         seedTypes.add(npcTerr);
         terrain = new ArrayList();
         for(Terrain t: CultimaPanel.allTerrain)
            if(t.getName().toLowerCase().contains("water"))
               terrain.add(t);
         TerrainBuilder.seed(water, -1, seedTypes, ((house.length) / (CultimaPanel.SCREEN_SIZE)));
         TerrainBuilder.build(water, terrain, false);
         if(pLeftOfNpc)
         {
            enterance[0] = house.length/2;
            enterance[1] = 0;
            //if we have controlled NPCs to help us fight, set their starting position
            for(int i=0; i<army.size() && (house.length/2 + 1 + i) < house.length; i++)
            {
               army.get(i).setMapIndex(1);
               army.get(i).setRow(house.length/2 + 1 + i);
               army.get(i).setCol(0);
               CultimaPanel.worldMonsters.add(army.get(i));
            }
            for(int r=0; r<land.length && r<house.length; r++)
               for(int c=0; c<land[0].length && c<house[0].length; c++)
                  house[r][c] = land[r][c];
            for(int r=0; r<water.length && r<house.length; r++)
               for(int c=0; c<water[0].length && c+(house[0].length/2)<house[0].length; c++)
                  house[r][c+(house[0].length/2)] = water[r][c];
            if(npcFacingDown)
            {
               for(int r=0; r<rightShipDown.length && r<house.length; r++)
                  for(int c=0; c<rightShipDown[0].length && c<house[0].length; c++)
                     if(rightShipDown[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = rightShipDown[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            } 
            else//if(npcFacingUp)
            {
               for(int r=0; r<rightShipUp.length && r<house.length; r++)
                  for(int c=0; c<rightShipUp[0].length && c<house[0].length; c++)
                     if(rightShipUp[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = rightShipUp[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            }            
         }
         else if(pRightOfNpc)
         {
            enterance[0] = house.length/2;
            enterance[1] = house[0].length-1;
            //if we have controlled NPCs to help us fight, set their starting position
            for(int i=0; i<army.size() && (house.length/2 + 1 + i) < house.length; i++)
            {
               army.get(i).setMapIndex(1);
               army.get(i).setRow(house.length/2 + 1 + i);
               army.get(i).setCol(house[0].length-1);
               CultimaPanel.worldMonsters.add(army.get(i));
            }
            for(int r=0; r<water.length && r<house.length; r++)
               for(int c=0; c<water[0].length && c<house[0].length; c++)
                  house[r][c] = water[r][c];
            for(int r=0; r<land.length && r<house.length; r++)
               for(int c=0; c<land[0].length && c+(house[0].length/2)<house[0].length; c++)
                  house[r][c+(house[0].length/2)] = land[r][c];  
            if(npcFacingDown)
            {
               for(int r=0; r<leftShipDown.length && r<house.length; r++)
                  for(int c=0; c<leftShipDown[0].length && c<house[0].length; c++)
                     if(leftShipDown[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = leftShipDown[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            } 
            else//if(npcFacingUp)
            {
               for(int r=0; r<leftShipUp.length && r<house.length; r++)
                  for(int c=0; c<leftShipUp[0].length && c<house[0].length; c++)
                     if(leftShipUp[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = leftShipUp[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            }     
         }
         else if(pAboveNpc)
         {
            enterance[0] = 0;
            enterance[1] = house[0].length/2;
            //if we have controlled NPCs to help us fight, set their starting position
            for(int i=0; i<army.size() && (house[0].length/2 + 1 + i) < house[0].length; i++)
            {
               army.get(i).setMapIndex(1);
               army.get(i).setRow(0);
               army.get(i).setCol(house[0].length/2 + 1 + i);
               CultimaPanel.worldMonsters.add(army.get(i));
            }
            for(int r=0; r<land.length && r<house.length; r++)
               for(int c=0; c<land[0].length && c<house[0].length; c++)
                  house[r][c] = land[r][c];
            for(int r=0; r<water.length && r+(house.length/2)<house.length; r++)
               for(int c=0; c<water[0].length && c<house[0].length; c++)
                  house[r+(house.length/2)][c] = water[r][c];  
            if(npcFacingLeft)
            {
               for(int r=0; r<bottomShipLeft.length && r<house.length; r++)
                  for(int c=0; c<bottomShipLeft[0].length && c<house[0].length; c++)
                     if(bottomShipLeft[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = bottomShipLeft[r][c];  
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }
            }
            else//if(npcFacingRight)
            {
               for(int r=0; r<bottomShipRight.length && r<house.length; r++)
                  for(int c=0; c<bottomShipRight[0].length && c<house[0].length; c++)
                     if(bottomShipRight[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = bottomShipRight[r][c];    
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }     
            }   
         }
         else //if(pBelowNpc)
         {
            enterance[0] = house.length-1;
            enterance[1] = house[0].length/2;
            //if we have controlled NPCs to help us fight, set their starting position
            for(int i=0; i<army.size() && (house[0].length/2 + 1 + i) < house[0].length; i++)
            {
               army.get(i).setMapIndex(1);
               army.get(i).setRow(house.length-1);
               army.get(i).setCol(house[0].length/2 + 1 + i);
               CultimaPanel.worldMonsters.add(army.get(i));
            }
            for(int r=0; r<water.length && r<house.length; r++)
               for(int c=0; c<water[0].length && c<house[0].length; c++)
                  house[r][c] = water[r][c];
            for(int r=0; r<land.length && r+(house.length/2)<house.length; r++)
               for(int c=0; c<land[0].length && c<house[0].length; c++)
                  house[r+(house.length/2)][c] = land[r][c];
            if(npcFacingLeft)
            {
               for(int r=0; r<topShipLeft.length && r<house.length; r++)
                  for(int c=0; c<topShipLeft[0].length && c<house[0].length; c++)
                     if(topShipLeft[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = topShipLeft[r][c]; 
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     } 
            }
            else//if(npcFacingRight)
            {
               for(int r=0; r<topShipRight.length && r<house.length; r++)
                  for(int c=0; c<topShipRight[0].length && c<house[0].length; c++)
                     if(topShipRight[r][c] != 0)
                     {
                        house[r+shipUpperLeft][c+shipUpperLeft] = topShipRight[r][c];
                        if(house[r+shipUpperLeft][c+shipUpperLeft] == woodFloor.getValue())
                           npcSpawns.add(new Point(r+shipUpperLeft,c+shipUpperLeft));
                     }         
            } 
         }
      }
      if(npcSpawns.size() > 0)
      {
         for(int i=0; i<numCorpses; i++)
         {
            Point place = npcSpawns.get((int)(Math.random()*npcSpawns.size()));
            int r = (int)(place.getX());
            int c = (int)(place.getY());
            byte charType = NPC.BRIGAND_SPEAR;
            if(npc.getCharIndex()==NPC.GREATSHIP)
               charType = NPC.randomBattlefieldWarrior();
            else
               charType = NPC.randomBrigand();
            NPCPlayer enemy = new NPCPlayer(charType, r, c, 1, r, c, "ship");
            CultimaPanel.corpses.get(enemy.getMapIndex()).add(enemy.getCorpse());
         }
         for(int i=0; i<numCrew; i++)
         {
            Point place = npcSpawns.get((int)(Math.random()*npcSpawns.size()));
            int r = (int)(place.getX());
            int c = (int)(place.getY());
            byte charType = NPC.BRIGAND_SPEAR;
            if(npc.getCharIndex()==NPC.GREATSHIP)
               charType = NPC.randomBattlefieldWarrior();
            else
               charType = NPC.randomBrigand();
            NPCPlayer enemy = new NPCPlayer(charType, r, c, 1, r, c, "ship");
            enemy.setMoveType(NPC.CHASE);
            CultimaPanel.worldMonsters.add(enemy);
         }
         if(npc.hasEffect("fire"))
         {
            Point place = npcSpawns.get((int)(Math.random()*npcSpawns.size()));
            int r = (int)(place.getX());
            int c = (int)(place.getY());
            CultimaPanel.worldMonsters.add(new NPCPlayer(NPC.FIRE, r, c, 1, "ship"));
         }
      }
      ans[0] = house;
      ans[1] = enterance;
      ans[2] = new Integer(numCrew);
      if(enterance[0]==-1 || enterance[1]==-1)
         return null;
      return ans;
   }
   
//type is the terrain type around the domicile
//lastDir is last player movement direction typed in 0-UP, 1-RIGHT, 2-DOWN, 3-LEFT, for establishing entrance side
//returns an array with 2 objects:   index 0 is the byte[][] of the location we just built
//                                   index 1 is an int[] with the row and col of where we enter the location
   public static Object[] buildBattlefield(Terrain type, String locType, int mapIndex, String provinceName, byte lastDir)
   {
      int size = (int)(Math.random()*10)+(CultimaPanel.SCREEN_SIZE);
      byte[][]house = new byte[size][size];
      Terrain edgeType = TerrainBuilder.getTerrainWithName("TER_$Grassland");
      ArrayList<Terrain> seedTypes = new ArrayList<Terrain>();
   //see if we have any controled monsters in the world that can join us
      ArrayList<NPCPlayer>army = new ArrayList<NPCPlayer>();
      for(int i=CultimaPanel.worldMonsters.size()-1; i>=0; i--)
      {
         NPCPlayer minion = CultimaPanel.worldMonsters.get(i);      
         if(minion.hasEffect("control") && !minion.canSwim())
         {
               //make sure they are currently on our screen
            double dist = Display.wrapDistance(minion.getRow(), minion.getCol(), CultimaPanel.player.getWorldRow(), CultimaPanel.player.getWorldCol());
            if(dist <= CultimaPanel.SCREEN_SIZE / 2 + 1)
            {
               army.add(minion);
               if(minion.getCharIndex()==NPC.DRAGON || minion.getCharIndex()==NPC.DRAGONKING)
                  Achievement.earnAchievement(Achievement.ZOMBIE_PETE);
               CultimaPanel.worldMonsters.remove(i);
            }
         }
      }
      if(type.getName().contains("Mountain") || type.getName().contains("Hills"))     
      {     //put some hllls on the edges, followed by mixed grass
         edgeType = TerrainBuilder.getTerrainWithName("TER_S_$Hills");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$High_grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
      }
      else if(type.getName().toLowerCase().contains("grassland"))
      {     //put some High_grassland on the edges, then mostly mixed grasses
         edgeType = TerrainBuilder.getTerrainWithName("TER_$High_grassland");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));    
      }
      else if(type.getName().contains("Dense_forest") || type.getName().equals("TER_$Forest"))
      {     //put some forest on the edges, then mostly grass
         edgeType = TerrainBuilder.getTerrainWithName("TER_S_B_$Dense_forest");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Forest"));    
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$High_grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
      }
      else if(type.getName().contains("Sand") || type.getName().contains("Dry_grass"))
      {     //put some TER_$Sand on the edges, then mostly TER_$Dry_grass
         edgeType = TerrainBuilder.getTerrainWithName("TER_$Sand");            
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));     
      }
      else if(type.getName().contains("Bog"))//bog
      {    
         edgeType = TerrainBuilder.getTerrainWithName("TER_S_$Bog");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$High_grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
      }
      else if(type.getName().contains("water"))
      {     
         edgeType = TerrainBuilder.getTerrainWithName("TER_S_$Bog");
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_S_$Bog"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$High_grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));     
      }
      else if(type.getName().contains("Dead_forest"))//dead-forest
      {     
         edgeType = TerrainBuilder.getTerrainWithName("TER_$Dead_forest");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));    
      }
      else
      {
         edgeType = TerrainBuilder.getTerrainWithName("TER_$High_grassland");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));    
      }   
      TerrainBuilder.seed(house, mapIndex, seedTypes, ((house.length) / (CultimaPanel.SCREEN_SIZE)) * 2);
   //put down edgeType around the borders
      for(int r=0; r<house.length; r++)
      {
         for(int c=0; c <=1; c++)
         {
            double prob = 0.5;
            if(c==1)
               prob = 0.25;
            if(Math.random() < prob)
               house[r][c] = edgeType.getValue();
            if(Math.random() < prob)
               house[r][house[0].length-1-c] = edgeType.getValue();
         }
      }
      for(int r=0; r<=1; r++)
      {
         double prob = 0.5;
         if(r==1)
            prob = 0.25;
         for(int c=0; c<house[0].length; c++)
         {
            if(Math.random() < prob)
               house[r][c] = edgeType.getValue();
            if(Math.random() < prob)
               house[house.length-1-r][c] = edgeType.getValue();
         }
      }
      ArrayList <Terrain> terrain = new ArrayList();
      for(Terrain t: CultimaPanel.allTerrain)
         if(t.getName().startsWith("TER"))
            terrain.add(t);
   
      TerrainBuilder.build(house, terrain, false);
      for(int r=0; r<house.length; r++)         //replace any impassable water in the battlefield with shallow water
         for(int c=0; c<house[0].length; c++)
         {
            String curr = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().toLowerCase();
            if(curr.contains("water"))
               house[r][c] = TerrainBuilder.getTerrainWithName("TER_V_$Shallow_water").getValue();
         }
      int enteranceRow = 0, enteranceCol = 0;   //coordinates of where we enter the location
      byte enemyMarchDir = NORTH;               //direction the enemy will be marching
      byte armyMarchDir = lastDir;              //direction friendly army will be marching
      if(lastDir == 2)                          //enter in row 0
      {
         enteranceRow = 1;
         enteranceCol = house[0].length/2;      //direction = SOUTH;
         enemyMarchDir = NORTH;
        //if we have controlled NPCs to help us fight, set their starting position
         for(int i=0; i<army.size() && (house[0].length/2 + 1 + i) < house[0].length; i++)
         {
            army.get(i).setMapIndex(mapIndex);
            army.get(i).setRow(0);
            army.get(i).setCol(house[0].length/2 + 1 + i);
            CultimaPanel.worldMonsters.add(army.get(i));
         }
      }      
      else if(lastDir == 3)                     //enter in last col
      {
         enteranceRow = house.length/2;
         enteranceCol = house[0].length - 2;    //direction = WEST;
         enemyMarchDir = EAST;
          //if we have controlled NPCs to help us fight, set their starting position
         for(int i=0; i<army.size() && (house.length/2 + 1 + i) < house.length; i++)
         {
            army.get(i).setMapIndex(mapIndex);
            army.get(i).setRow(house.length/2 + 1 + i);
            army.get(i).setCol(house[0].length - 1);
            CultimaPanel.worldMonsters.add(army.get(i));
         }
      }
      else if(lastDir == 0)                     //enter in last row
      {
         enteranceRow = house.length - 2;
         enteranceCol = house[0].length/2;      //direction = NORTH;
         enemyMarchDir = SOUTH;
         //if we have controlled NPCs to help us fight, set their starting position
         for(int i=0; i<army.size() && (house[0].length/2 + 1 + i) < house[0].length; i++)
         {
            army.get(i).setMapIndex(mapIndex);
            army.get(i).setRow(house.length - 1);
            army.get(i).setCol(house[0].length/2 + 1 + i);
            CultimaPanel.worldMonsters.add(army.get(i));
         }
      }  
      else                                      //ender in col 0
      {
         enteranceRow = house.length/2;
         enteranceCol = 1;                      //direction = EAST;
         enemyMarchDir = WEST;
         //if we have controlled NPCs to help us fight, set their starting position
         for(int i=0; i<army.size() && (house.length/2 + 1 + i) < house.length; i++)
         {
            army.get(i).setMapIndex(mapIndex);
            army.get(i).setRow(house.length/2 + 1 + i);
            army.get(i).setCol(0);
            CultimaPanel.worldMonsters.add(army.get(i));
         }
      } 
   
      if(Math.random() < 0.5)
      {   //maybe scatter some stone obstacles in the battlefield
         byte [] rockType = {TerrainBuilder.getTerrainWithName("INR_I_B_$Gray_Rock").getValue(), TerrainBuilder.getTerrainWithName("INR_I_B_$White_Rock").getValue(),
                    TerrainBuilder.getTerrainWithName("INR_D_B_$Gray_Rock").getValue(), TerrainBuilder.getTerrainWithName("INR_D_B_$White_Rock").getValue()};
         int numRocks = Player.rollDie(1,house.length);
         for(int i=0; i<numRocks; i++)
         {
            int r = Player.rollDie(2, house.length-3);
            int c = Player.rollDie(2, house[0].length-3);
            house[r][c] = rockType[(int)(Math.random()*rockType.length)];
         }
      }
   
   //add armies marching in respective directions
      int numFriendlies = Player.rollDie(4, house.length/4);
      int numEnemies = Math.max(numFriendlies, Player.rollDie(5, house.length-4));
      boolean mixedMonsters = false;
      if(Math.random() < 0.5)
         mixedMonsters = true;
      byte monsterType = NPC.randomBattlefieldMonsterLimited();  
      if(monsterType == NPC.SKELETON && Math.random() < 0.5)
         numEnemies = house.length-4;
      else if(monsterType == NPC.TROLL && Math.random() < 0.5)
         numEnemies /= 2;  
   
      if(armyMarchDir == NORTH)
      {
         int row = house.length - 2;
         int col = (house[0].length/2) - (numFriendlies/2 + 1);
         boolean hasCaptain = false;
         for(int i=0; i<numFriendlies; i++)
         {
            byte charType = NPC.randomBattlefieldWarrior();
            if(hasCaptain)                //only one captain per human army
               charType = NPC.randomBattlefieldWarriorLimited();
            if(charType == NPC.ROYALGUARD || charType == NPC.BOWASSASSIN || charType == NPC.ENFORCER)
               hasCaptain = true;
            if(row == enteranceRow && col == enteranceCol)
            {
               col++;
               continue;
            }
            NPCPlayer warrior = new NPCPlayer(charType, row, col, mapIndex, row, col, "battlefield");
            warrior.modifyStats(0.8);
            col++;
            warrior.setMoveType(NPC.CHASE);//MARCH_NORTH);
            CultimaPanel.worldMonsters.add(warrior);
         }
         row = 1;
         col = (house[0].length/2) - (numEnemies/2 + 1);
         hasCaptain = false;
         for(int i=0; i<numEnemies; i++)
         {
            byte charType = monsterType;
            if(mixedMonsters)
            {
               charType = NPC.randomBattlefieldMonster();
               if(hasCaptain)                //only one captain per human army
                  charType = NPC.randomBattlefieldMonsterLimited();
               if(charType == NPC.CYCLOPS || charType == NPC.GIANT)
                  hasCaptain = true;
            }
            NPCPlayer monster = new NPCPlayer(charType, row, col, mapIndex, row, col, "battlefield");
            if(NPC.isBrigand(charType))
               monster.modifyStats(1.2);
            col++;
            monster.setMoveType(NPC.CHASE);//MARCH_SOUTH);
            CultimaPanel.worldMonsters.add(monster);
         }
      }
      else if(armyMarchDir == SOUTH)
      {
         int row = 1;
         int col = (house[0].length/2) - (numFriendlies/2 + 1);
         boolean hasCaptain = false;
         for(int i=0; i<numFriendlies; i++)
         {
            byte charType = NPC.randomBattlefieldWarrior();
            if(hasCaptain)                //only one captain per human army
               charType = NPC.randomBattlefieldWarriorLimited();
            if(charType == NPC.ROYALGUARD || charType == NPC.BOWASSASSIN || charType == NPC.ENFORCER)
               hasCaptain = true;
            if(row == enteranceRow && col == enteranceCol)
            {
               col++;
               continue;
            }
            NPCPlayer warrior = new NPCPlayer(charType, row, col, mapIndex, row, col, "battlefield");
            warrior.modifyStats(0.8);
            col++;
            warrior.setMoveType(NPC.CHASE);//MARCH_SOUTH);
            CultimaPanel.worldMonsters.add(warrior);
         }
         row = house.length - 2;
         col = (house[0].length/2) - (numEnemies/2 + 1);
         hasCaptain = false;
         for(int i=0; i<numEnemies; i++)
         {
            byte charType = monsterType;
            if(mixedMonsters)
            {
               charType = NPC.randomBattlefieldMonster();
               if(hasCaptain)                //only one captain per human army
                  charType = NPC.randomBattlefieldMonsterLimited();
               if(charType == NPC.CYCLOPS || charType == NPC.GIANT)
                  hasCaptain = true;
            }
            NPCPlayer monster = new NPCPlayer(charType, row, col, mapIndex, row, col, "battlefield");
            if(NPC.isBrigand(charType))
               monster.modifyStats(1.2);
            col++;
            monster.setMoveType(NPC.CHASE);//MARCH_NORTH);
            CultimaPanel.worldMonsters.add(monster);
         }
      }
      else if(armyMarchDir == EAST)
      {
         int row = (house.length /2) - (numFriendlies/2 + 1);
         int col = 1;
         boolean hasCaptain = false;
         for(int i=0; i<numFriendlies; i++)
         {
            byte charType = NPC.randomBattlefieldWarrior();
            if(hasCaptain)                //only one captain per human army
               charType = NPC.randomBattlefieldWarriorLimited();
            if(charType == NPC.ROYALGUARD || charType == NPC.BOWASSASSIN || charType == NPC.ENFORCER)
               hasCaptain = true;
            if(row == enteranceRow && col == enteranceCol)
            {
               row++;
               continue;
            }
            NPCPlayer warrior = new NPCPlayer(charType, row, col, mapIndex, row, col, "battlefield");
            warrior.modifyStats(0.8);
            row++;
            warrior.setMoveType(NPC.CHASE);//MARCH_EAST);
            CultimaPanel.worldMonsters.add(warrior);
         }
         row = (house.length /2)  - (numEnemies/2 + 1);
         col = house[0].length - 2;
         hasCaptain = false;
         for(int i=0; i<numEnemies; i++)
         {
            byte charType = monsterType;
            if(mixedMonsters)
            {
               charType = NPC.randomBattlefieldMonster();
               if(hasCaptain)                //only one captain per human army
                  charType = NPC.randomBattlefieldMonsterLimited();
               if(charType == NPC.CYCLOPS || charType == NPC.GIANT)
                  hasCaptain = true;
            }
            NPCPlayer monster = new NPCPlayer(charType, row, col, mapIndex, row, col, "battlefield");
            if(NPC.isBrigand(charType))
               monster.modifyStats(1.2);
            row++;
            monster.setMoveType(NPC.CHASE);//MARCH_WEST);
            CultimaPanel.worldMonsters.add(monster);
         }
      }
      else //if(armyMarchDir == WEST)
      {
         int row = (house.length/2) - (numFriendlies/2 + 1);
         int col = house[0].length - 2;
         boolean hasCaptain = false;
         for(int i=0; i<numFriendlies; i++)
         {
            byte charType = NPC.randomBattlefieldWarrior();
            if(hasCaptain)                //only one captain per human army
               charType = NPC.randomBattlefieldWarriorLimited();
            if(charType == NPC.ROYALGUARD || charType == NPC.BOWASSASSIN || charType == NPC.ENFORCER)
               hasCaptain = true;
            if(row == enteranceRow && col == enteranceCol)
            {
               row++;
               continue;
            }
            NPCPlayer warrior = new NPCPlayer(charType, row, col, mapIndex, row, col, "battlefield");
            warrior.modifyStats(0.8);
            row++;
            warrior.setMoveType(NPC.CHASE);//MARCH_WEST);
            CultimaPanel.worldMonsters.add(warrior);
         }
         row = (house.length /2) - (numEnemies/2 + 1);
         col = 1;
         hasCaptain = false;
         for(int i=0; i<numEnemies; i++)
         {
            byte charType = monsterType;
            if(mixedMonsters)
            {
               charType = NPC.randomBattlefieldMonster();
               if(hasCaptain)                //only one captain per human army
                  charType = NPC.randomBattlefieldMonsterLimited();
               if(charType == NPC.CYCLOPS || charType == NPC.GIANT)
                  hasCaptain = true;
            }
            NPCPlayer monster = new NPCPlayer(charType, row, col, mapIndex, row, col, "battlefield");
            if(NPC.isBrigand(charType))
               monster.modifyStats(1.2);
            row++;
            monster.setMoveType(NPC.CHASE);//MARCH_WEST);
            CultimaPanel.worldMonsters.add(monster);
         }
      }
      int [] enterance = new int[2];
      enterance[0] = enteranceRow;
      enterance[1] = enteranceCol;
   
      Object [] retVal = new Object[3];
      retVal[0] = house;
      retVal[1] = enterance; 
      retVal[2] = new Point(monsterType, numEnemies);
      return retVal;
   }

//type is the terrain type around the domicile
//waterDir is the direction (NORTH, SOUTH, etc) that water is on for a port
//lastDir is last player movement direction typed in 0-UP, 1-RIGHT, 2-DOWN, 3-LEFT, for establishing entrance side
//returns an array with 3 objects:   index 0 is the byte[][] of the location we just built
//                                   index 1 is an int[] with the row and col of where we enter the location
//                                   index 2 is the number of occupants in the location     
   public static Object[] buildDomicile(Terrain type, String locType, int mapIndex, String provinceName, byte lastDir, byte waterDir, boolean capital, int locRow, int locCol)
   {
      int size = 51;
      if(capital)
         size = (int)(Math.random()*35)+(CultimaPanel.SCREEN_SIZE*5); 
      else if(locType.contains("domicile"))
         size = (int)(Math.random()*10)+(CultimaPanel.SCREEN_SIZE);
      else if(locType.contains("village") || locType.contains("temple") || locType.contains("tower"))
         size = (int)(Math.random()*20)+(CultimaPanel.SCREEN_SIZE+15);
      else if(locType.contains("Small_castle"))
         size = (int)(Math.random()*20)+(CultimaPanel.SCREEN_SIZE+15);  
      else if(locType.contains("Large_castle"))
         size = (int)(Math.random()*25)+(CultimaPanel.SCREEN_SIZE*2);  
      else if(locType.contains("arena"))
         size = (int)(Math.random()*25)+(CultimaPanel.SCREEN_SIZE*2);
      else if(locType.contains("fortress"))
         size = (int)(Math.random()*30)+(CultimaPanel.SCREEN_SIZE*2);  
      else if(locType.contains("port"))
         size = (int)(Math.random()*33)+(int)(CultimaPanel.SCREEN_SIZE*2.5); 
      else if(locType.contains("city"))
         size = (int)(Math.random()*35)+(CultimaPanel.SCREEN_SIZE*3); 
      if(provinceName.equals("Skara-Brae"))    //our endgame temple
         size = 51; 
      if(size%2==0)     //make odd size dimensions for acurate matrix rotation
         size++;     
      byte[][]house = new byte[size][size];
      Terrain edgeType = TerrainBuilder.getTerrainWithName("TER_$Grassland");
      ArrayList<Terrain> seedTypes = new ArrayList<Terrain>();
   
      if(type.getName().equals("TER_V_B_$Mountain") || type.getName().equals("TER_S_$Hills"))     
      {     //put some hllls on the edges, followed by mixed grass
         if(locType.contains("castle"))
            edgeType = TerrainBuilder.getTerrainWithName("TER_$Grassland"); 
         else
            edgeType = TerrainBuilder.getTerrainWithName("TER_S_$Hills");       
         if(!locType.contains("castle"))
            seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$High_grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
      }
      else if(type.getName().equals("TER_$High_grassland") || type.getName().equals("TER_$Grassland"))
      {     //put some TER_$High_grassland on the edges, then mostly TER_$Grassland
         if(locType.contains("castle"))
            edgeType = TerrainBuilder.getTerrainWithName("TER_$Grassland"); 
         else
            edgeType = TerrainBuilder.getTerrainWithName("TER_$High_grassland");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));    
      }
      else if(type.getName().equals("TER_S_B_$Dense_forest") || type.getName().equals("TER_$Forest"))
      {     //put some forest on the edges, then mostly grass
         if(locType.contains("castle"))
            edgeType = TerrainBuilder.getTerrainWithName("TER_$Grassland"); 
         else
            edgeType = TerrainBuilder.getTerrainWithName("TER_S_B_$Dense_forest");       
         if(!locType.contains("castle"))
            seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Forest"));    
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
      }
      else if(type.getName().equals("TER_$Sand") || type.getName().equals("TER_$Dry_grass"))
      {     //put some TER_$Sand on the edges, then mostly TER_$Dry_grass
         edgeType = TerrainBuilder.getTerrainWithName("TER_$Sand");            
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));     
      }
      else if(type.getName().equals("TER_S_$Bog"))//bog
      {    
         edgeType = TerrainBuilder.getTerrainWithName("TER_S_$Bog");       
         if(!locType.contains("castle"))
            seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$High_grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
      }
      else if(type.getName().equals("TER_V_$Shallow_water"))
      {     //put some TER_$Dead_forest on the edges, then mostly TER_S_$Bog and 
         if(locType.contains("castle"))
            edgeType = TerrainBuilder.getTerrainWithName("TER_$Grassland"); 
         else
            edgeType = TerrainBuilder.getTerrainWithName("TER_S_$Bog");
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_S_$Bog"));   
         if(!locType.contains("castle"))
            seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$High_grassland"));   
      }
      else if(type.getName().equals("TER_$Dead_forest"))//dead-forest
      {     
         edgeType = TerrainBuilder.getTerrainWithName("TER_$Dead_forest");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));    
      }
      else
      {
         if(locType.contains("castle"))
            edgeType = TerrainBuilder.getTerrainWithName("TER_$Grassland"); 
         else
            edgeType = TerrainBuilder.getTerrainWithName("TER_$High_grassland");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));    
      }
   
      TerrainBuilder.seed(house, mapIndex, seedTypes, ((house.length) / (CultimaPanel.SCREEN_SIZE)) * 2);
   //put down edgeType around the borders
      for(int r=0; r<house.length; r++)
      {
         for(int c=0; c <=1; c++)
         {
            double prob = 0.5;
            if(c==1)
               prob = 0.25;
            if(Math.random() < prob)
               house[r][c] = edgeType.getValue();
            if(Math.random() < prob)
               house[r][house[0].length-1-c] = edgeType.getValue();
         }
      }
      for(int r=0; r<=1; r++)
      {
         double prob = 0.5;
         if(r==1)
            prob = 0.25;
         for(int c=0; c<house[0].length; c++)
         {
            if(Math.random() < prob)
               house[r][c] = edgeType.getValue();
            if(Math.random() < prob)
               house[house.length-1-r][c] = edgeType.getValue();
         }
      }
      ArrayList <Terrain> terrain = new ArrayList();
      for(Terrain t: CultimaPanel.allTerrain)
      {
         String terName = t.getName().toLowerCase();
         if(t.getName().startsWith("TER") && !terName.contains("water") && !terName.contains("lava") && !terName.contains("mountain"))
            terrain.add(t);
      }     
      int waterSize = 0;                        //the number of rows or columns the water on the shore is deep before we can place any structures or spawn-points
      if(locType.contains("port") || capital)
         waterSize = placeShore(house, terrain, waterDir);   
      else
         TerrainBuilder.build(house, terrain, false);
   
      int enteranceRow = 0, enteranceCol = 0;   //coordinates of where we enter the location
      int direction = 0;                        //the direction we will build a road or path
      int numPeople = 0;                        //the number of people at a temple (needed to know if we will put monsters in there in CultimaPanel)
      if(lastDir == 2)                         //enter in row 0
      {
         enteranceRow = 0;
         enteranceCol = house[0].length/2;
         direction = SOUTH;
         if(waterDir == NORTH)
            enteranceRow = waterSize;
      }      
      else if(lastDir == 3)                    //enter in last col
      {
         enteranceRow = house.length/2;
         enteranceCol = house[0].length - 1;
         direction = WEST;
         if(waterDir == EAST)
            enteranceCol = house[0].length - 1 - waterSize;
      }
      else if(lastDir == 0)                    //enter in last row
      {
         enteranceRow = house.length - 1;
         enteranceCol = house[0].length/2;
         direction = NORTH;
         if(waterDir == SOUTH)
            enteranceRow = house.length - 1 - waterSize;
      }  
      else                                      //ender in col 0
      {
         enteranceRow = house.length/2;
         enteranceCol = 0;
         direction = EAST;
         if(waterDir == WEST)
            enteranceCol = waterSize;
      }   
   
      if(locType.contains("castle") || locType.contains("tower"))
      {
         buildCastle(house, 1, house[0].length/2, lastDir, mapIndex, provinceName, false, 0, 0);
      }
      else if(locType.contains("arena"))
      {
         buildArena(house, lastDir, mapIndex);
      }
      else if (locType.contains("fortress") || locType.contains("city"))
      {
         Terrain path = buildWall(house, enteranceRow, enteranceCol, locType, mapIndex, capital);
         int castleSize = (int)(Math.random()*20)+(CultimaPanel.SCREEN_SIZE+15);
         if(capital)   //put small castle in the center of a capital city
         {
         //put down street and moat around castle
            Terrain deepWater = TerrainBuilder.getTerrainWithName("TER_I_$Deep_water");
            for(int r=house.length/2 - castleSize/2 - 2; r<=house.length/2 + castleSize/2 + 2; r++)
               for(int c=house[0].length/2 - castleSize/2 - 2; c<=house[0].length/2 + castleSize/2 + 2; c++)
               {
                  if(r==house.length/2 - castleSize/2 - 2 || r==house.length/2 + castleSize/2 + 2 || 
                  c==house[0].length/2 - castleSize/2 - 2 || c==house[0].length/2 + castleSize/2 + 2)
                     house[r][c] = path.getValue();
                  else 
                     house[r][c] = deepWater.getValue();  
               }
            byte[][]castle = new byte[castleSize][castleSize];
            int entranceSide = Player.rollDie(0,3);
            Terrain buildable = TerrainBuilder.getTerrainWithName("TER_$Dry_grass");
            Terrain drawBridge = TerrainBuilder.getTerrainWithName("INR_$Wood_Plank_floor");
            for(int r=0; r<castle.length; r++)
               for(int c=0; c<castle.length; c++)
                  castle[r][c] = buildable.getValue();  
            int startRow = house.length/2 - castleSize/2;
            int startCol = house[0].length/2 - castleSize/2;
            int bridgeWidth = buildCastle(castle, 0, castle[0].length/2, entranceSide, mapIndex, provinceName, true, startRow, startCol);
            for(int r=0; r<castle.length && startRow<house.length; r++)
            {
               for(int c=0; c<castle[0].length && startCol<house[0].length; c++)
               {
                  house[startRow][startCol] = castle[r][c];
                  startCol++;
               }
               startRow++;
               startCol = house[0].length/2 - castleSize/2;
            }
            startRow = house.length/2 - castleSize/2;
            startCol = house[0].length/2 - castleSize/2;
            if(entranceSide == NORTH)//entering moving north, so drawbridge is on the south side
            {  //place down connecting moat using bridgeWidth
               for(int r=startRow+castle.length-2; r<=startRow+castle.length+2; r++)
                  for(int c=house[0].length/2 - bridgeWidth/2; c<=house[0].length/2 + bridgeWidth/2; c++)
                     if(r>=0 && r<house.length && c>=0 && c<house[0].length)
                        house[r][c] = drawBridge.getValue();  
            } 
            else if(entranceSide == SOUTH)
            {  //place down connecting moat using bridgeWidth
               for(int r=startRow-2; r<=startRow+2; r++)
                  for(int c=house[0].length/2 - bridgeWidth/2; c<=house[0].length/2 + bridgeWidth/2; c++)
                     if(r>=0 && r<house.length && c>=0 && c<house[0].length)
                        house[r][c] = drawBridge.getValue();  
            }
            else if(entranceSide == EAST)
            {  //place down connecting moat using bridgeWidth
               for(int r=house.length/2 - bridgeWidth/2; r<=house.length/2 + bridgeWidth/2; r++)
                  for(int c=startCol-2; c<=startCol+2; c++)
                     if(r>=0 && r<house.length && c>=0 && c<house[0].length)
                        house[r][c] = drawBridge.getValue();  
            }
            else if(entranceSide == WEST)
            {  //place down connecting moat using bridgeWidth
               for(int r=house.length/2 - bridgeWidth/2; r<=house.length/2 + bridgeWidth/2; r++)
                  for(int c=startCol+castle[0].length-2; c<=startCol+castle[0].length+2; c++)                     
                     if(r>=0 && r<house.length && c>=0 && c<house[0].length)
                        if(r>=0 && r<house.length && c>=0 && c<house[0].length)
                           house[r][c] = drawBridge.getValue();           
            }
         }//end - place castle in the center of the capital city
          
         int numStores = (int)(Math.random()*3) + 2;  //2, 3 or 4 stores in a city
         if(capital)
            numStores = 4;
         ArrayList<Integer> quadrants = new ArrayList<Integer>();
         quadrants.add(1);                            //if we separated the city into quadrants I-IV, like cartesian coordinate plane
         quadrants.add(2);   
         quadrants.add(3);   
         quadrants.add(4);
         ArrayList<String> stores = new ArrayList<String>();
         stores.add("tavern");   
         stores.add("armory");  
         stores.add("magic");   
         stores.add("rations");
         for(int i=0; i<numStores; i++)
         {
            String randStore = stores.remove((int)(Math.random()*stores.size()));
            int randQuadrant = quadrants.remove((int)(Math.random()*quadrants.size()));
            for(int numTries = 0; numTries < 1000; numTries++)
            {
               NPCPlayer[][] occupants = buildShoppe(house, randStore, path, randQuadrant, locType, 0, mapIndex, capital);
               if(occupants != null)
                  break;
            }
         }
         int numBuildings = (int)(Math.random() * 20) + 20;
         if(capital)
            numBuildings = (int)(Math.random() * 40) + 40;
         for(int i=0; i<numBuildings; i++)
         {
            Terrain wall = TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick");
            if(path.getName().toLowerCase().contains("red"))
            {
               Terrain [] walls = {TerrainBuilder.getTerrainWithName("INR_I_B_$Straw_Wall"), TerrainBuilder.getTerrainWithName("INR_I_B_$Blue_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Tile_Wall")};
               wall = walls[(int)(Math.random()*walls.length)];
            }
            else if(path.getName().toLowerCase().contains("blue"))
            {
               Terrain [] walls = {TerrainBuilder.getTerrainWithName("INR_I_B_$Straw_Wall"), TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Tile_Wall")};
               wall = walls[(int)(Math.random()*walls.length)];
            }
            else
            {
               Terrain [] walls = {TerrainBuilder.getTerrainWithName("INR_I_B_$Straw_Wall"), TerrainBuilder.getTerrainWithName("INR_I_B_$Blue_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Tile_Wall")};
               wall = walls[(int)(Math.random()*walls.length)];
            }
            if(i==0)    //place at least one house we can buy
               placeBuilding(house, mapIndex, wall, path, locType, TerrainBuilder.getTerrainWithName("INR_$PurpleDirty_floor_inside"));
            else
               placeBuilding(house, mapIndex, wall, path, locType, null);
         }
      }
      else if (locType.contains("port"))
      {
         Terrain path = buildWall(house, enteranceRow, enteranceCol, locType, mapIndex, capital);
         int numStores = (int)(Math.random()*2) + 1;
         ArrayList<Integer> quadrants = new ArrayList<Integer>();
         quadrants.add(1);                            //if we separated the city into quadrants I-IV, like cartesian coordinate plane
         quadrants.add(2);   
         quadrants.add(3);   
         quadrants.add(4);
         ArrayList<String> stores = new ArrayList<String>();
         stores.add("armory");  
         stores.add("magic");   
         int randQuadrant = quadrants.remove((int)(Math.random()*quadrants.size()));
         buildShoppe(house, "rations", path, randQuadrant, locType, 0, mapIndex, capital);
         randQuadrant = quadrants.remove((int)(Math.random()*quadrants.size()));
         buildShoppe(house, "tavern", path, randQuadrant, locType, 0, mapIndex, capital);
         for(int i=0; i<numStores; i++)
         {
            String randStore = stores.remove((int)(Math.random()*stores.size()));
            randQuadrant = quadrants.remove((int)(Math.random()*quadrants.size()));
            buildShoppe(house, randStore, path, randQuadrant, locType, 0, mapIndex, capital);
         }
         int numBuildings = (int)(Math.random() * 20) + 20;
         for(int i=0; i<numBuildings; i++)
         {
            Terrain wall = TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick");
            if(path.getName().toLowerCase().contains("red"))
            {
               Terrain [] walls = {TerrainBuilder.getTerrainWithName("INR_I_B_$Straw_Wall"), TerrainBuilder.getTerrainWithName("INR_I_B_$Blue_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Tile_Wall")};
               wall = walls[(int)(Math.random()*walls.length)];
            }
            else if(path.getName().toLowerCase().contains("blue"))
            {
               Terrain [] walls = {TerrainBuilder.getTerrainWithName("INR_I_B_$Straw_Wall"), TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Tile_Wall")};
               wall = walls[(int)(Math.random()*walls.length)];
            }
            else
            {
               Terrain [] walls = {TerrainBuilder.getTerrainWithName("INR_I_B_$Straw_Wall"), TerrainBuilder.getTerrainWithName("INR_I_B_$Blue_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick"), TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Tile_Wall")};
               wall = walls[(int)(Math.random()*walls.length)];
            }
            if(i==0)    //place at least one house we can buy
               placeBuilding(house, mapIndex, wall, path, locType, TerrainBuilder.getTerrainWithName("INR_$PurpleDirty_floor_inside"));
            else
               placeBuilding(house, mapIndex, wall, path, locType, null);
         }
      }
      
      else if (locType.contains("village"))
      {
         boolean nearWater = TerrainBuilder.waterInrange(0, locRow, locCol, 3);
         Terrain path = buildWall(house, enteranceRow, enteranceCol, locType, mapIndex, capital);
         if(nearWater)
            addRiver(house, true, locType);
         int numStores = (int)(Math.random()*2) + 1;  //1 or 2 stores in a village
         ArrayList<Integer> quadrants = new ArrayList<Integer>();
         quadrants.add(1);                            //if we separated the city into quadrants I-IV, like cartesian coordinate plane
         quadrants.add(2);   
         quadrants.add(3);   
         quadrants.add(4);
         ArrayList<String> stores = new ArrayList<String>();
         stores.add("tavern");   
         stores.add("magic");   
         stores.add("rations");
         for(int i=0; i<numStores; i++)
         {
            String randStore = stores.remove((int)(Math.random()*stores.size()));
            int randQuadrant = quadrants.remove((int)(Math.random()*quadrants.size()));
            buildShoppe(house, randStore, path, randQuadrant, locType, 0, mapIndex, capital);
         }
         int numBuildings = (int)(Math.random() * 8) + 3;
         for(int i=0; i<numBuildings; i++)
         {
            Terrain [] walls = {TerrainBuilder.getTerrainWithName("INR_I_B_$Straw_Wall"), TerrainBuilder.getTerrainWithName("INR_I_B_$Gray_Rock"), TerrainBuilder.getTerrainWithName("INR_I_B_$White_Rock"), TerrainBuilder.getTerrainWithName("INR_I_B_$Wood_Wall")};
            Terrain wall = walls[(int)(Math.random()*walls.length)];
            if(i==0)    //place at least one house we can buy
               placeBuilding(house, mapIndex, wall, path, locType, TerrainBuilder.getTerrainWithName("INR_$PurpleDirty_floor_inside"));
            else
               placeBuilding(house, mapIndex, wall, path, locType, null);
         }
         //possibly place a set of chicken eggs somewhere
         int numEggs = Player.rollDie(0,3);
         ArrayList<Point> eggSpots = new ArrayList<Point>();
         for(int r=0; r<house.length; r++)
            for(int c=0; c<house[0].length; c++)
            {  //record the points of all non-indoor locations
               if(!CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().contains("INR") && !nextToADoor(house, r, c) && isDryLand(house, r, c))
               {
                  eggSpots.add(new Point(r, c));
               }
            }
         if(eggSpots.size() > 0 && numEggs > 0)
         {
            for(int i=0; i<numEggs; i++)
            {
               Point randSpot = eggSpots.get((int)(Math.random()*eggSpots.size()));
               int r=(int)(randSpot.getX());
               int c=(int)(randSpot.getY());
               CultimaPanel.eggsToHarvest.add(new RestoreItem(mapIndex, r, c, NPC.CHICKEN));
            }
         }   
      }
      else if (locType.contains("domicile"))
      {
         boolean nearWater = TerrainBuilder.waterInrange(0, locRow, locCol, 3);
         if(nearWater)
            addRiver(house, true, locType); 
         Terrain path = TerrainBuilder.getTerrainWithName("TER_$Dry_grass");
         String[]stores = {"magic", "rations"};
         String randStore = stores[(int)(Math.random()*stores.length)];
         int randQuadrant = (int)(Math.random()*4)+1;
         buildShoppe(house, randStore, path, randQuadrant, locType, 0, mapIndex, capital);
      }
      else if (locType.contains("temple"))
      {
         numPeople = buildTemple(house, edgeType, lastDir, mapIndex, provinceName);
      }
   
      int [] enterance = new int[2];
      enterance[0] = enteranceRow;
      enterance[1] = enteranceCol;
   
      Object [] retVal = new Object[3];
      retVal[0] = house;
      retVal[1] = enterance; 
      retVal[2] = new Integer(numPeople);
      
      //fix any colliding barrels
      for(int i=CultimaPanel.civilians.get(mapIndex).size()-1; i>=0; i--)
      {
         NPCPlayer curr = CultimaPanel.civilians.get(mapIndex).get(i);            
         if(curr.getCharIndex()==NPC.BARREL)
         {
            String currTerrain = CultimaPanel.allTerrain.get(Math.abs(house[curr.getRow()][curr.getCol()])).getName().toLowerCase();
            boolean badSpot = (currTerrain.contains("chest") || currTerrain.contains("book") || currTerrain.contains("bed") || currTerrain.contains("coffin"));
            if(badSpot || !isDryLand(house, curr.getRow(), curr.getCol()) || nextToADoor(house, curr.getRow(), curr.getCol()))
               Utilities.removeNPCat(curr.getRow(), curr.getCol(), mapIndex); 
         }
      } 
      
      return retVal;
   }

//place a shore on the side of waterDir for port cities
//returns the waterSize
   public static int placeShore(byte[][]house, ArrayList<Terrain>terrain, byte waterDir)
   {      
      int waterSize = Player.rollDie(8,15);  //number of rows or columns with water on the side designated by waterDir
      byte shallow = TerrainBuilder.getTerrainWithName("TER_V_$Shallow_water").getValue();
      byte deep = TerrainBuilder.getTerrainWithName("TER_I_$Deep_water").getValue();
      byte rapid = TerrainBuilder.getTerrainWithName("TER_I_$Rapid_water").getValue();
      byte shore = TerrainBuilder.getTerrainWithName("TER_$Sand").getValue();
      if(Math.random() < 0.5)
         shore = TerrainBuilder.getTerrainWithName("TER_S_$Bog").getValue();
      if(waterDir == NORTH)
      {
         for(int r=0; r<waterSize && r<house.length; r++)
         {
            for(int c=0; c<house[0].length; c++)
            {
               if(r==waterSize-1)
                  house[r][c] = shore;
               else if(r==waterSize-2)         //water closes to the land is shallow
                  house[r][c] = shallow;
               else                       //the further out we go, the more likely it is deep or rapid
               {
                  if(Player.rollDie(1,waterSize) < r)
                     house[r][c] = shallow;
                  else
                  {
                     if(Math.random() < 0.25)
                        house[r][c] = rapid;
                     else
                        house[r][c] = deep;
                  }
               }   
            }
         }
      }
      else if(waterDir == SOUTH)
      {
         for(int r=(house.length - 1) - waterSize ; r<house.length; r++)
         {
            for(int c=0; c<house[0].length; c++)
            {
               if(r==(house.length - 1) - waterSize)         //water closes to the land is shallow
                  house[r][c] = shore;
               else if(r==(house.length - 1) - waterSize + 1)         //water closes to the land is shallow
                  house[r][c] = shallow;
               else                       //the further out we go, the more likely it is deep or rapid
               {
                  if(house[r-1][c] == shallow && Math.random() < .66)
                     house[r][c] = shallow;
                  else
                  {
                     if(Math.random() < 0.25)
                        house[r][c] = rapid;
                     else
                        house[r][c] = deep;
                  }
               }   
            }
         }
      }
      else if(waterDir == WEST)
      {
         for(int c=0; c<waterSize && c<house[0].length; c++)
         {
            for(int r=0; r<house.length; r++)
            {
               if(c==waterSize-1)         
                  house[r][c] = shore;
               else if(c==waterSize-2)         //water closes to the land is shallow
                  house[r][c] = shallow;
               else                       //the further out we go, the more likely it is deep or rapid
               {
                  if(Player.rollDie(1,waterSize) < c)
                     house[r][c] = shallow;
                  else
                  {
                     if(Math.random() < 0.25)
                        house[r][c] = rapid;
                     else
                        house[r][c] = deep;
                  }
               }   
            }
         }
      }
      else if(waterDir == EAST)
      {
         for(int c=house[0].length - waterSize; c<house[0].length; c++)
         {
            for(int r=0; r<house.length; r++)
            {
               if(c==house[0].length - waterSize)        
                  house[r][c] = shore;
               else if(c==house[0].length - waterSize + 1)         //water closes to the land is shallow
                  house[r][c] = shallow;
               else                       //the further out we go, the more likely it is deep or rapid
               {
                  if(house[r][c-1] == shallow && Math.random() < .66)
                     house[r][c] = shallow;
                  else
                  {
                     if(Math.random() < 0.25)
                        house[r][c] = rapid;
                     else
                        house[r][c] = deep;
                  }
               }   
            }
         }
      }
      TerrainBuilder.build(house, terrain, false);
   
   //put down piers
      byte pier = TerrainBuilder.getTerrainWithName("INR_$Wood_Plank_floor").getValue();
      int numPiers = Player.rollDie(0,house.length/3);
      boolean samePierLength = false;
      if(Math.random() < 0.5)
         samePierLength = true;
      int pLength = Player.rollDie(1,waterSize);
      
      ArrayList<Integer> pierRows = new ArrayList<Integer>();
      for(int r=0; r<house.length; r++)
         pierRows.add(r);
      ArrayList<Integer> pierCols = new ArrayList<Integer>();
      for(int c=0; c<house.length; c++)
         pierCols.add(c);
         
      for(int i=0; i<numPiers; i++)
      {
         if(waterDir == NORTH)
         {
            if(pierCols.size()==0)
               break;
            Integer pointCol = pierCols.remove((int)(Math.random()*pierCols.size()));
            int r = waterSize - 1;
            int c = pointCol.intValue();
            int pierLength = Player.rollDie(1,waterSize);
            if(samePierLength)
               pierLength = pLength;
            int row = r;
            for(int p=0; p<pierLength && row>=0; p++)
               house[row--][c] = pier;
         }
         else if(waterDir == SOUTH)
         {
            if(pierCols.size()==0)
               break;
            Integer pointCol = pierCols.remove((int)(Math.random()*pierCols.size()));
            int r = (house.length - 1) - waterSize;
            int c = pointCol.intValue();
            int pierLength = Player.rollDie(1,waterSize);
            if(samePierLength)
               pierLength = pLength;
            int row = r;
            for(int p=0; p<pierLength && row<house.length; p++)
               house[row++][c] = pier;
         }
         else if(waterDir == EAST)
         {
            if(pierRows.size()==0)
               break;
            Integer pointRow = pierRows.remove((int)(Math.random()*pierRows.size()));
            int c = house[0].length - waterSize;
            int r = pointRow.intValue();
            int pierLength = Player.rollDie(1,waterSize);
            if(samePierLength)
               pierLength = pLength;
            int col = c;
            for(int p=0; p<pierLength && col<house[0].length; p++)
               house[r][col++] = pier;
         }
         else if(waterDir == WEST)
         {
            if(pierRows.size()==0)
               break;
            Integer pointRow = pierRows.remove((int)(Math.random()*pierRows.size()));
            int c = waterSize-1;
            int r = pointRow.intValue();
            int pierLength = Player.rollDie(1,waterSize);
            if(samePierLength)
               pierLength = pLength;
            int col = c;
            for(int p=0; p<pierLength && col>=0; p++)
               house[r][col--] = pier;
         }
      
      }
      return waterSize;
   }

//type is the kind of shoppe (armory, magic, tavern, rations), path is the kind of street in the city, quadrant (1, 2, 3, 4) and location type (city, village, castle)
//if locType is a castle, path is the kind of carpet we want to avoid building on
//returns occupant array if it could find a place to fit it, and place it in, otherwise returns null
   public static NPCPlayer[][] buildShoppe(byte[][]house, String type, Terrain path, int quadrant, String locType, int turretSize, int mapIndex, boolean capitalCity)
   {
      NPCPlayer[][] occupants = new NPCPlayer[house.length][house[0].length];    //fill with occupants to add to the civilians ArrayList after we complete and possilby rotate the map
      byte shopkeepIndex = NPC.MAN; 
      String namePrefix = "Ironsmith";      
      if(type.equals("magic"))
      {
         int rand = (int)(Math.random()*2);
         if(rand==0)
         {
            shopkeepIndex = NPC.WOMAN;
            namePrefix = "Mage";
         }
         else //if(rand==2)
         {
            shopkeepIndex = NPC.WISE;
            namePrefix = "Mage";
         }
      }
      else if(type.equals("rations"))
      {
         namePrefix = "Butcher";
         int rand = (int)(Math.random()*2);
         if(rand==1)
         {
            shopkeepIndex = NPC.WOMAN;
            namePrefix = "Madame Baker";
         }
         if(capitalCity)
            locType = "port";    //so shoppekeep can sell a ship
      }            
      else if(type.equals("tavern"))
      {
         namePrefix = "Barkeep";
         int rand = (int)(Math.random()*2);
         if(rand==1)
         {
            shopkeepIndex = NPC.WOMAN;
            namePrefix = "Madame Barkeep"; 
         }
      }
   
      boolean guards = false;
   
      int shoppeSize = 8;
      double chestProb = 0.75;
      double barrelProb = Player.rollDie(1,5) / 10.0;
      if(locType.contains("domicile"))
      {
         shoppeSize = 6;
         if(type.equals("armory"))
            chestProb = 0.45;
         else if(type.equals("magic"))
            chestProb = 0.25;
         else if(type.equals("rations"))
            chestProb = 0.15;
         else if(type.equals("tavern"))
            chestProb = 0.05;
         if(Math.random() < 0.1)
            guards = true;
      }
      else if(locType.contains("village"))
      {
         shoppeSize = 6;
         if(type.equals("armory"))
            chestProb = 0.55;
         else if(type.equals("magic"))
            chestProb = 0.35;
         else if(type.equals("rations"))
            chestProb = 0.25;
         else if(type.equals("tavern"))
            chestProb = 0.15;
         if(Math.random() < 0.25)
            guards = true;
      }
      else if(locType.contains("port"))
      {
         shoppeSize = 4;
         if(type.equals("armory"))
            chestProb = 0.6;
         else if(type.equals("magic"))
            chestProb = 0.5;
         else if(type.equals("rations"))
            chestProb = 0.4;
         else if(type.equals("tavern"))
            chestProb = 0.3;
         if(Math.random() < 0.5)
            guards = true;
      }
      else if(locType.contains("castle"))
      {
         shoppeSize = 4;  
         if(type.equals("armory"))
            chestProb = 0.55; 
         else if(type.equals("magic"))
            chestProb = 0.35;
         else if(type.equals("rations"))
            chestProb = 0.25;
         else if(type.equals("tavern"))
            chestProb = 0.15;
         if(Math.random() < 0.65)
            guards = true;
      }
      else if(locType.contains("fortress"))
      {
         shoppeSize = 4;  
         if(type.equals("armory"))
            chestProb = 0.65; 
         else if(type.equals("magic"))
            chestProb = 0.45;
         else if(type.equals("rations"))
            chestProb = 0.35;
         else if(type.equals("tavern"))
            chestProb = 0.25;
         if(Math.random() < 0.35)
            guards = true;
      }
      else if(locType.contains("city"))
      {
         shoppeSize = 4;  
         if(type.equals("armory"))
            chestProb = 0.75; 
         else if(type.equals("magic"))
            chestProb = 0.55;
         else if(type.equals("rations"))
            chestProb = 0.45;
         else if(type.equals("tavern"))
            chestProb = 0.35;
         if(Math.random() < 0.5)
            guards = true;
      }
      Terrain sign = TerrainBuilder.getTerrainWithName("INR_I_L_3_$Sign_tavern");
      if(type.equals("armory"))
         sign = TerrainBuilder.getTerrainWithName("INR_I_L_3_$Sign_armory");
      else if(type.equals("magic"))
         sign = TerrainBuilder.getTerrainWithName("INR_I_L_3_$Sign_magic");
      else if(type.equals("rations"))
         sign = TerrainBuilder.getTerrainWithName("INR_I_L_3_$Sign_rations");
      Terrain nightDoor = TerrainBuilder.getTerrainWithName("INR_$Night_door_floor");  //a door that will close at night
   
   //find the walled-in area of the city
      int ulrow = 1;               //mark the upper-left and lower-right rectangle of open area for the chosen quadrant
      int ulcol = 1;                      //upper-left row/col
      int urrow = 1;
      int urcol = house[0].length-2;      //upper-right row/col
      int llrow = house.length-2;
      int llcol = 1;                      //lower-left row/col
      int lrrow = house.length-2;
      int lrcol = house[0].length-2;      //lower-right row/col
   
      Terrain shoppeWall = TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Brick");
      if(locType.contains("village")  || locType.contains("domicile"))
      {
         Terrain [] walls = {TerrainBuilder.getTerrainWithName("INR_I_B_$Straw_Wall"), TerrainBuilder.getTerrainWithName("INR_I_B_$Gray_Rock"), TerrainBuilder.getTerrainWithName("INR_I_B_$White_Rock"), TerrainBuilder.getTerrainWithName("INR_I_B_$Wood_Wall")};
         shoppeWall = walls[(int)(Math.random()*walls.length)];
      }
      else if(locType.contains("castle") || capitalCity)
      {
         shoppeWall = TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick");
      }
      Terrain window = null;
      if(shoppeWall.getName().toLowerCase().contains("red_brick"))
         window = TerrainBuilder.getTerrainWithName("INR_I_L_1_$Red_Brick_Window");
      else if(shoppeWall.getName().toLowerCase().contains("white_brick"))
         window = TerrainBuilder.getTerrainWithName("INR_I_L_1_$White_Brick_Window");
      else if(shoppeWall.getName().toLowerCase().contains("blue_brick"))
         window = TerrainBuilder.getTerrainWithName("INR_I_L_1_$Blue_Brick_Window");
      else if(shoppeWall.getName().toLowerCase().contains("wood_wall"))
         window = TerrainBuilder.getTerrainWithName("INR_I_L_1_$Wood_Wall_Window");
   
      if(!locType.contains("castle") && !capitalCity)
         if(path.getName().contains("Red"))    //make shoppe wall different from the main path
         {
            if(locType.contains("village") || locType.contains("domicile")) 
               shoppeWall = TerrainBuilder.getTerrainWithName("INR_I_B_$Straw_Wall");
            else
            {
               shoppeWall = TerrainBuilder.getTerrainWithName("INR_I_B_$Blue_Brick");
               window = TerrainBuilder.getTerrainWithName("INR_I_L_1_$Blue_Brick_Window");
            }
         }
      if(!locType.contains("village") && !locType.contains("domicile") && !locType.contains("castle") && !locType.contains("port"))
      {
         for(int i=0; i<house.length && i<house[0].length; i++)
            if(!isBuildableGround(house, i, i))    //we found the upper-left wall
            {
               ulrow = i+1;
               ulcol = i+1;
               urrow = i+1;
               llcol = i+1;
               break;
            }
         for(int i=house.length-1; i>=0; i--)
            if(!isBuildableGround(house, i, i))    //we found the lower-right wall
            {
               lrrow = i-1;
               lrcol = i-1;
               llrow = i-1;
               urcol = i-1;
               break;
            }
      }
      int upperLeftRow = ulrow;     //these will store the upper-left and lower-right coordinates of the open space in a quadrant to place a shoppe
      int upperLeftCol = ulcol;
      int lowerRightRow = lrrow;
      int lowerRightCol = lrcol;
      int ulShoppeRow=0, ulShoppeCol=0, height=0, base=0;
      String carpetName = path.getName();
      if(carpetName.toLowerCase().contains("grass"))
         carpetName = "";
   
      Terrain [] doorsLocked = {TerrainBuilder.getTerrainWithName("INR_I_B_$Wood_door_locked"), TerrainBuilder.getTerrainWithName("INR_I_B_$Iron_door_locked")};
      Terrain backDoor = doorsLocked[(int)(Math.random()*doorsLocked.length)];
      if(locType.contains("village") ||  locType.contains("domicile"))
         backDoor = doorsLocked[0];    //only wood doors in villages and domiciles
      boolean itFits = false;                               //used to pick again if we collide with pavement
      if(quadrant == 1)             //upper-right
      {
      //find dimensions of open area in the quadrant
         upperLeftRow = ulrow;      
         upperLeftCol = house[0].length/2;
         lowerRightRow = house.length/2;
         lowerRightCol = lrcol;
      
         int r2 = urrow;
         for(int c=urcol; c>=house[0].length/2 && r2<house.length/2; c--)
         {//trace from upper-right corner down to center - if the next Terrain is pavement, stop
            if(!isBuildableGround(house, r2+1, c-1))
            {
               upperLeftCol = c;
               lowerRightRow = r2;
               break;
            }
            r2++;
         }
      
         if(locType.contains("castle"))
         {
            upperLeftRow = 3 + turretSize + 6;                   //+3 so we don't build in the moat(3 is the min moat size), +6 for min shoppe size
            upperLeftCol = house[0].length / 2;
            lowerRightRow = house.length/2;
            lowerRightCol = house[0].length-3-turretSize;
         }
      
      //fit the dimensions for the size of the shoppe we are building
         base = (int)(Math.random()* shoppeSize) + 6;           //horizontal dimension
         height = (int)(Math.random() * shoppeSize) + 6;        //vertical dimension
         ulShoppeRow = (int)(Math.random()*(lowerRightRow-upperLeftRow+1)) + (upperLeftRow);
         ulShoppeCol = (int)(Math.random()*(lowerRightCol-upperLeftCol+1)) + (upperLeftCol);
         for(int numTries = 0; numTries < 1000 && !itFits; numTries++)
         {
            itFits = true;
            base = (int)(Math.random()* shoppeSize) + 6;           //horizontal dimension
            height = (int)(Math.random() * shoppeSize) + 6;        //vertical dimension
            if(numTries >= 900)        //we need at least enough room for exterior wall -> floor -> counter -> floor -> exterior wall
            {
               base = 5;
               height = 5;
            }
            lowerRightRow -= height;
            lowerRightCol -= base;
         //pick a random upper-left coordinate for the shoppe
            ulShoppeRow = (int)(Math.random()*(lowerRightRow-upperLeftRow+1)) + (upperLeftRow);
            ulShoppeCol = (int)(Math.random()*(lowerRightCol-upperLeftCol+1)) + (upperLeftCol);
         
         //see if we collide with any pavement so we pick again if we do
            for(int r=ulShoppeRow+1; r<=ulShoppeRow+height-1; r++)
               for(int c=ulShoppeCol+1; c<=ulShoppeCol+base-1; c++)
               {
                  if(r<0 || c<0 || r>=house.length || c>=house[0].length)
                  {
                     itFits = false;
                     break;
                  }
                  String curr = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName(); 
                  if((locType.contains("castle") && (isImpassable(house, r, c) || curr.equals(carpetName) || !clearOfMoatArea(house, r, c))) 
                  || (!locType.contains("castle") && (!isBuildableGround(house, r, c)  || curr.equals(carpetName))))
                  {
                     itFits = false;
                     break;
                  }
               }
         }
      //place the shoppe from (ulShoppeRow, ulShoppeCol) to (ulShoppeRow+height, ulShoppeCol+base)
         if(itFits)
         {
            placeShoppe(house,  ulShoppeRow,  ulShoppeCol,  base,  height,  shoppeWall, window, path, locType);
         
            Terrain pathToStreet = path;
            boolean southFront = true;    //store front facing South or West
            if(Math.random() < 0.5)
               southFront = false;        
            if (southFront)               //store front facing South
            {
               int doorLoc = (ulShoppeCol + (ulShoppeCol+base)) / 2;
               if(!outOfBounds(house, ulShoppeRow+height, doorLoc))
                  house[ulShoppeRow+height][doorLoc] = nightDoor.getValue();
               if(!outOfBounds(house, ulShoppeRow+height, doorLoc+1))
                  house[ulShoppeRow+height][doorLoc+1] = nightDoor.getValue();
               if((!outOfBounds(house, ulShoppeRow+height, doorLoc-1))  && !CultimaPanel.allTerrain.get(Math.abs(house[ulShoppeRow+height][doorLoc-1])).getName().equals(path.getName()))
                  house[ulShoppeRow+height][doorLoc-1] = sign.getValue();
               if(guards && !isImpassable(house, ulShoppeRow+height, doorLoc+1))
                  occupants[ulShoppeRow+height][doorLoc+1] = (new NPCPlayer(NPC.randGuard(), ulShoppeRow+height, doorLoc+1, mapIndex, ulShoppeRow+height, doorLoc+1, locType)); 
                                          //      public NPCPlayer(byte index, int r, int c, int mi, int hr, int hc, String loc, int animDelay) 
            //trace a dirt or paved path back to the main street 
               pathToStreet =  CultimaPanel.allTerrain.get(Math.abs(house[house.length/2][doorLoc]));
               if(Math.random() < 0.5)
                  pathToStreet = TerrainBuilder.getTerrainWithName("TER_$Dry_grass");
               if(locType.contains("castle") || capitalCity)
                  pathToStreet = path;
               for(int r=ulShoppeRow+height+1; r<house.length/2; r++)
               {
                  String current = CultimaPanel.allTerrain.get(Math.abs(house[r][doorLoc])).getName();
                  String current2 = CultimaPanel.allTerrain.get(Math.abs(house[r][doorLoc+1])).getName();
                  if(current.equals(path.getName()) || current2.equals(path.getName()))
                     break;
                  if(!outOfBounds(house,r, doorLoc))
                     house[r][doorLoc] = pathToStreet.getValue();
                  if(!outOfBounds(house, r, doorLoc+1))
                     house[r][doorLoc+1] = pathToStreet.getValue();
               }
               if(Math.random() < 0.75)    //put a back door on the shoppe
               {
                  int backDoorCol = (int)(Math.random() * ((ulShoppeCol+base-1) - (ulShoppeCol+1) + 1)) + (ulShoppeCol+1);
                  if(isBuildableGround(house, ulShoppeRow-1, backDoorCol) && ulShoppeRow > 2)
                     house[ulShoppeRow][backDoorCol] = backDoor.getValue();
               }
            //put down purchase counter and possibly chests and a shopkeep
               boolean placedBook = false;
               int shopkeep = Player.rollDie(ulShoppeCol+1,ulShoppeCol+base-1);
               for(int c=ulShoppeCol+1; c<=ulShoppeCol+base-1; c++)
               {
                  if(!outOfBounds(house, ulShoppeRow+3, c))
                     house[ulShoppeRow+3][c] = TerrainBuilder.getTerrainWithName("INR_I_L_2_$Counter_lighted").getValue();
                  if(!outOfBounds(house, ulShoppeRow+2, c))
                  {
                     if(c==shopkeep)
                     {
                        NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, ulShoppeRow+2, c, mapIndex, ulShoppeRow+2, c, locType));
                        int ofLoc = shopKeep.getName().indexOf(" of ");
                        if(ofLoc != -1)
                           shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                        shopKeep.setName(namePrefix + " " + shopKeep.getName());
                        if((locType.contains("castle") || locType.contains("tower")) && namePrefix.contains("Ironsmith"))
                           shopKeep.addItem(Item.getCraftingManual());
                        occupants[ulShoppeRow+2][c] = shopKeep;  
                     }
                     else if(Math.random() < chestProb)
                        house[ulShoppeRow+2][c] = TerrainBuilder.getTerrainWithName("ITM_D_$Chest").getValue();
                     else if(!placedBook && Math.random() < 0.25)
                     {
                        house[ulShoppeRow+2][c] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
                        placedBook = true;
                     }
                  }
               }
            }
            else                          //store front facing West
            {
               int doorLoc = (ulShoppeRow + (ulShoppeRow+height)) / 2;
               if(!outOfBounds(house, doorLoc, ulShoppeCol))
                  house[doorLoc][ulShoppeCol] = nightDoor.getValue();
               if(!outOfBounds(house, doorLoc-1, ulShoppeCol))
                  house[doorLoc-1][ulShoppeCol] = nightDoor.getValue();
               if((!outOfBounds(house, doorLoc+1, ulShoppeCol)) && !CultimaPanel.allTerrain.get(Math.abs(house[doorLoc+1][ulShoppeCol])).getName().equals(path.getName()))
                  house[doorLoc+1][ulShoppeCol] = sign.getValue();
               if(guards && !isImpassable(house, doorLoc-1, ulShoppeCol))
                  occupants[doorLoc-1][ulShoppeCol] = (new NPCPlayer(NPC.randGuard(), doorLoc-1, ulShoppeCol, mapIndex, doorLoc-1, ulShoppeCol, locType));
            //trace a dirt or paved path back to the main street
               pathToStreet =  CultimaPanel.allTerrain.get(Math.abs(house[doorLoc][house[0].length/2]));
               if(Math.random() < 0.5)
                  pathToStreet = TerrainBuilder.getTerrainWithName("TER_$Dry_grass");
               if(locType.contains("castle") || capitalCity)
                  pathToStreet = path;
               for(int c=ulShoppeCol-1; c>house[0].length/2; c--)
               {
                  String current = CultimaPanel.allTerrain.get(Math.abs(house[doorLoc][c])).getName();
                  String current2 = CultimaPanel.allTerrain.get(Math.abs(house[doorLoc-1][c])).getName();
                  if(current.equals(path.getName()) || current2.equals(path.getName()))
                     break;
                  if(!outOfBounds(house, doorLoc, c))
                     house[doorLoc][c] = pathToStreet.getValue();
                  if(!outOfBounds(house, doorLoc-1, c))
                     house[doorLoc-1][c] = pathToStreet.getValue();
               }   
               if(Math.random() < 0.75)    //put a back door on the shoppe
               {
                  int backDoorRow = (int)(Math.random() * ((ulShoppeRow+height-1) - (ulShoppeRow+1) + 1)) + (ulShoppeRow+1);
                  if(isBuildableGround(house, backDoorRow, ulShoppeCol+base+1) && ulShoppeCol+base < house[0].length-3)
                     house[backDoorRow][ulShoppeCol+base] = backDoor.getValue();
               }
            //put down purchase counter and possibly chests and a shopkeep
               int shopkeep = Player.rollDie(ulShoppeRow+1,ulShoppeRow+height-1);
               boolean placedBook = false;
               for(int r=ulShoppeRow+1; r<=ulShoppeRow+height-1; r++)
               {
                  if(!outOfBounds(house, r, ulShoppeCol+base-3))
                     house[r][ulShoppeCol+base-3] = TerrainBuilder.getTerrainWithName("INR_I_L_2_$Counter_lighted").getValue();
                  if(!outOfBounds(house, r, ulShoppeCol+base-2))
                  {
                     if(r==shopkeep)
                     {
                        NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, r, ulShoppeCol+base-2, mapIndex, r, ulShoppeCol+base-2, locType));
                        int ofLoc = shopKeep.getName().indexOf(" of ");
                        if(ofLoc != -1)
                           shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                        shopKeep.setName(namePrefix + " " + shopKeep.getName());
                        if((locType.contains("castle") || locType.contains("tower")) && namePrefix.contains("Ironsmith"))
                           shopKeep.addItem(Item.getCraftingManual());
                        occupants[r][ulShoppeCol+base-2] = shopKeep;  
                     }
                     else if(Math.random() < chestProb)
                        house[r][ulShoppeCol+base-2] = TerrainBuilder.getTerrainWithName("ITM_D_$Chest").getValue();
                     else if(!placedBook && Math.random() < 0.25)
                     {
                        house[r][ulShoppeCol+base-2] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
                        placedBook = true;
                     }
                  }
               
               }
            }  
         }
      }
      else if(quadrant == 2)  //upper-left
      {
      //find dimensions of open area in the quadrant
         upperLeftRow = ulrow;
         upperLeftCol = ulcol;
         lowerRightRow = house.length/2;
         lowerRightCol = house[0].length/2;
      
         for(int i=ulrow; i<house.length/2; i++)
         {
            if(!isBuildableGround(house, i+1, i+1))     
            {
               lowerRightRow = i;
               lowerRightCol = i;
               break;
            }
         }
      
         if(locType.contains("castle"))
         {
            upperLeftRow = 3 + turretSize + 6;    //+3 so we don't build in the moat(3 is the min moat size), +6 for min shoppe size
            upperLeftCol = 3 + turretSize + 6;                  
            lowerRightRow = house.length/2;
            lowerRightCol = house[0].length/2;
         }
         
      //fit the dimensions for the size of the shoppe we are building
         base = (int)(Math.random()* shoppeSize) + 6;           //horizontal dimension
         height = (int)(Math.random() * shoppeSize) + 6;        //vertical dimension
         ulShoppeRow = (int)(Math.random()*(lowerRightRow-upperLeftRow+1)) + (upperLeftRow);
         ulShoppeCol = (int)(Math.random()*(lowerRightCol-upperLeftCol+1)) + (upperLeftCol);
         for(int numTries = 0; numTries < 1000 && !itFits; numTries++)
         {
            itFits = true;
            base = (int)(Math.random()* shoppeSize) + 6;           //horizontal dimension
            height = (int)(Math.random() * shoppeSize) + 6;        //vertical dimension
            if(numTries >= 900)        //we need at least enough room for exterior wall -> floor -> counter -> floor -> exterior wall
            {
               base = 5;
               height = 5;
            }
            lowerRightRow -= height;
            lowerRightCol -= base;
         //pick a random upper-left coordinate for the shoppe
            ulShoppeRow = (int)(Math.random()*(lowerRightRow-upperLeftRow+1)) + (upperLeftRow);
            ulShoppeCol = (int)(Math.random()*(lowerRightCol-upperLeftCol+1)) + (upperLeftCol);
         
         //see if we collide with any pavement so we pick again if we do
            for(int r=ulShoppeRow+1; r<=ulShoppeRow+height-1; r++)
               for(int c=ulShoppeCol+1; c<=ulShoppeCol+base-1; c++)
               {
                  if(r<0 || c<0 || r>=house.length || c>=house[0].length)
                  {
                     itFits = false;
                     break;
                  }
                  String curr = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName(); 
                  if((locType.contains("castle") && (isImpassable(house, r, c) || curr.equals(carpetName) || !clearOfMoatArea(house, r, c))) 
                  || (!locType.contains("castle") && (!isBuildableGround(house, r, c)  || curr.equals(carpetName))))
                  {
                     itFits = false;
                     break;
                  }
               }
         }
      
      //place the shoppe from (ulShoppeRow, ulShoppeCol) to (ulShoppeRow+height, ulShoppeCol+base)
         if(itFits)
         {
            placeShoppe(house,  ulShoppeRow,  ulShoppeCol,  base,  height,  shoppeWall, window, path, locType);
         
            Terrain pathToStreet = path;
            boolean southFront = true;    //store front facing South or East
            if(Math.random() < 0.5)
               southFront = false;        
            if (southFront)               //store front facing South
            {
               int doorLoc = (ulShoppeCol + (ulShoppeCol+base)) / 2;
               if(!outOfBounds(house, ulShoppeRow+height, doorLoc))
                  house[ulShoppeRow+height][doorLoc] = nightDoor.getValue();
               if(!outOfBounds(house, ulShoppeRow+height, doorLoc+1))
                  house[ulShoppeRow+height][doorLoc+1] = nightDoor.getValue();
               if((!outOfBounds(house, ulShoppeRow+height, doorLoc-1)) && !CultimaPanel.allTerrain.get(Math.abs(house[ulShoppeRow+height][doorLoc-1])).getName().equals(path.getName()))
                  house[ulShoppeRow+height][doorLoc-1] = sign.getValue();
               if(guards && !isImpassable(house, ulShoppeRow+height, doorLoc+1))
                  occupants[ulShoppeRow+height][doorLoc+1] = (new NPCPlayer(NPC.randGuard(), ulShoppeRow+height, doorLoc+1, mapIndex, ulShoppeRow+height, doorLoc+1, locType));
            //trace a dirt or paved path back to the main street 
               pathToStreet =  CultimaPanel.allTerrain.get(Math.abs(house[house.length/2][doorLoc]));
               if(Math.random() < 0.5)
                  pathToStreet = TerrainBuilder.getTerrainWithName("TER_$Dry_grass");
               if(locType.contains("castle") || capitalCity)
                  pathToStreet = path;
               for(int r=ulShoppeRow+height+1; r<house.length/2; r++)
               {
                  String current = CultimaPanel.allTerrain.get(Math.abs(house[r][doorLoc])).getName();
                  String current2 = CultimaPanel.allTerrain.get(Math.abs(house[r][doorLoc+1])).getName();
                  if(current.equals(path.getName()) || current2.equals(path.getName()))
                     break;
                  if(!outOfBounds(house, r, doorLoc))
                     house[r][doorLoc] = pathToStreet.getValue();
                  if(!outOfBounds(house, r, doorLoc+1))
                     house[r][doorLoc+1] = pathToStreet.getValue();
               }
               if(Math.random() < 0.75)    //put a back door on the shoppe
               {
                  int backDoorCol = (int)(Math.random() * ((ulShoppeCol+base-1) - (ulShoppeCol+1) + 1)) + (ulShoppeCol+1);
                  if(isBuildableGround(house, ulShoppeRow-1, backDoorCol) && ulShoppeRow > 2)
                     house[ulShoppeRow][backDoorCol] = backDoor.getValue();
               }
            //put down purchase counter and possibly chests and a shopkeep
               int shopkeep = Player.rollDie(ulShoppeCol+1,ulShoppeCol+base-1);
               boolean placedBook = false;
               for(int c=ulShoppeCol+1; c<=ulShoppeCol+base-1; c++)
               {
                  if(!outOfBounds(house, ulShoppeRow+3, c))
                     house[ulShoppeRow+3][c] = TerrainBuilder.getTerrainWithName("INR_I_L_2_$Counter_lighted").getValue();
                  if(!outOfBounds(house, ulShoppeRow+2, c))
                  {
                     if(c==shopkeep)
                     {
                        NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, ulShoppeRow+2, c, mapIndex, ulShoppeRow+2, c, locType));
                        int ofLoc = shopKeep.getName().indexOf(" of ");
                        if(ofLoc != -1)
                           shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                        shopKeep.setName(namePrefix + " " + shopKeep.getName());
                        if((locType.contains("castle") || locType.contains("tower")) && namePrefix.contains("Ironsmith"))
                           shopKeep.addItem(Item.getCraftingManual());
                        occupants[ulShoppeRow+2][c] = shopKeep;  
                     }
                     else if(Math.random() < chestProb)
                        house[ulShoppeRow+2][c] = TerrainBuilder.getTerrainWithName("ITM_D_$Chest").getValue();
                     else if(!placedBook && Math.random() < 0.25)
                     {
                        house[ulShoppeRow+2][c] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
                        placedBook = true;
                     }
                  
                  }
               }
            }   
            else                          //store front facing East
            {
               int doorLoc = (ulShoppeRow + (ulShoppeRow+height)) / 2;
               if(!outOfBounds(house, doorLoc, ulShoppeCol+base))
                  house[doorLoc][ulShoppeCol+base] = nightDoor.getValue();
               if(!outOfBounds(house, doorLoc-1, ulShoppeCol+base))
                  house[doorLoc-1][ulShoppeCol+base] = nightDoor.getValue();
               if((!outOfBounds(house, doorLoc+1, ulShoppeCol+base)) && !CultimaPanel.allTerrain.get(Math.abs(house[doorLoc+1][ulShoppeCol+base])).getName().equals(path.getName()))
                  house[doorLoc+1][ulShoppeCol+base] = sign.getValue();
               if(guards && !isImpassable(house, doorLoc-1, ulShoppeCol+base))
                  occupants[doorLoc-1][ulShoppeCol+base] = (new NPCPlayer(NPC.randGuard(), doorLoc-1, ulShoppeCol+base, mapIndex, doorLoc-1, ulShoppeCol+base, locType));
            //trace a dirt or paved path back to the main street
               pathToStreet =  CultimaPanel.allTerrain.get(Math.abs(house[doorLoc][house[0].length/2]));
               if(Math.random() < 0.5)
                  pathToStreet = TerrainBuilder.getTerrainWithName("TER_$Dry_grass");
               if(locType.contains("castle") || capitalCity)
                  pathToStreet = path;
               for(int c=ulShoppeCol+base+1; c<house[0].length/2; c++)
               {
                  String current = CultimaPanel.allTerrain.get(Math.abs(house[doorLoc][c])).getName();
                  String current2 = CultimaPanel.allTerrain.get(Math.abs(house[doorLoc-1][c])).getName();
                  if(current.equals(path.getName()) || current2.equals(path.getName()))
                     break;
                  if(!outOfBounds(house, doorLoc, c))
                     house[doorLoc][c] = pathToStreet.getValue();
                  if(!outOfBounds(house, doorLoc-1, c))
                     house[doorLoc-1][c] = pathToStreet.getValue();
               }   
               if(Math.random() < 0.75)    //put a back door on the shoppe
               {
                  int backDoorRow = (int)(Math.random() * ((ulShoppeRow+height-1) - (ulShoppeRow+1) + 1)) + (ulShoppeRow+1);
                  if((!outOfBounds(house, backDoorRow, ulShoppeCol)) && isBuildableGround(house, backDoorRow, ulShoppeCol-1) && ulShoppeCol > 2)
                     house[backDoorRow][ulShoppeCol] = backDoor.getValue();
               }
            //put down purchase counter and possibly chests and a shopkeep
               int shopkeep = Player.rollDie(ulShoppeRow+1,ulShoppeRow+height-1);  
               boolean placedBook = false;                
               for(int r=ulShoppeRow+1; r<=ulShoppeRow+height-1; r++)
               {
                  if(!outOfBounds(house, r, ulShoppeCol+3))
                     house[r][ulShoppeCol+3] = TerrainBuilder.getTerrainWithName("INR_I_L_2_$Counter_lighted").getValue();
                  if(!outOfBounds(house, r, ulShoppeCol+2))
                  {
                     if(r==shopkeep)
                     {
                        NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, r, ulShoppeCol+2, mapIndex, r, ulShoppeCol+2, locType));
                        int ofLoc = shopKeep.getName().indexOf(" of ");
                        if(ofLoc != -1)
                           shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                        shopKeep.setName(namePrefix + " " + shopKeep.getName());
                        if((locType.contains("castle") || locType.contains("tower")) && namePrefix.contains("Ironsmith"))
                           shopKeep.addItem(Item.getCraftingManual());
                        occupants[r][ulShoppeCol+2] = shopKeep;  
                     }
                     else if(Math.random() < chestProb)
                        house[r][ulShoppeCol+2] = TerrainBuilder.getTerrainWithName("ITM_D_$Chest").getValue();
                     else if(!placedBook && Math.random() < 0.25)
                     {
                        house[r][ulShoppeCol+2] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
                        placedBook = true;
                     }
                  
                  }
               
               }
            }
         }
      
      }
      else if(quadrant == 3)  //lower-left
      {
      //find dimensions of open area in the quadrant        
         upperLeftRow = house.length/2;
         upperLeftCol = ulcol;
         lowerRightRow = lrrow;
         lowerRightCol = house[0].length/2;
      
         int row = llrow;
         for(int c=ulcol; c<house[0].length/2; c++)
         {
            if(!isBuildableGround(house, row-1, c+1))   
            {
               upperLeftRow = row;
               lowerRightCol = c;
               break;
            }
            row--;
         }
      
         if(locType.contains("castle"))
         {
            upperLeftRow = house.length/2;
            upperLeftCol = 3 + turretSize + 6;   //+3 so we don't build in the moat(3 is the min moat size), +6 for min shoppe size
            lowerRightRow = house.length- 3 - turretSize;
            lowerRightCol = house[0].length/2;
         }
           
      //fit the dimensions for the size of the shoppe we are building
         base = (int)(Math.random()* shoppeSize) + 6;           //horizontal dimension
         height = (int)(Math.random() * shoppeSize) + 6;        //vertical dimension
         ulShoppeRow = (int)(Math.random()*(lowerRightRow-upperLeftRow+1)) + (upperLeftRow);
         ulShoppeCol = (int)(Math.random()*(lowerRightCol-upperLeftCol+1)) + (upperLeftCol);
         for(int numTries = 0; numTries < 1000 && !itFits; numTries++)
         {
            itFits = true;
            base = (int)(Math.random()* shoppeSize) + 6;           //horizontal dimension
            height = (int)(Math.random() * shoppeSize) + 6;        //vertical dimension
            if(numTries >= 900)        //we need at least enough room for exterior wall -> floor -> counter -> floor -> exterior wall
            {
               base = 5;
               height = 5;
            }
            lowerRightRow -= height;
            lowerRightCol -= base;
         //pick a random upper-left coordinate for the shoppe
            ulShoppeRow = (int)(Math.random()*(lowerRightRow-upperLeftRow+1)) + (upperLeftRow);
            ulShoppeCol = (int)(Math.random()*(lowerRightCol-upperLeftCol+1)) + (upperLeftCol);
         
         //see if we collide with any pavement so we pick again if we do
            for(int r=ulShoppeRow+1; r<=ulShoppeRow+height-1; r++)
               for(int c=ulShoppeCol+1; c<=ulShoppeCol+base-1; c++)
               {
                  if(r<0 || c<0 || r>=house.length || c>=house[0].length)
                  {
                     itFits = false;
                     break;
                  }
                  String curr = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName(); 
                  if((locType.contains("castle") && (isImpassable(house, r, c) || curr.equals(carpetName) || !clearOfMoatArea(house, r, c))) 
                  || (!locType.contains("castle") && (!isBuildableGround(house, r, c)  || curr.equals(carpetName))))
                  {
                     itFits = false;
                     break;
                  }
               }
         }
      
      //place the shoppe from (ulShoppeRow, ulShoppeCol) to (ulShoppeRow+height, ulShoppeCol+base)
         if(itFits)
         {
            placeShoppe(house,  ulShoppeRow,  ulShoppeCol,  base,  height,  shoppeWall, window, path, locType);
         
            Terrain pathToStreet = path;
            boolean northFront = true;    //store front facing North or East
            if(Math.random() < 0.5)
               northFront = false;        
            if (northFront)               //store front facing North
            {
               int doorLoc = (ulShoppeCol + (ulShoppeCol+base)) / 2;
               if(!outOfBounds(house, ulShoppeRow, doorLoc))
                  house[ulShoppeRow][doorLoc] = nightDoor.getValue();
               if(!outOfBounds(house, ulShoppeRow, doorLoc+1))
                  house[ulShoppeRow][doorLoc+1] = nightDoor.getValue();
               if((!outOfBounds(house, ulShoppeRow, doorLoc-1)) && !CultimaPanel.allTerrain.get(Math.abs(house[ulShoppeRow][doorLoc-1])).getName().equals(path.getName()))
                  house[ulShoppeRow][doorLoc-1] = sign.getValue();
               if(guards && !isImpassable(house, ulShoppeRow, doorLoc+1))
                  occupants[ulShoppeRow][doorLoc+1] = (new NPCPlayer(NPC.randGuard(), ulShoppeRow, doorLoc+1, mapIndex, ulShoppeRow, doorLoc+1, locType));
            //trace a dirt or paved path back to the main street 
               pathToStreet =  CultimaPanel.allTerrain.get(Math.abs(house[house.length/2][doorLoc]));
               if(Math.random() < 0.5)
                  pathToStreet = TerrainBuilder.getTerrainWithName("TER_$Dry_grass");
               if(locType.contains("castle") || capitalCity)
                  pathToStreet = path;
               for(int r=ulShoppeRow-1; r>=house.length/2; r--)
               {
                  String current = CultimaPanel.allTerrain.get(Math.abs(house[r][doorLoc])).getName();
                  String current2 = CultimaPanel.allTerrain.get(Math.abs(house[r][doorLoc+1])).getName();
                  if(current.equals(path.getName()) || current2.equals(path.getName()))
                     break;
                  if(!outOfBounds(house, r, doorLoc))
                     house[r][doorLoc] = pathToStreet.getValue();
                  if(!outOfBounds(house, r, doorLoc+1))
                     house[r][doorLoc+1] = pathToStreet.getValue();
               }
               if(Math.random() < 0.75)    //put a back door on the shoppe
               {
                  int backDoorCol = (int)(Math.random() * ((ulShoppeCol+base-1) - (ulShoppeCol+1) + 1)) + (ulShoppeCol+1);
                  if(isBuildableGround(house, ulShoppeRow+height+1, backDoorCol) && ulShoppeRow+height < house.length-3)
                     house[ulShoppeRow+height][backDoorCol] = backDoor.getValue();
               }
            //put down purchase counter and possibly chests and a shopkeep
               int shopkeep = Player.rollDie(ulShoppeCol+1,ulShoppeCol+base-1);
               boolean placedBook = false;
               for(int c=ulShoppeCol+1; c<=ulShoppeCol+base-1; c++)
               {
                  if(!outOfBounds(house, ulShoppeRow+height-3, c))
                     house[ulShoppeRow+height-3][c] = TerrainBuilder.getTerrainWithName("INR_I_L_2_$Counter_lighted").getValue();
                  if(!outOfBounds(house, ulShoppeRow+height-2, c))
                  {
                     if(c==shopkeep)
                     {
                        NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, ulShoppeRow+height-2, c, mapIndex, ulShoppeRow+height-2, c, locType));
                        shopKeep.setName(namePrefix + " " + shopKeep.getName());
                        int ofLoc = shopKeep.getName().indexOf(" of ");
                        if(ofLoc != -1)
                           shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                        if((locType.contains("castle") || locType.contains("tower")) && namePrefix.contains("Ironsmith"))
                           shopKeep.addItem(Item.getCraftingManual());
                        occupants[ulShoppeRow+height-2][c] = shopKeep;  
                     }
                     else if(Math.random() < chestProb)
                        house[ulShoppeRow+height-2][c] = TerrainBuilder.getTerrainWithName("ITM_D_$Chest").getValue();
                     else if(!placedBook && Math.random() < 0.25)
                     {
                        house[ulShoppeRow+height-2][c] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
                        placedBook = true;
                     }
                  }
               }
            
            }   
            else                          //store front facing East
            {
               int doorLoc = (ulShoppeRow + (ulShoppeRow+height)) / 2;
               if(!outOfBounds(house, doorLoc, ulShoppeCol+base))
                  house[doorLoc][ulShoppeCol+base] = nightDoor.getValue();
               if(!outOfBounds(house, doorLoc-1, ulShoppeCol+base))
                  house[doorLoc-1][ulShoppeCol+base] = nightDoor.getValue();
               if((!outOfBounds(house, doorLoc+1, ulShoppeCol+base)) && !CultimaPanel.allTerrain.get(Math.abs(house[doorLoc+1][ulShoppeCol+base])).getName().equals(path.getName()))
                  house[doorLoc+1][ulShoppeCol+base] = sign.getValue();
               if(guards && !isImpassable(house, doorLoc-1, ulShoppeCol+base))
                  occupants[doorLoc-1][ulShoppeCol+base] = (new NPCPlayer(NPC.randGuard(), doorLoc-1, ulShoppeCol+base, mapIndex, doorLoc-1, ulShoppeCol+base, locType));
            //trace a dirt or paved path back to the main street
               pathToStreet =  CultimaPanel.allTerrain.get(Math.abs(house[doorLoc][house[0].length/2]));
               if(Math.random() < 0.5)
                  pathToStreet = TerrainBuilder.getTerrainWithName("TER_$Dry_grass");
               if(locType.contains("castle") || capitalCity)
                  pathToStreet = path;
               for(int c=ulShoppeCol+base+1; c<house[0].length/2; c++)
               {
                  String current = CultimaPanel.allTerrain.get(Math.abs(house[doorLoc][c])).getName();
                  String current2 = CultimaPanel.allTerrain.get(Math.abs(house[doorLoc-1][c])).getName();
                  if(current.equals(path.getName()) || current2.equals(path.getName()))
                     break;
                  if(!outOfBounds(house, doorLoc, c))
                     house[doorLoc][c] = pathToStreet.getValue();
                  if(!outOfBounds(house, doorLoc-1, c))
                     house[doorLoc-1][c] = pathToStreet.getValue();
               }   
               if(Math.random() < 0.75)    //put a back door on the shoppe
               {
                  int backDoorRow = (int)(Math.random() * ((ulShoppeRow+height-1) - (ulShoppeRow+1) + 1)) + (ulShoppeRow+1);
                  if(isBuildableGround(house, backDoorRow, ulShoppeCol-1) && ulShoppeCol > 2)
                     house[backDoorRow][ulShoppeCol] = backDoor.getValue();
               }
            //put down purchase counter and possibly chests and a shopkeep
               int shopkeep = Player.rollDie(ulShoppeRow+1,ulShoppeRow+height-1);
               boolean placedBook = false;
               for(int r=ulShoppeRow+1; r<=ulShoppeRow+height-1; r++)
               {
                  if(!outOfBounds(house, r, ulShoppeCol+3))
                     house[r][ulShoppeCol+3] = TerrainBuilder.getTerrainWithName("INR_I_L_2_$Counter_lighted").getValue();
                  if(!outOfBounds(house, r, ulShoppeCol+2))
                  {
                     if(r==shopkeep)
                     {
                        NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, r, ulShoppeCol+2, mapIndex, r, ulShoppeCol+2, locType));
                        shopKeep.setName(namePrefix + " " + shopKeep.getName());
                        int ofLoc = shopKeep.getName().indexOf(" of ");
                        if(ofLoc != -1)
                           shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                        if((locType.contains("castle") || locType.contains("tower")) && namePrefix.contains("Ironsmith"))
                           shopKeep.addItem(Item.getCraftingManual());
                        occupants[r][ulShoppeCol+2] = shopKeep;  
                     }
                     else if(Math.random() < chestProb)
                        house[r][ulShoppeCol+2] = TerrainBuilder.getTerrainWithName("ITM_D_$Chest").getValue();
                     else if(!placedBook && Math.random() < 0.25)
                     {
                        house[r][ulShoppeCol+2] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
                        placedBook = true;
                     }
                  }
               }
            }
         }
      }
      else                    //lower-right
      {
      //find dimensions of open area in the quadrant
         upperLeftRow = house.length/2;
         upperLeftCol = house[0].length/2;
         lowerRightRow = lrrow;
         lowerRightCol = lrcol;
      
         for(int i=lrcol; i>house[0].length/2; i--)
         {
            if(!isBuildableGround(house, i-1, i-1))
            {
               upperLeftRow = i;
               upperLeftCol = i;
               break;
            }
         }
      
         if(locType.contains("castle"))
         {
            upperLeftRow = house.length/2;
            upperLeftCol = house[0].length/2;
            lowerRightRow = house.length - 3 - turretSize;  //+3 so we don't build in the moat(3 is the min moat size), +6 for min shoppe size
            lowerRightCol = house[0].length - 3 - turretSize;
         }
      
      //fit the dimensions for the size of the shoppe we are building
         base = (int)(Math.random()* shoppeSize) + 6;           //horizontal dimension
         height = (int)(Math.random() * shoppeSize) + 6;        //vertical dimension
         ulShoppeRow = (int)(Math.random()*(lowerRightRow-upperLeftRow+1)) + (upperLeftRow);
         ulShoppeCol = (int)(Math.random()*(lowerRightCol-upperLeftCol+1)) + (upperLeftCol);
         for(int numTries = 0; numTries < 1000 && !itFits; numTries++)
         {
            itFits = true;
            base = (int)(Math.random()* shoppeSize) + 6;           //horizontal dimension
            height = (int)(Math.random() * shoppeSize) + 6;        //vertical dimension
            if(numTries >= 900)        //we need at least enough room for exterior wall -> floor -> counter -> floor -> exterior wall
            {
               base = 5;
               height = 5;
            }
            lowerRightRow -= height;
            lowerRightCol -= base;
         //pick a random upper-left coordinate for the shoppe
            ulShoppeRow = (int)(Math.random()*(lowerRightRow-upperLeftRow+1)) + (upperLeftRow);
            ulShoppeCol = (int)(Math.random()*(lowerRightCol-upperLeftCol+1)) + (upperLeftCol);
         
         //see if we collide with any pavement so we pick again if we do
         
            for(int r=ulShoppeRow+1; r<=ulShoppeRow+height-1; r++)
               for(int c=ulShoppeCol+1; c<=ulShoppeCol+base-1; c++)
               {
                  if(r<0 || c<0 || r>=house.length || c>=house[0].length)
                  {
                     itFits = false;
                     break;
                  }
                  String curr = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName(); 
                  if((locType.contains("castle") && (isImpassable(house, r, c) || curr.equals(carpetName) || !clearOfMoatArea(house, r, c))) 
                  || (!locType.contains("castle") && (!isBuildableGround(house, r, c)  || curr.equals(carpetName))))
                  {
                     itFits = false;
                     break;
                  }
               }
         }
      //place the shoppe from (ulShoppeRow, ulShoppeCol) to (ulShoppeRow+height, ulShoppeCol+base)
         if(itFits)
         {
            placeShoppe(house,  ulShoppeRow,  ulShoppeCol,  base,  height,  shoppeWall, window, path, locType);
         
            Terrain pathToStreet = path;
            boolean northFront = true;    //store front facing North or West
            if(Math.random() < 0.5)
               northFront = false;        
            if (northFront)               //store front facing North
            {
               int doorLoc = (ulShoppeCol + (ulShoppeCol+base)) / 2;
               if(!outOfBounds(house, ulShoppeRow, doorLoc))
                  house[ulShoppeRow][doorLoc] = nightDoor.getValue();
               if(!outOfBounds(house, ulShoppeRow, doorLoc+1))
                  house[ulShoppeRow][doorLoc+1] = nightDoor.getValue();
               if((!outOfBounds(house, ulShoppeRow, doorLoc-1)) && !CultimaPanel.allTerrain.get(Math.abs(house[ulShoppeRow][doorLoc-1])).getName().equals(path.getName()))
                  house[ulShoppeRow][doorLoc-1] = sign.getValue();
               if(guards && !isImpassable(house, ulShoppeRow, doorLoc+1))
                  occupants[ulShoppeRow][doorLoc+1] = (new NPCPlayer(NPC.randGuard(), ulShoppeRow, doorLoc+1, mapIndex, ulShoppeRow, doorLoc+1, locType));
            //trace a dirt or paved path back to the main street 
               pathToStreet =  CultimaPanel.allTerrain.get(Math.abs(house[house.length/2][doorLoc]));
               if(Math.random() < 0.5)
                  pathToStreet = TerrainBuilder.getTerrainWithName("TER_$Dry_grass");
               if(locType.contains("castle") || capitalCity)
                  pathToStreet = path;
               for(int r=ulShoppeRow-1; r>=house.length/2; r--)
               {
                  String current = CultimaPanel.allTerrain.get(Math.abs(house[r][doorLoc])).getName();
                  String current2 = CultimaPanel.allTerrain.get(Math.abs(house[r][doorLoc+1])).getName();
                  if(current.equals(path.getName()) || current2.equals(path.getName()))
                     break;
                  if(!outOfBounds(house, r, doorLoc))
                     house[r][doorLoc] = pathToStreet.getValue();
                  if(!outOfBounds(house, r, doorLoc+1))
                     house[r][doorLoc+1] = pathToStreet.getValue();
               }
               if(Math.random() < 0.75)    //put a back door on the shoppe
               {
                  int backDoorCol = (int)(Math.random() * ((ulShoppeCol+base-1) - (ulShoppeCol+1) + 1)) + (ulShoppeCol+1);
                  if(isBuildableGround(house, ulShoppeRow+height+1, backDoorCol) && ulShoppeRow+height < house.length - 3)
                     house[ulShoppeRow+height][backDoorCol] = backDoor.getValue();
               }
            //put down purchase counter and possibly chests and a shopkeep
               int shopkeep = Player.rollDie(ulShoppeCol+1,ulShoppeCol+base-1);
               boolean placedBook = false;
               for(int c=ulShoppeCol+1; c<=ulShoppeCol+base-1; c++)
               {
                  if(!outOfBounds(house, ulShoppeRow+height-3, c))
                     house[ulShoppeRow+height-3][c] = TerrainBuilder.getTerrainWithName("INR_I_L_2_$Counter_lighted").getValue();
                  if(!outOfBounds(house, ulShoppeRow+height-2, c))
                  {
                     if(c==shopkeep)
                     {
                        NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, ulShoppeRow+height-2, c, mapIndex, ulShoppeRow+height-2, c, locType));
                        shopKeep.setName(namePrefix + " " + shopKeep.getName());
                        int ofLoc = shopKeep.getName().indexOf(" of ");
                        if(ofLoc != -1)
                           shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                        if((locType.contains("castle") || locType.contains("tower")) && namePrefix.contains("Ironsmith"))
                           shopKeep.addItem(Item.getCraftingManual());
                        occupants[ulShoppeRow+height-2][c] = shopKeep;  
                     }
                     else if(Math.random() < chestProb)
                        house[ulShoppeRow+height-2][c] = TerrainBuilder.getTerrainWithName("ITM_D_$Chest").getValue();
                     else if(!placedBook && Math.random() < 0.25)
                     {
                        house[ulShoppeRow+height-2][c] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
                        placedBook = true;
                     }
                  }
               }
            }
            else                          //store front facing West
            {
               int doorLoc = (ulShoppeRow + (ulShoppeRow+height)) / 2;
               if(!outOfBounds(house, doorLoc, ulShoppeCol))
                  house[doorLoc][ulShoppeCol] = nightDoor.getValue();
               if(!outOfBounds(house, doorLoc-1, ulShoppeCol))
                  house[doorLoc-1][ulShoppeCol] = nightDoor.getValue();
               if((!outOfBounds(house, doorLoc+1, ulShoppeCol)) && !CultimaPanel.allTerrain.get(Math.abs(house[doorLoc+1][ulShoppeCol])).getName().equals(path.getName()))
                  house[doorLoc+1][ulShoppeCol] = sign.getValue();
               if(guards && !isImpassable(house, doorLoc-1, ulShoppeCol))
                  occupants[doorLoc-1][ulShoppeCol] = (new NPCPlayer(NPC.randGuard(), doorLoc-1, ulShoppeCol, mapIndex, doorLoc-1, ulShoppeCol, locType));
            //trace a dirt or paved path back to the main street
               pathToStreet =  CultimaPanel.allTerrain.get(Math.abs(house[doorLoc][house[0].length/2]));
               if(Math.random() < 0.5)
                  pathToStreet = TerrainBuilder.getTerrainWithName("TER_$Dry_grass");
               if(locType.contains("castle") || capitalCity)
                  pathToStreet = path;
               for(int c=ulShoppeCol-1; c>house[0].length/2; c--)
               {
                  String current = CultimaPanel.allTerrain.get(Math.abs(house[doorLoc][c])).getName();
                  String current2 = CultimaPanel.allTerrain.get(Math.abs(house[doorLoc-1][c])).getName();
                  if(current.equals(path.getName()) || current2.equals(path.getName()))
                     break;
                  if(!outOfBounds(house, doorLoc, c))
                     house[doorLoc][c] = pathToStreet.getValue();
                  if(!outOfBounds(house, doorLoc-1, c))
                     house[doorLoc-1][c] = pathToStreet.getValue();
               }   
               if(Math.random() < 0.75)    //put a back door on the shoppe
               {
                  int backDoorRow = (int)(Math.random() * ((ulShoppeRow+height-1) - (ulShoppeRow+1) + 1)) + (ulShoppeRow+1);
                  if(isBuildableGround(house, backDoorRow,ulShoppeCol+base+1) && ulShoppeCol+base<house[0].length-3)
                     house[backDoorRow][ulShoppeCol+base] = backDoor.getValue();
               }
            //put down purchase counter and possibly chests and a shopkeep
               int shopkeep = Player.rollDie(ulShoppeRow+1,ulShoppeRow+height-1);
               boolean placedBook = false;
               for(int r=ulShoppeRow+1; r<=ulShoppeRow+height-1; r++)
               {
                  if(!outOfBounds(house, r, ulShoppeCol+base-3))
                     house[r][ulShoppeCol+base-3] = TerrainBuilder.getTerrainWithName("INR_I_L_2_$Counter_lighted").getValue();
                  if(!outOfBounds(house, r, ulShoppeCol+base-2))
                  {
                     if(r==shopkeep)
                     {
                        NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, r, ulShoppeCol+base-2, mapIndex, r, ulShoppeCol+base-2, locType));
                        shopKeep.setName(namePrefix + " " + shopKeep.getName());
                        int ofLoc = shopKeep.getName().indexOf(" of ");
                        if(ofLoc != -1)
                           shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                        if((locType.contains("castle") || locType.contains("tower")) && namePrefix.contains("Ironsmith"))
                           shopKeep.addItem(Item.getCraftingManual());
                        occupants[r][ulShoppeCol+base-2] = shopKeep;  
                     }
                     else if(Math.random() < chestProb)
                        house[r][ulShoppeCol+base-2] = TerrainBuilder.getTerrainWithName("ITM_D_$Chest").getValue();
                     else if(!placedBook && Math.random() < 0.25)
                     {
                        house[r][ulShoppeCol+base-2] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
                        placedBook = true;
                     }
                  
                  }
               }
            }
         
         }
      }
      
      //place some barrels along the shoppe walls
      for(int r=ulShoppeRow-1; r<=ulShoppeRow+height+1; r++)
         for(int c=ulShoppeCol-1; c<=ulShoppeCol+base+1; c++)
         {
            if(r<0 || c<0 || r>=house.length || c>=house[0].length)
               continue;
            if((r<ulShoppeRow+1 || c<ulShoppeCol+1 || r>ulShoppeRow+height-1 || c>ulShoppeCol+base-1) && !nextToASign(house, r, c))
               continue;
            //make sure there isn't a shoppekeep 2 spaces adjacent so they don't get blocked in
            if((r-2 >= 0 && occupants[r-2][c] != null) || (r+2 < house.length && occupants[r+2][c] != null))
               continue;
            if((c-2 >= 0 && occupants[r][c-2] != null) || (c+2 < house[0].length && occupants[r][c+2] != null))
               continue;    
            String current = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().toLowerCase();
            if(!isImpassable(house, r, c) && nextToAWall(house, r, c) && !nextToADoor(house, r, c) &&
                  occupants[r][c]==null && !current.contains("chest") && !current.contains("book")  && 
                  !current.contains("bed")  && !current.contains("coffin") && 
                  (Math.random() < barrelProb || (nextToASign(house, r, c) && Math.random()<0.5)))
            {
               NPCPlayer barrel = (new NPCPlayer(NPC.BARREL, r, c, mapIndex, locType));
               barrel.clearItems();
               barrel.setGold(0);
               //make barrel contents match the shoppe type
               if(type.equals("armory"))
               {
                  if(Math.random() < 0.5)
                  {
                     barrel.setWeapon(Weapon.getRandomWeaponSimple());
                  }
                  else if(Math.random() < 0.5)
                  {
                     barrel.setArmor(Armor.getRandomArmorSimple());
                  }
               } 
               else if(type.equals("magic"))
               {
                  if(Math.random() < 0.5)
                     barrel.addItem(Potion.getRandomPotion());
                  else if(Math.random() < 0.1)
                     barrel.addItem(Item.getRandomMagicItemSimple());   
               }
               else if(type.equals("rations"))
               {
                  if(Math.random() < 0.5)       //chance will have rations
                  {
                     barrel.addItem(new Item("meat", Player.rollDie(2,6)));
                  }
               }
               else if(type.equals("tavern"))
               {
                  if(Math.random() < 0.25)       //chance will have rations
                  {
                     barrel.addItem(new Item("meat", Player.rollDie(1,2)));
                  }
                  if(Math.random() < 0.1)
                     barrel.setGold(Player.rollDie(1, 10));
               } 
               occupants[r][c] = barrel;
            }
         }
          
      if(itFits)
      {
         for(int r=0; r<occupants.length; r++)
            for(int c=0; c<occupants[0].length; c++)
               if(occupants[r][c] != null)
               {
                  NPCPlayer current = occupants[r][c];
                  if(mapIndex >= CultimaPanel.civilians.size())
                     CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
                  NPCPlayer tempNPC = new NPCPlayer(current.getName(), current.getCharIndex(), r, c, mapIndex, current.getHomeRow(),current.getHomeCol(), locType);
                  tempNPC.setWeapon(current.getWeapon());
                  tempNPC.setArmor(current.getArmor());
                  if(!Utilities.NPCAt(r, c, mapIndex))    
                     CultimaPanel.civilians.get(mapIndex).add(tempNPC);
               }
         return occupants;
      }
      return null;
   }

//place floor and walls for a building with an upper-left corner of (ulShoppeRow, ulShoppeCol, a width of <base> and a height of <height>
   public static void placeShoppe(byte[][]house, int ulShoppeRow, int ulShoppeCol, int base, int height, Terrain shoppeWall, Terrain window, Terrain path, String locType)
   {
   //put down floor of the shoppe, and clear out foliage around the shop
      Terrain floor = path;
      if(path.getName().contains("Blue_Brick"))
         floor = TerrainBuilder.getTerrainWithName("INR_$Blue_Brick_floor_inside");
      else if(path.getName().contains("Red_Brick"))
         floor = TerrainBuilder.getTerrainWithName("INR_$Red_Brick_floor_inside");   
      if(!locType.contains("castle") && !locType.contains("wolfenstein"))
      {
         for(int r=ulShoppeRow-1; r<=ulShoppeRow+height+1; r++)
            for(int c=ulShoppeCol-1; c<=ulShoppeCol+base+1; c++)
            {
               if(!outOfBounds(house, r, c))
               {
                  if(r>=ulShoppeRow+1 && r<=ulShoppeRow+height-1 && c>=ulShoppeCol+1 && c<=ulShoppeCol+base-1)
                     house[r][c] = floor.getValue();
                  else if(r>=0 && r<house.length && c>=0 && c<house[0].length && isBuildableGround(house, r, c))
                     house[r][c] = TerrainBuilder.getTerrainWithName("TER_$Dry_grass").getValue();
               }
            }
      }
   
   //put down shoppe walls
      for(int r=ulShoppeRow; r<=ulShoppeRow+height; r++)
      {
         if(window!=null)
         {
            if((r!=ulShoppeRow) && (r!=ulShoppeRow+height))
            {
               if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, r, ulShoppeCol)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, r, ulShoppeCol)))
               {
                  if(Math.random() < 0.1 && !nextToAWindow(house, r, ulShoppeCol))
                     house[r][ulShoppeCol] = window.getValue();
                  else
                     house[r][ulShoppeCol] = shoppeWall.getValue();
               }  
               if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, r, ulShoppeCol+base)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, r, ulShoppeCol+base)))
               {
                  if(Math.random() < 0.1 && !nextToAWindow(house, r, ulShoppeCol+base))
                     house[r][ulShoppeCol+base] = window.getValue();
                  else
                     house[r][ulShoppeCol+base] = shoppeWall.getValue();
               }
            }
            else
            {
               if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, r, ulShoppeCol)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, r, ulShoppeCol)))
                  house[r][ulShoppeCol] = shoppeWall.getValue();
               if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, r, ulShoppeCol+base)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, r, ulShoppeCol+base)))  
                  house[r][ulShoppeCol+base] = shoppeWall.getValue();
            }
         }
         else
         {
            if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, r, ulShoppeCol)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, r, ulShoppeCol)))
               house[r][ulShoppeCol] = shoppeWall.getValue();
            if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, r, ulShoppeCol+base)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, r, ulShoppeCol+base)))
               house[r][ulShoppeCol+base] = shoppeWall.getValue();
         }
      }
      for(int c=ulShoppeCol; c<=ulShoppeCol+base; c++)
      {
         if(window!=null)
         {
            if((c!=ulShoppeCol) && (c!=ulShoppeCol+base))
            {
               if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, ulShoppeRow, c)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, ulShoppeRow, c)))
               {
                  if(Math.random() < 0.1  && !nextToAWindow(house, ulShoppeRow, c))
                     house[ulShoppeRow][c] = window.getValue();
                  else
                     house[ulShoppeRow][c] = shoppeWall.getValue();
               }
               if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, ulShoppeRow+height, c)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, ulShoppeRow+height, c)))
               {
                  if(Math.random() < 0.1  && !nextToAWindow(house, ulShoppeRow+height, c))
                     house[ulShoppeRow+height][c] = window.getValue();
                  else
                     house[ulShoppeRow+height][c] = shoppeWall.getValue();
               }
            }
            else
            {
               if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, ulShoppeRow, c)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, ulShoppeRow, c)))
                  house[ulShoppeRow][c] = shoppeWall.getValue();
               if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, ulShoppeRow+height, c)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, ulShoppeRow+height, c)))
                  house[ulShoppeRow+height][c] = shoppeWall.getValue();
            }
         } 
         else
         {
            if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, ulShoppeRow, c)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, ulShoppeRow, c)))
               house[ulShoppeRow][c] = shoppeWall.getValue();
            if(((locType.contains("castle") || locType.contains("wolfenstein")) && !isImpassable(house, ulShoppeRow+height, c)) || (!locType.contains("castle") && !locType.contains("wolfenstein") && isBuildableGround(house, ulShoppeRow+height, c)))
               house[ulShoppeRow+height][c] = shoppeWall.getValue();
         }
      }
   }

//place floor and walls for a room in a building with an upper-left corner of (ulShoppeRow, ulShoppeCol, a width of <base> and a height of <height>
//prob is the probability that we place a wall block on a particular spot, to make it easy to create ruins with holes in it
   public static void placeRoom(byte[][]house, int ulShoppeRow, int ulShoppeCol, int base, int height, Terrain shoppeWall, Terrain floor, double prob)
   {
   //put down floor of the room
      if(floor != null)
      {
         for(int r=ulShoppeRow; r<=ulShoppeRow+height; r++)
            for(int c=ulShoppeCol; c<=ulShoppeCol+base; c++)
            {
               house[r][c] = floor.getValue();
            }
      }  
   //put down room walls
      for(int r=ulShoppeRow; r<=ulShoppeRow+height; r++)
      {
         if(!isImpassable(house, r, ulShoppeCol) && Math.random() < prob)
            house[r][ulShoppeCol] = shoppeWall.getValue();
         if(!isImpassable(house, r, ulShoppeCol+base) && Math.random() < prob)
            house[r][ulShoppeCol+base] = shoppeWall.getValue();
      }
      for(int c=ulShoppeCol; c<=ulShoppeCol+base; c++)
      {
         if(!isImpassable(house, ulShoppeRow, c) && Math.random() < prob)
            house[ulShoppeRow][c] = shoppeWall.getValue();
         if(!isImpassable(house, ulShoppeRow+height, c) && Math.random() < prob)
            house[ulShoppeRow+height][c] = shoppeWall.getValue();
      }
   }

//pick a random spot, see if we can get a building of at least 2x2 to fit there, 
//returns the occupant placed and the center of the room added as an object array
   public static Object[] placeBuilding(byte[][]house, int mapIndex, Terrain wall, Terrain floor, String locType, Terrain carpet)
   {
      ArrayList<NPCPlayer> occupants = new ArrayList<NPCPlayer>();    //fill with occupants to add to the civilians ArrayList after we complete and possilby rotate the map
      Object [] ans = new Object[2];
     
      boolean canBuy = (!locType.contains("castle") && !locType.contains("wolfenstein") && carpet != null && carpet.getName().toLowerCase().contains("purple"));
      
      Terrain [] chests = {TerrainBuilder.getTerrainWithName("ITM_D_$Chest"), TerrainBuilder.getTerrainWithName("ITM_I_D_$Chest_locked")};
      double chestProb = 0.1;
      double barrelProb = 0.1;
      if(locType.contains("village"))
      {
         chestProb = 0.2;
         barrelProb = 0.3;
      }
      else if(locType.contains("wolfenstein") || locType.contains("castle") || locType.contains("tower"))
      {
         chestProb = 0.3;
         barrelProb = 0.4;
      }
      else if(locType.contains("fortress") || locType.contains("port"))
      {
         chestProb = 0.4;
         barrelProb = 0.5;
      }
      else if(locType.contains("city"))
      {
         chestProb = 0.5;
         barrelProb = 0.6;
      }
      //TESTING
      barrelProb = 1;//0.1;
   
      if(canBuy)     //people are looking to move out
      {
         chestProb = 0;
         barrelProb = 0;
      }
      Point center = null;       
      Terrain torch = null;
      Terrain window = null;
      if(wall.getName().toLowerCase().contains("red_brick"))
      {
         torch = TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$Red_Brick_Torch");
         window = TerrainBuilder.getTerrainWithName("INR_I_L_1_$Red_Brick_Window");
      }
      else if(wall.getName().toLowerCase().contains("white_brick"))
      {
         torch = TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$White_Brick_Torch"); 
         window = TerrainBuilder.getTerrainWithName("INR_I_L_1_$White_Brick_Window");
      }
      else if(wall.getName().toLowerCase().contains("blue_brick"))
      {
         torch = TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$Blue_Brick_Torch");
         window = TerrainBuilder.getTerrainWithName("INR_I_L_1_$Blue_Brick_Window");
      } 
      else if(wall.getName().toLowerCase().contains("wood_wall"))
      {
         window = TerrainBuilder.getTerrainWithName("INR_I_L_1_$Wood_Wall_Window");
      } 
      if(locType.contains("wolfenstein") || locType.contains("castle"))
         window = null;
      Terrain[]floors = {TerrainBuilder.getTerrainWithName("INR_$Black_floor"), TerrainBuilder.getTerrainWithName("INR_$Blue_floor"), TerrainBuilder.getTerrainWithName("INR_$Red_floor"), TerrainBuilder.getTerrainWithName("INR_$Wood_Plank_floor_inside")};
   
      int size = 20;
      if(locType.contains("port"))
         size = 15;
      if(locType.contains("village"))
      {
         size = 3;
         if(Math.random() < 0.5)
         {
            Terrain[]vfloors = {TerrainBuilder.getTerrainWithName("INR_$Wood_Plank_floor_inside")};
            floor = vfloors[(int)(Math.random()*vfloors.length)];
         }      
      }
      else
         if(locType.contains("wolfenstein") || locType.contains("castle"))
         {
            size = 15;   
         }
         else
            floor = floors[(int)(Math.random()*floors.length)];
   
      if(canBuy)
      {  //this is a house we can possibly buy, so make the floor the same as the carpet argument and set carpet to null so the castle carpet collision code doesn't activate below
         floor = carpet;
         carpet = null;
      }
      
      int row = (int)(Math.random() * (house.length-2)) + 2;
      int col = (int)(Math.random() * (house[0].length-2)) + 2;
      int base = (int)(Math.random() * size) + 4;
      int height = (int)(Math.random() * size) + 4;
      boolean itFits = false;                               //used to pick again if we collide with pavement
      for(int numTries = 0; numTries < 1000 && !itFits; numTries++)
      {
         itFits = true;
         row = (int)(Math.random() *(house.length-2)) + 2;
         col = (int)(Math.random() * (house[0].length-2)) + 2;
         base = (int)(Math.random()* size) + 4;           //horizontal dimension
         height = (int)(Math.random() * size) + 4;        //vertical dimension
      
      //see if we collide with any pavement so we pick again if we do
         String carpetName = "";
         if(carpet != null)
            carpetName = carpet.getName();
         for(int r=row-1; r<=row+height+1; r++)
         {
            for(int c=col-1; c<=col+base+1; c++)
            {
               String curr = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName();
               if(r<2 || c<2 || r>=house.length-2 || c>=house[0].length-2)
               {
                  itFits = false;
                  break;
               }
               if(((locType.contains("wolfenstein") || locType.contains("castle")) && (isImpassable(house, r, c) || curr.equals(carpetName) || !clearOfMoatArea(house, r, c))) 
               || (!locType.contains("castle") && !locType.contains("wolfenstein") && !isBuildableGround(house, r, c)) )
               {
                  itFits = false;
                  break;
               }
            }
            if(!itFits)
               break;
         }
      
         if(itFits)
         {  
            center = new Point(row+height/2, col+base/2);
            placeShoppe(house, row, col, base, height, wall, window, floor, locType);  
         //put in a door
            Terrain [] doors = {TerrainBuilder.getTerrainWithName("INR_D_B_$Wood_door"), TerrainBuilder.getTerrainWithName("INR_I_B_$Wood_door_locked"), TerrainBuilder.getTerrainWithName("INR_D_B_$Iron_door"), TerrainBuilder.getTerrainWithName("INR_I_B_$Iron_door_locked")};
            Terrain door = doors[(int)(Math.random()*doors.length)];
                           
            if(locType.contains("village") || locType.contains("port"))   //only wood doors in villages/ports
               door = doors[(int)(Math.random()*(doors.length-2))];
               
            if(canBuy)           //if we can buy it, make door unlocked
               door = doors[0];  
               
            int side = (int)(Math.random()*4);
            if(side == 0 && row <= 2)                       //make sure we don't put a door on the edge of the map where we can't reach
               side = (int)(Math.random()*3) + 1;        
            else if(side == 1 && col >= house[0].length-3)  //right
            {
               side = (int)(Math.random()*3);
               if(side==1)
                  side = 3;
            }
            else if(side == 2 && row >= house.length-3)     //bottom
            {
               side = (int)(Math.random()*3);
               if(side==2)
                  side = 3;
            }
            else if(side == 3 && col <= 2)                  //left
            {
               side = (int)(Math.random()*3);
            }
         
            if(side==0)       //top
            {
               int minC = col+1;
               int maxC = col+base-1;
               int doorC = (int)(Math.random()*(maxC-minC+1)) + minC;
               house[row][doorC] = door.getValue();
               if(Math.random() < 0.5 && torch!=null)
                  house[row][doorC-1] = torch.getValue();
            }
            else if(side==1)  //right
            {
               int minR = row+1;
               int maxR = row+height-1;
               int doorR = (int)(Math.random()*(maxR-minR+1)) + minR;
               house[doorR][col+base] = door.getValue();
               if(Math.random() < 0.5 && torch!=null)
                  house[doorR-1][col+base] = torch.getValue();
            }
            else if(side==2)  //down
            {
               int minC = col+1;
               int maxC = col+base-1;
               int doorC = (int)(Math.random()*(maxC-minC+1)) + minC;
               house[row+height][doorC] = door.getValue();
               if(Math.random() < 0.5 && torch!=null)
                  house[row+height][doorC-1] = torch.getValue();
            }
            else              //left
            {
               int minR = row+1;
               int maxR = row+height-1;
               int doorR = (int)(Math.random()*(maxR-minR+1)) + minR;
               house[doorR][col] = door.getValue();
               if(Math.random() < 0.5 && torch!=null)
                  house[doorR-1][col] = torch.getValue();
            }
         
         //add some furniture inside of the building
            if(height > 5 || base > 5)
            {
               if((height > 7 || base > 7) && Math.random() < 0.5)
               {
                  int minR = row+2;
                  int maxR = row+height-4;
                  int minC = col+2;
                  int maxC = col+base-4;
                  int randR = (int)(Math.random()*(maxR - minR + 1)) + minR;
                  int randC = (int)(Math.random()*(maxC - minC + 1)) + minC;
                  int randHeight = (int)(Math.random() * (height - 3) ) + 3;
                  int randBase = (int)(Math.random() * (base - 3) ) + 3;
               
                  itFits = false;                               //used to pick again if we collide with pavement
                  for(numTries = 0; numTries < 1000 && !itFits; numTries++)
                  {
                     itFits = true;
                     randR = (int)(Math.random()*(maxR - minR + 1)) + minR;
                     randC = (int)(Math.random()*(maxC - minC + 1)) + minC;
                     randHeight = (int)(Math.random() * (height - 3) ) + 3;
                     randBase = (int)(Math.random() * (base - 3) ) + 3;
                  
                     for(int r=randR-1; r<=randR+randHeight+1; r++)
                     {
                        for(int c=randC-1; c<=randC+randBase+1; c++)
                        {
                           if(r<2 || c<2 || r>=house.length-2 || c>=house[0].length-2)
                           {
                              itFits = false;
                              break;
                           }
                           Terrain terr = CultimaPanel.allTerrain.get(Math.abs(house[r][c]));
                           if(terr.getName().contains("_I_"))
                           {
                              itFits = false;
                              break;
                           }
                        }
                        if(!itFits)
                           break;
                     }
                  }
                  if(itFits)
                  {  
                     placeRoom(house, randR, randC, randBase, randHeight, wall, null, 1); 
                     int doorTop = (int)(Math.random()*2);
                     int doorRight = (int)(Math.random()*2);
                     int doorBottom = (int)(Math.random()*2);
                     int doorLeft = (int)(Math.random()*2);
                     if(doorTop == 0)
                     {
                        minC = randC+1;
                        maxC = randC+randBase-1;
                        int doorC = (int)(Math.random()*(maxC-minC+1)) + minC;
                        if(Math.random() < 0.5)
                           house[randR][doorC] = door.getValue();
                        else
                           house[randR][doorC] = floor.getValue(); 
                     } 
                     if(doorRight == 0)
                     {
                        minR = randR+1;
                        maxR = randR+randHeight-1;
                        int doorR = (int)(Math.random()*(maxR-minR+1)) + minR;
                        if(Math.random() < 0.5)
                           house[doorR][randC+randBase] = door.getValue(); 
                        else
                           house[doorR][randC+randBase] = floor.getValue(); 
                     }
                     if(doorBottom == 0)
                     {
                        minC = randC+1;
                        maxC = randC+randBase-1;
                        int doorC = (int)(Math.random()*(maxC-minC+1)) + minC;
                        if(Math.random() < 0.5)
                           house[randR+randHeight][doorC] = door.getValue(); 
                        else
                           house[randR+randHeight][doorC] = floor.getValue(); 
                     }
                     if(doorLeft == 0 || (doorTop==1 && doorRight==1 && doorBottom==1))
                     {
                        minR = randR+1;
                        maxR = randR+randHeight-1;
                        int doorR = (int)(Math.random()*(maxR-minR+1)) + minR;
                        if(Math.random() < 0.5)
                           house[doorR][randC] = door.getValue(); 
                        else
                           house[doorR][randC] = floor.getValue(); 
                     }
                  }
               }
               else
               {
                  boolean vertWall = false;
                  if(base > height)
                     vertWall = true;
                  if(vertWall)
                  {
                     int minCol = col+2;
                     int maxCol = col+base-2;
                     int wallCol = (int)(Math.random()*(maxCol-minCol+1)) + minCol;
                     numTries = 0;
                     boolean failed = false;
                     while(CultimaPanel.allTerrain.get(Math.abs(house[row][wallCol])).getName().contains("door") || CultimaPanel.allTerrain.get(Math.abs(house[row+height][wallCol])).getName().contains("door"))
                     {
                        wallCol = (int)(Math.random()*(maxCol-minCol+1)) + minCol;
                        numTries++;
                        if(numTries >= 1000)
                        {
                           failed = true;
                           break;
                        }
                     }
                     if(!failed)
                     {
                        int minRow = row+1;
                        int maxRow = row+height-1;
                        int openingRow = (int)(Math.random()*(maxRow-minRow+1)) + minRow;
                        for(int r=minRow; r<=maxRow; r++)
                        {
                           if(r!=openingRow)
                              house[r][wallCol] = wall.getValue();
                           else if(Math.random() < 0.5)
                              house[r][wallCol] = TerrainBuilder.getTerrainWithName("INR_D_B_$Wood_door").getValue();
                        }
                     }
                  }
                  else     //horiz wall
                  {
                     int minRow = row+2;
                     int maxRow = row+height-2;
                     int wallRow = (int)(Math.random()*(maxRow-minRow+1)) + minRow;
                     numTries = 0;
                     boolean failed = false;
                     while(CultimaPanel.allTerrain.get(Math.abs(house[wallRow][col])).getName().contains("door") || CultimaPanel.allTerrain.get(Math.abs(house[wallRow][col+base])).getName().contains("door"))
                     {
                        wallRow = (int)(Math.random()*(maxRow-minRow+1)) + minRow;
                        numTries++;
                        if(numTries >= 1000)
                        {
                           failed = true;
                           break;
                        }
                     }
                     if(!failed)
                     {
                        int minCol = col+1;
                        int maxCol = col+base-1;
                        int openingCol = (int)(Math.random()*(maxCol-minCol+1)) + minCol;
                        for(int c=minCol; c<=maxCol; c++)
                        {
                           if(c!=openingCol)
                              house[wallRow][c] = wall.getValue();
                           else if(Math.random() < 0.5)
                              house[wallRow][c] = TerrainBuilder.getTerrainWithName("INR_D_B_$Wood_door").getValue();
                        }
                     }
                  
                  }
               }
            }
         } 
      }
      if(itFits && Math.random() < chestProb)                    //maybe put a chest in a corner or the center
      {
         int place = (int)(Math.random()*5);
         if(place == 0 && !nextToADoor(house, row+1, col+1))
         {
            if(!outOfBounds(house, row+1, col+1))
            {
               if(Math.random() < 0.9 || locType.contains("wolfenstein"))
                  house[row+1][col+1] = chests[(int)(Math.random()*chests.length)].getValue();
               else
                  house[row+1][col+1] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
            }
         }
         else if(place == 1 && !nextToADoor(house, row+1, col+base-1))
         {
            if(!outOfBounds(house, row+1, col+base-1))
            {
               if(Math.random() < 0.9 || locType.contains("wolfenstein"))
                  house[row+1][col+base-1] = chests[(int)(Math.random()*chests.length)].getValue();
               else
                  house[row+1][col+base-1] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
            }
         }
         else if(place == 2 && !nextToADoor(house, row+height-1, col+1))
         {
            if(!outOfBounds(house, row+height-1, col+1))
            {
               if(Math.random() < 0.9 || locType.contains("wolfenstein"))
                  house[row+height-1][col+1] = chests[(int)(Math.random()*chests.length)].getValue();
               else
                  house[row+height-1][col+1] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
            }
         }
         else if(place == 3 && !nextToADoor(house, row+height-1, col+base-1))
         {
            if(!outOfBounds(house, row+height-1, col+base-1))
            {
               if(Math.random() < 0.9 || locType.contains("wolfenstein"))
                  house[row+height-1][col+base-1] = chests[(int)(Math.random()*chests.length)].getValue();
               else
                  house[row+height-1][col+base-1] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
            }
         }
         else
         {
            if(!outOfBounds(house, row+height/2,col+base/2))
            {
               if(Math.random() < 0.9 || locType.contains("wolfenstein"))
                  house[row+height/2][col+base/2] = chests[(int)(Math.random()*chests.length)].getValue();
               else
                  house[row+height/2][col+base/2] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
            }
         }
      }
       
      double bedProb = 0.5;
      if(locType.contains("village"))
      {
         bedProb = 0.2;
      }
      else if(locType.contains("wolfenstein") || locType.contains("castle") || locType.contains("tower"))
      {
         bedProb = 0.2;
      }
      else if(locType.contains("fortress") || locType.contains("port"))
      {
         bedProb = 0.6;
      }
      else if(locType.contains("city"))
      {
         bedProb = 0.7;
      }
      
      if(canBuy)
         bedProb = 1;
      
      int bedRow = -1;
      int bedCol = -1;
      int bed2Row = -1;    //2nd bed for house we can buy in case we get married
      int bed2Col = -1;
   
      Terrain bed = TerrainBuilder.getTerrainWithName("INR_$Bed");
      if(itFits && Math.random() < bedProb)                    //maybe put a bed in a corner or the center
      {
         int place = (int)(Math.random()*5);
         if(place == 0 && !nextToADoor(house, row+1, col+1) && !chestAt(house, row+1, col+1))
         {
            if(!outOfBounds(house, row+1, col+1))           //upper-left corner
            {
               bedRow = row+1;
               bedCol = col+1;
               house[row+1][col+1] = bed.getValue();
               if(canBuy)
               {
                  if(!nextToADoor(house, row+1, col+2) && !outOfBounds(house, row+1, col+2)) 
                  {
                     bed2Row = row+1;
                     bed2Col = col+2;
                     house[row+1][col+2] = bed.getValue();
                  }
               }
            }
         }
         else if(place == 1 && !nextToADoor(house, row+1, col+base-1) && !chestAt(house, row+1, col+base-1))
         {
            if(!outOfBounds(house, row+1, col+base-1))      //upper-right corner
            {
               bedRow = row+1;
               bedCol = col+base-1;
               house[row+1][col+base-1] = bed.getValue();
               if(canBuy)
               {
                  if(!nextToADoor(house, row+1, col+base-2) && !outOfBounds(house, row+1, col+base-2)) 
                  {
                     bed2Row = row+1;
                     bed2Col = col+base-2;
                     house[row+1][col+base-2] = bed.getValue();
                  }
               }
            }
         }
         else if(place == 2 && !nextToADoor(house, row+height-1, col+1) && !chestAt(house, row+height-1, col+1))
         {
            if(!outOfBounds(house, row+height-1, col+1))    //lower-left corner
            {
               bedRow = row+height-1;
               bedCol = col+1;
               house[row+height-1][col+1] = bed.getValue();
               if(canBuy)
               {
                  if(!nextToADoor(house, row+height-1, col+2) && !outOfBounds(house, row+height-1, col+2))
                  {
                     bed2Row = row+height-1;
                     bed2Col = col+2;
                     house[row+height-1][col+2] = bed.getValue();
                  }
               }
            }
         }
         else if(place == 3 && !nextToADoor(house, row+height-1, col+base-1) && !chestAt(house, row+height-1, col+base-1))
         {
            if(!outOfBounds(house, row+height-1, col+base-1))  //lower-right corner
            {
               bedRow = row+height-1;
               bedCol = col+base-1;
               house[row+height-1][col+base-1] = bed.getValue();
               if(canBuy)
               {
                  if(!nextToADoor(house, row+height-1, col+base-2) && !outOfBounds(house, row+height-1, col+base-2))
                  {
                     bed2Row = row+height-1;
                     bed2Col = col+base-2;
                     house[row+height-1][col+base-2] = bed.getValue();
                  }
               }
            }
         }
         else if (!nextToADoor(house, row+height/2, col+base/2) && !chestAt(house, row+height/2, col+base/2))
         {
            if(!outOfBounds(house, row+height/2, col+base/2))  //in the middle of the house
            {
               bedRow = row+height/2;
               bedCol = col+base/2;
               house[row+height/2][col+base/2] = bed.getValue();
               if(canBuy)
               {
                  if (!nextToADoor(house, row+height/2, col+base/2-1) && !outOfBounds(house, row+height/2,col+base/2-1))
                  {
                     bed2Row = row+height/2;
                     bed2Col = col+base/2-1;
                     house[row+height/2][col+base/2-1] = bed.getValue();
                  }
                  else if (!nextToADoor(house, row+height/2, col+base/2+1) && !outOfBounds(house, row+height/2,col+base/2+1))
                  {
                     bed2Row = row+height/2;
                     bed2Col = col+base/2+1;
                     house[row+height/2][col+base/2+1] = bed.getValue();
                  }
               }
            }
         }
      }
      if(canBuy && bedRow == -1 && bedCol == -1)
      {
         if(!outOfBounds(house, row+height/2, col+base/2))
         {
            bedRow = row+height/2;
            bedCol = col+base/2;
            house[row+height/2][col+base/2] = bed.getValue();
            if(canBuy)
            {
               if (!nextToADoor(house, row+height/2, col+base/2-1) && !outOfBounds(house, row+height/2,col+base/2-1))
               {
                  bed2Row = row+height/2;
                  bed2Col = col+base/2-1;
                  house[row+height/2][col+base/2-1] = bed.getValue();
               }
               else if (!nextToADoor(house, row+height/2, col+base/2+1) && !outOfBounds(house, row+height/2,col+base/2+1))
               {
                  bed2Row = row+height/2;
                  bed2Col = col+base/2+1;
                  house[row+height/2][col+base/2+1] = bed.getValue();
               }
            }
         }
      
      }
      //maybe make this a coffin maker's shop
      Terrain coffin = TerrainBuilder.getTerrainWithName("INR_$Coffin_bed");
      if(itFits && bedRow==-1 && bedCol==-1 && Math.random()<0.2 && !canBuy && !locType.contains("wolfenstein"))
      {
         for(int place = 0; place < 5; place++)
         {
            if(place == 0 && !nextToADoor(house, row+1, col+1) && !chestAt(house, row+1, col+1))
            {
               if(!outOfBounds(house, row+1, col+1))
                  house[row+1][col+1] = coffin.getValue();
            }
            else if(place == 1 && !nextToADoor(house, row+1, col+base-1) && !chestAt(house, row+1, col+base-1))
            {
               if(!outOfBounds(house, row+1, col+base-1))
                  house[row+1][col+base-1] = coffin.getValue();
            }
            else if(place == 2 && !nextToADoor(house, row+height-1, col+1) && !chestAt(house, row+height-1, col+1))
            {
               if(!outOfBounds(house, row+height-1, col+1))
                  house[row+height-1][col+1] = coffin.getValue();
            }
            else if(place == 3 && !nextToADoor(house, row+height-1, col+base-1) && !chestAt(house, row+height-1, col+base-1))
            {
               if(!outOfBounds(house, row+height-1, col+base-1))
                  house[row+height-1][col+base-1] = coffin.getValue();
            }
            else if (!chestAt(house, row+height/2, col+base/2) && !nextToADoor(house, row+height/2, col+base/2))
            {
               if(!outOfBounds(house, row+height/2,col+base/2))
                  house[row+height/2][col+base/2] = coffin.getValue();
            }
         }
      }
      
      if(itFits && Math.random() < barrelProb)                    //maybe put a barrel in a corner or the center
      {
         if(Math.random()<0.2 && !outOfBounds(house, row+1, col+1) && !nextToADoor(house, row+1, col+1) && !isImpassable(house, row+1, col+1))
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(house[row+1][col+1])).getName().toLowerCase();
            if(!current.contains("chest") && !current.contains("bed") && !current.contains("book") && !current.contains("coffin"))
            {
               if(!Utilities.NPCAt(row+1, col+1, mapIndex))    
                  CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.BARREL, row+1, col+1, mapIndex, locType));
            }
         }
         if(Math.random()<0.2 && !outOfBounds(house, row+1, col+base-1) && !nextToADoor(house, row+1, col+base-1) && !isImpassable(house, row+1, col+base-1))
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(house[row+1][col+base-1])).getName().toLowerCase();
            if(!current.contains("chest") && !current.contains("bed") && !current.contains("book") && !current.contains("coffin"))
            {
               if(!Utilities.NPCAt(row+1, col+base-1, mapIndex))    
                  CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.BARREL, row+1, col+base-1, mapIndex, locType));
            }
         }
         if(Math.random()<0.2 && !outOfBounds(house, row+height-1, col+1) && !nextToADoor(house, row+height-1, col+1) && !isImpassable(house, row+height-1, col+1))
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(house[row+height-1][col+1])).getName().toLowerCase();
            if(!current.contains("chest") && !current.contains("bed") && !current.contains("book") && !current.contains("coffin"))
            {
               if(!Utilities.NPCAt(row+height-1, col+1, mapIndex))    
                  CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.BARREL, row+height-1, col+1, mapIndex, locType));
            }
         }
         if(Math.random()<0.2 && !outOfBounds(house, row+height-1, col+base-1) && !nextToADoor(house, row+height-1, col+base-1) && !isImpassable(house, row+height-1, col+base-1))
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(house[row+height-1][col+base-1])).getName().toLowerCase();
            if(!current.contains("chest") && !current.contains("bed") && !current.contains("book") && !current.contains("coffin"))
            {
               if(!Utilities.NPCAt(row+height-1, col+base-1, mapIndex))    
                  CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.BARREL, row+height-1, col+base-1, mapIndex, locType));
            }
         }
         if(Math.random()<0.2 && !outOfBounds(house, row+height/2,col+base/2) && !nextToADoor(house, row+height/2, col+base/2) && !isImpassable(house, row+height/2, col+base/2))
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(house[row+height/2][col+base/2])).getName().toLowerCase();
            if(!current.contains("chest") && !current.contains("bed") && !current.contains("book") && !current.contains("coffin"))
            {
               if(!Utilities.NPCAt(row+height/2, col+base/2, mapIndex))    
                  CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.BARREL, row+height/2, col+base/2, mapIndex, locType));
            }
         }
      }
   
      if(itFits)     //add a person into the room
      {
         int numPeople = (int)(Math.random()*2)+1;
         if(canBuy)
            numPeople = 1;
         for(int i=0; i<numPeople; i++)
         {
            byte charIndex = NPC.randCivilian();
            if(locType.contains("castle") || locType.contains("tower"))
               charIndex = NPC.randCastleCivilian();
            if(locType.contains("wolfenstein"))
               charIndex = NPC.randomWolfensteinMonster();
            boolean goodSpot = true; 
            if(carpet == null)
               carpet = floor;
            int r = Player.rollDie(row+1, row+height-1);
            int c =  Player.rollDie(col+1, col+base-1); 
            if(bedRow!=-1 && bedCol!=-1 && Math.random() < 0.75 && !canBuy) //place people on their bed because that is where they return at night
            {
               r = bedRow;
               c = bedCol;
            }
            int numTries = 0;
            while(isImpassable(house, r, c) || (house[r][c] != floor.getValue() && house[r][c] != carpet.getValue() && house[r][c] != bed.getValue())
            || (occupants.size()>0 && occupants.get(0).getRow()==r && occupants.get(0).getCol()==c)
            || (canBuy && house[r][c] == bed.getValue()))
            {
               r = Player.rollDie(row+1, row+height-1);
               c =  Player.rollDie(col+1, col+base-1);  
               numTries++;
               if(numTries >= 1000)
               {
                  goodSpot = false;
                  break;
               }
            }
            if(goodSpot)
            {
               NPCPlayer toAdd = new NPCPlayer(charIndex, r, c, mapIndex, r, c, locType);
               if(canBuy)
               {
                  toAdd.setNumInfo(10);
                  toAdd.setMoveType(NPC.STILL);  
               }
               if(locType.contains("wolfenstein"))
               {
                  if(Math.random() < 0.25)
                     occupants.add(toAdd);
               }
               else
                  occupants.add(toAdd);
               if(!locType.equals("castle") && !locType.equals("tower") && !locType.equals("wolfenstein"))
               {
                  if(mapIndex >= CultimaPanel.civilians.size())
                     CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
                  if(!Utilities.NPCAt(r, c, mapIndex))    
                     CultimaPanel.civilians.get(mapIndex).add(toAdd);
               }
            }
         }
      }
      ans[0] = occupants;
      ans[1] = center;
      return ans;
   }

//builds a random maze at (row,col) of dimensions (numRows, numCols) and returns two Points of the start and end coordinates
   public static Point[] buildMaze(byte[][]house, Terrain wall1, Terrain wall2, Terrain floor, int row, int col, int numRows, int numCols)
   {
      if(row+numRows >= house.length -1 || col + numCols >= house[0].length-1)
         return null;
      Point[] startAndEnd = new Point[2];
   
      String[][]array = Maze. buildMaze(numRows, numCols);
      for(int r=0; r<array.length; r++)
      {
         for(int c=0; c<array[0].length; c++)
         {
            String curr = array[r][c];
            if(curr.equals("S"))
            {
               startAndEnd[0] = new Point(r,c);
               house[r+row][c+col] = floor.getValue();
            }
            else if(curr.equals("E"))
            {
               startAndEnd[1] = new Point(r,c);
               house[r+row][c+col] = floor.getValue();
            }
            else if(curr.equals(" "))
            {
               house[r+row][c+col] = floor.getValue();
            }
            else if(curr.equals("+"))
            {
               house[r+row][c+col] = wall1.getValue();
            }
            else //if(curr.equals("+"))
            {
               house[r+row][c+col] = wall2.getValue();
            }
         }
      }
      if(startAndEnd[0]!=null && startAndEnd[1]!=null)
         return startAndEnd;
      return null;
   }

//returns true if we are allowed to build on this spot, because it is not paved and there isn't an indoor Terrain there
   public static boolean isBuildableGround(Terrain terr)
   {
      return (terr.getName().contains("TER") && !terr.getName().contains("_I_") && !terr.getName().contains("water"));
   }

   public static boolean isBuildableGround(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return false;
      Terrain terr = CultimaPanel.allTerrain.get(Math.abs(house[r][c]));
      return isBuildableGround(terr);
   }

   public static boolean isImpassable(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return true;
      Terrain terr = CultimaPanel.allTerrain.get(Math.abs(house[r][c]));
      return (terr.getName().contains("_I_"));
   }
   
   //true if house[r][c] is a passable spot that is not water filled
   public static boolean isDryLand(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return false;
      String terr = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().toLowerCase();
      return (!terr.contains("_i_") && !terr.contains("water") && !terr.contains("_d_"));
   }

   public static boolean isBreakable(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return false;
      Terrain terr = CultimaPanel.allTerrain.get(Math.abs(house[r][c]));
      return (terr.getName().contains("_D_"));
   }
   
   public static boolean isBreakableWall(String terr)
   {
      terr = terr.toLowerCase();
      return (terr.contains("_d_") && (terr.contains("wall") || terr.contains("rock")));
   }
   
      //returns true if the terrain is one that can be destroyed by a hammer or Phasewall spell
   public static boolean isHammerBreakable(Terrain t)
   {
      String n = t.getName().toLowerCase();
      if(n.contains("_i_") && (n.contains("wall") || n.contains("brick") || n.contains("window") || n.contains("torch") || n.contains("rock") || n.contains("door") || n.contains("counter") || n.contains("chest") || n.contains("book")))
         return true;
      if(n.contains("_d_"))    //anything destroyable can be breakable by hammer
         return true;   
      return false;
   }
  
   public static boolean isHammerBreakable(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return false;
      return isHammerBreakable(CultimaPanel.allTerrain.get(Math.abs(house[r][c])));
   }

//looks to see if the terrain at a specific location is combustable
   public static boolean isCombustable(byte[][]map, int r, int c)
   {
      if(outOfBounds(map, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(map[r][c])).getName().toLowerCase();
      if((current.contains("forest") && !current.contains("dead"))
         || current.contains("tile")|| current.contains("straw")  || current.contains("wood") 
         || current.contains("bed") || current.contains("coffin") || current.contains("counter") || current.contains("sign"))
         return true;   
      return false;
   }
   
   //looks to see if the terrain at a specific location is combustable structure
   public static boolean isCombustableStructure(byte[][]map, int r, int c)
   {
      if(outOfBounds(map, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(map[r][c])).getName().toLowerCase();
      if((current.contains("tile") || current.contains("straw") || current.contains("wood")) && current.contains("_i_"))
         return true;   
      return false;
   }
   
   public static boolean isCombustable(String current)
   {
      current = current.toLowerCase();
      if((current.contains("forest") && !current.contains("dead"))
         || current.contains("tile")|| current.contains("straw")  || current.contains("wood") 
         || current.contains("bed") || current.contains("coffin") || current.contains("counter") || current.contains("sign"))
         return true;   
      return false;
   }
   
   //looks to see if the terrain at a specific location is a wood door (for axe breaking)
   public static boolean isWoodDoor(byte[][]map, int r, int c)
   {
      if(outOfBounds(map, r, c))
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(map[r][c])).getName().toLowerCase();
      return (current.contains("wood_door"));
   }

//returns true if we can not attack through this tile
   public static boolean isBlocking(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return true;
      Terrain terr = CultimaPanel.allTerrain.get(Math.abs(house[r][c]));
      String name = terr.getName().toLowerCase();
      return ((name.contains("_i_") && name.contains("_b_")) || (name.contains("door") && !name.contains("night_door")) || (name.contains("_i_") && CultimaPanel.player.isInArena()));
   }
   
    //returns true if we can not talk through this tile
   public static boolean isBlockingToTalk(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return true;
      Terrain terr = CultimaPanel.allTerrain.get(Math.abs(house[r][c]));
      String name = terr.getName().toLowerCase();
      return ((name.contains("_i_") && name.contains("_b_")) || (name.contains("door") && !name.contains("night_door")));
   }

   public static boolean outOfBounds(byte[][]house, int r, int c)
   {
      if(r<0 || c<0 || r>=house.length || c>=house[0].length)
         return true;
      return false;
   }

   public static void fillSquare(byte[][]house, int size, Terrain t)
   {
      int start=house.length/2 - size/2, end=house.length/2 + size/2;
      for(int r=start; r<=end; r++)
         for(int c=start; c<=end; c++)
            house[r][c] = t.getValue();
   }

   //returns true if house[row][col] is next to a place with the terrain type terr
   public static boolean nextTo(byte[][]house, int row, int col, Terrain terr)
   {
      for(int r=row-1; r<=row+1; r++)
         for(int c=col-1; c<=col+1; c++)
         {
            if(r<0 || c<0 || r>=house.length || c>=house[0].length)
               continue;
            if(Math.abs(house[r][c]) == Math.abs(terr.getValue()))
               return true;
         }
      return false;
   }

   public static void buildArena(byte[][]house, int randSide, int mapIndex)
   {
      NPCPlayer[][] occupants = new NPCPlayer[house.length][house[0].length];    //fill with occupants to add to the civilians ArrayList after we complete and possilby rotate the map
      Terrain wall = TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick");
      Terrain wallTorch = TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$White_Brick_Torch");
      Terrain floor = TerrainBuilder.getTerrainWithName("INR_$Blue_floor");
      Terrain seats = TerrainBuilder.getTerrainWithName("INR_I_L_2_$Counter_lighted");
      Terrain [] grounds = {TerrainBuilder.getTerrainWithName("TER_$Sand"), TerrainBuilder.getTerrainWithName("TER_$Dry_grass"), TerrainBuilder.getTerrainWithName("INR_$Blue_Brick_floor")};
      Terrain ground = grounds[(int)(Math.random()*grounds.length)];
      Terrain nightDoor = TerrainBuilder.getTerrainWithName("INR_$Night_door_floor");
      Terrain monsterSpawn =  TerrainBuilder.getTerrainWithName("INR_$Red_floor");
      Terrain signTav = TerrainBuilder.getTerrainWithName("INR_I_L_3_$Sign_tavern");
      Terrain signArm = TerrainBuilder.getTerrainWithName("INR_I_L_3_$Sign_armory");
      Terrain signMag = TerrainBuilder.getTerrainWithName("INR_I_L_3_$Sign_magic");
      Terrain signRat = TerrainBuilder.getTerrainWithName("INR_I_L_3_$Sign_rations");
      Terrain [] doorsLocked = {TerrainBuilder.getTerrainWithName("INR_I_B_$Wood_door_locked"), TerrainBuilder.getTerrainWithName("INR_I_B_$Iron_door_locked")};
      Terrain door = doorsLocked[(int)(Math.random()*doorsLocked.length)];
      int fieldSize = house.length / 2;
      int borderSize = Player.rollDie(1,6);
      int pathWidth = (int)(Math.random()*4) + 2;
      int arenaSize = house.length - fieldSize - borderSize;
      fillSquare(house, house.length-(borderSize*2), wall);    //place outer wall
      int size = house.length-(borderSize*2)-2;
      for(int i=0; i<arenaSize; i++)                           //place seats
      {
         if(i%2==0)
            fillSquare(house, size, floor);
         else
            fillSquare(house, size, seats);
         size-=2;   
      }
      fillSquare(house, fieldSize, ground);                    //place arena ground
      //place strip for monster spawns
      int start=house[0].length/2 - fieldSize/2 + 1;
      int end=house[0].length/2 + fieldSize/2 - 1;
      for(int c=start; c<=end; c++)
         house[house.length/2-fieldSize/2+1][c] = monsterSpawn.getValue();
      if(Math.random() < 0.5)
         for(int c=start; c<=end; c++)
            if(c%2==0)
               house[house.length/2-fieldSize/2+2][c] = monsterSpawn.getValue();
      if(Math.random() < 0.5)
         for(int c=start; c<=end; c++)
            house[house.length/2-fieldSize/2+3][c] = monsterSpawn.getValue();
         
      //place entrance into arena
      start = house.length/2 - pathWidth/2 - 1;
      end = house.length/2 + pathWidth/2 + 1;
      for(int r=0; r<house.length; r++)
         for(int c=start; c<=end; c++)
         {
            if(c==start || c==end)                                //place tunnel walls around entrance and exit path
            {
               if(house[r][c] != nightDoor.getValue() && house[r][c] != ground.getValue() && r>borderSize && r<house.length-borderSize)
                  house[r][c] = wall.getValue();
            }
            else
            {
               if(r==house.length-borderSize-1 || r==borderSize)  
                  house[r][c] = nightDoor.getValue();
               else if(house[r][c] != nightDoor.getValue())
                  house[r][c] = ground.getValue(); 
            }
         }  
      //maybe put columns down the path
      int columnSize = (int)(Math.random()*2)+1;
      boolean columns = false;
      int columnPeriod = Player.rollDie(6,12);
      if(Math.random() <  0.5)
         columns = true;
      //maybe put torches on the columns   
      boolean columnTorch = false;   
      if(Math.random() < 0.5)        
         columnTorch = true;
      int pathLength = house.length - Player.rollDie(0,borderSize+arenaSize);
      int center = (house.length)/2;
      boolean statues = false;
      if(Math.random() <  0.75)
         statues = true;
      boolean statuePlacedLeft = false;   
      boolean statuePlacedRight = false;   
   
      byte statueType = NPC.randArenaStatue();
      for(int r=house.length-1; r>=house.length-1-pathLength && r>=0; r--)
         for(int c = center - pathWidth/2 - 1; c<= center + pathWidth/2 + 1; c++)
         {
            if(c == center - pathWidth/2 - 1 || c == center + pathWidth/2 + 1)
            {
               if(!outOfBounds(house, r, c))
               {
                  if(r%columnPeriod == 0 && columns)
                  {
                     if(c == center - pathWidth/2 - 1)
                     {
                        if(columnTorch)
                           house[r][c] = wallTorch.getValue();
                        else
                           house[r][c] = wall.getValue();
                        if(columnSize == 2)
                        {
                           if(!outOfBounds(house, r-1, c-1))
                              house[r-1][c-1] = wall.getValue();
                           if(!outOfBounds(house, r-1, c))
                              house[r-1][c] = wall.getValue();
                        }
                        if(statues && !statuePlacedLeft && !isImpassable(house, r, c-1) && r>=house.length-borderSize)
                        {
                           NPCPlayer statue = new NPCPlayer(statueType, r, c-1, mapIndex, "arena");
                           statue.setNumInfo(-999);
                           occupants[r][c-1] = statue;
                           statuePlacedLeft = true;
                        }
                     }
                     else if(c == center + pathWidth/2 + 1)
                     {
                        if(columnTorch)
                           house[r][c] = wallTorch.getValue();
                        else
                           house[r][c] = wall.getValue();
                        if(columnSize == 2)
                        {
                           if(!outOfBounds(house, r-1, c+1))
                              house[r-1][c+1] = wall.getValue();
                           if(!outOfBounds(house, r-1, c))
                              house[r-1][c] = wall.getValue();
                        }
                        if(statues && !statuePlacedRight && !isImpassable(house, r, c+1) && r>=house.length-borderSize)
                        {
                           NPCPlayer statue = new NPCPlayer(statueType, r, c+1, mapIndex, "arena");
                           statue.setNumInfo(-999);
                           occupants[r][c+1] = statue;
                           statuePlacedRight = true;
                        }
                     }
                  }
               }
            }
         }  
      //add spectators
      int numSpectators = (arenaSize * arenaSize)/8;
      ArrayList<Point> specSpawns = new ArrayList<Point>();
      for(int r=borderSize; r<=house.length-borderSize; r++)
         for(int c=borderSize; c<=house[0].length-borderSize; c++)
            if(house[r][c] == floor.getValue() && !nextTo(house, r, c, ground) && !nextTo(house, r, c, nightDoor))
               specSpawns.add(new Point(r,c));
      for(int i=0; i<numSpectators; i++)
      {
         Point specLoc = specSpawns.get((int)(Math.random()*specSpawns.size()));
         int specR = (int)(specLoc.getX());
         int specC = (int)(specLoc.getY());
         if(occupants[specR][specC]==null)
         {
            NPCPlayer spectator = new NPCPlayer(NPC.randArenaSpectator(), specR, specC, mapIndex, "arena");
            spectator.setNumInfo(0);
            occupants[specR][specC]=spectator;
         }
      }
      //add arena master
      int amRow = house.length/2 + fieldSize/2 - 1;    //location of arena master
      int amCol = house[0].length/2 - pathWidth/2 - 2;
      //surround the arena master with a barrier for safety
      for(int r=amRow-1; r<=amRow+2; r++)
         for(int c=amCol-1; c<=amCol+1; c++)
            house[r][c] = seats.getValue();
      house[amRow][amCol] = floor.getValue();
      byte arenaMasterType = NPC.randomArenaMaster();
      NPCPlayer arenaMaster = new NPCPlayer(arenaMasterType, amRow, amCol, mapIndex, "arena");
      String namePrefix = "Arenamaster";
      arenaMaster.setName(namePrefix + " " + arenaMaster.getName());
      arenaMaster.setNumInfo(1);
      occupants[amRow][amCol] = arenaMaster; 
      //place door to close when battle starts
      for(int c=house[0].length/2-pathWidth/2; c<=house[0].length/2+pathWidth/2 && c<house[0].length && c>=0; c++)
      {
         house[house.length/2-fieldSize/2-1][c] = nightDoor.getValue();
         house[amRow+2][c] = nightDoor.getValue();  
      }
      int guardRow = -1, guardCol = -1;
      
      guardRow = house.length/2-fieldSize/2-2;
      guardCol = house[0].length/2-pathWidth/2;
      if(!isImpassable(house,  guardRow, guardCol) && Math.random()<0.75)
      {
         NPCPlayer guard1 = new NPCPlayer(NPC.randGuard(), guardRow, guardCol, mapIndex, "arena");
         occupants[guardRow][guardCol] = guard1;
      }
      
      guardRow = house.length/2-fieldSize/2-2;
      guardCol = house[0].length/2-pathWidth/2 + pathWidth-1;
      if(!isImpassable(house,  guardRow, guardCol) && Math.random()<0.5)
      {
         NPCPlayer guard2 = new NPCPlayer(NPC.randGuard(), guardRow, guardCol, mapIndex, "arena");
         occupants[guardRow][guardCol] = guard2;
      }
      
      guardRow = amRow+3;
      guardCol = house[0].length/2-pathWidth/2;
      if(!isImpassable(house,  guardRow, guardCol) && Math.random()<0.75)
      {
         NPCPlayer guard3 = new NPCPlayer(NPC.randGuard(), guardRow, guardCol, mapIndex, "arena");
         occupants[guardRow][guardCol] = guard3;
      }
   
      guardRow = amRow+3;
      guardCol = house[0].length/2-pathWidth/2 + pathWidth-1;
      if(!isImpassable(house,  guardRow, guardCol) && Math.random()<0.5)
      {
         NPCPlayer guard4 = new NPCPlayer(NPC.randGuard(), guardRow, guardCol, mapIndex, "arena");
         occupants[guardRow][guardCol] = guard4;
      }
    
      //add shoppes at entrance
      boolean armory=false, tavern=false, rations=false, magic=false;
      for(int r=house.length-(borderSize+2)-4; r<=house.length-(borderSize+2) && r>=0; r++)
         for(int c=center - (pathWidth/2) - 4; c<=center + (pathWidth/2) + 4; c++)
            if(house[r][c] != ground.getValue())
            {  
               byte shopkeepIndex = NPC.MAN; 
               if(c==center-(pathWidth/2) -1 && r==house.length-(borderSize+2)-4 && Math.random()<0.5 && !tavern)      //upper-left shoppe - tavern
               {
                  tavern = true;
                  house[r][c] = signTav.getValue();
                  namePrefix = "Barkeep";
                  int rand = (int)(Math.random()*2);
                  if(rand==1)
                  {
                     shopkeepIndex = NPC.WOMAN;
                     namePrefix = "Madame Barkeep"; 
                  }
                  NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, house.length-(borderSize+2)-3, center-(pathWidth/2)-2, mapIndex, house.length-(borderSize+2)-3, center-(pathWidth/2)-2, "arena"));
                  int ofLoc = shopKeep.getName().indexOf(" of ");
                  if(ofLoc != -1)
                     shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                  shopKeep.setName(namePrefix + " " + shopKeep.getName());
                  occupants[house.length-(borderSize+2)-3][center-(pathWidth/2)-2] = shopKeep;
               }
               else if(c==center-(pathWidth/2) -1 && r==house.length-(borderSize+2) && Math.random()<0.5 && !rations)   //lower-left - rations
               {
                  rations = true;
                  house[r][c] = signRat.getValue();
                  namePrefix = "Butcher";
                  int rand = (int)(Math.random()*2);
                  if(rand==1)
                  {
                     shopkeepIndex = NPC.WOMAN;
                     namePrefix = "Madame Baker";
                  }
                  NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, house.length-(borderSize+2)-1, center-(pathWidth/2), mapIndex, house.length-(borderSize+2)-3, center-(pathWidth/2)-2, "arena"));
                  int ofLoc = shopKeep.getName().indexOf(" of ");
                  if(ofLoc != -1)
                     shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                  shopKeep.setName(namePrefix + " " + shopKeep.getName());
                  occupants[house.length-(borderSize+2)-1][center-(pathWidth/2)-2] = shopKeep;
               }
               else if(c==center+(pathWidth/2) +1 && r==house.length-(borderSize+2)-4 && Math.random()<0.75 && !armory) //upper-right - armory
               {
                  armory = true;
                  house[r][c] = signArm.getValue();
                  namePrefix = "Ironsmith";
                  shopkeepIndex = NPC.MAN;
                  NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, house.length-(borderSize+2)-3, center+(pathWidth/2)+2, mapIndex, house.length-(borderSize+2)-3, center-(pathWidth/2)-2, "arena"));
                  int ofLoc = shopKeep.getName().indexOf(" of ");
                  if(ofLoc != -1)
                     shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                  shopKeep.setName(namePrefix + " " + shopKeep.getName());
                  occupants[house.length-(borderSize+2)-3][center+(pathWidth/2)+2] = shopKeep;
               }
               else if(c==center+(pathWidth/2) +1 && r==house.length-(borderSize+2) && Math.random()<0.5 && !magic)   //lower-right - magic
               {
                  magic = true;
                  house[r][c] = signMag.getValue();
                  int rand = (int)(Math.random()*2);
                  if(rand==0)
                  {
                     shopkeepIndex = NPC.WOMAN;
                     namePrefix = "Mage";
                  }
                  else //if(rand==2)
                  {
                     shopkeepIndex = NPC.WISE;
                     namePrefix = "Mage";
                  }
                  NPCPlayer shopKeep = (new NPCPlayer(shopkeepIndex, house.length-(borderSize+2)-1, center-(pathWidth/2), mapIndex, house.length-(borderSize+2)-3, center+(pathWidth/2)+2, "arena"));
                  int ofLoc = shopKeep.getName().indexOf(" of ");
                  if(ofLoc != -1)
                     shopKeep.setName(shopKeep.getName().substring(0, ofLoc));
                  shopKeep.setName(namePrefix + " " + shopKeep.getName());
                  occupants[house.length-(borderSize+2)-1][center+(pathWidth/2)+2] = shopKeep;
               }
               else   
                  house[r][c] = wall.getValue();
            }
     //add counters for shoppes
      for(int r=house.length-(borderSize+2)-3; r<=house.length-(borderSize+2)-1 && r>=0; r++)
         for(int c=center - (pathWidth/2) - 3; c<=center + (pathWidth/2) + 3; c++)
            if(house[r][c] != ground.getValue())
            {
               if(c==center + (pathWidth/2) + 1 || c==center - (pathWidth/2)-1)
                  house[r][c] = seats.getValue();     //counter for armory
               else
                  house[r][c] = floor.getValue();
            }
     //place prisoner cells on the exit side
      int numPrisonRows = Player.rollDie(1,3);
      for(int np=0; np<numPrisonRows; np++)
      {
         int row = borderSize + 1 + (np*3);
         int col = center + (pathWidth/2) +1;
         for(int r=row-1; r<=row+1; r++)
            for(int c=col; c<=col+2; c++)
               house[r][c] = wall.getValue();
         house[row][col] = door.getValue();
         house[row][col+1] = floor.getValue();
         if(Math.random() < 0.66)
         {
            if(mapIndex >= CultimaPanel.civilians.size())
               CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
            NPCPlayer begger = new NPCPlayer(NPC.BEGGER, row, col+1, mapIndex, row, col+1, "arena");
            begger.setNumInfo(0);   
            occupants[row][col+1]=begger;   
         }
           
         row = borderSize + 1 + (np*3);
         col = center - (pathWidth/2) - 1;         
         for(int r=row-1; r<=row+1; r++)
            for(int c=col-2; c<=col; c++)
               house[r][c] = wall.getValue();
         house[row][col] = door.getValue();
         house[row][col-1] = floor.getValue();
         if(Math.random() < 0.66)
         {
            if(mapIndex >= CultimaPanel.civilians.size())
               CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
            NPCPlayer begger =  new NPCPlayer(NPC.BEGGER, row, col-1, mapIndex, row, col-1, "arena");
            begger.setNumInfo(0);
            occupants[row][col-1] = begger;   
         }
      } 
      if(mapIndex < CultimaPanel.civilians.size() && CultimaPanel.civilians.get(mapIndex)!=null)
         CultimaPanel.civilians.get(mapIndex).clear();
      //rotate map depending on what side the player entered in
      if(randSide != 0)
      {
         byte[][] temp = new byte[house.length][house[0].length];
         if(randSide == 1)
         {
            rotateRight(house);
            rotateRight(occupants);
         }
         else if(randSide == 2)
         {
            flipUpsideDown(house);
            flipUpsideDown(occupants);
         }
         else if(randSide == 3)
         {
            rotateLeft(house);
            rotateLeft(occupants);
         }
      }  
      //copy occupants to civilians containter
      for(int r=0; r<occupants.length; r++)
         for(int c=0; c<occupants[0].length; c++)
            if(occupants[r][c] != null)
            {
               if(mapIndex >= CultimaPanel.civilians.size())
                  CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
               occupants[r][c].setRow(r);
               occupants[r][c].setCol(c);
               occupants[r][c].setHomeRow(r);
               occupants[r][c].setHomeCol(c);   
               NPCPlayer toAdd = occupants[r][c]; 
               if(!Utilities.NPCAt(r, c, mapIndex))    
                  CultimaPanel.civilians.get(mapIndex).add(toAdd);
            }
   }

//returns false if the Terrain at (r,c) is one where a moat might exist, or is otherwise invalid place to put a shoppe
//used to make sure a shoppe is not built on a castle's moat
   public static boolean clearOfMoatArea(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return false;
      String terr = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().toLowerCase();
      if(terr.contains("water") || terr.contains("wood_plank"))
         return false;
      return true;
   }

//capitalRow and capitalCol are the upper-left coordinates of where this castle might be placed in a capital city.  
//                           They will be (0,0) if it is a standalone castle as its own location.
//returns the bridgeWidth
   public static int buildCastle(byte[][]house, int enteranceRow, int enteranceCol, int randSide, int mapIndex, String provinceName, boolean forceMoatSize, int capitalRow, int capitalCol)
   {
      int moatWidth = (int)(Math.random()*4) + 2;
      if(forceMoatSize)
         moatWidth = 2;
      int bridgeWidth = (int)(Math.random()*8) + 4;
      int turretSize = (int)(Math.random()*6) + 3;
      boolean isCapital = (capitalRow>0 && capitalCol>0);
   
      NPCPlayer[][] occupants = new NPCPlayer[house.length][house[0].length];    //fill with occupants to add to the civilians ArrayList after we complete and possilby rotate the map
      byte[] occTypes = {NPC.SWORD, NPC.SWORD, NPC.WISE, NPC.WISE, NPC.GUARD_SPEAR, NPC.GUARD_SWORD, NPC.JESTER, NPC.LUTE};
   
      Point topLeft = new Point(0, 0);
      Point topRight = new Point(0, house[0].length-1);
      Point bottomLeft = new Point(house.length-1, 0);
      Point bottomRight = new Point(house.length-1, house[0].length-1);
      Terrain water = TerrainBuilder.getTerrainWithName("TER_I_$Deep_water");
      Terrain drawBridge = TerrainBuilder.getTerrainWithName("INR_$Wood_Plank_floor");
      Terrain wall = TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick");
      Terrain wallTorch = TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$White_Brick_Torch");
      Terrain wallSecret = TerrainBuilder.getTerrainWithName("INR_D_B_$White_Brick_Wall");
   
      Terrain[]floors = {TerrainBuilder.getTerrainWithName("INR_$Blue_Brick_floor_inside"), TerrainBuilder.getTerrainWithName("INR_$Black_Stone_floor")}; 
      Terrain floor = floors[(int)(Math.random()*floors.length)];
   
      Terrain [] carpets = {TerrainBuilder.getTerrainWithName("INR_$Black_floor"), TerrainBuilder.getTerrainWithName("INR_$Blue_floor"), TerrainBuilder.getTerrainWithName("INR_$Red_floor"), TerrainBuilder.getTerrainWithName("INR_$Blue_Brick_floor_inside"), TerrainBuilder.getTerrainWithName("INR_$Black_Stone_floor")};
      Terrain carpet = carpets[(int)(Math.random()*carpets.length)];
      int numTries = 0;
      while(carpet.getName().equals(floor.getName()) && numTries < 1000)
      {
         carpet = carpets[(int)(Math.random()*carpets.length)];
         numTries++;
      }
      //for the Kings throne in the capital
      Terrain [] carpets2 = {TerrainBuilder.getTerrainWithName("INR_$Black_floor"), TerrainBuilder.getTerrainWithName("INR_$Blue_floor"), TerrainBuilder.getTerrainWithName("INR_$Red_floor")};
      Terrain carpet2 = carpets2[(int)(Math.random()*carpets2.length)];
      numTries = 0;
      while(carpet2.getName().equals(carpet.getName()) && numTries < 1000)
      {
         carpet2 = carpets2[(int)(Math.random()*carpets2.length)];
         numTries++;
      }
      Terrain chair = TerrainBuilder.getTerrainWithName("INR_I_L_2_$Counter_lighted");
      //////
   
      Terrain [] doors = {TerrainBuilder.getTerrainWithName("INR_D_B_$Wood_door"), TerrainBuilder.getTerrainWithName("INR_D_B_$Iron_door")};
      Terrain [] doorsLocked = {TerrainBuilder.getTerrainWithName("INR_I_B_$Wood_door_locked"), TerrainBuilder.getTerrainWithName("INR_I_B_$Iron_door_locked")};
   
      for(int r=(int)(topLeft.getX())+1; r <= (int)(bottomRight.getX())-1; r++)
         for(int c=(int)(topLeft.getY())+1; c <= (int)(bottomRight.getY())-1; c++)
            if(CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().contains("TER"))
               house[r][c] = floor.getValue();
   
   
      int center = house[0].length / 2;   
      for(int r=enteranceRow; r<=moatWidth; r++)
      {
         for(int c=0; c<house[0].length; c++)
         {
            if(c < center - bridgeWidth/2 || c > center + bridgeWidth/2)
               house[r][c] = water.getValue();
            else
               house[r][c] = drawBridge.getValue();
         }
      }
      topLeft = new Point(moatWidth+1, 0);
      topRight = new Point(moatWidth+1, house[0].length-1);
      bottomLeft = new Point(house.length-1, 0);
      bottomRight = new Point(house.length-1, house[0].length-1);
   
   //build wall around castle
      for(int r=moatWidth+1; r<house.length; r++)
      {
         house[r][0] = wall.getValue();
         house[r][house[0].length-1] = wall.getValue();
      }
   
   //put guard on each side of the entrance
      if(!isImpassable(house,  moatWidth+1, (center - bridgeWidth/2)))
         occupants[moatWidth+1][(center - bridgeWidth/2)] = (new NPCPlayer(NPC.randGuard(), moatWidth+1, (center - bridgeWidth/2), mapIndex, moatWidth+1, (center - bridgeWidth/2), "castle"));
      if(!isImpassable(house,  moatWidth+1, (center + bridgeWidth/2)))
         occupants[moatWidth+1][(center + bridgeWidth/2)] = (new NPCPlayer(NPC.randGuard(), moatWidth+1, (center + bridgeWidth/2), mapIndex, moatWidth+1, (center - bridgeWidth/2), "castle"));
   
      for(int c=0; c<house[0].length; c++)
      {
         if(c < center - bridgeWidth/2 || c > center + bridgeWidth/2)
         {
            if(c == (center - bridgeWidth/2-1) || c==(center + bridgeWidth/2+1))      //put torches at enterance of castle
               house[moatWidth+1][c] = wallTorch.getValue();
            else
               house[moatWidth+1][c] = wall.getValue();
         }
         else
            house[moatWidth+1][c] = drawBridge.getValue();
         house[house.length-1][c] = wall.getValue();
      }
   
   //put down main hallway
      double secretProb = 0.01;
      double secretProbTorch = 0.05;
   
      boolean closedHallway = false;
      if(Math.random() < 0.65)
         closedHallway = true;
      int columnSize = (int)(Math.random()*2) + 1; 
      int columnDepth = (int)(Math.random()*2) + 1;  
      boolean closedWithColumns = false;
      if(Math.random() < 0.5)
      {
         closedWithColumns = true;
         columnDepth = 2;
      } 
   
      int mainHallLength = ((int)(Math.random()*3) + 2) * (house.length/4);
      int torchPeriod = (int)(Math.random()*6) + 5;                                    //distance between torches
   
      if(Math.random() < 0.75)           //add a roaming guard moving south
      {
         if(!isImpassable(house, mainHallLength/2, center))
         {
            occupants[mainHallLength/2][center] = (new NPCPlayer(NPC.randGuard(), mainHallLength/2, center, mapIndex, mainHallLength/2, center, "castle"));
            occupants[mainHallLength/2][center].setMoveType(NPC.MARCH_SOUTH);
         }
      }
   
      for(int r=moatWidth+2; r<mainHallLength-1; r++)
      {
         for(int c=center - bridgeWidth/2 - 1; c<=center + bridgeWidth/2 + 1; c++)
         {
            if(c>=center - bridgeWidth/2 + 1 && c<=center + bridgeWidth/2 - 1)
               house[r][c] = carpet.getValue();
            else if((c==center - bridgeWidth/2 - 1 || c==center + bridgeWidth/2 + 1) && !closedHallway)
            {  //put in a column
               if(r % torchPeriod == 0)
                  house[r][c] = wallTorch.getValue();
               else if(r % torchPeriod == 1 && columnSize == 2)
               {
                  house[r][c] = wall.getValue();
                  if(c==center - bridgeWidth/2 - 1 && columnDepth == 2 && !outOfBounds(house, r, c-1))   //left column   
                     house[r][c-1] = wall.getValue();
                  else if(c==center + bridgeWidth/2 + 1 && columnDepth == 2 && !outOfBounds(house, r, c+1))   //left column   
                     house[r][c+1] = wall.getValue();
               }
               if(closedWithColumns)
               {
                  if(c==center - bridgeWidth/2 - 1 && columnDepth == 2 && !outOfBounds(house, r, c-1))   //left column   
                     house[r][c-1] = wall.getValue();
                  else if(c==center + bridgeWidth/2 + 1 && columnDepth == 2 && !outOfBounds(house, r, c+1))   //left column   
                     house[r][c+1] = wall.getValue();
               }
            }
            else if((c==center - bridgeWidth/2 - 1 || c==center + bridgeWidth/2 + 1) && closedHallway)
            {
               if(r % torchPeriod == 0)
                  house[r][c] = wallTorch.getValue();
               else if(r % torchPeriod == 1)             //maybe put an opening or a door
               {
                  int decision = (int)(Math.random()*4); //0-wall, 1-opening, 2-door, 3-locked door
                  if(decision == 0 || r + (torchPeriod/2) >= mainHallLength)
                  {
                     if(Math.random() < secretProbTorch)
                        house[r][c] = wallSecret.getValue();
                     else
                        house[r][c] = wall.getValue();
                  }
                  else 
                  {
                     if(decision == 1)
                        house[r][c] = floor.getValue();
                     else if(decision == 2)
                        house[r][c] = doors[0].getValue();
                     else
                        house[r][c] = doorsLocked[0].getValue();
                  //maybe make the opening or door go into some kind of room
                     if(Math.random() < 0.75)
                     {
                        int occRow = r;
                        int occCol = c;
                        if(c==center - bridgeWidth/2 - 1)      //left side
                           occCol = c-2;
                        else
                           occCol = c+2;                       //right side                       
                        if(!isImpassable(house, occRow, occCol))
                        {
                           byte charIndex = occTypes[(int)(Math.random()*occTypes.length)];
                           occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "castle");
                        }
                        int maxRoomSize = (house[0].length-1) - (center + bridgeWidth/2 + 1);
                        if(r < moatWidth+1 + turretSize || r > house.length - 1 - turretSize)
                           maxRoomSize = (house[0].length-1) - (center + bridgeWidth/2 + 1) - turretSize - 2;
                        int roomSize = (int)(Math.random()*(maxRoomSize - 3 + 1)) + 3;
                        if(c==center-bridgeWidth/2 - 1)         //left wall
                        {
                           int roomRowTop = r - (torchPeriod/2);
                           int roomRowBottom = r + (torchPeriod/2);
                           int leftWall = c-1-roomSize;
                           boolean topRowBreak = false;
                           boolean bottomRowBreak = false;
                           for(int col=c-1; col>= leftWall; col--)
                           {
                              if(!isImpassable(house, roomRowTop, col))
                              {
                                 if(Math.random() < secretProb)
                                    house[roomRowTop][col] = wallSecret.getValue();
                                 else
                                    house[roomRowTop][col] = wall.getValue();
                              }
                              else 
                              {
                                 topRowBreak=true;
                                 break;
                              }
                           }
                           for(int col=c-1; col>= leftWall; col--)
                           {
                              if(!isImpassable(house, roomRowBottom, col))
                              {
                                 if(Math.random() < secretProb)
                                    house[roomRowBottom][col] = wallSecret.getValue();
                                 else
                                    house[roomRowBottom][col] = wall.getValue();
                              }
                              else 
                              {
                                 bottomRowBreak = true;
                                 break;
                              }
                           }
                           if((!topRowBreak && !bottomRowBreak) || (!topRowBreak && bottomRowBreak))
                           {   
                              for(int row = roomRowTop+1; row<=roomRowBottom-1; row++)
                                 if(!isImpassable(house, row, leftWall))
                                 {
                                    if(Math.random() < secretProb)
                                       house[row][leftWall] = wallSecret.getValue();
                                    else
                                       house[row][leftWall] = wall.getValue();
                                 }
                                 else 
                                    break;
                           }
                           else if(topRowBreak && !bottomRowBreak)
                           {
                              for(int row = roomRowBottom-1; row>= roomRowTop+1; row--)
                                 if(!isImpassable(house, row, leftWall))
                                 {
                                    if(Math.random() < secretProb)
                                       house[row][leftWall] = wallSecret.getValue();
                                    else
                                       house[row][leftWall] = wall.getValue();
                                 }
                                 else 
                                    break;
                           }
                        }
                        else
                           if(c==center+bridgeWidth/2 + 1)         //right wall
                           {
                              int roomRowTop = r - (torchPeriod/2);
                              int roomRowBottom = r + (torchPeriod/2);
                              int rightWall = c+1+roomSize;
                              boolean topRowBreak = false;
                              boolean bottomRowBreak = false;
                              for(int col=c+1; col<= rightWall; col++)
                              {
                                 if(!isImpassable(house, roomRowTop, col))
                                 {
                                    if(Math.random() < secretProb)
                                       house[roomRowTop][col] = wallSecret.getValue();
                                    else
                                       house[roomRowTop][col] = wall.getValue();
                                 }
                                 else 
                                 {
                                    topRowBreak=true;
                                    break;
                                 }
                              }
                              for(int col=c+1; col<= rightWall; col++)
                              {
                                 if(!isImpassable(house, roomRowBottom, col))
                                 {
                                    if(Math.random() < secretProb)
                                       house[roomRowBottom][col] = wallSecret.getValue();
                                    else
                                       house[roomRowBottom][col] = wall.getValue();
                                 }
                                 else 
                                 {
                                    bottomRowBreak = true;
                                    break;
                                 }
                              }
                              if((!topRowBreak && !bottomRowBreak) || (!topRowBreak && bottomRowBreak))
                              {   
                                 for(int row = roomRowTop+1; row<=roomRowBottom-1; row++)
                                    if(!isImpassable(house, row, rightWall))
                                    {
                                       if(Math.random() < secretProb)
                                          house[row][rightWall] = wallSecret.getValue();
                                       else
                                          house[row][rightWall] = wall.getValue();
                                    }
                                    else 
                                       break;
                              }
                              else if(topRowBreak && !bottomRowBreak)
                              {
                                 for(int row = roomRowBottom-1; row>= roomRowTop+1; row--)
                                    if(!isImpassable(house, row, rightWall))
                                    {
                                       if(Math.random() < secretProb)
                                          house[row][rightWall] = wallSecret.getValue();
                                       else
                                          house[row][rightWall] = wall.getValue();
                                    }
                                    else 
                                       break;
                              }
                           }
                     }
                  }
               }
               else
               {
                  if(Math.random() < secretProb)
                     house[r][c] = wallSecret.getValue();
                  else
                     house[r][c] = wall.getValue();
               }
            }
         }
      }
   //put main chamber at the end of the main hallway, or another perpindicular hallway
      boolean kingPlaced = false;
      int centerR = center, centerC = center;
      boolean crossHallway = false;
      if(Math.random() < 0.5)
         crossHallway = true;
      if(Math.abs((mainHallLength - 1) - (house.length-2)) > 2) 
      {
         int chamberColMin = center - bridgeWidth;
         int chamberColMax = center + bridgeWidth;
         int chamberHeight = bridgeWidth + ((int)(Math.random()*bridgeWidth));
         if(crossHallway)
         {
            chamberHeight = bridgeWidth-1;
            chamberColMin = 4;
            chamberColMax = house[0].length - 5;
         }
         if(Math.random() < 0.75)           //add a roaming guard moving west
         {
            centerR = ((mainHallLength-1) + (mainHallLength-1 + chamberHeight))/2;
            centerC = (chamberColMin+chamberColMax)/2;
            if(!isImpassable(house, centerR, centerC))
            {
               occupants[centerR][centerC] = (new NPCPlayer(NPC.randGuard(), centerR, centerC, mapIndex, centerR, centerC, "castle"));
               occupants[centerR][centerC].setMoveType(NPC.MARCH_WEST);
            }
         }
         if(Math.random() < 0.95 || isCapital)           //add royalty and maybe surrounding guards
         {
            centerR = (((mainHallLength-1) + (mainHallLength-1 + chamberHeight))/2) + 2;
            centerC = (chamberColMin+chamberColMax)/2;
            if(!isImpassable(house, centerR, centerC))
            {
               occupants[centerR][centerC] = (new NPCPlayer(NPC.KING, centerR, centerC, mapIndex, centerR, centerC, "castle"));
               if(isCapital)              //make this the King of all
               {
                  kingPlaced = true;
                  occupants[centerR][centerC].setName("King Richard Garriott");
                  occupants[centerR][centerC].modifyStats(1.25);
                  occupants[centerR][centerC].setReputation(Player.rollDie(500,2000));
                  occupants[centerR][centerC].setWeapon(Weapon.getLifeSceptre());
                  if(Math.random() < 0.75)
                  {
                     int rand = (int)(Math.random() * 8);
                     if(rand == 0)
                        occupants[centerR][centerC].addItem(Item.getCharmring());
                     else if(rand == 1)
                        occupants[centerR][centerC].addItem(Item.getFocushelm());
                     else if(rand == 2)
                        occupants[centerR][centerC].addItem(Item.getPentangle());
                     else if(rand == 3)
                        occupants[centerR][centerC].addItem(Item.getMannastone());
                     else if(rand == 4)
                        occupants[centerR][centerC].addItem(Item.getMindTome());
                     else if(rand == 5)
                        occupants[centerR][centerC].addItem(Item.getSwiftBoots());
                     else if(rand == 6)
                        occupants[centerR][centerC].addItem(Item.getPowerBands());
                     else //if(rand == 7)
                        occupants[centerR][centerC].addItem(Item.getLifePearl());
                  }
                  occupants[centerR][centerC].addItem(Item.getWisdomEgg());
               }
               double guardProb = 0.5;
               if(isCapital)
                  guardProb = 0.75;
               if(Math.random() < 0.5 && !isCapital)
               {
                  if(Math.random() < 0.5 && !isImpassable(house, centerR, centerC-2))
                     occupants[centerR][centerC-2] = (new NPCPlayer(NPC.randGuard(), centerR, centerC-2, mapIndex, centerR, centerC-2, "castle"));
                  if(Math.random() < 0.5 && !isImpassable(house, centerR, centerC+2))
                     occupants[centerR][centerC+2] = (new NPCPlayer(NPC.randGuard(), centerR, centerC+2, mapIndex, centerR, centerC+2, "castle"));
               }
               else
               {
                  if(Math.random() < guardProb && !isImpassable(house, centerR, centerC-2))
                  {
                     occupants[centerR][centerC-2] = (new NPCPlayer(NPC.ROYALGUARD, centerR, centerC-2, mapIndex, centerR, centerC-2, "castle"));
                     if(isCapital)
                     {
                        occupants[centerR][centerC-2].modifyStats(1.25);
                        occupants[centerR][centerC-2].setReputation(Player.rollDie(100,1000));
                     }
                  }
                  if(Math.random() < guardProb && !isImpassable(house, centerR, centerC+2))
                  {
                     occupants[centerR][centerC+2] = (new NPCPlayer(NPC.ROYALGUARD, centerR, centerC+2, mapIndex, centerR, centerC+2, "castle"));
                     if(isCapital)
                     {
                        occupants[centerR][centerC+2].modifyStats(1.25);
                        occupants[centerR][centerC+2].setReputation(Player.rollDie(100,1000));
                     }
                  }
                  if(isCapital)  //extra guards for the King of all
                  {
                     if(Math.random() < guardProb && !isImpassable(house, centerR+1, centerC-2))
                     {
                        occupants[centerR+1][centerC-2] = (new NPCPlayer(NPC.ROYALGUARD, centerR+1, centerC-2, mapIndex, centerR+1, centerC-2, "castle"));
                        occupants[centerR+1][centerC-2].modifyStats(1.25);
                        occupants[centerR+1][centerC-2].setReputation(Player.rollDie(100,1000));
                     }
                     if(Math.random() < guardProb && !isImpassable(house, centerR+1, centerC+2))
                     {
                        occupants[centerR+1][centerC+2] = (new NPCPlayer(NPC.ROYALGUARD, centerR+1, centerC+2, mapIndex, centerR+1, centerC+2, "castle"));
                        occupants[centerR+1][centerC+2].modifyStats(1.25);
                        occupants[centerR+1][centerC+2].setReputation(Player.rollDie(100,1000));
                     }
                  }
               }
            }
         }
         for(int r=mainHallLength-1; r<mainHallLength-1 + chamberHeight && r < house.length - 1; r++)
         {
            for(int c=chamberColMin; c<=chamberColMax; c++)
            {
               if(closedHallway && (c==center - bridgeWidth/2 - 1 || c==center + bridgeWidth/2 + 1) && r==mainHallLength-1 && Math.random() < 0.75 && !crossHallway)
                  house[r][c] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
               else
                  house[r][c] = carpet.getValue();
            }
         }
         int topWallRow = mainHallLength  - 2;
         int bottomWall = mainHallLength-1 + chamberHeight + 1;
         int leftWall = chamberColMin - 2;
         int rightWall = chamberColMax + 2;
         if(closedHallway)    //place walls around the main chamber
         {
            for(int c=leftWall; c<=rightWall && c<house[0].length && c>0; c++)
            {
               if(!CultimaPanel.allTerrain.get(Math.abs(house[topWallRow][c])).getName().equals(carpet.getName()))
               {
                  if(Math.random() < secretProb)
                     house[topWallRow][c] = wallSecret.getValue();
                  else
                     house[topWallRow][c] = wall.getValue();
               }
               if(!isImpassable(house, bottomWall, c))
               {
               //maybe put a door along the bottom wall
                  if(c==(leftWall + rightWall)/2)
                  {
                     if(Math.random() < 0.5)
                        house[bottomWall][c] = doorsLocked[(int)(Math.random()*doorsLocked.length)].getValue();
                     else  if(Math.random() < 0.5)
                        house[bottomWall][c] = wallSecret.getValue();   
                     else
                        house[bottomWall][c] = wall.getValue();   
                  }
                  else
                  {
                     if(Math.random() < secretProb)
                        house[bottomWall][c] = wallSecret.getValue();
                     else
                        house[bottomWall][c] = wall.getValue();
                  }
               }
               if(!isImpassable(house, bottomWall-2, c) && (c==center - bridgeWidth/2 - 1 || c==center + bridgeWidth/2 + 1) && Math.random() < 0.75)
                  house[bottomWall-2][c] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
            }
            for(int r=topWallRow; r<=bottomWall; r++)
            {
               if(!isImpassable(house, r, leftWall))
               {
                  if(r % torchPeriod == 0)
                     house[r][leftWall] = wallTorch.getValue();
                  else
                  {
                     if(Math.random() < secretProb)
                        house[r][leftWall] = wallSecret.getValue();
                     else
                        house[r][leftWall] = wall.getValue();
                  }
               }
               if(!isImpassable(house, r, rightWall))
               {
                  if(r % torchPeriod == 0)
                     house[r][rightWall] = wallTorch.getValue();
                  else
                  {
                     if(Math.random() < secretProb)
                        house[r][rightWall] = wallSecret.getValue();
                     else
                        house[r][rightWall] = wall.getValue();
                  }
               }
            }
         }
         if(crossHallway && mainHallLength < house.length-4 && Math.random() < 0.5) //maybe extend the main hall to the last row
         {        
            for(int r=bottomWall - 1; r < house.length-1; r++)
            {
               for(int c=center - bridgeWidth/2 - 1; c<=center + bridgeWidth/2 + 1; c++)
               {
                  if(c>=center - bridgeWidth/2 + 1 && c<=center + bridgeWidth/2 - 1)
                     house[r][c] = carpet.getValue();
                  else if((c==center - bridgeWidth/2 - 1 || c==center + bridgeWidth/2 + 1) && closedHallway)
                  {
                     if(r % torchPeriod == 0)
                        house[r][c] = wallTorch.getValue();
                     else if(r % torchPeriod == 1)             //maybe put an opening or a door
                     {
                        int decision = (int)(Math.random()*4); //0-wall, 1-opening, 2-door, 3-locked door
                        if(decision == 0)
                        {
                           if(Math.random() < secretProbTorch)
                              house[r][c] = wallSecret.getValue();
                           else
                              house[r][c] = wall.getValue();
                        }
                        else 
                        {
                           if(decision == 1)
                              house[r][c] = floor.getValue();
                           else if(decision == 2)
                              house[r][c] = doors[0].getValue();
                           else
                              house[r][c] = doorsLocked[0].getValue();
                        //TO DO: maybe make the opening or door go into some kind of room
                        }
                     }
                     else
                     {
                        if(Math.random() < secretProb)
                           house[r][c] = wallSecret.getValue();
                        else
                           house[r][c] = wall.getValue();
                     }
                  }
               }
            }           
         }  
      }
      //put the king of all on a throne
      if(kingPlaced)
      {
         house[centerR][centerC] = carpet.getValue();
         for(int cr = centerR; cr<=centerR+1; cr++)
            for(int cc = centerC-1; cc<=centerC+1; cc++)
            {
               if((cr==centerR && cc==centerC) || outOfBounds(house, cr, cc) || occupants[cr][cc]!=null)
                  continue;
               house[cr][cc] = carpet2.getValue();
            }
      }
      boolean addedDungeon = false;
      int focusRow = -1;   //location of dungeon entrance
      int focusCol = -1;  
   
   //place interior rooms that are like little buildings in the castle
      ArrayList<Integer> quadrants = new ArrayList<Integer>();
      quadrants.add(1);                            //if we separated the city into quadrants I-IV, like cartesian coordinate plane
      quadrants.add(2);   
      quadrants.add(3);   
      quadrants.add(4);
      String[]stores = {"magic", "armory", "armory", "armory"};
      String randStore = stores[(int)(Math.random()*stores.length)];
      NPCPlayer[][]workers = null;
      while(!quadrants.isEmpty())
      {
         int randQuadrant = quadrants.remove((int)(Math.random()*quadrants.size()));
         workers = buildShoppe(house, randStore, carpet, randQuadrant, "castle", turretSize, mapIndex, false);
         if(workers!=null)
         {
            for(int r=0; r<workers.length && r<occupants.length; r++)
               for(int c=0; c<workers[0].length && c<occupants[0].length; c++)
                  if(workers[r][c]!=null)
                     occupants[r][c] = workers[r][c];
            break;
         }
      }
      int numBuildings = (int)(Math.random() * 8) + 5;
      if(closedHallway)
         numBuildings = (int)(Math.random() * 5) + 3;
      for(int i=0; i<numBuildings; i++)
      {
         Object [] info = placeBuilding(house, mapIndex, wall, floor, "castle", carpet);
         ArrayList<NPCPlayer> person = (ArrayList<NPCPlayer>)(info[0]);
         if(person!=null && person.size() > 0)
            for(NPCPlayer p:person)
               occupants[p.getRow()][p.getCol()] = p;
         Point roomCenter = (Point)(info[1]);
         if(!addedDungeon && Math.random() < 0.25 && roomCenter!=null && !nextToADoor(house, (int)(roomCenter.getX()), (int)(roomCenter.getY())))
         {
            focusRow = (int)(roomCenter.getX());
            focusCol = (int)(roomCenter.getY());
            addedDungeon = true;
            if(occupants[focusRow][focusCol]!=null)         //if there is a person on the dungeon entrance
            {
               if(Math.random() < 0.5)                      //remove them
                  occupants[focusRow][focusCol] = null;
               else                                         //or shift them if you can
               {
                  NPCPlayer toMove = occupants[focusRow][focusCol];
                  occupants[focusRow][focusCol] = null;
                  boolean moved = false;
                  for(int occRow=focusRow-1; occRow<=focusRow+1; occRow++)
                     for(int occCol=focusCol-1; occCol<=focusCol+1; occCol++)
                     {
                        if(!isImpassable(house, occRow, occCol) && (house[occRow][occCol]==floor.getValue() || house[occRow][occCol]==carpet.getValue()))
                        {
                           occupants[focusRow][focusCol] = toMove;
                           moved = true;
                           break;
                        }
                        if(moved)
                           break;
                     }
               
               }
            }
         }
      } 
   
   //add some turrets
      int doorIndex = (int)(Math.random() * doors.length);
      Terrain door = doors[doorIndex];
      Terrain doorLocked = doorsLocked[doorIndex];
   
      int topLeftRow = (int)(topLeft.getX());
      int topLeftCol = (int)(topLeft.getY());
      for(int r=topLeftRow+1; r<=topLeftRow + turretSize; r++)
         for(int c=topLeftCol+1; c<=topLeftCol + turretSize; c++)
            house[r][c] = floor.getValue();
   
      boolean sideDoor = true;
      if(Math.random() < 0.5)
         sideDoor = false;
   
      for(int r=topLeftRow; r<=topLeftRow + turretSize; r++)
      {
         int mid = (topLeftRow + (topLeftRow + turretSize))/2;
         if(r == mid && sideDoor)
         {
            if(Math.random() < 0.5)
               house[r][topLeftCol+turretSize] = door.getValue();
                    
            else
               house[r][topLeftCol+turretSize] = doorLocked.getValue();
            if(isImpassable(house, r, topLeftCol+turretSize+1) && topLeftCol+turretSize+1<house[0].length)
               house[r][topLeftCol+turretSize+1] = floor.getValue();
         }
         else
         {
            if(r==mid+1 && sideDoor)
               house[r][topLeftCol+turretSize] = wallTorch.getValue();
            else
               house[r][topLeftCol+turretSize] = wall.getValue();
         }
      }
      int occRow = (topLeftRow + (topLeftRow + turretSize))/2;
      int occCol = topLeftCol+(turretSize/2);
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = occTypes[(int)(Math.random()*occTypes.length)];
         occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "castle");
      }
   
      for(int c=topLeftCol; c<=topLeftCol + turretSize; c++)
      {
         int mid = (topLeftCol + (topLeftCol + turretSize))/2;
         if(c == mid && !sideDoor)
         {
            if(Math.random() < 0.5)
               house[topLeftRow+turretSize][c] = door.getValue();
            else
               house[topLeftRow+turretSize][c] = doorLocked.getValue();
            if(isImpassable(house, topLeftRow+turretSize+1, c) && topLeftRow+turretSize+1<house.length)
               house[topLeftRow+turretSize+1][c] = floor.getValue();
         }
         else
         {
            if(c==mid+1 && !sideDoor)
               house[topLeftRow+turretSize][c] = wallTorch.getValue();
            else
               house[topLeftRow+turretSize][c] = wall.getValue();
         }
      }
      occCol = (topLeftCol + (topLeftCol + turretSize))/2;
      occRow = topLeftRow+(turretSize/2);
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = occTypes[(int)(Math.random()*occTypes.length)];
         occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "castle");
      }
   
      int topRightRow = (int)(topRight.getX());
      int topRightCol = (int)(topRight.getY());
      for(int r=topRightRow+1; r<=topRightRow + turretSize; r++)
         for(int c=topRightCol-turretSize-1; c<=topRightCol; c++)
            house[r][c] = floor.getValue();
      sideDoor = true;
      if(Math.random() < 0.5)
         sideDoor = false;
   
      for(int r=topRightRow; r<=topRightRow + turretSize; r++)
      {
         int mid = (topRightRow + (topRightRow + turretSize))/2;
         if(r == mid && sideDoor)
         {
            if(Math.random() < 0.5)
               house[r][topRightCol-turretSize] = door.getValue();
            else
               house[r][topRightCol-turretSize] = doorLocked.getValue();
            if(isImpassable(house, r, topRightCol-turretSize-1) &&  topRightCol-turretSize-1 > 0)
               house[r][topRightCol-turretSize-1] = floor.getValue();
         }
         else
         {
            if(r==mid+1 && sideDoor)
               house[r][topRightCol-turretSize] = wallTorch.getValue();
            else
               house[r][topRightCol-turretSize] = wall.getValue();
         }
      }
      occCol = (topRightCol-turretSize/2);
      occRow = (topRightRow + (topRightRow + turretSize))/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = occTypes[(int)(Math.random()*occTypes.length)];
         occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "castle");
      }
   
      for(int c=topRightCol-turretSize; c<=topRightCol; c++)
      {
         int mid = (topRightCol + (topRightCol - turretSize))/2;
         if(c == mid && !sideDoor)
         {
            if(Math.random() < 0.5)
               house[topRightRow+turretSize][c] = door.getValue();
            else
               house[topRightRow+turretSize][c] = doorLocked.getValue();
            if(isImpassable(house, topRightRow+turretSize+1, c) && topRightRow+turretSize+1 < house.length)
               house[topRightRow+turretSize+1][c] = floor.getValue();
         }
         else
         {
            if(c==mid-1 && !sideDoor)
               house[topRightRow+turretSize][c] = wallTorch.getValue();
            else
               house[topRightRow+turretSize][c] = wall.getValue();
         }
      }
      occCol = (topRightCol + (topRightCol - turretSize))/2;
      occRow = topRightRow+turretSize/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = occTypes[(int)(Math.random()*occTypes.length)];
         occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "castle");
      }
   
      int bottomLeftRow = (int)(bottomLeft.getX());
      int bottomLeftCol = (int)(bottomLeft.getY());
      for(int r=bottomLeftRow - turretSize - 1; r<=bottomLeftRow - 1; r++)
         for(int c=bottomLeftCol + 1; c<=bottomLeftCol + turretSize; c++)
            house[r][c] = floor.getValue();
   
      sideDoor = true;
      if(Math.random() < 0.5)
         sideDoor = false;
     
      for(int r=bottomLeftRow - turretSize - 1; r<=bottomLeftRow - 1; r++)
      {
         int mid = ((bottomLeftRow - 1) + (bottomLeftRow - turretSize - 1))/2;
         if(r == mid && sideDoor)
         {
            if(Math.random() < 0.5)
               house[r][bottomLeftCol+turretSize] = door.getValue();
            else
               house[r][bottomLeftCol+turretSize] = doorLocked.getValue();
            if(isImpassable(house, r, bottomLeftCol+turretSize + 1) && bottomLeftCol+turretSize + 1 < house[0].length)
               house[r][bottomLeftCol+turretSize + 1] = floor.getValue();
         }
         else
         {
            if(r==mid-1 && sideDoor)
               house[r][bottomLeftCol+turretSize] = wallTorch.getValue();
            else
               house[r][bottomLeftCol+turretSize] = wall.getValue();
         }
      }
      occCol = (bottomLeftCol+turretSize/2);
      occRow = ((bottomLeftRow - 1) + (bottomLeftRow - turretSize - 1))/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = occTypes[(int)(Math.random()*occTypes.length)];
         occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "castle");
      }
   
      for(int c=bottomLeftCol; c<=bottomLeftCol + turretSize; c++)
      {
         int mid = (bottomLeftCol + (bottomLeftCol + turretSize))/2;
         if(c == mid && !sideDoor)
         {
            if(Math.random() < 0.5)
               house[bottomLeftRow-turretSize-1][c] = door.getValue();
            else
               house[bottomLeftRow-turretSize-1][c] = doorLocked.getValue();
            if(isImpassable(house,bottomLeftRow-turretSize-2, c) && bottomLeftRow-turretSize-2 > 0)    
               house[bottomLeftRow-turretSize-2][c] = floor.getValue();
         }
         else
         {
            if(c==mid+1 && !sideDoor)
               house[bottomLeftRow-turretSize-1][c] = wallTorch.getValue();
            else
               house[bottomLeftRow-turretSize-1][c] = wall.getValue();
         }
      }
      occCol = (bottomLeftCol + (bottomLeftCol + turretSize))/2;
      occRow = bottomLeftRow-turretSize/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = occTypes[(int)(Math.random()*occTypes.length)];
         occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "castle");
      }
      int bottomRightRow = (int)(bottomRight.getX());
      int bottomRightCol = (int)(bottomRight.getY());
      for(int r=bottomRightRow - turretSize; r<=bottomRightRow - 1; r++)
         for(int c=bottomRightCol - turretSize; c<=bottomRightCol - 1; c++)
            house[r][c] = floor.getValue();
   
      sideDoor = true;
      if(Math.random() < 0.5)
         sideDoor = false;
   
      for(int r=bottomRightRow - turretSize; r<=bottomRightRow - 1; r++)
      {
         int mid = ((bottomRightRow - 1) + (bottomRightRow - turretSize))/2;
         if(r == mid && sideDoor)
         {
            if(Math.random() < 0.5)
               house[r][bottomRightCol-turretSize] = door.getValue();
            else
               house[r][bottomRightCol-turretSize] = doorLocked.getValue();
            if(isImpassable(house, r,bottomRightCol-turretSize-1) && bottomRightCol-turretSize-1 > 0)   
               house[r][bottomRightCol-turretSize-1] = floor.getValue();
         }
         else
         {
            if(r==mid-1 && sideDoor)
               house[r][bottomRightCol-turretSize] = wallTorch.getValue();
            else
               house[r][bottomRightCol-turretSize] = wall.getValue();
         }
      }
      occCol = bottomRightCol-turretSize/2;
      occRow = ((bottomRightRow - 1) + (bottomRightRow - turretSize))/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = occTypes[(int)(Math.random()*occTypes.length)];
         occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "castle");
      }
   
      for(int c=bottomRightCol - turretSize; c<=bottomRightCol; c++)
      {
         int mid = (bottomRightCol + (bottomRightCol - turretSize))/2;
         if(c == mid && !sideDoor)
         {
            if(Math.random() < 0.5)
               house[bottomRightRow-turretSize-1][c] = door.getValue();
            else
               house[bottomRightRow-turretSize-1][c] = doorLocked.getValue();
            if(isImpassable(house, bottomRightRow-turretSize-2, c) && bottomRightRow-turretSize-2 > 0)
               house[bottomRightRow-turretSize-2][c] = floor.getValue();
         }
         else
         {
            if(c==mid-1 && !sideDoor)
               house[bottomRightRow-turretSize-1][c] = wallTorch.getValue();
            else
               house[bottomRightRow-turretSize-1][c] = wall.getValue();
         }
      }
      occCol = (bottomRightCol + (bottomRightCol - turretSize))/2;
      occRow = bottomRightRow-turretSize/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = occTypes[(int)(Math.random()*occTypes.length)];
         occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "castle");
      }
      fixBrokenDoors(house, floor);
   
   //**maybe add a prison or dungeon
   
      if(randSide != 2)    //not SOUTH
      {
         byte[][] temp = new byte[house.length][house[0].length];
         if(addedDungeon && focusRow >=0 && focusCol >= 0)
            temp[focusRow][focusCol] = 1;
         if(randSide == 3) //WEST
         {
            rotateRight(house);
            for(int r=0; r<occupants.length; r++)     //since we are rotating 90%, change the marching directions
               for(int c=0; c<occupants.length; c++)
                  if(occupants[r][c]!=null)
                  {
                     if(occupants[r][c].getMoveType()==NPC.MARCH_NORTH || occupants[r][c].getMoveType()==NPC.MARCH_SOUTH)
                        occupants[r][c].setMoveType(NPC.MARCH_WEST);
                     else if(occupants[r][c].getMoveType()==NPC.MARCH_EAST || occupants[r][c].getMoveType()==NPC.MARCH_WEST)
                        occupants[r][c].setMoveType(NPC.MARCH_NORTH);
                  }
         
            rotateRight(occupants);
            if(addedDungeon)
               rotateRight(temp);
         }
         else if(randSide == 0)     //NORTH
         {
            flipUpsideDown(house);
            flipUpsideDown(occupants);
            if(addedDungeon)
               flipUpsideDown(temp);
         }
         else if(randSide == 1)     //EAST
         {
            rotateLeft(house);
            for(int r=0; r<occupants.length; r++)     //since we are rotating 90%, change the marching directions
               for(int c=0; c<occupants.length; c++)
                  if(occupants[r][c]!=null)
                  {
                     if(occupants[r][c].getMoveType()==NPC.MARCH_NORTH || occupants[r][c].getMoveType()==NPC.MARCH_SOUTH)
                        occupants[r][c].setMoveType(NPC.MARCH_WEST);
                     else if(occupants[r][c].getMoveType()==NPC.MARCH_EAST || occupants[r][c].getMoveType()==NPC.MARCH_WEST)
                        occupants[r][c].setMoveType(NPC.MARCH_NORTH);
                  }
            rotateLeft(occupants);
            if(addedDungeon)
               rotateLeft(temp);
         }
         if(addedDungeon)
         {
            for(int r=0; r<temp.length; r++)
            {
               boolean breakOut = false;
               for(int c=0; c<temp.length; c++)
               {
                  if(temp[r][c]==1)
                  {
                     focusRow = r;
                     focusCol = c;
                     breakOut = true;
                     break;
                  }
               }
               if(breakOut)
                  break;
            }
         }
      }
   //maybe put an enterance to a dungeon here
      if(addedDungeon && focusRow >=0 && focusCol >= 0 && !forceMoatSize)
      {
         int doorRow = focusRow;   //find a place where there is an open spot to get to the dungeon entrance
         int doorCol = focusCol;
         numTries = 0;
         while(!outOfBounds(house, doorRow, doorCol) && blockedIn(house, doorRow, doorCol))
         {
            if(randSide==0)
               doorRow++;
            else if(randSide==1)
               doorCol--;
            else if(randSide==2)
               doorRow--;
            else
               doorCol++; 
         }
         if(!outOfBounds(house, doorRow, doorCol))
         {
            Terrain ter = TerrainBuilder.getTerrainWithName("LOC_$Dungeon_enterance");
            house[doorRow][doorCol] = ter.getValue();
         //String name = Utilities.nameGenerator("dungeon");
            String name = provinceName + " " +Utilities.dungeonSuffix[(int)(Math.random()* Utilities.dungeonSuffix.length)];
         //assign teleporter to a new world or location
            Teleporter teleporter = new Teleporter((CultimaPanel.map).size());
            Location dungeonLoc = new Location(name, doorRow, doorCol, mapIndex, ter, teleporter);
            CultimaPanel.allLocations.add(dungeonLoc);
            FileManager.writeOutLocationsToFile(CultimaPanel.allLocations, "maps/worldLocs.txt");
         }
      }   
   
      for(int r=0; r<occupants.length; r++)
         for(int c=0; c<occupants[0].length; c++)
         {
            if(occupants[r][c] != null)
            {
               occupants[r][c].setRow(r+capitalRow);   //reset occupant's homeRow and homeCol in case we rotated the castle, shifted if it is a castle in a capital city
               occupants[r][c].setCol(c+capitalCol);
               occupants[r][c].setHomeRow(r+capitalRow);  
               occupants[r][c].setHomeCol(c+capitalCol);
            }
            if(occupants[r][c] != null && !isImpassable(house, r, c) && (house[r][c]==floor.getValue() || house[r][c]==carpet.getValue() || house[r][c]==drawBridge.getValue()))
            {
               NPCPlayer fromArray = occupants[r][c];
               NPCPlayer toAdd = new NPCPlayer(occupants[r][c].getName(), occupants[r][c].getCharIndex(), r+capitalRow, c+capitalCol, mapIndex, occupants[r][c].getHomeRow(),occupants[r][c].getHomeCol(), "castle");
               NPCPlayer.copyStatsTo(fromArray, toAdd);
               toAdd.setMoveType(occupants[r][c].getMoveType());
            
               if(toAdd.getCharIndex() == NPC.JESTER)             //castle jesters are not thieves       
                  toAdd.setReputation(Math.abs(toAdd.getReputation()));
            
               if(mapIndex >= CultimaPanel.civilians.size())
                  CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
               if(!Utilities.NPCAt(r, c, mapIndex))    
                  CultimaPanel.civilians.get(mapIndex).add(toAdd);
            }
         } 
      return bridgeWidth;
   }

   public static int buildTemple(byte[][]house, Terrain edgeType, int randSide, int mapIndex, String provinceName)
   {
      Terrain [] paths =      {TerrainBuilder.getTerrainWithName("INR_$Black_Stone_floor"),           TerrainBuilder.getTerrainWithName("INR_$Blue_Brick_floor"),          TerrainBuilder.getTerrainWithName("TER_$Grassland"), TerrainBuilder.getTerrainWithName("TER_$Dry_grass"), TerrainBuilder.getTerrainWithName("TER_$Sand")};
      Terrain [] paths2 = {TerrainBuilder.getTerrainWithName("INR_$Black_Stone_floor"), TerrainBuilder.getTerrainWithName("INR_$Black_Stone_floor"), TerrainBuilder.getTerrainWithName("INR_$Blue_Brick_floor"), TerrainBuilder.getTerrainWithName("INR_$Blue_Brick_floor"), TerrainBuilder.getTerrainWithName("TER_$Grassland"), TerrainBuilder.getTerrainWithName("TER_$Grassland"), TerrainBuilder.getTerrainWithName("TER_$Dry_grass"), TerrainBuilder.getTerrainWithName("TER_$Dry_grass"), TerrainBuilder.getTerrainWithName("TER_$Sand"), TerrainBuilder.getTerrainWithName("TER_$Sand"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Red_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Yellow_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Blue_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Violet_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Green_Flowers")}; 
      Terrain [] flowers = {TerrainBuilder.getTerrainWithName("TER_$Grassland_Red_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Blue_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Yellow_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Violet_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Green_Flowers")}; 
   
      Terrain [] pathTraps =  {TerrainBuilder.getTerrainWithName("INR_D_T_$Black_Stone_floor_trap"),  TerrainBuilder.getTerrainWithName("INR_D_T_$Blue_Brick_floor_trap"), TerrainBuilder.getTerrainWithName("TER_$Grassland"), TerrainBuilder.getTerrainWithName("TER_$Dry_grass"), TerrainBuilder.getTerrainWithName("TER_$Sand")};
   
      Terrain [] walls =      { TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick")          ,TerrainBuilder.getTerrainWithName("INR_I_B_$Gray_Rock")          ,TerrainBuilder.getTerrainWithName("INR_I_B_$White_Rock")};
      Terrain [] wallSecrets ={ TerrainBuilder.getTerrainWithName("INR_D_B_$White_Brick_Wall")     ,TerrainBuilder.getTerrainWithName("INR_D_B_$Gray_Rock")          ,TerrainBuilder.getTerrainWithName("INR_D_B_$White_Rock")};
      Terrain [] wallTorches ={ TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$White_Brick_Torch"),TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$Gray_Rock_Torch"),TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$White_Rock_Torch")};
   
      Terrain [] waters = {TerrainBuilder.getTerrainWithName("TER_V_$Shallow_water"), TerrainBuilder.getTerrainWithName("TER_V_$Shallow_water"), TerrainBuilder.getTerrainWithName("TER_V_$Shallow_water"), TerrainBuilder.getTerrainWithName("TER_I_L_3_$Lava")}; 
      Terrain pool = waters[(int)(Math.random()*waters.length)];
   
      NPCPlayer[][] occupants = new NPCPlayer[house.length][house[0].length];    //fill with occupants to add to the civilians ArrayList after we complete and possilby rotate the map
      byte[] occTypes = {NPC.BEGGER, NPC.WISE, NPC.WOMAN, NPC.MAN};
      int numPeople = 0;
   
      boolean endGame = false;
      if(provinceName.equals("Skara-Brae"))    //our endgame temple
         endGame = true;
   
      boolean guards = false;
      if(Math.random() < 0.33)
         guards = true;
         
      boolean worshipers = false;
      if(Math.random() < 0.33)
         worshipers = true;
       
      boolean fountain = false;  
       
      int numTries = 0;
      int pathIndex = (int)(Math.random() * paths.length);
      while(paths[pathIndex].getName().equals(edgeType.getName()) && numTries < 1000)
      {
         pathIndex = (int)(Math.random() * paths.length);
         numTries++;
      }
      Terrain path = paths[pathIndex];
   
      Terrain path2 = paths2[(int)(Math.random() * paths2.length)];
      numTries = 0;
      while((path.getName().equals(path2.getName()) || (path.getName().contains("Grassland") && path2.getName().contains("Grassland"))) && numTries < 1000)
      {
         path2 = paths2[(int)(Math.random() * paths2.length)];
         numTries++;
      }
      boolean mixedFlowers = false;
      if(path2.getName().toLowerCase().contains("flower") && Math.random() < 0.5)
         mixedFlowers = true;
   
      int focusRow = house.length/2;      //focal point of the temple (structure, courtyard, etc)
      int focusCol = house[0].length/2;
   
      int wallIndex = (int)(Math.random() * walls.length);
      Terrain wall = walls[wallIndex];
      Terrain wallSecret = wallSecrets[wallIndex];
      Terrain wallTorch = wallTorches[wallIndex];
   
      Terrain torchBase = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch");
   
   //maybe put concentric paths around the courtyard
      if(Math.random() < 0.75)
      {
         int numSquares = (int)(Math.random()*(house.length/2)) + 1;
         int squareSize = 1;
         int squareIncrement = (int)(Math.random()*3) + 2;
         int center = house.length/2;
         if(Math.random() < 0.5)       //concentric circles
         {
            int centerRow = house.length/2;
            int centerCol = house.length/2;
            int startRadius = Player.rollDie(house.length/8 ,house.length/2);
            boolean halfCircle = (Math.random() < 0.25);
            for(int radius = startRadius; radius >= 1; radius--) 
            {
               if(radius%2==0)
                  placeFloorSpot(house, centerRow, centerCol, path2, radius, false, halfCircle);
               else if(mixedFlowers)
                  placeFloorSpot(house, centerRow, centerCol, flowers[(int)(Math.random() * flowers.length)], radius, false, halfCircle);
            }
         }
         else                          //concentric squares
         {
            for(int i=0; i<numSquares; i++)
            {
               for(int r=center-squareSize; r<=center+squareSize; r++)
               {
                  if(!outOfBounds(house, r, center-squareSize) && !outOfBounds(house, r, center+squareSize))
                  {
                     if(mixedFlowers)
                     {
                        house[r][center-squareSize]= flowers[(int)(Math.random() * flowers.length)].getValue();
                        house[r][center+squareSize]= flowers[(int)(Math.random() * flowers.length)].getValue();
                     }
                     else
                     {
                        house[r][center-squareSize]= path2.getValue();
                        house[r][center+squareSize]= path2.getValue();
                     }
                  }
                  else 
                     break;
               }
               for(int c=center-squareSize; c<=center+squareSize; c++)
               {
                  if(!outOfBounds(house, center-squareSize, c) && !outOfBounds(house, center+squareSize, c))
                  {
                     if(mixedFlowers)
                     {
                        house[center-squareSize][c]= flowers[(int)(Math.random() * flowers.length)].getValue();
                        house[center+squareSize][c]= flowers[(int)(Math.random() * flowers.length)].getValue();
                     }
                     else
                     {
                        house[center-squareSize][c]= path2.getValue();
                        house[center+squareSize][c]= path2.getValue();
                     }
                  }
                  else 
                     break;
               }
               squareSize += squareIncrement;
            }
         }  
      }
   //maybe put columns down the path
      int columnSize = (int)(Math.random()*2)+1;
      boolean columns = false;
      int columnPeriod = (int)(Math.random()*8)+2;
      if(Math.random() < 0.75)
         columns = true;
   //maybe put torches on the columns   
      boolean columnTorch = false;   
      if(Math.random() < 0.5)        
         columnTorch = true;
      boolean mazeBuilt = false;
   
   //put down path to the temple
      int pathWidth = (int)(Math.random()*4) + 2;
      int pathLength = ((int)(Math.random()*3) + 1) * (house.length/4);
      if(endGame)          //keep focal point in the center for the endgame temple
         pathLength = house.length/2;
      int center = (house[0].length)/2;
      for(int r=0; r<pathLength; r++)
         for(int c = center - pathWidth/2 - 1; c<= center + pathWidth/2 + 1; c++)
         {
            if(c == center - pathWidth/2 - 1 || c == center + pathWidth/2 + 1)
            {
               if(!outOfBounds(house, r, c))
               {
                  if(r%columnPeriod == 0 && columns)
                  {
                     if(c == center - pathWidth/2 - 1)
                     {
                        if(columnTorch)
                           house[r][c] = wallTorch.getValue();
                        else
                           house[r][c] = wall.getValue();
                        if(columnSize == 2)
                        {
                           if(!outOfBounds(house, r-1, c-1))
                              house[r-1][c-1] = wall.getValue();
                           if(!outOfBounds(house, r-1, c))
                              house[r-1][c] = wall.getValue();
                        }
                        if(guards && !isImpassable(house, r, c-1) && !TerrainBuilder.onWater(house, r, c-1) && Math.random() < 0.5)
                        {
                           occupants[r][c-1] = (new NPCPlayer(NPC.randGuard(), r, c-1, mapIndex, r, c-1, "temple"));
                        }
                     }
                     else if(c == center + pathWidth/2 + 1)
                     {
                        if(columnTorch)
                           house[r][c] = wallTorch.getValue();
                        else
                           house[r][c] = wall.getValue();
                        if(columnSize == 2)
                        {
                           if(!outOfBounds(house, r-1, c+1))
                              house[r-1][c+1] = wall.getValue();
                           if(!outOfBounds(house, r-1, c))
                              house[r-1][c] = wall.getValue();
                        }
                        if(guards && !isImpassable(house, r, c+1)  && !TerrainBuilder.onWater(house, r, c+1) && Math.random() < 0.5)
                           occupants[r][c+1] = (new NPCPlayer(NPC.randGuard(), r, c+1, mapIndex, r, c+1, "temple"));
                     }
                  }
                  else
                  {
                     if(mixedFlowers)
                        house[r][c] = flowers[(int)(Math.random() * flowers.length)].getValue();
                     else
                        house[r][c] = path2.getValue();
                  }
               }
               else
                  house[r][c] = path.getValue();
            }
         }
   
   
   //maybe place a courtyard at the end of the path
      int courtCenterR = pathLength;
      int courtCenterC = center;
      if(pathLength >= house.length - 4)
         courtCenterR = house.length/2;
   
      focusRow = courtCenterR;
      focusCol = courtCenterC;
   
      if(endGame || Math.random() < 0.5)
      {
         int courtBase = (pathWidth*2) + (int)(Math.random()*(pathWidth*2));
         int courtHeight = (pathWidth*2) + (int)(Math.random()*(pathWidth*2));
         boolean cornerTorches = false;      //maybe put torches in the corners
         boolean torchColumn = false;        //column torch or torch in base
         if(Math.random() < 0.75)       
         {
            cornerTorches = true;   
            if(Math.random() < 0.5)
               torchColumn = true;
         }
         for(int r=courtCenterR-courtHeight/2-1; r<=courtCenterR+courtHeight/2+1; r++)
            for(int c = courtCenterC - courtBase/2 - 1; c<= courtCenterC + courtBase/2 + 1; c++)
               if(!outOfBounds(house, r, c))
               {
                  if(mixedFlowers)
                     house[r][c] = flowers[(int)(Math.random() * flowers.length)].getValue();
                  else
                     house[r][c] = path2.getValue();
               }
      
         for(int r=courtCenterR-courtHeight/2; r<=courtCenterR+courtHeight/2; r++)
            for(int c = courtCenterC - courtBase/2; c<= courtCenterC + courtBase/2; c++)
            {
               if((r==courtCenterR-courtHeight/2 && c == courtCenterC - courtBase/2) || (r==courtCenterR-courtHeight/2 && c == courtCenterC + courtBase/2)
               || (r==courtCenterR+courtHeight/2 && c == courtCenterC - courtBase/2) || (r==courtCenterR+courtHeight/2 && c == courtCenterC + courtBase/2))
               {
                  if(torchColumn)
                  {
                     if(!outOfBounds(house, r, c))
                        house[r][c] = wallTorch.getValue();
                     if(columnSize == 2)
                     {
                        if(r==courtCenterR-courtHeight/2 && c == courtCenterC - courtBase/2) 
                        {
                           if(!outOfBounds(house, r-1, c-1))
                              house[r-1][c-1] = wall.getValue();
                           if(!outOfBounds(house, r-1, c))
                              house[r-1][c] = wall.getValue();
                        }
                        else if(r==courtCenterR-courtHeight/2 && c == courtCenterC + courtBase/2) 
                        {
                           if(!outOfBounds(house, r-1, c+1))
                              house[r-1][c+1] = wall.getValue();
                           if(!outOfBounds(house, r-1, c))
                              house[r-1][c] = wall.getValue();
                        }
                        else if(r==courtCenterR+courtHeight/2 && c == courtCenterC - courtBase/2) 
                        {
                           if(!outOfBounds(house, r+1, c-1))
                              house[r+1][c-1] = wall.getValue();
                           if(!outOfBounds(house, r+1, c))
                              house[r+1][c] = wall.getValue();
                        }
                        else if(r==courtCenterR+courtHeight/2 && c == courtCenterC + courtBase/2) 
                        {
                           if(!outOfBounds(house, r+1, c+1))
                              house[r+1][c+1] = wall.getValue();
                           if(!outOfBounds(house, r+1, c))
                              house[r+1][c] = wall.getValue();
                        }
                     }
                  }
                  else
                  {
                     if(!outOfBounds(house, r, c))
                        house[r][c] = torchBase.getValue();
                  }
               }
               else
               {
                  if(!outOfBounds(house, r, c))
                     house[r][c] = path.getValue();
               }
            }
      //maybe put a pool or structure in the center of the courtyard
      
         if(Math.random() < 0.5)
         {
            int centerBase = (int)(Math.random()*(courtBase - 2)) + 1;
            int centerHeight = (int)(Math.random()*(courtHeight - 2)) + 1;
         
            if(Math.random() < 0.5)
               fountain = true;
            for(int r=courtCenterR-centerHeight/2 - 1; r<=courtCenterR+centerHeight/2 + 1; r++)
               for(int c = courtCenterC - centerBase/2 - 1; c<= courtCenterC + centerBase/2 + 1; c++)
                  if(!outOfBounds(house, r, c))
                  {
                     if(mixedFlowers)
                        house[r][c] = flowers[(int)(Math.random() * flowers.length)].getValue();
                     else
                        house[r][c] = path2.getValue();
                  }
            if(fountain)
            {
               for(int r=courtCenterR-centerHeight/2; r<=courtCenterR+centerHeight/2; r++)
                  for(int c = courtCenterC - centerBase/2; c<= courtCenterC + centerBase/2; c++)
                     if(!outOfBounds(house, r, c) && r > 1 && c > 1 && r < house.length-1 && c < house.length -1)
                        house[r][c] = pool.getValue();
               if(Math.random() < 0.5 && !endGame)    //put a statue in the center of the fountain 
               {
                  house[courtCenterR][courtCenterC] = path.getValue();
                  NPCPlayer statue = new NPCPlayer(NPC.randFountainStatue(), courtCenterR, courtCenterC, mapIndex, "temple");
                  statue.setNumInfo(-999);
                  occupants[courtCenterR][courtCenterC]=statue;         
               }
            }
            else     //structure
            {
               boolean secret = false;    //secret opening or regular?
               boolean topOpen = false;
               if(Math.random() < 0.5)
                  topOpen = true;
               boolean rightOpen = false;
               if(Math.random() < 0.5)
                  rightOpen = true;
               boolean bottomOpen = false;
               if(Math.random() < 0.5)
                  bottomOpen = true;
               boolean leftOpen = false;
               if(Math.random() < 0.5)
                  leftOpen = true;
               if(Math.random() < 0.5)
                  secret = true;
               for(int r=courtCenterR-centerHeight/2; r<=courtCenterR+centerHeight/2; r++)
                  for(int c = courtCenterC - centerBase/2; c<= courtCenterC + centerBase/2; c++)
                  {
                     if(!outOfBounds(house, r, c))
                     {
                        if(r>courtCenterR-centerHeight/2 && r<courtCenterR+centerHeight/2 && c > courtCenterC - centerBase/2 && c< courtCenterC + centerBase/2)
                        {
                           if(mixedFlowers)
                              house[r][c] = flowers[(int)(Math.random() * flowers.length)].getValue();
                           else
                              house[r][c] = path2.getValue();
                        }
                        else
                        {
                           house[r][c] = wall.getValue();
                           if(secret == false)
                           {
                              if(topOpen && r==courtCenterR-centerHeight/2 && c==courtCenterC)
                              {
                                 if(mixedFlowers)
                                    house[r][c] = flowers[(int)(Math.random() * flowers.length)].getValue();
                                 else
                                    house[r][c] = path2.getValue();
                              }                                 
                              if(bottomOpen && r==courtCenterR+centerHeight/2 && c==courtCenterC)
                              {
                                 if(mixedFlowers)
                                    house[r][c] = flowers[(int)(Math.random() * flowers.length)].getValue();
                                 else
                                    house[r][c] = path2.getValue();
                              }                                 
                              if(leftOpen && c == courtCenterC - centerBase/2 && r==courtCenterR)
                              {
                                 if(mixedFlowers)
                                    house[r][c] = flowers[(int)(Math.random() * flowers.length)].getValue();
                                 else
                                    house[r][c] = path2.getValue();
                              }                                 
                              if(rightOpen && c == courtCenterC + centerBase/2 && r==courtCenterR)
                              {
                                 if(mixedFlowers)
                                    house[r][c] = flowers[(int)(Math.random() * flowers.length)].getValue();
                                 else
                                    house[r][c] = path2.getValue();
                              }                              
                           }
                           else
                           {
                              int side = (int)(Math.random()*4);
                              if(side==0 && r==courtCenterR-centerHeight/2 && c==courtCenterC)
                                 house[r][c] = wallSecret.getValue();
                              else if(side==1 && r==courtCenterR+centerHeight/2 && c==courtCenterC)
                                 house[r][c] = wallSecret.getValue();
                              else if(side==2 && c == courtCenterC - centerBase/2 && r==courtCenterR)
                                 house[r][c] = wallSecret.getValue(); 
                              else if(side==3 && c == courtCenterC + centerBase/2 && r==courtCenterR)
                                 house[r][c] = wallSecret.getValue();
                           }
                        }
                     }
                  }
            }
            if(Math.random() < 0.5 && !endGame)    //put a statue in the center of the structure 
            {
               NPCPlayer statue = new NPCPlayer(NPC.randFountainStatue(), courtCenterR, courtCenterC, mapIndex, "temple");
               statue.setNumInfo(-999);
               occupants[courtCenterR][courtCenterC]=statue;         
            }
         }
         else if(Math.random() < 0.5 && !endGame)    //put a statue in the center of the structure 
         {
            NPCPlayer statue = new NPCPlayer(NPC.randFountainStatue(), courtCenterR, courtCenterC, mapIndex, "temple");
            statue.setNumInfo(-999);
            occupants[courtCenterR][courtCenterC]=statue;
         } 
         if(endGame) //surround couryard with a wall that is only opened once the player reaches level 50
         {
            for(int r=courtCenterR-courtHeight/2-1; r<=courtCenterR+courtHeight/2+1; r++)
               for(int c = courtCenterC - courtBase/2 - 1; c<= courtCenterC + courtBase/2 + 1; c++)
                  if(!outOfBounds(house, r, c))
                  {
                     if(r==courtCenterR-courtHeight/2-1 || r==courtCenterR+courtHeight/2+1 || c==courtCenterC - courtBase/2 - 1 || c==courtCenterC + courtBase/2 + 1
                     || r==courtCenterR || c==courtCenterC)
                        house[r][c] = TerrainBuilder.getTerrainWithName("INR_I_B_$Night_door_closed").getValue();
                  }
            if(mixedFlowers)
               house[courtCenterR][courtCenterC] = flowers[(int)(Math.random() * flowers.length)].getValue();
            else
               house[courtCenterR][courtCenterC] = path2.getValue();  
            NPCPlayer SkaraBrae = (new NPCPlayer(NPC.DEMONKING, courtCenterR, courtCenterC, mapIndex, courtCenterR, courtCenterC, "temple"));
            SkaraBrae.setWeapon(Weapon.getDragonBolt());
            SkaraBrae.addItem(Item.getRandomStone());
            occupants[courtCenterR][courtCenterC] = SkaraBrae;  
            //place monoliths inside structure
            int dist = courtHeight/2-1;
            if(courtCenterR - dist >= 1 && courtCenterC - dist >= 1)
            {
               house[courtCenterR-dist][courtCenterC-dist] = path2.getValue();
               occupants[courtCenterR-dist][courtCenterC-dist] = (new NPCPlayer(NPC.MONOLITH, courtCenterR-dist, courtCenterC-dist, mapIndex, courtCenterR-dist, courtCenterC-dist, "temple"));
            }
            if(courtCenterR - dist >= 1 && courtCenterC + dist <= house[0].length-2)
            {
               house[courtCenterR-dist][courtCenterC+dist] = path2.getValue();
               occupants[courtCenterR-dist][courtCenterC+dist] = (new NPCPlayer(NPC.MONOLITH, courtCenterR-dist, courtCenterC+dist, mapIndex, courtCenterR-dist, courtCenterC+dist, "temple"));
            }
            if(courtCenterR + dist <= house.length-2 && courtCenterC - dist >= 1)
            {
               house[courtCenterR+dist][courtCenterC-dist] = path2.getValue();
               occupants[courtCenterR+dist][courtCenterC-dist] = (new NPCPlayer(NPC.MONOLITH, courtCenterR+dist, courtCenterC-dist, mapIndex, courtCenterR+dist, courtCenterC-dist, "temple"));
            }
            if(courtCenterR + dist <= house.length-2 && courtCenterC + dist <= house[0].length-2)
            {
               house[courtCenterR+dist][courtCenterC+dist] = path2.getValue();
               occupants[courtCenterR+dist][courtCenterC+dist] = (new NPCPlayer(NPC.MONOLITH, courtCenterR+dist, courtCenterC+dist, mapIndex, courtCenterR+dist, courtCenterC+dist, "temple"));
            }
         } 
      }
      else     //no courtyard, so put a structure at the end of the path
      {
         int strBase = (pathWidth*3) + (int)(Math.random()*(pathWidth*2));
         if(strBase%2!=0)
            strBase++;
         int strHeight = (pathWidth*3) + (int)(Math.random()*(pathWidth*2));
         if(strHeight%2!=0)
            strHeight++;
         int strULRow = pathLength;
         int strULCol = center-(strBase/2);
         double prob = ((Math.random() * 31) + 65) / 100.0;    //the probability that we will put a wall there
      
         focusRow = strULRow + strHeight/2;
         focusCol = strULCol + strBase/2;
      
      //place a room, or a random maze
         if(Math.random() < 0.5)
            placeRoom(house, strULRow, strULCol, strBase, strHeight, wall, null, prob);
         else
         {
            if(Math.random() < 0.5)
            {
               Terrain wall2 = walls[(int)(Math.random() * walls.length)];
               buildMaze(house, wall, wall2, path2, strULRow, strULCol, strHeight, strBase);
            }
            else
            {
               Terrain wall2 = TerrainBuilder.getTerrainWithName("TER_S_B_$Dense_forest");
               buildMaze(house, wall2, wall2, path2, strULRow, strULCol, strHeight, strBase);
            }
            mazeBuilt = true;
         }
      
         if(!mazeBuilt)
         {
         //maybe make the floor paved, not paved or mixed (overgrown)
            for(int c=center-pathWidth+1; c<=center+pathWidth-1; c++)
               house[strULRow][c] = path2.getValue();
            int floorType = (int)(Math.random() * 4);
            if(floorType > 0)
            {
               for(int r=strULRow+1; r<=strULRow+strHeight-1; r++)
               {
                  for(int c=strULCol+1; c<=strULCol+strBase-1; c++)
                  {
                     if(!outOfBounds(house, r, c))
                     {
                        if(floorType==1)
                           house[r][c] = path.getValue();
                        else if(floorType==2)
                           house[r][c] = path2.getValue();
                        else if(!(CultimaPanel.allTerrain.get(Math.abs(house[r-1][c])).getName().equals(edgeType.getName())))
                           house[r][c] = path2.getValue();
                     }
                  }
               }
            } 
         //maybe fill with columns, a fountain, other rooms 
            columns = false;
         
            if(Math.random() < 0.5)
               columns = true;
            fountain = false;
            if(Math.random() < 0.5)
               fountain = true;
            if(fountain)
            {
               int fountCenterR = strULRow + (strHeight/2);
               int fountCenterC = strULCol + (strBase/2);
               int fountHeight = (int)(Math.random()*(strHeight-2));
               int fountBase = (int)(Math.random()*(strBase-2));
            
               for(int r=fountCenterR-(fountHeight/2)-1; r<=fountCenterR+(fountHeight/2)+1; r++)
                  for(int c=fountCenterC-(fountBase/2)-1; c<=fountCenterC+(fountBase/2)+1; c++)
                     if(!outOfBounds(house, r, c))
                     {
                        if(CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().equals(path.getName()))
                        {
                           if(mixedFlowers)
                              house[r][c] = flowers[(int)(Math.random() * flowers.length)].getValue();
                           else
                              house[r][c] = path2.getValue();
                        }
                        else
                           house[r][c] = path.getValue();
                     }
               for(int r=fountCenterR-(fountHeight/2); r<=fountCenterR+(fountHeight/2); r++)
                  for(int c=fountCenterC-(fountBase/2); c<=fountCenterC+(fountBase/2); c++)
                     if(!outOfBounds(house, r, c) && r > 1 && c > 1 && r < house.length-1 && c < house.length -1)
                        house[r][c] = pool.getValue();
            }
            else //place rooms inside of rooms
            {
               int numRooms = (int)(Math.random() * (strBase/2)) + 1;
               int roomULRow = strULRow + 2;
               int roomULCol = strULCol + 2;
               int roomBase = strBase - ((int)(Math.random()*3)+2);
               int roomHeight = strHeight - ((int)(Math.random()*3)+2);
               int dist = ((int)(Math.random()*3)+2);
               for(int i=0; i<numRooms && roomBase>0 && roomHeight>0; i++)
               {
                  if(outOfBounds(house, roomULRow, roomULCol) || outOfBounds(house, roomULRow+roomHeight, roomULCol+roomBase))
                     break;
                  placeRoom(house, roomULRow, roomULCol, roomBase, roomHeight, wall, null, 0.75);
                  roomULRow -= dist;
                  roomULCol -= dist;
                  roomBase  -= dist;
                  roomHeight-= dist;
               }
            
            }
            if(columns)
            {
            
               int colCenterR = strULRow + (strHeight/2);
               int colCenterC = strULCol + (strBase/2);
               int colHeight = (int)(Math.random()*(strHeight-2));
               int colBase = (int)(Math.random()*(strBase-2));
            
               columnSize = (int)(Math.random()*2)+1;
               columnPeriod = (int)(Math.random()*6)+2;
            //maybe put torches on the columns   
               columnTorch = false;   
               if(Math.random() < 0.5)        
                  columnTorch = true;
            
               for(int r=colCenterR-(colHeight/2); r<=colCenterR+(colHeight/2); r++)
                  for(int c = colCenterC -(colBase/2); c<= colCenterC + (colBase/2); c++)
                  {
                     if(c == colCenterC -(colBase/2) || c == colCenterC + (colBase/2))
                     {
                        if(r%columnPeriod == 0 && !outOfBounds(house, r, c))
                        {
                           if(c == colCenterC -(colBase/2))
                           {
                              if(columnTorch)
                                 house[r][c] = wallTorch.getValue();
                              else
                                 house[r][c] = wall.getValue();
                              if(columnSize == 2)
                              {
                                 if(!outOfBounds(house, r-1, c-1))
                                    house[r-1][c-1] = wall.getValue();
                                 if(!outOfBounds(house, r-1, c))
                                    house[r-1][c] = wall.getValue();
                              }
                              if(guards && !isImpassable(house, r, c-1) && !TerrainBuilder.onWater(house, r, c-1) && Math.random() < 0.5)
                                 occupants[r][c-1] = (new NPCPlayer(NPC.randGuard(), r, c-1, mapIndex, r, c-1, "temple"));
                           }
                           else if(c == colCenterC + (colBase/2))
                           {
                              if(columnTorch)
                                 house[r][c] = wallTorch.getValue();
                              else
                                 house[r][c] = wall.getValue();
                              if(columnSize == 2)
                              {
                                 if(!outOfBounds(house, r-1, c+1))
                                    house[r-1][c+1] = wall.getValue();
                                 if(!outOfBounds(house, r-1, c))
                                    house[r-1][c] = wall.getValue();
                              }
                              if(guards && !isImpassable(house, r, c+1) && !TerrainBuilder.onWater(house, r, c+1) && Math.random() < 0.5)
                                 occupants[r][c+1] = (new NPCPlayer(NPC.randGuard(), r, c+1, mapIndex, r, c+1, "temple"));
                           }
                        }
                     }
                  }
            } 
         }      
      } 
      boolean addDungeon = false;
      if(!endGame && (Math.random() < 0.5 || (mazeBuilt && Math.random() < 0.75)))
         addDungeon = true;
           
      //maybe put a bridgekeeper on a bridge across a moat
      double bridgeProb = 0.25;   
      if((fountain && Math.random() < bridgeProb) || endGame)
      {
         int moatWidth = Player.rollDie(3,5);
         Terrain drawBridge = TerrainBuilder.getTerrainWithName("INR_$Wood_Plank_floor");
         Terrain trench = TerrainBuilder.getTerrainWithName("TER_I_$Deep_water");
         Terrain[] trenchWaters = {TerrainBuilder.getTerrainWithName("TER_I_$Deep_water"), TerrainBuilder.getTerrainWithName("TER_I_$Rapid_water")};
         if(pool.getName().toLowerCase().contains("lava"))
            trench = TerrainBuilder.getTerrainWithName("TER_I_L_3_$Lava");
              //extend the path a little past the bridge
         for(int r=house.length-2-(moatWidth*2); r<house.length; r++)
            house[r][center] = path2.getValue();
         for(int r=house.length-2-moatWidth; r<=house.length-2; r++)
         {
            for(int c=0; c<house[0].length; c++)
            {
               if (isImpassable(house, r, c))
                  continue;
               if(c != center)
               {
                  if(trench.getName().toLowerCase().contains("water"))
                     house[r][c] = trenchWaters[(int)(Math.random()*trenchWaters.length)].getValue();
                  else
                     house[r][c] = trench.getValue();
               }
               else
               {
                  house[r][c] = drawBridge.getValue();
                  if(r == ((house.length-2-moatWidth)  + (house.length-2))/2)
                  {
                     NPCPlayer bridgeKeeper = new NPCPlayer(NPC.WISE, r, c, mapIndex, r, c, "temple");
                     bridgeKeeper.setMoveType(NPC.STILL);
                     bridgeKeeper.setNumInfo(4);
                     bridgeKeeper.setName("Keeper of the Bridge");
                     bridgeKeeper.setReputation(CultimaPanel.player.getReputation());
                     occupants[r][c] = bridgeKeeper;
                  }
               }
            }
         }
      }      
      if(randSide != 0)
      {
         byte[][] temp = new byte[house.length][house[0].length];
         if(focusRow >=0 && focusCol >= 0 && focusRow < house.length && focusCol < house[0].length)
            temp[focusRow][focusCol] = 1;
         if(randSide == 1)
         {
            rotateRight(house);
            rotateRight(occupants);
            if(addDungeon)
               rotateRight(temp);
         }
         else if(randSide == 2)
         {
            flipUpsideDown(house);
            flipUpsideDown(occupants);
            if(addDungeon)
               flipUpsideDown(temp);
         }
         else if(randSide == 3)
         {
            rotateLeft(house);
            rotateLeft(occupants);
            if(addDungeon)
               rotateLeft(temp);
         }
         if(addDungeon)
         {
            for(int r=0; r<temp.length; r++)
            {
               boolean breakOut = false;
               for(int c=0; c<temp.length; c++)
               {
                  if(temp[r][c]==1)
                  {
                     focusRow = r;
                     focusCol = c;
                     breakOut = true;
                     break;
                  }
               }
               if(breakOut)
                  break;
            }
         }
      }
   
   //maybe put an enterance to a dungeon here
      if(addDungeon)
      {
         int doorRow = focusRow;   //find a place where there is an open spot to get to the dungeon entrance
         int doorCol = focusCol;
         while(!outOfBounds(house, doorRow, doorCol) && blockedIn(house, doorRow, doorCol))
         {
            if(randSide==0)
               doorRow++;
            else if(randSide==1)
               doorCol--;
            else if(randSide==2)
               doorRow--;
            else
               doorCol++;
         }
         if(!outOfBounds(house, doorRow, doorCol) && clearOfMoatArea(house, doorRow, doorCol))  //make sure dungeon entrance is not out of bounds or on a bridge over a trench
         {
         //maybe put guards at a Temple dungeon enterance
            if(guards)
            {
               if(!isImpassable(house, doorRow-1, doorCol)  && !TerrainBuilder.onWater(house, doorRow-1, doorCol) && Math.random() < 0.5)
                  occupants[doorRow-1][doorCol] = (new NPCPlayer(NPC.randGuard(), doorRow-1, doorCol, mapIndex, doorRow-1, doorCol, "temple"));
               if(!isImpassable(house, doorRow+1, doorCol)  && !TerrainBuilder.onWater(house, doorRow+1, doorCol) && Math.random() < 0.5)
                  occupants[doorRow+1][doorCol] = (new NPCPlayer(NPC.randGuard(), doorRow+1, doorCol, mapIndex, doorRow+1, doorCol, "temple"));
               if(!isImpassable(house, doorRow, doorCol-1)  && !TerrainBuilder.onWater(house, doorRow, doorCol-1) && Math.random() < 0.5)
                  occupants[doorRow][doorCol-1] = (new NPCPlayer(NPC.randGuard(), doorRow, doorCol-1, mapIndex, doorRow, doorCol-1, "temple"));
               if(!isImpassable(house, doorRow, doorCol+1)  && !TerrainBuilder.onWater(house, doorRow, doorCol+1) && Math.random() < 0.5)
                  occupants[doorRow][doorCol+1] = (new NPCPlayer(NPC.randGuard(), doorRow, doorCol+1, mapIndex, doorRow, doorCol+1, "temple"));
            }
            Terrain ter = TerrainBuilder.getTerrainWithName("LOC_$Dungeon_enterance");
            house[doorRow][doorCol] = ter.getValue();
            String name = provinceName + " " +Utilities.dungeonSuffix[(int)(Math.random()* Utilities.dungeonSuffix.length)];
         
         //assign teleporter to a new world or location
            Teleporter teleporter = new Teleporter((CultimaPanel.map).size());
         
            CultimaPanel.allLocations.add(new Location(name, doorRow, doorCol, mapIndex, ter, teleporter));
            FileManager.writeOutLocationsToFile(CultimaPanel.allLocations, "maps/worldLocs.txt");
            
            //maybe add monoliths around the enterance
            if(!worshipers && Math.random()<0.25)
            {
               int dist = Player.rollDie(1,5);
               if(doorRow - dist >= 1 && doorCol - dist >= 1 && !isImpassable(house, doorRow-dist, doorCol-dist))
                  occupants[doorRow-dist][doorCol-dist] = (new NPCPlayer(NPC.MONOLITH, doorRow-dist, doorCol-dist, mapIndex, doorRow-dist, doorCol-dist, "temple"));
               if(doorRow - dist >= 1 && doorCol + dist <= house[0].length-2 && !isImpassable(house, doorRow-dist, doorCol+dist))
                  occupants[doorRow-dist][doorCol+dist] = (new NPCPlayer(NPC.MONOLITH, doorRow-dist, doorCol+dist, mapIndex, doorRow-dist, doorCol+dist, "temple"));
               if(doorRow + dist <= house.length-2 && doorCol - dist >= 1 && !isImpassable(house, doorRow+dist, doorCol-dist))
                  occupants[doorRow+dist][doorCol-dist] = (new NPCPlayer(NPC.MONOLITH, doorRow+dist, doorCol-dist, mapIndex, doorRow+dist, doorCol-dist, "temple"));
               if(doorRow + dist <= house.length-2 && doorCol + dist <= house[0].length-2 && !isImpassable(house, doorRow+dist, doorCol+dist))
                  occupants[doorRow+dist][doorCol+dist] = (new NPCPlayer(NPC.MONOLITH, doorRow+dist, doorCol+dist, mapIndex, doorRow+dist, doorCol+dist, "temple"));
            } 
         }
      }   
      if(worshipers)
      {
         int numPpl = (int)(Math.random()*10) + 1;
         for(int i=0; i<numPpl; i++)
         {
            int r=Player.rollDie(2,house.length-2);
            int c=Player.rollDie(2,house[0].length-2);
            int numTimes = 0;
            boolean found = true;
            while(isImpassable(house, r, c) || TerrainBuilder.onWater(house, r, c) || occupants[r][c]!=null)
            {
               r=Player.rollDie(2,house.length-2);
               c=Player.rollDie(2,house[0].length-2);
               numTimes++;
               if(numTimes >= 1000)
               {
                  found=false;
                  break;
               }
            }
            byte charIndex = occTypes[(int)(Math.random()*occTypes.length)];
            if(i==0)
               charIndex = NPC.WISE;      //at least one wise man
            occupants[r][c] = new NPCPlayer(charIndex, r, c, mapIndex, r, c, "temple");
         }
      }   
      
      for(int r=0; r<occupants.length; r++)
         for(int c=0; c<occupants[0].length; c++)
            if(occupants[r][c] != null)
            {
               if(mapIndex >= CultimaPanel.civilians.size())
                  CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
               occupants[r][c].setRow(r);
               occupants[r][c].setCol(c);
               occupants[r][c].setHomeRow(r);
               occupants[r][c].setHomeCol(c);   
               NPCPlayer toAdd = occupants[r][c]; 
               if(endGame && toAdd.getCharIndex()==NPC.DEMONKING)
               {
                  toAdd.setName("Skara Brae");
                  toAdd.setMoveType(NPC.STILL);
                  toAdd.setBody(1000);
                  toAdd.setGold(1000);
                  toAdd.setWeapon(Weapon.getFlamebow());
                  toAdd.setArmor(Armor.getDragonScale());
                  toAdd.addItem(new Item("blessed-crown", 10000));
                  if(CultimaPanel.player.getReputation() < 0)
                     toAdd.setReputation(-2000);
                  else
                     toAdd.setReputation(2000);    
               }
               if(!Utilities.NPCAt(r, c, mapIndex))    
               {
                  CultimaPanel.civilians.get(mapIndex).add(toAdd);
                  numPeople++;
               }
            }
      return numPeople;
   }

//if there is a door with less than two walls on either side, turn it into a floor
   public static void fixBrokenDoors(byte[][]house, Terrain floor)
   {
      for(int r=0; r<house.length; r++)
      {
         for(int c=0; c<house[0].length; c++)
         {
            if(CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().toLowerCase().contains("door"))
            {
               int numWalls = 0;
               if(r-1 >= 0 && (CultimaPanel.allTerrain.get(Math.abs(house[r-1][c])).getName().contains("_B_")))
                  numWalls++; 
               if(r+1 < house.length && (CultimaPanel.allTerrain.get(Math.abs(house[r+1][c])).getName().contains("_B_")))
                  numWalls++; 
               if(c-1 >= 0 && (CultimaPanel.allTerrain.get(Math.abs(house[r][c-1])).getName().contains("_B_")))
                  numWalls++; 
               if(c+1 < house[0].length && (CultimaPanel.allTerrain.get(Math.abs(house[r][c+1])).getName().contains("_B_")))
                  numWalls++;
               if(numWalls != 2)
               {
                  house[r][c] = floor.getValue();
               }
            }
         }
      }
   }

//remove any floor torches that were placed right next to a door
   public static void fixBrokenTorches(byte[][]house, Terrain floor)
   {
      Terrain floorTorch = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch");
      for(int r=0; r<house.length; r++)
         for(int c=0; c<house[0].length; c++)
            if(Math.abs(house[r][c]) == floorTorch.getValue() && nextToADoor(house, r, c))
               house[r][c] = floor.getValue();
   }

   public static Terrain buildWall(byte[][]house, int enteranceRow, int enteranceCol, String locType, int mapIndex, boolean capitalCity)
   {
      NPCPlayer[][] occupants = new NPCPlayer[house.length][house[0].length];    //fill with occupants to add to the civilians ArrayList after we complete and possilby rotate the map
      boolean guards = false;
      if(capitalCity)
         guards = true;
      else if(locType.contains("village") && Math.random() < 0.25)
         guards = true;
      else if(locType.contains("city") && Math.random() < 0.5)
         guards = true;
      else if(locType.contains("port") && Math.random() < 0.75)
         guards = true;
      else if(locType.contains("fortress") && Math.random() < 0.9)
         guards = true;     
      int border = (int)(Math.random() * 8) + 2;      //number of steps in before we get to the wall
      double wallChance = 0.98;                       //probability that a city wall is not broken
      if(locType.contains("village") || locType.contains("port"))
         border = 2;
      if(!locType.contains("village") && !locType.contains("port"))
      {   
         Terrain wall = TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick");
         Terrain brokenWall = TerrainBuilder.getTerrainWithName("INR_D_B_$White_Brick_Wall");
         if(Math.random() < 0.5)
         {
            wall = TerrainBuilder.getTerrainWithName("INR_I_B_$White_Rock");
            brokenWall = TerrainBuilder.getTerrainWithName("INR_D_B_$White_Rock");
         }
         for(int r=border; r<house.length-border; r++)
         { 
            if(clearOfMoatArea(house, r, border))  //make sure we don't build a wall in a port (water or pier)
            {
               if(Math.random() < wallChance)
                  house[r][border] = wall.getValue();
               else
                  house[r][border] = brokenWall.getValue();
            }
            if(clearOfMoatArea(house, r, house[0].length - border))
            {
               if(Math.random() < wallChance)
                  house[r][house[0].length - border] = wall.getValue();
               else
                  house[r][house[0].length - border] = brokenWall.getValue();
            }
         }
         for(int c=border; c<house[0].length-border; c++)
         {  
            if(clearOfMoatArea(house, border, c))  //make sure we don't build a wall in a port (water or pier)
            {  
               if(Math.random() < wallChance)
                  house[border][c] = wall.getValue();
               else
                  house[border][c] = brokenWall.getValue();
            }
            if(clearOfMoatArea(house, house.length - border,  c))
            {
               if(Math.random() < wallChance)
                  house[house.length - border][c] = wall.getValue();
               else
                  house[house.length - border][c] = brokenWall.getValue();
            }
         }
         if(clearOfMoatArea(house, house.length - border,  house[0].length-border))
            house[house.length - border][house[0].length-border] = wall.getValue();
      }
   //put down main roads in/out of the city
      int enteranceSize = (int)(Math.random()*4) + 2;  //pathway entering city
      if(locType.contains("village"))
         enteranceSize = (int)(Math.random()*2) + 2;
      
      int center = house.length/2;
   //lay down a slightly wider path of dry grass before a paved path might go over it
      for(int r = border+1; r < house.length - (border + 1); r++)
         for(int c = center - (enteranceSize/2) - 1; c<= (center+enteranceSize/2)+1; c++)
            if(clearOfMoatArea(house, r, c))
               house[r][c] = TerrainBuilder.getTerrainWithName("TER_$Dry_grass").getValue();
      for(int r = center - (enteranceSize/2) - 1; r<= (center+enteranceSize/2)+1; r++)
         for(int c = border+1; c < house[0].length - (border + 1); c++)
            if(clearOfMoatArea(house, r, c))
               house[r][c] = TerrainBuilder.getTerrainWithName("TER_$Dry_grass").getValue();
      if(capitalCity)
      {
         for(int r = border+1; r < house.length - (border + 1); r++)
            for(int c = (center-center/2) - (enteranceSize/2) - 1; c<= ((center-center/2)+enteranceSize/2)+1; c++)
               if(clearOfMoatArea(house, r, c))
                  house[r][c] = TerrainBuilder.getTerrainWithName("TER_$Dry_grass").getValue();
         for(int r = border+1; r < house.length - (border + 1); r++)
            for(int c = (center+center/2) - (enteranceSize/2) - 1; c<= ((center+center/2)+enteranceSize/2)+1; c++)
               if(clearOfMoatArea(house, r, c))
                  house[r][c] = TerrainBuilder.getTerrainWithName("TER_$Dry_grass").getValue();
         for(int r = (center-center/2) - (enteranceSize/2) - 1; r<= ((center-center/2)+enteranceSize/2)+1; r++)
            for(int c = border+1; c < house[0].length - (border + 1); c++)
               if(clearOfMoatArea(house, r, c))
                  house[r][c] = TerrainBuilder.getTerrainWithName("TER_$Dry_grass").getValue();
         for(int r = (center+center/2) - (enteranceSize/2) - 1; r<= ((center+center/2)+enteranceSize/2)+1; r++)
            for(int c = border+1; c < house[0].length - (border + 1); c++)
               if(clearOfMoatArea(house, r, c))
                  house[r][c] = TerrainBuilder.getTerrainWithName("TER_$Dry_grass").getValue();
      }
   
      Terrain[]floors = {TerrainBuilder.getTerrainWithName("INR_$Blue_Brick_floor"), TerrainBuilder.getTerrainWithName("INR_$Red_Brick_floor")}; 
      Terrain[]floorsVillage = {TerrainBuilder.getTerrainWithName("INR_$Red_Brick_floor"), TerrainBuilder.getTerrainWithName("TER_$Dry_grass")}; 
      Terrain path = floors[(int)(Math.random()*floors.length)];
      if(locType.contains("village"))
         path = floorsVillage[(int)(Math.random()*floorsVillage.length)];
   
      if(capitalCity)      //maybe add side-streets to a capital city
      {
         boolean street1 = false;
         boolean street2 = false;
         boolean street3 = false;
         boolean street4 = false;
         if(Math.random() < 0.66)
            street1 = true;
         if(Math.random() < 0.66)
            street2 = true;
         if(Math.random() < 0.66)
            street3 = true;
         if(Math.random() < 0.66)
            street4 = true;
         if(street1)
            for(int r = border+1; r < house.length - (border + 1); r++)
               for(int c = (center-center/2) - (enteranceSize/2); c<= ((center-center/2)+enteranceSize/2); c++)
                  if(clearOfMoatArea(house, r, c))
                     house[r][c] = path.getValue();        
         if(street2)
            for(int r = border+1; r < house.length - (border + 1); r++)
               for(int c = (center+center/2) - (enteranceSize/2); c<= ((center+center/2)+enteranceSize/2); c++)
                  if(clearOfMoatArea(house, r, c))
                     house[r][c] = path.getValue();
         if(street3)
            for(int r = (center-center/2) - (enteranceSize/2); r<= ((center-center/2)+enteranceSize/2); r++)
               for(int c = border+1; c < house[0].length - (border + 1); c++)
                  if(clearOfMoatArea(house, r, c))
                     house[r][c] = path.getValue();
         if(street4)
            for(int r = (center+center/2) - (enteranceSize/2); r<= ((center+center/2)+enteranceSize/2); r++)
               for(int c = border+1; c < house[0].length - (border + 1); c++)
                  if(clearOfMoatArea(house, r, c))
                     house[r][c] = path.getValue();
      }     //end -maybe add side-streets to a capital city
   
   //add main street and enterances to the city
      boolean northEnterance = false;
      boolean southEnterance = false;
      boolean eastEnterance = false;
      boolean westEnterance = false;
      if(capitalCity)
      {
         if(Math.random() < 0.75)
            northEnterance = true;
         if(Math.random() < 0.75)
            southEnterance = true;
         if(Math.random() < 0.75)
            eastEnterance = true;
         if(Math.random() < 0.75)
            westEnterance = true;
      }
      else
      {
         if(Math.random() < 0.5)
            northEnterance = true;
         if(Math.random() < 0.5)
            southEnterance = true;
         if(Math.random() < 0.5)
            eastEnterance = true;
         if(Math.random() < 0.5)
            westEnterance = true;
      }
      if(enteranceRow == 0 || northEnterance)         //entering North side moving South
      {
         center = house[0].length/2;
         int lastRow = house.length;                  //road coming in either goes all the way through
         if(Math.random() < 0.5)                      //or stops in the city center
            lastRow = house.length / 2 + 1;
      
         for(int r=0; r<lastRow; r++)
            for(int c=center - enteranceSize/2; c<=center+enteranceSize/2; c++)
            {  //put torches at the enterance
               if(TerrainBuilder.getTerrainWithValue(house[r][c]).getName().contains("_I_") && r<=border 
               && clearOfMoatArea(house, r, c))    //there is a wall that our path is breaking through
               {  //place torches on either side of the path next to the enterance at the wall
                  if((c==center-enteranceSize/2) && r-1 >= 0 && c-1 >= 0)
                  {
                     if((!locType.contains("village") || Math.random() < 0.5) &&  clearOfMoatArea(house, r-1, c-1))                              //left side
                        house[r-1][c-1] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
                     if(guards && Math.random()<0.5 && LocationBuilder.canSpawnOn(house, r, c))
                        occupants[r][c] = (new NPCPlayer(NPC.randGuard(), r, c, mapIndex, r, c, locType));   
                  }   
                  if((c==center+enteranceSize/2) && r-1 >= 0 && c+1 < house[0].length)
                  {
                     if((!locType.contains("village") || Math.random() < 0.5) &&  clearOfMoatArea(house, r-1, c+1))                                //right side
                        house[r-1][c+1] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
                     if(guards && Math.random()<0.5 && LocationBuilder.canSpawnOn(house, r, c))
                        occupants[r][c] = (new NPCPlayer(NPC.randGuard(), r, c, mapIndex, r, c, locType));
                  }
               }
               if(clearOfMoatArea(house, r, c))
                  house[r][c] = path.getValue();
            }
      }
      if(guards && Math.random() < 0.5 && LocationBuilder.canSpawnOn(house, border, center))           //add a roaming guard moving south
      {
         occupants[border][center] = (new NPCPlayer(NPC.randGuard(), border, center, mapIndex, border, center, locType));
         occupants[border][center].setMoveType(NPC.MARCH_SOUTH);
      }
      if(enteranceCol == house[0].length - 1 || eastEnterance)    //entering East side moving West
      {
         int firstCol = house[0].length/2 - 1;
         if(Math.random() < 0.5)
            firstCol = 0;
      
         for(int r=center-enteranceSize/2; r<=center+enteranceSize/2; r++)
            for(int c=firstCol; c<house[0].length; c++)
            {  //put torches at the enterance
               if(TerrainBuilder.getTerrainWithValue(house[r][c]).getName().contains("_I_") && c>=house[0].length - border
               && clearOfMoatArea(house, r, c))    //there is a wall that our path is breaking through
               {  //place torches on either side of the path next to the enterance at the wall
                  if(r==(center-enteranceSize/2) && r-1 >= 0 && c+1 < house[0].length)
                  {
                     if((!locType.contains("village") || Math.random() < 0.5) && clearOfMoatArea(house, r-1, c+1))
                        house[r-1][c+1] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
                     if(guards && Math.random()<0.5 && LocationBuilder.canSpawnOn(house, r, c))
                        occupants[r][c] = (new NPCPlayer(NPC.randGuard(), r, c, mapIndex, r, c, locType));
                  }   
                  if(r==(center+enteranceSize/2) && r+1 < house.length && c+1 < house[0].length)
                  {
                     if((!locType.contains("village") || Math.random() < 0.5) &&clearOfMoatArea(house, r+1, c+1))
                        house[r+1][c+1] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
                     if(guards && Math.random()<0.5 && LocationBuilder.canSpawnOn(house, r, c))
                        occupants[r][c] = (new NPCPlayer(NPC.randGuard(), r, c, mapIndex, r, c, locType));
                  }
               }
               if(clearOfMoatArea(house, r, c))
                  house[r][c] = path.getValue();
            }
      }
      if(guards && Math.random() < 0.5 && LocationBuilder.canSpawnOn(house, center, house[0].length - border))           //add a roaming guard moving west
      {
         occupants[center][house[0].length - border] = (new NPCPlayer(NPC.randGuard(), center, house[0].length - border, mapIndex, center, house[0].length - border, locType));
         occupants[center][house[0].length - border].setMoveType(NPC.MARCH_WEST);
      }
      if(enteranceRow == house.length - 1 || southEnterance)       //entering South side moving North
      {
         center = house[0].length/2;
         int firstRow = house.length/2 - 1;           //road coming in either goes all the way through
         if(Math.random() < 0.5)                      //or stops in the city center
            firstRow = 0;
      
         for(int r=firstRow; r<house.length; r++)
            for(int c=center - enteranceSize/2; c<=center+enteranceSize/2; c++)
            {  //put torches at the enterance
               if(TerrainBuilder.getTerrainWithValue(house[r][c]).getName().contains("_I_") && r>=house.length - border
               && clearOfMoatArea(house, r, c))    //there is a wall that our path is breaking through
               {  //place torches on either side of the path next to the enterance at the wall
                  if(c==(center - enteranceSize/2) && r+1 <house.length && c-1 >= 0)
                  {
                     if((!locType.contains("village") || Math.random() < 0.5) &&clearOfMoatArea(house, r+1, c-1))
                        house[r+1][c-1] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
                     if(guards && Math.random()<0.5 && LocationBuilder.canSpawnOn(house, r, c))
                        occupants[r][c] = (new NPCPlayer(NPC.randGuard(), r, c, mapIndex, r, c, locType));
                  }   
                  if(c==(center + enteranceSize/2) && r+1 <house.length && c+1 < house[0].length)
                  {
                     if((!locType.contains("village") || Math.random() < 0.5)&&clearOfMoatArea(house, r+1, c+1))
                        house[r+1][c+1] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
                     if(guards && Math.random()<0.5 && LocationBuilder.canSpawnOn(house, r, c))
                        occupants[r][c] = (new NPCPlayer(NPC.randGuard(), r, c, mapIndex, r, c, locType));
                  }
               }
               if(clearOfMoatArea(house, r, c))
                  house[r][c] = path.getValue();
            }
      }
      if(guards && Math.random() < 0.5 && LocationBuilder.canSpawnOn(house, house.length - border, center))           //add a roaming guard moving north
      {
         occupants[house.length - border][center] = (new NPCPlayer(NPC.randGuard(), house.length - border, center, mapIndex, house.length - border, center, locType));
         occupants[house.length - border][center].setMoveType(NPC.MARCH_NORTH);
      }
      if(enteranceCol == 0 || westEnterance)      //entering West side moving East
      {
         int lastCol = house[0].length/2 + 1;
         if(Math.random() < 0.5)
            lastCol = house[0].length;
      
         for(int r=center-enteranceSize/2; r<=center+enteranceSize/2; r++)
            for(int c=0; c<lastCol; c++)
            {  //put torches at the enterance
               if(TerrainBuilder.getTerrainWithValue(house[r][c]).getName().contains("_I_") && c <= border
               && clearOfMoatArea(house, r, c))    //there is a wall that our path is breaking through
               {  //place torches on either side of the path next to the enterance at the wall
                  if(r==(center-enteranceSize/2) && r-1 >= 0 && c-1 >= 0)
                  {
                     if((!locType.contains("village") || Math.random() < 0.5)&&clearOfMoatArea(house, r-1, c-1))
                        house[r-1][c-1] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
                     if(guards && Math.random()<0.5 && LocationBuilder.canSpawnOn(house, r, c))
                        occupants[r][c] = (new NPCPlayer(NPC.randGuard(), r, c, mapIndex, r, c, locType));
                  }   
                  if(r==(center+enteranceSize/2) && r+1 < house.length && c-1 >= 0)
                  {
                     if((!locType.contains("village") || Math.random() < 0.5)&&clearOfMoatArea(house, r+1, c-1))
                        house[r+1][c-1] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
                     if(guards && Math.random()<0.5 && LocationBuilder.canSpawnOn(house, r, c))
                        occupants[r][c] = (new NPCPlayer(NPC.randGuard(), r, c, mapIndex, r, c, locType));
                  }
               }
               if(clearOfMoatArea(house, r, c))
                  house[r][c] = path.getValue();
            }
      }
      if(guards && Math.random() < 0.5 && LocationBuilder.canSpawnOn(house, center, border))           //add a roaming guard moving east
      {
         occupants[center][border] = (new NPCPlayer(NPC.randGuard(), center, border, mapIndex, center, border, locType));
         occupants[center][border].setMoveType(NPC.MARCH_EAST);
      }
           
   //maybe put in a courtyard in the middle of the city
      int numCourtYards = 1;
      if(capitalCity)
         numCourtYards = Player.rollDie(0, 8);
      ArrayList<Point> intersections = new ArrayList<Point>();
      intersections.add(new Point(house.length/4, house[0].length/4));   
      intersections.add(new Point(house.length/4, house[0].length/2));   
      intersections.add(new Point(house.length/4, house[0].length*3/4));   
      intersections.add(new Point(house.length/2, house[0].length/4));   
      intersections.add(new Point(house.length/2, house[0].length*3/4));   
      intersections.add(new Point(house.length*3/4, house[0].length/4));   
      intersections.add(new Point(house.length*3/4, house[0].length/2));   
      intersections.add(new Point(house.length*3/4, house[0].length*3/4));
   
      for(int i=0; i<numCourtYards; i++)
      {
         if(locType.contains("city") || capitalCity)
         {
            int courtYardSize = enteranceSize + (int)(Math.random()*enteranceSize);
            int centerR,centerC;
            if(!capitalCity)
            {
               centerR = house.length/2;
               centerC = house[0].length/2;
            }
            else
            {
               if(intersections.size()==0)
                  break;
               Point intersect = intersections.remove((int)(Math.random()*intersections.size()));
               centerR = (int)(intersect.getX());
               centerC = (int)(intersect.getY());
            }
            for(int r=centerR - courtYardSize; r<=centerR + courtYardSize; r++)
               for(int c=centerC - courtYardSize; c<=centerC + courtYardSize; c++)
                  house[r][c] = path.getValue();
            int middle = (int)(Math.random()*4);   //what is in the middle of the courtyard
            if(capitalCity)
               middle = (int)(Math.random()*2);
            if(middle == 0)                        //fountain
            {
               int fountainSize = (int)(Math.random() * (courtYardSize - 2)) + 1;
               Terrain [] waters = {TerrainBuilder.getTerrainWithName("TER_V_$Shallow_water"), TerrainBuilder.getTerrainWithName("TER_V_$Shallow_water"), TerrainBuilder.getTerrainWithName("TER_V_$Shallow_water"), TerrainBuilder.getTerrainWithName("TER_I_L_3_$Lava")}; 
               Terrain water = waters[(int)(Math.random()*waters.length)];
               for(int r=centerR - fountainSize; r<=centerR + fountainSize; r++)
                  for(int c=centerC - fountainSize; c<=centerC + fountainSize; c++)
                     house[r][c] = water.getValue();
               if(Math.random() < 0.5)             //put a structure in the middle of the fountain
               {
                  int size = (int)(Math.random() * (fountainSize - 2)) + 1;
                  Terrain[]options = {TerrainBuilder.getTerrainWithName("INR_$Black_floor"), TerrainBuilder.getTerrainWithName("INR_$Black_Stone_floor"), TerrainBuilder.getTerrainWithName("INR_$Blue_floor"), TerrainBuilder.getTerrainWithName("INR_$Red_floor")}; 
                  Terrain pick = options[(int)(Math.random()*options.length)];
                  for(int r=centerR - size; r<=centerR + size; r++)
                     for(int c=centerC - size; c<=centerC + size; c++)
                     {
                        if(r==centerR && c==centerC)
                           house[r][c] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
                        else
                           house[r][c] = pick.getValue();
                     }
               }
               else //put a statue in the center 
               {
                  if(Math.random() < 0.75)
                     house[centerR][centerC] = path.getValue();
                  NPCPlayer statue = new NPCPlayer(NPC.randFountainStatue(), centerR, centerC, mapIndex, "city");
                  statue.setNumInfo(-999);   
                  occupants[centerR][centerC]=statue;         
               }      
            }
            else if(middle == 1)                    //grass/trees
            {
               Terrain [] greens = {TerrainBuilder.getTerrainWithName("TER_$High_grassland"), TerrainBuilder.getTerrainWithName("TER_$Grassland"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Red_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Blue_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Yellow_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Violet_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Green_Flowers")}; 
               Terrain [] flowers = {TerrainBuilder.getTerrainWithName("TER_$Grassland_Red_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Blue_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Yellow_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Violet_Flowers"), TerrainBuilder.getTerrainWithName("TER_$Grassland_Green_Flowers")}; 
               Terrain grass = greens[(int)(Math.random()*greens.length)];
               boolean mixedFlowers = false;    //only pick amongst flowers
               if(grass.getName().toLowerCase().contains("flower") && Math.random() < 0.5)
                  mixedFlowers = true;
               boolean allMixed = false;        //random pick every cell
               if(Math.random() < .25)
                  allMixed = true;   
               int gardenSize = (int)(Math.random() * (courtYardSize - 2)) + 1;
               for(int r=centerR - gardenSize; r<=centerR + gardenSize; r++)
                  for(int c=centerC - gardenSize; c<=centerC + gardenSize; c++)
                  {
                     if(allMixed)
                        house[r][c] = greens[(int)(Math.random()*greens.length)].getValue();
                     else if(mixedFlowers)
                        house[r][c] = flowers[(int)(Math.random()*flowers.length)].getValue();
                     else
                        house[r][c] = grass.getValue();
                  }
               if(Math.random() < 0.5)             //put a structure in the middle
               {
                  int size = (int)(Math.random() * (gardenSize - 2)) + 1;
                  Terrain[]options = {TerrainBuilder.getTerrainWithName("INR_$Black_floor"), TerrainBuilder.getTerrainWithName("INR_$Black_Stone_floor"), TerrainBuilder.getTerrainWithName("INR_$Blue_floor"), TerrainBuilder.getTerrainWithName("INR_$Red_floor")}; 
                  Terrain pick = options[(int)(Math.random()*options.length)];
                  for(int r=centerR - size; r<=centerR + size; r++)
                     for(int c=centerC - size; c<=centerC + size; c++)
                     {
                        if(r==centerR && c==centerC)
                        {
                           if(Math.random() < 0.5)
                              house[r][c] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
                           else
                           {
                              if(Math.random() < 0.5)
                                 house[centerR][centerC] = path.getValue();
                              NPCPlayer statue = new NPCPlayer(NPC.randStatue(), centerR, centerC, mapIndex, "city");
                              statue.setNumInfo(-999);
                              occupants[centerR][centerC]= statue;        
                           }
                        }
                        else
                           house[r][c] = pick.getValue();
                     }
               }   
            }
            else if(middle == 2)                    //structure
            {
               Terrain [] walls =       {TerrainBuilder.getTerrainWithName("INR_I_B_$Gray_Rock"),           TerrainBuilder.getTerrainWithName("INR_I_B_$White_Rock"),           TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Tile_Wall"),       TerrainBuilder.getTerrainWithName("INR_I_B_$White_Brick"),           TerrainBuilder.getTerrainWithName("INR_I_B_$Earth_Wall")}; 
               Terrain [] wallTorches = {TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$Gray_Rock_Torch"), TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$White_Rock_Torch"), TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$Tile_Wall_Torch"), TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$White_Brick_Torch"), TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$Earth_Wall_Torch")}; 
               Terrain [] secrets =     {TerrainBuilder.getTerrainWithName("INR_D_B_$Gray_Rock"),           TerrainBuilder.getTerrainWithName("INR_D_B_$White_Rock"),           TerrainBuilder.getTerrainWithName("INR_D_B_$Tile_Wall"),           TerrainBuilder.getTerrainWithName("INR_D_B_$White_Brick_Wall"),      TerrainBuilder.getTerrainWithName("INR_D_B_$Earth_Wall")}; 
               int randWall = (int)(Math.random()*walls.length);
               Terrain wall = walls[randWall];
               Terrain torch = wallTorches[randWall];
               Terrain secret = secrets[randWall];
               Terrain[]options = {TerrainBuilder.getTerrainWithName("INR_$Black_floor"), TerrainBuilder.getTerrainWithName("INR_$Black_Stone_floor"), TerrainBuilder.getTerrainWithName("INR_$Blue_floor"), TerrainBuilder.getTerrainWithName("INR_$Red_floor")}; 
               Terrain pick = options[(int)(Math.random()*options.length)];
            
               boolean secretPlaced = false;   
               int structureSize = (int)(Math.random() * (courtYardSize - 1)) + 1;
            
               for(int r=centerR - structureSize; r<=centerR + structureSize; r++)
                  for(int c=centerC - structureSize; c<=centerC + structureSize; c++)
                  {
                     if((r==centerR-structureSize || r==centerR + structureSize || c==centerC - structureSize || c==centerC + structureSize))
                     {
                        if( !((r==(centerR - structureSize) && c==(centerC - structureSize)) || (r==(centerR - structureSize) && c==(centerC + structureSize)) ||
                        (r==(centerR + structureSize) && c==(centerC - structureSize)) || (r==(centerR + structureSize) && c==(centerC + structureSize)))
                        && !secretPlaced && Math.random() < 0.5)   
                        {     //if not on a corner and we haven't placed a secret wall, add one 50% of the time
                           house[r][c] = secret.getValue();
                           secretPlaced = true;
                        }
                        else
                        {
                           if(nextToATorch(house,r,c))
                              house[r][c] = wall.getValue();
                           else 
                              house[r][c] = torch.getValue();
                        }
                     }
                     else
                        house[r][c] = pick.getValue();
                  }
               if(!secretPlaced)
               {
                  Terrain [] doors = {TerrainBuilder.getTerrainWithName("INR_I_B_$Iron_door_locked"), TerrainBuilder.getTerrainWithName("INR_I_B_$Wood_door_locked")};
                  int randDoor = (int)(Math.random()*doors.length);
                  Terrain door = doors[randDoor];
                  int side = (int)(Math.random() * 4);
                  if(side == 0)
                     house[centerR - structureSize][centerC] = door.getValue();
                  else if(side == 1)
                     house[centerR + structureSize][centerC] = door.getValue();
                  else if(side == 2)
                     house[centerR][centerC - structureSize] = door.getValue();
                  else //if(side == 3)
                     house[centerR][centerC + structureSize] = door.getValue();
                  Terrain [] chests = {TerrainBuilder.getTerrainWithName("ITM_D_$Chest"), TerrainBuilder.getTerrainWithName("ITM_I_D_$Chest_locked"), TerrainBuilder.getTerrainWithName("ITM_D_T_$Chest_trap")};
                  if(!isImpassable(house, centerR, centerC))
                     house[centerR][centerC] = chests[(int)(Math.random()*chests.length)].getValue();
               }
            }
         }
      }//end - maybe put in a courtyard in the middle of the city
   
   //place a random jester starting in the center
      if(Math.random() < 0.5 && !capitalCity)
         for(int r=house.length/2; r>=0; r--)
            if(!isImpassable(house, r, house[0].length/2) && LocationBuilder.canSpawnOn(house, r, house[0].length/2))
            {
               occupants[r][house[0].length/2] = (new NPCPlayer(NPC.JESTER, r, house[0].length/2, mapIndex, r, house[0].length/2, "city"));
               break;
            }
   
   
      for(int r=0; r<occupants.length; r++)
         for(int c=0; c<occupants[0].length; c++)
            if(occupants[r][c] != null && LocationBuilder.canSpawnOn(house, r, c))
            {
               if(mapIndex >= CultimaPanel.civilians.size())
                  CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
               if(!Utilities.NPCAt(r, c, mapIndex))       
                  CultimaPanel.civilians.get(mapIndex).add(occupants[r][c]);
            }
      return path;
   }

//returns true if the Terrain at (r,c) is next to a Terrain cell with a torch (only up, down, left and right check)
   public static boolean nextToATorch(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return false;
      if(r-1>=0 && CultimaPanel.allTerrain.get(Math.abs(house[r-1][c])).getName().toLowerCase().contains("torch"))
         return true;
      if(r+1<house.length && CultimaPanel.allTerrain.get(Math.abs(house[r+1][c])).getName().toLowerCase().contains("torch"))
         return true;
      if(c-1>=0 && CultimaPanel.allTerrain.get(Math.abs(house[r][c-1])).getName().toLowerCase().contains("torch"))
         return true;
      if(c+1<house[0].length && CultimaPanel.allTerrain.get(Math.abs(house[r][c+1])).getName().toLowerCase().contains("torch"))
         return true;
      return false;
   }

   public static boolean adjacentToTorch(byte[][]house, int row, int col)
   {
      for(int r=row-1; r<=row+1; r++)
         for(int c=col-1; c<=col+1; c++)
         {
            if(r<0 || c<0 || r>=house.length || c>=house[0].length)
               continue;
            if(r==row && c==col)
               continue;
            if(CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().toLowerCase().contains("torch"))
               return true;
         }
      return false;    
   }

//returns true if the Terrain at (r,c) is a torch
   public static boolean onATorch(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return false;
      return (CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().toLowerCase().contains("torch"));
   }

//returns true if an NPC or monster can move onto the location house[r][c]
   public static boolean canMoveTo(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return false;
      if(isImpassable(house, r, c) || isBreakable(house, r, c) || onATorch(house, r, c))
         return false;
      if(r==CultimaPanel.player.getRow() && c==CultimaPanel.player.getCol())
         return false;
      if(Utilities.NPCAt(r, c, CultimaPanel.player.getMapIndex()))   
         return false;   
      return true;
   }

 //returns true if a non-swimming NPC can spawn on onto the location house[r][c]
   public static boolean canSpawnOn(byte[][]house, int r, int c)
   {
      if(outOfBounds(house, r, c))
         return false;
      boolean onWater = (CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().toLowerCase().contains("water"));
      if(onWater)
         return false;
      return canMoveTo(house,  r,  c);
   }

//returns true if an NPC or monster can move onto the location house[r][c]
   public static boolean canMoveTo(NPCPlayer p, byte[][]house, int r, int c)
   {
      if(r<0 || c<0 || r>=house.length || c>=house[0].length)
      {
         if(p.getMapIndex()==0)
         {
            r = CultimaPanel.equalizeWorldRow(r);
            c = CultimaPanel.equalizeWorldCol(c);
         }
         else
            return false;
      }
      String terr = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().toLowerCase();
      NPCPlayer NPCatLoc = Utilities.getNPCAt(r, c, p.getMapIndex());
      boolean anotherPlayerAtSameLocation = (NPCatLoc != null && p!=NPCatLoc);
      if(outOfBounds(house, r, c))
         return false;
      if(p.canFly() && p.getMapIndex()==0)
         return true;
      if((p.canSwim() || p.canWalkAndSwim()) && terr.contains("water") && !anotherPlayerAtSameLocation)
         return true;
      if(TerrainBuilder.habitablePlace(terr))   //in case the terrain tile is one of the world locations like a city tile
         return false;
      if(isImpassable(house, r, c) || isBreakable(house, r, c) || onATorch(house, r, c))
         return false;
      if(r==CultimaPanel.player.getRow() && c==CultimaPanel.player.getCol())
         return false;
      if(p.canSwim() && !terr.contains("water"))
         return false;
      if(p.getCharIndex()==NPC.TRIFFID && !terr.contains("bog"))     //triffids can only move in the swamp
         return false;
      if((p.getCharIndex()==NPC.TREE || p.getCharIndex()==NPC.TREEKING) && (terr.contains("mountain") || terr.contains("hills")))     
         return false;                                               //ropers can't move over hills or mountains
      if(!p.canSwim() && !p.canFly() && terr.contains("water"))   
         return false;
      if(p.getCharIndex()==NPC.GRABOID && !terr.contains("sand")  && !terr.contains("dry"))
         return false;                                               //graboids can only dig under sand and dry grass
      if(p.getCharIndex()==NPC.MAGMA && !terr.contains("lava"))
         return false;
      //last check is to see if p can move onto a space where another NPC is
      if(p.isFireResistant() && (NPCatLoc==null || (NPCatLoc!=null && NPCatLoc.getCharIndex()==NPC.FIRE))) 
         return true;
      if(p.canFly() && (NPCatLoc==null || (NPCatLoc!=null && NPCatLoc.getCharIndex()!=NPC.FIRE))) 
         return true;   
      if(anotherPlayerAtSameLocation)   
         return false;     
      return true;
   }

  //returns true if an NPC or monster can move onto the location house[r][c]
  //does not check to see if there is another NPC there - for removing those stuck
   public static boolean canMoveToNoNPCcheck(NPCPlayer p, byte[][]house, int r, int c)
   {
      if(r<0 || c<0 || r>=house.length || c>=house[0].length)
      {
         if(p.getMapIndex()==0)
         {
            r = CultimaPanel.equalizeWorldRow(r);
            c = CultimaPanel.equalizeWorldCol(c);
         }
         else
            return false;
      }
      String terr = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().toLowerCase();
      if(outOfBounds(house, r, c))
         return false;
      if(p.canFly() && p.getMapIndex()==0)
         return true;
      if((p.canSwim() || p.canWalkAndSwim()) && terr.contains("water"))
         return true;
      if(TerrainBuilder.habitablePlace(terr))   //in case the terrain tile is one of the world locations like a city tile
         return false;
      if(p.getCharIndex()==NPC.FIRE && !terr.contains("water") && !terr.contains("bog"))
         return true;
      if(isImpassable(house, r, c) || isBreakable(house, r, c) || onATorch(house, r, c))
         return false;
      if(p.canSwim() && !terr.contains("water"))
         return false;
      if(p.getCharIndex()==NPC.TRIFFID && !terr.contains("bog"))     //triffids can only move in the swamp
         return false;
      if((p.getCharIndex()==NPC.TREE || p.getCharIndex()==NPC.TREEKING) && (terr.contains("mountain") || terr.contains("hills")))     
         return false;                                               //ropers can't move over hills or mountains
      if(!p.canSwim() && !p.canWalkAndSwim() && !p.canFly() && terr.contains("water") && terr.contains("_i_"))
         return false;
      if(p.getCharIndex()==NPC.GRABOID && !terr.contains("sand")  && !terr.contains("dry"))
         return false; 
      if(p.getCharIndex()==NPC.MAGMA && !terr.contains("lava"))
         return false;   
      return true;
   }

    //pre: r and c are the center of the circle with radius size to check and valid coordinates of cave
    //post:true if we find a door within size radius of r,c (used for placing the 3-doors puzzle in a dungeon)
   public static boolean searchForDoorsInRadius(byte[][]cave, int r, int c, int size)
   {
      for(int row=r-size*2; row<=r+size*2; row++)
         for(int col=c-size*2; col<=c+size*2; col++)
         {
            if(outOfBounds(cave, row, col))
               continue;
            String terr = CultimaPanel.allTerrain.get(Math.abs(cave[row][col])).getName().toLowerCase();
            double dist = Display.findDistance(row, col, r, c);
            if(dist <= size && terr.contains("door"))      
               return true;
         }
      return false;
   }
   
   //returns if a path can be found to a traversable spot that is not water
   //used to build a puzzle area in dungeons for placement so that the player can navigate to the puzzle area
   public static boolean canFindPathToCircle(byte[][]cave, int sizeCircle, int centerR, int centerC)
   {  //seek north
      for(int r=centerR-(sizeCircle+1); r>=1; r--)
         if(isDryLand(cave, r, centerC))
            return true;
      //seek south
      for(int r=centerR+(sizeCircle+1); r<cave.length-1; r++)
         if(isDryLand(cave, r, centerC))
            return true;
      //seek east
      for(int c=centerC-(sizeCircle+1); c>=1; c--)
         if(isDryLand(cave, centerR, c))
            return true;
      //seek west
      for(int c=centerC+(sizeCircle+1); c<cave[0].length-1; c++)
         if(isDryLand(cave, centerR, c))
            return true;
      return false;
   }

 //places a circle of diameter size centered at (r,c) in cave with the texture floor
   public static void placeFloorSpot(byte[][]cave, int r, int c, Terrain floor, int size, boolean overwriteWalls, boolean halfCircle)
   {
      for(int row=r-size*2; row<=r+size*2; row++)
         for(int col=c-size*2; col<=c+size*2; col++)
         {
            if(outOfBounds(cave, row, col))
               continue;
            String terr = CultimaPanel.allTerrain.get(Math.abs(cave[row][col])).getName().toLowerCase();
            if(terr.contains("enterance"))      //don't overwrite portals
               continue;
            if(halfCircle && row>=((r-size*2)+(r+size*2))/2)
               continue;   
            double dist = Display.findDistance(row, col, r, c);
            if(dist <= size)
            {
               if(isImpassable(cave, row, col))
               {
                  if(overwriteWalls)
                     cave[row][col] = floor.getValue();
               }
               else
                  cave[row][col] = floor.getValue();
            } 
         }
   }
 
 //enterance will be a random spawn point close to the center
 //returns 3 Objects in an array.  Index 0 will be the Terrain[][] of the cave we built, index 1 will be an int[] of the starting coordinates
 //                                Index 2 will be the number of rooms, used to guide the number of monsters placed in CultimaPanel
   public static Object[] buildGraboidInnards()
   {     
      String type = "graboid";
      int mapIndex = 1;                                     //1 is the temporary map index used for ship battles
    
      //remove all old NPCs from this location
      CultimaPanel.civilians.get(mapIndex).clear();
      CultimaPanel.corpses.get(mapIndex).clear();
             
      int max = CultimaPanel.SCREEN_SIZE*4;
      int min = CultimaPanel.SCREEN_SIZE*2;
      int size = (int)(Math.random()*(max-min+1)) + min;
   
      byte[][]cave = new byte[size][size];
      
      Terrain randFloor = TerrainBuilder.getTerrainWithName("INR_$Red_floor");
      Terrain randFloorTrap = randFloor;
      Terrain randWall = TerrainBuilder.getTerrainWithName("INR_I_B_$Earth_Wall");
      Terrain randWallSecret = TerrainBuilder.getTerrainWithName("INR_D_B_$Earth_Wall"); 
        
      ArrayList<Terrain> caveFloors = new ArrayList<Terrain>();
      caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Black_floor"));
      caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Red_floor")); 
      caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Blue_floor"));
         
      for(int r=0; r<cave.length; r++)
         for(int c=0; c<cave[0].length; c++)
         {
            if(Math.random() < 0.8)
               cave[r][c] = randWall.getValue();
            else
               cave[r][c] = randWallSecret.getValue();
         }
      int enteranceRow = 0, enteranceCol = 0;         //coordinates of where we enter the cave
      int direction = 0;                              //the direction we will cut into the mountain
      int enteranceSize = 1;
      int startRow = 0, startCol = 0;                 //coordinates of where we will start cutting into the cave
   
      int minSize = 1;                                //minimum size of cave hallway
      int sizeRange = 2;                              //size variation of cave room
      int numPaths = (int)(Math.random()*3)+1;
      int numRooms = 0;
   
      ArrayList<Integer> exits = new ArrayList<Integer>();  //how many exits will there be
      for(int lastDir=0; lastDir<4; lastDir++)
         exits.add(lastDir);
      int numToRemove = Player.rollDie(0,2);
      for(int i=0; i<numToRemove; i++)
         exits.remove((int)(Math.random()*exits.size()));
       
      for(int j=0; j<exits.size(); j++)
      {     
         int lastDir = exits.get(j);
         if(lastDir == 2)                         //enter in row 0
         {
            enteranceRow = 0;
            enteranceCol = cave[0].length/2;
            direction = SOUTH;
            startRow = enteranceRow + enteranceSize;
            startCol = enteranceCol;
         }      
         else if(lastDir == 3)                    //enter in last col
         {
            enteranceRow = cave.length/2;
            enteranceCol = cave[0].length - 1;
            direction = WEST;
            startRow = enteranceRow;
            startCol = enteranceCol - enteranceSize;
         }
         else if(lastDir == 0)                    //enter in last row
         {
            enteranceRow = cave.length - 1;
            enteranceCol = cave[0].length/2;
            direction = NORTH;
            startRow = enteranceRow - enteranceSize;
            startCol = enteranceCol;
         }  
         else                                      //ender in col 0
         {
            enteranceRow = cave.length/2;
            enteranceCol = 0;
            direction = EAST;
            startRow = enteranceRow;
            startCol = enteranceCol + enteranceSize;
         }   
         int [] enterance = new int[2];
         enterance[0] = enteranceRow;
         enterance[1] = enteranceCol;
      
      //open up the mouth of the cave - random size from 2-7
         for(int r=enteranceRow - enteranceSize; r<=enteranceRow+enteranceSize; r++)
            for(int c=enteranceCol - enteranceSize; c<=enteranceCol + enteranceSize; c++)
            {
               if(r < 0 || c < 0 || r>=cave.length || c>=cave[0].length)
                  continue;
               cave[r][c] = randFloor.getValue();
            }
         int row = startRow;
         int col = startCol; 
         for(int i=0; i<numPaths; i++)
         { 
            row = startRow;
            col = startCol; 
         
            while(row >=1 && col>=1 && row < cave.length-1 && col<cave[0].length-1)
            {
               int[] newCoord = crawlToEdge(cave, row, col, direction, type);
               row = newCoord[0];
               col = newCoord[1]; 
            
            //random chance a cave can change hall and room dimensions
              /*
               if(Math.random() < 0.5)
               {
                  if(minSize == 1)
                     minSize++;
                  else if(minSize==2)
                     minSize--;
               
                  if(sizeRange == 2)
                     sizeRange++;
                  else if(sizeRange == 5)
                     sizeRange--;
                  else
                     sizeRange += Player.rollDie(-1,1);   
               }*/
            
            //put down a hallway
               if(Math.random() < 0.5)
               {
                  int hallLength = (int)(Math.random()*sizeRange*4) + (minSize*2);
                  if(direction==NORTH)
                  {
                     for(int r=row; r>=row-hallLength; r--)
                     {
                        for(int c=col - minSize; c<=col + minSize; c++)
                        {
                           if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                              continue;
                           cave[r][c] = randFloor.getValue();
                        }
                     }
                     if(row-hallLength > 1)
                        row = row-hallLength;
                  }
                  else if(direction==SOUTH)
                  {
                     for(int r=row; r<=row+hallLength; r++)
                     {
                        for(int c=col - minSize; c<=col + minSize; c++)
                        {
                           if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                              continue;
                           cave[r][c] = randFloor.getValue();
                        }
                     }
                     if(row+hallLength < cave.length-1)
                        row = row+hallLength;
                  }
                  else if(direction==EAST)
                  {
                     for(int r=row - minSize; r<=row + minSize; r++)
                     {
                        for(int c=col; c<=col+hallLength; c++)
                        {
                           if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                              continue;
                           cave[r][c] = randFloor.getValue();
                        }
                     }
                     if(col+hallLength < cave[0].length-1)
                        col = col+hallLength;
                  }
                  else if(direction==WEST)
                  {
                     for(int r=row - minSize; r<=row + minSize; r++)
                     {
                        for(int c=col; c>=col-hallLength; c--)
                        {
                           if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                              continue;
                           cave[r][c] = randFloor.getValue();
                        }
                     }
                     if(col-hallLength > 1)
                        col = col-hallLength;
                  }
               }
               boolean placeCoffin = false;
               //openHole(cave,  row,  col, randFloor, randFloorTrap, minSize, sizeRange, type, placeCoffin);
               int roomSize = Player.rollDie(1,2);
               placeFloorSpot(cave, row, col, randFloor, roomSize, true, false);
               int randF = (int)(Math.random()*caveFloors.size());
               Terrain randFloor2 = caveFloors.get((int)(Math.random()*caveFloors.size()));
               if(Math.random() < 0.5)
                  placeFloorSpot(cave, row, col, randFloor2, Math.max(1,roomSize-Player.rollDie(1,2)), true, false);
            
               numRooms++;
               if(row >=1 && col>=1 && row < cave.length-1 && col<cave[0].length-1)
                  if(Math.random() < 0.5)                //randomly change directions
                     direction = (int)(Math.random()*4);   
            }
         }
      }
            
      //make enterance in the center
      enteranceRow = cave.length/2;
      enteranceCol = cave[0].length/2;
      int [] enterance = new int[2];
      enterance[0] = enteranceRow;
      enterance[1] = enteranceCol;
      int roomSize = Player.rollDie(1,3);
      placeFloorSpot(cave, enteranceRow, enteranceCol, randFloor, roomSize, true, false);  
      Terrain randFloor2 = caveFloors.get((int)(Math.random()*caveFloors.size()));
      if(Math.random() < 0.5)
         placeFloorSpot(cave, enteranceRow, enteranceCol, randFloor2, Math.max(1,roomSize-Player.rollDie(1,2)), true, false);      
      Object [] retVal = new Object[3];
      retVal[0] = cave;
      retVal[1] = enterance; 
      retVal[2] = new Integer(numRooms);
      return retVal;
   }
   
 //enterance will be a random spawn point close to the center
 //returns 3 Objects in an array.  Index 0 will be the Terrain[][] of the cave we built, index 1 will be an int[] of the starting coordinates
 //                                Index 2 will be the number of rooms, used to guide the number of monsters placed in CultimaPanel
   public static Object[] buildBeastInnards()
   {     
      String type = "beast";
      int mapIndex = 1;                                     //1 is the temporary map index used for ship battles
    
      //remove all old NPCs from this location
      CultimaPanel.civilians.get(mapIndex).clear();
      CultimaPanel.corpses.get(mapIndex).clear();
             
      int max = CultimaPanel.SCREEN_SIZE;
      int min = CultimaPanel.SCREEN_SIZE/2;
      int size = (int)(Math.random()*(max-min+1)) + min;
      if(CultimaPanel.player.getMonsterType()==NPC.HYDRACLOPS)
         size *= 2;
   
      byte[][]cave = new byte[size][size];
      
      Terrain randFloor = TerrainBuilder.getTerrainWithName("INR_$Red_floor");
      Terrain randFloorTrap = randFloor;
      Terrain randWall = TerrainBuilder.getTerrainWithName("INR_I_B_$Earth_Wall");
      Terrain randWallSecret = TerrainBuilder.getTerrainWithName("INR_D_B_$Earth_Wall"); 
        
      ArrayList<Terrain> caveFloors = new ArrayList<Terrain>();
      caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Black_floor"));
      caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Red_floor")); 
      caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Blue_floor"));
         
      for(int r=0; r<cave.length; r++)
         for(int c=0; c<cave[0].length; c++)
         {
            if(Math.random() < 0.8)
               cave[r][c] = randWall.getValue();
            else
               cave[r][c] = randWallSecret.getValue();
         }              
      //make enterance in the center
      int enteranceRow = cave.length/2;
      int enteranceCol = cave[0].length/2;
      int [] enterance = new int[2];
      enterance[0] = enteranceRow;
      enterance[1] = enteranceCol;
      int roomSize = Player.rollDie(1,3);
      placeFloorSpot(cave, enteranceRow, enteranceCol, randFloor, roomSize, true, false);  
      Terrain randFloor2 = caveFloors.get((int)(Math.random()*caveFloors.size()));
      if(Math.random() < 0.5)
         placeFloorSpot(cave, enteranceRow, enteranceCol, randFloor2, Math.max(1,roomSize-Player.rollDie(1,2)), true, false);      
      Object [] retVal = new Object[3];
      retVal[0] = cave;
      retVal[1] = enterance; 
      retVal[2] = new Integer(0);
      return retVal;
   }

 //build realm for jurassica
   public static Object[] buildJurassica()
   {
      String type = "jurassica";
      int mapIndex = 1;                                     //1 is the temporary map index used for ship battles
    
      //remove all old NPCs from this location
      CultimaPanel.civilians.get(mapIndex).clear();
      CultimaPanel.corpses.get(mapIndex).clear();
   
      int max = CultimaPanel.SCREEN_SIZE*50;
      int min = CultimaPanel.SCREEN_SIZE*25;
      int size = (int)(Math.random()*(max-min+1)) + min;
   
      byte[][]world = new byte[size][size];
      NPCPlayer[][] occupants = new NPCPlayer[size][size];    //mabe will store monoliths
      boolean monolithsAdded = false;
   
      ArrayList <Terrain> terrain = new ArrayList();
      for(Terrain t: CultimaPanel.allTerrain)
         if(t.getName().startsWith("TER") && !t.getName().contains("Flower") && !t.getName().contains("Sand") && !t.getName().contains("Dry") && !t.getName().contains("Dead") && !t.getName().contains("Mountain"))
         {
            terrain.add(t);   //double the number of normal terrain except the more rare lava and rapids
            if(!t.getName().toLowerCase().contains("lava") && !t.getName().toLowerCase().contains("rapid"))
               terrain.add(t);
            if(world.length >= 700 && t.getName().toLowerCase().contains("water") )
            {
               for(int i=0; i<(world.length-700)/100; i++)
                  terrain.add(t);  
            }
         }
   //           void seed(byte[][]world,  int mapIndex, ArrayList <Terrain> terrain, int numSeeds)
      TerrainBuilder.seed(world, mapIndex, terrain, ((world.length * world[0].length) / (CultimaPanel.SCREEN_SIZE * CultimaPanel.SCREEN_SIZE)) * 4);
   //           void build(byte[][]world, ArrayList<Terrain> terrain, boolean wrapAround)
      TerrainBuilder.build(world, terrain, false);
      TerrainBuilder.smooth(world);
      
      //make enterance somewhere near the center
      int enteranceRow = 0, enteranceCol = 0;         //coordinates of where we enter the cave
      ArrayList<Point>allSpots = new ArrayList<Point>();
      ArrayList<Point>bestSpots = new ArrayList<Point>();
      
      Terrain grass = TerrainBuilder.getTerrainWithName("TER_$Grassland");
      for(int r=0; r<world.length; r++)
         for(int c=0; c<world[0].length; c++)
         {//remove flowers from Jurassia - they haven't evolved yet
            if(TerrainBuilder.isFlower(world, r, c))
               world[r][c] = grass.getValue();
            double distToCenter = Display.findDistance(r, c, world.length/2, world[0].length/2);
            if(!isImpassable(world, r, c))
            {
               allSpots.add(new Point(r,c));
               if(distToCenter < world.length / 4)
                  bestSpots.add(new Point(r,c));
            }
         }
      
      int [] enterance = new int[2];
      if(bestSpots.size() > 0)
      {
         Point spawn = bestSpots.get((int)(Math.random()*bestSpots.size()));
         enteranceRow = (int)(spawn.getX());
         enteranceCol = (int)(spawn.getY());
      }
      else
      {
         Point spawn = allSpots.get((int)(Math.random()*allSpots.size()));
         enteranceRow = (int)(spawn.getX());
         enteranceCol = (int)(spawn.getY());
      }
      
      //maybe add strange enterance out with monoliths around it
      if(Math.random() < 0.5)
      {
         Point spawn = allSpots.get((int)(Math.random()*allSpots.size()));
         int portalRow = (int)(spawn.getX());
         int portalCol = (int)(spawn.getY());
         if(!outOfBounds(world, portalRow, portalCol) && !isImpassable(world, portalRow, portalCol))
         {
            Terrain ter = TerrainBuilder.getTerrainWithName("LOC_$Dungeon_enterance_strange");
            world[portalRow][portalCol] = ter.getValue();
            
            //maybe put monoliths around the teleporter
            if(Math.random()< 0.75)
            {
               monolithsAdded = true;
               int dist = Player.rollDie(1,5);
               if(portalRow - dist >= 1 && portalCol - dist >= 1 && !isImpassable(world, portalRow-dist, portalCol-dist))
                  occupants[portalRow-dist][portalCol-dist] = (new NPCPlayer(NPC.MONOLITH, portalRow-dist, portalCol-dist, mapIndex, portalRow-dist, portalCol-dist, type));
               if(portalRow - dist >= 1 && portalCol + dist <= world[0].length-2 && !isImpassable(world, portalRow-dist, portalCol+dist))
                  occupants[portalRow-dist][portalCol+dist] = (new NPCPlayer(NPC.MONOLITH, portalRow-dist, portalCol+dist, mapIndex, portalRow-dist, portalCol+dist, type));
               if(portalRow + dist <= world.length-2 && portalCol - dist >= 1 && !isImpassable(world, portalRow+dist, portalCol-dist))
                  occupants[portalRow+dist][portalCol-dist] = (new NPCPlayer(NPC.MONOLITH, portalRow+dist, portalCol-dist, mapIndex, portalRow+dist, portalCol-dist, type));
               if(portalRow + dist <= world.length-2 && portalCol + dist <= world[0].length-2 && !isImpassable(world, portalRow+dist, portalCol+dist))
                  occupants[portalRow+dist][portalCol+dist] = (new NPCPlayer(NPC.MONOLITH, portalRow+dist, portalCol+dist, mapIndex, portalRow+dist, portalCol+dist, type));
            }
         }
      }
      if(monolithsAdded)
      {
         for(int r=0; r<occupants.length; r++)
            for(int c=0; c<occupants[0].length; c++)
            {
               if(occupants[r][c] != null && !isImpassable(world, r, c))
               {
                  NPCPlayer toAdd = occupants[r][c];
                  if(mapIndex >= CultimaPanel.civilians.size())
                     CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
                  if(!Utilities.NPCAt(r, c, mapIndex))    
                     CultimaPanel.civilians.get(mapIndex).add(toAdd);
               }
            }
      }
      
      enterance[0] = enteranceRow;
      enterance[1] = enteranceCol;
      
      Object [] retVal = new Object[3];
      retVal[0] = world;
      retVal[1] = enterance;
      retVal[2] = new Integer(0); 
      return retVal;
   }
   
   //build new teleport realm
   public static Object[] build1942()
   {
      int size = (int)(Math.random()*20)+(CultimaPanel.SCREEN_SIZE*4);
      byte[][]house = new byte[size][size];
      int mapIndex = 1;
      Terrain edgeType = TerrainBuilder.getTerrainWithName("TER_$Grassland");
      ArrayList<Terrain> seedTypes = new ArrayList<Terrain>();
      int roll = Player.rollDie(1,8);
      if(roll==1)     
      {     //put some hllls on the edges, followed by mixed grass
         edgeType = TerrainBuilder.getTerrainWithName("TER_S_$Hills");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$High_grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
      }
      else if(roll==2)
      {     //put some High_grassland on the edges, then mostly mixed grasses
         edgeType = TerrainBuilder.getTerrainWithName("TER_$High_grassland");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));    
      }
      else if(roll==3)
      {     //put some forest on the edges, then mostly grass
         edgeType = TerrainBuilder.getTerrainWithName("TER_S_B_$Dense_forest");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Forest"));    
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$High_grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
      }
      else if(roll==4)
      {     //put some TER_$Sand on the edges, then mostly TER_$Dry_grass
         edgeType = TerrainBuilder.getTerrainWithName("TER_$Sand");            
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));     
      }
      else if(roll==5)//bog
      {    
         edgeType = TerrainBuilder.getTerrainWithName("TER_S_$Bog");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$High_grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
      }
      else if(roll==6)
      {     
         edgeType = TerrainBuilder.getTerrainWithName("TER_S_$Bog");
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_S_$Bog"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$High_grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));     
      }
      else if(roll==7)//dead-forest
      {     
         edgeType = TerrainBuilder.getTerrainWithName("TER_$Dead_forest");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));    
      }
      else
      {
         edgeType = TerrainBuilder.getTerrainWithName("TER_$High_grassland");       
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
         seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));    
      }   
      TerrainBuilder.seed(house, mapIndex, seedTypes, ((house.length) / (CultimaPanel.SCREEN_SIZE)) * 2);
   //put down edgeType around the borders
      for(int r=0; r<house.length; r++)
      {
         for(int c=0; c <=1; c++)
         {
            double prob = 0.5;
            if(c==1)
               prob = 0.25;
            if(Math.random() < prob)
               house[r][c] = edgeType.getValue();
            if(Math.random() < prob)
               house[r][house[0].length-1-c] = edgeType.getValue();
         }
      }
      for(int r=0; r<=1; r++)
      {
         double prob = 0.5;
         if(r==1)
            prob = 0.25;
         for(int c=0; c<house[0].length; c++)
         {
            if(Math.random() < prob)
               house[r][c] = edgeType.getValue();
            if(Math.random() < prob)
               house[house.length-1-r][c] = edgeType.getValue();
         }
      }
      ArrayList <Terrain> terrain = new ArrayList();
      for(Terrain t: CultimaPanel.allTerrain)
         if(t.getName().startsWith("TER"))
            terrain.add(t);
   
      TerrainBuilder.build(house, terrain, false);
      for(int r=0; r<house.length; r++)         //replace any impassable water in the battlefield with shallow water
         for(int c=0; c<house[0].length; c++)
         {
            String curr = CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().toLowerCase();
            if(curr.contains("water"))
               house[r][c] = TerrainBuilder.getTerrainWithName("TER_V_$Shallow_water").getValue();
            else if(curr.contains("lava"))
               house[r][c] = TerrainBuilder.getTerrainWithName("TER_$Dead_forest").getValue();
            else if(curr.contains("mountain"))
               house[r][c] = TerrainBuilder.getTerrainWithName("TER_S_$Hills").getValue();
         }
          
      if(Math.random() < 0.5)
      {   //maybe scatter some stone obstacles in the battlefield
         byte [] rockType = {TerrainBuilder.getTerrainWithName("INR_I_B_$Gray_Rock").getValue(), TerrainBuilder.getTerrainWithName("INR_I_B_$White_Rock").getValue(),
                    TerrainBuilder.getTerrainWithName("INR_D_B_$Gray_Rock").getValue(), TerrainBuilder.getTerrainWithName("INR_D_B_$White_Rock").getValue()};
         int numRocks = Player.rollDie(1,house.length);
         for(int i=0; i<numRocks; i++)
         {
            int r = Player.rollDie(2, house.length-3);
            int c = Player.rollDie(2, house[0].length-3);
            house[r][c] = rockType[(int)(Math.random()*rockType.length)];
         }
      }
         
    //add armies marching in respective directions
      int numEnemies = Math.max(20, Player.rollDie(5, house.length/3));
      int armyMarchDir = Player.rollDie(0,3);     
      if(armyMarchDir == NORTH)
      {
         int row = 1;
         int col = (house[0].length/2) - (numEnemies/2 + 1);
         for(int i=0; i<numEnemies; i++)
         {
            byte charType = NPC.random1942Monster();
            NPCPlayer monster = new NPCPlayer(charType, row, col, mapIndex, row, col, "1942");
            col++;
            monster.setMoveType(NPC.MARCH_SOUTH);
            CultimaPanel.worldMonsters.add(monster);
         }
      }
      else if(armyMarchDir == SOUTH)
      {
         int row = house.length - 2;
         int col = (house[0].length/2) - (numEnemies/2 + 1);
         for(int i=0; i<numEnemies; i++)
         {
            byte charType = NPC.random1942Monster();
            NPCPlayer monster = new NPCPlayer(charType, row, col, mapIndex, row, col, "1942");
            col++;
            monster.setMoveType(NPC.MARCH_NORTH);
            CultimaPanel.worldMonsters.add(monster);
         }
      }
      else if(armyMarchDir == EAST)
      {
         int row = (house.length /2)  - (numEnemies/2 + 1);
         int col = house[0].length - 2;
         for(int i=0; i<numEnemies; i++)
         {
            byte charType = NPC.random1942Monster();
            NPCPlayer monster = new NPCPlayer(charType, row, col, mapIndex, row, col, "1942");
            row++;
            monster.setMoveType(NPC.MARCH_WEST);
            CultimaPanel.worldMonsters.add(monster);
         }
      }
      else //if(armyMarchDir == WEST)
      {
         int row = (house.length /2) - (numEnemies/2 + 1);
         int col = 1;
         for(int i=0; i<numEnemies; i++)
         {
            byte charType = NPC.random1942Monster();
            NPCPlayer monster = new NPCPlayer(charType, row, col, mapIndex, row, col, "battlefield");
            row++;
            monster.setMoveType(NPC.MARCH_WEST);
            CultimaPanel.worldMonsters.add(monster);
         }
      }
   
   
      int [] enterance = new int[2];
      enterance[0] = house.length/2;
      enterance[1] = house[0].length/2;
   
      Object [] retVal = new Object[3];
      retVal[0] = house;
      retVal[1] = enterance; 
      retVal[2] = new Integer(0);
      return retVal;
   
   }
   
   public static Object[] buildCastleWolfen()
   {
      int mapIndex = 1;
      int size = (int)(Math.random()*25)+(CultimaPanel.SCREEN_SIZE*2);  
      if(size%2==0)     //make odd size dimensions for acurate matrix rotation
         size++;     
      byte[][]house = new byte[size][size];
      Terrain edgeType = TerrainBuilder.getTerrainWithName("TER_$Grassland");
      ArrayList<Terrain> seedTypes = new ArrayList<Terrain>();  
      seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Grassland"));   
      seedTypes.add(TerrainBuilder.getTerrainWithName("TER_$Dry_grass"));        
      TerrainBuilder.seed(house, mapIndex, seedTypes, ((house.length) / (CultimaPanel.SCREEN_SIZE)) * 2);
   //put down edgeType around the borders
      for(int r=0; r<house.length; r++)
      {
         for(int c=0; c <=1; c++)
         {
            double prob = 0.5;
            if(c==1)
               prob = 0.25;
            if(Math.random() < prob)
               house[r][c] = edgeType.getValue();
            if(Math.random() < prob)
               house[r][house[0].length-1-c] = edgeType.getValue();
         }
      }
      for(int r=0; r<=1; r++)
      {
         double prob = 0.5;
         if(r==1)
            prob = 0.25;
         for(int c=0; c<house[0].length; c++)
         {
            if(Math.random() < prob)
               house[r][c] = edgeType.getValue();
            if(Math.random() < prob)
               house[house.length-1-r][c] = edgeType.getValue();
         }
      }
      ArrayList <Terrain> terrain = new ArrayList();
      for(Terrain t: CultimaPanel.allTerrain)
      {
         String terName = t.getName().toLowerCase();
         if(t.getName().startsWith("TER") && !terName.contains("water") && !terName.contains("lava") && !terName.contains("mountain"))
            terrain.add(t);
      }     
      TerrainBuilder.build(house, terrain, false);
      int[] enterance = buildCastleWolfen(house);
      
      Object [] retVal = new Object[3];
      retVal[0] = house;
      retVal[1] = enterance;
      retVal[2] = new Integer(0); 
      return retVal;
   }
   
   //returns the spawn-in location of our player
   public static int[] buildCastleWolfen(byte[][]house)
   {
      int [] enterance = new int[2];
      int moatWidth = (int)(Math.random()*4) + 2;
      int bridgeWidth = (int)(Math.random()*8) + 4;
      int turretSize = (int)(Math.random()*6) + 3;
      int mapIndex = 1; 
      int randSide = 2;
      int enteranceRow = 0;
      int enteranceCol = house[0].length/2;
           
      NPCPlayer[][] occupants = new NPCPlayer[house.length][house[0].length];    //fill with occupants to add to the civilians ArrayList after we complete and possilby rotate the map
   
      Point topLeft = new Point(0, 0);
      Point topRight = new Point(0, house[0].length-1);
      Point bottomLeft = new Point(house.length-1, 0);
      Point bottomRight = new Point(house.length-1, house[0].length-1);
      Terrain water = TerrainBuilder.getTerrainWithName("TER_I_$Deep_water");
      Terrain drawBridge = TerrainBuilder.getTerrainWithName("INR_$Wood_Plank_floor");
      Terrain wall = TerrainBuilder.getTerrainWithName("INR_I_B_$Red_Brick");
      Terrain wallTorch = TerrainBuilder.getTerrainWithName("INR_I_B_L_4_$Red_Brick_Torch");
      Terrain wallSecret = TerrainBuilder.getTerrainWithName("INR_D_B_$Red_Brick_Wall");
      Terrain floor = TerrainBuilder.getTerrainWithName("INR_$Black_floor");
      Terrain carpet = TerrainBuilder.getTerrainWithName("INR_$Black_floor");
      Terrain door = TerrainBuilder.getTerrainWithName("INR_D_B_$Iron_door");
      Terrain doorLocked = TerrainBuilder.getTerrainWithName("INR_I_B_$Iron_door_locked");
   
      for(int r=(int)(topLeft.getX())+1; r <= (int)(bottomRight.getX())-1; r++)
         for(int c=(int)(topLeft.getY())+1; c <= (int)(bottomRight.getY())-1; c++)
            if(CultimaPanel.allTerrain.get(Math.abs(house[r][c])).getName().contains("TER"))
               house[r][c] = floor.getValue();
   
      int center = house[0].length / 2;   
      for(int r=enteranceRow; r<=moatWidth; r++)
      {
         for(int c=0; c<house[0].length; c++)
         {
            if(c < center - bridgeWidth/2 || c > center + bridgeWidth/2)
               house[r][c] = water.getValue();
            else
               house[r][c] = drawBridge.getValue();
         }
      }
      topLeft = new Point(moatWidth+1, 0);
      topRight = new Point(moatWidth+1, house[0].length-1);
      bottomLeft = new Point(house.length-1, 0);
      bottomRight = new Point(house.length-1, house[0].length-1);
   
   //build wall around castle
      for(int r=moatWidth+1; r<house.length; r++)
      {
         house[r][0] = wall.getValue();
         house[r][house[0].length-1] = wall.getValue();
      }
   
   //put guard on each side of the entrance
      if(!isImpassable(house,  moatWidth+1, (center - bridgeWidth/2)))
         occupants[moatWidth+1][(center - bridgeWidth/2)] = (new NPCPlayer(NPC.SOLDIER, moatWidth+1, (center - bridgeWidth/2), mapIndex, moatWidth+1, (center - bridgeWidth/2), "wolfenstein"));
      if(!isImpassable(house,  moatWidth+1, (center + bridgeWidth/2)))
         occupants[moatWidth+1][(center + bridgeWidth/2)] = (new NPCPlayer(NPC.SOLDIER, moatWidth+1, (center + bridgeWidth/2), mapIndex, moatWidth+1, (center - bridgeWidth/2), "wolfenstein"));
   
      for(int c=0; c<house[0].length; c++)
      {
         if(c < center - bridgeWidth/2 || c > center + bridgeWidth/2)
         {
            if(c == (center - bridgeWidth/2-1) || c==(center + bridgeWidth/2+1))      //put torches at enterance of castle
               house[moatWidth+1][c] = wallTorch.getValue();
            else
               house[moatWidth+1][c] = wall.getValue();
         }
         else
            house[moatWidth+1][c] = drawBridge.getValue();
         house[house.length-1][c] = wall.getValue();
      }
   
   //put down main hallway
      double secretProb = 0.01;
      double secretProbTorch = 0.05;
   
      boolean closedHallway = false;
      if(Math.random() < 0.65)
         closedHallway = true;
      int columnSize = (int)(Math.random()*2) + 1; 
      int columnDepth = (int)(Math.random()*2) + 1;  
      boolean closedWithColumns = false;
      if(Math.random() < 0.5)
      {
         closedWithColumns = true;
         columnDepth = 2;
      } 
   
      int mainHallLength = ((int)(Math.random()*3) + 2) * (house.length/4);
      int torchPeriod = (int)(Math.random()*6) + 5;                                    //distance between torches
   
      if(Math.random() < 0.75)           //add a roaming guard moving south
      {
         if(!isImpassable(house, mainHallLength/2, center))
         {
            occupants[mainHallLength/2][center] = (new NPCPlayer(NPC.SOLDIER, mainHallLength/2, center, mapIndex, mainHallLength/2, center, "wolfenstein"));
            occupants[mainHallLength/2][center].setMoveType(NPC.MARCH_SOUTH);
         }
      }
   
      for(int r=moatWidth+2; r<mainHallLength-1; r++)
      {
         for(int c=center - bridgeWidth/2 - 1; c<=center + bridgeWidth/2 + 1; c++)
         {
            if(c>=center - bridgeWidth/2 + 1 && c<=center + bridgeWidth/2 - 1)
               house[r][c] = carpet.getValue();
            else if((c==center - bridgeWidth/2 - 1 || c==center + bridgeWidth/2 + 1) && !closedHallway)
            {  //put in a column
               if(r % torchPeriod == 0)
                  house[r][c] = wallTorch.getValue();
               else if(r % torchPeriod == 1 && columnSize == 2)
               {
                  house[r][c] = wall.getValue();
                  if(c==center - bridgeWidth/2 - 1 && columnDepth == 2 && !outOfBounds(house, r, c-1))   //left column   
                     house[r][c-1] = wall.getValue();
                  else if(c==center + bridgeWidth/2 + 1 && columnDepth == 2 && !outOfBounds(house, r, c+1))   //left column   
                     house[r][c+1] = wall.getValue();
               }
               if(closedWithColumns)
               {
                  if(c==center - bridgeWidth/2 - 1 && columnDepth == 2 && !outOfBounds(house, r, c-1))   //left column   
                     house[r][c-1] = wall.getValue();
                  else if(c==center + bridgeWidth/2 + 1 && columnDepth == 2 && !outOfBounds(house, r, c+1))   //left column   
                     house[r][c+1] = wall.getValue();
               }
            }
            else if((c==center - bridgeWidth/2 - 1 || c==center + bridgeWidth/2 + 1) && closedHallway)
            {
               if(r % torchPeriod == 0)
                  house[r][c] = wallTorch.getValue();
               else if(r % torchPeriod == 1)             //maybe put an opening or a door
               {
                  int decision = (int)(Math.random()*4); //0-wall, 1-opening, 2-door, 3-locked door
                  if(decision == 0 || r + (torchPeriod/2) >= mainHallLength)
                  {
                     if(Math.random() < secretProbTorch)
                        house[r][c] = wallSecret.getValue();
                     else
                        house[r][c] = wall.getValue();
                  }
                  else 
                  {
                     if(decision == 1)
                        house[r][c] = floor.getValue();
                     else if(decision == 2)
                        house[r][c] = door.getValue();
                     else
                        house[r][c] = doorLocked.getValue();
                  //maybe make the opening or door go into some kind of room
                     if(Math.random() < 0.75)
                     {
                        int occRow = r;
                        int occCol = c;
                        if(c==center - bridgeWidth/2 - 1)      //left side
                           occCol = c-2;
                        else
                           occCol = c+2;                       //right side                       
                        if(!isImpassable(house, occRow, occCol))
                        {
                           byte charIndex = NPC.randomWolfensteinMonster();
                           occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "wolfenstein");
                        }
                        int maxRoomSize = (house[0].length-1) - (center + bridgeWidth/2 + 1);
                        if(r < moatWidth+1 + turretSize || r > house.length - 1 - turretSize)
                           maxRoomSize = (house[0].length-1) - (center + bridgeWidth/2 + 1) - turretSize - 2;
                        int roomSize = (int)(Math.random()*(maxRoomSize - 3 + 1)) + 3;
                        if(c==center-bridgeWidth/2 - 1)         //left wall
                        {
                           int roomRowTop = r - (torchPeriod/2);
                           int roomRowBottom = r + (torchPeriod/2);
                           int leftWall = c-1-roomSize;
                           boolean topRowBreak = false;
                           boolean bottomRowBreak = false;
                           for(int col=c-1; col>= leftWall; col--)
                           {
                              if(!isImpassable(house, roomRowTop, col))
                              {
                                 if(Math.random() < secretProb)
                                    house[roomRowTop][col] = wallSecret.getValue();
                                 else
                                    house[roomRowTop][col] = wall.getValue();
                              }
                              else 
                              {
                                 topRowBreak=true;
                                 break;
                              }
                           }
                           for(int col=c-1; col>= leftWall; col--)
                           {
                              if(!isImpassable(house, roomRowBottom, col))
                              {
                                 if(Math.random() < secretProb)
                                    house[roomRowBottom][col] = wallSecret.getValue();
                                 else
                                    house[roomRowBottom][col] = wall.getValue();
                              }
                              else 
                              {
                                 bottomRowBreak = true;
                                 break;
                              }
                           }
                           if((!topRowBreak && !bottomRowBreak) || (!topRowBreak && bottomRowBreak))
                           {   
                              for(int row = roomRowTop+1; row<=roomRowBottom-1; row++)
                                 if(!isImpassable(house, row, leftWall))
                                 {
                                    if(Math.random() < secretProb)
                                       house[row][leftWall] = wallSecret.getValue();
                                    else
                                       house[row][leftWall] = wall.getValue();
                                 }
                                 else 
                                    break;
                           }
                           else if(topRowBreak && !bottomRowBreak)
                           {
                              for(int row = roomRowBottom-1; row>= roomRowTop+1; row--)
                                 if(!isImpassable(house, row, leftWall))
                                 {
                                    if(Math.random() < secretProb)
                                       house[row][leftWall] = wallSecret.getValue();
                                    else
                                       house[row][leftWall] = wall.getValue();
                                 }
                                 else 
                                    break;
                           }
                        }
                        else
                           if(c==center+bridgeWidth/2 + 1)         //right wall
                           {
                              int roomRowTop = r - (torchPeriod/2);
                              int roomRowBottom = r + (torchPeriod/2);
                              int rightWall = c+1+roomSize;
                              boolean topRowBreak = false;
                              boolean bottomRowBreak = false;
                              for(int col=c+1; col<= rightWall; col++)
                              {
                                 if(!isImpassable(house, roomRowTop, col))
                                 {
                                    if(Math.random() < secretProb)
                                       house[roomRowTop][col] = wallSecret.getValue();
                                    else
                                       house[roomRowTop][col] = wall.getValue();
                                 }
                                 else 
                                 {
                                    topRowBreak=true;
                                    break;
                                 }
                              }
                              for(int col=c+1; col<= rightWall; col++)
                              {
                                 if(!isImpassable(house, roomRowBottom, col))
                                 {
                                    if(Math.random() < secretProb)
                                       house[roomRowBottom][col] = wallSecret.getValue();
                                    else
                                       house[roomRowBottom][col] = wall.getValue();
                                 }
                                 else 
                                 {
                                    bottomRowBreak = true;
                                    break;
                                 }
                              }
                              if((!topRowBreak && !bottomRowBreak) || (!topRowBreak && bottomRowBreak))
                              {   
                                 for(int row = roomRowTop+1; row<=roomRowBottom-1; row++)
                                    if(!isImpassable(house, row, rightWall))
                                    {
                                       if(Math.random() < secretProb)
                                          house[row][rightWall] = wallSecret.getValue();
                                       else
                                          house[row][rightWall] = wall.getValue();
                                    }
                                    else 
                                       break;
                              }
                              else if(topRowBreak && !bottomRowBreak)
                              {
                                 for(int row = roomRowBottom-1; row>= roomRowTop+1; row--)
                                    if(!isImpassable(house, row, rightWall))
                                    {
                                       if(Math.random() < secretProb)
                                          house[row][rightWall] = wallSecret.getValue();
                                       else
                                          house[row][rightWall] = wall.getValue();
                                    }
                                    else 
                                       break;
                              }
                           }
                     }
                  }
               }
               else
               {
                  if(Math.random() < secretProb)
                     house[r][c] = wallSecret.getValue();
                  else
                     house[r][c] = wall.getValue();
               }
            }
         }
      }
   //put main chamber at the end of the main hallway, or another perpindicular hallway
      boolean crossHallway = false;
      if(Math.random() < 0.5)
         crossHallway = true;
      if(Math.abs((mainHallLength - 1) - (house.length-2)) > 2) 
      {
         int chamberColMin = center - bridgeWidth;
         int chamberColMax = center + bridgeWidth;
         int chamberHeight = bridgeWidth + ((int)(Math.random()*bridgeWidth));
         if(crossHallway)
         {
            chamberHeight = bridgeWidth-1;
            chamberColMin = 4;
            chamberColMax = house[0].length - 5;
         }
         if(Math.random() < 0.75)           //add a roaming guard moving west
         {
            int centerR = ((mainHallLength-1) + (mainHallLength-1 + chamberHeight))/2;
            int centerC = (chamberColMin+chamberColMax)/2;
            if(!isImpassable(house, centerR, centerC))
            {
               occupants[centerR][centerC] = (new NPCPlayer(NPC.SOLDIER, centerR, centerC, mapIndex, centerR, centerC, "wolfenstein"));
               occupants[centerR][centerC].setMoveType(NPC.MARCH_WEST);
            }
         }
         if(Math.random() < 0.95)           //add royalty and maybe surrounding guards
         {
            int centerR = (((mainHallLength-1) + (mainHallLength-1 + chamberHeight))/2) + 2;
            int centerC = (chamberColMin+chamberColMax)/2;
            if(!isImpassable(house, centerR, centerC))
            {
               occupants[centerR][centerC] = (new NPCPlayer(NPC.OFFICER, centerR, centerC, mapIndex, centerR, centerC, "wolfenstein"));
               
               if(Math.random() < 0.5 && !isImpassable(house, centerR, centerC-2))
                  occupants[centerR][centerC-2] = (new NPCPlayer(NPC.SOLDIER, centerR, centerC-2, mapIndex, centerR, centerC-2, "wolfenstein"));
               if(Math.random() < 0.5 && !isImpassable(house, centerR, centerC+2))
                  occupants[centerR][centerC+2] = (new NPCPlayer(NPC.SOLDIER, centerR, centerC+2, mapIndex, centerR, centerC+2, "wolfenstein"));
            }
         }
         for(int r=mainHallLength-1; r<mainHallLength-1 + chamberHeight && r < house.length - 1; r++)
         {
            for(int c=chamberColMin; c<=chamberColMax; c++)
            {
               if(closedHallway && (c==center - bridgeWidth/2 - 1 || c==center + bridgeWidth/2 + 1) && r==mainHallLength-1 && Math.random() < 0.75 && !crossHallway)
                  house[r][c] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
               else
                  house[r][c] = carpet.getValue();
            }
         }
         int topWallRow = mainHallLength  - 2;
         int bottomWall = mainHallLength-1 + chamberHeight + 1;
         int leftWall = chamberColMin - 2;
         int rightWall = chamberColMax + 2;
         if(closedHallway)    //place walls around the main chamber
         {
            for(int c=leftWall; c<=rightWall && c<house[0].length && c>0; c++)
            {
               if(!CultimaPanel.allTerrain.get(Math.abs(house[topWallRow][c])).getName().equals(carpet.getName()))
               {
                  if(Math.random() < secretProb)
                     house[topWallRow][c] = wallSecret.getValue();
                  else
                     house[topWallRow][c] = wall.getValue();
               }
               if(!isImpassable(house, bottomWall, c))
               {
               //maybe put a door along the bottom wall
                  if(c==(leftWall + rightWall)/2)
                  {
                     if(Math.random() < 0.5)
                        house[bottomWall][c] = doorLocked.getValue();
                     else  if(Math.random() < 0.5)
                        house[bottomWall][c] = wallSecret.getValue();   
                     else
                        house[bottomWall][c] = wall.getValue();   
                  }
                  else
                  {
                     if(Math.random() < secretProb)
                        house[bottomWall][c] = wallSecret.getValue();
                     else
                        house[bottomWall][c] = wall.getValue();
                  }
               }
               if(!isImpassable(house, bottomWall-2, c) && (c==center - bridgeWidth/2 - 1 || c==center + bridgeWidth/2 + 1) && Math.random() < 0.75)
                  house[bottomWall-2][c] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
            }
            for(int r=topWallRow; r<=bottomWall; r++)
            {
               if(!isImpassable(house, r, leftWall))
               {
                  if(r % torchPeriod == 0)
                     house[r][leftWall] = wallTorch.getValue();
                  else
                  {
                     if(Math.random() < secretProb)
                        house[r][leftWall] = wallSecret.getValue();
                     else
                        house[r][leftWall] = wall.getValue();
                  }
               }
               if(!isImpassable(house, r, rightWall))
               {
                  if(r % torchPeriod == 0)
                     house[r][rightWall] = wallTorch.getValue();
                  else
                  {
                     if(Math.random() < secretProb)
                        house[r][rightWall] = wallSecret.getValue();
                     else
                        house[r][rightWall] = wall.getValue();
                  }
               }
            }
         }
         if(crossHallway && mainHallLength < house.length-4 && Math.random() < 0.5) //maybe extend the main hall to the last row
         {        
            for(int r=bottomWall - 1; r < house.length-1; r++)
            {
               for(int c=center - bridgeWidth/2 - 1; c<=center + bridgeWidth/2 + 1; c++)
               {
                  if(c>=center - bridgeWidth/2 + 1 && c<=center + bridgeWidth/2 - 1)
                     house[r][c] = carpet.getValue();
                  else if((c==center - bridgeWidth/2 - 1 || c==center + bridgeWidth/2 + 1) && closedHallway)
                  {
                     if(r % torchPeriod == 0)
                        house[r][c] = wallTorch.getValue();
                     else if(r % torchPeriod == 1)             //maybe put an opening or a door
                     {
                        int decision = (int)(Math.random()*4); //0-wall, 1-opening, 2-door, 3-locked door
                        if(decision == 0)
                        {
                           if(Math.random() < secretProbTorch)
                              house[r][c] = wallSecret.getValue();
                           else
                              house[r][c] = wall.getValue();
                        }
                        else 
                        {
                           if(decision == 1)
                              house[r][c] = floor.getValue();
                           else if(decision == 2)
                              house[r][c] = door.getValue();
                           else
                              house[r][c] = doorLocked.getValue();
                        }
                     }
                     else
                     {
                        if(Math.random() < secretProb)
                           house[r][c] = wallSecret.getValue();
                        else
                           house[r][c] = wall.getValue();
                     }
                  }
               }
            }        
               
         }   
      }
   
      boolean addedDungeon = false;
      int focusRow = -1;   //location of dungeon entrance
      int focusCol = -1;  
   
      int numBuildings = (int)(Math.random() * 8) + 5;
      if(closedHallway)
         numBuildings = (int)(Math.random() * 6) + 3; 
      for(int i=0; i<numBuildings; i++)
      {
         Object [] info = placeBuilding(house, mapIndex, wall, floor, "wolfenstein", null);
         ArrayList<NPCPlayer> person = (ArrayList<NPCPlayer>)(info[0]);
         if(person!=null && person.size() > 0)
            for(NPCPlayer p:person)
               occupants[p.getRow()][p.getCol()] = p;
         Point roomCenter = (Point)(info[1]);
         if(!addedDungeon && Math.random() < 0.25 && roomCenter!=null && !nextToADoor(house, (int)(roomCenter.getX()), (int)(roomCenter.getY())))
         {
            focusRow = (int)(roomCenter.getX());
            focusCol = (int)(roomCenter.getY());
            addedDungeon = true;
            if(occupants[focusRow][focusCol]!=null)         //if there is a person on the dungeon entrance
            {
               if(Math.random() < 0.5)                      //remove them
                  occupants[focusRow][focusCol] = null;
               else                                         //or shift them if you can
               {
                  NPCPlayer toMove = occupants[focusRow][focusCol];
                  occupants[focusRow][focusCol] = null;
                  boolean moved = false;
                  for(int occRow=focusRow-1; occRow<=focusRow+1; occRow++)
                     for(int occCol=focusCol-1; occCol<=focusCol+1; occCol++)
                     {
                        if(!isImpassable(house, occRow, occCol) && (house[occRow][occCol]==floor.getValue() || house[occRow][occCol]==carpet.getValue()))
                        {
                           occupants[focusRow][focusCol] = toMove;
                           moved = true;
                           break;
                        }
                        if(moved)
                           break;
                     }
               
               }
            }
         }
      } 
   
   //add some turrets   
      int spawnTurret = Player.rollDie(1,4);
      boolean spawnPlaced = false;
        
      int topLeftRow = (int)(topLeft.getX());
      int topLeftCol = (int)(topLeft.getY());
      for(int r=topLeftRow+1; r<=topLeftRow + turretSize; r++)
         for(int c=topLeftCol+1; c<=topLeftCol + turretSize; c++)
            house[r][c] = floor.getValue();
   
      boolean sideDoor = true;
      if(Math.random() < 0.5)
         sideDoor = false;
   
      for(int r=topLeftRow; r<=topLeftRow + turretSize; r++)
      {
         int mid = (topLeftRow + (topLeftRow + turretSize))/2;
         if(r == mid && sideDoor)
         {
            if(Math.random() < 0.5)
               house[r][topLeftCol+turretSize] = door.getValue();       
            else
               house[r][topLeftCol+turretSize] = doorLocked.getValue();
            if(isImpassable(house, r, topLeftCol+turretSize+1) && topLeftCol+turretSize+1<house[0].length)
               house[r][topLeftCol+turretSize+1] = floor.getValue();
         }
         else
         {
            if(r==mid+1 && sideDoor)
               house[r][topLeftCol+turretSize] = wallTorch.getValue();
            else
               house[r][topLeftCol+turretSize] = wall.getValue();
         }
      }
      int occRow = (topLeftRow + (topLeftRow + turretSize))/2;
      int occCol = topLeftCol+(turretSize/2);
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = NPC.randomWolfensteinMonster();
         if(spawnTurret==1 && !spawnPlaced)
         {
            enterance[0]=occRow;
            enterance[1]=occCol;
            spawnPlaced = true;
         }
         else if(spawnTurret!=1)    //we don't want a guard possibly spawning in the same room as us
            occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "wolfenstein");
      }
   
      for(int c=topLeftCol; c<=topLeftCol + turretSize; c++)
      {
         int mid = (topLeftCol + (topLeftCol + turretSize))/2;
         if(c == mid && !sideDoor)
         {
            if(Math.random() < 0.5)
               house[topLeftRow+turretSize][c] = door.getValue();
            else
               house[topLeftRow+turretSize][c] = doorLocked.getValue();
            if(isImpassable(house, topLeftRow+turretSize+1, c) && topLeftRow+turretSize+1<house.length)
               house[topLeftRow+turretSize+1][c] = floor.getValue();
         }
         else
         {
            if(c==mid+1 && !sideDoor)
               house[topLeftRow+turretSize][c] = wallTorch.getValue();
            else
               house[topLeftRow+turretSize][c] = wall.getValue();
         }
      }
      occCol = (topLeftCol + (topLeftCol + turretSize))/2;
      occRow = topLeftRow+(turretSize/2);
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = NPC.randomWolfensteinMonster();
         if(spawnTurret==1 && !spawnPlaced)
         {
            enterance[0]=occRow;
            enterance[1]=occCol;
            spawnPlaced = true;
         }
         else if(spawnTurret!=1)//we don't want a guard possibly spawning in the same room as us
            occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "wolfenstein");
      }
   
      int topRightRow = (int)(topRight.getX());
      int topRightCol = (int)(topRight.getY());
      for(int r=topRightRow+1; r<=topRightRow + turretSize; r++)
         for(int c=topRightCol-turretSize-1; c<=topRightCol; c++)
            house[r][c] = floor.getValue();
      sideDoor = true;
      if(Math.random() < 0.5)
         sideDoor = false;
   
      for(int r=topRightRow; r<=topRightRow + turretSize; r++)
      {
         int mid = (topRightRow + (topRightRow + turretSize))/2;
         if(r == mid && sideDoor)
         {
            if(Math.random() < 0.5)
               house[r][topRightCol-turretSize] = door.getValue();
            else
               house[r][topRightCol-turretSize] = doorLocked.getValue();
            if(isImpassable(house, r, topRightCol-turretSize-1) &&  topRightCol-turretSize-1 > 0)
               house[r][topRightCol-turretSize-1] = floor.getValue();
         }
         else
         {
            if(r==mid+1 && sideDoor)
               house[r][topRightCol-turretSize] = wallTorch.getValue();
            else
               house[r][topRightCol-turretSize] = wall.getValue();
         }
      }
      occCol = (topRightCol-turretSize/2);
      occRow = (topRightRow + (topRightRow + turretSize))/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = NPC.randomWolfensteinMonster();
         if(spawnTurret==2 && !spawnPlaced)
         {
            enterance[0]=occRow;
            enterance[1]=occCol;
            spawnPlaced = true;
         }
         else if(spawnTurret!=2)//we don't want a guard possibly spawning in the same room as us
            occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "wolfenstein");
      }
   
      for(int c=topRightCol-turretSize; c<=topRightCol; c++)
      {
         int mid = (topRightCol + (topRightCol - turretSize))/2;
         if(c == mid && !sideDoor)
         {
            if(Math.random() < 0.5)
               house[topRightRow+turretSize][c] = door.getValue();
            else
               house[topRightRow+turretSize][c] = doorLocked.getValue();
            if(isImpassable(house, topRightRow+turretSize+1, c) && topRightRow+turretSize+1 < house.length)
               house[topRightRow+turretSize+1][c] = floor.getValue();
         }
         else
         {
            if(c==mid-1 && !sideDoor)
               house[topRightRow+turretSize][c] = wallTorch.getValue();
            else
               house[topRightRow+turretSize][c] = wall.getValue();
         }
      }
      occCol = (topRightCol + (topRightCol - turretSize))/2;
      occRow = topRightRow+turretSize/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = NPC.randomWolfensteinMonster();
         if(spawnTurret==2 && !spawnPlaced)
         {
            enterance[0]=occRow;
            enterance[1]=occCol;
            spawnPlaced = true;
         }
         else if(spawnTurret!=2)//we don't want a guard possibly spawning in the same room as us
            occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "wolfenstein");
      }
   
      int bottomLeftRow = (int)(bottomLeft.getX());
      int bottomLeftCol = (int)(bottomLeft.getY());
      for(int r=bottomLeftRow - turretSize - 1; r<=bottomLeftRow - 1; r++)
         for(int c=bottomLeftCol + 1; c<=bottomLeftCol + turretSize; c++)
            house[r][c] = floor.getValue();
   
      sideDoor = true;
      if(Math.random() < 0.5)
         sideDoor = false;
     
      for(int r=bottomLeftRow - turretSize - 1; r<=bottomLeftRow - 1; r++)
      {
         int mid = ((bottomLeftRow - 1) + (bottomLeftRow - turretSize - 1))/2;
         if(r == mid && sideDoor)
         {
            if(Math.random() < 0.5)
               house[r][bottomLeftCol+turretSize] = door.getValue();
            else
               house[r][bottomLeftCol+turretSize] = doorLocked.getValue();
            if(isImpassable(house, r, bottomLeftCol+turretSize + 1) && bottomLeftCol+turretSize + 1 < house[0].length)
               house[r][bottomLeftCol+turretSize + 1] = floor.getValue();
         }
         else
         {
            if(r==mid-1 && sideDoor)
               house[r][bottomLeftCol+turretSize] = wallTorch.getValue();
            else
               house[r][bottomLeftCol+turretSize] = wall.getValue();
         }
      }
      occCol = (bottomLeftCol+turretSize/2);
      occRow = ((bottomLeftRow - 1) + (bottomLeftRow - turretSize - 1))/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = NPC.randomWolfensteinMonster();
         if(spawnTurret==3 && !spawnPlaced)
         {
            enterance[0]=occRow;
            enterance[1]=occCol;
            spawnPlaced = true;
         }
         else if(spawnTurret!=3)//we don't want a guard possibly spawning in the same room as us
            occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "wolfenstein");
      }
   
      for(int c=bottomLeftCol; c<=bottomLeftCol + turretSize; c++)
      {
         int mid = (bottomLeftCol + (bottomLeftCol + turretSize))/2;
         if(c == mid && !sideDoor)
         {
            if(Math.random() < 0.5)
               house[bottomLeftRow-turretSize-1][c] = door.getValue();
            else
               house[bottomLeftRow-turretSize-1][c] = doorLocked.getValue();
            if(isImpassable(house,bottomLeftRow-turretSize-2, c) && bottomLeftRow-turretSize-2 > 0)    
               house[bottomLeftRow-turretSize-2][c] = floor.getValue();
         }
         else
         {
            if(c==mid+1 && !sideDoor)
               house[bottomLeftRow-turretSize-1][c] = wallTorch.getValue();
            else
               house[bottomLeftRow-turretSize-1][c] = wall.getValue();
         }
      }
      occCol = (bottomLeftCol + (bottomLeftCol + turretSize))/2;
      occRow = bottomLeftRow-turretSize/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = NPC.randomWolfensteinMonster();
         if(spawnTurret==3 && !spawnPlaced)
         {
            enterance[0]=occRow;
            enterance[1]=occCol;
            spawnPlaced = true;
         }
         else if(spawnTurret!=3)//we don't want a guard possibly spawning in the same room as us
            occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "wolfenstein");
      }
      int bottomRightRow = (int)(bottomRight.getX());
      int bottomRightCol = (int)(bottomRight.getY());
      for(int r=bottomRightRow - turretSize; r<=bottomRightRow - 1; r++)
         for(int c=bottomRightCol - turretSize; c<=bottomRightCol - 1; c++)
            house[r][c] = floor.getValue();
   
      sideDoor = true;
      if(Math.random() < 0.5)
         sideDoor = false;
   
      for(int r=bottomRightRow - turretSize; r<=bottomRightRow - 1; r++)
      {
         int mid = ((bottomRightRow - 1) + (bottomRightRow - turretSize))/2;
         if(r == mid && sideDoor)
         {
            if(Math.random() < 0.5)
               house[r][bottomRightCol-turretSize] = door.getValue();
            else
               house[r][bottomRightCol-turretSize] = doorLocked.getValue();
            if(isImpassable(house, r,bottomRightCol-turretSize-1) && bottomRightCol-turretSize-1 > 0)   
               house[r][bottomRightCol-turretSize-1] = floor.getValue();
         }
         else
         {
            if(r==mid-1 && sideDoor)
               house[r][bottomRightCol-turretSize] = wallTorch.getValue();
            else
               house[r][bottomRightCol-turretSize] = wall.getValue();
         }
      }
      occCol = bottomRightCol-turretSize/2;
      occRow = ((bottomRightRow - 1) + (bottomRightRow - turretSize))/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = NPC.randomWolfensteinMonster();
         if(spawnTurret==4 && !spawnPlaced)
         {
            enterance[0]=occRow;
            enterance[1]=occCol;
            spawnPlaced = true;
         }
         else if(spawnTurret!=4)//we don't want a guard possibly spawning in the same room as us
            occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "wolfenstein");
      }
   
      for(int c=bottomRightCol - turretSize; c<=bottomRightCol; c++)
      {
         int mid = (bottomRightCol + (bottomRightCol - turretSize))/2;
         if(c == mid && !sideDoor)
         {
            if(Math.random() < 0.5)
               house[bottomRightRow-turretSize-1][c] = door.getValue();
            else
               house[bottomRightRow-turretSize-1][c] = doorLocked.getValue();
            if(isImpassable(house, bottomRightRow-turretSize-2, c) && bottomRightRow-turretSize-2 > 0)
               house[bottomRightRow-turretSize-2][c] = floor.getValue();
         }
         else
         {
            if(c==mid-1 && !sideDoor)
               house[bottomRightRow-turretSize-1][c] = wallTorch.getValue();
            else
               house[bottomRightRow-turretSize-1][c] = wall.getValue();
         }
      }
      occCol = (bottomRightCol + (bottomRightCol - turretSize))/2;
      occRow = bottomRightRow-turretSize/2;
      if(!isImpassable(house, occRow, occCol))
      {
         byte charIndex = NPC.randomWolfensteinMonster();
         if(spawnTurret==4 && !spawnPlaced)
         {
            enterance[0]=occRow;
            enterance[1]=occCol;
            spawnPlaced = true;
         }
         else if(spawnTurret!=4)//we don't want a guard possibly spawning in the same room as us
            occupants[occRow][occCol] = new NPCPlayer(charIndex, occRow, occCol, mapIndex, occRow, occCol, "wolfenstein");
      }
      fixBrokenDoors(house, floor);
   
         //maybe put an enterance to a dungeon here
      if(addedDungeon && focusRow >=0 && focusCol >= 0)
      {
         int doorRow = focusRow;   //find a place where there is an open spot to get to the dungeon entrance
         int doorCol = focusCol;
         int numTries = 0;
         while(!outOfBounds(house, doorRow, doorCol) && blockedIn(house, doorRow, doorCol))
         {
            if(randSide==0)
               doorRow++;
            else if(randSide==1)
               doorCol--;
            else if(randSide==2)
               doorRow--;
            else
               doorCol++; 
         }
         if(!outOfBounds(house, doorRow, doorCol))
         {
            Terrain ter = TerrainBuilder.getTerrainWithName("LOC_$Dungeon_enterance_strange");
            house[doorRow][doorCol] = ter.getValue();
            if(!spawnPlaced)
            {
               enterance[0]=doorRow;
               enterance[1]=doorCol;
               spawnPlaced = true;
            }
         }
      }   
   
      for(int r=0; r<occupants.length; r++)
         for(int c=0; c<occupants[0].length; c++)
         {
            if(occupants[r][c] != null)
            {
               occupants[r][c].setRow(r);   //reset occupant's homeRow and homeCol in case we rotated the castle, shifted if it is a castle in a capital city
               occupants[r][c].setCol(c);
               occupants[r][c].setHomeRow(r);  
               occupants[r][c].setHomeCol(c);
            }
            if(occupants[r][c] != null && !isImpassable(house, r, c) && (house[r][c]==floor.getValue() || house[r][c]==carpet.getValue() || house[r][c]==drawBridge.getValue()))
            {
               NPCPlayer toAdd = new NPCPlayer(occupants[r][c].getName(), occupants[r][c].getCharIndex(), r, c, mapIndex, occupants[r][c].getHomeRow(),occupants[r][c].getHomeCol(), "wolfenstein");
               toAdd.setMoveType(occupants[r][c].getMoveType());
            
               if(mapIndex >= CultimaPanel.civilians.size())
                  CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
               if(!Utilities.NPCAt(r, c, mapIndex))    
                  CultimaPanel.civilians.get(mapIndex).add(toAdd);
            }
         }
         
      if(!spawnPlaced)
      {
         ArrayList<Point>spawns = new ArrayList<Point>();
         for(int r=0; r<house.length; r++)
            for(int c=0; c<house[0].length; c++)
               if(!isImpassable(house, r, c) && (house[r][c]==floor.getValue() || house[r][c]==carpet.getValue() || house[r][c]==drawBridge.getValue()))
                  spawns.add(new Point(r,c));
         Point spawnPoint = spawns.get((int)(Math.random()*spawns.size()));
         enterance[0] = (int)(spawnPoint.getX());
         enterance[1] = (int)(spawnPoint.getY());
      }  
      return enterance;
   }

 //enterance will be a random spawn point close to the center
 //returns 3 Objects in an array.  Index 0 will be the Terrain[][] of the cave we built, index 1 will be an int[] of the starting coordinates
 //                                Index 2 will be the location of wells for the 3 wells puzzle 
   public static Object[] buildUnderworld()
   {     
      String type = "underworld";
      int mapIndex = 1;                                     //1 is the temporary map index used for ship battles
    
      //remove all old NPCs from this location
      CultimaPanel.civilians.get(mapIndex).clear();
      CultimaPanel.corpses.get(mapIndex).clear();
             
      int max = CultimaPanel.SCREEN_SIZE*12;
      int min = CultimaPanel.SCREEN_SIZE*6;
      int size = (int)(Math.random()*(max-min+1)) + min;
   
      byte[][]cave = new byte[size][size];
      NPCPlayer[][] occupants = new NPCPlayer[size][size];    //mabe will store monoliths
      boolean monolithsAdded = false;
      
      ArrayList<Terrain> caveFloors = new ArrayList<Terrain>();
      caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Black_floor"));
      caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Blue_floor"));
      caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Red_floor")); 
   
      int randF = (int)(Math.random()*caveFloors.size());
      Terrain randFloor = caveFloors.get((int)(Math.random()*caveFloors.size()));
      Terrain randFloorTrap = randFloor;
      Terrain randWall = TerrainBuilder.getTerrainWithName("TER_I_L_3_$Lava");
         
      for(int r=0; r<cave.length; r++)
         for(int c=0; c<cave[0].length; c++)
            cave[r][c] = randWall.getValue();
   
      int enteranceRow = 0, enteranceCol = 0;         //coordinates of where we enter the cave
      int direction = 0;                              //the direction we will cut into the mountain
      int enteranceSize = (int)(Math.random()*4) + 2;
      int startRow = 0, startCol = 0;                 //coordinates of where we will start cutting into the cave
   
      int minSize = (int)(Math.random()*2) + 1;       //minimum size of cave hallway
      int sizeRange = (int)(Math.random()*3) + 2;     //size variation of cave room
      int numPaths = (int)(Math.random()*3)+1;
      int numRooms = 0;
   
      ArrayList<Integer> exits = new ArrayList<Integer>();  //how many exits will there be
      for(int lastDir=0; lastDir<4; lastDir++)
         exits.add(lastDir);
      int numToRemove = Player.rollDie(0,2);
      for(int i=0; i<numToRemove; i++)
         exits.remove((int)(Math.random()*exits.size()));
       
      for(int j=0; j<exits.size(); j++)
      {     
         int lastDir = exits.get(j);
         if(lastDir == 2)                         //enter in row 0
         {
            enteranceRow = 0;
            enteranceCol = cave[0].length/2;
            direction = SOUTH;
            startRow = enteranceRow + enteranceSize;
            startCol = enteranceCol;
         }      
         else if(lastDir == 3)                    //enter in last col
         {
            enteranceRow = cave.length/2;
            enteranceCol = cave[0].length - 1;
            direction = WEST;
            startRow = enteranceRow;
            startCol = enteranceCol - enteranceSize;
         }
         else if(lastDir == 0)                    //enter in last row
         {
            enteranceRow = cave.length - 1;
            enteranceCol = cave[0].length/2;
            direction = NORTH;
            startRow = enteranceRow - enteranceSize;
            startCol = enteranceCol;
         }  
         else                                      //ender in col 0
         {
            enteranceRow = cave.length/2;
            enteranceCol = 0;
            direction = EAST;
            startRow = enteranceRow;
            startCol = enteranceCol + enteranceSize;
         }   
         int [] enterance = new int[2];
         enterance[0] = enteranceRow;
         enterance[1] = enteranceCol;
      
      //open up the mouth of the cave - random size from 2-7
         for(int r=enteranceRow - enteranceSize; r<=enteranceRow+enteranceSize; r++)
            for(int c=enteranceCol - enteranceSize; c<=enteranceCol + enteranceSize; c++)
            {
               if(r < 0 || c < 0 || r>=cave.length || c>=cave[0].length)
                  continue;
               cave[r][c] = randFloor.getValue();
            }
         int row = startRow;
         int col = startCol; 
         for(int i=0; i<numPaths; i++)
         { 
            row = startRow;
            col = startCol; 
         
            while(row >=1 && col>=1 && row < cave.length-1 && col<cave[0].length-1)
            {
               int[] newCoord = crawlToEdge(cave, row, col, direction, type);
               row = newCoord[0];
               col = newCoord[1]; 
            
            //random chance a cave can change hall and room dimensions
               if(Math.random() < 0.5)
               {
                  if(minSize == 1)
                     minSize++;
                  else if(minSize==2)
                     minSize--;
               
                  if(sizeRange == 2)
                     sizeRange++;
                  else if(sizeRange == 5)
                     sizeRange--;
                  else
                     sizeRange += Player.rollDie(-1,1);   
               }
            
            //put down a hallway
               if(Math.random() < 0.5)
               {
                  int hallLength = (int)(Math.random()*sizeRange*2) + minSize;
                  if(direction==NORTH)
                  {
                     for(int r=row; r>=row-hallLength; r--)
                     {
                        for(int c=col - minSize; c<=col + minSize; c++)
                        {
                           if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                              continue;
                           cave[r][c] = randFloor.getValue();
                        }
                     }
                     if(row-hallLength > 1)
                        row = row-hallLength;
                  }
                  else if(direction==SOUTH)
                  {
                     for(int r=row; r<=row+hallLength; r++)
                     {
                        for(int c=col - minSize; c<=col + minSize; c++)
                        {
                           if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                              continue;
                           cave[r][c] = randFloor.getValue();
                        }
                     }
                     if(row+hallLength < cave.length-1)
                        row = row+hallLength;
                  }
                  else if(direction==EAST)
                  {
                     for(int r=row - minSize; r<=row + minSize; r++)
                     {
                        for(int c=col; c<=col+hallLength; c++)
                        {
                           if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                              continue;
                           cave[r][c] = randFloor.getValue();
                        }
                     }
                     if(col+hallLength < cave[0].length-1)
                        col = col+hallLength;
                  }
                  else if(direction==WEST)
                  {
                     for(int r=row - minSize; r<=row + minSize; r++)
                     {
                        for(int c=col; c>=col-hallLength; c--)
                        {
                           if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                              continue;
                           cave[r][c] = randFloor.getValue();
                        }
                     }
                     if(col-hallLength > 1)
                        col = col-hallLength;
                  }
               }
               boolean placeCoffin = false;
               openHole(cave,  row,  col, randFloor, randFloorTrap, minSize, sizeRange, type, placeCoffin);
               numRooms++;
               if(row >=1 && col>=1 && row < cave.length-1 && col<cave[0].length-1)
                  if(Math.random() < 0.25)                //randomly change directions
                     direction = (int)(Math.random()*4);   
            }
         }
                          
         //maybe add portal to warp us back to another cave in the world
         if (Math.random() < 0.5)
         {
            int numTries = 0;
            while(row<2 || col<2 || row>=cave.length-2 || col>=cave[0].length-2)
            {
               if(direction==NORTH)                   //march the coordinates away from the last direction we were going
                  row++;                              //to get the coordinates in-bounds
               else if(direction==SOUTH)
                  row--;
               else if(direction==EAST)
                  col--;
               else
                  col++;
               numTries++;
               if(numTries >= 1000 && outOfBounds(cave, row, col))
                  break;
            }
            if(!outOfBounds(cave, row, col) && !isImpassable(cave, row, col))
            {
               Terrain ter = TerrainBuilder.getTerrainWithName("LOC_$Dungeon_enterance_strange");
               cave[row][col] = ter.getValue();
            
            //maybe put monoliths around the teleporter
               if(Math.random()< 0.75)
               {
                  monolithsAdded = true;
                  int dist = Player.rollDie(1,5);
                  if(row - dist >= 1 && col - dist >= 1 && !isImpassable(cave, row-dist, col-dist))
                     occupants[row-dist][col-dist] = (new NPCPlayer(NPC.MONOLITH, row-dist, col-dist, mapIndex, row-dist, col-dist, type));
                  if(row - dist >= 1 && col + dist <= cave[0].length-2 && !isImpassable(cave, row-dist, col+dist))
                     occupants[row-dist][col+dist] = (new NPCPlayer(NPC.MONOLITH, row-dist, col+dist, mapIndex, row-dist, col+dist, type));
                  if(row + dist <= cave.length-2 && col - dist >= 1 && !isImpassable(cave, row+dist, col-dist))
                     occupants[row+dist][col-dist] = (new NPCPlayer(NPC.MONOLITH, row+dist, col-dist, mapIndex, row+dist, col-dist, type));
                  if(row + dist <= cave.length-2 && col + dist <= cave[0].length-2 && !isImpassable(cave, row+dist, col+dist))
                     occupants[row+dist][col+dist] = (new NPCPlayer(NPC.MONOLITH, row+dist, col+dist, mapIndex, row+dist, col+dist, type));
               }
            }
         }
      }
      
      //add one monster king to the underworld
      byte kingType = NPC.randomUnderworldKing();
      int r = (int)(Math.random()*cave.length);
      int c = (int)(Math.random()*cave[0].length);
            
      ArrayList<Point>spawnPoints = new ArrayList<Point>();
      for(int cr = 0; cr<cave.length; cr++)
         for(int cc = 0; cc<cave[0].length; cc++)
            if(LocationBuilder.canMoveTo(cave, cr, cc) && !TerrainBuilder.onWater(cave, cr, cc))
               spawnPoints.add(new Point(cr, cc));
      if(spawnPoints.size() > 0)
      {
         Point spawn = spawnPoints.get((int)(Math.random()*spawnPoints.size()));
         r = (int)(spawn.getX());
         c = (int)(spawn.getY());
      } 
      NPCPlayer targNPC = new NPCPlayer(kingType, r, c, mapIndex, "underworld");
      targNPC.setMoveType(NPC.CHASE);
      CultimaPanel.worldMonsters.add(targNPC);
      
      if(monolithsAdded)
      {
         for( r=0; r<occupants.length; r++)
            for( c=0; c<occupants[0].length; c++)
            {
               if(occupants[r][c] != null && !isImpassable(cave, r, c))
               {
                  NPCPlayer toAdd = occupants[r][c];
                  if(mapIndex >= CultimaPanel.civilians.size())
                     CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
                  if(!Utilities.NPCAt(r, c, mapIndex))    
                     CultimaPanel.civilians.get(mapIndex).add(toAdd);
               }
            }
      }
      
      //make enterance somewhere near the center
      ArrayList<Point>allSpots = new ArrayList<Point>();
      ArrayList<Point>bestSpots = new ArrayList<Point>();
      
      for( r=0; r<cave.length; r++)
         for( c=0; c<cave[0].length; c++)
         {
            double distToCenter = Display.findDistance(r, c, cave.length/2, cave[0].length/2);
            if(!isImpassable(cave, r, c))
            {
               allSpots.add(new Point(r,c));
               if(distToCenter < cave.length / 4)
                  bestSpots.add(new Point(r,c));
            }
         }
      
      int [] enterance = new int[2];
      if(bestSpots.size() > 0)
      {
         Point spawn = bestSpots.get((int)(Math.random()*bestSpots.size()));
         enteranceRow = (int)(spawn.getX());
         enteranceCol = (int)(spawn.getY());
      }
      else
      {
         Point spawn = allSpots.get((int)(Math.random()*allSpots.size()));
         enteranceRow = (int)(spawn.getX());
         enteranceCol = (int)(spawn.getY());
      }
      enterance[0] = enteranceRow;
      enterance[1] = enteranceCol;
      
       //maybe add "islands" of different colored and sized circles
      if(Math.random() < 0.5)
      {  //placeFloorSpot(byte[][]cave, int r, int c, Terrain floor, int size, boolean overwriteWalls)
         caveFloors.clear(); 
         caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Black_floor"));
         caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Blue_floor"));
         caveFloors.add(TerrainBuilder.getTerrainWithName("INR_$Red_floor")); 
         int spacing = Player.rollDie(1,3);
         int density = Player.rollDie(1,4);
         for( r=0; r<cave.length; r+=spacing)
            for( c=0; c<cave[0].length; c+=spacing)
            {
               int sizeCircle = Player.rollDie(1,4);
               Terrain color = caveFloors.get((int)(Math.random()*caveFloors.size()));
               if(Player.rollDie(100) < density)
                  placeFloorSpot(cave, r, c, color, sizeCircle, true, false);
            } 
      }
      //maybe place the wells riddle where the player can win a legendary weapon
      int sizeCircle = 10;
      int centerR = (int)(Math.random()*(cave.length - (sizeCircle*2))) + sizeCircle;
      int centerC = (int)(Math.random()*(cave[0].length - (sizeCircle*2))) + sizeCircle;
      Point well1 = null, well2 = null;
      if(!CultimaPanel.player.hasLegendaryWeapon() || Math.random() < 0.25)
      {
         Terrain water = TerrainBuilder.getTerrainWithName("TER_V_$Shallow_water");
         Terrain rapidWater = TerrainBuilder.getTerrainWithName("TER_I_$Rapid_water");
         Terrain marble = TerrainBuilder.getTerrainWithName("INR_$Black_Stone_floor");
         //add the base
         Terrain ter = caveFloors.get((int)(Math.random()*caveFloors.size()));
         placeFloorSpot(cave, centerR, centerC, ter, sizeCircle, true, false);
         //add the water surrounding the island
         sizeCircle -= 2;
         placeFloorSpot(cave, centerR, centerC, water, sizeCircle, true, false);
         //add the island in the water
         sizeCircle -= 2;
         ter = caveFloors.get((int)(Math.random()*caveFloors.size()));
         placeFloorSpot(cave, centerR, centerC, ter, sizeCircle, true, false);
         //add a marble frame around the 3 wells
         for(r=centerR-1; r<=centerR+1; r++)
            for(c=centerC-3; c<=centerC+3; c++)
               cave[r][c] = marble.getValue();
         //add the 3 wells      
         cave[centerR][centerC-2] = water.getValue();          //well 1
         cave[centerR][centerC] = water.getValue();            //well 2
         cave[centerR][centerC+2] = rapidWater.getValue();     //well 3
         well1 = new Point(centerR, centerC-2);                //store the location of the wells so we know where we can draw water from for the challenge (to setWellNumber for player)
         well2 = new Point(centerR, centerC);
         //add the riddle book
         for(r=centerR+2; r<=centerR+3; r++)
            for(c=centerC-1; c<=centerC+1; c++)
               cave[r][c] = marble.getValue();
         cave[centerR+2][centerC] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
         //add the demon puzzle giver at (centerR-2, centerC)
         for(r=centerR-3; r<=centerR-2; r++)
            for(c=centerC-1; c<=centerC+1; c++)
               cave[r][c] = marble.getValue();
         //DEMONKING
         NPCPlayer puzzleGiver = (new NPCPlayer(NPC.DEMONKING, centerR-2, centerC, mapIndex,  centerR-2, centerC, "underworld"));
         puzzleGiver.setName("Puzzle-Giver");
         puzzleGiver.setMoveType(NPC.STILL);
         //CultimaPanel.worldMonsters.add(puzzleGiver);
         CultimaPanel.civilians.get(mapIndex).add(puzzleGiver);
         //maybe add bridges extending out from 4 directions seeking a traversable spot to land
         Terrain bridge = TerrainBuilder.getTerrainWithName("INR_$Wood_Plank_floor");
         sizeCircle = 10;
         if(Math.random() < 0.5)
         {  //seek north
            boolean landingSpot = false;
            for(r=centerR-(sizeCircle+1); r>=1; r--)
            {
               if(!isImpassable(cave, r, centerC))
               {
                  landingSpot = true;
                  break;
               }
            }
            if(landingSpot)
            {
               for(r=centerR-(sizeCircle+1); r>=1; r--)
               {
                  if(!isImpassable(cave, r, centerC))
                     break;
                  cave[r][centerC] = bridge.getValue();
               }
            }
         }
         if(Math.random() < 0.5)
         {  //seek south
            boolean landingSpot = false;
            for(r=centerR+(sizeCircle+1); r<cave.length-1; r++)
            {
               if(!isImpassable(cave, r, centerC))
               {
                  landingSpot = true;
                  break;
               }
            }
            if(landingSpot)
            {
               for(r=centerR+(sizeCircle+1); r<cave.length-1; r++)
               {
                  if(!isImpassable(cave, r, centerC))
                     break;
                  cave[r][centerC] = bridge.getValue();
               }
            }
         }
         if(Math.random() < 0.5)
         {  //seek east
            boolean landingSpot = false;
            for(c=centerC-(sizeCircle+1); c>=1; c--)
            {
               if(!isImpassable(cave, centerR, c))
               {
                  landingSpot = true;
                  break;
               }
            }
            if(landingSpot)
            {
               for(c=centerC-(sizeCircle+1); c>=1; c--)
               {
                  if(!isImpassable(cave, centerR, c))
                     break;
                  cave[centerR][c] = bridge.getValue();
               }
            }
         }
         if(Math.random() < 0.5)
         {  //seek west
            boolean landingSpot = false;
            for(c=centerC+(sizeCircle+1); c<cave[0].length-1; c++)
            {
               if(!isImpassable(cave, centerR, c))
               {
                  landingSpot = true;
                  break;
               }
            }
            if(landingSpot)
            {
               for(c=centerC+(sizeCircle+1); c<cave[0].length-1; c++)
               {
                  if(!isImpassable(cave, centerR, c))
                     break;
                  cave[centerR][c] = bridge.getValue();
               }
            }
         }
      
      }
      
      Object [] retVal = new Object[3];
      retVal[0] = cave;
      retVal[1] = enterance; 
      retVal[2] = null;
      if(well1 != null && well2 != null)
      {
         Point [] wells = {well1, well2};
         retVal[2] = wells;
      }
      return retVal;
   }


//lastDir is last player movement direction typed in 0-UP, 1-RIGHT, 2-DOWN, 3-LEFT, for establishing entrance side
//returns 4 Objects in an array: Index 0 will be the Terrain[][] of the cave we built
//                               Index 1 will be an int[] of the starting coordinates
//                               Index 2 will be the number of rooms, used to guide the number of monsters placed in CultimaPanel
//                               Index 3 will be an array of Points that are the locations of 3 doors for a dungeon's 3-doors-puzzle, null if there is no puzzle
//we are sending it the cave's own world location coordinates (thisRow, thisCol) in case it is an insanity cave and we need to teleport us back to the entrance if we get to the end
//if forcePrisons is true, we are generating this for a rescue mission, so add prisons and don't leave it up to randomness
   public static Object[] buildCave(String type, int mapIndex, int thisRow, int thisCol, boolean forcePrisons, byte lastDir)
   {      
      int max = CultimaPanel.SCREEN_SIZE*10;
      int min = CultimaPanel.SCREEN_SIZE*2;
      int size = (int)(Math.random()*(max-min+1)) + min;
   
   //we want the chance for mines to be larger than caves, so roll again if it is smaller than the middle size
      if((type.contains("mine") || type.contains("lair")) && size < (max+min)/2)
         size = (int)(Math.random()*(max-min+1)) + min;
   
      byte[][]cave = new byte[size][size];
      NPCPlayer[][] occupants = new NPCPlayer[size][size];    //mabe will store monoliths
      boolean monolithsAdded = false;
      
      ArrayList<Terrain> caveWalls = new ArrayList<Terrain>();
      ArrayList<Terrain> caveWallSecrets = new ArrayList<Terrain>();
      ArrayList<Terrain> caveWallTorches = new ArrayList<Terrain>();
      ArrayList<Terrain> caveFloors = new ArrayList<Terrain>();
      ArrayList<Terrain> caveFloorTraps = new ArrayList<Terrain>();
      ArrayList<Terrain> caveWallGold = new ArrayList<Terrain>();
   
      for(Terrain t:CultimaPanel.allTerrain)
      {
         if(t.getName().equals("INR_I_B_$Gray_Rock") || t.getName().equals("INR_I_B_$White_Rock"))
            caveWalls.add(t);  
         else if(type.contains("lair") && t.getName().equals("INR_I_B_$Red_Tile_Wall"))               //red tiles
            caveWalls.add(t);
         if(!type.contains("dungeon") && t.getName().equals("INR_I_B_$Earth_Wall"))
            caveWalls.add(t);
         if(type.contains("dungeon") && (t.getName().equals("INR_I_B_$Blue_Brick") || t.getName().equals("INR_I_B_$Red_Brick") || t.getName().equals("INR_I_B_$White_Brick")))
            caveWalls.add(t); 
      
         if(t.getName().equals("INR_D_B_$Gray_Rock") || t.getName().equals("INR_D_B_$White_Rock"))
            caveWallSecrets.add(t);  
         else if(type.contains("lair") && t.getName().equals("INR_D_B_$Tile_Wall"))                   //red tiles
            caveWallSecrets.add(t);
         if(!type.contains("dungeon") && t.getName().equals("INR_D_B_$Earth_Wall"))
            caveWallSecrets.add(t); 
         if(type.contains("dungeon") && (t.getName().equals("INR_D_B_$Blue_Brick_Wall") || t.getName().equals("INR_D_B_$Red_Brick_Wall") || t.getName().equals("INR_D_B_$White_Brick_Wall")))
            caveWallSecrets.add(t); 
      
         if(t.getName().equals("INR_I_B_L_4_$Gray_Rock_Torch") || t.getName().equals("INR_I_B_L_4_$White_Rock_Torch"))
            caveWallTorches.add(t);  
         else if(type.contains("lair") && t.getName().equals("INR_I_B_L_4_$Tile_Wall_Torch"))         //red tiles
            caveWallTorches.add(t);
         if(!type.contains("dungeon") && t.getName().equals("INR_I_B_L_4_$Earth_Wall_Torch"))
            caveWallTorches.add(t); 
         if(type.contains("dungeon") && (t.getName().equals("INR_I_B_L_4_$Blue_Brick_Torch") || t.getName().equals("INR_I_B_L_4_$Red_Brick_Torch") || t.getName().equals("INR_I_B_L_4_$White_Brick_Torch")))
            caveWallTorches.add(t); 
         
         else if(t.getName().equals("INR_$Black_floor") || t.getName().equals("INR_$Blue_floor") || t.getName().equals("INR_$Red_floor"))
            caveFloors.add(t);
         else if(type.contains("lair") && t.getName().equals("INR_$Black_Stone_floor"))               //black stone floor
            caveFloors.add(t);  
         
         else if(t.getName().equals("INR_D_T_$Black_floor_trap") || t.getName().equals("INR_D_T_$Blue_floor_trap") || t.getName().equals("INR_D_T_$Red_floor_trap"))
            caveFloorTraps.add(t);
         else if(type.contains("lair") && t.getName().equals("INR_D_T_$Black_Stone_floor_trap"))     //black stone floor
            caveFloorTraps.add(t);
      }
   
      for(Terrain t:caveWalls)
      {
         if(t.getName().equals("INR_I_B_$Gray_Rock"))
            caveWallGold.add(TerrainBuilder.getTerrainWithName("INR_D_B_$Gray_Rock_Gold"));
         else if(t.getName().equals("INR_I_B_$White_Rock"))
            caveWallGold.add(TerrainBuilder.getTerrainWithName("INR_D_B_$White_Rock_Gold"));
         else if(t.getName().equals("INR_I_B_$Earth_Wall"))
            caveWallGold.add(TerrainBuilder.getTerrainWithName("INR_D_B_$Earth_Wall_Gold"));
         else 
            caveWallGold.add(t);   
      }     
   
      int randW = (int)(Math.random()*caveWalls.size());
      Terrain randWall = caveWalls.get(randW);
      Terrain randWallSecret = caveWallSecrets.get(randW);
      Terrain randWallTorch = caveWallTorches.get(randW);
      Terrain randWallGold = caveWallGold.get(randW);
   
      for(int r=0; r<cave.length; r++)
         for(int c=0; c<cave[0].length; c++)
            cave[r][c] = randWall.getValue();
   
      int enteranceRow = 0, enteranceCol = 0;   //coordinates of where we enter the cave
      int direction = 0;                        //the direction we will cut into the mountain
      int enteranceSize = (int)(Math.random()*4) + 2;
      int startRow = 0, startCol = 0;           //coordinates of where we will start cutting into the cave
   
      int minSize = (int)(Math.random()*2) + 1;       //minimum size of cave hallway
      int sizeRange = (int)(Math.random()*3) + 2;     //size variation of cave room
      int numPaths = 1;
      if(type.contains("mine"))
      {
         minSize = 1;
         sizeRange = 2;
      }
      else if(type.contains("dungeon"))
      {
         sizeRange = (int)(Math.random()*4) + 2;
         numPaths = (int)(Math.random()*3)+1;
         enteranceSize = (int)(Math.random()*2) + 1;
      }
      else if(type.contains("lair"))
      {
         minSize = (int)(Math.random()*3) + 1; 
         sizeRange = (int)(Math.random()*4) + 2; 
         if(minSize == 1 && sizeRange ==2)
            numPaths = (int)(Math.random()*3)+1;
      }
   
      if(lastDir == 2)                         //enter in row 0
      {
         enteranceRow = 0;
         enteranceCol = cave[0].length/2;
         direction = SOUTH;
         startRow = enteranceRow + enteranceSize;
         startCol = enteranceCol;
      }      
      else if(lastDir == 3)                    //enter in last col
      {
         enteranceRow = cave.length/2;
         enteranceCol = cave[0].length - 1;
         direction = WEST;
         startRow = enteranceRow;
         startCol = enteranceCol - enteranceSize;
      }
      else if(lastDir == 0)                    //enter in last row
      {
         enteranceRow = cave.length - 1;
         enteranceCol = cave[0].length/2;
         direction = NORTH;
         startRow = enteranceRow - enteranceSize;
         startCol = enteranceCol;
      }  
      else                                      //ender in col 0
      {
         enteranceRow = cave.length/2;
         enteranceCol = 0;
         direction = EAST;
         startRow = enteranceRow;
         startCol = enteranceCol + enteranceSize;
      }   
      int [] enterance = new int[2];
      enterance[0] = enteranceRow;
      enterance[1] = enteranceCol;
      int randF = (int)(Math.random()*caveFloors.size());
      Terrain randFloor = caveFloors.get(randF);
      Terrain randFloorTrap = caveFloorTraps.get(randF);
   //open up the mouth of the cave - random size from 2-7
      for(int r=enteranceRow - enteranceSize; r<=enteranceRow+enteranceSize; r++)
         for(int c=enteranceCol - enteranceSize; c<=enteranceCol + enteranceSize; c++)
         {
            if(r < 0 || c < 0 || r>=cave.length || c>=cave[0].length)
               continue;
            cave[r][c] = randFloor.getValue();
         }
      int row = startRow;
      int col = startCol; 
      int numRooms = 0;
      boolean placeCoffin = true;
      for(int i=0; i<numPaths; i++)
      { 
         row = startRow;
         col = startCol; 
      
         while(row >=1 && col>=1 && row < cave.length-1 && col<cave[0].length-1)
         {
            int[] newCoord = crawlToEdge(cave, row, col, direction, type);
            row = newCoord[0];
            col = newCoord[1]; 
            
            //random chance a cave can change hall and room dimensions
            if(type.contains("cave") && Math.random() < 0.5)
            {
               if(minSize == 1)
                  minSize++;
               else if(minSize==2)
                  minSize--;
               
               if(sizeRange == 2)
                  sizeRange++;
               else if(sizeRange == 5)
                  sizeRange--;
               else
                  sizeRange += Player.rollDie(-1,1);   
            }
         
         //put down a hallway
            if(type.contains("dungeon") || (type.contains("lair") && Math.random()< 0.5) || Math.random()<0.25)
            {
               int hallLength = (int)(Math.random()*sizeRange*2) + minSize;
               if(direction==NORTH)
               {
                  for(int r=row; r>=row-hallLength; r--)
                  {
                     for(int c=col - minSize; c<=col + minSize; c++)
                     {
                        if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                           continue;
                        cave[r][c] = randFloor.getValue();
                     }
                  }
                  if(row-hallLength > 1)
                     row = row-hallLength;
               }
               else if(direction==SOUTH)
               {
                  for(int r=row; r<=row+hallLength; r++)
                  {
                     for(int c=col - minSize; c<=col + minSize; c++)
                     {
                        if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                           continue;
                        cave[r][c] = randFloor.getValue();
                     }
                  }
                  if(row+hallLength < cave.length-1)
                     row = row+hallLength;
               }
               else if(direction==EAST)
               {
                  for(int r=row - minSize; r<=row + minSize; r++)
                  {
                     for(int c=col; c<=col+hallLength; c++)
                     {
                        if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                           continue;
                        cave[r][c] = randFloor.getValue();
                     }
                  }
                  if(col+hallLength < cave[0].length-1)
                     col = col+hallLength;
               }
               else if(direction==WEST)
               {
                  for(int r=row - minSize; r<=row + minSize; r++)
                  {
                     for(int c=col; c>=col-hallLength; c--)
                     {
                        if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
                           continue;
                        cave[r][c] = randFloor.getValue();
                     }
                  }
                  if(col-hallLength > 1)
                     col = col-hallLength;
               }
            }
            boolean coffinPlaced = openHole(cave,  row,  col, randFloor, randFloorTrap, minSize, sizeRange, type, placeCoffin);
            if(coffinPlaced == true)
               placeCoffin = false;
            numRooms++;
            if(row >=1 && col>=1 && row < cave.length-1 && col<cave[0].length-1)
               if(Math.random() < 0.25)                //randomly change directions
                  direction = (int)(Math.random()*4);   
         }
      }
      if(!type.contains("cave"))
         addTorches(cave, randWallTorch, type);  
   
      if(type.contains("mine"))
         addPosts(cave);
   
      addDoorsAndSecretWalls(cave, randWallSecret, type);
   
      if(type.contains("insanity"))                //insanity cave warps us back to another cave in the world
      {
         int numTries = 0;
         while(row<2 || col<2 || row>=cave.length-2 || col>=cave[0].length-2)
         {
            if(direction==NORTH)                   //march the coordinates away from the last direction we were going
               row++;                              //to get the coordinates in-bounds
            else if(direction==SOUTH)
               row--;
            else if(direction==EAST)
               col--;
            else
               col++;
            numTries++;
            if(numTries >= 1000 && outOfBounds(cave, row, col))
               break;
         }
         if(!outOfBounds(cave, row, col))
         {
            Terrain ter = TerrainBuilder.getTerrainWithName("LOC_$Dungeon_enterance_strange");
            cave[row][col] = ter.getValue();
            //maybe put monoliths around the teleporter
            if(Math.random() < 0.25)
            {
               monolithsAdded = true;
               int dist = Player.rollDie(1,5);
               if(row - dist >= 1 && col - dist >= 1 && !isImpassable(cave, row-dist, col-dist))
                  occupants[row-dist][col-dist] = (new NPCPlayer(NPC.MONOLITH, row-dist, col-dist, mapIndex, row-dist, col-dist, type));
               if(row - dist >= 1 && col + dist <= cave[0].length-2 && !isImpassable(cave, row-dist, col+dist))
                  occupants[row-dist][col+dist] = (new NPCPlayer(NPC.MONOLITH, row-dist, col+dist, mapIndex, row-dist, col+dist, type));
               if(row + dist <= cave.length-2 && col - dist >= 1 && !isImpassable(cave, row+dist, col-dist))
                  occupants[row+dist][col-dist] = (new NPCPlayer(NPC.MONOLITH, row+dist, col-dist, mapIndex, row+dist, col-dist, type));
               if(row + dist <= cave.length-2 && col + dist <= cave[0].length-2 && !isImpassable(cave, row+dist, col+dist))
                  occupants[row+dist][col+dist] = (new NPCPlayer(NPC.MONOLITH, row+dist, col+dist, mapIndex, row+dist, col+dist, type));
            }
         }
      }
      fixBrokenDoors(cave, randFloor);
      fixBrokenTorches(cave, randFloor);
   
      boolean hasPrisons = false;
      if((type.contains("lair") || type.contains("dungeon")) && (Math.random() < 0.5 || forcePrisons))       //add prisons
         hasPrisons = true;
   
      boolean intersectsCave = addRiver(cave, hasPrisons, type);
     
      if(hasPrisons)       //add prisons
      {
         double cellProb = Math.random();
         HashSet<Point> toSkip = new HashSet<Point>();
         int numCells = 0;
         int maxCells = Player.rollDie(5,15);
         if(Math.random() < 0.1)                //10% chance this is a large prison
            maxCells = Player.rollDie(10,30);
         for(int r=2; r<cave.length-2 && numCells <= maxCells; r++)
            for(int c=2; c<cave[0].length-2 && numCells <= maxCells; c++)
            {
               Point loc = new Point(r, c);
               if(toSkip.contains(loc))
                  continue;
               if(Math.random() < cellProb)
               {
                  HashSet<Point> newSkip = tryToPlaceCell(cave, r, c, mapIndex, randFloor, randWall, intersectsCave);
                  if(newSkip.size() > 0)
                     numCells++;
                  for(Point p:newSkip)
                     toSkip.add(p);   
               }
            }
      }
   
   //add gold to mine
      double goldProb = 0.05;
      if(type.contains("cave"))
         goldProb = 0.01;
      if(type.contains("mine") || type.contains("cave"))
      {
         for(int r=2; r<cave.length-2; r++)
            for(int c=2; c<cave[0].length-2; c++)
            {
               Terrain current = TerrainBuilder.getTerrainWithValue(cave[r][c]);
               if(caveWalls.contains(current) && Math.random() < goldProb)
               {
                  cave[r][c] = randWallGold.getValue();
               }
            }
      }
   
   //for lairs with tile walls, fill in with earth wall - red tiles only by paths so starting a fire doesn't eat through the whole mountain
      ArrayList<Point> eggSpots = new ArrayList<Point>();
      ArrayList<Point> barrelSpots = new ArrayList<Point>();
      Terrain earthWall = TerrainBuilder.getTerrainWithName("INR_I_B_$Earth_Wall");
      for(int r=2; r<cave.length-2; r++)
         for(int c=2; c<cave[0].length-2; c++)
         {
            Terrain current = TerrainBuilder.getTerrainWithValue(cave[r][c]);
            String currName = current.getName();
            if(randWall.getName().equals("INR_I_B_$Red_Tile_Wall"))  
            {
               if(currName.equals("INR_I_B_$Red_Tile_Wall") && blockedInFull(cave, r, c))
                  cave[r][c] = earthWall.getValue();
            }
            else if(!currName.toLowerCase().contains("water") && !isImpassable(cave, r, c))
            {
               eggSpots.add(new Point(r, c));
               if(!nextToADoor(cave, r, c) && !currName.toLowerCase().contains("chest") && !currName.toLowerCase().contains("coffin") && !currName.toLowerCase().contains("_d_") &&
                  !currName.toLowerCase().contains("torch") && !currName.toLowerCase().contains("door") && !currName.toLowerCase().contains("gold") &&  occupants[r][c]==null)
               {
                  if(nextToAWall(cave, r, c) || Math.random() < 0.1)
                     barrelSpots.add(new Point(r, c));
               }   
            }
         }
      
      if(type.contains("cave") && eggSpots.size() > 0 && Math.random() < 0.5)
      {
         Point randSpot = eggSpots.get((int)(Math.random()*eggSpots.size()));
         int r=(int)(randSpot.getX());
         int c=(int)(randSpot.getY());
         byte eggType = NPC.SNAKE;
         if(Math.random() < 0.1)
            eggType = NPC.DRAGON;
         CultimaPanel.eggsToHarvest.add(new RestoreItem(mapIndex, r, c, eggType));
      }   
      
      boolean barrelsAdded = false;
      if((type.contains("mine") || type.contains("dungeon")) && barrelSpots.size() > 0)
      {
         int numBarrels = Player.rollDie(1, barrelSpots.size()/20);
         if(type.contains("mine"))
            numBarrels/=2;
         for(int i=0; i<numBarrels && barrelSpots.size() > 0; i++)
         {
            barrelsAdded = true;
            Point randSpot = barrelSpots.remove((int)(Math.random()*barrelSpots.size()));
            int r=(int)(randSpot.getX());
            int c=(int)(randSpot.getY());
            occupants[r][c] = (new NPCPlayer(NPC.BARREL, r, c, mapIndex, type));
         }
      }
      
      
      //***maybe add 3-doors puzzle to dungeon
      boolean puzzleAdded = false;
      Point door1 = null, door2 = null, door3 = null;
      double puzzleChance = 0.25;
      double cavePuzzleChance = 0.25;
      if((type.contains("dungeon") && Math.random() < puzzleChance) || (type.contains("cave") && Math.random() < cavePuzzleChance))
      {
         int sizeCircle = 10;
         if(type.contains("cave"))
            sizeCircle = 8;
         int centerR = (int)(Math.random()*(cave.length - (sizeCircle*2))) + sizeCircle;
         int centerC = (int)(Math.random()*(cave[0].length - (sizeCircle*2))) + sizeCircle;
         boolean doorInRadius = searchForDoorsInRadius(cave, centerR, centerC, sizeCircle);
         boolean canFindPath = canFindPathToCircle(cave, sizeCircle,  centerR,  centerC);
         int numTries = 0;
         while((doorInRadius || !canFindPath) && numTries < 1000)
         {  //don't place puzzle area over any place where there are doors and make it so it can be reached 
            centerR = (int)(Math.random()*(cave.length - (sizeCircle*2))) + sizeCircle;
            centerC = (int)(Math.random()*(cave[0].length - (sizeCircle*2))) + sizeCircle;
            doorInRadius = searchForDoorsInRadius(cave, centerR, centerC, sizeCircle);
            canFindPath = canFindPathToCircle(cave, sizeCircle,  centerR,  centerC);
            numTries++;
         }
         if(!doorInRadius && canFindPath)
         {
            puzzleAdded = true;
            Terrain door = TerrainBuilder.getTerrainWithName("INR_I_B_$Iron_door_locked");
            Terrain marble = TerrainBuilder.getTerrainWithName("INR_$Black_Stone_floor");
            if(type.contains("cave"))
               marble =  caveFloors.get((int)(Math.random()*caveFloors.size()));
            Terrain treasure = TerrainBuilder.getTerrainWithName("ITM_D_$Chest");
            //add the base
            Terrain ter = caveFloors.get((int)(Math.random()*caveFloors.size()));
            placeFloorSpot(cave, centerR, centerC, ter, sizeCircle, true, false);
            //add the floor surrounding the island
            sizeCircle -= 2;
            placeFloorSpot(cave, centerR, centerC, randFloor, sizeCircle, true, false);
            //add the island
            sizeCircle -= 2;
            ter = caveFloors.get((int)(Math.random()*caveFloors.size()));
            placeFloorSpot(cave, centerR, centerC, ter, sizeCircle, true, false);
            //add rooms for the 3 doors
            for(int r=centerR-3; r<=centerR; r++)
               for(int c=centerC-4; c<=centerC+4; c++)
                  cave[r][c] = randWall.getValue();
                       
            if(type.contains("dungeon") )      
            {
              //add the openings for the 3 rooms
               cave[centerR-1][centerC-2] = randFloor.getValue();   
               cave[centerR-1][centerC] = randFloor.getValue();   
               cave[centerR-1][centerC+2] = randFloor.getValue();   
            //add the 3 doors
               cave[centerR][centerC-2] = door.getValue();           //door 1
               cave[centerR][centerC] = door.getValue();             //door 2
               cave[centerR][centerC+2] = door.getValue();           //door 3
                 //store the location of the doors   
               door1 = new Point(centerR, centerC-2);                
               door2 = new Point(centerR, centerC);
               door3 = new Point(centerR, centerC+2); 
            }
            else if(type.contains("cave") )
            {
               cave[centerR][centerC-1] = earthWall.getValue();           //door 1
               cave[centerR][centerC] = earthWall.getValue();             //door 2
               cave[centerR][centerC+1] = earthWall.getValue();           //door 3
                 //store the location of the doors   
               door1 = new Point(centerR, centerC-1);                
               door2 = new Point(centerR, centerC);
               door3 = new Point(centerR, centerC+1); 
            }
                         //add the riddle book
            for(int r=centerR+2; r<=centerR+3; r++)
               for(int c=centerC-1; c<=centerC+1; c++)
                  cave[r][c] = marble.getValue();
            cave[centerR+2][centerC] = TerrainBuilder.getTerrainWithName("ITM_D_$Book").getValue();
            //add the sorcerer puzzle bringer at (centerR-2, centerC)
            if(type.contains("dungeon") )
            {
               NPCPlayer puzzleBringer = (new NPCPlayer(NPC.SORCERER, centerR+2, centerC-1, mapIndex,  centerR+2, centerC-1, "dungeon"));
               puzzleBringer.setName("Puzzle-Bringer");
               puzzleBringer.modifyStats(1.25);
               puzzleBringer.setWeapon(Weapon.getDragonBolt());
               puzzleBringer.setMoveType(NPC.STILL);
               CultimaPanel.civilians.get(mapIndex).add(puzzleBringer);
            }
            else //if(type.contains("cave") )
            {
               NPCPlayer puzzleBringer = (new NPCPlayer(NPC.WISE, centerR+2, centerC-1, mapIndex,  centerR+2, centerC-1, "cave"));
               puzzleBringer.setName("Puzzle-Master");
               puzzleBringer.modifyStats(1.25);
               puzzleBringer.setMoveType(NPC.STILL);
               puzzleBringer.setNumInfo(0);
               CultimaPanel.civilians.get(mapIndex).add(puzzleBringer);
            }
               //pick random spots for the two monsters and gold
            if(type.contains("dungeon") )
            {
               int spotWithGold = Player.rollDie(1,3);
               if(spotWithGold == 1)
               {
                  cave[centerR-1][centerC-2] = treasure.getValue();
               //put monster behind door2 and 3
                  CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.DRAGON, centerR-1, centerC, mapIndex, "dungeon"));
                  CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.DRAGON, centerR-1, centerC+2, mapIndex, "dungeon"));
               }
               else if(spotWithGold == 2)
               {
                  cave[centerR-1][centerC] = treasure.getValue();
               //put monster behind door1 and 3
                  CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.DRAGON, centerR-1, centerC-2, mapIndex, "dungeon"));
                  CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.DRAGON, centerR-1, centerC+2, mapIndex, "dungeon"));
               }
               else//if(spotWithGold == 3)
               {
                  cave[centerR-1][centerC+2] = treasure.getValue();
               //put monster behind door1 and 2
                  CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.DRAGON, centerR-1, centerC-2, mapIndex, "dungeon"));
                  CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.DRAGON, centerR-1, centerC, mapIndex, "dungeon"));
               }
            }
            //maybe add tunnels extending out from 4 directions seeking a traversable spot to connect to 
            sizeCircle = 10;
            if(type.contains("cave"))
               sizeCircle = 8;
            //seek north
            boolean landingSpot = false;
            for(int r=centerR-(sizeCircle+1); r>=1; r--)
            {
               if(!isImpassable(cave, r, centerC))
               {
                  landingSpot = true;
                  break;
               }
            }
            if(landingSpot)
            {
               for(int r=centerR-(sizeCircle+1); r>=1; r--)
               {
                  if(!isImpassable(cave, r, centerC))
                     break;
                  //if the next spot is where we break out, maybe make a secret wall here   
                  if(!isImpassable(cave, r-1, centerC) && r-1>=1 && Math.random()<0.5)
                     cave[r][centerC] = randWallSecret.getValue();
                  else   
                     cave[r][centerC] = randFloor.getValue();
               }
            }
            //seek south
            landingSpot = false;
            for(int r=centerR+(sizeCircle+1); r<cave.length-1; r++)
            {
               if(!isImpassable(cave, r, centerC))
               {
                  landingSpot = true;
                  break;
               }
            }
            if(landingSpot)
            {
               for(int r=centerR+(sizeCircle+1); r<cave.length-1; r++)
               {
                  if(!isImpassable(cave, r, centerC))
                     break;
                  //if the next spot is where we break out, maybe make a secret wall here   
                  if(!isImpassable(cave, r+1, centerC) && r+1<cave.length-1 && Math.random()<0.5)
                     cave[r][centerC] = randWallSecret.getValue();
                  else
                     cave[r][centerC] = randFloor.getValue();
               }
            }
            //seek east
            landingSpot = false;
            for(int c=centerC-(sizeCircle+1); c>=1; c--)
            {
               if(!isImpassable(cave, centerR, c))
               {
                  landingSpot = true;
                  break;
               }
            }
            if(landingSpot)
            {
               for(int c=centerC-(sizeCircle+1); c>=1; c--)
               {
                  if(!isImpassable(cave, centerR, c))
                     break;
                   //if the next spot is where we break out, maybe make a secret wall here   
                  if(!isImpassable(cave, centerR, c-1) && c-1>=1 && Math.random()<0.5)
                     cave[centerR][c] = randWallSecret.getValue();
                  else  
                     cave[centerR][c] = randFloor.getValue();
               }
            }
            //seek west
            landingSpot = false;
            for(int c=centerC+(sizeCircle+1); c<cave[0].length-1; c++)
            {
               if(!isImpassable(cave, centerR, c))
               {
                  landingSpot = true;
                  break;
               }
            }
            if(landingSpot)
            {
               for(int c=centerC+(sizeCircle+1); c<cave[0].length-1; c++)
               {
                  if(!isImpassable(cave, centerR, c))
                     break;
                     //if the next spot is where we break out, maybe make a secret wall here   
                  if(!isImpassable(cave, centerR, c+1) && c+1<cave[0].length-1 && Math.random()<0.5)
                     cave[centerR][c] = randWallSecret.getValue();
                  else
                     cave[centerR][c] = randFloor.getValue();
               }
            }
         }
      }
      //***end 3-doors puzzle
      if(monolithsAdded || barrelsAdded)
      {
         for(int r=0; r<occupants.length; r++)
            for(int c=0; c<occupants[0].length; c++)
            {
               if(occupants[r][c] != null && !isImpassable(cave, r, c))
               {
                  NPCPlayer toAdd = occupants[r][c];
                  if(mapIndex >= CultimaPanel.civilians.size())
                     CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
                  CultimaPanel.civilians.get(mapIndex).add(toAdd);
               }
            }
      }
      
      Object [] retVal = new Object[4];
      retVal[0] = cave;
      retVal[1] = enterance; 
      retVal[2] = new Integer(numRooms);
      if(!puzzleAdded)
         retVal[3] = null;
      else
      {
         Point [] doors = {door1, door2, door3};
         retVal[3] = doors;
      }
      return retVal;
   }

//returns whether or not the river intersects the cave
   public static boolean addRiver(byte[][]cave, boolean onlyShallow, String locType)
   {
   //add vein of water or lava going thorugh cave/mine
      Terrain bridge = TerrainBuilder.getTerrainWithName("INR_$Wood_Plank_floor");
   
      locType = locType.toLowerCase();
      int enteranceRow = 0; int enteranceCol = 0;   //coordinates of where we enter the cave
      int randSide = (int)(Math.random() * 4);  //pick a random side to enter in
      int direction = 0;                        //the direction we will cut into the mountain
      int enteranceSize = (int)(Math.random()*3) + 1;
      int startRow = 0; int startCol = 0;           //coordinates of where we will start cutting into the cave
      int numPaths = (int)(Math.random()*3)+1;
      if(locType.contains("village"))
      {
         enteranceSize = (int)(Math.random()*2) + 1;
         numPaths = (int)(Math.random()*2)+1;
      }
      else if(locType.contains("domicile"))
      {
         enteranceSize = 1;
         numPaths = 1;
      }
      Terrain [] waters = {TerrainBuilder.getTerrainWithName("TER_V_$Shallow_water"), TerrainBuilder.getTerrainWithName("TER_I_$Deep_water"), TerrainBuilder.getTerrainWithName("TER_I_$Rapid_water"), TerrainBuilder.getTerrainWithName("TER_I_L_3_$Lava")}; 
      Terrain water = waters[(int)(Math.random()*waters.length)];
      if(onlyShallow)                            //if there are prison cells in this lair, make any water shallow so it makes all cells accessible
         water = waters[0];
   
      double [] segments = {0.25, 0.5, 0.75};
      if(locType.contains("village") || locType.contains("domicile"))
      {
         double [] segmentsTemp = {0.25, 0.75};
         segments = segmentsTemp;
      }
      if(randSide == 0)                         //enter in row 0
      {
         enteranceRow = 0;
         enteranceCol = (int)(cave[0].length*segments[(int)(Math.random()*segments.length)]);
         direction = SOUTH;
         startRow = enteranceRow + enteranceSize;
         startCol = enteranceCol;
      }      
      else if(randSide == 1)                    //enter in last col
      {
         enteranceRow = (int)(cave.length*segments[(int)(Math.random()*segments.length)]);
         enteranceCol = cave[0].length - 1;
         direction = WEST;
         startRow = enteranceRow;
         startCol = enteranceCol - enteranceSize;
      }
      else if(randSide == 2)                    //enter in last row
      {
         enteranceRow = cave.length - 1;
         enteranceCol = (int)(cave[0].length*segments[(int)(Math.random()*segments.length)]);
         direction = NORTH;
         startRow = enteranceRow - enteranceSize;
         startCol = enteranceCol;
      }  
      else                                      //ender in col 0
      {
         enteranceRow = (int)(cave.length*segments[(int)(Math.random()*segments.length)]);
         enteranceCol = 0;
         direction = EAST;
         startRow = enteranceRow;
         startCol = enteranceCol + enteranceSize;
      }   
      int row = startRow;
      int col = startCol; 
   
      double wanderProb = (Math.random()*50) + 25;
      int wanderAmount = (int)(Math.random()*(enteranceSize/2)) + 1;
      double sizeChangeProb = (Math.random()*30) + 1;
      boolean intersectsCave = false;
      for(int i=0; i<numPaths; i++)
      { 
         row = startRow;
         col = startCol; 
         while(row >=1 && col>=1 && row < cave.length-1 && col<cave[0].length-1)
         {
            for(int r=row-enteranceSize/2; r<=row+enteranceSize/2; r++)
            {
               for(int c=col-enteranceSize/2; c<=col+enteranceSize/2; c++)
               {
                  if(outOfBounds(cave, r, c))
                     continue;
                  String current = TerrainBuilder.getTerrainWithValue(cave[r][c]).getName().toLowerCase();   
                  if(!locType .contains("village") && !locType.contains("domicile"))
                  {  
                     if(!isImpassable(cave, r, c))
                     {
                        if(current.contains("floor"))
                           intersectsCave = true;
                        continue;
                     }
                     if(adjacentToTorch(cave, r, c))
                        continue;   
                  }
                  if(locType.contains("village") || locType.contains("domicile"))
                  {
                     if(current.contains("floor"))
                        cave[r][c] = bridge.getValue();
                     else
                        cave[r][c] = water.getValue();   
                  }
                  else
                     cave[r][c] = water.getValue();   
               }
            }
            if(direction == NORTH)
            {
               row-=enteranceSize/2;
               if(Math.random() < wanderProb)
               {
                  if(Math.random() < 0.5)
                     col+=wanderAmount;
                  else
                     col-=wanderAmount;
               }
            }
            else if(direction == SOUTH)
            {
               row+=enteranceSize/2;   
               if(Math.random() <wanderProb)
               {
                  if(Math.random() < 0.5)
                     col+=wanderAmount;
                  else
                     col-=wanderAmount;
               }
            }
            else if(direction == EAST)
            {
               col+=enteranceSize/2;
               if(Math.random() < wanderProb)
               {
                  if(Math.random() < 0.5)
                     row+=wanderAmount;
                  else
                     row-=wanderAmount;
               }
            }
            else
            {
               col-=enteranceSize/2;
               if(Math.random() < wanderProb)
               {
                  if(Math.random() < 0.5)
                     row+=wanderAmount;
                  else
                     row-=wanderAmount;
               }
            }
            if(Math.random() < sizeChangeProb)
            {
               if(Math.random() < 0.5)
                  enteranceSize++;
               else
               {
                  enteranceSize--;
                  if(enteranceSize == 0 && Math.random() < 0.5)
                     enteranceSize = 1;
               }
            } 
         }
      }
      return intersectsCave; 
   }

//if we can fit it, place a cell at row,col, returns the collection of points to skip trying to place another cell
//canBeOnWater means there is a shallow water chanel in the lair that intersects the main passages
   public static HashSet<Point> tryToPlaceCell(byte[][]cave, int row, int col, int mapIndex, Terrain floor, Terrain wall, boolean canBeOnWater)
   {
      Terrain [] doors  = {TerrainBuilder.getTerrainWithName("INR_I_B_$Wood_door_locked"), TerrainBuilder.getTerrainWithName("INR_I_B_$Iron_door_locked")};
      HashSet<Point>skip = new HashSet<Point>();
      if(!canBeOnWater)
      { 
         boolean isWater = false;
         for(int r=row-2; r<=row+2; r++)
            for(int c=col-2; c<col+2; c++)
            {
               if(outOfBounds(cave, r, c))
                  continue;
               if(TerrainBuilder.getTerrainWithValue(cave[r][c]).getName().toLowerCase().contains("water"))
               {
                  isWater = true;
                  break;
               }
            }
         if(isWater)   
            return skip;
      }
   //cell North of row, col
      if(!outOfBounds(cave, row-2, col-1) && !outOfBounds(cave, row+1, col+1))
      {
         boolean placeCell = true;
         if(isImpassable(cave, row, col) && !isImpassable(cave, row+1, col))
         {  //we want to put a locked door at [row][col], because it is reachable one spot below at [row+1][col]
            for(int r=row-2; r<=row; r++)
               for(int c=col-1; c<=col+1; c++)
                  if(outOfBounds(cave, r, c) || !isImpassable(cave, r, c) || cave[r][c]==doors[0].getValue() || cave[r][c]==doors[1].getValue())
                     placeCell = false;
         
            if(placeCell)
            {
               for(int r=row-2; r<=row; r++)
                  for(int c=col-1; c<=col+1; c++)
                     cave[r][c] = wall.getValue();
               cave[row][col] = doors[(int)(Math.random()*doors.length)].getValue();
               cave[row-1][col] = floor.getValue();
               if(Math.random() < 0.66)
               {
                  if(mapIndex >= CultimaPanel.civilians.size())
                     CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
                  NPCPlayer prisoner = new NPCPlayer(NPC.BEGGER, row-1, col, mapIndex, row-1, col, "dungeon");
                  if(!Utilities.NPCAt(row-1, col, mapIndex))    
                     CultimaPanel.civilians.get(mapIndex).add(prisoner);  
               }
               for(int r=row-2; r<=row+1; r++)
                  for(int c=col-1; c<=col+1; c++)
                     skip.add(new Point(r, c));      
               return skip;  
            }
         }
      }
   //cell South of row, col
      if(!outOfBounds(cave, row-1, col-1) && !outOfBounds(cave, row+2, col+1))
      {
         boolean placeCell = true;
         if(isImpassable(cave, row, col) && !isImpassable(cave, row-1, col))
         {  //we want to put a locked door at [row][col], because it is reachable one spot below at [row+1][col]
            for(int r=row; r<=row+2; r++)
               for(int c=col-1; c<=col+1; c++)
                  if(outOfBounds(cave, r, c) || !isImpassable(cave, r, c) || cave[r][c]==doors[0].getValue() || cave[r][c]==doors[1].getValue())
                     placeCell = false;
            if(placeCell)
            {
               for(int r=row; r<=row+2; r++)
                  for(int c=col-1; c<=col+1; c++)
                     cave[r][c] = wall.getValue();
               cave[row][col] = doors[(int)(Math.random()*doors.length)].getValue();
               cave[row+1][col] = floor.getValue();
               if(Math.random() < 0.66)
               {
                  if(mapIndex >= CultimaPanel.civilians.size())
                     CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
                  if(!Utilities.NPCAt(row+1, col, mapIndex))    
                     CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.BEGGER, row+1, col, mapIndex, row+1, col, "dungeon"));   
               }
               for(int r=row-1; r<=row+2; r++)
                  for(int c=col-1; c<=col+1; c++)
                     skip.add(new Point(r, c));     
               return skip; 
            }
         }
      }
   //cell East of row, col
      if(!outOfBounds(cave, row-1, col-1) && !outOfBounds(cave, row+1, col+2))
      {
         boolean placeCell = true;
         if(isImpassable(cave, row, col) && !isImpassable(cave, row, col-1))
         {  //we want to put a locked door at [row][col], because it is reachable one spot left at [row][col-1]
            for(int r=row-1; r<=row+1; r++)
               for(int c=col; c<=col+2; c++)
                  if(outOfBounds(cave, r, c) || !isImpassable(cave, r, c) || cave[r][c]==doors[0].getValue() || cave[r][c]==doors[1].getValue())
                     placeCell = false;
            if(placeCell)
            {
               for(int r=row-1; r<=row+1; r++)
                  for(int c=col; c<=col+2; c++)
                     cave[r][c] = wall.getValue();
               cave[row][col] = doors[(int)(Math.random()*doors.length)].getValue();
               cave[row][col+1] = floor.getValue();
               if(Math.random() < 0.66)
               {
                  if(mapIndex >= CultimaPanel.civilians.size())
                     CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
                  if(!Utilities.NPCAt(row, col+1, mapIndex))    
                     CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.BEGGER, row, col+1, mapIndex, row, col+1, "dungeon"));   
               }
               for(int r=row-1; r<=row+1; r++)
                  for(int c=col-1; c<=col+2; c++)
                     skip.add(new Point(r, c));     
               return skip; 
            }
         }
      }
   //cell West of row, col
      if(!outOfBounds(cave, row-1, col-2) && !outOfBounds(cave, row+1, col+1))
      {
         boolean placeCell = true;
         if(isImpassable(cave, row, col) && !isImpassable(cave, row, col+1))
         {  //we want to put a locked door at [row][col], because it is reachable one spot left at [row][col-1]
            for(int r=row-1; r<=row+1; r++)
               for(int c=col-2; c<=col; c++)
                  if(outOfBounds(cave, r, c) || !isImpassable(cave, r, c) || cave[r][c]==doors[0].getValue() || cave[r][c]==doors[1].getValue())
                     placeCell = false;
            if(placeCell)
            {
               for(int r=row-1; r<=row+1; r++)
                  for(int c=col-2; c<=col; c++)
                     cave[r][c] = wall.getValue();
               cave[row][col] = doors[(int)(Math.random()*doors.length)].getValue();
               cave[row][col-1] = floor.getValue();
               if(Math.random() < 0.66)
               {
                  if(mapIndex >= CultimaPanel.civilians.size())
                     CultimaPanel.civilians.add(mapIndex, new ArrayList<NPCPlayer>());
                  if(!Utilities.NPCAt(row, col-1, mapIndex))    
                     CultimaPanel.civilians.get(mapIndex).add(new NPCPlayer(NPC.BEGGER, row, col-1, mapIndex, row, col-1, "dungeon"));   
               }
               for(int r=row-1; r<=row+1; r++)
                  for(int c=col-2; c<=col+1; c++)
                     skip.add(new Point(r, c));     
               return skip; 
            }
         }
      }
   
      return skip;
   }

   public static int[] crawlToEdge(byte[][]cave, int row, int col, int direction, String type)
   {
      double varyDirChance = 0.5;
      double edgeBreakChance = 0.25;
      if(type.contains("mine"))
      {
         varyDirChance = 0.25;
         edgeBreakChance = 0.05;
      }
      else if(type.contains("dungeon"))
      {
         varyDirChance = 0.05;
         edgeBreakChance = 0.02;
      }
      else if(type.contains("lair"))
      {
         edgeBreakChance = 0.125;
      }
      int[] newCoord = new int[2];
      while(!CultimaPanel.allTerrain.get(Math.abs(cave[row][col])).getName().contains("_I_"))
      {
         if(row < 1 || col < 1 || row >= cave.length-1 || col>=cave[0].length-1)
            break;
      
         if(direction == NORTH && row-1 >= 0)
         {
            row--;
            if(Math.random() < varyDirChance)    //float different direction
            {
               if(Math.random() < 0.5)
               {
                  if(col-1 >=0)
                     col--;
               }
               else
               {
                  if(col+1 < cave[0].length)
                     col++;
               }
            }
         }
         else if(direction == EAST && col+1 < cave[0].length)
         {
            col++;
            if(Math.random() < varyDirChance)    //float different direction
            {
               if(Math.random() < 0.5)
               {
                  if(row-1 >=0)
                     row--;
               }
               else
               {
                  if(row+1 < cave.length)
                     row++;
               }
            }
         }
         else if(direction == SOUTH && row+1 < cave.length)
         {
            row++;
            if(Math.random() < varyDirChance)    //float different direction
            {
               if(Math.random() < 0.5)
               {
                  if(col-1 >=0)
                     col--;
               }
               else
               {
                  if(col+1 < cave[0].length)
                     col++;
               }
            }
         }
         else if(direction == WEST && col-1 >= 0)
         {
            col--;
            if(Math.random() < varyDirChance)    //float different direction
            {
               if(Math.random() < 0.5)
               {
                  if(row-1 >=0)
                     row--;
               }
               else
               {
                  if(row+1 < cave.length)
                     row++;
               }
            }
         }
         if(Math.random() < edgeBreakChance)
            break;  
      }
      newCoord[0] = row;
      newCoord[1] = col;
      return newCoord;
   }

//caves will make rounder rooms
//mines will make square rooms
//lair will be half-and-half
//returns whether or not a coffin was placed
   public static boolean openHole(byte[][]cave, int row, int col, Terrain randFloor, Terrain randFloorTrap, int minSize, int sizeRange, String type, boolean placeCoffin)
   {
      Terrain [] chests = {TerrainBuilder.getTerrainWithName("ITM_D_$Chest"), TerrainBuilder.getTerrainWithName("ITM_I_D_$Chest_locked"), TerrainBuilder.getTerrainWithName("ITM_D_T_$Chest_trap")};
      double chestProb = 0.01;
      double trapProb = 0;
      double coffinProb = 0;
      boolean coffinPlaced = false;
      if(type.contains("dungeon"))
      {
         chestProb = 0.0075;
         trapProb = 0.025;
         coffinProb = 0.005;
      }
      else if(type.contains("lair"))
      {
         chestProb = 0.01;
         trapProb = 0.02;
         coffinProb = 0.001;
      }
      else if(type.contains("mine"))
      {
         chestProb = 0.005;
         trapProb = 0.01;
         coffinProb = 0.001;
      }
      else if(type.contains("cave"))
      {
         chestProb = 0.005;
         trapProb = 0.005;
         coffinProb = 0.001;
      }
      else if(type.contains("underworld"))
      {
         chestProb = 0;
         trapProb = 0;
         coffinProb = 0;
      }
      Terrain coffin = TerrainBuilder.getTerrainWithName("INR_$Coffin_bed");
   
      int randSize = (int)(Math.random()*sizeRange) + minSize;
      for(int r=row - randSize; r<=row+randSize; r++)
         for(int c=col - randSize; c<=col + randSize; c++)
         {
            if(r < 1 || c < 1 || r >= cave.length-1 || c>=cave[0].length-1)
               continue;
            if((type.contains("mine") || type.contains("dungeon")) && Display.distanceSimple(row, col, r, c) > randSize)
               continue;  
            if(type.contains("lair") && (Math.random() < 0.5) && Display.distanceSimple(row, col, r, c) > randSize)
               continue;  
            if(Math.random() < trapProb && r>2 && c>2 && r<cave.length-2 && c<cave[0].length-2)
               cave[r][c] = randFloorTrap.getValue();
            else if(Math.random() < chestProb && r>2 && c>2 && r<cave.length-2 && c<cave[0].length-2)
               cave[r][c] = chests[(int)(Math.random()*chests.length)].getValue();
            else if(placeCoffin && Math.random() < coffinProb && r>2 && c>2 && r<cave.length-2 && c<cave[0].length-2 && !nextToADoor(cave, r, c))
            {
               cave[r][c] = coffin.getValue();
               coffinPlaced = true;
            }
            else  
               cave[r][c] = randFloor.getValue();
            if(type.contains("lair") || type.contains("mine") || type.contains("dungeon"))     //possibly add torches in to rooms
            {  
            //maybe put a torch in the middle of the room
               if((type.contains("lair") || type.contains("dungeon")) && r==row && c==col && CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName().equals(randFloor.getName()) && !nextToALight(cave,  r,  c))
                  if(Math.random() < 0.25 && r>2 && c>2 && r<cave.length-2 && c<cave[0].length-2)
                     cave[r][c] = TerrainBuilder.getTerrainWithName("INR_D_L_5_$Torch").getValue();
            }
         }
      return coffinPlaced;
   }

   public static void addTorches(byte[][]cave, Terrain randWallTorch, String type)
   {
      double prob = 0.2;
      if(type.contains("mine"))
         prob = 0.1;
      for(int r=0; r<cave.length; r++)
         for(int c=0; c<cave[0].length; c++)
            if(goodTorchSpot(cave, r, c) && Math.random() < prob)
               cave[r][c] = randWallTorch.getValue();
   }

   public static void addPosts(byte[][]cave)
   {
      Terrain wall = TerrainBuilder.getTerrainWithName("INR_I_B_$Wood_Wall");
      int dist = (int)(Math.random()*6) + 5;       //distance between posts
      for(int r=0; r<cave.length; r++)
         for(int c=0; c<cave[0].length; c++)
         {
            String current = CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName();
            if(current.contains("_I_") && !current.contains("_L_") && nextToAFloor(cave, r, c) && (r%dist==0 || c%dist==0))
            {
               cave[r][c] = wall.getValue();
            }
         }
   }

   public static boolean goodTorchSpot(byte[][]cave, int r, int c)
   {
      if(r-1 < 0 || c-1 < 0 || r+1>=cave.length || c+1>=cave.length)
         return false;
      if(!CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName().contains("_I_"))
         return false;
      if(nextToALight(cave, r, c))
         return false;
      if(nextToADoor(cave, r, c))
         return false;
      if(!nextToAFloor(cave, r, c))
         return false;
      return true;
   }

   //returns true if cave[r][c] is next to a shoppe sign
   public static boolean nextToASign(byte[][]cave, int row, int col)
   {
      for(int r=row - 1; r<=row+1; r++)
         for(int c=col - 1; c<=col + 1; c++)
         {
            if(r<0 || c<0 || r>=cave.length || c>=cave[0].length || (r==row && c==col))
               continue;
            String current = CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName().toLowerCase();
            if(current.contains("sign"))
               return true;
         }
      return false;
   }

//returns true if cave[r][c] is next to a wall that is not a light
   public static boolean nextToAWall(byte[][]cave, int row, int col)
   {
      for(int r=row - 1; r<=row+1; r++)
         for(int c=col - 1; c<=col + 1; c++)
         {
            if(r<0 || c<0 || r>=cave.length || c>=cave[0].length || (r==row && c==col))
               continue;
            String current = CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName();
            if(current.contains("_L_"))
               continue;   
            else if(current.contains("_I_"))
               return true;
         }
      return false;
   }

//returns true if cave[r][c] is next to a floor
   public static boolean nextToAFloor(byte[][]cave, int row, int col)
   {
      for(int r=row - 1; r<=row+1; r++)
         for(int c=col - 1; c<=col + 1; c++)
         {
            if(r<0 || c<0 || r>=cave.length || c>=cave[0].length || (r==row && c==col))
               continue;
            String current = CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName();
            if(current.contains("floor"))
               return true;   
         }
      return false;
   }

//returns true if (row, col) can not be reached because it is surrounded by Impassables
   public static boolean blockedIn(byte[][]cave, int r, int c)
   {
      if((r-1>=0 && CultimaPanel.allTerrain.get(Math.abs(cave[r-1][c])).getName().contains("_I_"))
      && (c-1>=0 && CultimaPanel.allTerrain.get(Math.abs(cave[r][c-1])).getName().contains("_I_"))
      && (c+1<cave[0].length && CultimaPanel.allTerrain.get(Math.abs(cave[r][c+1])).getName().contains("_I_"))
      && (r+1<cave.length && CultimaPanel.allTerrain.get(Math.abs(cave[r+1][c])).getName().contains("_I_")))
         return true;
      return false;
   }
   
      //returns true if (row, col) can not be reached because it is surrounded by Impassables
   public static boolean blockedInFull(byte[][]cave, int r, int c)
   {
      if((r-1>=0 && CultimaPanel.allTerrain.get(Math.abs(cave[r-1][c])).getName().contains("_I_"))
      && (c-1>=0 && CultimaPanel.allTerrain.get(Math.abs(cave[r][c-1])).getName().contains("_I_"))
      && (c+1<cave[0].length && CultimaPanel.allTerrain.get(Math.abs(cave[r][c+1])).getName().contains("_I_"))
      && (r+1<cave.length && CultimaPanel.allTerrain.get(Math.abs(cave[r+1][c])).getName().contains("_I_"))
      && (r-1>=0 && c-1>=0 && CultimaPanel.allTerrain.get(Math.abs(cave[r-1][c-1])).getName().contains("_I_"))
      && (r-1>=0 && c+1<cave[0].length && CultimaPanel.allTerrain.get(Math.abs(cave[r-1][c+1])).getName().contains("_I_"))
      && (r+1<cave.length && c-1>=0 && CultimaPanel.allTerrain.get(Math.abs(cave[r+1][c-1])).getName().contains("_I_"))
      && (r+1<cave.length && c+1<cave[0].length && CultimaPanel.allTerrain.get(Math.abs(cave[r+1][c+1])).getName().contains("_I_"))
      )
         return true;
      return false;
   }

//returns true if cave[r][c] is next to a window
   public static boolean nextToAWindow(byte[][]cave, int row, int col)
   {
      for(int r=row - 1; r<=row+1; r++)
         for(int c=col - 1; c<=col + 1; c++)
         {
            if(r<0 || c<0 || r>=cave.length || c>=cave[0].length || (r==row && c==col))
               continue;
            String current = CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName();
            if(current.toLowerCase().contains("window"))
               return true;   
         }
      return false;
   }

//returns true if cave[r][c] is next to a light within 5 units
   public static boolean nextToALight(byte[][]cave, int row, int col)
   {
      for(int r=row - 2; r<=row+2; r++)
         for(int c=col - 2; c<=col + 2; c++)
         {
            if(r<0 || c<0 || r>=cave.length || c>=cave[0].length || (r==row && c==col))
               continue;
            if(CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName().contains("_L_"))
               return true;   
         }
      return false;
   }

//returns true if cave[r][c] is next to a door or secret wall
   public static boolean nextToADoor(byte[][]cave, int row, int col)
   {
      for(int r=row - 1; r<=row+1; r++)
         for(int c=col - 1; c<=col + 1; c++)
         {
            if(r<0 || c<0 || r>=cave.length || c>=cave[0].length || (r==row && c==col))
               continue;
            String current = CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName().toLowerCase();
            if(current.contains("door") || (current.contains("_d_") && (current.contains("wall") || current.contains("rock"))))
               return true;   
         }
      return false;
   }
   
   //returns true if cave[r][c] is next to an outdoor tile
   public static boolean nextToDirt(byte[][]cave, int row, int col)
   {
      for(int r=row - 1; r<=row+1; r++)
         for(int c=col - 1; c<=col + 1; c++)
         {
            if(r<0 || c<0 || r>=cave.length || c>=cave[0].length || (r==row && c==col))
               continue;
            String current = CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName().toLowerCase();
            if(current.contains("ter_"))
               return true;   
         }
      return false;
   }
   
      //returns true if cave[r][c] has a chest
   public static boolean chestAt(byte[][]cave, int r, int c)
   {
      if(r < 0 || c < 0 || r>= cave.length || c>=cave[0].length)
         return false;
      String current = CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName().toLowerCase();
      if(current.contains("chest") || current.contains("book"))
         return true;   
      return false;
   }

//for some openings between walls of size one, put in a secret destroyable wall
   public static void addDoorsAndSecretWalls(byte[][]cave, Terrain randWallSecret, String locType)
   {
      Terrain woodDoor = TerrainBuilder.getTerrainWithName("INR_D_B_$Wood_door");
      Terrain woodDoorLocked = TerrainBuilder.getTerrainWithName("INR_I_B_$Wood_door_locked");
      Terrain woodDoorTrap = TerrainBuilder.getTerrainWithName("INR_D_B_T_$Wood_door_trap");
      Terrain ironDoor = TerrainBuilder.getTerrainWithName("INR_D_B_$Iron_door");
      Terrain ironDoorLocked = TerrainBuilder.getTerrainWithName("INR_I_B_$Iron_door_locked");
      Terrain ironDoorTrap = TerrainBuilder.getTerrainWithName("INR_D_B_T_$Iron_door_trap");
      
      Terrain [] doors     = {woodDoor, woodDoor, woodDoorLocked, woodDoor, woodDoor, woodDoorLocked, woodDoor, woodDoor, woodDoorLocked, ironDoor, ironDoorLocked};
      Terrain [] doorTraps = {woodDoorTrap, woodDoorTrap, woodDoorTrap, woodDoorTrap, woodDoorTrap, woodDoorTrap, woodDoorTrap, woodDoorTrap, woodDoorTrap, ironDoorTrap};
   
      double trapProb = 0.1;
      if(locType.contains("lair"))
         trapProb = 0.25;
   
      for(int r=0; r<cave.length; r++)
      {
         for(int c=0; c<cave[0].length; c++)
         {
            if(r+2 < cave.length && c-1>=0 && c+1< cave[0].length)                                 //   I   
            {                                                                                      //  PPP
               if(CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName().contains("_I_")      //   I
               && CultimaPanel.allTerrain.get(Math.abs(cave[r+2][c])).getName().contains("_I_")    
               && !CultimaPanel.allTerrain.get(Math.abs(cave[r+1][c])).getName().contains("_I_")  
               && !CultimaPanel.allTerrain.get(Math.abs(cave[r+1][c-1])).getName().contains("_I_") 
               && !CultimaPanel.allTerrain.get(Math.abs(cave[r+1][c+1])).getName().contains("_I_") )
               {
                  if(!nextToADoor(cave, r+1, c))
                  {
                     if(Math.random() < 0.25)
                        cave[r+1][c] = randWallSecret.getValue(); 
                     else if(locType.contains("dungeon") || (locType.contains("lair") && Math.random() < 0.5))
                     {
                        if (Math.random() < trapProb)
                           cave[r+1][c] = doorTraps[(int)(Math.random()*doorTraps.length)].getValue();
                        else
                           cave[r+1][c] = doors[(int)(Math.random()*doors.length)].getValue();
                     }
                  }
               }
            }
            else if(c+2 < cave[0].length && r-1>=0 && r+1 < cave.length)                           //   P
            {                                                                                      //  IPI
               if(CultimaPanel.allTerrain.get(Math.abs(cave[r][c])).getName().contains("_I_")      //   P
               && CultimaPanel.allTerrain.get(Math.abs(cave[r][c+2])).getName().contains("_I_")   
               && !CultimaPanel.allTerrain.get(Math.abs(cave[r][c+1])).getName().contains("_I_") 
               && !CultimaPanel.allTerrain.get(Math.abs(cave[r-1][c+1])).getName().contains("_I_") 
               && !CultimaPanel.allTerrain.get(Math.abs(cave[r+1][c+1])).getName().contains("_I_") )
               {
                  if(!nextToADoor(cave, r, c+1))
                  {
                     if(Math.random() < 0.25)
                        cave[r][c+1] = randWallSecret.getValue(); 
                     else if(locType.contains("dungeon") || (locType.contains("lair") && Math.random() < 0.5))
                     {
                        if (Math.random() < trapProb)
                           cave[r][c+1] = doorTraps[(int)(Math.random()*doorTraps.length)].getValue();
                        else
                           cave[r][c+1] = doors[(int)(Math.random()*doors.length)].getValue();
                     }
                  }
               }
            }
         }
      }
   }

   private static byte[][] copyArray(byte[][] m) 
   {
      byte[][] ans = new byte[m.length][m[0].length];
      for (int i = 0; i < m.length; i++) 
      {
         for (int j = 0; j < m[0].length; j++) 
         {
            ans[i][j] = m[i][j];
         }
      }
      return ans;
   }

   private static void transpose(byte[][] m) 
   {
      for (int i = 0; i < m.length; i++) 
      {
         for (int j = i; j < m[0].length; j++) 
         {
            byte x = m[i][j];
            m[i][j] = m[j][i];
            m[j][i] = x;
         }
      }
   }

   public static void rotateLeft(byte[][] m) 
   {
      transpose(m);
      for (int  i = 0; i < m.length/2; i++) 
      {
         for (int j = 0; j < m[0].length; j++) 
         {
            byte x = m[i][j];
            m[i][j] = m[m.length -1 -i][j]; 
            m[m.length -1 -i][j] = x;
         }
      }
   }

   public static void rotateRight(byte[][] m)
   {
      transpose(m);
      for (int  j = 0; j < m[0].length/2; j++) 
      {
         for (int i = 0; i < m.length; i++) 
         {
            byte x = m[i][j];
            m[i][j] = m[i][m[0].length -1 -j]; 
            m[i][m[0].length -1 -j] = x;
         }
      }
   }

   public static void flipUpsideDown(byte[][]m)
   {
      for (int  i = 0; i < m.length/2; i++) 
      {
         for (int j = 0; j < m[0].length; j++) 
         {
            byte x = m[i][j];
            m[i][j] = m[m.length -1 -i][j]; 
            m[m.length -1 -i][j] = x;
         }
      }
   }
   
   public static byte[][] mirrorFlip(byte [][] grid)
   {
      byte [][] temp = new byte[grid.length][grid[0].length];
      for(int r=0;r<grid.length;r++)	
      {
         int boardC = grid[0].length-1;
         for(int c=0;c<grid[0].length;c++)
         {
            temp[r][c] = grid[r][boardC--];
         }
      }
      return temp;
   }
   
   public static void mirrorFlip(Object [][] grid)
   {
      Object [][] temp = new Object[grid.length][grid[0].length];
      for(int r=0;r<grid.length;r++)	
      {
         int boardC = grid[0].length-1;
         for(int c=0;c<grid[0].length;c++)
         {
            temp[r][c] = grid[r][boardC--];
         }
      }
      grid = temp;
   }

   private static void transpose(Object[][] m) 
   {
      for (int i = 0; i < m.length; i++) 
      {
         for (int j = i; j < m[0].length; j++) 
         {
            Object x = m[i][j];
            m[i][j] = m[j][i];
            m[j][i] = x;
         }
      }
   }

   public static void rotateLeft(Object[][] m) 
   {
      transpose(m);
      for (int  i = 0; i < m.length/2; i++) 
      {
         for (int j = 0; j < m[0].length; j++) 
         {
            Object x = m[i][j];
            m[i][j] = m[m.length -1 -i][j]; 
            m[m.length -1 -i][j] = x;
         }
      }
   }

   public static void rotateRight(Object[][] m)
   {
      transpose(m);
      for (int  j = 0; j < m[0].length/2; j++) 
      {
         for (int i = 0; i < m.length; i++) 
         {
            Object x = m[i][j];
            m[i][j] = m[i][m[0].length -1 -j]; 
            m[i][m[0].length -1 -j] = x;
         }
      }
   }

   public static void flipUpsideDown(Object[][]m)
   {
      for (int  i = 0; i < m.length/2; i++) 
      {
         for (int j = 0; j < m[0].length; j++) 
         {
            Object x = m[i][j];
            m[i][j] = m[m.length -1 -i][j]; 
            m[m.length -1 -i][j] = x;
         }
      }
   }

//returns the dungeon location information for the castle (at mapIndex) that houses it
//returns null if the castle at mapIndex does not have a dungeon
   public static Location doesCastleHaveDungeon(int mapIndex)
   {
      for(Location loc:CultimaPanel.allLocations)
      {
         if(loc.getFromMapIndex()==mapIndex)
            return loc;
      }
      return null;
   }

//counts the number of prosoners being held at a certain location (loc)
   public static int countPrisoners(Location loc)
   {
      int count = 0;
      int currentLocIndex = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, loc.getRow(), loc.getCol(), loc.getFromMapIndex());
      if(currentLocIndex < 0 || currentLocIndex >= CultimaPanel.civilians.size())
         return -1;
      for(NPCPlayer p:CultimaPanel.civilians.get(currentLocIndex))
      {
         if(p.getCharIndex()==NPC.BEGGER && !p.isStatue() && !p.isNonPlayer())   //don't include statues in count
            count++;
      }
      return count;
   }

//counts the number of combatant gang members a certain location (loc)
   public static int countGangMembers(Location loc)
   {
      int count = 0;
      for(NPCPlayer p:CultimaPanel.worldMonsters)
      {
         if((p.getName().endsWith("Sibling") || p.getName().contains("Brute")) && !p.isStatue() && !p.isNonPlayer())   //don't include statues in count
            count++;
      }
      return count;
   }
   
   //counts the number of fires at a certain locatino
   public static int countFires(Location loc)
   {
      int count = 0;
      int currentLocIndex = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, loc.getRow(), loc.getCol(), loc.getFromMapIndex());
      for(NPCPlayer p:CultimaPanel.worldMonsters)
         if(p.getMapIndex()==currentLocIndex && p.getCharIndex()==NPC.FIRE)   
            count++;
      return count;
   }

//counts the number of living monsters at a certain locatino
   public static int countMonsters(Location loc)
   {
      int count = 0;
      int currentLocIndex = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, loc.getRow(), loc.getCol(), loc.getFromMapIndex());
      for(NPCPlayer p:CultimaPanel.worldMonsters)
      {
         if(p.getMapIndex()==currentLocIndex && !p.isStatue()  && !p.isNonPlayer() && !NPC.isNatural(p.getCharIndex()) && p.getCharIndex()!=NPC.DOG)   //don't include statues in count
            count++;
      }
      return count;
   }

//counts the number of living civilians at a certain locatino
   public static int countCivilians(Location loc)
   {
      int count = 0;
      int currentLocIndex = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, loc.getRow(), loc.getCol(), loc.getFromMapIndex());
      if(currentLocIndex < 0 || currentLocIndex >= CultimaPanel.civilians.size())
         return -1;
      for(NPCPlayer p:CultimaPanel.civilians.get(currentLocIndex))
      {
         if(NPC.isCivilian(p.getCharIndex()) && !p.isStatue() && !p.isNonPlayer() && p.getCharIndex()!=NPC.BARREL)   //don't include statues in count
            count++;
      }
      return count;
   }

//locType is "dungeon", "lair", "cave", "mine", "arena" or "temple"
//if being called from building a castle, nextIndex will be 1 so that this location's index will be after the index used for the castle that houses it
   public static Location constructAdventureAt(Location loc, String locType)
   {
   //procedurally generate the world at this mapIndex
      int currentLocIndex = CultimaPanel.getLocationIndex(CultimaPanel.allLocations, loc.getRow(), loc.getCol(), loc.getFromMapIndex());
      Location currentLoc = null;
      if(currentLocIndex >= 1)
         currentLoc = CultimaPanel.allLocations.get(currentLocIndex);
      String provinceName = "?";
      if(currentLoc != null)
         provinceName = Display.provinceName(currentLoc.getName());
      boolean capitalCity = Utilities.isCapitalCity(currentLoc);
   
      CultimaPanel.civilians.add(new ArrayList<NPCPlayer>());
      CultimaPanel.corpses.add(new ArrayList<Corpse>());
      Object[] temp = null;
   
      if(locType.contains("cave") || locType.contains("mine") || locType.contains("lair") || locType.contains("dungeon"))
         temp = LocationBuilder.buildCave(locType, CultimaPanel.map.size(), loc.getRow(), loc.getCol(), true, (byte)(Player.rollDie(0,3)));
      else //if(locType.contains("temple") || city || village || fort ||domicile)
      {
         byte waterDir = -1;
         if(locType.contains("port") || capitalCity)
         {
            byte[][]currMap = CultimaPanel.map.get(0);
            int lastR = loc.getRow();
            int lastC = loc.getCol();
            for(int dist = 1; dist<=2; dist++)
            {
               String rightTerrain = CultimaPanel.allTerrain.get(Math.abs(currMap[lastR][CultimaPanel.equalizeCol(lastC+dist)])).getName().toLowerCase();
               String leftTerrain = CultimaPanel.allTerrain.get(Math.abs(currMap[lastR][CultimaPanel.equalizeCol(lastC-dist)])).getName().toLowerCase();
               String upTerrain = CultimaPanel.allTerrain.get(Math.abs(currMap[CultimaPanel.equalizeRow(lastR-dist)][CultimaPanel.equalizeCol(lastC)])).getName().toLowerCase();
               String downTerrain = CultimaPanel.allTerrain.get(Math.abs(currMap[CultimaPanel.equalizeRow(lastR+dist)][CultimaPanel.equalizeCol(lastC)])).getName().toLowerCase();
               if(rightTerrain.contains("water"))
                  waterDir = LocationBuilder.EAST;
               if(leftTerrain.contains("water"))
                  waterDir = LocationBuilder.WEST;
               if(upTerrain.contains("water"))
                  waterDir = LocationBuilder.NORTH;
               if(downTerrain.contains("water"))
                  waterDir = LocationBuilder.SOUTH;
               if(waterDir == -1)
               {
                  String upRightTerrain = CultimaPanel.allTerrain.get(Math.abs(currMap[CultimaPanel.equalizeRow(lastR-dist)][CultimaPanel.equalizeCol(lastC+dist)])).getName().toLowerCase();
                  String upLeftTerrain = CultimaPanel.allTerrain.get(Math.abs(currMap[CultimaPanel.equalizeRow(lastR-dist)][CultimaPanel.equalizeCol(lastC-dist)])).getName().toLowerCase();
                  String downRightTerrain = CultimaPanel.allTerrain.get(Math.abs(currMap[CultimaPanel.equalizeRow(lastR+dist)][CultimaPanel.equalizeCol(lastC+dist)])).getName().toLowerCase();
                  String downLeftTerrain = CultimaPanel.allTerrain.get(Math.abs(currMap[CultimaPanel.equalizeRow(lastR+dist)][CultimaPanel.equalizeCol(lastC-dist)])).getName().toLowerCase();
                  if(upRightTerrain.contains("water"))
                  {
                     waterDir = LocationBuilder.NORTH;
                     if(Math.random()<0.5)
                        waterDir = LocationBuilder.EAST;
                  }
                  if(upLeftTerrain.contains("water"))
                  {
                     waterDir = LocationBuilder.NORTH;
                     if(Math.random()<0.5)
                        waterDir = LocationBuilder.WEST;
                  }                           
                  if(downRightTerrain.contains("water"))
                  {
                     waterDir = LocationBuilder.SOUTH;
                     if(Math.random()<0.5)
                        waterDir = LocationBuilder.EAST;
                  }                           
                  if(downLeftTerrain.contains("water"))
                  {
                     waterDir = LocationBuilder.SOUTH;
                     if(Math.random()<0.5)
                        waterDir = LocationBuilder.WEST;
                  }                    
               }
               if(waterDir != -1)
                  break;
            }
         }
         temp = LocationBuilder.buildDomicile(TerrainBuilder.getTerrainWithName("TER_$Grassland"), locType, CultimaPanel.map.size(), provinceName, (byte)(Player.rollDie(0,3)), waterDir, capitalCity, loc.getRow(), loc.getCol());
      }                               
      byte[][]newLocation = (byte[][])(temp[0]);
      int[] startCoord = (int[])(temp[1]);
      int numOccupants = ((Integer)(temp[2])).intValue();   //number of people at a temple, or # rooms in a cave, mine, lair or dungeon
       
      if((locType.contains("dungeon") || locType.contains("cave")) && temp.length<=4 && temp[3]!=null)
      {  //see if this is a dungeon with the 3-doors riddle, record the location of the 3 doors
         Point [] doorLocs = (Point[])(temp[3]);
         if(doorLocs.length == 3)
         {
            Point door1 = doorLocs[0];
            Point door2 = doorLocs[1];
            Point door3 = doorLocs[2];
            ArrayList<Point>riddleDoors = new ArrayList<Point>();
            riddleDoors.add(door1);
            riddleDoors.add(door2);
            riddleDoors.add(door3);
            loc.setRiddlePoints(riddleDoors);
         }
      }  
      int locIndex = CultimaPanel.map.size();
      Teleporter tele = new Teleporter(locIndex, startCoord[0], startCoord[1], locType);
      loc.setTeleporter(tele);
      loc.setMapIndex(locIndex);
         
   //pick and add monsters to loc  
      if(locType.contains("cave") || locType.contains("mine") || locType.contains("lair") || locType.contains("dungeon"))
      {
         int numMonsters = (int)(Math.random() * (numOccupants/2 - numOccupants/3 + 1)) + (numOccupants/3);
         byte monsterIndex = NPC.randomWorldMonster();
         if(locType.contains("dungeon"))
            monsterIndex = NPC.randomDominantDungeonMonster();
         else if(locType.contains("lair"))
            monsterIndex = NPC.randomDominantLairMonster();
         else if(locType.contains("cave"))
            monsterIndex = NPC.randomCaveMonster();
         else if(locType.contains("mine"))
            monsterIndex = NPC.randomDominantMineMonster();
         else if(locType.contains("temple"))
         {
            monsterIndex = NPC.randomTempleMonster();   
            numMonsters = (int)(Math.random() * (newLocation.length/4 - newLocation.length/8 + 1)) + (newLocation.length/8);
         }  
         ArrayList<Point> monsterFreq = new ArrayList<Point>();
         monsterFreq.add(new Point(monsterIndex, numMonsters));
         loc.setMonsterFreq(monsterFreq);
      }
      CultimaPanel.allLocations.set(currentLocIndex, loc);                                       
      Display.swap(CultimaPanel.allLocations, currentLocIndex, locIndex);   //move the Location into the same index in allLocations List as the mapIndex in the maps List
      CultimaPanel.map.add(newLocation);
      FileManager.writeOutLocationsToFile(CultimaPanel.allLocations, "maps/worldLocs.txt");
      String filename = "maps/"+locIndex+"_"+newLocation.length+"_"+newLocation[0].length+".bin";
      CultimaPanel.mapFiles.add(filename);
      FileManager.writeOutMapFileNames(CultimaPanel.mapFiles, "maps/mapFileNames.txt");
      FileManager.writeToBinFile(filename, newLocation); 
   
      Location castleLoc = LocationBuilder.doesCastleHaveDungeon(locIndex);
      if(castleLoc!=null && castleLoc.getMapIndex() == -1)
      {  //procedurally generate the world at this mapIndex
         castleLoc = LocationBuilder.constructAdventureAt(castleLoc, "dungeon");
      }
      
      return loc;
   }
   
   //returns the location of a bed in the Player's house in the current location, null if there is none
   public static Point findOurBed()
   {
      if(!TerrainBuilder.isCity(CultimaPanel.player.getLocationType()))
         return null;
      byte[][]currMap = (CultimaPanel.map.get(CultimaPanel.player.getMapIndex())); 
      Terrain floor = TerrainBuilder.getTerrainWithName("INR_$Purple_floor_inside");
      Terrain bed = TerrainBuilder.getTerrainWithName("INR_$Bed"); 
      for(int r=1; r<currMap.length-1; r++)
         for(int c=1; c<currMap.length-1; c++)
         {
            if(Math.abs(currMap[r][c]) == bed.getValue())
            {
               for(int r2=r-2; r2<=r+2; r2++)
                  for(int c2=c-2; c2<=c+2; c2++)
                  {
                     if(r2<0 || c2<0 || r2>=currMap.length || c2>=currMap[0].length)
                        continue;
                     if(Math.abs(currMap[r2][c2])==floor.getValue())
                        return new Point(r, c);
                  }
            }
         }
      
      return null;
   }
  
    //returns the location of a bed in what could be the Player's house in the current location, null if there is none
   public static Point findOurBed2()
   {
      if(!TerrainBuilder.isCity(CultimaPanel.player.getLocationType()))
         return null;
      byte[][]currMap = (CultimaPanel.map.get(CultimaPanel.player.getMapIndex())); 
      Terrain floor = TerrainBuilder.getTerrainWithName("INR_$PurpleDirty_floor_inside");
      Terrain bed = TerrainBuilder.getTerrainWithName("INR_$Bed"); 
      for(int r=1; r<currMap.length-1; r++)
         for(int c=1; c<currMap.length-1; c++)
         {
            if(Math.abs(currMap[r][c]) == bed.getValue())
            {
               for(int r2=r-2; r2<=r+2; r2++)
                  for(int c2=c-2; c2<=c+2; c2++)
                  {
                     if(r2<0 || c2<0 || r2>=currMap.length || c2>=currMap[0].length)
                        continue;
                     if(Math.abs(currMap[r2][c2])==floor.getValue())
                        return new Point(r, c);
                  }
            }
         }
      
      return null;
   }
}