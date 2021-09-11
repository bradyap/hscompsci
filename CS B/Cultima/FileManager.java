import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.util.Scanner;
import java.awt.Point;

public class FileManager {
    // to test reading in player information from a file
    public static void main(String[] arg) {
        boolean pass = false;
        // try from testPlayerFile1.txt
        Player attempt = FileManager.readPlayerFromFile("testPlayerFile1.txt");

        if (attempt == null) {
            System.out.println("testPlayerFile1.txt work. Check the last message to see where the problem is");
        } else {
            // System.out.println(attempt.toString());
            String fromYourMethod = attempt.toString();
            String fromFile = readFile("testPlayerFile1.txt");
            if (fromYourMethod.equals(fromFile)) {
                System.out.println("Success reading from testPlayerFile1.txt");
                pass = true;
            } else {
                System.out.println("Something went wrong. Compare the following with what is in testPlayerFile1.txt");
                System.out.println(fromYourMethod);
            }
        }

        // try from testPlayerFile2.txt if the first test worked
        if (pass) {
            attempt = FileManager.readPlayerFromFile("testPlayerFile2.txt");
            if (attempt == null) {
                System.out.println("testPlayerFile2.txt work. Check the last message to see where the problem is");
            } else {
                String fromYourMethod = attempt.toString();
                String fromFile = readFile("testPlayerFile2.txt");
                if (fromYourMethod.equals(fromFile)) {
                    System.out.println("Success reading from testPlayerFile2.txt");
                } else {
                    System.out
                            .println("Something went wrong. Compare the following with what is in testPlayerFile2.txt");
                    System.out.println(fromYourMethod);
                }
            }
        }
        System.exit(0);
    }

    // pre: line != null
    // post:if active is true, returns an encoded version of the String line to
    // write out to the player file
    // if active is false, returns the unaltered String line
    public static String encode(String line, boolean activate) {
        if (!activate)
            return line;
        String ans = "";
        for (int i = 0; i < line.length(); i++) {
            char let = line.charAt(i);
            if (let < 33 || let > 126)
                ans += let;
            else
                ans += (char) (let - 3);
        }
        return ans;
    }

    // pre: line != null
    // post:if active is true, returns a decoded version of the String line
    // if active is false, returns the unaltered String line
    public static String decode(String line, boolean activate) {
        if (!activate)
            return line;
        String ans = "";
        for (int i = 0; i < line.length(); i++) {
            char let = line.charAt(i);
            if (let < 33 || let > 126)
                ans += let;
            else
                ans += (char) (let + 3);
        }
        return ans;
    }

    // pre: word != null
    // post:returns true if word could be represented as a whole number, positive or
    // negative
    public static boolean wordIsInt(String word) {
        if (word == null || word.length() == 0)
            return false;
        word = word.trim();
        if (word.length() == 1 && (word.charAt(0) == '-' || !Character.isDigit(word.charAt(0))))
            return false;
        for (int i = 1; i < word.length(); i++)
            if (!Character.isDigit(word.charAt(i))) {
                return false;
            }
        return true;
    }

    // pre: word != null
    // post:returns true if word could be represented as a double, positive or
    // negative
    public static boolean wordIsDouble(String word) {
        if (word == null || word.length() == 0)
            return false;
        word = word.trim();
        if (word.length() == 1 && (word.charAt(0) == '-' || !Character.isDigit(word.charAt(0))))
            return false;
        int numDecimals = 0;
        for (int i = 1; i < word.length(); i++) {
            char current = word.charAt(i);
            if (current == '.')
                numDecimals++;
            else if (!Character.isDigit(current))
                return false;
            if (numDecimals > 1)
                return false;
        }
        return true;
    }

    // ***********************Player file
    // post: writes out all Player information into a file
    public static boolean writeOutPlayerToFile(String fileName) {
        File imageFile = new File(fileName);
        if (imageFile.exists()) // make sure to write over old file
        {
            imageFile.delete();
            imageFile = new File(fileName);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(imageFile);
            if (CultimaPanel.ENCODE_FILES)
                writer.println(Player.rollDie(1, 1000) * 2); // starting with an even number means that we want to
                                                             // encode the strings out to the file
            else
                writer.println((Player.rollDie(1, 1000) * 2) + 1);
            writer.println(encode(CultimaPanel.player.toString(), CultimaPanel.ENCODE_FILES));
            writer.println(encode(CultimaPanel.days + "     **days", CultimaPanel.ENCODE_FILES));
            double roundedTime = ((int) (CultimaPanel.time * 100)) / 100.0;
            writer.println(encode(roundedTime + "     ***time", CultimaPanel.ENCODE_FILES));
            writer.close();
        } catch (Exception ex) {
            System.out.println("writeOutPlayerToFile error:" + ex);
            return false;
        }
        return true;
    }

    // pre: fileName is a properly formated file of player information
    // post:read Player info from file and returns a Player object.
    // If the file doesn't exist or is improperly formated, returns null
    public static Player readPlayerFromFile(String fileName) {
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            boolean encoded = false;
            if (input.hasNextLine()) {
                String line = input.nextLine().trim(); // read in whether or not the file is encoded, and needs to be
                                                       // decoded
                int pos = line.indexOf("**"); // this will be a random integer. If it is even, then the file is encoded.
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                int enc = 0;
                if (wordIsInt(line))
                    enc = Integer.parseInt(line);
                if (enc % 2 == 0)
                    encoded = true;
            }
            if (input.hasNextLine()) {
                // code to read name from file: Line 2
                String name = "";
                String line = decode(input.nextLine().trim(), encoded); // **name
                int pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                name = line;

                // code to read mapMark from file: Line 3
                int[] mapMark = null;
                line = decode(input.nextLine().trim(), encoded); // **mapMark
                pos = line.indexOf("**");
                if (pos >= 0) {
                    line = line.substring(0, pos).trim();
                }
                // **************TO DO: enter code to process mapMark from file: Line 3
                // initialize the mapMark array to an array of 3 ints line -> "-1 -1 -1"
                // split the String line around the space character parts[0]->"-1",
                // parts[1]->"-1", parts[2]->"-1"
                // trim each element, and fill the mapMark array with the int version of each
                // cleaned element
                mapMark = new int[3];
                String[] parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (!wordIsInt(parts[i])) {
                        return null;
                    }
                    parts[i] = parts[i].trim();
                    mapMark[i] = Integer.parseInt(parts[i]);
                }

                // ******************************************************************/
                if (mapMark == null || mapMark.length != 3) {
                    System.out.println("Error reading mapMark from player file");
                    return null;
                }

                // code to read potions from file: Line 4
                int[] potions = null;
                line = decode(input.nextLine().trim(), encoded); // **potions
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process potions from file: Line 4
                potions = new int[Potion.NUM_POTIONS];
                parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (!wordIsInt(parts[i])) {
                        return null;
                    }
                    parts[i] = parts[i].trim();
                    potions[i] = Integer.parseInt(parts[i]);
                }

                // ******************************************************************/
                if (potions == null || potions.length != Potion.NUM_POTIONS) {
                    System.out.println("Error reading potions from player file");
                    return null;
                }

                // code to read flowerBoxCount from file: Line 5
                int[] flowerBoxCount = null;
                line = decode(input.nextLine().trim(), encoded); // **flowerBoxCount
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process flowerBoxCount from file: Line 5
                flowerBoxCount = new int[Player.NUM_FLOWERS];
                parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (!wordIsInt(parts[i])) {
                        return null;
                    }
                    parts[i] = parts[i].trim();
                    flowerBoxCount[i] = Integer.parseInt(parts[i]);
                }

                // ******************************************************************/
                if (flowerBoxCount == null || flowerBoxCount.length != Player.NUM_FLOWERS) {
                    System.out.println("Error reading flowerBoxCount from player file");
                    return null;
                }

                // code to read specificExperience from file: Line 6
                int[] specificExperience = null;
                line = decode(input.nextLine().trim(), encoded); // **specificExperience
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process specificExperience from file: Line
                // 6
                specificExperience = new int[4];
                parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (!wordIsInt(parts[i])) {
                        return null;
                    }
                    parts[i] = parts[i].trim();
                    specificExperience[i] = Integer.parseInt(parts[i]);
                }
                // ******************************************************************/
                if (specificExperience == null || specificExperience.length != 4) {
                    System.out.println("Error reading specificExperience from player file");
                    return null;
                }

                // code to read stats from file: Line 7
                int[] stats = null;
                line = decode(input.nextLine().trim(), encoded); // **stats
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process stats from file: Line 7
                stats = new int[Player.NUM_STATS];
                parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (!wordIsInt(parts[i])) {
                        return null;
                    }
                    parts[i] = parts[i].trim();
                    stats[i] = Integer.parseInt(parts[i]);
                }
                // ******************************************************************/
                if (stats == null || stats.length != Player.NUM_STATS) {
                    System.out.println("Error reading stats from player file");
                    return null;
                }

                // code to read infoIndexes from file: Line 8
                int[] infoIndexes = null;
                line = decode(input.nextLine().trim(), encoded); // **infoIndexes
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process infoIndexes from file: Line 8
                infoIndexes = new int[Player.NUM_INFO_INDEXES];
                parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (!wordIsInt(parts[i])) {
                        return null;
                    }
                    parts[i] = parts[i].trim();
                    infoIndexes[i] = Integer.parseInt(parts[i]);
                }
                // ******************************************************************/
                if (infoIndexes == null || infoIndexes.length != Player.NUM_INFO_INDEXES) {
                    System.out.println("Error reading infoIndexes from player file");
                    return null;
                }

                // code to read spellHotKeys from file: Line 9
                int[] spellHotKeys = null;
                line = decode(input.nextLine().trim(), encoded); // **spellHotKeys
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process spellHotKeys from file: Line 9
                spellHotKeys = new int[4];
                parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (!wordIsInt(parts[i])) {
                        return null;
                    }
                    parts[i] = parts[i].trim();
                    spellHotKeys[i] = Integer.parseInt(parts[i]);
                }
                // ******************************************************************/
                if (spellHotKeys == null || spellHotKeys.length != 4) {
                    System.out.println("Error reading spellHotKeys from player file");
                    return null;
                }

                // code to read potionHotKeys from file: Line 10
                int[] potionHotKeys = null;
                line = decode(input.nextLine().trim(), encoded); // **potionHotKeys
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process potionHotKeys from file: Line 10
                potionHotKeys = new int[Potion.NUM_POTIONS];
                parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (!wordIsInt(parts[i])) {
                        return null;
                    }
                    parts[i] = parts[i].trim();
                    potionHotKeys[i] = Integer.parseInt(parts[i]);
                }
                // ******************************************************************/
                if (potionHotKeys == null || potionHotKeys.length != Potion.NUM_POTIONS) {
                    System.out.println("Error reading potionHotKeys from player file");
                    return null;
                }

                // code to read weaponHotKeys from file: Line 11
                Point[] weaponHotKeys = null;
                line = decode(input.nextLine().trim(), encoded); // **weaponHotKeys
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process weaponHotKeys from file: Line 11
                weaponHotKeys = new Point[4];
                parts = line.split(" ");
                for (int i = 0; i < parts.length; i++) {
                    if (!wordIsInt(parts[i])) {
                        return null;
                    }
                    parts[i] = parts[i].trim();
                    weaponHotKeys[i / 2] = new Point(Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1]));
                    i++;
                }
                // ******************************************************************/
                if (weaponHotKeys == null || weaponHotKeys.length != 4) {
                    System.out.println("Error reading weaponHotKeys from player file");
                    return null;
                }

                // code to read effects from file: Line 12
                ArrayList<String> effects = null;
                line = decode(input.nextLine().trim(), encoded); // **effects
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process effects from file: Line 12
                effects = new ArrayList<String>();
                String temp = line.substring(1, line.length() - 1).trim();``````
                parts = temp.split(",");
                for (int i = 0; i < parts.length; i++) {
                    effects.add(parts[i].trim());
                }
                // ******************************************************************/
                if (effects == null) {
                    System.out.println("Error reading effects from player file");
                    return null;
                }

                // code to read images from file: Line 13
                ArrayList<String> fileNames = null;
                line = decode(input.nextLine().trim(), encoded); // **images ArrayList
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process fileNames from file: Line 13
                // initialize the fileNames ArrayList (substring),line stores:
                // "[images/characters/player/saber1.Gif, images/characters/player/saber2.Gif,
                // images/characters/player/saber3.Gif, images/characters/player/saber4.Gif]"
                // remove '[' and ']' (substring),line stores:
                // "images/characters/player/saber1.Gif, images/characters/player/saber2.Gif,
                // images/characters/player/saber3.Gif, images/characters/player/saber4.Gif"
                // separate elements around ',' (split) String [] stores 4 elements:
                // "images/characters/player/saber1.Gif", "images/characters/player/saber2.Gif",
                // "images/characters/player/saber3.Gif" and
                // "images/characters/player/saber4.Gif"
                // add each trimmed element into fileNames ArrayList(add, trim) fileNames
                // ArrayList stores 4 elements: "images/characters/player/saber1.Gif",
                // "images/characters/player/saber2.Gif", "images/characters/player/saber3.Gif"
                // and "images/characters/player/saber4.Gif"
                fileNames = new ArrayList<String>();
                temp = line.substring(1, line.indexOf("]"));
                parts = temp.split(",");
                for (int i = 0; i < parts.length; i++) {
                    fileNames.add(parts[i].trim());
                }
                // ******************************************************************/
                if (fileNames == null || fileNames.size() == 0) {
                    System.out.println("Error reading fileNames from player file");
                    return null;
                }

                // code to read items from file: Line 14
                ArrayList<String> items = null;
                line = decode(input.nextLine().trim(), encoded); // **items
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process items from file: Line 14
                items = new ArrayList<String>();
                temp = line.substring(1, line.indexOf("]"));

                parts = temp.split(",");
                for (int i = 0; i < parts.length; i++) {
                    items.add(parts[i].trim());
                }
                // ******************************************************************/
                if (items == null) {
                    System.out.println("Error reading items from player file");
                    return null;
                }

                // code to read spells from file: Line 15
                ArrayList<Spell> spells = null;
                line = decode(input.nextLine().trim(), encoded); // **spells
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process spells from file: Line 15
                spells = new ArrayList<Spell>();
                temp = line.substring(1, line.indexOf("]"));
                parts = temp.split(",");
                for (int i = 0; i < parts.length; i++) {
                    spells.add(Spell.getSpellWithName((parts[i].trim())));
                }
                // ******************************************************************/
                if (spells == null) {
                    System.out.println("Error reading spells from player file");
                    return null;
                }

                // code to read teleporter memory from file: Line 16
                LinkedList<Teleporter> memory = null;
                line = decode(input.nextLine().trim(), encoded); // **teleporter-memory
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process teleporter memory from file: Line
                // 16
                memory = new LinkedList<Teleporter>();
                temp = line.substring(1, line.indexOf("]"));
                parts = temp.split(", ");
                for (int i = 0; i < parts.length; i++) {
                    String[] secondParts = parts[i].trim().substring(1, parts[i].length() - 1).split(" ");

                    int ti, tr, tc;
                    if (!wordIsInt(secondParts[0]) || !wordIsInt(secondParts[1]) || !wordIsInt(secondParts[2])) {
                        System.out.println("Error reading teleporter memory from player file int");
                        return null;
                    }

                    ti = Integer.parseInt(secondParts[0]);
                    tr = Integer.parseInt(secondParts[1]);
                    tc = Integer.parseInt(secondParts[2]);
                    String lt = secondParts[3];
                    memory.add(new Teleporter(ti, tr, tc, lt));
                }
                // ******************************************************************/
                if (memory == null) {
                    System.out.println("Error reading teleporter memory from player file");
                    return null;
                }

                // code to read armorSet and armorFreq from file: Line 17
                ArrayList<Armor> armorSet = null;
                ArrayList<Integer> armorFreq = null;
                line = decode(input.nextLine().trim(), encoded); // **armor:armorFrequency
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                // **************TO DO: enter code to process armorSet and armorFreq from file:
                // Line 17
                armorSet = new ArrayList<Armor>();
                armorFreq = new ArrayList<Integer>();
                temp = line.substring(1, line.indexOf("]"));
                parts = temp.split(",");

                for (int i = 0; i < parts.length; i++) {
                    // ****************************************************************
                    // System.out.println(parts[i]);
                    var secondParts = parts[i].trim().split(":");
                    if (secondParts.length != 2) {
                        System.out.println("Error reading armorSet and armorFreq from player file len");
                        return null;
                    }
                    if (!wordIsInt(secondParts[1])) {
                        System.out.println("Error reading armorSet and armorFreq from player file int");
                        return null;
                    }
                    armorSet.add(Armor.getArmorWithName(secondParts[0]));
                    armorFreq.add(Integer.parseInt(secondParts[1]));
                }

                // ******************************************************************/
                if (armorSet == null || armorFreq == null) {
                    System.out.println("Error reading armorSet and armorFreq from player file");
                    return null;
                }

                // code to read weapons and weaponFrequencies from file: Lines 18-32
                ArrayList<Weapon>[] weapons = null;
                ArrayList<Integer>[] weaponFrequencies = null;
                // **************TO DO: enter code to process weapons and weaponFrequencies from
                // file: Lines 18-32
                weapons = new ArrayList[15];
                weaponFrequencies = new ArrayList[15];
                for (int i = 0; i < 15; i++) {
                    weapons[i] = new ArrayList<Weapon>();
                    weaponFrequencies[i] = new ArrayList<Integer>();
                }
                for (int i = 0; i < 15; i++) {
                    line = decode(input.nextLine().trim(), encoded);
                    pos = line.indexOf("**");
                    if (pos >= 0)
                        line = line.substring(0, pos).trim();
                    System.out.println(line);
                    temp = line.trim().substring(1, line.length());
                    parts = temp.split(",");

                    for (int j = 0; j < parts.length; j++) {
                        if (parts.length == 1) {
                            continue;
                        }
                        
                            
                        // System.out.println(parts[j]);

                        var secondParts = parts[j].trim().split(":");
                        if (j == parts.length - 1) {
                            secondParts[1] = secondParts[1].substring(0, secondParts[1].length() - 1);
                        }
                        
                        System.out.println(secondParts[0] + " " + secondParts[1]);
                        if (secondParts.length != 2) {
                            System.out.println("Error reading weapons and weaponFrequencies from player file len");
                            return null;
                        }
                        
                        if (!wordIsInt(secondParts[1])) {

                            System.out.println("Error reading weapons and weaponFrequencies from player file int");
                            return null;
                        }
                        weapons[i].add(Weapon.getWeaponWithName(secondParts[0]));
                        weaponFrequencies[i].add(Integer.parseInt(secondParts[1]));
                    }
                }
                // **************************DONE!************************************/
                if (weapons == null || weaponFrequencies == null || weapons.length != Player.LUTE + 1
                        || weaponFrequencies.length != Player.LUTE + 1) {
                    System.out.println("Error reading weapons and weaponFrequencies from player file lute");
                    return null;
                }

                // code to read imageIndex from file: Line 33
                byte imageIndex = 0;
                line = decode(input.nextLine().trim(), encoded); // **imageIndex
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line)) {
                    // System.out.println(line);
                    System.out.println("Error reading ,hjdgfbsk");
                    return null;
                }
                imageIndex = Byte.parseByte(line);

                // code to read startStoryIndex from file: Line 34
                byte startStoryIndex = 0;
                line = decode(input.nextLine().trim(), encoded); // **startStoryIndex
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                startStoryIndex = Byte.parseByte(line);

                // code to read mapIndex from file: Line 35
                int mapIndex = 0;
                line = decode(input.nextLine().trim(), encoded); // **mapIndex
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                mapIndex = Integer.parseInt(line);

                // code to read row from file: Line 36
                int row = 0;
                line = decode(input.nextLine().trim(), encoded); // **row
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                row = Integer.parseInt(line);

                // code to read col from file: Line 37
                int col = 0;
                line = decode(input.nextLine().trim(), encoded); // **col
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                col = Integer.parseInt(line);

                // code to read worldRow from file: Line 38
                int worldRow = 0;
                line = decode(input.nextLine().trim(), encoded); // **world-row
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                worldRow = Integer.parseInt(line);

                // code to read worldCol from file: Line 39
                int worldCol = 0;
                line = decode(input.nextLine().trim(), encoded); // **world-col
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                worldCol = Integer.parseInt(line);

                // code to read locationType from file: Line 40
                String locationType = "";
                line = decode(input.nextLine().trim(), encoded); // **locationType
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                locationType = line;

                // code to read might from file: Lne 41
                int might = 0;
                line = decode(input.nextLine().trim(), encoded); // **might
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                might = Integer.parseInt(line);

                // code to read mind from file: Line 42
                int mind = 0;
                line = decode(input.nextLine().trim(), encoded); // **mind
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                mind = Integer.parseInt(line);

                // code to read agility from file: Line 43
                int agility = 0;
                line = decode(input.nextLine().trim(), encoded); // **agility
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                agility = Integer.parseInt(line);

                // code to read body from file: Line 44
                int body = 0;
                line = decode(input.nextLine().trim(), encoded); // **body
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                body = Integer.parseInt(line);

                // code to read manna from file: Line 45
                int manna = 0;
                line = decode(input.nextLine().trim(), encoded); // **manna
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                manna = Integer.parseInt(line);

                // code to read awareness from file: Line 46
                int awareness = 0;
                line = decode(input.nextLine().trim(), encoded); // **awareness
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                awareness = Integer.parseInt(line);

                // code to read reputation from file: Line 47
                int reputation = 0;
                line = decode(input.nextLine().trim(), encoded); // **reputation
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                reputation = Integer.parseInt(line);

                // code to read bounty from file: Line 48
                int bounty = 0;
                line = decode(input.nextLine().trim(), encoded); // **bounty
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                bounty = Integer.parseInt(line);

                // code to read gold from file: Line 49
                int gold = 0;
                line = decode(input.nextLine().trim(), encoded); // **gold
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                gold = Integer.parseInt(line);

                // code to read experience from file: Line 50
                int experience = 0;
                line = decode(input.nextLine().trim(), encoded); // **experience
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                experience = Integer.parseInt(line);

                // code to read expToLevelUp from file: Line 51
                int expToLevelUp = 0;
                line = decode(input.nextLine().trim(), encoded); // **experienceToLevelUp
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                expToLevelUp = Integer.parseInt(line);

                // code to read weaponSelect from file: Lne 52
                byte weaponSelect = 0;
                line = decode(input.nextLine().trim(), encoded); // **weaponSelect
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                weaponSelect = Byte.parseByte(line);

                // code to read armorSelect from file: Line 53
                byte armorSelect = 0;
                line = decode(input.nextLine().trim(), encoded); // **armorSelect
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                armorSelect = Byte.parseByte(line);

                // code to read rations from file: Line 54
                byte rations = 0;
                line = decode(input.nextLine().trim(), encoded); // **rations
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                rations = Byte.parseByte(line);

                // code to read numArrows from file: Line 55
                int numArrows = 0;
                line = decode(input.nextLine().trim(), encoded); // **numArrows
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                numArrows = Integer.parseInt(line);

                // code to read dogBasicInfo from file: Line 56
                String dogBasicInfo = "";
                line = decode(input.nextLine().trim(), encoded); // **dogBasicInfo
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                dogBasicInfo = line;

                // code to read horseBasicInfo from file: Line 57
                line = decode(input.nextLine().trim(), encoded); // **horseBasicInfo
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                String horseBasicInfo = line;

                Player ans = new Player(name, fileNames, imageIndex, startStoryIndex, mapIndex, row, col, worldRow,
                        worldCol, memory, locationType, mapMark, might, mind, agility, body, manna, awareness,
                        reputation, bounty, gold, experience, expToLevelUp, weaponSelect, armorSelect, weapons,
                        weaponFrequencies, armorSet, armorFreq, rations, items, effects, potions, flowerBoxCount,
                        spells, numArrows, dogBasicInfo, horseBasicInfo, specificExperience, stats, infoIndexes,
                        weaponHotKeys, spellHotKeys, potionHotKeys);

                int days = 0;
                line = decode(input.nextLine().trim(), encoded); // **days
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsInt(line))
                    return null;
                days = Integer.parseInt(line);

                double time = 0;
                line = decode(input.nextLine().trim(), encoded); // **time
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!wordIsDouble(line))
                    return null;
                time = Double.parseDouble(line);

                CultimaPanel.days = days;
                CultimaPanel.time = time;
                input.close();
                return ans;
            }
            return null;
        } catch (IOException ex) // file is corrupted or doesn't exist - remake the file
        {
            System.out.println("readPlayerFromFile error:" + ex);
            return null;
        }
    }

    // ***************Wardrobe Inventory
    public static void readWardrobeInventory(String fileName) {
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            while (input.hasNextLine()) {
                String line = input.nextLine().trim(); // read in name, frequency
                String[] parts = line.split(",");
                if (parts.length != 2)
                    continue;
                String name = parts[0].trim();
                if (!wordIsInt(parts[1].trim()))
                    continue;
                int freq = Integer.parseInt(parts[1]);

                Weapon weap = Weapon.getWeaponWithName(name);
                if (weap != null) {
                    CultimaPanel.wardrobeInventory.add(weap);
                    CultimaPanel.wardrobeFreq.add(freq);
                } else {
                    Armor arm = Armor.getArmorWithName(name);
                    if (arm != null) {
                        CultimaPanel.wardrobeInventory.add(arm);
                        CultimaPanel.wardrobeFreq.add(freq);
                    } else {
                        Potion ptn = Potion.getPotionWithName(name);
                        if (ptn != null) {
                            CultimaPanel.wardrobeInventory.add(ptn);
                            CultimaPanel.wardrobeFreq.add(freq);
                        } else {
                            Item itm = Item.getItemWithName(name);
                            if (itm != null) {
                                CultimaPanel.wardrobeInventory.add(itm);
                                CultimaPanel.wardrobeFreq.add(freq);
                            }
                        }
                    }
                }
            }
            input.close();
        } catch (IOException ex) // file is corrupted or doesn't exist
        {
            System.out.println("readWardrobeInventory error:" + ex);
        }
    }

    public static boolean writeOutWardrobeInventory(String fileName) {
        File imageFile = new File(fileName);
        if (imageFile.exists()) // make sure to write over old file
        {
            imageFile.delete();
            imageFile = new File(fileName);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(imageFile);
            for (int i = 0; i < CultimaPanel.wardrobeInventory.size() && i < CultimaPanel.wardrobeFreq.size(); i++)
                writer.println(
                        CultimaPanel.wardrobeInventory.get(i).getName() + "," + CultimaPanel.wardrobeFreq.get(i));
            writer.close();
        } catch (Exception ex) {
            System.out.println("writeOutWardrobeInventory error:" + ex);
            return false;
        }
        return true;
    }

    // ****************Missions (all and those underway)
    public static LinkedList<Mission> readMissionStack(String fileName) {
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            LinkedList<Mission> missionStack = new LinkedList<Mission>();
            CultimaPanel.missionsGiven = new byte[Mission.NUM_MISSIONS];
            boolean encoded = false;
            String type = "";
            String[] story = { "", "", "" };
            int worldRow = -1, worldCol = -1;
            int gold = 0;
            Item item = null;
            String clearLoc = "";
            Item target = null;
            String targetHolder = "";
            int targetRow = -1, targetCol = -1;
            Mission mission = null;
            if (input.hasNextLine()) {
                String line = input.nextLine().trim(); // read in whether or not the file is encoded, and needs to be
                                                       // decoded
                int pos = line.indexOf("**"); // this will be a random integer. If it is even, then the file is encoded.
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                int enc = 0;
                if (wordIsInt(line))
                    enc = Integer.parseInt(line);
                if (enc % 2 == 0)
                    encoded = true;
            }
            if (input.hasNextLine()) // read in missionsGiven array
            {
                String line = decode(input.nextLine().trim(), encoded); // read in mission types given, 0 or 1 for false
                                                                        // or true
                int pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                String[] parts = line.split(" ");
                if (parts.length == Mission.NUM_MISSIONS) {
                    for (int i = 0; i < parts.length; i++) {
                        String s = parts[i].trim();
                        if (!wordIsInt(s))
                            break;
                        CultimaPanel.missionsGiven[i] = Byte.parseByte(s);
                    }
                }
            }
            while (input.hasNextLine()) {
                String line = decode(input.nextLine().trim(), encoded); // read in mission type
                if (line.startsWith("**"))
                    continue;
                int pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                type = line;
                if (!input.hasNextLine())
                    break;

                line = decode(input.nextLine().trim(), encoded); // read in startStory
                if (line.startsWith("**"))
                    continue;
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                String startStory = line;
                if (!input.hasNextLine())
                    break;

                line = decode(input.nextLine().trim(), encoded); // read in middleStory
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                String middleStory = line;
                if (!input.hasNextLine())
                    break;

                line = decode(input.nextLine().trim(), encoded); // read in endStory
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                String endStory = line;
                story[0] = startStory;
                story[1] = middleStory;
                story[2] = endStory;
                if (!input.hasNextLine())
                    break;

                line = decode(input.nextLine().trim(), encoded); // read in worldRow, worldCol
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                line = line.substring(1, line.length() - 1);
                String[] parts = line.split(",");
                if (wordIsInt(parts[0].trim()) && wordIsInt(parts[1].trim())) {
                    worldRow = Integer.parseInt(parts[0].trim());
                    worldCol = Integer.parseInt(parts[1].trim());
                }
                if (!input.hasNextLine())
                    break;

                line = decode(input.nextLine().trim(), encoded); // read in gold
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                gold = 0;
                if (wordIsInt(line))
                    gold = Integer.parseInt(line);
                if (!input.hasNextLine())
                    break;

                line = decode(input.nextLine().trim(), encoded); // read in item
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                String itemInfo = line.trim();
                item = null;
                if (!itemInfo.equals("null")) {
                    parts = itemInfo.split(":");
                    if (wordIsInt(parts[1].trim())) {
                        item = new Item(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                    }
                }
                if (!input.hasNextLine())
                    break;

                line = decode(input.nextLine().trim(), encoded); // read in clearLoc
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                clearLoc = line.trim();
                if (!input.hasNextLine())
                    break;

                line = decode(input.nextLine().trim(), encoded); // read in target
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                itemInfo = line.trim();
                target = null;
                if (!itemInfo.equals("null")) {
                    parts = itemInfo.split(":");
                    if (wordIsInt(parts[1].trim())) {
                        target = new Item(parts[0].trim(), Integer.parseInt(parts[1].trim()));
                    }
                }
                if (!input.hasNextLine())
                    break;

                line = decode(input.nextLine().trim(), encoded); // read in targetHolder
                if (line.startsWith("**"))
                    continue;
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                targetHolder = line;
                String missType = type.toLowerCase();
                if (missType.contains("collect") || missType.contains("frighten") || missType.contains("assassinate")
                        || missType.contains("marry"))
                    CultimaPanel.missionTargetNames.add(targetHolder);
                if (!input.hasNextLine())
                    break;

                line = decode(input.nextLine().trim(), encoded); // read in targetRow, targetCol
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                line = line.substring(1, line.length() - 1);
                parts = line.split(",");
                if (wordIsInt(parts[0].trim()) && wordIsInt(parts[1].trim())) {
                    targetRow = Integer.parseInt(parts[0].trim());
                    targetCol = Integer.parseInt(parts[1].trim());
                }

                line = decode(input.nextLine().trim(), encoded); // read in failed
                pos = line.indexOf("**");
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                if (!line.equals("true") && !line.equals("false"))
                    break;
                boolean failed = Boolean.parseBoolean(line);

                // make new mission, add it to the missionStack
                mission = new Mission(type, story, worldRow, worldCol, gold, item, clearLoc, target, targetHolder,
                        targetRow, targetCol, failed);
                missionStack.add(mission);
            }
            input.close();
            return missionStack;
        } catch (IOException ex) // file is corrupted or doesn't exist - remake the file
        {
            System.out.println("readMissionStack error:" + ex);
            return new LinkedList<Mission>();
        }
    }

    public static boolean writeOutMissionStack(LinkedList<Mission> missionStack, String fileName) {
        File imageFile = new File(fileName);
        if (imageFile.exists()) // make sure to write over old file
        {
            imageFile.delete();
            imageFile = new File(fileName);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(imageFile);
            int missionNum = 0;
            if (CultimaPanel.ENCODE_FILES)
                writer.println(Player.rollDie(1, 1000) * 2); // starting with an even number means that we want to
                                                             // encode the strings out to the file
            else
                writer.println((Player.rollDie(1, 1000) * 2) + 1);
            for (byte num : CultimaPanel.missionsGiven)
                writer.print(num + " ");
            writer.println("**missionsGiven");
            for (Mission m : missionStack) {
                writer.println(encode("******************Mission " + missionNum, CultimaPanel.ENCODE_FILES));
                missionNum++;
                writer.println(encode(m.toString(), CultimaPanel.ENCODE_FILES));
            }
            writer.close();
        } catch (Exception ex) {
            System.out.println("writeOutMissionStack error:" + ex);
            return false;
        }
        return true;
    }

    // ***************Locations file
    // reads in all Location information on a map into an ArrayList of Locations for
    // CultimaPanel.allLocations. Expecting the format to be:
    // <name>:<row>:<col>:<Terrain mapIndex>:<Terrain value>:<teleporter>, where
    // teleporter is "(toIndex,toRow,toCol)"
    public static ArrayList<Location> readLocations(String fileName) {
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            ArrayList<Location> ans = new ArrayList<Location>();
            while (input.hasNextLine()) {
                String name = "";
                int row = 0, col = 0, fromMapIndex = 0, mapIndex = 0;
                byte value = 0;
                int toIndex = 0, toRow = 0, toCol = 0;
                String locType = "?";

                String line = input.nextLine().trim(); // read in name

                if (line.startsWith("**"))
                    continue;

                String[] parts = line.split(":");
                if (parts.length != 9) // something went wrong
                    continue;
                name = parts[0].trim(); // NAME

                if (!wordIsInt(parts[1]))
                    continue;
                row = Integer.parseInt(parts[1]); // row

                if (!wordIsInt(parts[2]))
                    continue;
                col = Integer.parseInt(parts[2]); // col

                if (!wordIsInt(parts[3]))
                    continue;
                fromMapIndex = Integer.parseInt(parts[3]); // fromMapIndex

                if (!wordIsInt(parts[4]))
                    continue;
                mapIndex = Integer.parseInt(parts[4]); // toMapIndex

                if (!wordIsInt(parts[5]))
                    continue;
                value = Byte.parseByte(parts[5]); // value

                Teleporter tele = null;
                String teleInfo = parts[6];
                if (!teleInfo.equals("null")) {
                    if (teleInfo.length() < 7 || teleInfo.charAt(0) != '('
                            || teleInfo.charAt(teleInfo.length() - 1) != ')')
                        continue;

                    teleInfo = teleInfo.substring(1, teleInfo.length() - 1); // remove () on each side
                    String[] coord = teleInfo.split(" ");
                    if (coord.length != 4)
                        continue;
                    if (!wordIsInt(coord[0]))
                        continue;
                    toIndex = Integer.parseInt(coord[0]); // toIndex
                    if (!wordIsInt(coord[1]))
                        continue;
                    toRow = Integer.parseInt(coord[1]); // toRow
                    if (!wordIsInt(coord[2]))
                        continue;
                    toCol = Integer.parseInt(coord[2]); // toCol
                    locType = coord[3].trim();
                    // public Teleporter(int ti, int tr, int tc)
                    tele = new Teleporter(toIndex, toRow, toCol, locType);
                }

                String mf = parts[7];
                ArrayList<Point> monsterFreq = new ArrayList<Point>();
                if (mf.length() < 2 || !mf.startsWith("[") || !mf.endsWith("]"))
                    continue;
                mf = mf.substring(1, mf.length() - 1); // remove () on each side
                if (mf.length() > 0) {
                    String[] points = mf.split(", ");
                    for (String p : points) {
                        int xStart = p.indexOf("[x=");
                        int xEnd = p.indexOf(",y=");
                        int yEnd = p.indexOf("]");
                        if (xStart == -1 || xEnd == -1 || yEnd == -1)
                            continue;
                        String xWord = p.substring(xStart + 3, xEnd);
                        String yWord = p.substring(xEnd + 3, yEnd);
                        if (!wordIsInt(xWord) || !wordIsInt(yWord))
                            continue;
                        int x = Integer.parseInt(xWord);
                        int y = Integer.parseInt(yWord);
                        Point toAdd = new Point(x, y);
                        monsterFreq.add(toAdd);
                    }
                }

                String rp = parts[8];
                ArrayList<Point> riddlePoints = new ArrayList<Point>();
                if (rp.length() < 2 || !rp.startsWith("[") || !rp.endsWith("]"))
                    continue;
                rp = rp.substring(1, rp.length() - 1); // remove () on each side
                if (rp.length() > 0) {
                    String[] points = rp.split(", ");
                    for (String p : points) {
                        int xStart = p.indexOf("[x=");
                        int xEnd = p.indexOf(",y=");
                        int yEnd = p.indexOf("]");
                        if (xStart == -1 || xEnd == -1 || yEnd == -1)
                            continue;
                        String xWord = p.substring(xStart + 3, xEnd);
                        String yWord = p.substring(xEnd + 3, yEnd);
                        if (!wordIsInt(xWord) || !wordIsInt(yWord))
                            continue;
                        int x = Integer.parseInt(xWord);
                        int y = Integer.parseInt(yWord);
                        Point toAdd = new Point(x, y);
                        riddlePoints.add(toAdd);
                    }
                }

                // public Terrain(String name, ArrayList<String> fileNames, int ad,
                // HashMap<String, Integer> nei, String d, int mi, byte v)
                Terrain ter = TerrainBuilder.getTerrainWithValue(value);
                // public Location(String n, int r, int c, Terrain t, Teleporter tele)
                Location loc = new Location(name, row, col, fromMapIndex, ter, tele);
                loc.setMapIndex(mapIndex);
                loc.setMonsterFreq(monsterFreq);
                loc.setRiddlePoints(riddlePoints);
                String locTerType = TerrainBuilder.locationTerrainType(ter);
                if (locTerType.equals("town"))
                    CultimaPanel.allDomiciles.add(loc);
                else if (locTerType.equals("adventure"))
                    CultimaPanel.allAdventure.add(loc);
                else if (locTerType.equals("temple"))
                    CultimaPanel.allTemples.add(loc);
                else if (locTerType.equals("battlefield"))
                    CultimaPanel.allBattlefields.add(loc);
                ans.add(loc);
            }
            input.close();
            return ans;
        } catch (IOException ex) // file is corrupted or doesn't exist - clear high scores and remake the file
        {
            System.out.println("readLocations error:" + ex);
            return null;
        }
    }

    // writes out all Location information on a map into a file in the format:
    // <name>:<row>:<col>:<Terrain mapIndex>:<Terrain value>:<teleporter>, where
    // teleporter is "(toIndex,toRow,toCol)"
    public static boolean writeOutLocationsToFile(ArrayList<Location> allLocations, String fileName) {
        File imageFile = new File(fileName);
        if (imageFile.exists()) // make sure to write over old file
        {
            imageFile.delete();
            imageFile = new File(fileName);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(imageFile);
            writer.println("** World Map Location info");
            writer.println(
                    "** name : row : col : fromMapIndex : getMapIndex : terrain : teleporter : monsterFrequencies : riddlePoints");
            for (Location loc : allLocations) {
                String line = loc.getName() + ":" + loc.getRow() + ":" + loc.getCol() + ":" + loc.getFromMapIndex()
                        + ":" + loc.getMapIndex() + ":" + (loc.getTerrain()).getValue() + ":" + loc.getTeleporter()
                        + ":" + loc.getMonsterFreq() + ":" + loc.getRiddlePoints();
                line = line.replace("java.awt.Point", "");
                writer.println(line);
            }
            writer.println();
            writer.close();
        } catch (Exception ex) {
            System.out.println("writeOutLocationsToFile error:" + ex);
            return false;
        }
        return true;
    }

    // ******************NPC Civilians
    // read in all NPC civilians into an ArrayList of ArrayLists, indexed by which
    // location index they live in
    public static void readAllNPCFile(ArrayList<ArrayList<NPCPlayer>> ans, String fileName) {
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            boolean encoded = false;
            if (input.hasNextLine()) {
                String line = input.nextLine().trim(); // read in whether or not the file is encoded, and needs to be
                                                       // decoded
                int pos = line.indexOf("**"); // this will be a random integer. If it is even, then the file is encoded.
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                int enc = 0;
                if (wordIsInt(line))
                    enc = Integer.parseInt(line);
                if (enc % 2 == 0)
                    encoded = true;
            }
            while (input.hasNextLine()) {
                String name = "";
                byte charIndex = (byte) (-1);
                int mapIndex = 0;
                int row = 0, col = 0;
                int homeRow = 0, homeCol = 0;
                char maritalStatus = 'N';
                String missionInfo = "none";
                int might = 0, mind = 0, agility = 0;
                int body = 0, reputation = 0, bounty = 0;
                boolean hasMet = false;
                int restoreDay = -1;
                int endChaseDay = -1;
                byte moveType = NPC.STILL;
                String locationType = "";
                int numInfo = 0;
                String weaponName = "";
                Weapon weapon = null;
                String armorName = "";
                Armor armor = null;
                ArrayList<Item> items = new ArrayList<Item>();
                boolean hasTrained = false;
                int gold = 0;
                String effect = "none";

                String line = decode(input.nextLine().trim(), encoded); // read in name
                if (line.startsWith("**"))
                    continue;
                String[] parts = line.split(",");
                if (parts.length != 26) // something went wrong
                    continue;
                name = parts[0].trim(); // NAME

                if (!wordIsInt(parts[1])) // charIndex
                    continue;
                charIndex = Byte.parseByte(parts[1]);

                if (!wordIsInt(parts[2])) // mapIndex
                    continue;
                mapIndex = Integer.parseInt(parts[2]);

                if (!wordIsInt(parts[3])) // row
                    continue;
                row = Integer.parseInt(parts[3]);

                if (!wordIsInt(parts[4])) // col
                    continue;
                col = Integer.parseInt(parts[4]);

                if (!wordIsInt(parts[5])) // homeRow
                    continue;
                homeRow = Integer.parseInt(parts[5]);

                if (!wordIsInt(parts[6])) // homeCol
                    continue;
                homeCol = Integer.parseInt(parts[6]);

                missionInfo = parts[7].trim(); // missionInfo

                if (!wordIsInt(parts[8])) // might
                    continue;
                might = Integer.parseInt(parts[8]);

                if (!wordIsInt(parts[9])) // mind
                    continue;
                mind = Integer.parseInt(parts[9]);

                if (!wordIsInt(parts[10])) // agility
                    continue;
                agility = Integer.parseInt(parts[10]);

                if (!wordIsInt(parts[11])) // body
                    continue;
                body = Integer.parseInt(parts[11]);

                if (!wordIsInt(parts[12])) // reputation
                    continue;
                reputation = Integer.parseInt(parts[12]);

                if (!parts[13].equals("true") && !parts[13].equals("false"))
                    parts[13] = "false";
                hasMet = Boolean.parseBoolean(parts[13]);

                if (!wordIsInt(parts[14])) // restoreDay
                    continue;
                restoreDay = Integer.parseInt(parts[14]);

                if (!wordIsInt(parts[15])) // endChaseDay
                    continue;
                endChaseDay = Integer.parseInt(parts[15]);

                if (!wordIsInt(parts[16])) // moveType
                    continue;
                moveType = Byte.parseByte(parts[16]);

                locationType = parts[17].trim(); // locationType

                if (!wordIsInt(parts[18])) // numInfo
                    continue;
                numInfo = Integer.parseInt(parts[18]);

                weaponName = parts[19].trim(); // weapon
                weapon = Weapon.getWeaponWithName(weaponName);

                armorName = parts[20].trim(); // armor
                armor = Armor.getArmorWithName(armorName);

                String it = parts[21].trim(); // items
                if (it.length() < 2)
                    continue;
                it = it.substring(1, it.length() - 1); // remove the [] on each side
                String[] itemParts = it.split(" ");
                for (String ip : itemParts) {
                    if (!ip.contains(":"))
                        continue;
                    String[] temp = ip.split(":");
                    if (temp.length != 2)
                        continue;
                    String itemName = temp[0].trim();
                    if (!wordIsInt(temp[1]))
                        continue;
                    int itemValue = Integer.parseInt(temp[1]);
                    items.add(new Item(itemName, itemValue));
                }

                if (!parts[22].equals("true") && !parts[22].equals("false"))
                    parts[22] = "false";
                hasTrained = Boolean.parseBoolean(parts[22]);

                if (!wordIsInt(parts[23])) // gold
                    parts[23] = "0";
                gold = Integer.parseInt(parts[23]);

                effect = parts[24].trim(); // effect

                maritalStatus = (parts[25].trim().charAt(0)); // maritalStatus

                // public NPCPlayer(byte index, int r, int c, int mi, String loc, int animDelay)
                NPCPlayer p = new NPCPlayer(charIndex, row, col, mapIndex, homeRow, homeCol, "");
                p.setName(name);
                p.setMissionInfo(missionInfo);
                p.setMight(might);
                p.setMind(mind);
                p.setAgility(agility);
                p.setBody(body);
                p.setReputation(reputation);
                p.setHasMet(hasMet);
                p.setRestoreDay(restoreDay);
                p.setEndChaseDay(endChaseDay);
                p.setMoveType(moveType);
                if (endChaseDay != -1 && endChaseDay >= CultimaPanel.days)
                    p.setMoveTypeTemp(NPC.CHASE);
                p.setLocationType(locationType);
                p.setNumInfo(numInfo);
                p.setWeapon(weapon);
                p.setArmor(armor);
                p.setItems(items);
                p.setHasTrained(hasTrained);
                p.setGold(gold);
                if (!effect.equals("none"))
                    p.addEffect(effect);
                p.setMaritalStatus(maritalStatus);
                while (mapIndex >= ans.size())
                    ans.add(new ArrayList<NPCPlayer>());

                ans.get(mapIndex).add(p);
            }
            input.close();
        } catch (IOException ex) // file is corrupted or doesn't exist
        {
            System.out.println("readAllNPCFile error:" + ex);
            return;
        }
    }

    public static boolean writeOutAllNPCFile(ArrayList<ArrayList<NPCPlayer>> collection, String fileName) {
        File imageFile = new File(fileName);
        if (imageFile.exists()) // make sure to write over old file
        {
            imageFile.delete();
            imageFile = new File(fileName);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(imageFile);
            if (CultimaPanel.ENCODE_FILES)
                writer.println(Player.rollDie(1, 1000) * 2); // starting with an even number means that we want to
                                                             // encode the strings out to the file
            else
                writer.println((Player.rollDie(1, 1000) * 2) + 1);
            writer.println(encode(
                    "**name,charIndex,mapIndex,row,col,homeRow,homeCol,missionIndex,might,mind,agility,body,reputation,hasMet,restoreDay,endChaseDay,origMoveType,locationType,numInfo,weapon,armor,items,gold,effect",
                    CultimaPanel.ENCODE_FILES));
            for (ArrayList<NPCPlayer> c : collection)
                for (NPCPlayer p : c)
                    writer.println(encode(p.toString(), CultimaPanel.ENCODE_FILES));
            writer.close();
        } catch (Exception ex) {
            System.out.println("writeOutAllNPCFile error:" + ex);
            return false;
        }
        return true;
    }

    // read in world-monsters and NPCs to restore into a single arraylist
    public static ArrayList<NPCPlayer> readNPCFile(String fileName) {
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            ArrayList<NPCPlayer> ans = new ArrayList<NPCPlayer>();
            boolean encoded = false;
            if (input.hasNextLine()) {
                String line = input.nextLine().trim(); // read in whether or not the file is encoded, and needs to be
                                                       // decoded
                int pos = line.indexOf("**"); // this will be a random integer. If it is even, then the file is encoded.
                if (pos >= 0)
                    line = line.substring(0, pos).trim();
                int enc = 0;
                if (wordIsInt(line))
                    enc = Integer.parseInt(line);
                if (enc % 2 == 0)
                    encoded = true;
            }
            while (input.hasNextLine()) {
                String name = "";
                byte charIndex = (byte) (-1);
                int mapIndex = 0;
                int row = 0, col = 0;
                int homeRow = 0, homeCol = 0;
                String missionInfo = "none";
                int might = 0, mind = 0, agility = 0;
                int body = 0, reputation = 0;
                boolean hasMet = false;
                int restoreDay = -1;
                int endChaseDay = -1;
                byte moveType = NPC.STILL;
                String locationType = "";
                int numInfo = 0;
                String weaponName = "";
                Weapon weapon = null;
                String armorName = "";
                Armor armor = null;
                ArrayList<Item> items = new ArrayList<Item>();
                boolean hasTrained = false;
                int gold = 0;
                String effect = "none";
                char maritalStatus = 'N';
                String line = decode(input.nextLine().trim(), encoded); // read in name
                String[] parts = line.split(",");
                if (parts.length != 26) // something went wrong
                    continue;

                name = parts[0].trim(); // NAME

                if (!wordIsInt(parts[1])) // charIndex
                    continue;
                charIndex = Byte.parseByte(parts[1]);

                if (!wordIsInt(parts[2])) // mapIndex
                    continue;
                mapIndex = Integer.parseInt(parts[2]);

                if (!wordIsInt(parts[3])) // row
                    continue;
                row = Integer.parseInt(parts[3]);

                if (!wordIsInt(parts[4])) // col
                    continue;
                col = Integer.parseInt(parts[4]);

                if (!wordIsInt(parts[5])) // homeRow
                    continue;
                homeRow = Integer.parseInt(parts[5]);

                if (!wordIsInt(parts[6])) // homeCol
                    continue;
                homeCol = Integer.parseInt(parts[6]);

                missionInfo = parts[7].trim(); // missionInfo

                if (!wordIsInt(parts[8])) // might
                    continue;
                might = Integer.parseInt(parts[8]);

                if (!wordIsInt(parts[9])) // mind
                    continue;
                mind = Integer.parseInt(parts[9]);

                if (!wordIsInt(parts[10])) // agility
                    continue;
                agility = Integer.parseInt(parts[10]);

                if (!wordIsInt(parts[11])) // body
                    continue;
                body = Integer.parseInt(parts[11]);

                if (!wordIsInt(parts[12])) // reputation
                    continue;
                reputation = Integer.parseInt(parts[12]);

                if (!parts[13].equals("true") & !parts[13].equals("false"))
                    parts[13] = "false";
                hasMet = Boolean.parseBoolean(parts[13]);

                if (!wordIsInt(parts[14])) // restoreDay
                    continue;
                restoreDay = Integer.parseInt(parts[14]);

                if (!wordIsInt(parts[15])) // endChaseDay
                    continue;
                endChaseDay = Integer.parseInt(parts[15]);

                if (!wordIsInt(parts[16])) // moveType
                    continue;
                moveType = Byte.parseByte(parts[16]);

                locationType = parts[17].trim(); // locationType

                if (!wordIsInt(parts[18])) // numInfo
                    continue;
                numInfo = Integer.parseInt(parts[18]);

                weaponName = parts[19].trim(); // weapon
                weapon = Weapon.getWeaponWithName(weaponName);

                armorName = parts[20].trim(); // armor
                armor = Armor.getArmorWithName(armorName);

                String it = parts[21].trim(); // items
                if (it.length() < 2)
                    continue;
                it = it.substring(1, it.length() - 1); // remove the [] on each side
                String[] itemParts = it.split(" ");
                for (String ip : itemParts) {
                    if (!ip.contains(":"))
                        continue;
                    String[] temp = ip.split(":");
                    if (temp.length != 2)
                        continue;
                    String itemName = temp[0].trim();
                    if (!wordIsInt(temp[1]))
                        continue;
                    int itemValue = Integer.parseInt(temp[1]);
                    items.add(new Item(itemName, itemValue));
                }

                if (!parts[22].equals("true") && !parts[22].equals("false"))
                    parts[22] = "false";
                hasTrained = Boolean.parseBoolean(parts[22]);

                if (!wordIsInt(parts[23])) // gold
                    parts[23] = "0";
                gold = Integer.parseInt(parts[23]);

                effect = parts[24].trim(); // effect

                maritalStatus = (parts[25].trim().charAt(0)); // maritalStatus

                // public NPCPlayer(byte index, int r, int c, int mi, String loc, int animDelay)
                NPCPlayer p = new NPCPlayer(charIndex, row, col, mapIndex, homeRow, homeCol, "");
                p.setName(name);
                p.setMissionInfo(missionInfo);
                p.setMight(might);
                p.setMind(mind);
                p.setAgility(agility);
                p.setBody(body);
                p.setReputation(reputation);
                p.setHasMet(hasMet);
                p.setRestoreDay(restoreDay);
                p.setEndChaseDay(endChaseDay);
                p.setMoveType(moveType);
                if (endChaseDay != -1 && endChaseDay >= CultimaPanel.days)
                    p.setMoveTypeTemp(NPC.CHASE);
                p.setLocationType(locationType);
                p.setNumInfo(numInfo);
                p.setWeapon(weapon);
                p.setArmor(armor);
                p.setItems(items);
                p.setHasTrained(hasTrained);
                p.setGold(gold);
                ans.add(p);
                if (!effect.equals("none"))
                    p.addEffect(effect);
                p.setMaritalStatus(maritalStatus);
            }
            input.close();
            return ans;
        } catch (IOException ex) // file is corrupted or doesn't exist - clear high scores and remake the file
        {
            System.out.println("readNPCFile error:" + ex);
            return new ArrayList<NPCPlayer>();
        }
    }

    public static boolean writeOutNPCFile(ArrayList<NPCPlayer> collection, String fileName) {
        File imageFile = new File(fileName);
        if (imageFile.exists()) // make sure to write over old file
        {
            imageFile.delete();
            imageFile = new File(fileName);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(imageFile);
            if (CultimaPanel.ENCODE_FILES)
                writer.println(Player.rollDie(1, 1000) * 2); // starting with an even number means that we want to
                                                             // encode the strings out to the file
            else
                writer.println((Player.rollDie(1, 1000) * 2) + 1);
            writer.println(encode(
                    "**name,charIndex,mapIndex,row,col,homeRow,homeCol,missionIndex,might,mind,agility,body,reputation,hasMet,restoreDay,endChaseDay,origMoveType,locationType,numInfo,weapon,armor,items,gold,effect,maritalStatus",
                    CultimaPanel.ENCODE_FILES));
            for (NPCPlayer c : collection)
                writer.println(encode(c.toString(), CultimaPanel.ENCODE_FILES));

            writer.close();
        } catch (Exception ex) {
            System.out.println("writeOutNPCFile error:" + ex);
            return false;
        }
        return true;
    }

    // *****************Doors file
    // writes out all Doors to close information into a file
    public static boolean writeOutDoorsToFile(String fileName) {
        File imageFile = new File(fileName);
        if (imageFile.exists()) // make sure to write over old file
        {
            imageFile.delete();
            imageFile = new File(fileName);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(imageFile);

            for (RestoreItem d : CultimaPanel.doorsToClose)
                writer.println(d);

            writer.close();
        } catch (Exception ex) {
            System.out.println("writeOutDoorsToFile error:" + ex);
            return false;
        }
        return true;
    }

    public static LinkedList<RestoreItem> readDoorsFile(String fileName) { // Door in the form of (mapIndex row col
                                                                           // value)
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            LinkedList<RestoreItem> ans = new LinkedList<RestoreItem>();
            while (input.hasNextLine()) {
                int mapIndex = 0, row = 0, col = 0;
                byte value = 0;
                int days = 0;

                String line = input.nextLine().trim(); // read in name
                if (line.length() < 11 || line.charAt(0) != '(' || line.charAt(line.length() - 1) != ')')
                    continue;

                line = line.substring(1, line.length() - 1); // remove () on each side

                String[] coord = line.split(" ");
                if (coord.length != 5)
                    continue;
                if (!wordIsInt(coord[0]))
                    continue;
                mapIndex = Integer.parseInt(coord[0]);
                if (!wordIsInt(coord[1]))
                    continue;
                row = Integer.parseInt(coord[1]);
                if (!wordIsInt(coord[2]))
                    continue;
                col = Integer.parseInt(coord[2]);
                if (!wordIsInt(coord[3]))
                    continue;
                value = Byte.parseByte(coord[3]);
                if (!wordIsInt(coord[4]))
                    continue;
                days = Integer.parseInt(coord[4]);

                ans.push(new RestoreItem(mapIndex, row, col, value));
            }
            input.close();
            return ans;
        } catch (IOException ex) // file is corrupted or doesn't exist
        {
            System.out.println("readDoorsFile error:" + ex);
            return new LinkedList<RestoreItem>();
        }
    }

    // ****************Chests file****************************************
    // writes out all chests to restore information into a file
    public static boolean writeTilesToRestore(String fileName) {
        File imageFile = new File(fileName);
        if (imageFile.exists()) // make sure to write over old file
        {
            imageFile.delete();
            imageFile = new File(fileName);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(imageFile);

            for (RestoreItem d : CultimaPanel.tilesToRestore)
                writer.println(d);

            writer.close();
        } catch (Exception ex) {
            System.out.println("writeTilesToRestore error:" + ex);
            return false;
        }
        return true;
    }

    public static ArrayList<RestoreItem> readTilesToRestore(String fileName) { // Chests to restore in the form of
                                                                               // (mapIndex row col value)
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            ArrayList<RestoreItem> ans = new ArrayList<RestoreItem>();
            while (input.hasNextLine()) {
                int mapIndex = 0, row = 0, col = 0;
                byte value = 0;
                int days = 0;

                String line = input.nextLine().trim(); // read in name
                if (line.length() < 11 || line.charAt(0) != '(' || line.charAt(line.length() - 1) != ')')
                    continue;

                line = line.substring(1, line.length() - 1); // remove () on each side

                String[] coord = line.split(" ");
                if (coord.length != 5)
                    continue;
                if (!wordIsInt(coord[0]))
                    continue;
                mapIndex = Integer.parseInt(coord[0]);
                if (!wordIsInt(coord[1]))
                    continue;
                row = Integer.parseInt(coord[1]);
                if (!wordIsInt(coord[2]))
                    continue;
                col = Integer.parseInt(coord[2]);
                if (!wordIsInt(coord[3]))
                    continue;
                value = Byte.parseByte(coord[3]);
                if (!wordIsInt(coord[4]))
                    continue;
                days = Integer.parseInt(coord[4]);
                ans.add(new RestoreItem(mapIndex, row, col, value, days));
            }
            input.close();
            return ans;
        } catch (IOException ex) // file is corrupted or doesn't exist
        {
            System.out.println("readTilesToRestore error:" + ex);
            return new ArrayList<RestoreItem>();
        }
    }

    public static boolean writeBarrelsToRestore(String fileName) {
        File imageFile = new File(fileName);
        if (imageFile.exists()) // make sure to write over old file
        {
            imageFile.delete();
            imageFile = new File(fileName);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(imageFile);

            for (RestoreItem d : CultimaPanel.barrelsToRestore)
                writer.println(d);

            writer.close();
        } catch (Exception ex) {
            System.out.println("writeBarrelsToRestore error:" + ex);
            return false;
        }
        return true;
    }

    // ****************Harvest file****************************************
    // writes out all egg harvest to restore information into a file
    public static boolean writeHarvestToRestore(String fileName) {
        File imageFile = new File(fileName);
        if (imageFile.exists()) // make sure to write over old file
        {
            imageFile.delete();
            imageFile = new File(fileName);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(imageFile);

            for (RestoreItem d : CultimaPanel.eggsToHarvest)
                writer.println(d);

            writer.close();
        } catch (Exception ex) {
            System.out.println("writeHarvestToRestore error:" + ex);
            return false;
        }
        return true;
    }

    public static ArrayList<RestoreItem> readHarvestToRestore(String fileName) { // egg harvest to restore in the form
                                                                                 // of (mapIndex row col value)
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            ArrayList<RestoreItem> ans = new ArrayList<RestoreItem>();
            while (input.hasNextLine()) {
                int mapIndex = 0, row = 0, col = 0;
                byte value = 0;
                int days = 0;

                String line = input.nextLine().trim(); // read in name
                if (line.length() < 11 || line.charAt(0) != '(' || line.charAt(line.length() - 1) != ')')
                    continue;

                line = line.substring(1, line.length() - 1); // remove () on each side

                String[] coord = line.split(" ");
                if (coord.length != 5)
                    continue;
                if (!wordIsInt(coord[0]))
                    continue;
                mapIndex = Integer.parseInt(coord[0]);
                if (!wordIsInt(coord[1]))
                    continue;
                row = Integer.parseInt(coord[1]);
                if (!wordIsInt(coord[2]))
                    continue;
                col = Integer.parseInt(coord[2]);
                if (!wordIsInt(coord[3]))
                    continue;
                value = Byte.parseByte(coord[3]);
                if (!wordIsInt(coord[4]))
                    continue;
                days = Integer.parseInt(coord[4]);
                ans.add(new RestoreItem(mapIndex, row, col, value, days));
            }
            input.close();
            return ans;
        } catch (IOException ex) // file is corrupted or doesn't exist
        {
            System.out.println("readHarvestToRestore error:" + ex);
            return new ArrayList<RestoreItem>();
        }
    }

    // ********************Map file
    public static boolean readMapFromBinFile(String fileName, byte[][] board) {
        // Files.readAllBytes java.nio.file.Files

        int r = 0;
        int c = 0;

        try {
            FileInputStream fileIs = new FileInputStream(fileName);
            ObjectInputStream is = new ObjectInputStream(fileIs);
            while (r < board.length && c < board[0].length) {
                boolean found = false;
                byte value = is.readByte();
                board[r][c] = value;
                c++;
                if (c >= board[0].length) {
                    c = 0;
                    r++;
                }

            }
            is.close();
        } catch (FileNotFoundException e) {
            System.out.println("readMapFromBinFile file not found");
            return false;
        } catch (IOException ex) {
            System.out.println("readMapFromBinFile failed to read past " + r + ":" + c + " with error:" + ex);
            return false;
        }
        return true;
    }

    public static boolean writeToBinFile(String fileName, byte[][] board) {
        int r = 0, c = 0;
        try {
            FileOutputStream fileOs = new FileOutputStream(fileName);
            ObjectOutputStream os = new ObjectOutputStream(fileOs);
            for (r = 0; r < board.length; r++)
                for (c = 0; c < board[0].length; c++) {
                    byte current = board[r][c];
                    os.writeByte(current);
                }
            // FLUSH
            os.flush();
            os.close();
            fileOs.flush();
            fileOs.close();

        } catch (FileNotFoundException e) {
            System.out.println("writeToBinFile file not found");
            return false;
        } catch (IOException ex) {
            System.out.println("writeToBinFile failed to close properly with error:" + ex);
            return false;
        }
        return true;
    }

    // ***************Map File Names
    public static ArrayList<String> readMapFileNames(String fileName) {
        ArrayList<String> fileNames = new ArrayList<String>();
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            while (input.hasNextLine()) {
                String current = input.nextLine();
                File imageFile = new File(current);
                if (imageFile.exists())
                    fileNames.add(current);
            }
            input.close();
        } catch (IOException ex) // file is corrupted or doesn't exist - clear and remake the file
        {
            System.out.println("readMapFileNames error:" + ex);
            return new ArrayList<String>();
        }
        return fileNames;

    }

    public static boolean writeOutMapFileNames(ArrayList<String> mapFileNames, String fileName) {
        File imageFile = new File(fileName);
        if (imageFile.exists()) // make sure to write over old file
        {
            imageFile.delete();
            imageFile = new File(fileName);
        }
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(imageFile);
            for (String filename : mapFileNames) {
                writer.println(filename);
            }
            writer.close();
        } catch (Exception ex) {
            System.out.println("writeOutMapFileNames error:" + ex);
            return false;
        }
        return true;
    }

    public static void saveProgress() {
        if (CultimaPanel.player.getMapIndex() == 1 && CultimaPanel.player.isInSpecialRealm())
            return;
        if (CultimaPanel.player.getBody() > 0) {
            FileManager.writeToBinFile("maps/world.bin", (CultimaPanel.map.get(0)));
            FileManager.writeOutPlayerToFile("data/player.txt");
            FileManager.writeOutWardrobeInventory("data/wardrobe.txt");
            FileManager.writeOutDoorsToFile("maps/doors.txt");
            FileManager.writeTilesToRestore("maps/worldRestore.txt");
            FileManager.writeHarvestToRestore("maps/harvestRestore.txt");
            FileManager.writeBarrelsToRestore("maps/barrelRestore.txt");
            FileManager.writeOutAllNPCFile(CultimaPanel.civilians, "maps/civilians.txt");
            FileManager.writeOutNPCFile(CultimaPanel.NPCsToRestore, "maps/NPCRestore.txt");
            FileManager.writeOutNPCFile(CultimaPanel.worldMonsters, "maps/worldMonsters.txt");
            FileManager.writeOutLocationsToFile(CultimaPanel.allLocations, "maps/worldLocs.txt");
            FileManager.writeOutMissionStack(CultimaPanel.missionStack, "data/missions.txt");
            int index = CultimaPanel.player.getMapIndex();
            if (index > 0) // save the mapping progress and destroyed map-elements if we quit inside of a
                           // Location
            {
                String filename = "maps/" + index + "_" + (CultimaPanel.map.get(index)).length + "_"
                        + (CultimaPanel.map.get(index))[0].length + ".bin";
                FileManager.writeToBinFile(filename, (CultimaPanel.map.get(index)));
            }
        }
    }

    // pre: "fileName" is the name of a real file containing lines of text - the
    // first line intended to be unused
    // post:returns a String of all the lines in <filename>.txt for testing the
    // readPlayerFromFile method
    public static String readFile(String fileName) {
        String ans = "";
        try {
            Scanner input = new Scanner(new FileReader(fileName));
            input.nextLine(); // skip the first line in the file (used for encoding/decoding)
            while (input.hasNextLine()) // while there is another line in the file
            {
                String current = input.nextLine().trim(); // read in the next Line in the file and store it in the
                                                          // String
                if (!current.contains("**days") && !current.contains("**time")) {
                    ans += current;
                }
                if (input.hasNextLine()) {
                    ans += "\n";
                }
            }
            input.close();
        } catch (IOException ex) // file is corrupted or doesn't exist - remake the file
        {
            System.out.println("readFile error:" + ex);
        }
        return ans.trim();
    }
}
